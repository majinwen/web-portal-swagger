package com.toutiao.app.api.chance.response.plot;

import lombok.Data;

import java.util.Map;

@Data
public class PlotDetailsFewResponse {
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
     * 小区照片
     */
    private String[] photo;
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
     * 地铁站编号
     */
    private String[] metroStationId;
    /**
     * 地铁站
     */
    private String[] metroStation;
    /**
     * 地铁站与小区的距离
     */
    private Map metroWithPlotsDistance;
    /**
     * 地铁线路编号
     */
    private String[] subwayLineId;
    /**
     * 地铁线路
     */
    private String[] subwayLine;
    /**
     * 交通信息
     */
    private String TrafficInformation;
    /**
     * 标签编号
     */
    private String[] labelId;
    /**
     * 标签
     */
    private String[] label;
    /**
     * 待售房源
     */
    private String[] saleHouse;
    /**
     * 均价
     */
    private Integer avgPrice;
    /**
     * 总价
     */
    private Integer sumPrice;
    /**
     * 建成年代
     */
    private String abbreviatedAge;
    /**
     * 车位配比
     */
    private String carPositionRatio;
    /**
     * 小区总个数
     */
    private Integer totalNum;
}
