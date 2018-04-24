package com.toutiao.app.dao.rent;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.Map;

@Configuration
public interface RentEsDao {

    /**
     * 查询小区内的出租房屋(普租+公寓)
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse queryRentListByPlotId(BoolQueryBuilder booleanQueryBuilder,Integer from) ;

    /**
     * 根据出租房源的id查询出租房源详情
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse queryRentByRentId(BoolQueryBuilder booleanQueryBuilder) ;

    /**
     * 附近5km内出租房源(规则:app的是吧，那就优先三公里的录入房源由近到远)
     * @param boolQueryBuilder
     * @param location
     * @param sort
     * @return
     */
    SearchResponse queryNearHouseByLocation(BoolQueryBuilder boolQueryBuilder, GeoDistanceQueryBuilder location, GeoDistanceSortBuilder sort, Integer from, Integer size);

    /**
     * 根据小区id查询该小区下的出租房源的个数
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse queryRentNumByPlotId(BoolQueryBuilder boolQueryBuilder);

}
