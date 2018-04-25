package com.toutiao.app.service.favorite;

import com.toutiao.app.domain.favorite.IsFavoriteDo;
import com.toutiao.app.domain.favorite.UserCenterFavoriteCountDo;
import com.toutiao.app.domain.favorite.UserFavoriteNewHouse;
import com.toutiao.app.domain.favorite.UserFavoriteVillage;
import com.toutiao.web.common.restmodel.NashResult;

public interface FavoriteRestService {



    /**
     * 获取列表新房收藏数量
     */

    Integer newHouseFavoriteByNewCode(Integer newCode);


    /**
     * 获取个人中心收藏数量
     */

    UserCenterFavoriteCountDo getFavoriteCountByUser(Integer userId);


    /**
     * 判断是否被收藏
     */

    Boolean  getIsFavorite(Integer type, IsFavoriteDo isFavoriteDo);


    /**
     * 取消新房收藏
     */

    NashResult cancelNewHouseByNewCode(UserFavoriteNewHouse userFavoriteNewHouse);

    /**
     * 取消小区收藏
     */
    NashResult cancelVillageByVillageId(UserFavoriteVillage userFavoriteVillage);


}
