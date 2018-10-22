package com.toutiao.app.dao.homepage.impl;

import com.toutiao.app.dao.homepage.HomePageCountEsDao;
import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CuiShihao on 2018/10/19
 */
@Service
public class HomePageCountEsDaoImpl implements HomePageCountEsDao {

    private static final Integer IS_DEL = 0;//新房未删除
    private static final Integer IS_APPROVE = 1;//新房未下架
    @Autowired
    private ESClientTools esClientTools;

    @Override
    public SearchResponse getNewCount(String city) {
        TransportClient client = esClientTools.init();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termsQuery("sale_status_id", 0, 1, 5, 6));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_approve", IS_APPROVE));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", IS_DEL));
        boolQueryBuilder.must(QueryBuilders.termsQuery("property_type_id", 1, 2));
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getNewHouseIndex(city)).setTypes(ElasticCityUtils.getNewHouseParentType(city));
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(0).addAggregation(AggregationBuilders.terms("buildingCount").field("sale_status_id"))
                .addAggregation(AggregationBuilders.terms("preferentialCount").field("is_active"))/*.addAggregation(AggregationBuilders.count("onsaleCount").field(""))
                .addAggregation(AggregationBuilders.count("forthcomingCount").field("sale_status_id"))*/.execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse getEsfCount(String city) {
        TransportClient client = esClientTools.init();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termsQuery("isDel", "0"));
        boolQueryBuilder.must(QueryBuilders.termQuery("is_claim", "0"));
        SearchRequestBuilder srb = client.prepareSearch(ElasticCityUtils.getEsfHouseIndex(city)).setTypes(ElasticCityUtils.getEsfHouseTpye(city));
        SearchResponse response = srb.setQuery(boolQueryBuilder).setSize(0).addAggregation(AggregationBuilders.terms("reduceCount").field("isCutPrice"))
                .addAggregation(AggregationBuilders.terms("hotSaleCount").field("isMustRob")).addAggregation(AggregationBuilders.terms("missingCount").field("isLowPrice"))
                .execute().actionGet();
        return response;
    }
}
