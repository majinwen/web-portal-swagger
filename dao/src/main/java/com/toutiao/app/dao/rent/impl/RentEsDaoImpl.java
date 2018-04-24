package com.toutiao.app.dao.rent.impl;

import com.toutiao.app.dao.rent.RentEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RentEsDaoImpl implements RentEsDao {
    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.zufang.rent.index}")
    private String rentIndex;
    @Value("${tt.zufang.rent.type}")
    private String rentType;
    private static final Integer ZHENGZU = 1;
    private static final Integer HEZU = 2;


    @Override
    public SearchResponse queryRentListByPlotId(BoolQueryBuilder booleanQueryBuilder,Integer from) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).setFrom(from).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryRentByRentId(BoolQueryBuilder booleanQueryBuilder){
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).execute().actionGet();
        return searchResponse;
    }
    @Override
    public SearchResponse queryNearHouseByLocation(BoolQueryBuilder boolQueryBuilder, GeoDistanceQueryBuilder location, GeoDistanceSortBuilder sort, Integer from, Integer size) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = searchRequestBuilder.setQuery(boolQueryBuilder).setFrom(from).setSize(size).setPostFilter(location).addSort(sort)
                .execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryRentNumByPlotId(BoolQueryBuilder boolQueryBuilder) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = searchRequestBuilder.setQuery(boolQueryBuilder)
                .addAggregation(AggregationBuilders.filter("ZHENGZU", QueryBuilders.termQuery("rent_type", ZHENGZU)))
                .addAggregation(AggregationBuilders.filter("HEZU", QueryBuilders.termQuery("rent_type", HEZU)))
                .execute().actionGet();
        return searchResponse;
    }
}
