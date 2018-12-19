package com.toutiao.web.dao.advertisement.impl;

import com.toutiao.web.dao.advertisement.AggAdLandingDao;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AggAdLandingDaoImpl implements AggAdLandingDao{


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

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public SearchResponse getRecommendSellHouseAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageSize) {

        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic","adSort"};

        SearchRequest searchRequest = new SearchRequest(recommendEsfIndex).types(recommendEsfType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).sort("adSort",SortOrder.ASC).size(pageSize).fetchSource(returnField,null);
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
    public SearchResponse getClaimSellHouseDetailsAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic","adSort"};

        SearchRequest searchRequest = new SearchRequest(recommendEsfIndex).types(recommendEsfType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).size(pageSize).fetchSource(returnField,null);
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
    public SearchResponse getUnClaimSellHouseDetailsAdLanding(BoolQueryBuilder booleanQueryBuilder, Integer pageNum, Integer pageSize) {
        String[] returnField = new String[]{"houseTotalPrices", "houseId", "housePhoto","housePhotoTitle", "room", "hall", "buildArea",
                "plotName","forwardName","houseTitle","tagsName","housePlotLocation","houseBusinessName","area","houseBusinessName","traffic"};

        SearchRequest searchRequest = new SearchRequest(projhouseIndex).types(projhouseType);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder).from(pageNum).size(pageSize).fetchSource(returnField,null);
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
