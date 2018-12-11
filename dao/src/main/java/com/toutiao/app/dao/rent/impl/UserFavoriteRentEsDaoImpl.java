package com.toutiao.app.dao.rent.impl;

import com.toutiao.app.dao.rent.UserFavoriteRentEsDao;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Service
public class UserFavoriteRentEsDaoImpl implements UserFavoriteRentEsDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Value("${tt.zufang.rent.index}")
    private String rentIndex;
    @Value("${tt.zufang.rent.type}")
    private String rentType;
    @Override
    public SearchResponse queryRentListByUserFavorite(BoolQueryBuilder boolQueryBuilder, Integer from, Integer size, String city) {
        return null;
    }
}
