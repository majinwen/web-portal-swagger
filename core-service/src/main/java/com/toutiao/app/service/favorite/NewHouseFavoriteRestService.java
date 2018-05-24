package com.toutiao.app.service.favorite;

import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteListDoQuery;

public interface NewHouseFavoriteRestService {

    /**
     * 新房收藏列表
     * @param newHouseFavoriteListDoQuery
     * @return
     */
    NewHouseFavoriteDomain queryNewHouseFavoriteListByUserId(NewHouseFavoriteListDoQuery newHouseFavoriteListDoQuery);
}
