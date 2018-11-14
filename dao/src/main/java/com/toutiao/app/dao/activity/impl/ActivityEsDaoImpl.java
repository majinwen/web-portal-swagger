package com.toutiao.app.dao.activity.impl;

import com.toutiao.app.dao.activity.ActivityEsDao;
import com.toutiao.web.common.util.ESClientTools;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-21
 * Time:   13:09
 * Theme:
 */
@Service
public class ActivityEsDaoImpl implements ActivityEsDao{

    @Value("${tt.newhouse.index}")
    private String newhouseIndex;//索引名称
    @Value("${tt.newhouse.type}")
    private String newhouseType;//索引类型

    @Autowired
    private ESClientTools esClientTools;


    @Override
    public SearchResponse getActivityCount(BoolQueryBuilder booleanQueryBuilder) {

        TransportClient client = esClientTools.init();

        SearchResponse searchResponse = client.prepareSearch(newhouseIndex).setTypes(newhouseType).setQuery(booleanQueryBuilder)
                .execute().actionGet();

        return searchResponse;
    }
}
