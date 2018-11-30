package com.toutiao.app.service.favorite;

import com.toutiao.app.domain.favorite.*;
import com.toutiao.app.domain.plot.PlotFavoriteListDo;
import com.toutiao.app.domain.plot.PlotFavoriteListDoQuery;
import com.toutiao.web.common.restmodel.NashResult;

public interface FavoriteRestService {

    /**
     * 获取列表新房收藏数量
     */

//    Integer newHouseFavoriteByNewCode(Integer newCode);

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

    Integer cancelNewHouseByNewCode(UserFavoriteNewHouse userFavoriteNewHouse);

    /**
     * 取消小区收藏
     */
    Integer cancelVillageByVillageId(PlotIsFavoriteDoQuery plotIsFavoriteDoQuery);


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
     * @param plotFavoriteListDoQuery
     * @return
     */
    PlotFavoriteListDo getPlotFavoriteByUserId(PlotFavoriteListDoQuery plotFavoriteListDoQuery);

    /**
     * 添加小区收藏
     * @param plotsAddFavoriteDoQuery
     * @return
     */
    Integer addPlotsFavorite(PlotsAddFavoriteDoQuery plotsAddFavoriteDoQuery);

    /**
     * 添加新房收藏
     * @param newHouseAddFavoriteDoQuery
     * @return
     */
    Integer addNewHouseFavorite(NewHouseAddFavoriteDoQuery newHouseAddFavoriteDoQuery);





    /**
     * 添加二手房收藏
     */
    Integer addEsfFavorite(UserFavoriteEsHouseDoQuery userFavoriteEsHouseDoQuery);

    /**
     * 添加出租收藏
     */
    Integer addRentFavorite(UserFavoriteRentDoQuery userFavoriteRent);

    /**
     * 查询我的收藏房源
     * @param favoriteHouseDoQuery
     * @return
     */
    FavoriteHouseDomain queryFavoriteHouseList(FavoriteHouseListDoQuery favoriteHouseDoQuery);

    Integer cancelFavoriteHouse(CancelFavoriteHouseDto cancelFavoriteHouseDto);
}
