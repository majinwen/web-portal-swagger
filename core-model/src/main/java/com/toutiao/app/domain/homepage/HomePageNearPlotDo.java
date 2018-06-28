package com.toutiao.app.domain.homepage;

import lombok.Data;

import java.util.List;

@Data
public class HomePageNearPlotDo {
    /**
     * 小区编号
     */
    private Integer id;
    /**
     * 小区名称/楼盘名称
     */
    private String rc;
    /**
     * 别名
     */
    private String alias;
    /**
     * 区域编号
     */
    private String areaId;
    /**
     * 区域
     */
    private String area;
    /**
     * 商圈编号
     */
    private String tradingAreaId;
    /**
     * 商圈
     */
    private String tradingArea;
    /**
     * 地址
     */
    private String address;
    /**
     * 坐标
     */
    private String location;
    /**
     * 待售房源
     */
    private List<String> saleHouse;
    /**
     * 均价
     */
    private Double avgPrice;
    /**
     * 距离
     */
    private Double distance;
    /**
     * 商圈均价排名
     */
    private Integer ranking;
    /**
     * 在售房源套数
     */
    private Integer esfNum;
    /**
     * 最低价格
     */
    private Double StartPrice;
}
