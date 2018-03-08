package com.toutiao.web.service.rent.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.sources.beijing.AreaMap;
import com.toutiao.web.dao.sources.beijing.DistrictMap;
import com.toutiao.web.domain.query.RentHouseQuery;
import com.toutiao.web.service.rent.RentHouseService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;

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
        //查询置顶数据

        String keys = "";
        if(rentHouseQuery.getSubwayLineId() !=null && rentHouseQuery.getSubwayLineId()!=0){
            keys = rentHouseQuery.getSubwayLineId().toString();
        }
        //地铁站id
        if(rentHouseQuery.getSubwayStationId()!=null && rentHouseQuery.getSubwayStationId()!=0){
            keys = keys+"$"+rentHouseQuery.getSubwayStationId().toString();
        }
        Map<String, Object> result = new HashMap<>();
        SearchResponse searchTopRentResponse= getTopRentHouseList(rentHouseQuery,client);
        long top_size = 0;
        List zufangList = new ArrayList();
        if(searchTopRentResponse != null){
            top_size = searchTopRentResponse.getHits().getTotalHits();
            int topInfo = searchTopRentResponse.getHits().getHits().length;
            if(topInfo == 10){

                zufangList = computeZufangList(searchTopRentResponse,rentHouseQuery,keys);

            }else if(topInfo < 10 && topInfo > 0){

                zufangList = computeZufangList(searchTopRentResponse,rentHouseQuery,keys);
                zufangList.add(computeZufangList_1(searchTopRentResponse, rentHouseQuery, client, keys));

            }else if(topInfo == 0){

                zufangList = computeZufangList_2(rentHouseQuery, client, keys,top_size);
            }
        }
        result.put("data",zufangList);
        return result;
    }


    /**
     * 整合置顶和普通房源集合方法
     * @param searchTopRentResponse
     * @return
     */
    public List computeZufangList(SearchResponse searchTopRentResponse, RentHouseQuery rentHouseQuery, String keys){

        SearchHits hits = searchTopRentResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<Map<String,Object>> zufanglist = new ArrayList<>();
        for (SearchHit hit : searchHists) {
            Map<String,String> nearbysubway = (Map<String, String>) hit.getSource().get("nearby_subway");
            String nearbysubwayKey = nearbysubway.get(keys);
            Map<String,Object> zufangMap = hit.getSourceAsMap();
            zufangMap.put("nearsubway",nearbysubwayKey);
            zufangMap.put("pageNum",rentHouseQuery.getPageNum());
            zufanglist.add(zufangMap);
        }
        return zufanglist;
    }


    public List computeZufangList_1(SearchResponse searchTopRentResponse, RentHouseQuery rentHouseQuery,
                                    TransportClient client, String keys){

        SearchResponse searchFreeRentResponse= getFreeRentHouseList(searchTopRentResponse, rentHouseQuery,
                client);
        SearchHits hits = searchFreeRentResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<Map<String,Object>> zufanglist = new ArrayList<>();
        for (SearchHit hit : searchHists) {
            Map<String,String> nearbysubway = (Map<String, String>) hit.getSource().get("nearby_subway");
            String nearbysubwayKey = nearbysubway.get(keys);
            Map<String,Object> zufangMap = hit.getSourceAsMap();
            zufangMap.put("pageNum",rentHouseQuery.getPageNum());
            zufangMap.put("nearsubway",nearbysubwayKey);
            zufanglist.add(zufangMap);
        }
        return zufanglist;
    }

    public List computeZufangList_2(RentHouseQuery rentHouseQuery,
                                    TransportClient client, String keys, long top_size){

        SearchResponse searchFreeRentResponse= getFreeRentHouseList_1(rentHouseQuery,
                client,top_size);
        SearchHits hits = searchFreeRentResponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        List<Map<String,Object>> zufanglist = new ArrayList<>();
        for (SearchHit hit : searchHists) {
            Map<String,Object> zufangMap = hit.getSourceAsMap();
            Map<String,String> nearbysubway = (Map<String, String>) hit.getSource().get("nearby_subway");
            String nearbysubwayKey = nearbysubway.get(keys);
            zufangMap.put("pageNum",rentHouseQuery.getPageNum());
            zufangMap.put("nearsubway",nearbysubwayKey);
            zufanglist.add(zufangMap);
        }
        return zufanglist;
    }




    /**
     * 普通房源过滤条件 and 返回结果集合(10<topInfo<0)
     * @param rentHouseQuery
     * @param client
     * @return
     */
    public SearchResponse getFreeRentHouseList(SearchResponse searchTopRentResponse, RentHouseQuery rentHouseQuery,
                                               TransportClient client){

        int pageNum = 1;
        int pageSize = 10;
        int count = searchTopRentResponse.getHits().getHits().length;
        if(rentHouseQuery.getPageNum()!=null && rentHouseQuery.getPageNum()>1){
            pageNum = rentHouseQuery.getPageNum();
        }
        BoolQueryBuilder booleanQueryBuilder = boolQuery();


        Map<String,Object> filter = getFilterCondition(rentHouseQuery);
        booleanQueryBuilder = (BoolQueryBuilder) filter.get("booleanQueryBuilder");
        booleanQueryBuilder.mustNot(QueryBuilders.termQuery("is_top", 1));
        GeoDistanceQueryBuilder location = (GeoDistanceQueryBuilder) filter.get("location");
        GeoDistanceSortBuilder geoDistanceSort = (GeoDistanceSortBuilder) filter.get("geoDistanceSort");

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        if(location!=null && geoDistanceSort!=null){
            searchRequestBuilder.setPostFilter(location);
            searchRequestBuilder.addSort(geoDistanceSort);
        }
        SearchResponse searchresponse = searchRequestBuilder
                .setQuery(booleanQueryBuilder).addSort("zufang_score",SortOrder.DESC)
                .setFetchSource(new String[]{"village_name","village_id","house_area","forward","room","hall",
                                "toilet","kitchen","balcony","area_name","area_id","district_name","district_id",
                                "house_id","location","nearest_subway","rent_house_tags_name","nearby_subway",
                                "house_title_img","rent_house_price"},
                        null)
                .setFrom(((0) * pageSize)*rentHouseQuery.getPageSize())
                .setSize(pageSize-count)
                .execute().actionGet();

        return searchresponse;
    }


    /**
     * 普通房源过滤条件 and 返回结果集合(topInfo==0)
     * @param rentHouseQuery
     * @param client
     * @return
     */
    public SearchResponse getFreeRentHouseList_1(RentHouseQuery rentHouseQuery, TransportClient client, long top_size){

        int pageNum = 1;
        int pageSize = 10;
        if(rentHouseQuery.getPageNum()!=null && rentHouseQuery.getPageNum()>1){
            pageNum = rentHouseQuery.getPageNum();
        }

        long zufang_from = (pageNum-1)*pageSize - top_size;
        BoolQueryBuilder booleanQueryBuilder = boolQuery();


        Map<String,Object> filter = getFilterCondition(rentHouseQuery);
        booleanQueryBuilder = (BoolQueryBuilder) filter.get("booleanQueryBuilder");
        booleanQueryBuilder.mustNot(QueryBuilders.termQuery("is_top", 1));
        GeoDistanceQueryBuilder location = (GeoDistanceQueryBuilder) filter.get("location");
        GeoDistanceSortBuilder geoDistanceSort = (GeoDistanceSortBuilder) filter.get("geoDistanceSort");

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        if(location!=null && geoDistanceSort!=null){
            searchRequestBuilder.setPostFilter(location);
            searchRequestBuilder.addSort(geoDistanceSort);
        }
        SearchResponse searchresponse = searchRequestBuilder
                .setQuery(booleanQueryBuilder).addSort("zufang_score",SortOrder.DESC)
                .setFetchSource(new String[]{"village_name","village_id","house_area","forward","room","hall",
                                "toilet","kitchen","balcony","area_name","area_id","district_name","district_id",
                                "house_id","location","nearest_subway","rent_house_tags_name","nearby_subway",
                                "house_title_img","rent_house_price"},
                        null)
                .setFrom(Integer.valueOf((int) zufang_from))
                .setSize(pageSize)
                .execute().actionGet();

        return searchresponse;
    }


    /**
     * 置顶房源过滤条件 and 返回结果集合
     * @param rentHouseQuery
     * @param client
     * @return
     */
    public SearchResponse getTopRentHouseList(RentHouseQuery rentHouseQuery,TransportClient client){

        int pageNum = 1;

        if(rentHouseQuery.getPageNum()!=null && rentHouseQuery.getPageNum()>1){
            pageNum = rentHouseQuery.getPageNum();
        }

        BoolQueryBuilder booleanQueryBuilder = boolQuery();

        //置顶数据只根据区域、商圈确定
        List<String> topKeywords = new ArrayList<>();
        if (StringTool.isNotBlank(rentHouseQuery.getDistrictId())) {
            if (StringUtil.isNotNullString(DistrictMap.getDistrict(rentHouseQuery.getDistrictId().toString()))) {
                topKeywords.add(DistrictMap.getDistrict(rentHouseQuery.getDistrictId().toString()));
            }
        }
        if (StringTool.isNotBlank(rentHouseQuery.getAreaId())) {
            if (StringUtil.isNotNullString(AreaMap.getArea(rentHouseQuery.getAreaId().toString()))) {
                topKeywords.add(AreaMap.getArea(rentHouseQuery.getAreaId().toString()));
            }
        }
        if(StringTool.isNotBlank(rentHouseQuery.getKeyword())){
            if (StringUtil.isNotNullString(DistrictMap.getDistricts(rentHouseQuery.getKeyword()))) {
                topKeywords.add(rentHouseQuery.getKeyword());
            }
            if (StringUtil.isNotNullString(AreaMap.getAreas(rentHouseQuery.getKeyword()))){
                topKeywords.add(rentHouseQuery.getKeyword());
            }
        }
        //匹配置顶关键字（区域、商圈）查询
        if(null!=topKeywords && !topKeywords.isEmpty()){
            String topkey = String.join(",", topKeywords);
            String[] key = topkey.split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("top_keyword", key));
        }
        booleanQueryBuilder.must(QueryBuilders.termQuery("is_top", 1));

        //获取公共筛选条件
        Map<String,Object> filter = getFilterCondition(rentHouseQuery);

        booleanQueryBuilder = (BoolQueryBuilder) filter.get("booleanQueryBuilder");
        GeoDistanceQueryBuilder location = (GeoDistanceQueryBuilder) filter.get("location");
        GeoDistanceSortBuilder geoDistanceSort = (GeoDistanceSortBuilder) filter.get("geoDistanceSort");

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        if(location!=null && geoDistanceSort!=null){
            searchRequestBuilder.setPostFilter(location);
            searchRequestBuilder.addSort(geoDistanceSort);
        }
        SearchResponse searchresponse = searchRequestBuilder
                .setQuery(booleanQueryBuilder).addSort("top_time",SortOrder.ASC)
                .setFetchSource(new String[]{"village_name","village_id","house_area","forward","room","hall",
                                "toilet","kitchen","balcony","area_name","area_id","district_name","district_id",
                                "house_id","location","nearest_subway","rent_house_tags_name","nearby_subway",
                                "house_title_img","rent_house_price"},
                        null)
                .setFrom((pageNum-1)*rentHouseQuery.getPageSize())
                .setSize(rentHouseQuery.getPageSize())
                .execute().actionGet();



        return searchresponse;
    }

    /**
     * 公共过滤条件
     * @param rentHouseQuery
     * @return
     */
    public Map<String,Object> getFilterCondition(RentHouseQuery rentHouseQuery){


        Map<String,Object> result = new HashMap<>();
        //声明过滤筛选条件查询方法
        BoolQueryBuilder booleanQueryBuilder = boolQuery();

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
        if(rentHouseQuery.getSubwayLineId() !=null && rentHouseQuery.getSubwayLineId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_line_id", new int[]{rentHouseQuery.getSubwayLineId()}));
        }
        //地铁站id
        if(rentHouseQuery.getSubwayStationId()!=null && rentHouseQuery.getSubwayStationId()!=0){
            booleanQueryBuilder.must(termsQuery("subway_station_id", new int[]{rentHouseQuery.getSubwayStationId()}));
        }
        //附近1,3,5km
        GeoDistanceQueryBuilder location = null;
        GeoDistanceSortBuilder geoDistanceSort = null;
        if(StringUtil.isNotNullString(rentHouseQuery.getNear())){
            location = QueryBuilders.geoDistanceQuery("housePlotLocation")
                    .point(rentHouseQuery.getLat(), rentHouseQuery.getLon()).distance(rentHouseQuery.getNear()
                            , DistanceUnit.KILOMETERS);

            geoDistanceSort = SortBuilders.geoDistanceSort("housePlotLocation", rentHouseQuery.getLat(),
                    rentHouseQuery.getLon());
            geoDistanceSort.unit(DistanceUnit.KILOMETERS);
            geoDistanceSort.order(SortOrder.ASC);
            geoDistanceSort.point(rentHouseQuery.getLat(), rentHouseQuery.getLon());
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
        booleanQueryBuilder.must(QueryBuilders.termsQuery("is_del", "0"));
        result.put("booleanQueryBuilder",booleanQueryBuilder);
        result.put("location",location);
        result.put("geoDistanceSort",geoDistanceSort);

        return result;
    }

}
