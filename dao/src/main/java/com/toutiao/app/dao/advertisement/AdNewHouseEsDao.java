package com.toutiao.app.dao.advertisement;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-11-02
 * Time:   15:41
 * Theme:
 */
public interface AdNewHouseEsDao {

    SearchResponse getAdNewHouse(BoolQueryBuilder booleanQueryBuilder, String city);
}
