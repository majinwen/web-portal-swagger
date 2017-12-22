package com.toutiao.web.domain.query;

import lombok.Data;
//小区
//(查找范围的数组字段，按照顺序赋值就可以)
@Data
public class VillageRequest {
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
    private String searchMetroStationId;  //搜索地铁站编号
    private String[] subwayLineId;  //地铁线路编号
    private String[] subwayLine;  //地铁线路
    private String searchSubwayLineId;  //搜索地铁线编号
    private String TrafficInformation;  //交通信息
    private String[] labelId;  //标签编号
    private String[] label;  //标签
    private String[] saleHouse;  //待售房源
    private Integer avgPrice;  //均价
    private Integer[] searchAvgPrice;  //查询价格范围
    private String abbreviatedAge;  //建成年代
    private Integer age;  //楼龄
    private Integer[] searchAge;  //查询楼龄范围
    private String areaSize;  //面积
    private String[] searchAreaSize;  //查询面积大小
    private String sumBuilding;  //总栋数
    private String sumHousehold;  //总户数
    private Double avgGreeningRate;  //户均绿化率
    private Double avgElevator;  //户均电梯
    private String elevator;  //电梯配备
    private String[] searchElevator;  //查询电梯有无
    private Double carPositionRatio;  //车位配比
    private Double parkingRate;  //停车费
    private String desc;  //小区详情介绍
    private String characteristic;  //小区特色
    private String developers;  //开发商
    private String property;  //物业公司
    private String propertyFee;  //物业费
    private String propertyType;  //物业类型
    private String[] searchPropertyType;  //物业类型查询
    private String yopr;  //产权年限
    private String[] architectureTypeId;  //建筑类型编号
    private String[] architectureType;  //建筑类型
    private String buildingStructure;  //建筑结构
    private String residentialType;  //住宅类型
    private String heatingMode;  //供暖方式
    private String[] searchHeatingMode;  //查询供暖方式
    private String sdr;  //供需关系
    private Integer level;  //楼盘级别
    private Integer page;  //起始条数
    private Integer size;  //每页显示条数
    private String villageCharacteristics;  //楼盘特色
    private String[] searchVillageCharacteristics;  //楼盘特色查询
    private String cityId;  //城市编号
    private String ringRoad;  //环线
//    private Double pm;  //pm2.5
//    private Double noiseIndex;  //噪声指数
//    private String[] mainPushHouse;  //主推房源
//    private String recommendedReasons;  //推荐理由
}
