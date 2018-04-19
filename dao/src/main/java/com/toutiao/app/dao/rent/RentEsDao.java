package com.toutiao.app.dao.rent;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
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
}
