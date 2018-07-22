package com.toutiao.app.domain.plot;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

@Data
public class PlotTop50Do {

    /**
     * 均价
     */
    //@ChangeName("averagePrice")
    private Double avgPrice;

    /**
     * 小区编号
     */
    //@ChangeName("buildingId")
    private Integer id;

    /**
     * 小区照片
     */
    //@ChangeName("buildingImages")
    private String[] photo;


    /**
     * 近地铁描述
     */
    private String trafficInformation;


    /**
     * 在商圈种排名
     */
    private Integer rankAvgInBizcircle;


    /**
     * 待售二手房数量
     */
    private Integer houseCount;

    /**
     * 最低价
     */
    private Double lowestPrice;


    /**
     * 商圈
     */
    //@ChangeName("areaName")
    private String tradingArea;

    /**
     * 区域
     */
    // @ChangeName("districtName")
    private String area;


    /**
     * 大楼名称
     */
    private String rc;
}