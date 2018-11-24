package com.toutiao.app.dao.mapsearch;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

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
     * 区域查询
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

}
