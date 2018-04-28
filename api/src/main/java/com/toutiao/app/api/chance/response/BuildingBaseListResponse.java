package com.toutiao.app.api.chance.response;

import lombok.Data;

/**
 * 楼盘列表基础返回实体类
 * 新楼，小区
 */

@Data
public class BuildingBaseListResponse {

    /**
     * 楼盘id
     */
    private Integer buildingId;
    /**
     * 楼盘名称
     */
    private String buildingName;
    /**
     * 楼盘昵称
     */
    private String buildingNickName;
    /**
     * 区域名称
     */
    private String districtName;
    /**
     * 商圈名称
     */
    private String areaName;
    /**
     * 楼盘参考均价(元/m2)
     */
    private Double averagePrice;
    /**
     * 楼盘总价
     */
    private Double totalPrice;
    /**
     * 楼盘标签
     */
    private String[] tags;
    /**
     * 最近地铁站信息
     */
    private String nearBySubway;
    /**
     * 车位配比
     */
    private String parkRatio;
    /**
     * 收藏数量
     */
    private Integer favoriteCount;
    /**
     * 是否被收藏
     */
    private Integer isFavorite;

}
