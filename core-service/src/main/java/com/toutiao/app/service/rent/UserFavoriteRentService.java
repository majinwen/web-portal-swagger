package com.toutiao.app.service.rent;

/**
 * Created by CuiShihao on 2018/12/11
 */

import com.toutiao.app.domain.rent.RentHouseDoQuery;
import com.toutiao.app.domain.rent.UserFavoriteRentListDomain;

/**
 * 定制条件租房列表接口
 */
public interface UserFavoriteRentService {

    /**
     * 根据定制条件获取租房列表
     * @param rentHouseDoQuery
     * @return
     */
    UserFavoriteRentListDomain queryRentListByUserFavorite(RentHouseDoQuery rentHouseDoQuery, String city);
}
