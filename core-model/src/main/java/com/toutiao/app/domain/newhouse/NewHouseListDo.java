package com.toutiao.app.domain.newhouse;

import com.toutiao.app.domain.sellhouse.HouseLable;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NewHouseListDo  {

    @ApiModelProperty(value = "最小面积")
    private Double houseMinArea;

    @ApiModelProperty(value = "最大面积")
    private  Double houseMaxArea;

    @ApiModelProperty(value = "楼盘名称")
    private String buildingName;

    @ApiModelProperty(value = "楼盘id")
    private  Integer buildingNameId;

    @ApiModelProperty(value = "区域名称")
    private  String  districtName;

    @ApiModelProperty(value = "区域id")
    private Integer districtId;

    @ApiModelProperty(value = "地铁信息")
    private  String roundStation;

    @ApiModelProperty(value = "最近交房")
    private  String deliverTime;

    @ApiModelProperty(value = "车位配比")
    private String parkRadio;

    @ApiModelProperty(value = "均价")
    private Double averagePrice;

    @ApiModelProperty(value = "销售状态")
    private  String  saleStatusName;

    @ApiModelProperty(value = "楼盘标题图")
    private  String buildingTitleImg;

    @ApiModelProperty(value = "户型总数")
    private  long roomTotalCount;

    @ApiModelProperty(value = "户型类型")
    private String roomType;

    @ApiModelProperty(value = "新房收藏数量")
    private Integer newHouseFavorite;

    @ApiModelProperty(value = "总价")
    private Double  totalPrice;

    @ApiModelProperty(value = "类别")
    private  String propertyType;

    @ApiModelProperty(value = "桥")
    private  String  ringRoadName;

    @ApiModelProperty(value = "附近地铁")
    private Map nearbysubway;

    @ApiModelProperty(value = "房源与地铁站的距离")
    private String subwayDistanceInfo;

    @ApiModelProperty(value = "楼盘标签")
    private  String [] buildingTags;

    @ApiModelProperty(value = "小区所属环线")
    private  Integer  ringRoad;

    @ApiModelProperty(value = "推荐理由")
    private String buildingFeature;

    @ApiModelProperty(value = "建筑类别")
    private String  buildingType;

    @ApiModelProperty(value = "户型最低价格")
    private Double houseMinPrice;

    @ApiModelProperty(value = "户型最大价格")
    private Double houseMaxPrice;

    @ApiModelProperty(value = "开发商")
    private  String  developers;

    @ApiModelProperty(value = "物业管理公司")
    private  String propertymanage;

    @ApiModelProperty(value = "开盘时间")
    private  String openedTime;

    @ApiModelProperty(value = "开盘时间描述")
    private String openedTimeDesc;

    @ApiModelProperty(value = "交付时间描述")
    private String deliverTimeDesc;

    @ApiModelProperty(value = "楼盘动态")
    private List<NewHouseDynamicDo> newHouseDynamic;

    @ApiModelProperty(value = "地址")
    private  String buildingAddress;

    @ApiModelProperty(value = "新房动态数量")
    private Long dynamicTotal;

    @ApiModelProperty(value = "安全电话")

    private String saletelphone;
    @ApiModelProperty(value = "是否参与活动(0:未参与,1:参与)")
    private Integer isActive;

    @ApiModelProperty(value = "优惠活动")
    private List<ActivityInfoDo> activityInfo;

    @ApiModelProperty(value = "标签列表")
    private List<HouseLable> houseLabelList;

    @ApiModelProperty(value = "描述（高位）")
    private  String descHigh;

    @ApiModelProperty(value = "描述（中位）")
    private  String descMid;


}
