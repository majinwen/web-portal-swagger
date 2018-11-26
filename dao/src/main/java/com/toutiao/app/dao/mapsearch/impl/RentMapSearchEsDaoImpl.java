package com.toutiao.app.dao.mapsearch.impl;

import com.toutiao.app.dao.mapsearch.RentMapSearchEsDao;
import com.toutiao.web.common.util.elastic.ElasticCityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RentMapSearchEsDaoImpl  implements RentMapSearchEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchResponse getRentMapSearch(SearchSourceBuilder searchSourceBuilder, String city) {
        SearchRequest searchRequest = new SearchRequest(ElasticCityUtils.getRentHouseIndex(city)).types(ElasticCityUtils.getRentHouseType(city));
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return search;
    }

    @Override
    public SearchResponse getDistanceAndAreainfo(SearchSourceBuilder searchSourceBuilder) {
        SearchRequest searchRequest = new SearchRequest("district_bizcircle_average_price").types("dbavgprice");
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return search;
    }
}
