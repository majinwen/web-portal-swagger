package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.HouseBusinessAndRoomEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 商圈+户型房源专题页数据层实现类
 */
@Service
public class HouseBusinessAndRoomEsDaoImpl implements HouseBusinessAndRoomEsDao{

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse getHouseBusinessAndRoomHouses(BoolQueryBuilder query, Integer pageNum, Integer pageSize, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query).sort("houseTotalPrices", SortOrder.ASC).from((pageNum - 1) * pageSize).size(pageSize);
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
    public SearchResponse getHouseBusinessAndRoomAveragePrice(BoolQueryBuilder query, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.source(searchSourceBuilder.query(query));
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
