package com.toutiao.app.domain.rent;

import lombok.Data;

/**
 * Created by 18710 on 2018/12/13.
 */
@Data
public class RentGuessYourLikeQuery {
    /**
     * 整租:1/合租:2/未知:3
     */
    private Integer rentType;

    /**
     * 几室
     */
    private String room;//几室

    /**
     * 几厅
     */
    private Integer hall;//合租户型

    /**
     * 出租价格
     */
    private double totalPrice;

    /**
     * 商圈id
     */
    private Integer areaId;

    /**
     * 页数
     */
    private Integer pageNum = 1;

    /**
     * 页面数量
     */
    private Integer pageSize = 10;
}
