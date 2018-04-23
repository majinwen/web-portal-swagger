package com.toutiao.web.dao.advertisement.impl;

import com.toutiao.web.common.util.ESClientTools;
import com.toutiao.web.dao.advertisement.AggAdLandingDao;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AggAdLandingDaoImpl implements AggAdLandingDao{


    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.claim.esfhouse.index}")
    private String recommendEsfIndex;//推荐二手房房源索引
    @Value("${tt.claim.esfhouse.type}")
    private String recommendEsfType;//推荐二手房房源索引类型
    @Value("${tt.esf.agent.index}")
    private String agentIndex;//认领二手房房源索引
    @Value("${tt.esf.agent.type}")
    private String agentType;//认领二手房房源索引类型
    @Value("${tt.projhouse.index}")
    private String projhouseIndex;//索引名称
    @Value("${tt.projhouse.type}")
    private String projhouseType;//索引类


    @Override
    public SearchResponse getRecommendSellHouseAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageSize) {

        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic","adSort"};

        TransportClient client = esClientTools.init();


        SearchResponse searchResponse = client.prepareSearch(recommendEsfIndex).setTypes(recommendEsfType)
                .setQuery(booleanQueryBuilder).addSort("adSort",SortOrder.ASC).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }


    @Override
    public SearchResponse getClaimSellHouseDetailsAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic","adSort"};

        TransportClient client = esClientTools.init();


        SearchResponse searchResponse = client.prepareSearch(recommendEsfIndex).setTypes(recommendEsfType)
                .setQuery(booleanQueryBuilder).setFrom(pageNum).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }

    @Override
    public SearchResponse getUnClaimSellHouseDetailsAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic"};

        TransportClient client = esClientTools.init();


        SearchResponse searchResponse = client.prepareSearch(projhouseIndex).setTypes(projhouseType)
                .setQuery(booleanQueryBuilder).setFrom(pageNum).setSize(pageSize)
                .setFetchSource(returnField,null)
                .execute().actionGet();


        return searchResponse;
    }
}
