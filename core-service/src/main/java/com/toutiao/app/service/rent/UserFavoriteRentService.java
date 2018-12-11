package com.toutiao.app.service.rent;

/**
 * Created by CuiShihao on 2018/12/11
 */

import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.rent.UserFavoriteRentListDomain;

/**
 * 定制条件租房列表接口
 */
public interface UserFavoriteRentService {

    /**
     * 根据定制条件获取租房列表
     * @param userFavoriteConditionDoQuery
     * @return
     */
    UserFavoriteRentListDomain queryRentListByUserFavorite(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery);
}
