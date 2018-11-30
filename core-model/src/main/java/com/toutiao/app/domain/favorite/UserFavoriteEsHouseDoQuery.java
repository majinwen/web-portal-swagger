package com.toutiao.app.domain.favorite;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class UserFavoriteEsHouseDoQuery {

    /**
     * 室
     */
    private Integer room;

    /**
     * 面积
     */
    private Double buildArea;


    /**
     * 大楼名称
     */
    private String buildingName;

    /**
     * 总价
     */
    private BigDecimal houseTotalPrices;

    /**
     * 用户id
     */
    private Integer userId;


    /**
     * 房源id
     */
    private String houseId;

    /**
     * 二手房标题图
     */
    private String housePhotoTitle;

    /**
     * 二手房标题
     */
    private String houseTitle;

    private Double rentPrice;

    private String rentType;

    private String forward;

    private Date createTime;

    /**
     * 价格涨降标志（0未变动，1涨价，2降价）
     */
    private Integer priceIncreaseDecline;
    /**
     * 认领标志
     */
    private Integer isClaim;

    private Integer housePhotoTitleTags;

    private Integer cityId;

    private Integer hall;

    private Short status = 0;

    private String districtName;

    private String areaName;

    private String[] tags;

    private Short isDel = 0;

    private String companyIcon;


}
