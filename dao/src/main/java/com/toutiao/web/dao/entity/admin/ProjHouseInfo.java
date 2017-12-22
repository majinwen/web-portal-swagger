package com.toutiao.web.dao.entity.admin;

import lombok.Data;

import java.util.Map;

@Data
public class ProjHouseInfo {
   /* *//**
     * 页面展示字段
     *//*
    // 房源id
    private Integer houseId;

    // 房源名称
    private String houseTitle;

    // 房源面积
    private String houseArea;

    // 房源户型
    private String houseType;

    // 房源户型id
    private String houseTypeId;

    // 房源朝向
    private String houseOrientation;

    // 房源朝向id
    private String houseOrientationId;

    // 房源总价
    private String houseTotalPrices;

    // 房源单价(52365元/㎡)
    private String houseUnitCost;

    // 房源标签(满二)
    private String[] houseLabel;

    // 房源标签(满二)
    private String[] houseLabelId;

    // 房源楼层
    private String houseFloorId;
    // 房源楼层
    private String houseFloor;

    // 房源楼龄
    private String houseYear;

    // 房源用途id
    private String housePurposeId;

    // 房源用途
    private String housePurpose;

    // 房源电梯id
    private String houseLiftId;
    // 房源电梯
    private String houseLift;

    // 房源供暖id
    private String houseHeatingId;
    // 房源供暖
    private String houseHeating;

    // 房源权属id
    private String houseOwnershipId;
    // 房源权属
    private String houseOwnership;

    // 房源推荐描述
    private String houseRecommendInfo;

    // 房源照片
    private String[] housePhoto;

    // 房源预算
    private String houseBudget;

    // 房源交通信息
    private String houseTrafficInfo;

    // 房源更新时间
    private String houseUpdateTime;

    // 物业类型id
    private String houseManagementTypeId;
    // 物业类型
    private String houseManagementType;

    // 建筑类别id
    private String houseBuildingTypeId;
    // 建筑类别
    private String houseBuildingType;

    // 房源级别(排序)
    private String houseRank;
    *//**
     * 房源描述
     *//*
    // 经纪人头像
    private String houseProxyPhoto;
    // 经纪人姓名
    private String houseProxyName;
    // 经纪人电话号
    private String houseProxyPhone;
    // 房源介绍
    private String houseRecommend;
    *//**
     * 小区信息 通过小区id或者名称获取 页面要显示小区名称 并且查询时候要讲小区id传递到后台
     *//*
    *//**
     * 配套地图 通过小区地理坐标显示在百度地图上
     *//*
    // 房源小区id
    private Integer housePlotId;

    // 房源小区地理坐标
    private String housePlotLocation;

    // 房源小区名称
    private String housePlotName;

    //房源小区照片
    private String[] housePlotPhoto;

    // 房源小区信息
    private String housePlotInfo;

    *//**
     * 附近好房 通过 获取当前房源所属小区内的房源信息
     *//*

    *//**
     * 附近小区 通过小区地理坐标查询附近小区 （算距离）
     *//*
    *//**
     * 点击地理标签需要存储的字段
     *//*
    // 商圈名称
    private String houseBusinessNameId;

    // 商圈名称
    private String houseBusinessName;

    // 区域id
    private String areaId;

    // 区域名称(朝阳)
    private String areaName;

    // 地铁线id
    private String[] subwayLineId;

    // 地铁线名称
    private String[] subwayLineName;

    // 地铁站id
    private String[] subwayStationId;

    // 地铁站名称
    private String[] subwayStationName;

    //距离您多少公里
    private Map<String, String> houseToSubwayDistance;
    
    private String key;
    //版本控制
    private int version;*/

    /**
     * 房源ID
     */
    private Integer houseId;

    /**
     * 房源标题
     */
    private String houseTitle;


    /**
     * 建筑面积，在使用面积的基础上加上了墙体所占用的面积(房源面积)
     */
    private Double buildArea;

    /**
     * 使用面积，户主真正使用的面积，住宅买卖中一般不采用使用面积来计算价格
     */
    private Double liveArea;

    /**
     * 装修(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他)
     */
    private Integer fitment;

    /**
     * 室 数字
     */
    private Integer room;


    /**
     * 厅
     */
    private Integer hall;

    /**
     * 厨
     */
    private Integer kitchen;

    /**
     * 卫
     */
    private Integer toilet;

    /**
     * 阳台
     */
    private Integer balcony;

    /**
     * 朝向(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    private Integer forward;


    // 房源总价
    private Double houseTotalPrices;

    // 房源单价(52365元/㎡)
    private Double houseUnitCost;

    /**
     * 价格单位
     */
    private String priceUnit;

    /**
     * 标签(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    private Integer[] tags;

    /**
     * 是否推荐房源，0-否，1-是
     */
    private Integer isRecommend;

    /**
     * 发布公司
     */
    private String ofCompany;


    /**
     * 房源所在层（高层/低层/中层）
     */
    private String floor;

    /**
     * 总的楼层数
     */
    private Integer totalFloor;

    /**
     * 房源所在层数(地下室填负数)
     */
    private Integer floorNo;


    /**
     * 建成年份
     */
    private String year;


    /**
     * 产权性质(1:已购公房,2:商品房,3:空置房,4:使用权房,5:央产,6:经济适用房)（权属）
     */
    private Integer propertyRight;

    /**
     * 房屋类型：普通住宅、经济适用房、公寓、安置房、四合院等（物业类型）
     *（ 1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼
     */
    private Integer houseType;

    /**
     * 房源信息来源（0-未知来源，1-编辑录入，2-我爱我家导入，3-中原地产导入）
     */
    private Integer source;

    /**
     * 推荐理由
     */
    private String recommendReason;


    // 房源照片
    private String[] housePhoto;


    //房源首付 （后台提供）
    private Double HousingDeposit;

    //房源月供
    private Double houseMonthPayment;

    /**
     * 交通状况
     */
    private String traffic;


    /**
     * 是否删除(0-未删除/1-已删除)
     */
    private Short isDel;


    /**
     * 更新时间
     */
    private Data updateTime;


    /**
     * 建筑类别：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    private String buildCategory;


    /**
     * 建筑形式1：低层，2：多层，3：小高层，4：高层，5：超高层
     */
    private String buildForm;


    /**
     * 住宅-房屋结构（平层、错层、跃层、复式、开间） 别墅-厅结构（平层、挑高）
     */
    private String houseStructure;


    /**
     * 状态(0-未发布/1-已发布)
     */
    private Short status;

    /**
     * 房源分级，0-默认，1-最好，2-次之，3-再次
     */
    private Integer houseLevel;


    /**
     * 房源描述
     */
    private String houseDesc;

    // 经纪人头像
    private String houseProxyPhoto;
    // 经纪人姓名
    private String houseProxyName;
    // 经纪人电话号
    private String houseProxyPhone;

    /**
     * 楼盘ID(楼盘/小区)
     */
    private Integer newcode;
    //小区名称
    private String plotName;


    // 商圈id
    private Integer houseBusinessNameId;

    // 区域id
    private Integer areaId;


    // 商圈id
    private String houseBusinessName;

    // 区域id
    private String area;


    private String subwayDistince;


    //版本控制
    private Integer version;

}