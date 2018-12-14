package com.toutiao.app.service.rent;

/**
 * Created by CuiShihao on 2018/12/11
 */

import com.toutiao.app.domain.rent.RentCustomConditionDomain;
import com.toutiao.app.domain.rent.UserFavoriteRentListDoQuery;
import com.toutiao.app.domain.rent.UserFavoriteRentListDomain;

/**
 * 定制条件租房列表接口
 */
public interface UserFavoriteRentService {

    /**
     * 根据定制条件获取租房列表
     * @param userFavoriteRentListDoQuery
     * @return
     */
    UserFavoriteRentListDomain queryRentListByUserFavorite(UserFavoriteRentListDoQuery userFavoriteRentListDoQuery, String city);

    /**
     * 根据定制条件里的地铁线id获取对应的房源和小区数量
     * @param userFavoriteRentListDoQuery
     * @param city
     * @return
     */
    RentCustomConditionDomain querySubwayLineHouse(UserFavoriteRentListDoQuery userFavoriteRentListDoQuery, String city);
}
