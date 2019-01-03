package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.sellhouse.HouseLable;
import com.toutiao.app.domain.sellhouse.HouseSubject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * PlotEsfResponse
 */
@Data
public class PlotEsfResponse {

    @JsonProperty("agentBaseDo")
    private AgentBaseDo agentBaseDo;

    @JsonProperty("area")
    @ApiModelProperty("区域名称")
    private String area;

    @JsonProperty("areaId")
    @ApiModelProperty("区域编号")
    private Integer areaId;

    @JsonProperty("与商圈平均单价的绝对值差")
    @ApiModelProperty("区域编号")
    private Double avgAbsoluteWithBizcircle;

    @JsonProperty("avgAbsoluteWithCommunity")
    @ApiModelProperty("与小区平均单价的绝对值差")
    private Double avgAbsoluteWithCommunity;

    @JsonProperty("avgAbsoluteWithDistrict")
    @ApiModelProperty("与区县平均单价的绝对值差")
    private Double avgAbsoluteWithDistrict;

    @JsonProperty("avgDealCycle")
    @ApiModelProperty("平均成交天数")
    private Integer avgDealCycle;

    @JsonProperty("avgRelativeWithBizcircle")
    @ApiModelProperty("与商圈平均单价的相对值(百分比)")
    private Double avgRelativeWithBizcircle;

    @JsonProperty("avgRelativeWithCommunity")
    @ApiModelProperty("与小区平均单价的相对值(百分比)")
    private Double avgRelativeWithCommunity;

    @JsonProperty("avgRelativeWithDistrict")
    @ApiModelProperty("与区县平均单价的相对值(百分比)")
    private Double avgRelativeWithDistrict;

    @JsonProperty("buildArea")
    @ApiModelProperty("建筑面积")
    private Double buildArea;

    @JsonProperty("forwardName")
    @ApiModelProperty("朝向")
    private String forwardName;

    @JsonProperty("hall")
    @ApiModelProperty("厅")
    private Integer hall;

    @JsonProperty("houseBusinessName")
    @ApiModelProperty("商圈名称")
    private String houseBusinessName;

    @JsonProperty("houseBusinessNameId")
    @ApiModelProperty("商圈id")
    private String houseBusinessNameId;

    @JsonProperty("houseId")
    @ApiModelProperty("二手房房源id")
    private String houseId;

    @JsonProperty("housePhotoTitle")
    @ApiModelProperty("标题图")
    private String housePhotoTitle;

    @JsonProperty("houseTitle")
    @ApiModelProperty("房源标题")
    private String houseTitle;

    @JsonProperty("houseTotalPrices")
    @ApiModelProperty("房源总价")
    private Double houseTotalPrices;

    @JsonProperty("houseUnitCost")
    @ApiModelProperty("单价")
    private Double houseUnitCost;

    @JsonProperty("importTime")
    @ApiModelProperty("房源导入时间")
    private String importTime;

    @JsonProperty("isCommunityTopHouse")
    @ApiModelProperty("是否是top50小区房源(0-否，1-是)")
    private Integer isCommunityTopHouse;

    @JsonProperty("isCutPrice")
    @ApiModelProperty("是否降价房(0-否，1-降价房，2-涨价房)")
    private Integer isCutPrice;

    @JsonProperty("isDealLayout")
    @ApiModelProperty("是否成交户型(0-否，1-是)")
    private Integer isDealLayout;

    @JsonProperty("isDefaultImage")
    @ApiModelProperty("是否显示默认图片标志")
    private Integer isDefaultImage;

    @JsonProperty("isLowPrice")
    @ApiModelProperty("是否价格洼地(0-否，1-是)")
    private Integer isLowPrice;

    @JsonProperty("isLowest")
    @ApiModelProperty("是否同户型小区均价最低(0-否，1-是)")
    private Integer isLowest;

    @JsonProperty("isMainLayout")
    @ApiModelProperty("是否主力户型(0-否，1-是)")
    private Integer isMainLayout;

    @JsonProperty("isMustRob")
    @ApiModelProperty("是否逢出必抢房(0-否，1-是)")
    private Integer isMustRob;

    @JsonProperty("isNew")
    @ApiModelProperty("是否新导入房源(0-否，1-是)")
    private Integer isNew;

    @JsonProperty("nearPark")
    @ApiModelProperty("近公园")
    private String nearPark;

    @JsonProperty("楼盘ID(楼盘/小区)")
    @ApiModelProperty("近公园")
    private Integer newCode;

    @JsonProperty("plotName")
    @ApiModelProperty("小区名称")
    private String plotName;

    /**
     * 楼盘名称
     */
    @ApiModelProperty(value = "楼盘名称", name = "plotNameAccurate")
    private String plotNameAccurate;

    @JsonProperty("priceFloat")
    @ApiModelProperty("浮动价格")
    private Double priceFloat;

    @JsonProperty("rankInLowCommunityLayout")
    @ApiModelProperty("同小区同户型范围内做低价排名")
    private Integer rankInLowCommunityLayout;

    @JsonProperty("rankLowInBizcircleLayout")
    @ApiModelProperty("在同商圈同户型范围内做低价排名")
    private Integer rankLowInBizcircleLayout;

    @JsonProperty("recommendBuildTagsId")
    @Valid
    @ApiModelProperty("推荐标签id")
    private List<Object> recommendBuildTagsId;

    @JsonProperty("recommendBuildTagsName")
    @Valid
    @ApiModelProperty("推荐标签名称")
    private List<Object> recommendBuildTagsName;

    @JsonProperty("room")
    @ApiModelProperty("室")
    private Integer room;

    @JsonProperty("tagsName")
    @Valid
    @ApiModelProperty("标签名称")
    private List<String> tagsName;

    @JsonProperty("totalAbsoluteWithBizcircle")
    @ApiModelProperty("与商圈平均总价的绝对值差")
    private Double totalAbsoluteWithBizcircle;

    @JsonProperty("totalAbsoluteWithCommunity")
    @ApiModelProperty("与小区平均总价的绝对值差")
    private Double totalAbsoluteWithCommunity;

    @JsonProperty("totalAbsoluteWithDistrict")
    @ApiModelProperty("与区县平均总价的绝对值差")
    private Double totalAbsoluteWithDistrict;

    @JsonProperty("totalRelativeWithBizcircle")
    @ApiModelProperty("与商圈平均总价的相对值(百分比)")
    private Double totalRelativeWithBizcircle;

    @JsonProperty("totalRelativeWithCommunity")
    @ApiModelProperty("与小区平均总价的相对值(百分比)")
    private Double totalRelativeWithCommunity;

    @JsonProperty("totalRelativeWithDistrict")
    @ApiModelProperty("与区县平均总价的相对值(百分比)")
    private Double totalRelativeWithDistrict;

    @JsonProperty("typeCounts")
    @ApiModelProperty("各个类型数量")
    private Map<String, Map<String, Integer>> typeCounts;

    @ApiModelProperty(value = "专题列表", name = "houseSubjectList")
    private List<HouseSubject> houseSubjectList;

    @ApiModelProperty(value = "公司图标", name = "companyIcon")
    private String companyIcon;

    @ApiModelProperty(value = "附近位置", name = "nearbyDistance")
    private String nearbyDistance;

    @ApiModelProperty(value = "房源标签", name = "houseLableList")
    private List<HouseLable> houseLableList;


}

