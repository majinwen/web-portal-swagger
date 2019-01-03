package com.toutiao.app.dao.plot;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;


public interface PlotsThemeEsDao {
    SearchResponse getPlotsThemeList(BoolQueryBuilder boolQueryBuilder, Integer pageNum, Integer pageSize, String city);


    SearchResponse getHouseAreaByPlotId(Integer plotId, String city);

    SearchResponse getHouseMaxAndMinArea(Integer plotId, String city);
}
