package com.toutiao.app.dao.newhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;

public interface NewHouseEsDao {

    /**
     * 根据新房id获取新房详情
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse getNewHouseBulid(BoolQueryBuilder boolQueryBuilder, String userAgent, String city);


    /**
     * 获取新房列表
     * @param boolQueryBuilder
     * @param pageNum
     * @param pageSize
     * @return
     */
    SearchResponse getNewHouseList(BoolQueryBuilder  boolQueryBuilder, Integer pageNum,Integer pageSize,FieldSortBuilder levelSort,FieldSortBuilder buildingSort, String userAgent, String city);



    SearchResponse getDynamicByNewCode(BoolQueryBuilder  boolQueryBuilder, Integer pageNum,Integer pageSize, String userAgent, String city);



}
