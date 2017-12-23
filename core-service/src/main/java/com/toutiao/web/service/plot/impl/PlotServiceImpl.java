package com.toutiao.web.service.plot.impl;


import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.admin.ProjHouseInfoES;
import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.dao.entity.admin.VillageEntityES;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.domain.query.VillageResponse;
import com.toutiao.web.service.plot.PlotService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
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
import java.util.ArrayList;
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


    @Override
    public List GetNearByhHouseAndDistance(double lat, double lon) {
        List houseList = new ArrayList();
        try {
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
            //从该坐标查询距离为distance
            GeoDistanceQueryBuilder location1 = QueryBuilders.geoDistanceQuery("location").point(lat, lon).distance(distance, DistanceUnit.METERS);
            srb.setPostFilter(location1);
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
            //小区ID
            if (villageRequest.getId() != null) {
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("id", villageRequest.getId()));
            }
            //小区名称
            if (villageRequest.getRc() != null) {
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("rc", villageRequest.getRc()));
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
            String labelId = villageRequest.getLabelId();
            if (labelId != null && labelId.length() != 0) {
                String[] LabelId = labelId.split(",");
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                BoolQueryBuilder queryBuilder1 = booleanQueryBuilder.should(QueryBuilders.termsQuery("labelId", LabelId));
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

            //总价
            String beginPrice = villageRequest.getBeginPrice();
            String endPrice = villageRequest.getEndPrice();
            if (beginPrice != null && beginPrice.length() != 0 &&!beginPrice.equals("undefined")&&!endPrice.equals("undefined")&& endPrice != null && endPrice.length() != 0) {
                queryBuilder = boolQueryBuilder.must(QueryBuilders.rangeQuery("sumPrice").gt(Double.valueOf(beginPrice)).lte(Double.valueOf(endPrice)));
            }

            //面积
            if (StringUtil.isNotNullString(villageRequest.getAreaSize())) {
                String[] AreaSize = villageRequest.getAreaSize().split(",");
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                for (int i = 0; i < AreaSize.length; i = i + 2) {
                    if (i + 1 > AreaSize.length) {
                        break;
                    }
                    BoolQueryBuilder areaSize1 = booleanQueryBuilder.should(JoinQueryBuilders
                            .hasChildQuery(childType, QueryBuilders.rangeQuery("houseArea").gt(AreaSize[i]).lte(AreaSize[i + 1]), ScoreMode.None));
                    queryBuilder = boolQueryBuilder.must(areaSize1);
                }
            }
            //楼龄
            String age = villageRequest.getAge();
            if (age != null && age.length() != 0 && age.length() % 2 == 0) {
                String[] Age = age.split(",");
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                for (int i = 0; i < Age.length; i = i + 2) {
                    if (i + 1 > Age.length) {
                        break;
                    }
                    BoolQueryBuilder Age1 = booleanQueryBuilder.should(QueryBuilders.rangeQuery("age").gt(Age[i]).lte(Age[i + 1]));
                    queryBuilder = boolQueryBuilder.must(Age1);
                }
            }

            //物业类型
            String propertyType = villageRequest.getPropertyType();
            if (propertyType != null && propertyType.length() != 0) {
                String[] PropertyType = propertyType.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("propertyType", PropertyType));
            }
            //电梯
            String elevator = villageRequest.getElevator();
            if (elevator != null && elevator.length() != 0) {
                String[] Elevator = elevator.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("elevator", Elevator));
            }
            //建筑类型编号
            String architectureTypeId = villageRequest.getArchitectureTypeId();
            if (architectureTypeId != null && architectureTypeId.length() != 0) {
                String[] ArchitectureTypeId = architectureTypeId.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("architectureTypeId", ArchitectureTypeId));
            }
            //楼盘特色
            String villageCharacteristics = villageRequest.getVillageCharacteristics();
            if (villageCharacteristics != null && villageCharacteristics.length() != 0) {
                String[] VillageCharacteristics = villageCharacteristics.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("villageCharacteristics", VillageCharacteristics));
            }
            //供暖方式
            String heatingMode = villageRequest.getHeatingMode();
            if (heatingMode != null && heatingMode.length() != 0) {
                String[] HeatingMode = heatingMode.split(",");
                queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("heatingMode", HeatingMode));
            }
            //排序
            //均价
            if (villageRequest.getAvgPrice() != null && villageRequest.getAvgPrice().equals("2")) {
                srb.addSort("avgPrice", SortOrder.ASC);
            }

            if (villageRequest.getAvgPrice() != null && villageRequest.getAvgPrice().equals("1")) {
                srb.addSort("avgPrice", SortOrder.DESC);
            }

            //分页
            // 每页大小
            if (villageRequest.getSize() == null || villageRequest.getSize() < 1) {
                villageRequest.setSize(10);
            }
            // 当前页
            if (villageRequest.getPage() == null || villageRequest.getPage() < 1) {
                villageRequest.setPage(1);
            }

            int rows = (villageRequest.getPage() - 1) * villageRequest.getSize();
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