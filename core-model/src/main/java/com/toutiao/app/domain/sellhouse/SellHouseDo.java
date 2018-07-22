package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SellHouseDo {

    /**
     * 二手房房源id
     */
    @ChangeName("houseId")
    private String claimHouseId;
    /**
     * 房源标题
     */
    @ChangeName("houseTitle")
    private String claimHouseTitle;
    /**
     * 房源图片
     */
    @ChangeName("housePhoto")
    private String[] housePhoto;

    /**
     * 标题图
     */
    @ChangeName("housePhotoTitle")
    private String claimHousePhotoTitle;

    /**
     * 价格涨降标志
     */
    private String priceIncreaseDecline;

    /**
     * 价格涨降金额
     */
    private Double priceIncreaseDeclineAmount;

    /**
     * 7天新导入标志
     *
     */
//    private Integer newImport;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;

    /**
     * 房源总价
     */
    private Double houseUnitCost;

    /**
     * 建筑面积
     */
//    @ChangeName("houseArea")
    private Double buildArea;

    /**
     * 标签名称(1:近地铁 4:随时看 8:满二年 16:满五年 32:近公园)
     */
    @ChangeName("tags")
    private String[] claimTagsName;

    /**
     * 室
     */
    private Integer room;
    /**
     * 厅
     */
    private Integer hall;

    /**
     * 朝向
     */
    @ChangeName("forward")
    private String forwardName;

    /**
     * 区域名称
     */
    @ChangeName("districtName")
    private String area;

    /**
     * 商圈id
     */
    @ChangeName("areaId")
    private String houseBusinessNameId;
    /**
     * 商圈名称
     */
    @ChangeName("areaName")
    private String houseBusinessName;

    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    private String plotName;

    /**
     * 小区id
     */
    @ChangeName("buildingId")
    private Integer newcode;

    /**
     * 地铁线/站 距离
     */
    @ChangeName("nearbyStationsDistance")
    private Map<String, String> subwayDistince;
    /**
     * 建成年代
     */
    @ChangeName("buildYears")
    private String year;
    /**
     * 房源小区地理坐标
     */
    @ChangeName("location")
    private String housePlotLocation;

    /**
     * 车位配比
     */
    @ChangeName("parkRatio")
    private String parkRadio;

    /**
     * 推荐广告查询uid
     */
    private String uid;

    /**
     * 是否认领
     */
    private Integer isClaim;
    /**
     * 经纪人id
     */
    private Integer userId;


    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;

    @ChangeName("nearBySubwayDesc")
    private  String traffic;

    private Integer housePhotoTitleTags = -1;

    /**
     * 排序规则字段
     */
    private String sortFields;

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



}
