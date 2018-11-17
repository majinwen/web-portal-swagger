package com.toutiao.app.domain.sellhouse;

import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HouseBusinessAndRoomDo {
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
    @ChangeName("forward")
    @ApiModelProperty(value = "朝向名称", name = "buildArea")
    private String forwardName;

    /**
     * 房源价格(单位:万)
     */
    @ApiModelProperty(value = "房源价格(单位:万)", name = "buildArea")
    private Double houseTotalPrices;

    /**
     * 价格浮动(单位:万)
     */
    @ApiModelProperty(value = "价格浮动(单位:万)", name = "buildArea")
    private Double priceFloat;

    /**
     * 房源标题图片
     */
    @ApiModelProperty(value = "房源标题图片", name = "buildArea")
    private String housePhotoTitle;

    /**
     * 区域Id
     */
    @ApiModelProperty(value = "区域Id", name = "buildArea")
    @ChangeName("districtId")
    private Integer areaId;

    /**
     * 商圈Id
     */
    @ApiModelProperty(value = "商圈Id", name = "buildArea")
    @ChangeName("areaId")
    private Integer houseBusinessNameId;

    /**
     * 室
     */
    @ApiModelProperty(value = "室", name = "buildArea")
    private Integer room;

    /**
     * 厅
     */
    @ApiModelProperty(value = "厅", name = "buildArea")
    private Integer hall;


//    /**
//     * 标签
//     */
//    private Integer[] tags;

    /**
     * 标签名称
     */
    @ApiModelProperty(value = "标签名称", name = "buildArea")
    @ChangeName("tags")
    private String[] tagsName;

    /**
     * 是否主力户型(0-否,1-是)
     */
    @ApiModelProperty(value = "是否主力户型(0-否,1-是)", name = "buildArea")
    private Integer isMainLayout;

    /**
     * 是否成交户型(0-否,1-是)
     */
    @ApiModelProperty(value = "是否成交户型(0-否,1-是)", name = "buildArea")
    private Integer isDealLayout;

    /**
     * 是否降价房(0-否, 1-降价房, 2-涨价房)
     */
    @ApiModelProperty(value = "是否降价房(0-否, 1-降价房, 2-涨价房)", name = "buildArea")
    private Integer isCutPrice;

    /**
     * 是否价格洼地(0-否, 1-是)
     */
    @ApiModelProperty(value = "是否价格洼地(0-否, 1-是)", name = "buildArea")
    private Integer isLowPrice;

    /**
     * 是否同户型小区均价最低(0-否,1-是)
     */
    @ApiModelProperty(value = "是否同户型小区均价最低(0-否,1-是)", name = "buildArea")
    private Integer isLowest;

    /**
     * 是否新导入房源(0-否,1-是)
     */
    @ApiModelProperty(value = "是否新导入房源(0-否,1-是)", name = "buildArea")
    private Integer isNew;

    /**
     * 是否是top50小区房源(0-否, 1-是)
     */
    @ApiModelProperty(value = "是否是top50小区房源(0-否, 1-是)", name = "buildArea")
    private Integer isCommunityTopHouse;

    /**
     * 在同商圈同户型范围内做低价排名
     */
    @ApiModelProperty(value = "在同商圈同户型范围内做低价排名", name = "buildArea")
    private Integer rankLowInBizcircleLayout;

    /**
     * 同小区同户型范围内做低价排名
     */
    @ApiModelProperty(value = "同小区同户型范围内做低价排名", name = "buildArea")
    private Integer rankInLowCommunityLayout;

    /**
     * 与小区平均单价的绝对值差
     */
    @ApiModelProperty(value = "与小区平均单价的绝对值差", name = "buildArea")
    private Double avgAbsoluteWithCommunity;

    /**
     * 与商圈平均单价的绝对值差
     */
    @ApiModelProperty(value = "与商圈平均单价的绝对值差", name = "buildArea")
    private Double avgAbsoluteWithBizcircle;

    /**
     * 与区县平均单价的绝对值差
     */
    @ApiModelProperty(value = "与区县平均单价的绝对值差", name = "buildArea")
    private Double avgAbsoluteWithDistrict;

    /**
     * 与小区平均单价的相对值(百分比)
     */
    @ApiModelProperty(value = "与小区平均单价的相对值(百分比)", name = "buildArea")
    private Double avgRelativeWithCommunity;

    /**
     * 与商圈平均单价的相对值(百分比)
     */
    @ApiModelProperty(value = "与商圈平均单价的相对值(百分比)", name = "buildArea")
    private Double avgRelativeWithBizcircle;

    /**
     * 与区县平均单价的相对值(百分比)
     */
    @ApiModelProperty(value = "与区县平均单价的相对值(百分比)", name = "buildArea")
    private Double avgRelativeWithDistrict;

    /**
     * 与小区平均总价的绝对值差
     */
    @ApiModelProperty(value = "与小区平均总价的绝对值差", name = "buildArea")
    private Double totalAbsoluteWithCommunity;

    /**
     * 与商圈平均总价的绝对值差
     */
    @ApiModelProperty(value = "与商圈平均总价的绝对值差", name = "buildArea")
    private Double totalAbsoluteWithBizcircle;

    /**
     * 与区县平均总价的绝对值差
     */
    @ApiModelProperty(value = "与区县平均总价的绝对值差", name = "buildArea")
    private Double totalAbsoluteWithDistrict;

    /**
     * 与小区平均总价的相对值(百分比)
     */
    @ApiModelProperty(value = "与小区平均总价的相对值(百分比)", name = "buildArea")
    private Double totalRelativeWithCommunity;

    /**
     * 与商圈平均总价的相对值(百分比)
     */
    @ApiModelProperty(value = "与商圈平均总价的相对值(百分比)", name = "buildArea")
    private Double totalRelativeWithBizcircle;

    /**
     * 与区县平均总价的相对值(百分比)
     */
    @ApiModelProperty(value = "与区县平均总价的相对值(百分比)", name = "buildArea")
    private Double totalRelativeWithDistrict;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "buildArea")
    private String updateTime;

    /**
     * 经纪人信息
     */
    @ApiModelProperty(value = "经纪人信息", name = "buildArea")
    @ChangeName("agent")
    private AgentBaseDo agentBaseDo;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id", name = "buildArea")
    private Integer userId;

    /**
     * 是否认领(0-否,1-是)
    */
    @ApiModelProperty(value = "是否认领(0-否,1-是)", name = "buildArea")
    private Integer isClaim;

    /**
     * 房源均价
     */
    @ApiModelProperty(value = "房源均价", name = "buildArea")
    private Double houseUnitCost;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称", name = "buildArea")
    @ChangeName("districtName")
    private String area;

    /**
     * 商圈名称
     */
    @ApiModelProperty(value = "商圈名称", name = "buildArea")
    @ChangeName("areaName")
    private String houseBusinessName;

    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称", name = "buildArea")
    @ChangeName("buildingName")
    private String plotName;

    /**
     * 小区id
     */
    @ApiModelProperty(value = "小区id", name = "buildArea")
    @ChangeName("buildingId")
    private Integer newcode;

    /**
     * 平均成交天数
     */
    @ApiModelProperty(value = "平均成交天数", name = "buildArea")
    private Integer avgDealCycle;

    /**
     * 房源标题
     */
    @ApiModelProperty(value = "房源标题", name = "buildArea")
    private String houseTitle;

    /**
     * 各个类型数量
     */
    @ApiModelProperty(value = "各个类型数量", name = "buildArea")
    private Map<Integer,Map<String,Integer>> typeCounts;

    private List recommendBuildTagsName;

    private List recommendBuildTagsId;

    /**
     * 近公园
     */
    @ApiModelProperty(value = "近公园", name = "buildArea")
    private String nearPark;
    /**
     * 楼盘专家
     */
    @ApiModelProperty(value = "楼盘专家", name = "buildArea")
    private String projExpertUserId;

    /**
     * 房源导入时间
     */
    @ApiModelProperty(value = "房源导入时间", name = "buildArea")
    private String importTime;

    /**
     * 是否显示默认图片标志
     */
    @ApiModelProperty(value = "是否显示默认图片标志", name = "isDefaultImage")
    private Integer isDefaultImage = 0;
}
