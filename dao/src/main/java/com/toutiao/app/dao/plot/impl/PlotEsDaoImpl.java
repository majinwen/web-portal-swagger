package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class PlotEsDaoImpl implements PlotEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;



    @Override
    public SearchResponse queryPlotDetail(BoolQueryBuilder booleanQueryBuilder,  String city){

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
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
    public SearchResponse queryNearPlotByLocationAndDistance(BoolQueryBuilder boolQueryBuilder,GeoDistanceQueryBuilder location,GeoDistanceSortBuilder sort, String city){

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(5).postFilter(location).sort(sort);
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
//    public SearchResponse queryNearPlotListByLocationAndDistance(BoolQueryBuilder boolQueryBuilder, GeoDistanceQueryBuilder location, GeoDistanceSortBuilder sort){
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
//        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setPostFilter(location).addSort(sort).execute().actionGet();
//        return searchResponse;
//    }

//    @Override
//    public SearchResponse queryPlotListByRequirement(Integer from, BoolQueryBuilder boolQueryBuilder, FieldSortBuilder levelSort, Integer size) {
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
//        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).setSize(size).addSort(levelSort).execute().actionGet();
//        return searchResponse;
//    }

//    @Override
//    public SearchResponse queryPlotListByRequirementAndKeyword(Integer from, FunctionScoreQueryBuilder functionScoreQueryBuilder,Integer size) {
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
//        SearchResponse searchResponse = srb.setQuery(functionScoreQueryBuilder).setFrom(from).setSize(size).execute().actionGet();
//        return searchResponse;
//    }

    @Override
    public SearchResponse queryPlotListByRequirementAndKeywordV1(Integer from, BoolQueryBuilder boolQueryBuilder, Integer size, GeoDistanceSortBuilder sort,FieldSortBuilder levelSort,FieldSortBuilder plotScoreSort,  String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort(levelSort).sort(plotScoreSort).sort(sort).from(from).size(size);
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
    public SearchResponse queryCommonPlotList(Integer from, BoolQueryBuilder boolQueryBuilder, Integer size, String keyword, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if(StringTool.isNotEmpty(keyword)){
            searchSourceBuilder.query(boolQueryBuilder).from(from).size(size).sort("_score",SortOrder.DESC).sort("level", SortOrder.ASC).sort("plotScore", SortOrder.DESC);

        }else{
            searchSourceBuilder.query(boolQueryBuilder).from(from).size(size).sort("level", SortOrder.ASC).sort("plotScore", SortOrder.DESC);
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

//    @Override
//    public SearchResponse queryPlotListByPlotIdList(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size) {
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType).setSearchType(SearchType.QUERY_THEN_FETCH);
//        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).setSize(size).execute().actionGet();
//        return searchResponse;
//    }

    @Override
    public SearchResponse getPlotByIds(IdsQueryBuilder idsQueryBuilder,String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(idsQueryBuilder);
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
    public SearchResponse getPlotTop50List(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).from((from - 1) * size).size(size);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  searchResponse;

    }

    @Override
    public SearchResponse getPlotByRecommendCondition(BoolQueryBuilder boolQueryBuilder,ScriptSortBuilder scrip, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).sort(scrip).size(5);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  searchResponse;
    }


//    @Override
//    public SearchResponse getPlotByKeyWord(BoolQueryBuilder booleanQueryBuilder, String city) {
//
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getPlotIndex(city)).setTypes(ElasticCityUtils.getPlotParentType(city));
//        SearchResponse searchresponse=srb.setQuery(booleanQueryBuilder).execute().actionGet();
//        return searchresponse;
//    }
//
//    @Override
//    public SearchResponse getPlotByNickNameKeyWord(BoolQueryBuilder booleanQueryBuilder) {
//
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(searchEnginesIndex).setTypes(searchEnginesType);
//        SearchResponse searchresponse=srb.setQuery(booleanQueryBuilder).execute().actionGet();
//        return searchresponse;
//    }

}
