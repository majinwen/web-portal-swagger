package com.toutiao.app.dao.rent;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Configuration
public interface UserFavoriteRentEsDao {

    /**
     * 根据定制条件获取租房列表
     * @param from
     * @param size
     * @param city
     * @param sort
     * @return
     */
    SearchResponse queryRentListByUserFavorite(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size, String city, String sort);

    /**
     * 根据定制条件里的地铁线id获取对应的房源和小区数量
     * @param searchSourceBuilder
     * @param city
     * @return
     */
    SearchResponse querySubwayLineHouse(SearchSourceBuilder searchSourceBuilder, String city);

    /**
     * 获取地铁站信息
     * @param boolQueryBuilder
     * @param city
     * @return
     */
    SearchResponse getSubwayStationinfo(BoolQueryBuilder boolQueryBuilder, String city);
}
