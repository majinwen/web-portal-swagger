package com.toutiao.app.service.favorite;

import com.toutiao.app.domain.favorite.IsFavoriteDo;
import com.toutiao.app.domain.favorite.UserCenterFavoriteCountDo;

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




}
