package com.toutiao.app.dao.activity;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-21
 * Time:   13:08
 * Theme:
 */
public interface ActivityEsDao {


    SearchResponse getActivityCount(BoolQueryBuilder booleanQueryBuilder);


}
