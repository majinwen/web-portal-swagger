package com.toutiao.web.service.projhouse.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.admin.ProjHouseInfo;
import com.toutiao.web.dao.entity.admin.ProjHouseInfoES;
import com.toutiao.web.dao.sources.beijing.*;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.domain.query.ProjHouseInfoResponse;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
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
    @Value("${distance}")
    private Double distance;

    /**
     * 功能描述：通过小区的经度纬度查找房源信息
     * <p>
     * //     * @param [lat, lon]
     *
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
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must(QueryBuilders.geoDistanceQuery("housePlotLocation").point(lat, lon).distance(distance, DistanceUnit.METERS));
            srb.setQuery(boolQueryBuilder).setFetchSource(new String[]{"houseTotalPrices", "houseId", "housePhoto", "houseType", "buildArea", "plotName", "housePlotLocation"}, null).execute().actionGet();
            SearchResponse searchResponse = srb.setSize(10).execute().actionGet();

            SearchHits hits = searchResponse.getHits();
            String[] house = new String[(int) hits.getTotalHits()];

            ArrayList<Map<String, Object>> buildinglist = new ArrayList<>();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit hit : searchHists) {
                Map<String, Object> buildings = hit.getSource();
                //排除自身
                String housePlotLocation = (String) hit.getSource().get("housePlotLocation");
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(lat).append(lon);
                if (stringBuffer.toString().equalsIgnoreCase(housePlotLocation)) {
                   //移除指定的小区坐标
                    buildings.remove(housePlotLocation);
                }
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
     * <p>
     * //     * @param [projHouseInfoRequest]
     *
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
            //商圈名称
            if (StringTool.isNotEmpty(projHouseInfoRequest.getHouseBusinessName())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessName", projHouseInfoRequest.getHouseBusinessName()));
            }
            //商圈id
            if (StringTool.isNotEmpty(projHouseInfoRequest.getHouseBusinessId())) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("houseBusinessNameId", projHouseInfoRequest.getHouseBusinessId()));

            }
            String id = projHouseInfoRequest.getAreaId();
            //区域
            if (StringTool.isNotEmpty((projHouseInfoRequest.getAreaId()))) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("areaId", projHouseInfoRequest.getAreaId()));

            }
            //区域的名称
            if (StringTool.isNotEmpty((projHouseInfoRequest.getArea()))) {
                booleanQueryBuilder.must(QueryBuilders.termQuery("area", projHouseInfoRequest.getArea()));

            }

            //地铁线id
            if (StringTool.isNotEmpty(projHouseInfoRequest.getSubwayLineId())) {
                booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayLineId", projHouseInfoRequest.getSubwayLineId()));
                key = projHouseInfoRequest.getSubwayLineId();
            }
            //地铁站id
            if (StringTool.isNotEmpty(projHouseInfoRequest.getSubwayStationId())) {
                booleanQueryBuilder.must(QueryBuilders.termsQuery("subwayStationId", projHouseInfoRequest.getSubwayStationId()));
                key = projHouseInfoRequest.getSubwayLineId() + "$" + projHouseInfoRequest.getSubwayStationId();
            }
            //范围====================
            //总价查询
            if (projHouseInfoRequest.getBeginPrice() != null && projHouseInfoRequest.getEndPrice() != 0) {
                booleanQueryBuilder
                        .must(QueryBuilders.boolQuery().should(QueryBuilders.rangeQuery("houseTotalPrices").gte(projHouseInfoRequest.getBeginPrice()).lte(projHouseInfoRequest.getEndPrice())));

            }
            //面积
            if (StringUtil.isNotNullString(projHouseInfoRequest.getBuildAreaId())) {
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                String[] layoutId = projHouseInfoRequest.getBuildAreaId().split(",");
                for (int i = 0; i < layoutId.length; i = i + 2) {
                    if (i + 1 > layoutId.length) {
                        break;
                    }
                    boolQueryBuilder.should(QueryBuilders.rangeQuery("buildArea").gt(layoutId[i]).lte(layoutId[i + 1]));
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
            //户型(室)
            if (StringUtil.isNotNullString(projHouseInfoRequest.getRoom())) {
                String[] layoutId = projHouseInfoRequest.getRoom().split(",");
                booleanQueryBuilder.must(QueryBuilders.termsQuery("room", layoutId));
            }
            //物业类型
            if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseTypeId())) {
                String[] layoutId = projHouseInfoRequest.getHouseTypeId().split(",");
                booleanQueryBuilder.must(QueryBuilders.termsQuery("houseType", layoutId));
            }
            //建筑类型
            if (StringUtil.isNotNullString(projHouseInfoRequest.getBuildCategoryId())) {
                String[] layoutId = projHouseInfoRequest.getBuildCategoryId().split(",");
                booleanQueryBuilder.must(QueryBuilders.termsQuery("buildCategory", layoutId));
            }

            //朝向
            if (StringUtil.isNotNullString(projHouseInfoRequest.getForward())) {
                String[] layoutId = projHouseInfoRequest.getForward().split(",");
                booleanQueryBuilder.must(QueryBuilders.termsQuery("forward", layoutId));
            }
            //标签(满二，满三，满五)
            if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseLabelId())) {
                String[] layoutId = projHouseInfoRequest.getHouseLabelId().split(",");
                booleanQueryBuilder.must(QueryBuilders.termsQuery("tags", layoutId));
            }

            //楼层
            if (StringUtil.isNotNullString(projHouseInfoRequest.getHouseFloorId())) {
                String[] layoutId = projHouseInfoRequest.getHouseFloorId().split(",");
                booleanQueryBuilder.must(QueryBuilders.termsQuery("floor", layoutId));

            }
            //权属
            if (StringUtil.isNotNullString(projHouseInfoRequest.getPropertyRightId())) {
                String[] layoutId = projHouseInfoRequest.getPropertyRightId().split(",");
                booleanQueryBuilder.must(QueryBuilders.termsQuery("propertyRight", layoutId));

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
                searchresponse = srb.setQuery(booleanQueryBuilder).addSort("houseLevel", SortOrder.DESC)
                        .setFrom((pageNum - 1) * pageSize)
                        .setSize(pageSize)
                        .execute().actionGet();
            }
            SearchHits hits = searchresponse.getHits();
            List houseList = new ArrayList();
            SearchHit[] searchHists = hits.getHits();
            for (SearchHit hit : searchHists) {
                Map<String, Object> buildings = hit.getSource();
                Class<ProjHouseInfoResponse> entityClass = ProjHouseInfoResponse.class;
                ProjHouseInfoResponse instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                instance.setKey(key);
                //小区坐标
                instance.setLon(Double.valueOf(instance.getHousePlotLocation().split(",")[0]));
                instance.setLat(Double.valueOf(instance.getHousePlotLocation().split(",")[1]));
                //朝向
                String forWard = ForWardMap.getForWard(instance.getFloor());
                instance.setForwardName(forWard);
                /*//装修
                String fitment = FitmentMap.getFitment(String.valueOf(instance.getFitment()));
                instance.setFitmentName(fitment);*/
                Integer[] tags = instance.getTags();
                String[] tag = new String[tags.length];
                for (int i = 0; i < tags.length; i++) {

                    if (StringTool.isNotEmpty(tags[i])){
                        //标签
                        tag[i] = LabelMap.getLabel(String.valueOf(tags[i]));
                    }

                }
                instance.setTagsName(tag);
                /*//权属
                instance.setPropertyRightName(OwnerShipMap.getOwnership(String.valueOf(instance.getPropertyRight())));*/
                //物业类型
//                instance.setHouseTypeName(PropertyTypeMap.getPropertyType(String.valueOf(instance.getHouseType())));
//                //建筑形式
//                instance.setBuildCategoryName(ResidenceMap.getResidenceBuildCategory(instance.getBuildCategory()));
//                //电梯/
//                instance.setElevator(instance.getElevator() == null || instance.getElevator() == "" ? "无电梯" : "有电梯");
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
                Map<String, Object> buildings = hit.getSource();
                Class<ProjHouseInfoResponse> entityClass = ProjHouseInfoResponse.class;
                ProjHouseInfoResponse instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                //小区坐标
                instance.setLon(Double.valueOf(instance.getHousePlotLocation().split(",")[0]));
                instance.setLat(Double.valueOf(instance.getHousePlotLocation().split(",")[1]));
                //朝向
                String forWard = ForWardMap.getForWard(instance.getFloor());
                instance.setForwardName(forWard);
                //装修
                String fitment = FitmentMap.getFitment(String.valueOf(instance.getFitment()));
                instance.setFitmentName(fitment);
                Integer[] tags = instance.getTags();
                String[] tag = new String[tags.length];
                for (int i = 0; i < tags.length; i++) {

                    if (StringTool.isNotEmpty(tags[i])){
                        //标签
                        tag[i] = LabelMap.getLabel(String.valueOf(tags[i]));
                    }
                }
                instance.setTagsName(tag);
                //权属
                instance.setPropertyRightName(OwnerShipMap.getOwnership(String.valueOf(instance.getPropertyRight())));
                //物业类型
                instance.setHouseTypeName(PropertyTypeMap.getPropertyType(String.valueOf(instance.getHouseType())));
                //建筑形式
                instance.setBuildCategoryName(ResidenceMap.getResidenceBuildCategory(instance.getBuildCategory()));
                //电梯/
                instance.setElevator(instance.getElevator() == "1" ? "有电梯" : "无电梯");
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
     * <p>
     * //     * @param [text]
     *
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
            for (AnalyzeResponse.AnalyzeToken analyzeToken : tokens) {
                queryBuilder = QueryBuilders.boolQuery()
                        .should(QueryBuilders.fuzzyQuery("area", analyzeToken.getTerm()))
                        .should(QueryBuilders.fuzzyQuery("houseBusinessName", analyzeToken.getTerm()))
                        .should(QueryBuilders.fuzzyQuery("plotName", analyzeToken.getTerm()));
                ww.should(queryBuilder);
            }
            SearchResponse searchResponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                    .setQuery(ww)
                   /* .addSort("houseRank", SortOrder.DESC)*/
                    .setFrom(0)
                    .setSize(10)
                    .execute().actionGet();
            List houseList = new ArrayList();
            for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                Map<String, Object> buildings = searchHit.getSource();
                Class<ProjHouseInfoResponse> entityClass = ProjHouseInfoResponse.class;
                ProjHouseInfoResponse instance = entityClass.newInstance();
                BeanUtils.populate(instance, buildings);
                //小区坐标
                instance.setLon(Double.valueOf(instance.getHousePlotLocation().split(",")[0]));
                instance.setLat(Double.valueOf(instance.getHousePlotLocation().split(",")[1]));
                houseList.add(instance);
            }
            return houseList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述：往es中保存数据
     *
     * @return boolean
     * @author zhw
     * @date 2017/12/16 11:10
     * //     * @param [projHouseInfo]
     */
    @Override
    public void saveProjHouseInfo(ProjHouseInfoES projHouseInfoes) {
        TransportClient client = esClientTools.init();
        ProjHouseInfo projHouseInfo = new ProjHouseInfo();
        try {
            BeanUtils.copyProperties(projHouseInfo, projHouseInfoes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = (JSONObject) JSONObject.toJSON(projHouseInfo);
        IndexRequest indexRequest = new IndexRequest(projhouseIndex, projhouseType, String.valueOf(projHouseInfo.getHouseId()))
                .version(projHouseInfo.getVersion())
                .versionType(VersionType.EXTERNAL.versionTypeForReplicationAndRecovery())
                .source(json);
        client.index(indexRequest).actionGet();
    }


}
