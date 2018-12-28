package com.toutiao.app.domain.plot;


import com.alibaba.fastjson.annotation.JSONField;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;


import java.util.List;
import java.util.Map;

@Data
public class PlotDetailsDo {
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
    @ChangeName("buildingNickName")
    private String alias;
    /**
     * 小区照片
     */
    @ChangeName("buildingImages")
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
    private String address;
    /**
     * 坐标
     */
    private String location;
    /**
     * 地铁站编号
     */
    @ChangeName("subwayStationId")
    private  List<String> metroStationId;
    /**
     * 地铁站
     */
    @ChangeName("subwayStationName")
    private List<String> metroStation;
    /**
     * 地铁站与小区的距离
     */
    @ChangeName("nearbyStationsDistance")
    private Map metroWithPlotsDistance;
    /**
     * 地铁线路编号
     */
    @ChangeName("subwayLineId")
    private List<String> subwayLineId;
    /**
     * 地铁线路
     */
    @ChangeName("subwayLineName")
    private List<String> subwayLine;
    /**
     * 交通信息
     */
    private String TrafficInformation;
    /**
     * 标签编号
     */
    @ChangeName("tagsId")
    private List<String> labelId;
    /**
     * 标签
     */
    @ChangeName("tags")
    private List<String> label;
    /**
     * 待售房源
     */
    private List<String> saleHouse;
    /**
     * 均价
     */
    @ChangeName("averagePrice")
    private Double avgPrice;
    /**
     * 总价
     */
    @ChangeName("totalPrice")
    private Double sumPrice;
    /**
     * 建成年代
     */
    @ChangeName("buildYears")
    private String abbreviatedAge;
    /**
     * 楼龄
     */
    private Integer age;
    /**
     * 占地面积
     */
    private Double areaSize;
    /**
     * 建筑面积
     */
    private Double buildingAreaSize;
    /**
     * 总栋数
     */
    private String sumBuilding;
    /**
     * 总户数
     */
    private String sumHousehold;
    /**
     * 户均绿化率
     */
    private Double avgGreening;
    /**
     * 梯户比
     */
    private Double liftDoorRadio;
    /**
     * 户均电梯
     */
    private String avgElevator;
    /**
     * 电梯配备
     */
    private String elevator;
    /**
     * 车位配比
     */
    @ChangeName("parkRatio")
    private String carPositionRatio;
    /**
     * 停车费
     */
    private String parkingRate;
    /**
     * 小区详情介绍
     */
    private String desc;
    /**
     * 小区特色
     */
    private String characteristic;
    /**
     * 开发商
     */
    private String developers;
    /**
     * 物业公司
     */
    private String property;
    /**
     * 物业费
     */
    private String propertyFee;
    /**
     * 物业类型
     */
    @ChangeName("propertyTypeId")
    private String propertyType;
    /**
     * 物业类型名称
     */
    @ChangeName("propertyType")
    private String propertyTypeName;
    /**
     * 产权年限
     */
    private String yopr;
    /**
     * 建筑类型编号
     */
    private List<String> architectureTypeId;
    /**
     * 建筑类型
     */
    private List<String> architectureType;
    /**
     * 建筑结构
     */
    private String buildingStructure;
    /**
     * 住宅类型
     */
    private String residentialType;
    /**
     * 供暖方式
     */
    private String heatingMode;
    /**
     * 供需关系
     */
    private String sdr;
    /**
     * 楼盘级别
     */
    private Integer level;
    /**
     * 楼盘特色
     */
    private String villageCharacteristics;
    /**
     * 列表页展示商圈或者与地铁站的距离
     */
    private String key;
    /**
     * 城市编号
     */
    private String cityId;
    /**
     * 环线
     */
    private String ringRoad;
    /**
     * 容积率
     */
    private Double dimension;
    /**
     * 空气质量
     */
    private String airQuality;
    /**
     * 供电
     */
    private String electricSupply;
    /**
     * 电费
     */
    private Double electricFee;
    /**
     * 供水
     */
    private String waterSupply;
    /**
     * 水费
     */
    private Double waterFee;
    /**
     * 装修标准
     */
    private String decorationType;
    /**
     * 详情环线
     */
    private String ringRoadName;
    /**
     * 详情环线距离
     */
    private Integer ringRoadDistance;
    /**
     * 同比
     */
    private Double tongbi;
    /**
     * 环比
     */
    private Double huanbi;
    /**
     * 总数
     */
    private Long total;
    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 电梯配备中文
     */
   private String hasElevator;
    /**
     * 是否收藏
     */
    private Boolean isFavorite = false;

    /**
     * 二手房，租房信息
     */
    private PlotsHousesDomain plotsHousesDomain;

    /**
     * 标签名称
     */
    private List<String> recommendBuildTagsName;
    /**
     * 标签id
     */
    private List<Integer> recommendBuildTagsId;

    /**
     * 小区行情
     */
    private PlotMarketDomain plotMarketDomain;

    /**
     * 小区别名
     */
    @JSONField(name = "rc_nickname")
    private String rcNickname;
    /**
     * 上架房源
     */
    private Integer increaseHouse;
    /**
     * 下架房源
     */
    private Integer decreaseHouse;

    /**
     * 标题图
     */
    private String titlePhoto;

    /**
     * 小区均价浮动比率
     */
    private Double averagePriceFloatRatio;

    /**
     * 上月成交二手房数量
     */
    private Integer lastMonthsDeal;

    /**
     * 区域小区热度榜单排名
     */
    private Integer districtHotSort;
}
