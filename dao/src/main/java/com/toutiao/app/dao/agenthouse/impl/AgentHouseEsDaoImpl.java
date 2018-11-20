package com.toutiao.app.dao.agenthouse.impl;

import com.toutiao.app.dao.agenthouse.AgentHouseEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class AgentHouseEsDaoImpl implements AgentHouseEsDao{


    @Autowired
    private RestHighLevelClient restHighLevelClient;

//    @Override
//    public SearchResponse getAgentHouseByHouseId(BoolQueryBuilder booleanQueryBuilder) {
//
//        TransportClient client = esClientTools.init();
//        SearchResponse searchResponse = client.prepareSearch(agentIndex).setTypes(agentType).setQuery(booleanQueryBuilder)
//                .execute().actionGet();
//        return searchResponse;
//    }

//    @Override
//    public SearchResponse getAgentRentByRentId(BoolQueryBuilder booleanQueryBuilder) {
//        TransportClient client = esClientTools.init();
//        SearchResponse searchResponse = client.prepareSearch(agentRentIndex).setTypes(agentRentType).setQuery(booleanQueryBuilder)
//                .execute().actionGet();
//        return searchResponse;
//    }

    @Override
    public SearchResponse getRentInfoByUserId(BoolQueryBuilder booleanQueryBuilder, String city) {

        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getAgentIndex(city)).types(ElasticCityUtils.getAgentType(city));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder);
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
