package com.toutiao.app.dao.suggest.impl;

import com.toutiao.app.dao.suggest.SuggestEsDao;
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

@Service
public class SuggestEsDaoImpl implements SuggestEsDao{
    private static final String NEW_HOUSE_TYPE = "0";
    private static final String PLOT_TYPE = "1";
    private static final String ESF_TYPE = "2";
    private static final String RENT_TYPE = "3";
    private static final String APARTMENT_TYPE = "4";

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 区域商圈联想词
     * @param booleanQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getAreaAndDistrictSuggest(BoolQueryBuilder booleanQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getSearchScopeIndex(city)).types(ElasticCityUtils.getSearchScopeType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder)
                .aggregation(AggregationBuilders.filter("plot", QueryBuilders.termQuery("search_type_sings", PLOT_TYPE)))
                .aggregation(AggregationBuilders.filter("esf",QueryBuilders.termQuery("search_type_sings", ESF_TYPE)))
                .aggregation(AggregationBuilders.filter("newHouse",QueryBuilders.termQuery("search_type_sings", NEW_HOUSE_TYPE)))
                .aggregation(AggregationBuilders.filter("rent",QueryBuilders.termQuery("search_type_sings", RENT_TYPE)))
                .aggregation(AggregationBuilders.filter("apartment",QueryBuilders.termQuery("search_type_sings", APARTMENT_TYPE)));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResponse;
    }

    /**
     * 房源联想词
     * @param booleanQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getKeywordSuggest(BoolQueryBuilder booleanQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getSearchEnginesIndex(city)).types(ElasticCityUtils.getSearchEnginesType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder)
                .aggregation(AggregationBuilders.filter("plot",QueryBuilders.termQuery("search_type_sings", PLOT_TYPE)))
                .aggregation(AggregationBuilders.filter("esf",QueryBuilders.termQuery("search_type_sings", ESF_TYPE)))
                .aggregation(AggregationBuilders.filter("newHouse",QueryBuilders.termQuery("search_type_sings", NEW_HOUSE_TYPE)))
                .aggregation(AggregationBuilders.filter("rent",QueryBuilders.termQuery("search_type_sings", RENT_TYPE)))
                .aggregation(AggregationBuilders.filter("apartment",QueryBuilders.termQuery("search_type_sings", APARTMENT_TYPE)));
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
