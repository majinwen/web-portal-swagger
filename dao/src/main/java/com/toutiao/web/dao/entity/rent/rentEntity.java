package com.toutiao.web.dao.entity.rent;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class rentEntity {
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
    private Integer houseId;
    /**
     * 租赁方式(1-整租，2-合租)
     */
    private Integer rentType;
    /**
     * 租赁方式名称
     */
    private String rentTypeName;
    /**
     * 出租房源标志
     */
    private Integer[] rentSign;
    /**
     * 出租房源标志名称
     */
    private String[] rentSignName;
    /**
     * 楼盘ID(楼盘/小区)
     */
    private Integer villageId;
    /**
     * 楼盘名称(楼盘/小区)
     */
    private String villageName;
    /**
     * 坐标(lat,lon)
     */
    private String location;
    /**
     * 房源面积
     */
    private Double houseArea;
    /**
     * 朝向ID（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private Integer forward;
    /**
     * 朝向（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
     */
    private String forwardType;
    /**
     * 几室
     */
    private Integer room;
    /**
     * 几厅
     */
    private Integer hall;
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
    private String[] nearBySubway;
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
    private Map rentHouseImg;
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
     * 房源经纪人
     */
    private String estateAgent;
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
     * 是否有电梯
     */
    private Integer hasElevator;
    /**
     * 房源来源
     */
    private String dataSource;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 发布状态
     */
    private Integer releaseStatus;
    /**
     * 是否上架
     */
    private Integer isDel;
    /**
     * 版本
     */
    private Integer version;

}
