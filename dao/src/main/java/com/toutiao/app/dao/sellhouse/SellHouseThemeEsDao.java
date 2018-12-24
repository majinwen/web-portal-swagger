package com.toutiao.app.dao.sellhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;


public interface SellHouseThemeEsDao {

    SearchResponse getCutPriceShellHouse(BoolQueryBuilder query, Integer pageNum, Integer pageSize, String city);


    SearchResponse getLowPriceShellHouse(BoolQueryBuilder query, Integer pageNum, Integer pageSize, String city);



    SearchResponse getBeSureToSnatchShellHouse(BoolQueryBuilder query, Integer pageNum, Integer pageSize, String city);
}
