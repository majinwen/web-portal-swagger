package com.toutiao.app.dao.sellhouse.impl;

import com.toutiao.app.dao.sellhouse.NearbySellHouseEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NearbySellHouseEsDaoImpl implements NearbySellHouseEsDao{


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse getNearbySellHouseByFilter(FunctionScoreQueryBuilder query, Integer pageNum,Integer pageSize,String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getEsfHouseIndex(city)).types(ElasticCityUtils.getEsfHouseTpye(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(query).from((pageNum - 1) * pageSize).size(pageSize);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchresponse = null;
        try {
            searchresponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchresponse;
    }
}
