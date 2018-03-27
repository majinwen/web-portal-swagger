package com.toutiao.app.dao.sellhouse;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.ScriptSortBuilder;


public interface SellHouseEsDao {


    /**
     * 根据houseid获取二手房房源信息
     * @param booleanQueryBuilder
     * @return
     */
    SearchResponse getSellHouseByHouseId(BoolQueryBuilder booleanQueryBuilder);

    /**
     * 二手房附件好房
     * @param booleanQueryBuilder
     * @param scriptSortBuilder
     * @param sort
     * @return
     */
    SearchResponse getSellHouseByHouseIdAndLocation(BoolQueryBuilder booleanQueryBuilder, ScriptSortBuilder scriptSortBuilder, GeoDistanceSortBuilder sort);


    /**
     * 条件筛选二手房房源
     * @param booleanQueryBuilder
     * @param location
     * @param sort
     * @param order
     * @return
     */
    SearchResponse getSellHouseByChoose(BoolQueryBuilder booleanQueryBuilder,GeoDistanceQueryBuilder location, GeoDistanceSortBuilder sort,String keyWord,Integer order);
}
