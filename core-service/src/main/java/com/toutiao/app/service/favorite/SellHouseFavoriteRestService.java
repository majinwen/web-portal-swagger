package com.toutiao.app.service.favorite;


import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;

public interface SellHouseFavoriteRestService {

    /**
     * 二手房收藏列表
     * @param sellHouseFavoriteListDoQuery
     * @return
     */
    SellHouseFavoriteDomain queryNewHouseFavoriteListByUserId(SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery, String cityId);
}
