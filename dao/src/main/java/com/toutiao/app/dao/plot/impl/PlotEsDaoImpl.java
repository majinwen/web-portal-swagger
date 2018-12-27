package com.toutiao.app.dao.plot.impl;

import com.alibaba.fastjson.JSON;
import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Map;


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

    public SearchResponse getReview(BoolQueryBuilder boolQueryBuilder,GeoDistanceQueryBuilder location,GeoDistanceSortBuilder sort, String city){

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
    public SearchResponse queryPlotListByRequirementAndKeywordV1(Integer from, BoolQueryBuilder boolQueryBuilder, Integer size,
                                                                 GeoDistanceSortBuilder geoDistanceSort,FieldSortBuilder levelSort,
                                                                 FieldSortBuilder plotScoreSort,  String city, String sort) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(boolQueryBuilder).sort(levelSort).sort(plotScoreSort).sort(geoDistanceSort).from(from).size(size);
        searchSourceBuilder.query(boolQueryBuilder).from(from).size(size);
        if("3".equals(sort)){//均价从低到高
            searchSourceBuilder.sort("avgPrice", SortOrder.ASC).sort(geoDistanceSort);
        }else if("4".equals(sort)){//均价从高到低
            searchSourceBuilder.sort("avgPrice", SortOrder.DESC).sort(geoDistanceSort);
        }else{
            searchSourceBuilder.sort(levelSort).sort(plotScoreSort).sort(geoDistanceSort);
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
    public SearchResponse queryCommonPlotList(Integer from, BoolQueryBuilder boolQueryBuilder, Integer size, String keyword, String city, String sort) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if(StringTool.isNotEmpty(keyword)){
//            searchSourceBuilder.query(boolQueryBuilder).from(from).size(size).sort("_score",SortOrder.DESC).sort("level", SortOrder.ASC).sort("plotScore", SortOrder.DESC);
            searchSourceBuilder.query(boolQueryBuilder).from(from).size(size);
           if("3".equals(sort)){//均价从低到高
                searchSourceBuilder.sort("avgPrice", SortOrder.ASC);
            }else if("4".equals(sort)){//均价从高到低
                searchSourceBuilder.sort("avgPrice", SortOrder.DESC);
            }else{
                searchSourceBuilder.sort("_score",SortOrder.DESC).sort("level", SortOrder.ASC).sort("plotScore", SortOrder.DESC);
            }
        }else{
            //searchSourceBuilder.query(boolQueryBuilder).from(from).size(size).sort("level", SortOrder.ASC).sort("plotScore", SortOrder.DESC);
            searchSourceBuilder.query(boolQueryBuilder).from(from).size(size);
            if("3".equals(sort)){//均价从低到高
                searchSourceBuilder.sort("avgPrice", SortOrder.ASC);
            }else if("4".equals(sort)){//均价从高到低
                searchSourceBuilder.sort("avgPrice", SortOrder.DESC);
            }else{
                searchSourceBuilder.sort("level", SortOrder.ASC).sort("plotScore", SortOrder.DESC);
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

    @Override
    public SearchResponse queryPlotByPlotId(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();


        searchSourceBuilder.query(boolQueryBuilder).fetchSource(new String[]{"photo","rc","id","avgPrice"}, null);
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
    public SearchResponse getReviewById(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
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
    public SearchResponse queryTagsById(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getPlotIndex(city)).types(ElasticCityUtils.getPlotParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).fetchSource(new String[]{"recommendBuildTagsName","label","districtHotSort"}, null);
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
