package com.toutiao.app.domain.favorite;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class UserFavoriteRentDoQuery {

    /**
     * 室
     */
    private Integer room;

    /**
     * 面积
     */
    private Double houseArea;


    /**
     * 大楼名称
     */
    private String buildingName;

    /**
     * 总价
     */
    private Double houseTotalPrice;

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

    private String RentTypeName;

    private Date createTime;

    private BigDecimal rentPrice;

    private String rentType;

    private String forward;

    private Integer cityId;

    private Integer hall;

    private Short status = 0;

    private String districtName;

    private String areaName;

    private String[] tags;

    private Short isDel = 0;

    private String companyIcon;


}
