package com.toutiao.app.dao.mapsearch.impl;

import com.toutiao.app.dao.mapsearch.EsfMapSearchEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName EsfMapSearchEsDaoImpl
 * @Author jiangweilong
 * @Date 2018/11/23 7:41 PM
 * @Description:
 **/

@Service
public class EsfMapSearchEsDaoImpl implements EsfMapSearchEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public SearchResponse esfMapSearchByDistrict(BoolQueryBuilder boolQueryBuilder, String city) {

//        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
//
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //searchSourceBuilder.query(boolQueryBuilder).size(0).aggregation(AggregationBuilders.terms(""))

//
//        searchSourceBuilder.query(boolQueryBuilder).aggregation()
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getDbAvgPriceIndex(city)).types(ElasticCityUtils.getDbAvgPriceType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.terms("houseCount").field("district_id").size(200)
                    .subAggregation(AggregationBuilders.terms("districtName").field("district_name"))
                    .subAggregation(AggregationBuilders.terms("districtAvgPrice").field("district_avgprice"))
                    .subAggregation(AggregationBuilders.terms("districtLatitude").field("district_latitude"))
                    .subAggregation(AggregationBuilders.terms("districtLongitude").field("district_longitude")));

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
    public SearchResponse esfMapSearchByBizcircle(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getDbAvgPriceIndex(city)).types(ElasticCityUtils.getDbAvgPriceType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.terms("houseCount").field("bizcircle_id").size(200)
                    .subAggregation(AggregationBuilders.terms("bizcircleName").field("bizcircle_name"))
                    .subAggregation(AggregationBuilders.terms("bizcircleAvgprice").field("bizcircle_avgprice"))
                    .subAggregation(AggregationBuilders.terms("bizcircleLatitude").field("bizcircle_latitude"))
                    .subAggregation(AggregationBuilders.terms("bizcircleLongitude").field("bizcircle_longitude")));
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
    public SearchResponse esfMapSearchByCommunity(BoolQueryBuilder boolQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.terms("houseCount").field("newcode").size(200)
                        .subAggregation(AggregationBuilders.terms("communityName").field("plotName_accurate"))
                        .subAggregation(AggregationBuilders.terms("communityAvgPrice").field("communityAvgPrice"))
                        .subAggregation(AggregationBuilders.terms("plotLatitude").field("plotLatitude"))
                        .subAggregation(AggregationBuilders.terms("plotLongitude").field("plotLongitude")));

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
    public SearchResponse esfMapSearchHouseList(FunctionScoreQueryBuilder query, Integer distance, String keyword,
                                                Integer pageNum, Integer pageSize, String city, GeoDistanceSortBuilder sort) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if((null != keyword && !"".equals(keyword)) || null != distance){
            searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize).sort(sort);
        }else{
            searchSourceBuilder.query(query).sort("extraTagsCount", SortOrder.DESC).sort("updateTimeSort",SortOrder.DESC).from((pageNum - 1) * pageSize).size(pageSize);
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = null;
        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public SearchResponse esfMapSearchByNear(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.terms("houseCount").field("newcode").size(200)
                    .subAggregation(AggregationBuilders.terms("communityName").field("plotName_accurate"))
                    .subAggregation(AggregationBuilders.terms("communityAvgPrice").field("communityAvgPrice"))
                    .subAggregation(AggregationBuilders.terms("plotLatitude").field("plotLatitude"))
                    .subAggregation(AggregationBuilders.terms("plotLongitude").field("plotLongitude")));

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
    public SearchResponse esfMapSearchBySubway(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.terms("houseCount").field("subwayStationId").size(200)
                    .subAggregation(AggregationBuilders.terms("communityName").field("plotName_accurate"))
                    .subAggregation(AggregationBuilders.terms("communityAvgPrice").field("communityAvgPrice"))
                    .subAggregation(AggregationBuilders.terms("plotLatitude").field("plotLatitude"))
                    .subAggregation(AggregationBuilders.terms("plotLongitude").field("plotLongitude")));

        SearchResponse searchResponse = null;

        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
