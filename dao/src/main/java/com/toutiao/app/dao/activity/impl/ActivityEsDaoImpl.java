package com.toutiao.app.dao.activity.impl;

import com.toutiao.app.dao.activity.ActivityEsDao;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-21
 * Time:   13:09
 * Theme:
 */
@Service
public class ActivityEsDaoImpl implements ActivityEsDao{

    @Value("${bdw.newhouse.index}")
    private String newhouseIndex;//索引名称
    @Value("${bdw.newhouse.parent.type}")
    private String newhouseType;//索引类型

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    @Override
    public SearchResponse getActivityCount(BoolQueryBuilder booleanQueryBuilder) {

        SearchRequest searchRequest = new SearchRequest(newhouseIndex).types(newhouseType);
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
