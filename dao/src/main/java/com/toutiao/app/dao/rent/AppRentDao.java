package com.toutiao.app.dao.rent;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.Map;

@Configuration
public interface AppRentDao {

    /**
     * 查询小区内的出租房屋(普租+公寓)
     * @param plotId
     * @return
     */
    SearchResponse queryRentListByPlotId(BoolQueryBuilder booleanQueryBuilder) throws Exception;

    /**
     * 根据出租房源的id查询出租房源详情
     * @param rentId
     * @return
     */
    SearchResponse queryRentByRentId(BoolQueryBuilder booleanQueryBuilder) throws Exception;
}
