package com.toutiao.app.dao.homepage;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface HomePageEsDao {


    SearchResponse  getHomePageEsf( BoolQueryBuilder boolQueryBuilder);
}
