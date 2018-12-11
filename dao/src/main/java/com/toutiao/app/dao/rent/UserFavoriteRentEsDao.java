package com.toutiao.app.dao.rent;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Configuration
public interface UserFavoriteRentEsDao {

    /**
     * 根据定制条件获取租房列表
     * @param boolQueryBuilder
     * @param from
     * @param size
     * @param city
     * @return
     */
    SearchResponse queryRentListByUserFavorite(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size, String city);
}
