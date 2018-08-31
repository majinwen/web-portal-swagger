package com.toutiao.app.dao.homepage.impl;

import com.toutiao.app.dao.homepage.RecommendEsDao;
import com.toutiao.app.domain.homepage.RecommendTopicDoQuery;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    private ESClientTools esClientTools;
    @Value("${tt.projhouse.index}")
    private String esfIndex;//二手房索引名称
    @Value("${tt.projhouse.type}")
    private String esfType;//二手房索引类

    @Override
    public SearchResponse getRecommendByRecommendBuildTags(RecommendTopicDoQuery recommendTopicDoQuery, BoolQueryBuilder boolQueryBuilder, String city) {

        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getEsfHouseIndex(city)).setTypes(ElasticCityUtils.getEsfHouseTpye(city));
        SearchResponse searchResponse = null;
//        if(null!=recommendTopicDoQuery.getDistrictId()){
//             searchResponse = srb.setQuery(boolQueryBuilder).setSize(0)
//                .addAggregation(AggregationBuilders.terms("areaId").field("areaId")
//                        .subAggregation(AggregationBuilders.cardinality("count").field("newcode"))
//                        .subAggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
//                        .subAggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices")))
//                .execute().actionGet();
//        }else{
//            searchResponse = srb.setQuery(boolQueryBuilder).setSize(0)
//                    .addAggregation(AggregationBuilders.cardinality("count").field("newcode"))
//                    .addAggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
//                    .addAggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices"))
//                    .execute().actionGet();
//        }

        searchResponse = srb.setQuery(boolQueryBuilder).setSize(0)
                .addAggregation(AggregationBuilders.cardinality("count").field("newcode"))
                .addAggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
                .addAggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices"))
                .addAggregation(AggregationBuilders.terms("areaIds").field("areaId"))
                .execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse getRecommendByRecommendHouseTags(RecommendTopicDoQuery recommendTopicDoQuery, BoolQueryBuilder boolQueryBuilder, String city) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getEsfHouseIndex(city)).setTypes(ElasticCityUtils.getEsfHouseTpye(city));
        SearchResponse searchResponse = null;
//        if(null!=recommendTopicDoQuery.getDistrictId()){
//            searchResponse = srb.setQuery(boolQueryBuilder).setSize(0)
//                    .addAggregation(AggregationBuilders.terms("areaId").field("areaId")
//                            .subAggregation(AggregationBuilders.cardinality("count").field("houseId"))
//                            .subAggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
//                            .subAggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices")))
//                    .execute().actionGet();
//        }else{
//            searchResponse = srb.setQuery(boolQueryBuilder).setSize(0)
//                    .addAggregation(AggregationBuilders.cardinality("count").field("houseId"))
//                    .addAggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
//                    .addAggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices"))
//                    .execute().actionGet();
//        }
        searchResponse = srb.setQuery(boolQueryBuilder).setSize(0)
                .addAggregation(AggregationBuilders.cardinality("count").field("houseId"))
                .addAggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
                .addAggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices"))
                .addAggregation(AggregationBuilders.terms("areaIds").field("areaId"))
                .execute().actionGet();
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
