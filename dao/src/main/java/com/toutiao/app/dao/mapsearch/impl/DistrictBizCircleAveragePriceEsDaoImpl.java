package com.toutiao.app.dao.mapsearch.impl;

import com.toutiao.app.dao.mapsearch.DistrictBizCircleAveragePriceEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName DistricrBizCircleAveragePriceEsDaoImpl
 * @Author jiangweilong
 * @Date 2018/11/25 12:54 PM
 * @Description:
 **/

@Service
public class DistrictBizCircleAveragePriceEsDaoImpl implements DistrictBizCircleAveragePriceEsDao {


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse getLocationByDistrictId(BoolQueryBuilder boolQueryBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getDbAvgPriceIndex(city)).types(ElasticCityUtils.getDbAvgPriceType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.terms("latitude").field("district_latitude"))
                .aggregation(AggregationBuilders.terms("longitude").field("district_longitude"));
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
    public SearchResponse getLocationByBizCircleId(BoolQueryBuilder boolQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getDbAvgPriceIndex(city)).types(ElasticCityUtils.getDbAvgPriceType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(100);
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
