package com.toutiao.app.service.favorite;

import com.toutiao.app.domain.favorite.*;
import com.toutiao.app.domain.plot.PlotDetailsFewDomain;
import com.toutiao.app.domain.plot.PlotFavoriteListDo;
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
     * 列表页获取小区收藏数量
     * @param plotId
     * @return
     */
    Integer getPlotFavoriteCountByPlotId(Integer plotId);

    /**
     * 取消新房收藏
     */

    NashResult cancelNewHouseByNewCode(UserFavoriteNewHouse userFavoriteNewHouse);

    /**
     * 取消小区收藏
     */
    NashResult cancelVillageByVillageId(UserFavoriteVillage userFavoriteVillage);


    /**
     * 二手房取消收藏
     * @param deleteEsfFavoriteDo
     * @return
     */
    Boolean updateEsfFavoriteByEsfIdAndUserId(DeleteEsfFavoriteDo deleteEsfFavoriteDo);

    /**
     * 租房取消收藏
     * @param deleteRentFavoriteDoQuery
     * @return
     */
    Boolean updateRentFavoriteByRentIdAndUserId(DeleteRentFavoriteDoQuery deleteRentFavoriteDoQuery);

    /**
     * 小区是否收藏
     * @param plotIsFavoriteDoQuery
     * @return
     */
    Boolean getPlotIsFavorite(PlotIsFavoriteDoQuery plotIsFavoriteDoQuery);

    /**
     * 新房是否收藏
     * @param newHouseIsFavoriteDoQuery
     * @return
     */
    Boolean getNewHouseIsFavorite(NewHouseIsFavoriteDoQuery newHouseIsFavoriteDoQuery);


    /**
     * 获取小区收藏列表
     * @param userId
     * @return
     */
    PlotFavoriteListDo getPlotFavoriteByUserId(Integer userId, Integer pageNum, Integer size);

}
