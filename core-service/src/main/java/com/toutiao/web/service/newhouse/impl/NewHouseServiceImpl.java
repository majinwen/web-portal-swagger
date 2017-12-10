package com.toutiao.web.service.newhouse.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.service.newhouse.NewHouseService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class NewHouseServiceImpl implements NewHouseService{

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.newhouse.index}")
    private String newhouseIndex;//索引名称
    @Value("${tt.newhouse.type}")
    private String newhouseType;//索引类型

    /**
     * 根绝新房筛选新房
     * @param newHouseQuery
     * @return
     */
    @Override
    public Map<String,Object> getNewHouse(NewHouseQuery newHouseQuery) {

        //建立连接

        TransportClient client = esClientTools.init();
        //
        SearchResponse searchresponse = new SearchResponse();
        //校验筛选条件，根据晒选条件展示列表
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法

        //城市
        if(newHouseQuery.getCityId()!=null && newHouseQuery.getCityId()!=0){
            booleanQueryBuilder.must(QueryBuilders.termQuery("city_id", newHouseQuery.getDistrictId()));
        }
        //区域
        if(newHouseQuery.getDistrictId()!=null && newHouseQuery.getDistrictId() !=0){
            booleanQueryBuilder.must(QueryBuilders.termQuery("district_id",newHouseQuery.getDistrictId()));
        }
        //商圈
        if(newHouseQuery.getAreaId()!=null && newHouseQuery.getAreaId()!=0){
            booleanQueryBuilder.must(QueryBuilders.termQuery("area_id", newHouseQuery.getAreaId()));
        }
        //地铁线id
        if(newHouseQuery.getSubwayLineId() !=null && newHouseQuery.getSubwayLineId()!=0){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("subway_line_id", new int[]{newHouseQuery.getSubwayLineId()}));
        }
        //地铁站id
        if(newHouseQuery.getSubwayLineId()!=null && newHouseQuery.getSubwayStationId()!=0){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("subway_station_id", new int[]{newHouseQuery.getSubwayStationId()}));
        }
        ///==============================
        //总价
        if(newHouseQuery.getBeginPrice()!=null && newHouseQuery.getEndPrice()!=0){
            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gte(newHouseQuery.getBeginPrice()).lte(newHouseQuery.getEndPrice())));
        }

        //户型
        if(StringUtil.isNotNullString(newHouseQuery.getLayoutId())){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("layoutId", new int[]{Integer.valueOf(newHouseQuery.getLayoutId())}));

        }

        //        //面积
//        if(null!= newHouseQuery.getHouseAreaSize() && newHouseQuery.getHouseAreaSize().length>0){
//
//
//            booleanQueryBuilder.must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("totalPrice").gt(newHouseQuery.getBeginPrice()).lt(newHouseQuery.getEndPrice())));
//        }


        ///================================
        //物业类型
        if(StringUtil.isNotNullString(newHouseQuery.getPropertyTypeId())){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("property_type_id",new int[]{Integer.valueOf(newHouseQuery.getPropertyTypeId())}));
        }

        //电梯
        if(!StringUtils.isEmpty(newHouseQuery.getElevatorFlag())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("elevator_flag", newHouseQuery.getElevatorFlag()));
        }
        //建筑类型
        if(StringUtil.isNotNullString(newHouseQuery.getBuildingType())){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("building_type_id", new int[]{Integer.valueOf(newHouseQuery.getBuildingType())}));
        }
//        //销售状态
        if(StringUtil.isNotNullString(newHouseQuery.getSaleType())){
            booleanQueryBuilder.must(QueryBuilders.termQuery("sale_status_id", newHouseQuery.getSaleType()));
        }
        //楼盘特色
        if(StringUtil.isNotNullString(newHouseQuery.getBuildingFeature())){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("building_feature_id", new int[]{Integer.valueOf(newHouseQuery.getBuildingFeature())}));
        }
        //装修
        if(StringUtil.isNotNullString(newHouseQuery.getDeliverStyle())){
            booleanQueryBuilder.must(QueryBuilders.termsQuery("redecorate_type_id", new int[]{Integer.valueOf(newHouseQuery.getDeliverStyle())}));
        }

        searchresponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType)
                .setQuery(booleanQueryBuilder).setFetchSource(
                        new String[]{"building_name_id","building_name","average_price","building_tags","activity_desc","city_id",
                                "district_id","district_name","area_id","area_name","building_imgs"},
                        null)
                .setFrom(0)
                .setSize(10)
                .execute().actionGet();
        SearchHits hits = searchresponse.getHits();
        ArrayList<Map<String,Object>> buildinglist = new ArrayList<>();

        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map<String,Object> buildings = hit.getSourceAsMap();
            buildinglist.add(buildings);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data",buildinglist);
        result.put("total", hits.getTotalHits());
        return result;
    }



}
