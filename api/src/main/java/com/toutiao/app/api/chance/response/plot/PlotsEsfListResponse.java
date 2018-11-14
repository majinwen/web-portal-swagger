package com.toutiao.app.api.chance.response.plot;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PlotsEsfListResponse {

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
     * 楼盘ID(楼盘/小区)
     */
    @ChangeName("buildingId")
    private Integer newCode;

    /**
     * 房源总价
     */
    private Double houseTotalPrices;
    /**
     * 建筑面积
     */
    private Double buildArea;
    /**
     * 朝向
     */
    @ChangeName("forward")
    private String forwardName;
    /**
     * 标题图
     */
    private String housePhotoTitle;

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
     * 在同商圈同户型范围内做低价排名
     */
    private Integer rankLowInBizcircleLayout;

    /**
     * 同小区同户型范围内做低价排名
     */
    private Integer rankInLowCommunityLayout;

    @ChangeName("buildingName")
    private String plotName;

    @ChangeName("tags")
    private String[] tagsName;

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
     * 单价
     */
    private Double houseUnitCost;

    /**
     * 厅
     */
    private Integer hall;

    /**
     * 区域名称
     */
    @ChangeName("districtName")
    private String area;
    /**
     * 区域id
     */
    @ChangeName("districtId")
    private Integer areaId;

    /**
     * 经纪人信息
     */
    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;


    /**
     * 房源导入时间
     */
    private String importTime;

    /**
     * 是否显示默认图片标志
     */
    private Integer isDefaultImage = 0;

}
