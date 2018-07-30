package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.PlotEsDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    public SearchResponse queryPlotDetail(BoolQueryBuilder booleanQueryBuilder,  String city){
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getPlotIndex(city)).setTypes(ElasticCityUtils.getPlotParentType(city));
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
    public SearchResponse queryPlotListByRequirementAndKeywordV1(Integer from, BoolQueryBuilder boolQueryBuilder, Integer size, GeoDistanceSortBuilder sort,FieldSortBuilder levelSort,FieldSortBuilder plotScoreSort,  String city) {
        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchRequestBuilder srb=client.prepareSearch(ElasticCityUtils.getPlotIndex(city)).setTypes(ElasticCityUtils.getPlotParentType(city));
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).addSort(levelSort).addSort(plotScoreSort).setFrom(from).setSize(size).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryCommonPlotList(Integer from, BoolQueryBuilder boolQueryBuilder, Integer size, String keyword, HttpServletRequest request, HttpServletResponse response, String city) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getPlotIndex(city)).setTypes(ElasticCityUtils.getPlotParentType(city));
        SearchResponse searchResponse = null;
        if (StringTool.isNotEmpty(keyword)){
            searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).setSize(size).addSort("_score",SortOrder.DESC).addSort("level", SortOrder.ASC).addSort("plotScore", SortOrder.DESC).execute().actionGet();
        }else {
            searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).setSize(size).addSort("level", SortOrder.ASC).addSort("plotScore", SortOrder.DESC).execute().actionGet();
        }
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
    public SearchResponse getPlotByIds(IdsQueryBuilder idsQueryBuilder) {
        TransportClient client = esClientTools.init();
        SearchResponse searchresponse = client.prepareSearch(index).setTypes(parentType)
                .setQuery(idsQueryBuilder)
                .execute().actionGet();
        return searchresponse;
    }

    @Override
    public SearchResponse getPlotTop50List(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setFrom((from - 1) * size).setSize(size).execute().actionGet();
        return  searchResponse;

    }

}
