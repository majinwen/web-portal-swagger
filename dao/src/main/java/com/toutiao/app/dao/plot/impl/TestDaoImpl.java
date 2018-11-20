package com.toutiao.app.dao.plot.impl;

import com.toutiao.app.dao.plot.TestDao;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDaoImpl implements TestDao {
    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public SearchResponse search(SearchRequest searchRequest) throws Exception
    {
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return search;
    }
}
