package com.toutiao.app.dao.sellhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;

public interface NearbySellHouseEsDao {


    SearchResponse getNearbySellHouseByFilter(FunctionScoreQueryBuilder query, Integer pageNum,Integer pageSize, String city);
}
