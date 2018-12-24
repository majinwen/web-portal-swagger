package com.toutiao.app.dao.sellhouse.impl;


import com.toutiao.app.dao.sellhouse.SellHouseThemeEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SellHouseThemeEsDaoImpl implements SellHouseThemeEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /***
     * 查询降价房列表
     */
//    @Override
//    public SearchResponse getMustBuySellHouse(BoolQueryBuilder query, Integer sort, Integer pageNum, Integer pageSize, Integer topicType, String city) {
//
//        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize);
//        if (sort == 0) {
//            searchSourceBuilder.sort("updateTimeSort", SortOrder.DESC);
//        } else if (sort == 1) {
//            searchSourceBuilder.sort("houseTotalPrices", SortOrder.ASC);
//        } else if (sort == 2) {
//            searchSourceBuilder.sort("houseTotalPrices", SortOrder.DESC);
//        } else if (topicType == 1 && sort == 3) {
//            searchSourceBuilder.sort("priceFloat", SortOrder.ASC);
//        } else if (topicType == 1 && sort == 4) {
//            searchSourceBuilder.sort("priceFloat", SortOrder.DESC);
//        } else if (topicType == 2 && sort == 3) {
//            searchSourceBuilder.sort("buildArea", SortOrder.ASC);
//        } else if (topicType == 2 && sort == 4) {
//            searchSourceBuilder.sort("buildArea", SortOrder.DESC);
//        }
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse searchResponse = null;
//        try {
//            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return searchResponse;
//    }

    @Override
    public SearchResponse getCutPriceShellHouse(BoolQueryBuilder query, Integer pageNum, Integer pageSize, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize)
                .fetchSource(new String[]{"plotName","initialPrice","houseTotalPrices","room","hall","buildArea","import_time",
                        "priceFloat","houseId","housePhotoTitle"}, null).sort("priceFloat", SortOrder.DESC);
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
    public SearchResponse getLowPriceShellHouse(BoolQueryBuilder query, Integer pageNum, Integer pageSize, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize);
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
    public SearchResponse getBeSureToSnatchShellHouse(BoolQueryBuilder query, Integer pageNum, Integer pageSize, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize);
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
