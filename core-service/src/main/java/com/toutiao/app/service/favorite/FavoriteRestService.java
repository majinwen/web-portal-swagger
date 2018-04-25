package com.toutiao.app.service.favorite;


public interface FavoriteRestService {



    /**
     * 获取新房收藏数量
     */

    Integer newHouseFavoriteByNewCode(Integer newCode);

    /**
     * 个人中心小区收藏数量
     */
    Integer queryPlotFavoriteByUserId(Integer userId);

    /**
     * 列表页小区总收藏数量展示
     */
    Integer queryPlotFavoriteByPlotId(Integer plotId);

    /**
     * 个人中心二手房取消收藏
     * @param esfId
     * @param userId
     * @return
     */
    Integer updateEsfFavoriteByEsfIdAndUserId(Integer esfId,Integer userId);
}
