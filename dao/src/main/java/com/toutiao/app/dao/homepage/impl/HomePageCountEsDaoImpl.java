package com.toutiao.app.dao.homepage.impl;

import com.toutiao.app.dao.homepage.HomePageCountEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;
import static org.elasticsearch.index.query.QueryBuilders.termsQuery;

/**
 * Created by CuiShihao on 2018/10/19
 */
@Service
public class HomePageCountEsDaoImpl implements HomePageCountEsDao {

    private static final Integer IS_DEL = 0;//新房未删除
    private static final Integer IS_APPROVE = 1;//新房未下架
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse getNewCount(String city) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(termsQuery("sale_status_id", new int[]{0,1,5,6}));
        boolQueryBuilder.must(termQuery("is_approve", IS_APPROVE));
        boolQueryBuilder.must(termQuery("is_del", IS_DEL));
        boolQueryBuilder.must(termsQuery("property_type_id",  new int[]{1,2}));
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getNewHouseIndex(city)).types(ElasticCityUtils.getNewHouseParentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.terms("buildingCount").field("sale_status_id"))
                .aggregation(AggregationBuilders.terms("preferentialCount").field("is_active"));
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
    public SearchResponse getEsfCount(String city) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(termQuery("isDel", "0"));
        boolQueryBuilder.must(termQuery("is_claim", "0"));

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder).size(0)
                .aggregation(AggregationBuilders.terms("reduceCount").field("isCutPrice"))
                .aggregation(AggregationBuilders.terms("hotSaleCount").field("isMustRob"))
                .aggregation(AggregationBuilders.terms("missingCount").field("isLowPrice"));
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
