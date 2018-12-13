package com.toutiao.app.domain.rent;

import lombok.Data;


/**
 * Created by CuiShihao on 2018/12/12
 */
@Data
public class UserFavoriteRentListDoQuery {

    /**
     * 起始价格
     */
    private double beginPrice;

    /**
     * 区域id
     */
    private Integer[] districtId = null;

    /**
     * 整租户型
     */
    private String elo = null;

    /**
     * 结束价格
     */
    private double endPrice;

    /**
     * 合租户型
     */
    private String jlo = null;

    /**
     * 户型
     */
    private Integer[] layoutId = null;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 页容量
     */
    private Integer pageSize = 10;

    /**
     * 整租:1/合租:2/未知:3
     */
    private String rentType = null;

    /**
     * 地铁线id
     */
    private Integer[] subwayLineId = null;

    /**
     * 排序
     */
    private String sort;
}
