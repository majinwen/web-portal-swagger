package com.toutiao.app.dao.rent.impl;

import com.toutiao.app.dao.rent.RentEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RentEsDaoImpl implements RentEsDao {
    @Autowired
    private ESClientTools esClientTools;
    @Value("${tt.zufang.rent.index}")
    private String rentIndex;
    @Value("${tt.zufang.rent.type}")
    private String rentType;


    @Override
    public SearchResponse queryRentListByPlotId(BoolQueryBuilder booleanQueryBuilder) throws Exception {
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).setSize(3).execute().actionGet();
        return searchResponse;
    }

    @Override
    public SearchResponse queryRentByRentId(BoolQueryBuilder booleanQueryBuilder) throws Exception{
        TransportClient client = esClientTools.init();
        SearchRequestBuilder srb = client.prepareSearch(rentIndex).setTypes(rentType);
        SearchResponse searchResponse = srb.setQuery(booleanQueryBuilder).execute().actionGet();
        return searchResponse;
    }
}
