package com.toutiao.app.dao.homepage.impl;

import com.toutiao.app.dao.homepage.HomePageEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HomePageEsDaoImpl implements HomePageEsDao {

    /**
     * 索引名称
     */
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;

    /**
     * 索引类
     */
    @Value("${tt.projhouse.type}")
    private String projhouseType;

    /**
     * 推荐二手房房源索引
     */
    @Value("${tt.claim.esfhouse.index}")
    private String recommendEsfIndex;

    /**
     * 推荐二手房房源索引类型
     */
    @Value("${tt.claim.esfhouse.type}")
    private String recommendEsfType;

    @Autowired
    private ESClientTools esClientTools;

    @Override
    public SearchResponse getHomePageEsf(BoolQueryBuilder boolQueryBuilder) {
        SearchResponse searchResponse = null;
        try {
            TransportClient client = esClientTools.init();
            SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
            srb.addSort("sortingScore", SortOrder.DESC);
            searchResponse = srb.setQuery(boolQueryBuilder).setSize(20).execute().actionGet();
            return searchResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResponse;

    }

    @Override
    public SearchResponse getHomeThemeHouse(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(recommendEsfIndex).setTypes(recommendEsfType);
        SearchResponse searchResponse = searchRequestBuilder.setQuery(boolQueryBuilder).setFrom(from).setSize(size).execute().actionGet();
        return searchResponse;
    }

    /**
     * 首页获取降价房8条
     */
    @Override
    public SearchResponse getHomePageCutPrice(BoolQueryBuilder boolQueryBuilder) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        //根据修改时间排序
        srb.addSort("updateTimeSort", SortOrder.DESC);
        //限制结果8条
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(8).execute().actionGet();
        return searchResponse;
    }

    /**
     * 首页获取价格洼地8条
     */
    @Override
    public SearchResponse getHomePageLowerPrice(BoolQueryBuilder boolQueryBuilder) {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(projhouseIndex).setTypes(projhouseType);
        //根据修改时间排序
        srb.addSort("updateTimeSort", SortOrder.DESC);
        //限制结果8条
        SearchResponse searchResponse = srb.setQuery(boolQueryBuilder).setSize(8).execute().actionGet();
        return searchResponse;
    }
}
