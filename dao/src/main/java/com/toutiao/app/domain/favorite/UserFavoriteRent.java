package com.toutiao.app.domain.favorite;

import lombok.Data;

import java.util.Date;
@Data
public class UserFavoriteRent {
    /**
     * 租房收藏id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 租房id
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
     * 租金
     */
    private  Double rentPrice;

    /**
     * 上下架状态
     */
    private  Integer status;

    /**
     * 出租类型
     */
    private  String rentType;

    /**
     * 房屋面积
     */
    private  Double houseArea;

    /**
     * 室
     */
    private  Integer room;

    /**
     * 朝向
     */
    private  String forward;

    /**
     * 大楼名称
     */
    private  String buildingName;

    /**
     * 房屋标题图
     */
    private  String housePhotoTitle;

    /**
     * 房屋标题
     */
    private  String houseTitle;
}