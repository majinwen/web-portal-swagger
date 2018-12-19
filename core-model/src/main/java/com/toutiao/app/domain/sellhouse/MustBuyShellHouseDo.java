package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MustBuyShellHouseDo {
    /**
     * 房源id
     */
    @ApiModelProperty(value = "房源id", name = "houseId")
    private String houseId;

    /**
     * 房源面积(单位:平方米)
     */
    @ApiModelProperty(value = "房源面积(单位:平方米)", name = "buildArea")
    private Double buildArea;

    /**
     * 朝向名称
     */
    @ApiModelProperty(value = "朝向名称", name = "forwardName")
    @ChangeName("forward")
    private String forwardName;

    /**
     * 房源价格(单位:万)
     */
    @ApiModelProperty(value = "房源价格(单位:万)", name = "houseTotalPrices")
    private Double houseTotalPrices;

    /**
     * 价格浮动(单位:万)
     */
    @ApiModelProperty(value = "价格浮动(单位:万)", name = "priceFloat")
    private Double priceFloat;

    /**
     * 房源标题图片
     */
    @ApiModelProperty(value = "房源标题图片", name = "housePhotoTitle")
    private String housePhotoTitle;

    /**
     * 区域Id
     */
    @ApiModelProperty(value = "区域Id", name = "areaId")
    @ChangeName("districtId")
    private Integer areaId;

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
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称", name = "tagsName")
    @ChangeName("tags")
    private String[] tagsName;

    /**
     * 是否主力户型(0-否,1-是)
     */
    @ApiModelProperty(value = "是否主力户型(0-否,1-是)", name = "isMainLayout")
    private Integer isMainLayout;

    /**
     * 是否成交户型(0-否,1-是)
     */
    @ApiModelProperty(value = "是否成交户型(0-否,1-是)", name = "isDealLayout")
    private Integer isDealLayout;

    /**
     * 是否降价房(0-否, 1-降价房, 2-涨价房)
     */
    @ApiModelProperty(value = "是否降价房(0-否, 1-降价房, 2-涨价房)", name = "isCutPrice")
    private Integer isCutPrice;

    /**
     * 是否价格洼地(0-否, 1-是)
     */
    @ApiModelProperty(value = "是否价格洼地(0-否, 1-是)", name = "isLowPrice")
    private Integer isLowPrice;

    /**
     * 是否同户型小区均价最低(0-否,1-是)
     */
    @ApiModelProperty(value = "是否同户型小区均价最低(0-否,1-是)", name = "isLowest")
    private Integer isLowest;

    /**
     * 是否新导入房源(0-否,1-是)
     */
    @ApiModelProperty(value = "是否新导入房源(0-否,1-是)", name = "isNew")
    private Integer isNew;

    /**
     * 是否是top50小区房源(0-否, 1-是)
     */
    @ApiModelProperty(value = "是否是top50小区房源(0-否, 1-是)", name = "isCommunityTopHouse")
    private Integer isCommunityTopHouse;

    /**
     * 在同商圈同户型范围内做低价排名
     */
    @ApiModelProperty(value = "在同商圈同户型范围内做低价排名", name = "rankLowInBizcircleLayout")
    private Integer rankLowInBizcircleLayout;

    /**
     * 同小区同户型范围内做低价排名
     */
    @ApiModelProperty(value = "同小区同户型范围内做低价排名", name = "rankInLowCommunityLayout")
    private Integer rankInLowCommunityLayout;

    /**
     * 与小区平均单价的绝对值差
     */
    @ApiModelProperty(value = "与小区平均单价的绝对值差", name = "avgAbsoluteWithCommunity")
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
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    private String updateTime;

    /**
     * 经纪人信息
     */
    @ApiModelProperty(value = "经纪人信息", name = "agentBaseDo")
    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id", name = "userId")
    private Integer userId;

    /**
     * 是否认领(0-否,1-是)
     */
    @ApiModelProperty(value = "是否认领(0-否,1-是)", name = "isClaim")
    private Integer isClaim;

    /**
     * 房源均价
     */
    @ApiModelProperty(value = "房源均价", name = "houseUnitCost")
    private Double houseUnitCost;

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
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称", name = "plotName")
    @ChangeName("buildingName")
    private String plotName;

    /**
     * 小区id
     */
    @ApiModelProperty(value = "小区id", name = "newcode")
    @ChangeName("buildingId")
    private Integer newcode;

    /**
     * 平均成交天数
     */
    @ApiModelProperty(value = "平均成交天数", name = "avgDealCycle")
    private Integer avgDealCycle;

    /**
     * 房源标题
     */
    @ApiModelProperty(value = "房源标题", name = "houseTitle")
    private String houseTitle;



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
