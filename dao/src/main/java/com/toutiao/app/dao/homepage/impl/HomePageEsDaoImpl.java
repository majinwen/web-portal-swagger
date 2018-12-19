package com.toutiao.app.dao.homepage.impl;

import com.toutiao.app.dao.homepage.HomePageEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HomePageEsDaoImpl implements HomePageEsDao {

    @Value("${bdw.esf.index}")
    private String projhouseIndex;//索引名称
    @Value("${bdw.esf.type}")
    private String projhouseType;//索引类



    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse getHomePageEsf(BoolQueryBuilder boolQueryBuilder) {

        SearchRequest searchRequest = new SearchRequest(projhouseIndex).types(projhouseType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort("sortingScore", SortOrder.DESC).size(20);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResponse;

    }

//    @Override
//    public SearchResponse getHomeThemeHouse(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size) {
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(recommendEsfIndex).setTypes(recommendEsfType);
//        SearchResponse searchResponse = searchRequestBuilder.setQuery(boolQueryBuilder).setFrom(from).setSize(size).execute().actionGet();
//        return searchResponse;
//    }

    @Override
    public SearchResponse getHomePageNearPlot(BoolQueryBuilder boolQueryBuilder, Integer size, GeoDistanceSortBuilder sort, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(size).sort(sort);
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
    public SearchResponse getHomePageNearPlotNoLocation(BoolQueryBuilder boolQueryBuilder, Integer size, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(size);
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
    public SearchResponse getHomePageNearEsf(BoolQueryBuilder boolQueryBuilder, Integer size, GeoDistanceSortBuilder sort, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort("extraTagsCount",SortOrder.DESC).size(size).sort(sort);
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
    public SearchResponse getHomePageNearEsfNoLocation(BoolQueryBuilder boolQueryBuilder, Integer size, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort("extraTagsCount",SortOrder.DESC).size(size);
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
    public SearchResponse getPlotSpecialPage(BoolQueryBuilder boolQueryBuilder, GeoDistanceSortBuilder sort, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort(sort);
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
    public SearchResponse getEsfSpecialPage(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort("extraTagsCount",SortOrder.DESC).from(from).size(size);
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
    public SearchResponse getHomePageTop50(BoolQueryBuilder boolQueryBuilder, String city) {
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(ElasticCityUtils.getPlotIndex(city))
//                .setTypes(ElasticCityUtils.getPlotParentType(city));
//        AggregationBuilder agg_tophits = AggregationBuilders.topHits("group_hits").size(1);
//        SearchResponse response=searchRequestBuilder.setQuery(boolQueryBuilder).addAggregation(AggregationBuilders.terms("count")
//                .field("areaId").subAggregation(agg_tophits)).get();

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        AggregationBuilder agg_tophits = AggregationBuilders.topHits("group_hits").size(1);
        searchSourceBuilder.query(boolQueryBuilder).aggregation(AggregationBuilders.terms("count").field("areaId").subAggregation(agg_tophits));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  searchResponse;

    }

    /**
     *  首页缝出必抢
     * @param boolQueryBuilder
     * @param from
     * @param size
     * @return
     */
    @Override
    public SearchResponse getHomeBeSureToSnatch(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size) {

        SearchRequest searchRequest = new SearchRequest(projhouseIndex).types(projhouseType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort("updateTimeSort",SortOrder.DESC).from((from-1)*size).size(size);
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
     * 首页获取不买亏二手房8条
     */
    @Override
    public SearchResponse getHomePageMustBuy(BoolQueryBuilder boolQueryBuilder) {

        SearchRequest searchRequest = new SearchRequest(projhouseIndex).types(projhouseType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort("updateTimeSort",SortOrder.DESC).size(8);
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
