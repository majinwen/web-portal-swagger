package com.toutiao.appV2.model.sellhouse;


import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.sellhouse.HouseColorLable;
import com.toutiao.app.domain.sellhouse.HouseSubject;
import com.toutiao.appV2.model.plot.PlotDetailsResponse;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "SellHouseDetailsResponse", description = "SellHouseDetailsResponse")
public class SellHouseDetailsResponse {

    /**
     * 二手房房源id
     */
    @ApiModelProperty(value = "二手房房源id", name = "houseId")
    private String houseId;
    /**
     * 房源标题
     */
    @ApiModelProperty(value = "房源标题", name = "houseTitle")
    private String houseTitle;
    /**
     * 室
     */
    @ApiModelProperty(value = "室", name = "room")
    private Integer room;
    /**
     * 厅
     */
    @ApiModelProperty(value = "厅", name = "hall")
    private Integer hall;
    /**
     * 卫
     */
    @ApiModelProperty(value = "卫", name = "toilet")
    private Integer toilet;

    /**
     * 阳台
     */
    @ApiModelProperty(value = "阳台", name = "balcony")
    private Integer balcony;
    /**
     * 厨
     */
    @ApiModelProperty(value = "厨", name = "kitchen")
    private Integer kitchen;
    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称", name = "area")
    //@ChangeName("districtName")
    private String area;
    /**
     * 区域id
     */
    @ApiModelProperty(value = "区域id", name = "areaId")
    //@ChangeName("districtId")
    private Integer areaId;
    /**
     * 商圈id
     */
    @ApiModelProperty(value = "商圈id", name = "houseBusinessNameId")
    //@ChangeName("areaId")
    private Integer houseBusinessNameId;
    /**
     * 商圈名称
     */
    @ApiModelProperty(value = "商圈名称", name = "houseBusinessName")
    //@ChangeName("areaName")
    private String houseBusinessName;
    /**
     * 楼盘ID(楼盘/小区)
     */
    @ApiModelProperty(value = "楼盘ID(楼盘/小区)", name = "newcode")
    //@ChangeName("buildingId")
    private Integer newcode;
    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称", name = "plotName")
    //@ChangeName("buildingName")
    private String plotName;

    @ApiModelProperty(value = "小区名称", name = "plotNameAccurate")
    //@ChangeName("buildingName")
    private String plotNameAccurate;
    /**
     * 小区图片
     */
//    @ApiModelProperty(value = "小区图片", name = "plotPhoto")
//    //@ChangeName("buildingImages")
//    private String plotPhoto;
    /**
     * 地铁站id
     */
    @ApiModelProperty(value = "地铁站id", name = "subwayStationId")
    private String[] subwayStationId;
    /**
     * 地铁线id
     */
    @ApiModelProperty(value = "地铁线id", name = "subwayLineId")
    private Integer[] subwayLineId;
    /**
     * 地铁线/站 距离
     */
    @ApiModelProperty(value = "地铁线/站 距离", name = "subwayDistince")
    private Map<String, String> subwayDistince;
    /**
     * 是否推荐
     */
    @ApiModelProperty(value = "是否推荐", name = "isRecommend")
    private Integer isRecommend;
    /**
     * 建成年代
     */
    @ApiModelProperty(value = "建成年代", name = "year")
    //@ChangeName("buildYears")
    private String year;
    /**
     * 房源信息来源（0-未知来源，1-编辑录入，2-我爱我家导入，3-中原地产导入）
     */
    @ApiModelProperty(value = "房源信息来源（0-未知来源，1-编辑录入，2-我爱我家导入，3-中原地产导入）", name = "source")
    //@ChangeName("houseSourceId")
    private Integer source;
    /**
     * 产权性质(1:已购公房,2:商品房,3:空置房,4:使用权房,5:央产,6:经济适用房)（权属）
     */
    @ApiModelProperty(value = "产权性质(1:已购公房,2:商品房,3:空置房,4:使用权房,5:央产,6:经济适用房)（权属）", name = "propertyRight")
    private Integer propertyRight;
    /**
     * 产权性质名称(1:已购公房,2:商品房,3:空置房,4:使用权房,5:央产,6:经济适用房)（权属）
     */
    @ApiModelProperty(value = "产权性质名称(1:已购公房,2:商品房,3:空置房,4:使用权房,5:央产,6:经济适用房)（权属）", name = "propertyRightName")
    private Integer propertyRightName;

    /**
     * 建筑形式1：低层，2：多层，3：小高层，4：高层，5：超高层
     */
    @ApiModelProperty(value = "建筑形式1：低层，2：多层，3：小高层，4：高层，5：超高层", name = "buildFormName")
    private String buildFormName;
    /**
     * 电梯(有 无)
     */
    @ApiModelProperty(value = "电梯(有 无)", name = "elevator")
    //@ChangeName("hasElevatorId")
    private Integer elevator;
    /**
     * 电梯
     */
    @ApiModelProperty(value = "电梯", name = "elevatorName")
    //@ChangeName("hasElevator")
    private String elevatorName;
    /**
     *供暖
     */
    @ApiModelProperty(value = "供暖", name = "houseHeating")
    //@ChangeName("heatingModeId")
    private Integer houseHeating;
    /**
     *供暖
     */
    @ApiModelProperty(value = "供暖", name = "heatingMode")
    //@ChangeName("heatingMode")
    private String houseHeatingName;
    /**
     * 交通状况（最近地铁信息）
     */
    @ApiModelProperty(value = "交通状况（最近地铁信息）", name = "traffic")
    //@ChangeName("nearBySubwayDesc")
    private String traffic;
    /**
     * 建筑类别：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    @ApiModelProperty(value = "建筑类别：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他", name = "buildCategory")
    private String[] buildCategory;
    /**
     * 建筑类别名称：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    @ApiModelProperty(value = "建筑类别名称：1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他", name = "buildCategoryName")
    private String buildCategoryName;
    /**
     * 装修规则名称(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他)
     */
    @ApiModelProperty(value = "装修规则名称(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他)", name = "fitmentName")
    private String fitmentName;
    /**
     * 房源小区地理坐标
     */
    @ApiModelProperty(value = "房源小区地理坐标", name = "housePlotLocation")
    //@ChangeName("location")
    private String housePlotLocation;
    /**
     * 二手房房源标题图
     */
    @ApiModelProperty(value = "二手房房源标题图", name = "housePhotoTitle")
    private String housePhotoTitle;
    /**
     * 标签(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    @ApiModelProperty(value = "标签(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)", name = "tags")
    //@ChangeName("tagsId")
    private Integer[] tags;
    /**
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    @ApiModelProperty(value = "标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)", name = "tagsName")
    //@ChangeName("tags")
    private String[] tagsName;
    /**
     * 总的楼层数
     */
    @ApiModelProperty(value = "总的楼层数", name = "totalFloor")
    private Integer totalFloor;
    /**
     * 房源所在层数(地下室填负数)
     */
    @ApiModelProperty(value = "房源所在层数(地下室填负数)", name = "floorNo")
    //@ChangeName("floor")
    private Integer floorNo;
    /**
     * 房源照片
     */
    @ApiModelProperty(value = "房源照片", name = "housePhoto")
    private String[] housePhoto;
    /**
     * 房源首付
     */
    @ApiModelProperty(value = "房源首付", name = "housingDeposit")
    private Double housingDeposit;
    /**
     * 状态(0-未发布/1-已发布)
     */
    @ApiModelProperty(value = "状态(0-未发布/1-已发布)", name = "status")
    //@ChangeName("releaseStatus")
    private Short status;
    /**
     * 房源描述
     */
    @ApiModelProperty(value = "房源描述", name = "houseDesc")
    private String houseDesc;
    /**
     * 发布公司
     */
    @ApiModelProperty(value = "发布公司", name = "ofCompany")
    //@ChangeName("agentCompany")
    private String ofCompany;
    /**
     * 房源分级，0-默认，1-最好，2-次之，3-再次
     */
    @ApiModelProperty(value = "房源分级，0-默认，1-最好，2-次之，3-再次", name = "houseLevel")
    private Integer houseLevel;
    /**
     * 房源所在层（高层/低层/中层）
     */
    @ApiModelProperty(value = "房源所在层（高层/低层/中层）", name = "floor")
    //@ChangeName("floorName")
    private String floor;
    /**
     * 朝向(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    @ApiModelProperty(value = "朝向(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)", name = "forward")
    //@ChangeName("forwardId")
    private Integer forward;
    /**
     * 朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    @ApiModelProperty(value = "朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)", name = "forwardName")
    //@ChangeName("forward")
    private String forwardName;
    /**
     * 房屋类型：普通住宅、经济适用房、公寓、安置房、四合院等（物业类型）
     * （ 1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼
     */
    @ApiModelProperty(value = "房屋类型：普通住宅、经济适用房、公寓、安置房、四合院等（物业类型） 1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼", name = "houseType")
    private Integer houseType;
    /**
     * 房屋类型名称：普通住宅、经济适用房、公寓、安置房、四合院等（物业类型）
     * （ 1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼
     */
    @ApiModelProperty(value = "房屋类型名称：普通住宅、经济适用房、公寓、安置房、四合院等（物业类型）（ 1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼", name = "houseTypeName")
    private String houseTypeName;
    /**
     * 房源总价
     */
    @ApiModelProperty(value = "房源总价", name = "houseTotalPrices")
    private Double houseTotalPrices;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    private String updateTime;
    /**
     * 建筑面积
     */
    @ApiModelProperty(value = "建筑面积", name = "buildArea")
    private Double buildArea;
    /**
     * 使用面积
     */
    @ApiModelProperty(value = "使用面积", name = "liveArea")
    private Double liveArea;
    /**
     * 月供
     */
    @ApiModelProperty(value = "月供", name = "houseMonthPayment")
    private Double houseMonthPayment;
    /**
     * 是否删除(0-未删除/1-已删除)
     */
    @ApiModelProperty(value = "是否删除(0-未删除/1-已删除)", name = "isDel")
    private Integer isDel;
    /**
     * 房源总数
     */
    @ApiModelProperty(value = "房源总数", name = "totalNum")
    private Integer totalNum;
    /**
     * 是否认领
     */
    @ApiModelProperty(value = "是否认领", name = "isClaim")
    private Integer isClaim;
    /**
     * 车位配比
     */
    @ApiModelProperty(value = "车位配比", name = "parkRadio")
    //@ChangeName("parkRatio")
    private String parkRadio;
    /**
     * 经纪人姓名
     */
    @ApiModelProperty(value = "经纪人姓名", name = "houseProxyName")
    //@ChangeName("agentName")
    private String houseProxyName;
    /**
     * 经纪人电话
     */
    @ApiModelProperty(value = "经纪人电话", name = "houseProxyPhone")
    //@ChangeName("displayPhone")
    private String houseProxyPhone;
    /**
     * 经纪人头像
     */
    @ApiModelProperty(value = "经纪人头像", name = "houseProxyPhoto")
    //@ChangeName("headPhoto")
    private String houseProxyPhoto;
    /**
     * 均价
     */
    @ApiModelProperty(value = "均价", name = "houseUnitCost")
    //@ChangeName("avgPrice")
    private Double houseUnitCost;

    //@ChangeName("agent")
    private AgentBaseDo agentBaseDo;

    /**
     * 是否收藏
     */
    @ApiModelProperty(value = "是否收藏", name = "isFavorite")
    private Boolean isFavorite;
    /**
     * 价格涨降标志
     */
    @ApiModelProperty(value = "价格涨降标志", name = "priceIncreaseDecline")
    private String priceIncreaseDecline;
    /**
     * 价格涨降金额
     */
    @ApiModelProperty(value = "价格涨降金额", name = "priceIncreaseDeclineAmount")
    private Double priceIncreaseDeclineAmount;

    private Integer housePhotoTitleTags;

    /**
     * 是否主力户型(0-否，1-是)
     */
    @ApiModelProperty(value = "是否主力户型(0-否，1-是)", name = "isMainLayout")
    private Integer isMainLayout;

    /**
     * 是否成交户型(0-否，1-是)
     */
    @ApiModelProperty(value = "是否成交户型(0-否，1-是)", name = "isDealLayout")
    private Integer isDealLayout;

    /**
     * 平均成交天数
     */
    @ApiModelProperty(value = "平均成交天数", name = "avgDealCycle")
    private Integer avgDealCycle;

    /**
     * 是否价格洼地(0-否，1-是)
     */
    @ApiModelProperty(value = "是否价格洼地(0-否，1-是)", name = "isLowPrice")
    private Integer isLowPrice;

    /**
     *是否降价房(0-否，1-降价房，2-涨价房)
     */
    @ApiModelProperty(value = "是否降价房(0-否，1-降价房，2-涨价房)", name = "isCutPrice")
    private Integer isCutPrice;

    /**
     * 是否逢出必抢房(0-否，1-是)
     */
    @ApiModelProperty(value = "是否逢出必抢房(0-否，1-是)", name = "isMustRob")
    private Integer isMustRob;

    /**
     * 是否同户型小区均价最低(0-否，1-是)
     */
    @ApiModelProperty(value = "是否同户型小区均价最低(0-否，1-是)", name = "isLowest")
    private Integer isLowest;

    /**
     * 是否新导入房源(0-否，1-是)
     */
    @ApiModelProperty(value = "是否新导入房源(0-否，1-是)", name = "isNew")
    private Integer isNew;

    /**
     * 是否是top50小区房源(0-否，1-是)
     */
    @ApiModelProperty(value = "是否是top50小区房源(0-否，1-是)", name = "isCommunityTopHouse")
    private Integer isCommunityTopHouse;



    /**
     * 与商圈平均单价的绝对值差
     */
    @ApiModelProperty(value = "与商圈平均单价的绝对值差", name = "avgAbsoluteWithCommunity")
    private Double avgAbsoluteWithCommunity;

    /**
     * 与商圈平均单价的绝对值差
     */
    @ApiModelProperty(value = "与商圈平均单价的绝对值差", name = "avgAbsoluteWithBizcircle")
    private Double avgAbsoluteWithBizcircle;

    /**
     * 与区县平均单价的绝对值差
     */
    @ApiModelProperty(value = "与区县平均单价的绝对值差", name = "avgAbsoluteWithDistrict")
    private Double avgAbsoluteWithDistrict;

    /**
     * 与小区平均单价的相对值(百分比)
     */
    @ApiModelProperty(value = "与小区平均单价的相对值(百分比)", name = "avgRelativeWithCommunity")
    private Double avgRelativeWithCommunity;

    /**
     * 与商圈平均单价的相对值(百分比)
     */
    @ApiModelProperty(value = "与商圈平均单价的相对值(百分比)", name = "avgRelativeWithBizcircle")
    private Double avgRelativeWithBizcircle;

    /**
     * 与区县平均单价的相对值(百分比)
     */
    @ApiModelProperty(value = "与区县平均单价的相对值(百分比)", name = "avgRelativeWithDistrict")
    private Double avgRelativeWithDistrict;

    /**
     * 与小区平均总价的绝对值差
     */
    @ApiModelProperty(value = "与小区平均总价的绝对值差", name = "totalAbsoluteWithCommunity")
    private Double totalAbsoluteWithCommunity;

    /**
     * 与商圈平均总价的绝对值差
     */
    @ApiModelProperty(value = "与商圈平均总价的绝对值差", name = "totalAbsoluteWithBizcircle")
    private Double totalAbsoluteWithBizcircle;

    /**
     * 与区县平均总价的绝对值差
     */
    @ApiModelProperty(value = "与区县平均总价的绝对值差", name = "totalAbsoluteWithDistrict")
    private Double totalAbsoluteWithDistrict;

    /**
     * 与小区平均总价的相对值(百分比)
     */
    @ApiModelProperty(value = "与小区平均总价的相对值(百分比)", name = "totalRelativeWithCommunity")
    private Double totalRelativeWithCommunity;

    /**
     * 与商圈平均总价的相对值(百分比)
     */
    @ApiModelProperty(value = "与商圈平均总价的相对值(百分比)", name = "totalRelativeWithBizcircle")
    private Double totalRelativeWithBizcircle;

    /**
     * 与区县平均总价的相对值(百分比)
     */
    @ApiModelProperty(value = "与区县平均总价的相对值(百分比)", name = "totalRelativeWithDistrict")
    private Double totalRelativeWithDistrict;
    /**
     * 累计涨降金额
     */
    @ApiModelProperty(value = "累计涨降金额", name = "priceFloat")
    private Double priceFloat;

    /**
     * 推荐标签id
     */
    @ApiModelProperty(value = "推荐标签id", name = "recommendBuildTagsId")
    private List recommendBuildTagsId;
    /**
     * 推荐标签名称
     */
    @ApiModelProperty(value = "推荐标签名称", name = "recommendBuildTagsName")
    private List recommendBuildTagsName;

    /**
     * 近公园
     */
    @ApiModelProperty(value = "近公园", name = "nearPark")
    private String nearPark;

    /**
     * 各个类型数量
     */
    @ApiModelProperty(value = "各个类型数量", name = "typeCounts")
    private Map<Integer,Map<String,Integer>> typeCounts;

    /**
     * 在同商圈同户型范围内做低价排名
     */
    @ApiModelProperty(value = "在同商圈同户型范围内做低价排名", name = "rankLowInBizcircleLayout")
    private Integer rankLowInBizcircleLayout;

    /**
     * 同小区同户型范围内做低价排名
     */
    @ApiModelProperty(value = "同小区同户型范围内做低价排名", name = "rankInLowCommunityLayout")
    private Integer rankInLowCommunityLayout;

    /**
     * 名片
     */
    @ApiModelProperty(value = "名片", name = "agentBusinessCard")
    private String agentBusinessCard;

    /**
     * 经济公司营业执照
     */
    @ApiModelProperty(value = "经济公司营业执照", name = "companyCard")
    private String companyCard;

    /**
     * 楼盘专家
     */
    @ApiModelProperty(value = "楼盘专家", name = "projExpertUserId")
    private String projExpertUserId;

    /**
     * 房源导入时间
     */
    @ApiModelProperty(value = "房源导入时间", name = "importTime")
    private String importTime;

    /**
     * 是否显示默认图片标志
     */
    @ApiModelProperty(value = "是否显示默认图片标志", name = "isDefaultImage")
    private Integer isDefaultImage = 0;

    @ApiModelProperty(value = "公司图标", name = "companyIcon")
    private String companyIcon;

    @ApiModelProperty(value = "小区信息", name = "plotInfo")
    private PlotDetailsResponse plotInfo;

    @ApiModelProperty(value = "初始价格", name = "initialPrice")
    private Double initialPrice;

    @ApiModelProperty(value = "降价房排名表述", name = "houseCutLabel")
    private String houseCutLabel;

    @ApiModelProperty(value = "捡漏房排名表述", name = "houseLowerLabel")
    private String houseLowerLabel;

    @ApiModelProperty(value = "抢手房排名表述", name = "houseRobLabel")
    private String houseRobLabel;

    @ApiModelProperty(value = "房源标签列表", name = "houseColorLableList")
    private List<HouseColorLable> houseColorLableList;

    @ApiModelProperty(value = "专题列表", name = "houseSubjectList")
    private List<HouseSubject> houseSubjectList;
}
