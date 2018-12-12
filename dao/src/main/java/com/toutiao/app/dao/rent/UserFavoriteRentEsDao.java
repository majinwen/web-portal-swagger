package com.toutiao.app.dao.rent;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Configuration
public interface UserFavoriteRentEsDao {

    /**
     * 根据定制条件获取租房列表
     * @param query
     * @param distance
     * @param keyword
     * @param from
     * @param size
     * @param city
     * @param geoDistanceSort
     * @param sort
     * @return
     */
    SearchResponse queryRentListByUserFavorite(FunctionScoreQueryBuilder query, Integer distance, String keyword, Integer from, Integer size, String city, GeoDistanceSortBuilder geoDistanceSort, String sort);
}
