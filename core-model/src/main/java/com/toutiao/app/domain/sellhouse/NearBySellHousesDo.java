package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NearBySellHousesDo {

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
     * 二手房房源图片
     */
    @ApiModelProperty(value = "二手房房源图片", name = "housePhoto")
    private String[] housePhoto;

    /**
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    @ApiModelProperty(value = "标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)", name = "tagsName")
    @ChangeName("tags")
    private String[] tagsName;

    /**
     * 房源总价
     */
    @ApiModelProperty(value = "房源总价", name = "houseTotalPrices")
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    @ApiModelProperty(value = "建筑面积", name = "buildArea")
    private Double buildArea;

    /**
     * 朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    @ApiModelProperty(value = "朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)", name = "forwardName")
    @ChangeName("forward")
    private String forwardName;

    /**
     *
     * 车位配比
     */
    @ApiModelProperty(value = "车位配比", name = "parkRadio")
    @ChangeName("parkRatio")
    private String parkRadio;

    /**
     * 建成年代
     */
    @ApiModelProperty(value = "建成年代", name = "year")
    @ChangeName("buildYears")
    private String year;

    /**
     * 认领标识
     */
    @ApiModelProperty(value = "认领标识", name = "isClaim")
    private  Integer isClaim;

    /**
     * 地铁最近
     */
    @ApiModelProperty(value = "地铁最近", name = "traffic")
    @ChangeName("nearBySubway")
    private  String traffic;

    /**
     * 楼盘名称
     */
    @ApiModelProperty(value = "楼盘名称", name = "plotNameAccurate")
    @ChangeName("buildingName")
    private  String plotNameAccurate;

    /**
     * 地铁站编号
     */
    @ApiModelProperty(value = "地铁站编号", name = "subwayStationId")
    private String[] subwayStationId;
    /**
     * 地铁线编号
     */
    @ApiModelProperty(value = "地铁线编号", name = "subwayLineId")
    private String[] subwayLineId;

    /**
     * 地铁到房源的距离
     */
    @ApiModelProperty(value = "地铁到房源的距离", name = "subwayDistince")
    @ChangeName("subwayDistance")
    private Map subwayDistince;

    /**
     * 区域
     */
    @ApiModelProperty(value = "区域", name = "area")
    @ChangeName("districtName")
    private  String area;

    /**
     * 区域
     */
    @ApiModelProperty(value = "区域Id", name = "areaId")
    @ChangeName("districtId")
    private  Integer areaId;


    /**
     * 商圈id
     */
    @ApiModelProperty(value = "商圈id", name = "houseBusinessNameId")
    @ChangeName("areaId")
    private String houseBusinessNameId;
    /**
     * 商圈
     */
    @ApiModelProperty(value = "商圈", name = "houseBusinessName")
    @ChangeName("areaName")
    private  String houseBusinessName;


    /**
     * 均价
     */
    @ApiModelProperty(value = "均价", name = "houseUnitCost")
     private  Double houseUnitCost;

    /**
     * 小区id
     */
    @ApiModelProperty(value = "小区id", name = "newcode")
    @ChangeName("buildingId")
    private Integer newcode;

    private  String housePhotoTitle;

    /**
     * 地铁与房子的距离
     */
    @ApiModelProperty(value = "地铁与房子的距离", name = "subwayDistanceInfo")
    private String subwayDistanceInfo;

    /**
     * 经纪人信息
     */
    @ApiModelProperty(value = "经纪人信息", name = "agentBaseDo")
    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;

    private Integer userId;

    /**
     * 价格涨降标志
     */
    @ApiModelProperty(value = "价格涨降标志", name = "priceIncreaseDecline")
    private String priceIncreaseDecline;

    private Integer housePhotoTitleTags = -1;


    /**
     * 价格涨降金额
     */
    @ApiModelProperty(value = "价格涨降金额", name = "priceIncreaseDeclineAmount")
    private Double priceIncreaseDeclineAmount;

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
    @ApiModelProperty(value = "是否同户型小区均价最低(0-否，1-是", name = "isLowest")
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
     * 涨降金额
     */
    @ApiModelProperty(value = "涨降金额", name = "priceFloat")
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
     * 同小区同户型范围内做低价排名
     */
    @ApiModelProperty(value = "同小区同户型范围内做低价排名", name = "rankLowInBizcircleLayout")
    private Integer rankLowInBizcircleLayout;

    /**
     * 同小区同户型范围内做低价排名
     */
    @ApiModelProperty(value = "同小区同户型范围内做低价排名", name = "rankInLowCommunityLayout")
    private Integer rankInLowCommunityLayout;

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

    @ApiModelProperty(value = "逛逛标签列表", name = "houseColorLableList")
    private List<HouseColorLable> houseColorLableList;

    @ApiModelProperty(value = "逛逛第一行弹幕", name = "houseBarrageFirstList")
    private List<String> houseBarrageFirstList;

    @ApiModelProperty(value = "逛逛第二行弹幕", name = "houseBarrageSecondList")
    private List<String> houseBarrageSecondList;

}
