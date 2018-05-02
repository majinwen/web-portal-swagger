package com.toutiao.app.service.favorite;

import com.toutiao.app.domain.favorite.*;
import com.toutiao.app.domain.plot.PlotDetailsFewDomain;
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
     * @param deleteRentFavoriteDo
     * @return
     */
    Boolean updateRentFavoriteByRentIdAndUserId(DeleteRentFavoriteDo deleteRentFavoriteDo);

    /**
     * 小区是否收藏
     * @param plotIsFavoriteDo
     * @return
     */
    Boolean getPlotIsFavorite(PlotIsFavoriteDo plotIsFavoriteDo);

    /**
     * 新房是否收藏
     * @param newHouseIsFavoriteDo
     * @return
     */
    Boolean getNewHouseIsFavorite(NewHouseIsFavoriteDo newHouseIsFavoriteDo);


    /**
     * 获取小区收藏列表
     * @param userId
     * @return
     */
    PlotDetailsFewDomain getPlotFavoriteByUserId(Integer userId,Integer pageNum,Integer size);


    /**
     * 添加二手房收藏
     */
    NashResult addEsfFavorite(UserFavoriteEsHouse userFavoriteEsHouse);

    /**
     * 添加出租收藏
     */
    NashResult addRentFavorite(UserFavoriteRent userFavoriteRent);

}
