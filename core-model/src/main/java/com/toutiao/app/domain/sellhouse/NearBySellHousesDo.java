package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class NearBySellHousesDo {

    /**
     * 二手房房源id
     */
    private String houseId;

    /**
     * 房源标题
     */
    private String houseTitle;

    /**
     * 室
     */
    private Integer room;
    /**
     * 厅
     */
    private Integer hall;
    /**
     * 卫
     */
    private Integer toilet;

    /**
     * 二手房房源图片
     */
    private String[] housePhoto;

    /**
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    @ChangeName("tags")
    private String[] tagsName;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    private Double buildArea;

    /**
     * 朝向名称(1:东,2:西,3:南,4:北,5:东南,6:西南,7:东北,8:西北,9:东西,10:南北)
     */
    @ChangeName("forward")
    private String forwardName;

    /**
     *
     * 车位配比
     */
    @ChangeName("parkRatio")
    private String parkRadio;

    /**
     * 建成年代
     */
    @ChangeName("buildYears")
    private String year;

    /**
     * 认领标识
     */
    private  Integer isClaim;

    /**
     * 地铁最近
     */
    @ChangeName("nearBySubway")
    private  String traffic;

    /**
     * 楼盘名称
     */
    @ChangeName("buildingName")
    private  String plotNameAccurate;

    /**
     * 地铁站编号
     */
    private String[] subwayStationId;
    /**
     * 地铁线编号
     */
    private String[] subwayLineId;

    /**
     * 地铁到房源的距离
     */
    @ChangeName("subwayDistance")
    private Map subwayDistince;

    /**
     * 区域
     */
    @ChangeName("districtName")
    private  String area;

    /**
     * 区域
     */
    @ChangeName("districtId")
    private  Integer areaId;


    /**
     * 商圈id
     */
    @ChangeName("areaId")
    private String houseBusinessNameId;
    /**
     * 商圈
     */
    @ChangeName("areaName")
    private  String houseBusinessName;


    /**
     * 均价
     */
     private  Double houseUnitCost;

    /**
     * 小区id
     */
    @ChangeName("buildingId")
    private Integer newcode;

    private  String housePhotoTitle;

    /**
     * 地铁与房子的距离
     */
    private String subwayDistanceInfo;

    /**
     * 经纪人信息
     */
    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;

    private Integer userId;

    /**
     * 价格涨降标志
     */
    private String priceIncreaseDecline;

    private Integer housePhotoTitleTags = -1;


    /**
     * 价格涨降金额
     */
    private Double priceIncreaseDeclineAmount;

    /**
     * 是否主力户型(0-否，1-是)
     */
    private Integer isMainLayout;

    /**
     * 是否成交户型(0-否，1-是)
     */
    private Integer isDealLayout;

    /**
     * 平均成交天数
     */
    private Integer avgDealCycle;

    /**
     * 是否价格洼地(0-否，1-是)
     */
    private Integer isLowPrice;

    /**
     *是否降价房(0-否，1-降价房，2-涨价房)
     */
    private Integer isCutPrice;

    /**
     * 是否逢出必抢房(0-否，1-是)
     */
    private Integer isMustRob;

    /**
     * 是否同户型小区均价最低(0-否，1-是)
     */
    private Integer isLowest;

    /**
     * 是否新导入房源(0-否，1-是)
     */
    private Integer isNew;

    /**
     * 是否是top50小区房源(0-否，1-是)
     */
    private Integer isCommunityTopHouse;



    /**
     * 与商圈平均单价的绝对值差
     */
    private Double avgAbsoluteWithCommunity;

    /**
     * 与商圈平均单价的绝对值差
     */
    private Double avgAbsoluteWithBizcircle;

    /**
     * 与区县平均单价的绝对值差
     */
    private Double avgAbsoluteWithDistrict;

    /**
     * 与小区平均单价的相对值(百分比)
     */
    private Double avgRelativeWithCommunity;

    /**
     * 与商圈平均单价的相对值(百分比)
     */
    private Double avgRelativeWithBizcircle;

    /**
     * 与区县平均单价的相对值(百分比)
     */
    private Double avgRelativeWithDistrict;

    /**
     * 与小区平均总价的绝对值差
     */
    private Double totalAbsoluteWithCommunity;

    /**
     * 与商圈平均总价的绝对值差
     */
    private Double totalAbsoluteWithBizcircle;

    /**
     * 与区县平均总价的绝对值差
     */
    private Double totalAbsoluteWithDistrict;

    /**
     * 与小区平均总价的相对值(百分比)
     */
    private Double totalRelativeWithCommunity;

    /**
     * 与商圈平均总价的相对值(百分比)
     */
    private Double totalRelativeWithBizcircle;

    /**
     * 与区县平均总价的相对值(百分比)
     */
    private Double totalRelativeWithDistrict;

    /**
     * 涨降金额
     */
    private Double priceFloat;

    /**
     * 推荐标签id
     */
    private List recommendBuildTagsId;
    /**
     * 推荐标签名称
     */
    private List recommendBuildTagsName;

    /**
     * 近公园
     */
    private String nearPark;

    /**
     * 各个类型数量
     */
    private Map<Integer,Map<String,Integer>> typeCounts;


    /**
     * 同小区同户型范围内做低价排名
     */
    private Integer rankLowInBizcircleLayout;

    /**
     * 同小区同户型范围内做低价排名
     */
    private Integer rankInLowCommunityLayout;

    /**
     * 楼盘专家
     */
    private String projExpertUserId;

    /**
     * 房源导入时间
     */
    private String importTime;

    /**
     * 是否显示默认图片标志
     */
    private Integer isDefaultImage = 0;



}
