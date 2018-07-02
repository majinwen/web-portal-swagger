package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class PlotEsDaoImpl implements PlotEsDao {
    @Value("${plot.index}")
    private String index ;
    @Value("${plot.parent.type}")
    private String parentType;
    @Value("${plot.child.type}")
    private String childType;
    @Value("${distance}")
    private Double distance;
    @Autowired
    private ESClientTools esClientTools;



    @Override
    public SearchResponse queryPlotDetail(BoolQueryBuilder booleanQueryBuilder){
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryNearPlotByLocationAndDistance(BoolQueryBuilder boolQueryBuilder,GeoDistanceQueryBuilder location,GeoDistanceSortBuilder sort){
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(5).setPostFilter(location).addSort(sort).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryNearPlotListByLocationAndDistance(BoolQueryBuilder boolQueryBuilder, GeoDistanceQueryBuilder location, GeoDistanceSortBuilder sort){
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setPostFilter(location).addSort(sort).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryPlotListByRequirement(Integer from, BoolQueryBuilder boolQueryBuilder, FieldSortBuilder levelSort, Integer size) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).setSize(size).addSort(levelSort).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryPlotListByRequirementAndKeyword(Integer from, FunctionScoreQueryBuilder functionScoreQueryBuilder,Integer size) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchResponse searchResponse = srb.setQuery(functionScoreQueryBuilder).setFrom(from).setSize(size).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryPlotListByPlotIdList(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType).setSearchType(SearchType.QUERY_THEN_FETCH);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).setSize(size).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse getPlotTop50List(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).setSize(size).execute().actionGet();
        return  searchResponse;

    }

}
