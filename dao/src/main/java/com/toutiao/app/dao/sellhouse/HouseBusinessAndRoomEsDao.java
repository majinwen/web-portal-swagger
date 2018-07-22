package com.toutiao.app.dao.sellhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * 商圈+户型房源专题页数据层
 */
public interface HouseBusinessAndRoomEsDao {
    SearchResponse getHouseBusinessAndRoomHouses(BoolQueryBuilder query, Integer pageNum, Integer pageSize);

    SearchResponse getHouseBusinessAndRoomAveragePrice(BoolQueryBuilder query);
}
