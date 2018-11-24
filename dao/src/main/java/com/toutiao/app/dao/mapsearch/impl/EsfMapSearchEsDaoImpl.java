package com.toutiao.app.dao.mapsearch.impl;

import com.toutiao.app.dao.mapsearch.EsfMapSearchEsDao;
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


        return null;
    }

    @Override
    public SearchResponse esfMapSearchByBizcircle(BoolQueryBuilder boolQueryBuilder, String city) {
        return null;
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
}
