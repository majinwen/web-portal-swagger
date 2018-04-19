package com.toutiao.app.dao.suggest.impl;

import com.toutiao.app.dao.suggest.SuggestEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SuggestEsDaoImpl implements SuggestEsDao{
    private static final String NEW_HOUSE_TYPE = "0";
    private static final String PLOT_TYPE = "1";
    private static final String ESF_TYPE = "2";
    private static final String RENT_TYPE = "3";
    private static final String APARTMENT_TYPE = "4";

    @Value("${tt.search.engines}")
    private String search_engines_index ;
    @Value("${tt.search.scope}")
    private String search_scope_index;
    @Value("${tt.search.engines.type}")
    private String search_engines_type;
    @Value("${tt.search.scope.type}")
    private String search_scope_type;
    @Autowired
    private ESClientTools esClientTools;

    /**
     * 区域商圈联想词
     * @param booleanQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getAreaAndDistrictSuggest(BoolQueryBuilder booleanQueryBuilder) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srbScope = client.prepareSearch(search_scope_index).setTypes(search_scope_type);
        srbScope.addSort("search_sort", SortOrder.ASC)
                .addAggregation(AggregationBuilders.filter("plot", QueryBuilders.termQuery("search_type_sings", PLOT_TYPE)))
                .addAggregation(AggregationBuilders.filter("esf",QueryBuilders.termQuery("search_type_sings", ESF_TYPE)))
                .addAggregation(AggregationBuilders.filter("newHouse",QueryBuilders.termQuery("search_type_sings", NEW_HOUSE_TYPE)))
                .addAggregation(AggregationBuilders.filter("rent",QueryBuilders.termQuery("search_type_sings", RENT_TYPE)))
                .addAggregation(AggregationBuilders.filter("apartment",QueryBuilders.termQuery("search_type_sings", APARTMENT_TYPE)));
        SearchResponse searchResponse = srbScope.setQuery(booleanQueryBuilder).execute().actionGet();
        return searchResponse;
    }

    /**
     * 房源联想词
     * @param booleanQueryBuilder
     * @return
     */
    @Override
    public SearchResponse getKeywordSuggest(BoolQueryBuilder booleanQueryBuilder) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srbEngines = client.prepareSearch(search_engines_index).setTypes(search_engines_type);
        srbEngines.addAggregation(AggregationBuilders.filter("plot",QueryBuilders.termQuery("search_type_sings", PLOT_TYPE)))
                  .addAggregation(AggregationBuilders.filter("esf",QueryBuilders.termQuery("search_type_sings", ESF_TYPE)))
                  .addAggregation(AggregationBuilders.filter("newHouse",QueryBuilders.termQuery("search_type_sings", NEW_HOUSE_TYPE)))
                  .addAggregation(AggregationBuilders.filter("rent",QueryBuilders.termQuery("search_type_sings", RENT_TYPE)))
                  .addAggregation(AggregationBuilders.filter("apartment",QueryBuilders.termQuery("search_type_sings", APARTMENT_TYPE)));
        SearchResponse searchResponse = srbEngines.setQuery(booleanQueryBuilder).execute().actionGet();
        return searchResponse;
    }
}
