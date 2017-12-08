package com.toutiao.web.dao.entity.admin;

import lombok.Data;

@Data
public class village {
    private Integer id; //序号
    private String rc;  //小区名称/楼盘名称
    private String alias;  //别名
    private String[] photo;  //小区照片
    private String areaId;  //区域编号
    private String area;  //区域
    private String tradingareaId;  //商圈编号
    private String tradingarea;  //商圈
    private String address;  //地址
    private Double[] location; //坐标
    private String[] metroStationId;  //地铁站编号
    private String[] metroStation;  //地铁站
    private String[] subwaylineId;  //地铁线路编号
    private String[] subwayline;  //地铁线路
    private String TrafficInformation;  //交通信息
    private String[] labelId;  //标签编号
    private String[] label;  //标签
    private String[] salehouse;  //待售房源
    private String avgprice;  //均价
    private String abbreviatedage;  //建成年代
    private String age;  //楼龄
    private String areaSize;  //面积
    private String sumbuilding;  //总栋数
    private String sumhousehold;  //总户数
    private Double avggreeningrate;  //户均绿化率
    private Double avgelevator;  //户均电梯
    private String elevator;  //电梯配备
    private Double carpositionratio;  //车位配比
    private Double parkingrate;  //停车费
    private String desc;  //小区详情介绍
    private String characteristic;  //小区特色
    private String developers;  //开发商
    private String property;  //物业公司
    private String propertyfee;  //物业费
    private String propertytype;  //物业类型
    private String yopr;  //产权年限
    private String[] architecturaltypeId;  //建筑类型编号
    private String[] architecturaltype;  //建筑类型
    private String buildingstructure;  //建筑结构
    private String too;  //业态
    private String residentialtype;  //住宅类型
    private String heatingmode;  //供暖方式
    private String sdr;  //供需关系
//    private Double pm;  //pm2.5
//    private Double noiseindex;  //噪声指数
//    private String[] mainpushhouse;  //主推房源
//    private String recommendedreasons;  //推荐理由
}
