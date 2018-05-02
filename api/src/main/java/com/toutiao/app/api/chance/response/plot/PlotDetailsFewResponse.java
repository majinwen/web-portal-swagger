package com.toutiao.app.api.chance.response.plot;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class PlotDetailsFewResponse implements Serializable{
    /**
     * 小区编号
     */
//    @JSONField(name = "buildingId")
    private Integer id;
    /**
     * 小区名称/楼盘名称
     */
//    @JSONField(name = "buildingName")
    private String rc;
    /**
     * 别名
     */
//    @JSONField(name = "houseAlias")
    private String alias;
    /**
     * 小区照片
     */
//    @JSONField(name = "housePhoto")
    private String[] photo;
    /**
     * 区域编号
     */
//    @JSONField(name = "districtId")
    private String areaId;
    /**
     * 区域
     */
//    @JSONField(name = "districtName")
    private String area;
    /**
     * 商圈编号
     */
//    @JSONField(name = "areaId")
    private String tradingAreaId;
    /**
     * 商圈
     */
//    @JSONField(name = "areaName")
    private String tradingArea;
    /**
     * 地址
     */
//    @JSONField(name = "houseAddress")
    private String address;
    /**
     * 坐标
     */
    private String location;
    /**
     * 地铁站编号
     */
//    @JSONField(name = "subwayStationId")
    private String[] metroStationId;
    /**
     * 地铁站
     */
//    @JSONField(name = "subwayStation")
    private String[] metroStation;
    /**
     * 地铁站与小区的距离
     */
//    @JSONField(name = "subwayWithHouseDistance")
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
//    @JSONField(name = "trafficInformation")
    private String TrafficInformation;
    /**
     * 标签编号
     */
//    @JSONField(name = "tagsId")
    private String[] labelId;
    /**
     * 标签
     */
//    @JSONField(name = "tags")
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
    /**
     * 获取地铁站与小区的距离
     */
    private String key;
}
