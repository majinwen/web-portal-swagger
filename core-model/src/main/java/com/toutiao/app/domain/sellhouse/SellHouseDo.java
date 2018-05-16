package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

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
     * 7天新导入标志
     *
     */
    private Integer newImport;

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

    private Integer housePhotoTitleTags;




}
