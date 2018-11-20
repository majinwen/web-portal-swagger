package com.toutiao.app.dao.homepage.impl;

import com.toutiao.app.dao.homepage.RecommendEsDao;
import com.toutiao.app.domain.homepage.RecommendTopicDoQuery;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-07
 * Time:   14:03
 * Theme:
 */
@Service
public class RecommendEsDaoImpl implements RecommendEsDao{

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Value("${tt.projhouse.index}")
    private String esfIndex;//二手房索引名称
    @Value("${tt.projhouse.type}")
    private String esfType;//二手房索引类

    @Override
    public SearchResponse getRecommendByRecommendBuildTags(RecommendTopicDoQuery recommendTopicDoQuery, BoolQueryBuilder boolQueryBuilder, String city) {

        SearchResponse searchResponse = null;

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.cardinality("count").field("newcode"))
                .aggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
                .aggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices"))
                .aggregation(AggregationBuilders.terms("areaIds").field("areaId"));

        searchRequest.source(searchSourceBuilder);

        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResponse;
    }

    @Override
    public SearchResponse getRecommendByRecommendHouseTags(RecommendTopicDoQuery recommendTopicDoQuery, BoolQueryBuilder boolQueryBuilder, String city) {

        SearchResponse searchResponse = null;


        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.cardinality("count").field("houseId"))
                .aggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
                .aggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices"))
                .aggregation(AggregationBuilders.terms("areaIds").field("areaId"));

        searchRequest.source(searchSourceBuilder);

        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResponse;
    }

//    /**
//     * 根据小区楼盘推荐标志查询
//     * @param boolQueryBuilder
//     * @return
//     */
//    @Override
//    public SearchResponse getRecommendByRecommendBuildTags(BoolQueryBuilder boolQueryBuilder) {
//
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(esfIndex).setTypes(esfType);
//        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(0)
//                .addAggregation(AggregationBuilders.terms("areaId").field("areaId")
//                        .subAggregation(AggregationBuilders.cardinality("count").field("newcode"))
//                        .subAggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
//                        .subAggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices")))
//                .execute().actionGet();
//
//
//        return searchResponse;
//    }
//
//    @Override
//    public SearchResponse getRecommendByRecommendHouseTags(BoolQueryBuilder boolQueryBuilder) {
//
//
//        TransportClient client = esClientTools.init();
//        SearchRequestBuilder srb = client.prepareSearch(esfIndex).setTypes(esfType);
//        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(0)
//                .addAggregation(AggregationBuilders.terms("areaId").field("areaId")
//                        .subAggregation(AggregationBuilders.cardinality("count").field("houseId"))
//                        .subAggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
//                        .subAggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices")))
//                .execute().actionGet();
//
//        return searchResponse;
//    }
}
