package com.toutiao.web.service.plot.impl;


import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.admin.ProjHouseInfoES;
import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.dao.entity.admin.VillageEntityES;
import com.toutiao.web.dao.entity.officeweb.PlotRatio;
import com.toutiao.web.dao.mapper.officeweb.PlotRatioMapper;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.domain.query.VillageResponse;
import com.toutiao.web.service.plot.PlotService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PlotServiceImpl implements PlotService {
    @Value("${plot.index}")
    private String index;
    @Value("${plot.parent.type}")
    private String parentType;
    @Value("${plot.child.type}")
    private String childType;
    @Value("${distance}")
    private Double distance;

    @Autowired
    private ESClientTools esClientTools;
    @Autowired
    private PlotRatioMapper plotRatioMapper;

    @Override
    public List GetNearByhHouseAndDistance(double lat, double lon) {
        List houseList = new ArrayList();
        try {
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
            //从该坐标查询距离为distance
            GeoDistanceQueryBuilder location1 = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(distance, DistanceUnit.METERS);
            srb.setPostFilter(location1).setSize(5);
            // 获取距离多少公里 这个才是获取点与点之间的距离的
            GeoDistanceSortBuilder sort = SortBuilders.geoDistanceSort("location", lat, lon);
            sort.unit(DistanceUnit.METERS);
            sort.order(SortOrder.ASC);
            sort.point(lat, lon);
            srb.addSort(sort);

            SearchResponse searchResponse = srb.execute().actionGet();
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHists = hits.getHits();
            String[] house = new String[(int) hits.getTotalHits()];


            for (SearchHit hit : searchHists) {
                Map source = hit.getSource();
                Class<VillageEntity> entityClass = VillageEntity.class;
                VillageEntity instance = entityClass.newInstance();
                BeanUtils.populate(instance, source);
                String location = lat + "," + lon;
                if (instance.getLocation() != null) {
                    String[] split = instance.getLocation().split(",");
                    if (Double.valueOf(split[0]) != lat & Double.valueOf(split[1]) != lon) {
                        houseList.add(instance);
                    }
                }
//                 获取距离值，并保留两位小数点
                BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[0]);
                Map<String, Object> hitMap = hit.getSource();
                // 在创建MAPPING的时候，属性名的不可为geoDistance。
                hitMap.put("geoDistance", geoDis.setScale(1, BigDecimal.ROUND_HALF_DOWN));
                String distance1 = hit.getSource().get("geoDistance") + DistanceUnit.METERS.toString();//距离
//                System.out.println("距离你的位置为：" + hit.getSource().get("geoDistance") + DistanceUnit.METERS.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return houseList;
    }


    @Override
    public List findVillageByConditions(VillageRequest villageRequest) {
        List houseList = new ArrayList();
        BoolQueryBuilder queryBuilder = null;
        try {
            TransportClient client = esClientTools.init();

            String key = null;
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //默认查询均格大于零
//            if (villageRequest.getAvgPrice()==null){
//                villageRequest.setAvgPrice("0,10000000");
//            }else {
//                villageRequest.setAvgPrice("0,10000000,"+villageRequest.getAvgPrice());
//            }
            //小区ID
            if (villageRequest.getId() != null) {
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("id", villageRequest.getId()));
            }
            //小区名称
            if (null != villageRequest.getKeyword()) {
//                queryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("rc", villageRequest.getRc()));
                AnalyzeResponse response = esClientTools.init().admin().indices()
                        .prepareAnalyze(villageRequest.getKeyword())//内容
                        .setAnalyzer("ik_smart")//指定分词器3`3
                        .execute().actionGet();//执行
                List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
                for (AnalyzeResponse.AnalyzeToken analyzeToken :tokens) {
                    queryBuilder = QueryBuilders.boolQuery()
                            .should(QueryBuilders.fuzzyQuery("rc", analyzeToken.getTerm()))
                            .should(QueryBuilders.fuzzyQuery("area", analyzeToken.getTerm()))
                            .should(QueryBuilders.fuzzyQuery("tradingArea", analyzeToken.getTerm()));

                    queryBuilder = boolQueryBuilder.should(queryBuilder);
                }


            }
            //区域编号
            if (villageRequest.getDistrictId() != null) {
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("areaId", villageRequest.getDistrictId()));
            }
            //商圈编号
            if (StringUtils.isNotBlank(villageRequest.getAreaId())) {
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("tradingAreaId", villageRequest.getAreaId()));
            }
            //地铁线路编号
            String subwayLineId = villageRequest.getSubwayLineId();
            String[] SubwayLineId = null;
            if (subwayLineId != null && subwayLineId.length() != 0) {
                SubwayLineId = subwayLineId.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("subwayLineId", SubwayLineId[0]));
                key = SubwayLineId[0];
            }
            //地铁站编号
            String subwayStationId = villageRequest.getSubwayStationId();
            if (subwayLineId != null && subwayLineId.length() != 0 && subwayStationId != null && subwayStationId.length() != 0) {
                String[] SubwayStationId = subwayStationId.split(",");
                queryBuilder = boolQueryBuilder.should(QueryBuilders.termsQuery("metroStationId", SubwayStationId[0]));
                key = SubwayLineId[0] + "$" + SubwayStationId[0];
            }
            //标签
            String buildingFeature = villageRequest.getBuildingFeature();
            if (buildingFeature != null && buildingFeature.length() != 0) {
                String[] BuildingFeature = buildingFeature.split(",");
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                BoolQueryBuilder queryBuilder1 = booleanQueryBuilder.should(QueryBuilders.termsQuery("labelId", BuildingFeature));
                queryBuilder = boolQueryBuilder.must(queryBuilder1);
            }

            //均价
            String avgPrice = villageRequest.getAvgPrice();
            if (avgPrice != null && avgPrice.length() != 0) {
                String[] AvgPrice = avgPrice.split(",");
                if (AvgPrice.length % 2 == 0) {
                    BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                    for (int i = 0; i < AvgPrice.length; i = i + 2) {
                        if (i + 1 > AvgPrice.length) {
                            break;
                        }
                        BoolQueryBuilder AvgPrice1 = booleanQueryBuilder.should(QueryBuilders.rangeQuery("avgPrice").gt(AvgPrice[i]).lte(AvgPrice[i + 1]));
                        queryBuilder = boolQueryBuilder.must(AvgPrice1);
                    }
                }
            }

            //列表页均价搜索
            String beginPrice = villageRequest.getBeginPrice();
            String endPrice = villageRequest.getEndPrice();
            if (beginPrice != null && beginPrice.length() != 0 &&!beginPrice.equals("undefined")&&!endPrice.equals("undefined")&& endPrice != null && endPrice.length() != 0) {
                queryBuilder = boolQueryBuilder.must(QueryBuilders.rangeQuery("avgPrice").gt(Double.valueOf(beginPrice)).lte(Double.valueOf(endPrice)));
            }

            //面积
            if (StringUtil.isNotNullString(villageRequest.getHouseAreaSize())) {
                String area = villageRequest.getHouseAreaSize().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");
                String[] houseAreaSize = area.split(",");
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                for (int i = 0; i < houseAreaSize.length; i = i + 2) {
                    if (i + 1 > houseAreaSize.length) {
                        break;
                    }
                    BoolQueryBuilder areaSize1 = booleanQueryBuilder.should(JoinQueryBuilders
                            .hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea").gt(houseAreaSize[i]).lte(houseAreaSize[i + 1]), ScoreMode.None));
                    queryBuilder = boolQueryBuilder.must(areaSize1);
                }
            }
            //楼龄
            if (StringUtil.isNotNullString(villageRequest.getAge())) {
                String age = villageRequest.getAge().replaceAll("\\[","").replaceAll("]","").replaceAll("-",",");
                String[] Age = age.split(",");
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
//                for (int i = 0; i < Age.length; i = i + 2) {
//                    if (i + 1 > Age.length) {
//                        break;
//                    }
                    BoolQueryBuilder Age1 = booleanQueryBuilder.must(QueryBuilders.rangeQuery("age")
                            .gt(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())),Integer.valueOf(Age[1]))))
                            .lte(String.valueOf(Math.subtractExact(Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())),Integer.valueOf(Age[0])))));
                    queryBuilder = boolQueryBuilder.must(Age1);
//                }
            }

            //物业类型ID
            String propertyTypeId = villageRequest.getPropertyTypeId();
            if (propertyTypeId != null && propertyTypeId.length() != 0) {
                String[] PropertyTypeId = propertyTypeId.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("propertyType", PropertyTypeId));
            }
            //电梯
            String elevatorFlag = villageRequest.getElevatorFlag();
            if (elevatorFlag != null && elevatorFlag.length() != 0) {
                String[] ElevatorFlag = elevatorFlag.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("elevator", ElevatorFlag));
            }
            //建筑类型编号
            String buildingType = villageRequest.getBuildingType();
            if (buildingType != null && buildingType.length() != 0) {
                String[] BuildingType = buildingType.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("architectureTypeId", BuildingType));
            }
            //楼盘特色
            String villageCharacteristics = villageRequest.getVillageCharacteristics();
            if (villageCharacteristics != null && villageCharacteristics.length() != 0) {
                String[] VillageCharacteristics = villageCharacteristics.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("villageCharacteristics", VillageCharacteristics));
            }
            //装修标准ID
            String deliverStyle = villageRequest.getDeliverStyle();
            if (deliverStyle != null && deliverStyle.length() != 0) {
                String[] DeliverStyle = deliverStyle.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("decorationType", DeliverStyle));
            }
            //供暖方式
            String heatingMode = villageRequest.getHeatingMode();
            if (heatingMode != null && heatingMode.length() != 0) {
                String[] HeatingMode = heatingMode.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("heatingMode", HeatingMode));
            }

            //小区默认排序
            //级别从小到大  分数由小到大  先发布后发布
            srb.addSort("level",SortOrder.ASC).addSort("plotScore",SortOrder.DESC).addSort("is_approve",SortOrder.DESC);

            //排序
            //均价
            if (villageRequest.getSort() != null && villageRequest.getSort().equals("2")) {
                srb.addSort("avgPrice", SortOrder.ASC);
            }

            if (villageRequest.getSort() != null && villageRequest.getSort().equals("1")) {
                srb.addSort("avgPrice", SortOrder.DESC);
            }

            //分页
            // 每页大小
            if (villageRequest.getSize() == null || villageRequest.getSize() < 1) {
                villageRequest.setSize(10);
            }
            // 当前页
            if (villageRequest.getPageNum() == null || villageRequest.getPageNum() < 1) {
                villageRequest.setPageNum(1);
            }

            int rows = (villageRequest.getPageNum() - 1) * villageRequest.getSize();
            Integer size = villageRequest.getSize();
            srb.setFrom(rows).setSize(size);
            SearchResponse response = srb.setQuery(queryBuilder).execute().actionGet();
            SearchHit[] searchHists = response.getHits().getHits();

            if (searchHists != null) {
                for (SearchHit hit : searchHists) {
                    Map source = hit.getSource();
                    Class<VillageResponse> entityClass = VillageResponse.class;
                    VillageResponse instance = entityClass.newInstance();
                    BeanUtils.populate(instance, source);
                    instance.setKey(key);
                    if ("商电".equals(instance.getElectricSupply())){
                        instance.setElectricFee("1.33");
                    }else {
                        instance.setElectricFee("0.48");
                    }
                    if ("商水".equals(instance.getWaterSupply())){
                        instance.setWaterFee("6");
                    }else {
                        instance.setWaterFee("5");
                    }
                    PlotRatio plotRatio = plotRatioMapper.selectByPrimaryKey(instance.getId());
                    instance.setTongbi(Double.valueOf(plotRatio.getTongbi()));
                    instance.setHuanbi(Double.valueOf(plotRatio.getHuanbi()));
                    houseList.add(instance);
//            System.out.println(instance);
                }
            }
        } catch
                (Exception e) {
            e.printStackTrace();
        }
        return houseList;
    }

    @Override
    public void saveParent(VillageEntityES village) {
        TransportClient client = esClientTools.init();
        VillageEntity villageEntity = new VillageEntity();
//        String jsonStr  = JSONObject.toJSONString(village);
//        JSONObject json = JSONObject.parseObject(jsonStr);
        org.springframework.beans.BeanUtils.copyProperties(village, villageEntity);
        JSONObject json = (JSONObject) JSONObject.toJSON(villageEntity);
        String id = String.valueOf(village.getId());
        IndexRequest indexRequest = new IndexRequest(index, parentType, id)
                .version(village.getVersion())
                .versionType(VersionType.EXTERNAL.versionTypeForReplicationAndRecovery())
                .source(json);
//        UpdateRequest updateRequest = new UpdateRequest(index, parentType, id)
//                .doc(json)
//                .upsert(indexRequest);
        client.index(indexRequest).actionGet();
    }

    @Override
    public void saveChild(ProjHouseInfoES projHouseInfoes) {
        TransportClient client = esClientTools.init();
        String jsonStr = JSONObject.toJSONString(projHouseInfoes);
        JSONObject json = JSONObject.parseObject(jsonStr);
//        BulkRequestBuilder bulkRequest = client.prepareBulk();
        String id = String.valueOf(projHouseInfoes.getHouseId());
        IndexRequest indexRequest = new IndexRequest(index, childType, id)
                .parent(String.valueOf(projHouseInfoes.getHousePlotId()))
                .version(projHouseInfoes.getVersion())
                .versionType(VersionType.EXTERNAL.versionTypeForReplicationAndRecovery())
                .source(json);
//        UpdateRequest updateRequest = new UpdateRequest(index, childType, id)
//                .parent(String.valueOf(projHouseInfo.getHousePlotId()))
//                .doc(json)
//                .upsert(indexRequest);
//        bulkRequest.add(indexRequest);
//        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//        return bulkResponse.hasFailures();
        client.index(indexRequest).actionGet();
    }
}