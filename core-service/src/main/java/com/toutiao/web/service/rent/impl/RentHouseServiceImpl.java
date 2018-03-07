package com.toutiao.web.service.rent.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.domain.query.RentHouseQuery;
import com.toutiao.web.service.rent.RentHouseService;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

/**
 * 业务逻辑层
 *
 */

@Service
public class RentHouseServiceImpl implements RentHouseService{


    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.zufang.rent.index}")
    private String rentIndex;
    @Value("${tt.zufang.rent.type}")
    private String rentType;



    /**
     * 据筛选条件查询(普租、公寓)
     * @param rentHouseQuery
     * @return
     */
    @Override
    public Map<String, Object> getRentHouseList(RentHouseQuery rentHouseQuery) {

        //建立连接
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = new SearchResponse();
        //校验筛选条件，根据晒选条件展示列表
        BoolQueryBuilder booleanQueryBuilder = boolQuery();//声明符合查询方法
        QueryBuilder queryBuilder = null;

        //城市
        if(rentHouseQuery.getCityId()!=null && rentHouseQuery.getCityId()!=0){
            booleanQueryBuilder.must(termQuery("city_id", rentHouseQuery.getDistrictId()));
        }
        //区域
        if(rentHouseQuery.getDistrictId()!=null && rentHouseQuery.getDistrictId() !=0){
            booleanQueryBuilder.must(termQuery("district_id",rentHouseQuery.getDistrictId()));
        }
        //商圈
        if(rentHouseQuery.getAreaId()!=null && rentHouseQuery.getAreaId()!=0){
            booleanQueryBuilder.must(termQuery("area_id", rentHouseQuery.getAreaId()));
        }
        //地铁线id
        String keys = "";
        if(rentHouseQuery.getSubwayLineId() !=null && rentHouseQuery.getSubwayLineId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_line_id", new int[]{rentHouseQuery.getSubwayLineId()}));
            keys = rentHouseQuery.getSubwayLineId().toString();
        }
        //地铁站id
        if(rentHouseQuery.getSubwayStationId()!=null && rentHouseQuery.getSubwayStationId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_station_id", new int[]{rentHouseQuery.getSubwayStationId()}));
            keys = keys+"$"+rentHouseQuery.getSubwayStationId().toString();
        }
        //附近1,3,5km
        GeoDistanceQueryBuilder location = null;
        if(StringUtil.isNotNullString(rentHouseQuery.getNear())){
            location = QueryBuilders.geoDistanceQuery("housePlotLocation")
                    .point(rentHouseQuery.getLat(), rentHouseQuery.getLon()).distance(rentHouseQuery.getNear()
                    , DistanceUnit.KILOMETERS);
        }
        //总价
        if(rentHouseQuery.getBeginPrice()!=null && rentHouseQuery.getEndPrice()!=0){
            booleanQueryBuilder.must(boolQuery().should(QueryBuilders.rangeQuery("rent_house_price")
                    .gte(rentHouseQuery.getBeginPrice()).lte(rentHouseQuery.getEndPrice())));
        }
        //租赁方式
        if(rentHouseQuery.getRt()!=null && rentHouseQuery.getRt()!=0){
            booleanQueryBuilder.must(termQuery("rent_type", rentHouseQuery.getRt()));
        }
        //户型
        if(StringUtil.isNotNullString(rentHouseQuery.getLo())){
            String[] layoutId = rentHouseQuery.getLo().split(",");
            booleanQueryBuilder.must(termQuery("room", layoutId));
        }
        //来源
        if(StringUtil.isNotNullString(rentHouseQuery.getSource())){
            String[] source = rentHouseQuery.getSource().split(",");
            booleanQueryBuilder.must(termQuery("data_source_sign", source));
        }
        //面积
        if (StringUtil.isNotNullString(rentHouseQuery.getHouseAreaSize())) {
            String area = rentHouseQuery.getHouseAreaSize().replaceAll("\\[","")
                    .replaceAll("]","").replaceAll("-",",");
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String[] layoutId = area.split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                boolQueryBuilder.should(QueryBuilders.rangeQuery("house_area").gt(layoutId[i]).lte(layoutId[i + 1]));
                booleanQueryBuilder.must(boolQueryBuilder);
            }
        }
        //朝向
        if (StringUtil.isNotNullString(rentHouseQuery.getForward())) {
            String[] layoutId = rentHouseQuery.getForward().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("forward_type", layoutId));
        }

        //暖气
        if (StringTool.isNotEmpty(rentHouseQuery.getHt())) {
            String[] ht = rentHouseQuery.getHt().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("heating_type", ht));
        }
        //特色
        if (StringTool.isNotEmpty(rentHouseQuery.getTags())) {
            String[] tag = rentHouseQuery.getTags().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("rent_house_tags_id", tag));
        }

        int pageNum = 1;
        int pageSize = 10;

        if (rentHouseQuery.getPageNum() != null && rentHouseQuery.getPageNum() > 1) {
            pageNum = rentHouseQuery.getPageNum();
        }
        if (rentHouseQuery.getPageSize() != null && rentHouseQuery.getPageSize()>= 10) {
            pageSize = rentHouseQuery.getPageSize();
        }

        searchresponse = client.prepareSearch(rentIndex).setTypes(rentType)
                .setQuery(booleanQueryBuilder).setPostFilter(location).setFetchSource(
                        new String[]{"village_name","village_id","house_area","forward","room","hall",
                                "toilet","kitchen","balcony","area_name","area_id","district_name","district_id",
                                "house_id","location","nearest_subway","rent_house_tags_name","nearby_subway","house_title_img","rent_house_price"},
                        null)
                .setFrom((pageNum-1)*rentHouseQuery.getPageSize())
                .setSize(rentHouseQuery.getPageSize())
                .execute().actionGet();

        SearchHits hits = searchresponse.getHits();
        List<Map<String,Object>> rentlist = new ArrayList<>();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map<String,String> nearbySubway = (Map<String, String>) hit.getSource().get("nearby_subway");
            String subways = nearbySubway.get(keys);
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildings.put("nearsubway",subways);
            buildings.put("pageNum",rentHouseQuery.getPageNum());
            rentlist.add(buildings);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data", rentlist);
        result.put("total", hits.getTotalHits());
        return result;
    }
}
