package com.toutiao.web.domain.query;

import lombok.Data;

import java.util.Map;

@Data
public class VillageResponse {
    private Integer id; //序号
    private String rc;  //小区名称/楼盘名称
    private String alias;  //别名
    private String[] photo;  //小区照片
    private String areaId;  //区域编号
    private String area;  //区域
    private String tradingAreaId;  //商圈编号
    private String tradingArea;  //商圈
    private String address;  //地址
    private String location; //坐标
    private String[] metroStationId;  //地铁站编号
    private String[] metroStation;  //地铁站
    private Map metroWithPlotsDistance;  //地铁站与小区的距离
    private String[] subwayLineId;  //地铁线路编号
    private String[] subwayLine;  //地铁线路
    private String TrafficInformation;  //交通信息
    private String[] labelId;  //标签编号
    private String[] label;  //标签
    private String[] saleHouse;  //待售房源
    private Integer avgPrice;  //均价
    private Integer sumPrice;  //总价
    private String abbreviatedAge;  //建成年代
    private Integer age;  //楼龄
    private String areaSize;  //占地面积
    private String buildingAreaSize;  //建筑面积
    private String sumBuilding;  //总栋数
    private String sumHousehold;  //总户数
    private Double avgGreening;  //户均绿化率
    private Double liftDoorRadio;  //梯户比
    private String avgElevator;  //户均电梯
    private String elevator;  //电梯配备
    private String carPositionRatio;  //车位配比
    private String parkingRate;  //停车费
    private String desc;  //小区详情介绍
    private String characteristic;  //小区特色
    private String developers;  //开发商
    private String property;  //物业公司
    private String propertyFee;  //物业费
    private String propertyType;  //物业类型
    private String propertyTypeName;  //物业类型名称
    private String yopr;  //产权年限
    private String[] architectureTypeId;  //建筑类型编号
    private String[] architectureType;  //建筑类型
    private String buildingStructure;  //建筑结构
    private String residentialType;  //住宅类型
    private String heatingMode;  //供暖方式
    private String sdr;  //供需关系
    private Integer level;  //楼盘级别
    private String villageCharacteristics;  //楼盘特色
    private String key;  //列表页展示商圈或者与地铁站的距离
    private String cityId;  //城市编号
    private String ringRoad;  //环线
    private Double dimension;  //容积率
    private String airQuality;  //空气质量
    private String electricSupply;  //供电
    private String electricFee;  //电费
    private String waterSupply;  //供水
    private String waterFee;  //水费
    private String decorationType;  //装修标准
    private String ringRoadName;  //详情环线
    private Integer ringRoadDistance;  //详情环线距离
    private Double tongbi;  //同比
    private Double huanbi;  //环比
    private Long total;

//    private Double pm;  //pm2.5
//    private Double noiseindex;  //噪声指数
//    private String[] mainpushhouse;  //主推房源
//    private String recommendedreasons;  //推荐理由
}
