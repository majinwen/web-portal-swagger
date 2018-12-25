package com.toutiao.app.dao.mapsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * @ClassName NewHouseMapSearchEsDao
 * @Author jiangweilong
 * @Date 2018/11/24 7:23 PM
 * @Description:
 **/
public interface NewHouseMapSearchEsDao {


    SearchResponse NewHouseMapSearchByDistrict(BoolQueryBuilder boolQueryBuilder, String city);



    SearchResponse NewHouseMapSearchByBuild(BoolQueryBuilder boolQueryBuilder, String city);



    SearchResponse getNewHouseMapByDbAvgPrice(BoolQueryBuilder boolQueryBuilder, String city);



    SearchResponse getSubwayLineAndSubwayStationinfo(BoolQueryBuilder boolQueryBuilder, String city);



    SearchResponse queryDistiictNewHouseCount(BoolQueryBuilder boolQueryBuilder, String city);
}
