package com.toutiao.app.api.chance.response.rent;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
@Data
public class RentDetailResponse {
    /**
     * 经纪人头像路径
     */
    private String agentHeadPhoto;
    /**
     * 小区Id
     */
    private Integer zufangId;
    /**
     * 小区名称
     */
    private String zufangName;
    /**
     * 小区分数
     */
    private Integer zufangScore;
    /**
     * 城市编号
     */
    private Integer cityId;
    /**
     * 区域编号
     */
    private Integer districtId;
    /**
     * 区域名称
     */
    private String districtName;
    /**
     * 商圈编号
     */
    private Integer areaId;
    /**
     * 商圈名称
     */
    private String areaName;
    /**
     * 出租房源Id
     */
    private String houseId;
    /**
     * 租赁方式(1-整租，2-合租)
     */
    private Integer rentType;
    /**
     * 租赁方式名称
     */
    private String rentTypeName;
    /**
     * 出租房源标志(普租/公寓)
     */
    private Integer rentSign;
    /**
     * 出租房源标志名称(出租/公寓)
     */
    private String rentSignName;
    /**
     * 楼盘ID(楼盘/小区)
     */
    private Integer villageId;
    /**
     * 需求
     */
    private String demand;
    /**
     * 坐标(lat,lon)
     */
    private String location;
    /**
     * 房源面积
     */
    private Double house_area;
    /**
     * 朝向ID（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private String forward;
    /**
     * 朝向（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private Integer forwardType;
    /**
     * 几室
     */
    private String room;
    /**
     * 几厅
     */
    private Integer hall;
    /**
     * 供热方式
     */
    private Integer heatingType;
    /**
     * 几卫
     */
    private Integer toilet;
    /**
     * 几厨
     */
    private Integer kitchen;
    /**
     * 阳台
     */
    private Integer balcony;
    /**
     * 房源所在楼层(地下室为负数)
     */
    private Integer floor;
    /**
     * 总楼层数
     */
    private Integer totalFloor;
    /**
     * 楼盘3km地铁线ID
     */
    private Integer[] subwayLineId;
    /**
     * 楼盘3km地铁站ID
     */
    private Integer[] subwayStationId;
    /**
     * 附近地铁站
     */
    private HashMap nearbySubway;
    /**
     * 最近的地铁
     */
    private String nearestSubway;
    /**
     * 出租房源标签(1-采光好，2-地铁近，3-首次出租，4-独立阳台，5-独立卫生间，6-集中供暖，7-可注册办公)
     */
    private Integer[] rentHouseTagsId;
    /**
     * 出租房源标签名称
     */
    private String[] rentHouseTagsName;
    /**
     * 租金(元/月)
     */
    private Double rentHousePrice;
    /**
     * 房源标题图片
     */
    private String houseTitleImg;
    /**
     * 房源图片
     */
    private List rentHouseImg;
    /**
     * 付款方式(1-面议，2-押一付一，3-押一付二，4-押一付三，5-押二付一，6-押二付二，7-押二付三，8-半年付，9-年付)
     */
    private Integer payMode;
    /**
     * 付款方式名称
     */
    private String payModeName;
    /**
     * 配套设施(字符串数组)
     */
    private String[] supportingFacilities;
    /**
     * 经纪机构
     */
    private String brokerageAgency;
    /**
     * 电话
     */
    private String phone;
    /**
     * 房源描述
     */
    private String houseDesc;
    /**
     * 房源来源
     */
    private String dataSourceName;
    /**
     * 房源来源标志
     */
    private Integer dataSourceSign;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String upStringTime;
    /**
     * 发版时间
     */
    private String publishTime;
    /**
     * 发布状态
     */
    private Integer releaseStatus;
    /**
     * 是否上架
     */
    private Integer isDel;
    /**
     * 是否是推荐
     */
    private Integer isRecommend;
    /**
     * 是否是置顶
     */
    private Integer isTop;
    /**
     * 版本
     */
    private Integer version;
    /**
     * 置顶关键词
     */
    private String topKeyword;
    /**
     * 置顶时间
     */
    private String topTime;
    /**
     * 经纪人
     */
    private String estateAgent;
    /**
     * 整租户型
     */
    private Integer eRentLayout;
    /**
     * 合租户型
     */
    private Integer jRentLayout;



    /**
     * 类型
     */
    private  Integer rentHouseType;

    /**
     * 导入房源标题
     */
    private  String importHouseTitle;

    /**
     * 录入的房源标题
     */
    private  String entryHouseTitle;

    /**
     * 电梯情况
     */
    private  String hasLift;

    /**
     * 楼层级别
     */
    private  String  floorLevel;



}
