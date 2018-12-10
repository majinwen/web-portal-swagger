package com.toutiao.app.dao.mapsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public interface RentMapSearchEsDao {

    SearchResponse getRentMapSearch(SearchSourceBuilder searchSourceBuilder, String city);

    SearchResponse getDistanceAndAreainfo(SearchSourceBuilder searchSourceBuilder);

    SearchResponse getSubwayLineAndSubwayStationinfo(SearchSourceBuilder searchSourceBuilder);

    SearchResponse getSubwayInfo(SearchSourceBuilder searchSourceBuilder);

    SearchResponse getSubwayStationinfo(BoolQueryBuilder boolQueryBuilder, String city);

}
