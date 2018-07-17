package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

@Data
public class CutPriceShellHouseDo {
    /**
     * 房源id
     */
    private Integer houseId;

    /**
     * 房源面积(单位:平方米)
     */
    private Double buildArea;

    /**
     * 朝向名称
     */
    @ChangeName("forward")
    private String forwardName;

    /**
     * 房源价格(单位:万)
     */
    private Double houseTotalPrices;

    /**
     * 价格浮动(单位:万)
     */
    private Double priceFloat;

    /**
     * 房源标题图片
     */
    private String housePhotoTitle;

    /**
     * 区域Id
     */
    @ChangeName("districtId")
    private Integer areaId;

    /**
     * 室
     */
    private Integer room;

    /**
     * 厅
     */
    private Integer hall;

    /**
     * 标签名称
     */
    @ChangeName("tags")
    private String[] tagsName;

    /**
     * 是否主力户型(0-否,1-是)
     */
    private Integer isMainLayout;

    /**
     * 是否成交户型(0-否,1-是)
     */
    private Integer isDealLayout;

    /**
     * 是否降价房(0-否, 1-降价房, 2-涨价房)
     */
    private Integer isCutPrice;

    /**
     * 是否同户型小区均价最低(0-否,1-是)
     */
    private Integer isLowest;

    /**
     * 是否新导入房源(0-否,1-是)
     */
    private Integer isNew;

    /**
     * 是否是top50小区房源(0-否, 1-是)
     */
    private Integer isCommunityTopHouse;

    /**
     * 在同商圈同户型范围内做低价排名
     */
    private Integer rankLowInBizcircleLayout;

    /**
     * 同小区同户型范围内做低价排名
     */
    private Integer rankInLowCommunityLayout;

    /**
     * 与小区平均单价的绝对值差
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
     * 更新时间
     */
    private String updateTime;

    /**
     * 排序属性
     */
    private String sortField;

    /**
     * uid
     */
    private String uid;

    /**
     * 经纪人信息
     */
    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 是否认领(0-否,1-是)
     */
    private Integer isClaim;

    /**
     * 房源均价
     */
    private Double houseUnitCost;

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
     * 平均成交天数
     */
    private Integer avgDealCycle;
}
