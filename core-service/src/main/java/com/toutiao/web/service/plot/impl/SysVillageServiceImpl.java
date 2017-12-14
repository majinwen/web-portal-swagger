package com.toutiao.web.service.plot.impl;


import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.dao.entity.admin.VillageEntity;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.domain.query.VillageResponse;
import com.toutiao.web.service.plot.SysVillageService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.join.query.JoinQueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysVillageServiceImpl implements SysVillageService {
    private String index = "village";
    private String type = "polt";
    private String childType = "house";
    private Double distance = 300000000.0;

    @Autowired
    private ESClientTools esClientTools;


    @Override
    public List GetNearByhHouseAndDistance(double lat, double lon) {
        List houseList = new ArrayList();
        try {
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(type);
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
            System.out.println("附近的小区(" + hits.getTotalHits() + "个)：");

            for (SearchHit hit : searchHists) {
                Map source = hit.getSource();
                Class<VillageEntity> entityClass = VillageEntity.class;
                VillageEntity instance = entityClass.newInstance();
                BeanUtils.populate(instance, source);
                System.out.println(instance);
                houseList.add(instance);
//            List<Double> location = (List<Double>) hit.getSource().get("location");
                // 获取距离值，并保留两位小数点
//            BigDecimal geoDis = new BigDecimal((Double) hit.getSortValues()[0]);
//            Map<String, Object> hitMap = hit.getSource();
//            // 在创建MAPPING的时候，属性名的不可为geoDistance。
//            hitMap.put("geoDistance", geoDis.setScale(1, BigDecimal.ROUND_HALF_DOWN));
//            String distance1 = hit.getSource().get("geoDistance") + DistanceUnit.METERS.toString();//距离
////            System.out.println(rc + "距离你的位置为：" + hit.getSource().get("geoDistance") + DistanceUnit.METERS.toString());
            }
            return houseList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List findVillageByConditions(VillageRequest villageRequest) {
        List houseList = new ArrayList();
        try {
            TransportClient client = esClientTools.init();

            String key = null;
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(type);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //小区ID
            if (villageRequest.getId() != null) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.must(QueryBuilders.termQuery("id", villageRequest.getId()));
                srb.setQuery(queryBuilder);
            }
            //区域编号
            if (villageRequest.getAreaId() != null) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("areaId", villageRequest.getAreaId()));
                srb.setQuery(queryBuilder);
            }
            //区域地名编号
            if (StringUtils.isNotBlank(villageRequest.getAreaNameId())) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("areaNameId", villageRequest.getAreaNameId()));
                srb.setQuery(queryBuilder);
            }
            //商圈编号
            if (StringUtils.isNotBlank(villageRequest.getTradingAreaId())) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("tradingAreaId", villageRequest.getTradingAreaId()));
                srb.setQuery(queryBuilder);
            }
            //地铁线路编号
            if (StringUtils.isNotBlank(villageRequest.getSearchSubwayLineId())) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("subwayLineId", villageRequest.getSearchSubwayLineId()));
                srb.setQuery(queryBuilder);
                key = villageRequest.getSearchSubwayLineId();
            }
            //地铁站编号
            if (StringUtils.isNotBlank(villageRequest.getSearchMetroStationId()) && StringUtils.isNotBlank(villageRequest.getSearchSubwayLineId())) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.should(QueryBuilders.termsQuery("metroStationId", villageRequest.getSearchMetroStationId()));
                srb.setQuery(queryBuilder);
                key = villageRequest.getSearchSubwayLineId() + "_" + villageRequest.getSearchMetroStationId();
            }
            //标签
            String[] labelId = villageRequest.getLabelId();
            if (labelId != null && labelId.length != 0) {
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                BoolQueryBuilder queryBuilder = booleanQueryBuilder.should(QueryBuilders.termsQuery("labelId", labelId));
                BoolQueryBuilder must = boolQueryBuilder.must(queryBuilder);
                srb.setQuery(must);
            }

            //均价
            Integer[] searchAvgPrice = villageRequest.getSearchAvgPrice();
            if (searchAvgPrice != null && searchAvgPrice.length != 0) {
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                for (int i = 0; i < searchAvgPrice.length; i = i + 2) {
                    if (i + 1 > searchAvgPrice.length) {
                        break;
                    }
                    BoolQueryBuilder avgPrice = booleanQueryBuilder.should(QueryBuilders.rangeQuery("avgPrice").gt(searchAvgPrice[i]).lte(searchAvgPrice[i + 1]));
                    BoolQueryBuilder must = boolQueryBuilder.must(avgPrice);
                    srb.setQuery(must);
                }
            }
            //面积
            String[] areaSize = villageRequest.getSearchAreaSize();
            if (areaSize != null && areaSize.length != 0) {
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                for (int i = 0; i < areaSize.length; i = i + 2) {
                    if (i + 1 > areaSize.length) {
                        break;
                    }
                    BoolQueryBuilder areaSize1 = booleanQueryBuilder.should(JoinQueryBuilders
                            .hasChildQuery(childType, QueryBuilders.rangeQuery("areaSize").gt(areaSize[i]).lte(areaSize[i + 1]), ScoreMode.None));
                    BoolQueryBuilder must = boolQueryBuilder.must(areaSize1);
                    srb.setQuery(must);
                }
            }
            //楼龄
            Integer[] searchAge = villageRequest.getSearchAge();
            if (searchAge != null && searchAge.length != 0) {
                BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
                for (int i = 0; i < searchAge.length; i = i + 2) {
                    if (i + 1 > searchAge.length) {
                        break;
                    }
                    BoolQueryBuilder age = booleanQueryBuilder.should(QueryBuilders.rangeQuery("age").gt(searchAge[i]).lte(searchAge[i + 1]));
                    BoolQueryBuilder must = boolQueryBuilder.must(age);
                    srb.setQuery(must);
                }
            }
            //物业类型
            String[] searchPropertyType = villageRequest.getSearchPropertyType();
            if (searchPropertyType != null && searchPropertyType.length != 0) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("propertyType", searchPropertyType));
                srb.setQuery(queryBuilder);
            }
            //电梯
            String[] searchElevator = villageRequest.getSearchElevator();
            if (searchElevator != null && searchElevator.length != 0) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("elevator", searchElevator));
                srb.setQuery(queryBuilder);
            }
            //建筑类型
            String[] architectureTypeId = villageRequest.getArchitectureTypeId();
            if (architectureTypeId != null && architectureTypeId.length != 0) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("architectureTypeId", architectureTypeId));
                srb.setQuery(queryBuilder);
            }
            //楼盘特色
            String[] searchVillageCharacteristics = villageRequest.getSearchVillageCharacteristics();
            if (searchVillageCharacteristics != null && searchVillageCharacteristics.length != 0) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("villageCharacteristics", searchVillageCharacteristics));
                srb.setQuery(queryBuilder);
            }
            //供暖方式
            String[] searchHeatingMode = villageRequest.getSearchHeatingMode();
            if (searchHeatingMode != null && searchHeatingMode.length != 0) {
                BoolQueryBuilder queryBuilder = boolQueryBuilder.must(QueryBuilders.termsQuery("heatingMode", searchHeatingMode));
                srb.setQuery(queryBuilder);
            }
            //排序
            //均价
//        srb.addSort("level",SortOrder.DESC);
            srb.addSort("avgPrice", SortOrder.ASC);
            //分页
            villageRequest.setSize(10);
            if (villageRequest.getPage() == null) {
                villageRequest.setPage(1);
            }
            int rows = (villageRequest.getPage() - 1) * villageRequest.getSize();
            Integer size = villageRequest.getSize();
            srb.setFrom(rows).setSize(size);
            SearchResponse response = srb.execute().actionGet();
            SearchHits hits = response.getHits();
            SearchHit[] searchHists = hits.getHits();

            for (SearchHit hit : searchHists) {
                Map source = hit.getSource();
                Class<VillageResponse> entityClass = VillageResponse.class;
                VillageResponse instance = entityClass.newInstance();
                BeanUtils.populate(instance, source);
                instance.setKey(key);
                houseList.add(instance);
//            System.out.println(instance);
            }
            return houseList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}