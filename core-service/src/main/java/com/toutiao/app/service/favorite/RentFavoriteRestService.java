package com.toutiao.app.service.favorite;


import com.toutiao.app.domain.favorite.rent.RentFavoriteDomain;
import com.toutiao.app.domain.favorite.rent.RentFavoriteListDoQuery;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;

public interface RentFavoriteRestService {

    /**
     * 租房收藏列表
     * @param rentFavoriteListDoQuery
     * @return
     */
    RentFavoriteDomain queryRentFavoriteListByUserId(RentFavoriteListDoQuery rentFavoriteListDoQuery);
    RentFavoriteDomain guessULikeRentByUserId(RentFavoriteListDoQuery rentFavoriteListDoQuery);
}
