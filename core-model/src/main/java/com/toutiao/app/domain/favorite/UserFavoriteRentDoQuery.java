package com.toutiao.app.domain.favorite;

import lombok.Data;


@Data
public class UserFavoriteRentDoQuery {

    /**
     * 室
     */
    private  Integer  room;

    /**
     * 面积
     */
    private  Double  houseArea;


    /**
     *  大楼名称
     */
    private  String buildingName;

    /**
     * 总价
     */
    private  Double houseTotalPrice;

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
    private  String  housePhotoTitle;

    /**
     * 二手房标题
     */
    private  String  houseTitle;

    private  Double rentPrice;

    private  String rentType;

    private  String forward;


}
