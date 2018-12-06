package com.toutiao.app.dao.rent.impl;

import com.toutiao.app.dao.rent.RentEsDao;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RentEsDaoImpl implements RentEsDao {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Value("${tt.zufang.rent.index}")
    private String rentIndex;
    @Value("${tt.zufang.rent.type}")
    private String rentType;
    private static final Integer ZHENGZU = 1;
    private static final Integer HEZU = 2;


    @Override
    public SearchResponse queryRentListByPlotId(BoolQueryBuilder booleanQueryBuilder, Integer from, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentHouseType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).sort("sortingScore", SortOrder.DESC).from(from);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResponse;
    }

    @Override
    public SearchResponse queryRentByRentId(BoolQueryBuilder booleanQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentHouseType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse queryNearHouseByLocation(BoolQueryBuilder boolQueryBuilder, GeoDistanceQueryBuilder location, GeoDistanceSortBuilder sort, Integer from, Integer size) {

        SearchRequest searchRequest = new SearchRequest(rentIndex).types(rentType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).from(from).size(size).postFilter(location).sort(sort);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse queryRentNumByPlotId(BoolQueryBuilder boolQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentHouseIndex(city)).types(ElasticCityUtils.getRentHouseType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).aggregation(AggregationBuilders.filter("ZHENGZU", QueryBuilders.termQuery("rent_type", ZHENGZU)))
                .aggregation(AggregationBuilders.filter("HEZU", QueryBuilders.termQuery("rent_type", HEZU)));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse queryRentCountByPlotId(BoolQueryBuilder boolQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse queryRentList(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort("sortingScore", SortOrder.DESC).from(from).size(size).fetchSource(
                new String[]{"house_desc", "house_id", "area_id", "house_title", "rent_house_price", "rent_type_name", "house_area", "room", "hall", "forward",
                        "district_name", "area_name", "zufang_name", "zufang_id", "rent_house_tags_name",
                        "house_title_img", "estate_agent", "brokerage_agency", "phone", "agent_headphoto", "userId", "rent_type", "rentHouseType",
                        "nearest_subway", "rent_house_img"}, null);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse queryRecommendRentList(BoolQueryBuilder boolQueryBuilder, String uid, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (!uid.equals("0")) {
            searchSourceBuilder.searchAfter(new String[]{uid});
        }
        searchSourceBuilder.query(boolQueryBuilder).sort("_uid", SortOrder.DESC).size(1).fetchSource(
                new String[]{"house_desc", "house_id", "area_id", "house_title", "rent_house_price", "rent_type_name", "house_area", "room", "hall", "forward",
                        "district_name", "area_name", "zufang_name", "zufang_id", "rent_house_tags_name", "house_title_img", "estate_agent", "brokerage_agency",
                        "phone", "agent_headphoto", "userId", "rent_type", "rentHouseType", "nearest_subway", "rent_house_img"}, null);

        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse queryNearRentHouse(FunctionScoreQueryBuilder query, Integer from, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query).from(from);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResponse;
    }

    @Override
    public SearchResponse queryRentSearchList(FunctionScoreQueryBuilder query, Integer distance, String keyword, Integer pageNum, Integer pageSize, String city, GeoDistanceSortBuilder geoDistanceSort, String sort) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if ((null != keyword && !"".equals(keyword)) || (null != distance && distance > 0)) {
            //searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort(geoDistanceSort);
            if ("1".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("update_time", SortOrder.DESC).sort(geoDistanceSort);
            } else if ("3".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("rent_house_price", SortOrder.ASC).sort(geoDistanceSort);
            } else if ("4".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("rent_house_price", SortOrder.DESC).sort(geoDistanceSort);
            } else if ("6".equals(sort)) {
                searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort("house_area", SortOrder.DESC).sort(geoDistanceSort);
            } else {
                if (StringTool.isNotEmpty(geoDistanceSort)) {
                    searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort(geoDistanceSort);
                } else {
                    searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize);
                }
            }
        } else {
            //searchSourceBuilder.query(query).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            if ("1".equals(sort)) {
                searchSourceBuilder.query(query).sort("update_time", SortOrder.DESC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            } else if ("3".equals(sort)) {
                searchSourceBuilder.query(query).sort("rent_house_price", SortOrder.ASC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            } else if ("4".equals(sort)) {
                searchSourceBuilder.query(query).sort("rent_house_price", SortOrder.DESC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            } else if ("6".equals(sort)) {
                searchSourceBuilder.query(query).sort("house_area", SortOrder.DESC).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            } else {
                searchSourceBuilder.query(query).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
            }
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;

    }

    @Override
    public SearchResponse querySimilarRentSearchList(BoolQueryBuilder boolQueryBuilder, String city, GeoDistanceSortBuilder geoDistanceSort) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(3).sort("update_time", SortOrder.DESC).sort(geoDistanceSort);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    @Override
    public SearchResponse queryCommuteRentSearchList(FunctionScoreQueryBuilder query, Integer distance, String keyword, Integer pageNum, Integer pageSize, String city, GeoDistanceSortBuilder sort) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if ((null != keyword && !"".equals(keyword)) || null != distance) {
            searchSourceBuilder.query(query).sort(sort).from((pageNum - 1) * pageSize).size(pageSize);
        } else {
            searchSourceBuilder.query(query).sort(sort).sort("sortingScore", SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }

    /**
     * 获取小区出租房源均价最低
     *
     * @param boolQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getRentPriceByPlotId(BoolQueryBuilder boolQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentIndex(city)).types(ElasticCityUtils.getRentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0).aggregation(AggregationBuilders.min("minRentPrice").field("rent_house_price"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
