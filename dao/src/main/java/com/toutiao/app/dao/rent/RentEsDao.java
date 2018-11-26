package com.toutiao.app.dao.rent;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface RentEsDao {

    /**
     * 查询小区内的出租房屋(普租+公寓)
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse queryRentListByPlotId(BoolQueryBuilder booleanQueryBuilder,Integer from, String city) ;

    /**
     * 根据出租房源的id查询出租房源详情
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse queryRentByRentId(BoolQueryBuilder booleanQueryBuilder, String cityCode) ;

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
    SearchResponse queryRentNumByPlotId(BoolQueryBuilder boolQueryBuilder, String city);

    /**
     * 根据小区id查询该小区下的出租房源的个数（非聚合）
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse queryRentCountByPlotId(BoolQueryBuilder boolQueryBuilder, String city);

    /**
     * 获取租房推荐列表
     * @param boolQueryBuilder
     * @param from
     * @param size
     * @return
     */
    SearchResponse queryRentList(BoolQueryBuilder boolQueryBuilder,Integer from, Integer size, String city);

    /**
     * 获取推优房源
     * @param boolQueryBuilder
     * @param uid
     * @return
     */
    SearchResponse queryRecommendRentList(BoolQueryBuilder boolQueryBuilder,String uid, String city);

    /**
     * 获取附近的出租房源
     * @param query
     * @param from
     * @return
     */
    SearchResponse queryNearRentHouse(FunctionScoreQueryBuilder query, Integer from, String city);

    /**
     * 获取搜索列表
     * @param query
     * @param distance
     * @param keyword
     * @param from
     * @param size
     * @return
     */
    SearchResponse queryRentSearchList(FunctionScoreQueryBuilder query ,Integer distance, String keyword, Integer from, Integer size, String city, GeoDistanceSortBuilder geoDistanceSort, String sort);
    /**
     * 获取搜索列表
     * @param query
     * @param distance
     * @param keyword
     * @param from
     * @param size
     * @return
     */
    SearchResponse queryCommuteRentSearchList(FunctionScoreQueryBuilder query ,Integer distance, String keyword, Integer from, Integer size, String city, GeoDistanceSortBuilder sort);

    /**
     * 获取小区出租房源均价最低
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse getRentPriceByPlotId(BoolQueryBuilder boolQueryBuilder, String city);

}
