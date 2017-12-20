package com.toutiao.web.dao.entity.admin;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Map;

@Data
public class VillageEntityES {
    @JSONField(name = "newcode")
    private Integer id; //序号
    @JSONField(name = "projname")
    private String rc;  //小区名称/楼盘名称
    @JSONField(name = "nickname")
    private String alias;  //别名
//    @JSONField(name = "")
    private String[] photo;  //小区照片
    @JSONField(name = "districtId")
    private String areaId;  //区域编号
//    @JSONField(name = "")
    private String area;  //区域
    @JSONField(name = "areaId")
    private String tradingAreaId;  //商圈编号
//    @JSONField(name = "")
    private String tradingArea;  //商圈
    @JSONField(name = "address")
    private String address;  //地址
//    @JSONField(name = "")
    private String location; //坐标
    @JSONField(name = "metroStationId")
    private String[] metroStationId;  //地铁站编号
//    @JSONField(name = "")
    private String[] metroStation;  //地铁站
//    @JSONField(name = "")
    private Map<String,String> metroWithPlotsDistance;  //地铁线站与小区的距离
    @JSONField(name = "subwayLineId")
    private String[] subwayLineId;  //地铁线路编号
//    @JSONField(name = "")
    private String[] subwayLine;  //地铁线路
//    @JSONField(name = "")
    private String TrafficInformation;  //交通信息
//    @JSONField(name = "")
    private String[] labelId;  //标签编号
//    @JSONField(name = "")
    private String[] label;  //标签
//    @JSONField(name = "")
    private String[] saleHouse;  //待售房源
    @JSONField(name = "esfPrice")
    private Integer avgPrice;  //均价
    @JSONField(name = "esfTotalPrice")
    private Integer sumPrice;  //总价
    @JSONField(name = "finishdate")
    private String abbreviatedAge;  //建成年代
//    @JSONField(name = "")
    private Integer age;  //楼龄
//    @JSONField(name = "")
    private String areaSize;  //面积
    @JSONField(name = "buildCount")
    private String sumBuilding;  //总栋数
    @JSONField(name = "sumHousehold")
    private String sumHousehold;  //总户数
//    @JSONField(name = "")
    private Double avgGreeningRate;  //户均绿化率
//    @JSONField(name = "")
    private Double avgElevator;  //户均电梯
    @JSONField(name = "hasLift")
    private String elevator;  //电梯配备 (1-有，2-无)
    @JSONField(name = "parkRadio")
    private Double carPositionRatio;  //车位配比
    @JSONField(name = "carRentPrice")
    private Double parkingRate;  //停车费
    @JSONField(name = "projdesc")
    private String desc;  //小区详情介绍
    @JSONField(name = "projdesc")
    private String characteristic;  //小区特色
    @JSONField(name = "developer")
    private String developers;  //开发商
    @JSONField(name = "propertymanage")
    private String property;  //物业公司
    @JSONField(name = "propertyfee")
    private String propertyFee;  //物业费
    @JSONField(name = "propertyType")
    private String propertyType;  //物业类型
    @JSONField(name = "rightYear")
    private String yopr;  //产权年限
    @JSONField(name = "buildForm")
    private String[] architectureTypeId;  //建筑类型编号
//    @JSONField(name = "")
    private String[] architectureType;  //建筑类型
    @JSONField(name = "buildCategory")
    private String buildingStructure;  //建筑结构
    @JSONField(name = "villaStyle")
    private String residentialType;  //住宅类型
    @JSONField(name = "heatingMode")
    private String heatingMode;  //供暖方式(0-未知，1-集中供暖，2-自供暖）
//    @JSONField(name = "")
    private String sdr;  //供需关系
//    @JSONField(name = "")
    private Integer level;  //楼盘级别
//    @JSONField(name = "")
    private Integer version;  //版本
    @JSONField(name = "cityId")
    private String cityId;  //城市编号
    @JSONField(name = "projFeature")
    private String villageCharacteristics;  //楼盘特色
    @JSONField(name = "ringRoad")
    private String ringRoad;  //环线
//    private Double pm;  //pm2.5
//    private Double noiseindex;  //噪声指数
//    private String[] mainpushhouse;  //主推房源
//    private String recommendedreasons;  //推荐理由
}
