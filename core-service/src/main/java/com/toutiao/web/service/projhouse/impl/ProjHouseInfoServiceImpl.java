package com.toutiao.web.service.projhouse.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjHouseInfoServiceImpl implements ProjHouseInfoService {

    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类
    /*private String projhouseIndex = "a";//索引名称
    private String projhouseType = "b";//索引类*/

    /**
     * 功能描述：通过小区的经度纬度查找房源信息
     *
     * @param [lat, lon]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author zhw
     * @date 2017/12/15 11:50
     */
    @Override
    public Map<String, Object> queryProjHouseByhouseIdandLocation(double lat, double lon) {


        Map<String, Object> result = null;
        try {
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
            //从该坐标查询距离为distance      housePlotLocation
//        GeoDistanceQueryBuilder location1 = QueryBuilders.geoDistanceQuery("housePlotLocation").point(lat, lon).distance("30000000000000", DistanceUnit.METERS);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.geoDistanceQuery("housePlotLocation").point(lon, lat).distance("3000", DistanceUnit.METERS));
//        srb.setPostFilter(location1);
            // 获取距离多少公里 这个才是获取点与点之间的距离的
//        GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("housePlotLocation", lat, lon);
//        sort.unit(DistanceUnit.METERS);
//        sort.order(SortOrder.ASC);
//        sort.point(lat, lon);
            srb.setQuery(boolQueryBuilder).setFetchSource(new String[]{"houseTotalPrices", "houseId", "housePhoto", "houseType", "houseArea", "housePlotName","housePlotLocation"}, null).execute().actionGet();
            SearchResponse searchResponse = srb.setSize(10).execute().actionGet();

            SearchHits hits = searchResponse.getHits();
            String[] house = new String[(int) hits.getTotalHits()];
            System.out.println("附近的房源(" + hits.getTotalHits() + "个)：");
            ArrayList<Map<String, Object>> buildinglist = new ArrayList<>();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit hit : searchHists) {
                Map<String, Object> buildings = hit.getSource();
                buildinglist.add(buildings);
            }
            result = new HashMap<>();
            result.put("data_plot", buildinglist);
            result.put("total_plot", hits.getTotalHits());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述：随机获取数据并且根据房源级别排序
     *
     * @param [projHouseInfoRequest]
     * @return java.util.List
     * @author zhw
     * @date 2017/12/15 11:07
     */
    @Override
    public List queryProjHouseInfo(ProjHouseInfoQuery projHouseInfoRequest) {
        try {
            TransportClient client = esClientTools.init();

            SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
            SearchResponse searchresponse = null;
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();//声明符合查询方法
            String key = null;
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
                booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessName", projHouseInfoRequest.getHouseBusinessName()));
            }
            //商圈id
            if (StringTool.isNotEmpty(projHouseInfoRequest.getHouseBusinessId())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("businessAreaId", projHouseInfoRequest.getHouseBusinessId()));

            }
            String id = projHouseInfoRequest.getAreaId();
            //区域
            if (StringTool.isNotEmpty((projHouseInfoRequest.getAreaId()))) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", projHouseInfoRequest.getAreaId()));

            }
            //地铁线id
            if (StringTool.isNotEmpty(projHouseInfoRequest.getSubwayLineId())) {
                booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayLineId", projHouseInfoRequest.getSubwayLineId()));
                key = projHouseInfoRequest.getSubwayLineId();

            }
            //地铁站id
            if (StringTool.isNotEmpty(projHouseInfoRequest.getSubwayStationId())) {
                booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayStationId", projHouseInfoRequest.getSubwayStationId()));
                key = projHouseInfoRequest.getSubwayLineId() + "," + projHouseInfoRequest.getSubwayStationId();

            }
        /*//查找距离
        if (StringTool.isNotEmpty(key)) {
            booleanQueryBuilder.must(QueryBuilders.termsQuery("houseToSubwayDistance", key));
        }*/
            //范围====================
            //总价  单选
            if (StringUtil.isNotNullString(projHouseInfoRequest.getPrice())) {
                String[] layoutId = projHouseInfoRequest.getPrice().split(",");
                booleanQueryBuilder
                        .must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseTotalPrices").gte(layoutId[0]).lte(layoutId[1])));

            }
            //范围=========================
            //多选=========================
            //面积
            if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseAreaId())) {
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                String[] layoutId = projHouseInfoRequest.getHouseAreaId().split(",");
                for (int i = 0; i < layoutId.length; i = i + 2) {
                    if (i + 1 > layoutId.length) {
                        break;
                    }
                    boolQueryBuilder.should(QueryBuilders.rangeQuery("houseArea").gt(layoutId[i]).lte(layoutId[i + 1]));
                    booleanQueryBuilder.must(boolQueryBuilder);
                }
            }
            //楼龄
            if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseYearId())) {
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                String[] layoutId = projHouseInfoRequest.getHouseYearId().split(",");
                for (int i = 0; i < layoutId.length; i = i + 2) {
                    if (i + 1 > layoutId.length) {
                        break;
                    }
                    boolQueryBuilder.should(QueryBuilders.rangeQuery("houseYear").gt(layoutId[i]).lte(layoutId[i + 1]));
                    booleanQueryBuilder.must(boolQueryBuilder);

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
                booleanQueryBuilder.must(QueryBuilders.termsQuery("houseBuildingTypeId", layoutId));


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
                booleanQueryBuilder.must(QueryBuilders.termsQuery("houseHeatingId", layoutId));

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
//            System.out.println(booleanQueryBuilder);

            if (projHouseInfoRequest.getSort() != null && projHouseInfoRequest.getSort() == 1) {
                searchresponse = srb.setQuery(booleanQueryBuilder).addSort("houseTotalPrices", SortOrder.ASC)
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
                searchresponse = srb.setQuery(booleanQueryBuilder).addSort("houseTotalPrices", SortOrder.DESC)
                        .setFrom((pageNum - 1) * pageSize)
                        .setSize(pageSize)
                        .execute().actionGet();
            } else {
                searchresponse = srb.setQuery(booleanQueryBuilder).addSort("houseRank", SortOrder.DESC)
                        .setFrom((pageNum - 1) * pageSize)
                        .setSize(pageSize)
                        .execute().actionGet();
            }
            SearchHits hits = searchresponse.getHits();


            List houseList = new ArrayList();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit hit : searchHists) {
                Map<String, Object> buildings = hit.getSource();

                Class<ProjHouseInfo> entityClass = ProjHouseInfo.class;
                ProjHouseInfo instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                instance.setKey(key);
                houseList.add(instance);
            }
            return houseList;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述：通过二手房id查找房源信息
     *
     * @param [houseId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author zhw
     * @date 2017/12/15 11:50
     */
    @Override
    public Map<String, Object> queryByHouseId(Integer houseId) {

        Map<String, Object> result = null;
        try {
            TransportClient client = esClientTools.init();
            //声明符合查询方法
            BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();

            booleanQueryBuilder.must(QueryBuilders.termQuery("houseId", houseId));
            SearchResponse searchresponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                    .setQuery(booleanQueryBuilder)
                    .setSize(10)
                    .execute().actionGet();
            SearchHits hits = searchresponse.getHits();
            SearchHit[] searchHists = hits.getHits();
            List houseList = new ArrayList();
            for (SearchHit hit : searchHists) {
                Map source = hit.getSource();
                Class<ProjHouseInfo> entityClass = ProjHouseInfo.class;
                ProjHouseInfo instance = entityClass.newInstance();
                BeanUtils.populate(instance, source);
                System.out.println(instance);
                houseList.add(instance);
            }
            result = new HashMap<>();
            result.put("data_house", houseList.get(0));
            result.put("total_house", hits.getTotalHits());
            return result;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述：通过输入的搜索框信息查询数据
     *
     * @param [text]
     * @return java.util.List
     * @author zhw
     * @date 2017/12/15 15:07
     */
    @Override
    public List queryBySearchBox(String text) {
        try {
            QueryBuilder queryBuilder = null;
            TransportClient client = esClientTools.init();
            AnalyzeResponse response = esClientTools.init().admin().indices()
                    .prepareAnalyze(text)//内容
                    .setAnalyzer("ik_smart")//指定分词器
                    //.setTokenizer("standard")
                    .execute().actionGet();//执行
            List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
            BoolQueryBuilder ww = QueryBuilders.boolQuery();
            for (AnalyzeResponse.AnalyzeToken analyzeToken :tokens) {
                queryBuilder = QueryBuilders.boolQuery()
                        .should(QueryBuilders.fuzzyQuery("areaName", analyzeToken.getTerm()))
                        .should(QueryBuilders.fuzzyQuery("houseBusinessName", analyzeToken.getTerm()))
                        .should(QueryBuilders.fuzzyQuery("housePlotName", analyzeToken.getTerm()));
                ww.should(queryBuilder);
            }
            SearchResponse searchResponse = client.prepareSearch("house123")
                    .setTypes("house1234")
                    .setQuery(ww)
                   /* .addSort("houseRank", SortOrder.DESC)*/
                    .setFrom(0)
                    .setSize(10)
                    .execute().actionGet();
            List houseList = new ArrayList();
            for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                Map<String, Object> buildings = searchHit.getSource();
                Class<ProjHouseInfo> entityClass = ProjHouseInfo.class;
                ProjHouseInfo instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                houseList.add(instance);
            }
            return houseList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     * 功能描述：往es中保存数据
     * @author zhw
     * @date 2017/12/16 11:10
     * @param [projHouseInfo]
     * @return boolean
     */
    @Override
    public boolean saveProjHouseInfo(ProjHouseInfo projHouseInfo) {
        TransportClient client = esClientTools.init();
        boolean flag = true;
        String json = JSONObject.toJSONString(projHouseInfo);
        BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
        IndexRequest indexRequest = new IndexRequest(projhouseIndex, projhouseType, String.valueOf(projHouseInfo.getHouseId()))
                .version(projHouseInfo.getVersion()).versionType(VersionType.EXTERNAL).source(json);
        bulkRequestBuilder.add(indexRequest);
        BulkResponse response = bulkRequestBuilder.execute().actionGet();
        if (response.hasFailures()) {
            flag = false;
        }
        return flag;
    }


}
