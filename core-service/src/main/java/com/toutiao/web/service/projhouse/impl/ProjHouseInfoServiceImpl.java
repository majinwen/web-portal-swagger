package com.toutiao.web.service.projhouse.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProjHouseInfoServiceImpl implements ProjHouseInfoService {

    @Autowired
    private ESClientTools esClientTools;
    /*@Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类*/
    private String projhouseIndex="a";//索引名称
    private String projhouseType="b";//索引类

    /**
     * 通过小区的经度纬度查找房源信息
     *
     * @param lat
     * @param lon
     * @return
     */
    @Override
    public Map<String, Object> queryProjHouseByhouseIdandLocation(double lat, double lon) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        SearchResponse searchresponse = new SearchResponse();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法
        //从该坐标查询距离为distance      housePlotLocation
        GeoDistanceQueryBuilder location1 = QueryBuilders.geoDistanceQuery("housePlotLocation").point(lat, lon).distance("30000", DistanceUnit.METERS);
        srb.setPostFilter(location1);
        // 获取距离多少公里 这个才是获取点与点之间的距离的
        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("30000", lat, lon);
        sort.unit(DistanceUnit.METERS);
        sort.order(SortOrder.ASC);
        sort.point(lat, lon);
        srb.addSort(sort).setFetchSource(new String[]{"houseTotalPrices", "houseId", "housePhoto", "houseType", "houseArea", "housePlotName"}, null).execute().actionGet();
        SearchHits hits = searchresponse.getHits();
        String[] house = new String[(int) hits.getTotalHits()];
        System.out.println("附近的房源(" + hits.getTotalHits() + "个)：");
        ArrayList<Map<String, Object>> buildinglist = new ArrayList<>();
        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map<String, Object> buildings = hit.getSource();
            buildinglist.add(buildings);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("data_plot", buildinglist);
        result.put("total_plot", hits.getTotalHits());
        return result;
    }

    /**
     * 随机获取数据并且根据房源级别排序
     *
     * @return
     */
    @Override
    public Map<String, Object> queryProjHouseInfo(ProjHouseInfoQuery projHouseInfoRequest) {
        TransportClient client = esClientTools.init();

        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        SearchResponse searchresponse  = new SearchResponse();
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法
        /*//参数都为null,则查询所有数据
        if (projHouseInfoRequest==null) {
            booleanQueryBuilder.must(QueryBuilders.matchAllQuery());
        }*/
        // 二手房ID
        /*if (StringTool.isNotEmpty(projHouseInfoRequest.getHouseId())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", projHouseInfoRequest.getHouseId()));
        }*/
        //商圈名称
        if (StringTool.isNotEmpty(projHouseInfoRequest.getHouseBusinessName())) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("businessAreaName", projHouseInfoRequest.getHouseBusinessName()));
        }
        //商圈id
        if (projHouseInfoRequest.getHouseBusinessId() != null && projHouseInfoRequest.getHouseBusinessId() != 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("businessAreaId", projHouseInfoRequest.getHouseBusinessId()));

        }
        //区域
        if (projHouseInfoRequest.getAreaId() != null && projHouseInfoRequest.getAreaId() != 0) {
            booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", projHouseInfoRequest.getAreaId()));

        }
        //地铁线id
        if (projHouseInfoRequest.getSubwayLineId() != null && projHouseInfoRequest.getSubwayLineId().length != 0) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("metroSubwayLineId", projHouseInfoRequest.getSubwayLineId()));

        }
        //地铁站id
        if (projHouseInfoRequest.getSubwayStationId() != null && projHouseInfoRequest.getSubwayStationId().length != 0) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("metroStationId", projHouseInfoRequest.getSubwayStationId()));

        }
        //范围====================
        //总价  单选
        if (projHouseInfoRequest.getBeginPrice() != null && projHouseInfoRequest.getEndPrice() != 0) {
            booleanQueryBuilder
                    .must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseTotalPrices").gte(projHouseInfoRequest.getBeginPrice()).lte(projHouseInfoRequest.getEndPrice())));

        }
        //范围=========================
        //多选=========================
        //面积
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseAreaId())) {
            String[] layoutId = projHouseInfoRequest.getHouseAreaId().split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                booleanQueryBuilder.should(QueryBuilders.rangeQuery("houseArea").gt(layoutId[i]).lte(layoutId[i + 1]));
                booleanQueryBuilder.must(booleanQueryBuilder);
            }
        }
        //楼龄
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseAreaId())) {
            String[] layoutId = projHouseInfoRequest.getHouseAreaId().split(",");
            for (int i = 0; i < layoutId.length; i = i + 2) {
                if (i + 1 > layoutId.length) {
                    break;
                }
                booleanQueryBuilder.should(QueryBuilders.rangeQuery("houseYear").gt(layoutId[i]).lte(layoutId[i + 1]));
                booleanQueryBuilder.must(booleanQueryBuilder);

            }
        }
        //户型
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseTypeId())) {
            String[] layoutId = projHouseInfoRequest.getHouseTypeId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseTypeId", layoutId));
        }
        //物业类型
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseManagementTypeId())) {
            String[] layoutId = projHouseInfoRequest.getHouseManagementTypeId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseManagementTypeId", layoutId));


        }
        //电梯
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseLiftId())) {
            String[] layoutId = projHouseInfoRequest.getHouseLiftId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseLiftId", layoutId));


        }
        //建筑类型
        if (StringUtil.isNotNullString(projHouseInfoRequest.getBuildingTypeId())) {
            String[] layoutId = projHouseInfoRequest.getBuildingTypeId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("buildingTypeId", layoutId));


        }
        //朝向houseOrientationId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseOrientationId())) {
            String[] layoutId = projHouseInfoRequest.getHouseOrientationId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseOrientationId", layoutId));


        }
        //标签(满二，满三，满五)houseLabelId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseLabelId())) {
            String[] layoutId = projHouseInfoRequest.getHouseLabelId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseLabelId", layoutId));
        }
        //楼层houseFloorId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseFloorId())) {
            String[] layoutId = projHouseInfoRequest.getHouseFloorId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseFloorId", layoutId));

        }
        //用途housePurposeId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHousePurposeId())) {
            String[] layoutId = projHouseInfoRequest.getHousePurposeId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("housePurposeId", layoutId));

        }

        //供暖houseHeatingId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseHeatingId())) {
            String[] layoutId = projHouseInfoRequest.getHouseHeatingId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("housePurposeId", layoutId));

        }

        //权属houseOwnershipId
        if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseOwnershipId())) {
            String[] layoutId = projHouseInfoRequest.getHouseOwnershipId().split(",");
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseOwnershipId", layoutId));

        }
        /**
         * 排序  0--默认（按房源级别（广告优先））--1总价升排序--2总价降排序
         */
        int pageNum = 1;
        int pageSize = 10;
        if (projHouseInfoRequest.getPageNum() != null && projHouseInfoRequest.getPageNum() > 1) {
            pageNum = projHouseInfoRequest.getPageNum();
        }
        System.out.println(booleanQueryBuilder);

        if (projHouseInfoRequest.getSort() != null && projHouseInfoRequest.getSort() == 1) {
            searchresponse= srb.setQuery(booleanQueryBuilder).addSort("houseTotalPrices", SortOrder.ASC)
                    /**
                     * 设置需要返回的参数传递到页面
                     * setFetchSource(
                     new String[]{"building_name_id", "building_name", "average_price", "building_tags", "activity_desc", "city_id",
                     "district_id", "district_name", "area_id", "area_name", "building_imgs"},
                     null)
                     */
                    .setFrom((pageNum - 1) * pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        } else if (projHouseInfoRequest.getSort() != null && projHouseInfoRequest.getSort() == 2) {
            searchresponse=srb.setQuery(booleanQueryBuilder).addSort("houseTotalPrices", SortOrder.DESC)
                    .setFrom((pageNum - 1) * pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        } else {
            searchresponse=srb.setQuery(booleanQueryBuilder).addSort("houseId", SortOrder.DESC)
                    .setFrom((pageNum - 1) * pageSize)
                    .setSize(pageSize)
                    .execute().actionGet();
        }
        SearchHits hits = searchresponse.getHits();
        ArrayList<Map<String, Object>> buildinglist = new ArrayList<>();

        SearchHit[] searchHists = hits.getHits();
        for (SearchHit hit : searchHists) {
            Map<String, Object> buildings = hit.getSource();
            buildinglist.add(buildings);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("data", buildinglist);
        result.put("total", hits.getTotalHits());

        return result;
    }

    /**
     * 通过二手房id查找房源信息
     *
     * @param houseId
     * @return
     */
    @Override
    public Map<String, Object> queryByHouseId(Integer houseId) {
        TransportClient client = esClientTools.init();
        //声明符合查询方法
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

        booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", houseId));
        SearchResponse searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder)
                .execute().actionGet();
        SearchHits hits = searchresponse.getHits();
        SearchHit[] searchHists = hits.getHits();
        ArrayList<Map<String, Object>> buildinglist = new ArrayList<>();
        for (SearchHit hit : searchHists) {
            Map<String, Object> buildings = hit.getSource();
            buildinglist.add(buildings);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("data_house", buildinglist);
        result.put("total_house", hits.getTotalHits());
        return result;
    }


}
