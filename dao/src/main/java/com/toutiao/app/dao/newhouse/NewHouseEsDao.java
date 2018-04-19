package com.toutiao.app.dao.newhouse;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;

public interface NewHouseEsDao {

    /**
     * 根据新房id获取新房详情
     * @param boolQueryBuilder
     * @return
     */
    SearchResponse getNewHouseBulid(BoolQueryBuilder boolQueryBuilder);


    /**
     * 获取新房列表
     * @param boolQueryBuilder
     * @param pageNum
     * @param pageSize
     * @return
     */
    SearchResponse getNewHouseList(BoolQueryBuilder  boolQueryBuilder, Integer pageNum,Integer pageSize);



    SearchResponse getDynamicByNewCode(BoolQueryBuilder  boolQueryBuilder, Integer pageNum,Integer pageSize);



}
