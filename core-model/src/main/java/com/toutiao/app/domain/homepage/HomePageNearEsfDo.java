package com.toutiao.app.domain.homepage;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HomePageNearEsfDo {
    /**
     * 二手房房源id
     */
    private String houseId;
    /**
     * 房源标题
     */
    private String houseTitle;
    /**
     * 区域
     */
    @ChangeName("districtName")
    private String area;

    /**
     * 区域
     */
    @ChangeName("districtId")
    private String areaId;
    /**
     * 商圈
     */
    @ChangeName("areaName")
    private String houseBusinessName;

    @ChangeName("areaId")
    private String houseBusinessNameId;
    /**
     * 朝向
     */
    @ChangeName("forward")
    private String forwardName;
    /**
     * 房源标题图
     */
    private String housePhotoTitle;
    /**，
     * 室
     */
    private Integer room;
    /**
     * 厅
     */
    private Integer hall;
    /**
     * 房源单价
     */
    private Double unitPrice;
    /**
     * 房源总价
     */
    private Double houseTotalPrices;
    /**
     * 建筑面积
     */
    private Double buildArea;
    /**
     * 房源标签
     */
    @ChangeName("tags")
    private List tagsName;
    /**
     * 是否属于top50小区
     */
    private Integer isCommunityTopHouse;
    /**
     * 是否是降价房(0:否 1:降 2:涨)
     */
    private Integer isCutPrice ;
    /**
     * 距离
     */
    private Double distance;
    /**
     * 价格浮动
     */
    private double priceFloat;
    /**
     * 小区名称
     */
    @ChangeName("buildingName")
    private String plotName;
    /**
     * 楼盘ID(楼盘/小区)
     */
    @ChangeName("buildingId")
    private Integer newcode;
//    /**
//     * 排序规则字段
//     */
//    private String upTimestamp;
//    /**
//     * 推荐广告查询uid
//     */
//    private String uid;
    /**
     * 是否认领
     */
    private Integer isClaim;
    /**
     * 经纪人信息
     */
    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;
    /**
     * 主力户型
     */
    private  Integer isMainLayout;
    /**
     * 同小区同户型范围内做低价排名
     */
    private Integer rankInLowCommunityLayout;
    /**
     * 逢出必抢
     */
    private Integer isMustRob;
    /**
     * 与商圈平均单价的绝对值差
     */
    private Double avgAbsoluteWithBizcircle;
    /**
     * 推荐标签id
     */
    private List recommendBuildTagsId;
    /**
     * 推荐标签名称
     */
    private List recommendBuildTagsName;
    /**
     * 在同商圈同户型范围内做低价排名
     */
    private Integer rankLowInBizcircleLayout;
    /**
     * 成交天数
     */
    private Integer avgDealCycle;

    @ChangeName("location")
    private String housePlotLocation;

    /**
     * 与小区平均单价的绝对值差
     */
    private Double avgAbsoluteWithCommunity;

    /**
     * 与小区平均总价的绝对值差
     */
    private Double totalAbsoluteWithCommunity;


    private Map<Integer,Map<String,Integer>> typeCounts;


    private String nearPark;


    private Double totalAbsoluteWithBizcircle;
    private Double isLowPrice;

    /**
     * 房源导入时间
     */
    private String importTime;

    /**
     * 是否显示默认图片标志
     */
    private Integer isDefaultImage = 0;
}
