package com.toutiao.app.domain.favorite;

import lombok.Data;


@Data
public class UserFavoriteEsHouseDoQuery {

    /**
     * 室
     */
    private  Integer  room;

    /**
     * 面积
     */
    private  Double  buildArea;


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
