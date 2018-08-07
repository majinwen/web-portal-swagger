package com.toutiao.app.dao.homepage.impl;

import com.toutiao.app.dao.homepage.RecommendEsDao;
import com.toutiao.web.common.util.ESClientTools;
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

    /**
     * 根据小区楼盘推荐标志查询
     * @param boolQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getRecommendByRecommendBuildTags(BoolQueryBuilder boolQueryBuilder) {

        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(esfIndex).setTypes(esfType);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(0)
                .addAggregation(AggregationBuilders.terms("areaId").field("areaId")
                        .subAggregation(AggregationBuilders.cardinality("count").field("newcode"))
                        .subAggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
                        .subAggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices")))
                .execute().actionGet();


        return searchResponse;
    }

    @Override
    public SearchResponse getRecommendByRecommendHouseTags(BoolQueryBuilder boolQueryBuilder) {


        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(esfIndex).setTypes(esfType);
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(0)
                .addAggregation(AggregationBuilders.terms("areaId").field("areaId")
                        .subAggregation(AggregationBuilders.cardinality("count").field("houseId"))
                        .subAggregation(AggregationBuilders.min("minPrice").field("houseTotalPrices"))
                        .subAggregation(AggregationBuilders.max("maxPrice").field("houseTotalPrices")))
                .execute().actionGet();

        return searchResponse;
    }
}
