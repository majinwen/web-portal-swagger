package com.toutiao.app.dao.advertisement;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-11-02
 * Time:   18:21
 * Theme:
 */


public interface AdCommunityEsDao {

    SearchResponse getAdCommunity(BoolQueryBuilder booleanQueryBuilder, String city);
}
