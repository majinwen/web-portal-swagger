package com.toutiao.app.api.chance.response.newhouse;

import com.toutiao.app.domain.newhouse.ActivityInfoDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NewHouseDetailResponse {

    @ApiModelProperty(value = "销售信息")
    private  String saleStatusName;

    @ApiModelProperty(value = "销售许可")
    private Map<String,String> sellLicence;

    @ChangeName("ringRoad")
    @ApiModelProperty(value = "环路桥")
    private  String ringRoadName;

    @ChangeName("nearBySubwayDesc")
    @ApiModelProperty(value = "最近的地铁")
    private Map<String,String> nearbysubway;

    @ApiModelProperty(value = "房屋的最大面积")
    private  Double houseMaxArea;

    @ApiModelProperty(value = "空气质量")
    private  String airQuality;

    @ApiModelProperty(value = "区域id")
    private  Integer districtId;

    @ApiModelProperty(value = "物业类别/业态：（1-住宅,2-别墅,3-写字楼，4-商铺，5-底商，6-住宅底商，7=办公别墅，8-标准写字楼，" +
            "9-酒店写字楼，10-写字楼商铺，11-市场类商铺，12-商务型公寓，13-购物中心，14-企业独栋，15-商业，16-总部园区）")
    private  String propertyType;

    @ChangeName("buildingImages")
    @ApiModelProperty(value = "大楼图片")
    private String[] buildingImg;

    @ChangeName("buildingArea")
    @ApiModelProperty(value = "建筑面积(平方米)")
    private  Double purposeArea;

    @ApiModelProperty(value = "楼盘名称")
    private  String buildingName;

    @ChangeName("propertyCompany")
    @ApiModelProperty(value = "物业管理公司")
    private  String propertymanage;

    @ChangeName("salePhone")
    @ApiModelProperty(value = "售楼热线")
    private String saletelphone;

    @ApiModelProperty(value = "售楼地址")
    private  String saleAddress;

    @ChangeName("parkSpaceNum")
    @ApiModelProperty(value = "停车位数量")
    private  Integer parkSpace;

    @ApiModelProperty(value = "供水")
    private String waterSupply;

    @ApiModelProperty(value = "最小面积")
    private  Double houseMinArea;

    @ApiModelProperty(value = "坐标")
    private String location;

    @ApiModelProperty(value = "供电")
    private  String electricSupply;

    @ApiModelProperty(value = "区域名称")
    private  String districtName;

    @ApiModelProperty(value = "开发商")
    private  String  developers;

    @ChangeName("tags")
    @ApiModelProperty(value = "楼盘标签(1-近地铁，3-现房，4-精装修，5-花园洋房，6-复式，7-车位充足，8-低密度，9-500强房企，10-优质物业，" +
            "11-购物方便，12-教育配套，13-医疗配套，14-换手率高，15-租金月供比高，16-五证齐全，17-人车分流)")
    private  String [] buildingTags;

    @ApiModelProperty(value = "楼栋数")
    @ChangeName("totalBuilding")
    private  Integer buildCount;

    @ChangeName("buildingId")
    @ApiModelProperty(value = "楼盘newcode")
    private Integer  buildingNameId;

    @ChangeName("totalHousehold")
    @ApiModelProperty(value = "总户数")
    private  Integer totaldoor;

    @ApiModelProperty(value = "入住时间/交房时间")
    private String deliverTime;

    @ChangeName("parkRatio")
    @ApiModelProperty(value = "车位配比")
    private  String parkRadio;

    @ApiModelProperty(value = "距离最近的环线(米)")
    private  Integer ringRoadDistance;

    @ChangeName("districtName")
    @ApiModelProperty(value = "区域名称")
    private  String areaName;

    @ApiModelProperty(value = "地铁站")
    private  Integer[] subwayStationId;

    @ChangeName("buildingNickName")
    @ApiModelProperty(value = "新房别名")
    private  String buildingNickname;

    @ChangeName("houseAddress")
    @ApiModelProperty(value = "新盘地址")
    private  String buildingAddress;

    @ApiModelProperty(value = "活动信息")
    private  String activityDesc;

    @ApiModelProperty(value = "新房标题图")
    private  String buildingTitleImg;

    @ApiModelProperty(value = "总价")
    private  Double totalPrice;

    @ApiModelProperty(value = "审核状态")
    private  Integer isApprove;

    @ChangeName("heatingMode")
    @ApiModelProperty(value = "供暖方式(0-未知，1-集中供暖，2-自供暖）")
    private  String heatingType;

    @ChangeName("propertyFee")
    @ApiModelProperty(value = "物业管理费")
    private  Double propertyfee;

    @ApiModelProperty(value = "开盘时间")
    private  String openedTime;

    @ApiModelProperty(value = "销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)")
    private  Integer saleStatusId;

    @ChangeName("fitmentId")
    @ApiModelProperty(value = "装修(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他 6:非毛坯 7:公共部分简单装修)")
    private  Integer [] redecorateTypeId;

    @ChangeName("yopr")
    @ApiModelProperty(value = "产权年限")
    private  Integer  buildingLife;

    @ApiModelProperty(value = "建筑类别")
    private String  buildingType;

    @ChangeName("coversArea")
    @ApiModelProperty(value = "占地面积(平方米)")
    private  Double groundArea;

    @ApiModelProperty(value = "平均单价-新房")
    private  Double averagePrice;

    @ApiModelProperty(value = "楼盘评分")
    private  Integer buildingSort;

    @ChangeName("parkingRentFee")
    @ApiModelProperty(value = "停车位租价")
    private  Double carRentPrice;

    @ApiModelProperty(value = "梯户比")
    private  String liftDoorRadio;

    @ChangeName("feature")
    @ApiModelProperty(value = "项目特色")
    private  String buildingFeature;

    @ApiModelProperty(value = "容积率(%)")
    private  Double dimension;

    @ChangeName("avgGreening")
    @ApiModelProperty(value = "绿化率")
    private Double virescencerate;

    @ApiModelProperty(value = "是否参与活动(0:未参与,1:参与)")
    private Integer isActive;

    @ApiModelProperty(value = "优惠活动")
    private List<ActivityInfoDo> activityInfo;

    @ApiModelProperty(value = "户型")
    private String roomType;

    @ApiModelProperty(value = "是否收藏")
    private Boolean isFavorite;
}
