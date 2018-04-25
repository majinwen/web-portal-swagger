package com.toutiao.app.service.favorite;


import com.toutiao.app.domain.favorite.DeleteEsfFavoriteDo;
import com.toutiao.app.domain.favorite.DeleteRentFavoriteDo;
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

    /**
     * 列表页获取小区收藏数量
     * @param plotId
     * @return
     */
    Integer getPlotFavoriteCountByPlotId(Integer plotId);

    /**
     * 二手房取消收藏
     * @param deleteEsfFavoriteDo
     * @return
     */
    Boolean updateEsfFavoriteByEsfIdAndUserId(DeleteEsfFavoriteDo deleteEsfFavoriteDo);

    /**
     * 租房取消收藏
     * @param deleteRentFavoriteDo
     * @return
     */
    Boolean updateRentFavoriteByRentIdAndUserId(DeleteRentFavoriteDo deleteRentFavoriteDo);

}
