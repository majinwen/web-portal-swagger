package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.PlotDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.StringTool;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class PlotDaoImpl implements PlotDao {
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
    public SearchResponse queryPlotDetail(BoolQueryBuilder booleanQueryBuilder) throws Exception {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryNearPlotByLocationAndDistance(BoolQueryBuilder boolQueryBuilder,GeoDistanceQueryBuilder location,GeoDistanceSortBuilder sort) throws Exception{
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(5).setPostFilter(location).addSort(sort).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryPlotListByRequirement(String keyword,Integer from, BoolQueryBuilder boolQueryBuilder, FieldSortBuilder avgPriceSort, FieldSortBuilder scoreSort, FieldSortBuilder levelSort, FieldSortBuilder plotScoreSort) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
        SearchResponse searchResponse = null;
        if (StringTool.isNotEmpty(avgPriceSort)){
            if (StringTool.isNotEmpty(keyword)){
                searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).addSort(avgPriceSort).addSort(scoreSort).addSort(levelSort).addSort(plotScoreSort).execute().actionGet();
            }else {
                searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).addSort(avgPriceSort).addSort(levelSort).addSort(plotScoreSort).execute().actionGet();
            }
        }else {
            if (StringTool.isNotEmpty(keyword)){
                searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).addSort(scoreSort).addSort(levelSort).addSort(plotScoreSort).execute().actionGet();
            }else {
                searchResponse = srb.setQuery(boolQueryBuilder).setFrom(from).addSort(levelSort).addSort(plotScoreSort).execute().actionGet();
            }
        }

        return searchResponse;
    }


}
