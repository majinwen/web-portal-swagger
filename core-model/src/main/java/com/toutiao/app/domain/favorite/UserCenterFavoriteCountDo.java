package com.toutiao.app.domain.favorite;

import lombok.Data;

@Data
public class UserCenterFavoriteCountDo {


    /**
     * 二手房收藏数量
     */
    private  Integer esfHouseFavoriteCount;


    /**
     * 租房收藏数量
     */
    private  Integer rentHouseFavoriteCount;

    /**
     * 小区收藏数量
     */
     private Integer plotFavoriteCount;

    /**
     * 新房收藏数量
     */
    private  Integer newHouseFavoriteCount;
}
