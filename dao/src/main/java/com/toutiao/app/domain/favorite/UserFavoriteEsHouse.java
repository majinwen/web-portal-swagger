package com.toutiao.app.domain.favorite;

import lombok.Data;

import java.util.Date;
@Data
public class UserFavoriteEsHouse {
    /**
     * 二手房收藏id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 二手房房源id
     */
    private String houseId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel;

    /**
     * 是否下架
     */
    private  Integer status;

    private  Integer  room;

    private  String buildingName;

    private  Double houseTotalPrices;

    private  String  housePhotoTitle;

    private  String  houseTitle;

    private  Double  buildArea;

    private  String forward;

    /**
     * 价格涨降标志
     */
    private Integer priceIncreaseDecline;
    /**
     * 认领标志
     */
    private Integer isClaim;

    /**
     * 城市
     */
    private Integer cityId;
}