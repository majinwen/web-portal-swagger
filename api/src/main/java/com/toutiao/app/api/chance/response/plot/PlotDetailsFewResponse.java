package com.toutiao.app.api.chance.response.plot;


import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class PlotDetailsFewResponse implements Serializable{
    /**
     * 小区编号
     */
    @ChangeName("buildingId")
    private Integer id;
    /**
     * 小区名称/楼盘名称
     */
    @ChangeName("buildingName")
    private String rc;
    /**
     * 别名
     */
    @ChangeName("houseAlias")
    private String alias;
    /**
     * 小区照片
     */
    @ChangeName("housePhoto")
    private String[] photo;
    /**
     * 区域编号
     */
    @ChangeName("districtId")
    private String areaId;
    /**
     * 区域
     */
    @ChangeName("districtName")
    private String area;
    /**
     * 商圈编号
     */
    @ChangeName("areaId")
    private String tradingAreaId;
    /**
     * 商圈
     */
    @ChangeName("areaName")
    private String tradingArea;
    /**
     * 地址
     */
    @ChangeName("houseAddress")
    private String address;
    /**
     * 坐标
     */
    private String location;
    /**
     * 地铁站编号
     */
    @ChangeName("subwayStationId")
    private String[] metroStationId;
    /**
     * 地铁站
     */
    @ChangeName("subwayStation")
    private String[] metroStation;
    /**
     * 地铁站与小区的距离
     */
    @ChangeName("subwayWithHouseDistance")
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
    @ChangeName("trafficInformation")
    private String TrafficInformation;
    /**
     * 标签编号
     */
    @ChangeName("tagsId")
    private String[] labelId;
    /**
     * 标签
     */
    @ChangeName("tags")
    private String[] label;
    /**
     * 均价
     */
    @ChangeName("averagePrice")
    private Integer avgPrice;
    /**
     * 总价
     */
    @ChangeName("totalPrice")
    private Integer sumPrice;
    /**
     * 建成年代
     */
    @ChangeName("buildYears")
    private String abbreviatedAge;
    /**
     * 车位配比
     */
    @ChangeName("parkRatio")
    private String carPositionRatio;
    /**
     * 获取地铁站与小区的距离
     */
    @ChangeName("distanceKey")
    private String key;
}
