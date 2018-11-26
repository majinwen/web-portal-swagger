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

    /**
     * 房源列表
     * @param query
     * @param city
     * @param sort
     * @return
     */
    SearchResponse esfMapSearchHouseList(FunctionScoreQueryBuilder query, Integer distance, String keyword,
                                         Integer pageNum, Integer pageSize, String city, GeoDistanceSortBuilder sort);

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

}
