package com.toutiao.app.dao.homepage.impl;

import com.toutiao.app.dao.homepage.HomePageEsDao;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class HomePageEsDaoImpl implements HomePageEsDao {

    @Override
    public SearchResponse getHomePageEsf(BoolQueryBuilder boolQueryBuilder) {

    }
}
