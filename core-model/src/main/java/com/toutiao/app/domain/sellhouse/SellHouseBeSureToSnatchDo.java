package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SellHouseBeSureToSnatchDo {

    /**
     * 大楼名称
     */
    @ApiModelProperty(value = "大楼名称", name = "plotName")
    @ChangeName("buildingName")
    private  String  plotName;

    /**
     * 普通房源标题图
     */
    @ApiModelProperty(value = "普通房源标题图", name = "housePhotoTitle")
    private  String housePhotoTitle;

    /**
     * 认领房源标题图
     */
    @ApiModelProperty(value = "认领房源标题图", name = "claimHousePhotoTitle")
    private String claimHousePhotoTitle;

    /**
     * 认领房源标题
     */
    @ApiModelProperty(value = "认领房源标题", name = "claimHouseTitle")
    private String claimHouseTitle;

    /**
     * 认领房源标题
     */
    @ApiModelProperty(value = "认领房源标题", name = "claimTagsName")
    private String[] claimTagsName;


    /**
     * 室
     */
    @ApiModelProperty(value = "室", name = "room")
    private  Integer room;


    /**
     * 建筑面积
     */
    @ApiModelProperty(value = "建筑面积", name = "buildArea")
    private Double buildArea;


    /**
     * 房源总价
     */
    @ApiModelProperty(value = "房源总价", name = "houseTotalPrices")
    private Double houseTotalPrices;

    /**
     * 降幅
     */
    @ApiModelProperty(value = "降幅", name = "priceFloat")
    private  Double priceFloat;

    /**
     * 同小区同户型范围内做低价排名
     */
    @ApiModelProperty(value = "同小区同户型范围内做低价排名", name = "rankInLowCommunityLayout")
    private Integer rankInLowCommunityLayout;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "sort")
    private Object sort;

    /**
     * 房源id
     */
    @ApiModelProperty(value = "房源id", name = "houseId")
    private  String houseId;

    /**
     * 认领房源id
     */
    @ApiModelProperty(value = "认领房源id", name = "claimHouseId")
    private  String claimHouseId;

    private  Integer isClaim;

    private Integer userId;

    /**
     * 厅
     */
    @ApiModelProperty(value = "厅", name = "hall")
    private Integer hall;

    /**
     * 朝向
     */
    @ApiModelProperty(value = "朝向", name = "forwardName")
    @ChangeName("forward")
    private String forwardName;

    /**
     * 区域Id
     */
    @ApiModelProperty(value = "区域Id", name = "areaId")
    @ChangeName("districtId")
    private Integer areaId;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称", name = "area")
    @ChangeName("districtName")
    private String area;

    /**
     * 商圈id
     */
    @ApiModelProperty(value = "商圈id", name = "houseBusinessNameId")
    @ChangeName("areaId")
    private String houseBusinessNameId;
    /**
     * 商圈名称
     */
    @ApiModelProperty(value = "商圈名称", name = "houseBusinessName")
    @ChangeName("areaName")
    private String houseBusinessName;

    /**
     * 房源单价
     */
    @ApiModelProperty(value = "房源单价", name = "houseUnitCost")
    private Double houseUnitCost;

    /**
     * 经纪人
     */
    @ApiModelProperty(value = "经纪人", name = "agentBaseDo")
    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;

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
     * 标签
     */
    @ApiModelProperty(value = "标签", name = "tagsName")
    @ChangeName("tags")
    private String[] tagsName;

    private String houseTitle;


    @ChangeName("buildingId")
    private  Integer newCode;

    @ChangeName("nearBySubwayDesc")
    private String traffic;

    /**
     * 各个类型数量
     */
    @ApiModelProperty(value = "各个类型数量", name = "typeCounts")
    private Map<Integer,Map<String,Integer>> typeCounts;

    private List recommendBuildTagsName;

    private List recommendBuildTagsId;

    /**
     * 近公园
     */
    @ApiModelProperty(value = "近公园", name = "nearPark")
    private String nearPark;

    /**
     * 同小区同户型范围内做低价排名
     */
    @ApiModelProperty(value = "同小区同户型范围内做低价排名", name = "rankLowInBizcircleLayout")
    private Integer rankLowInBizcircleLayout;

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


}
