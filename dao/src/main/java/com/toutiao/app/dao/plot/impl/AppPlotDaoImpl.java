package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.AppPlotDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.domain.query.PlotRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AppPlotDaoImpl implements AppPlotDao {
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
    public Map queryPlotByCondition(PlotRequest plotRequest) {
        try {
            String key = null;
            List nearPlotList = new ArrayList();
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(index).setTypes(parentType);
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            //小区ID
            if (plotRequest.getPlotId() != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("id", plotRequest.getPlotId()));
            }
            //关键字

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
