package com.toutiao.app.dao.mapsearch;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;

/**
 * @ClassName EsfMapSearchEsDaoImpl
 * @Author jiangweilong
 * @Date 2018/11/23 7:41 PM
 * @Description:
 **/

public interface EsfMapSearchEsDao {

    /**
     * 区域查询
     * @param boolQueryBuilder
     * @param city
     * @return
     */
    SearchResponse esfMapSearchByDistrict(BoolQueryBuilder boolQueryBuilder, String city);


    /**
     * 商圈查询
     * @param boolQueryBuilder
     * @param city
     * @return
     */
    SearchResponse esfMapSearchByBizcircle(BoolQueryBuilder boolQueryBuilder, String city);

    /**
     * 社区查询
     * @param boolQueryBuilder
     * @param city
     * @return
     */
    SearchResponse esfMapSearchByCommunity(BoolQueryBuilder boolQueryBuilder, String city);


    SearchResponse esfMapSearchHouseDrawCircleList(FunctionScoreQueryBuilder query,
                                                   Integer pageNum, Integer pageSize, String city, String sort);

    /**
     * 房源列表
     * @param boolQueryBuilder
     * @param sort
     * @param pageNum
     * @param pageSize
     * @param city
     * @return
     */
    SearchResponse esfMapSearchHouseList(BoolQueryBuilder boolQueryBuilder, GeoDistanceSortBuilder geoDistanceSort, Integer pageNum, Integer pageSize, String city, String sort);

    /**
     * 附近查询
     * @param boolQueryBuilder
     * @param city
     * @return
     */
    SearchResponse esfMapSearchByNear(BoolQueryBuilder boolQueryBuilder, String city);

    /**
     * 地铁找房
     * @param boolQueryBuilder
     * @param city
     * @return
     */
    SearchResponse esfMapSearchBySubway(BoolQueryBuilder boolQueryBuilder, String city);

    /**
     * 根据地铁站id获取地铁站坐标
     * @param stationId
     * @param city
     * @return
     */
    SearchResponse queryStationPoint(Integer stationId, String city);

    /**
     * 根据地铁线id获取地铁线信息
     * @param lineId
     * @param city
     * @return
     */
    SearchResponse queryLinePoint(Integer lineId, String city);

}
