package com.toutiao.app.service.rent.impl;

import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.rent.UserFavoriteRentDetailDo;
import com.toutiao.app.domain.rent.UserFavoriteRentListDomain;
import com.toutiao.app.service.rent.UserFavoriteRentService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Service
public class UserFavoriteRentServiceImpl implements UserFavoriteRentService {

    private static final Integer IS_DEL = 0;//房源未删除 0-未删除
    private static final Integer RELEASE_STATUS = 1;//房源发布状态 1-已发布

    @Override
    public UserFavoriteRentListDomain queryRentListByUserFavorite(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery) {
        UserFavoriteRentListDomain userFavoriteRentListDomain = new UserFavoriteRentListDomain();
        List<UserFavoriteRentDetailDo> userFavoriteRentDetailDos = new ArrayList<>();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("is_del", IS_DEL));
        boolQueryBuilder.must(QueryBuilders.termQuery("release_status", RELEASE_STATUS));

        return null;
    }
}
