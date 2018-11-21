package com.toutiao.app.dao.plot;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;

public interface NearbyPlotsEsDao {

    /**
     * 根据用户坐标获取用户附近小区列表
     * @param geoDistanceQueryBuilder 坐标
     * @param sort 距离计算
     * @param boolQueryBuilder 筛选条件
     * @param pageNum 起始
     * @param size 每页查询数量
     * @return
     */
    SearchResponse queryNearbyPlotsListByUserCoordinate(GeoDistanceQueryBuilder geoDistanceQueryBuilder, GeoDistanceSortBuilder sort,
                                                        BoolQueryBuilder boolQueryBuilder, String keyword, Integer pageNum, Integer size,
                                                        String city);
}
