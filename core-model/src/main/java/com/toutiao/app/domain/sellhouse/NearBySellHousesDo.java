package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

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
}
