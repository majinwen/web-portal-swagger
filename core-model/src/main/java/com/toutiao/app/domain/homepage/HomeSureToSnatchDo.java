package com.toutiao.app.domain.homepage;

import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HomeSureToSnatchDo {
    /**
     * top50
     */
    private  Integer isCommunityTopHouse;

    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    private String plotName;

    /**
     * 室
     */
    private Integer room;

    /**
     * 朝向
     */
    @ChangeName("forward")
    private String forwardName;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;

    /**
     * 建筑面积
     */
    private Double buildArea;

    /**
     * 房源与商圈的均价比
     */
     private  Double avgRelativeWithBizcircle;


    /**
     * 主力户型
     */
    private  Integer isMainLayout;


    /**
     * 普通房源标题图
     */
    private  String housePhotoTitle;

    /**
     * 认领房源标题图
     */
    private String claimHousePhotoTitle;

    /**
     * 是否是认领
     */
    private  Integer isClaim;

    /**
     * 房源id
     */
    private  String houseId;

    /**
     * 认领房源id
     */
     private  String claimHouseId;

     private Double avgRelativeWithDistrict;
     private Double avgRelativeWithCommunity;
     private Integer avgDealCycle;
     private Double avgAbsoluteWithDistrict;
     private Double avgAbsoluteWithCommunity;
     private Double avgAbsoluteWithBizcircle;
     private Double totalAbsoluteWithBizcircle;
     private Double totalAbsoluteWithCommunity;
     private Double totalAbsoluteWithDistrict;
     private Double totalRelativeWithBizcircle;
     private Double totalRelativeWithCommunity;
     private Double totalRelativeWithDistrict;


    /**
     * 是否成交户型(0-否,1-是)
     */
    private Integer isDealLayout;

    /**
     * 是否降价房(0-否, 1-降价房, 2-涨价房)
     */
    private Integer isCutPrice;

    /**
     * 是否价格洼地(0-否, 1-是)
     */
    private Integer isLowPrice;

    /**
     * 是否同户型小区均价最低(0-否,1-是)
     */
    private Integer isLowest;

    /**
     * 是否逢出毕抢(0-否, 1-是)
     */
    private Integer isMustRob;

    /**
     * 是否新导入房源(0-否,1-是)
     */
    private Integer isNew;

    /**
     * 在同商圈同户型范围内做低价排名
     */
    private Integer rankLowInBizcircleLayout;

    /**
     * 同小区同户型范围内做低价排名
     */
    private Integer rankInLowCommunityLayout;

    private List recommendBuildTagsName;

    private List recommendBuildTagsId;

    /**
     * 各个类型数量
     */
    private Map<Integer,Map<String,Integer>> typeCounts;

    /**
     * 区域Id
     */
    @ChangeName("districtId")
    private Integer areaId;

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



}
