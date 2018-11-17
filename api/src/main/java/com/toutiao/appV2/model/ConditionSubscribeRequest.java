package com.toutiao.appV2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ConditionSubscribeRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:27:32.320Z")
@ApiModel(value = "ConditionSubscribeRequest", description = "条件订阅参数")
public class ConditionSubscribeRequest   {
    @JsonProperty("areaId")
    @ApiModelProperty(value = "商圈Id", name = "areaId")
    private String areaId = null;

    @JsonProperty("areaName")
    @ApiModelProperty(value = "商圈名称", name = "areaName")
    private String areaName = null;

    @JsonProperty("beginArea")
    @ApiModelProperty(value = "最小建筑面积", name = "beginArea", required = false)
    private Double beginArea = null;

    @JsonProperty("beginPrice")
    @ApiModelProperty(value = "起始价格", name = "beginPrice", required = false)
    private Integer beginPrice = null;

    @JsonProperty("districtId")
    @ApiModelProperty(value = "区域id", name = "districtId", required = false)
    private String districtId = null;

    @JsonProperty("districtName")
    @ApiModelProperty(value = "区域名称", name = "districtName", required = false)
    private String districtName = null;

    @JsonProperty("endArea")
    @ApiModelProperty(value = "最大建筑面积", name = "endArea", required = false)
    private Double endArea = null;

    @JsonProperty("endPrice")
    @ApiModelProperty(value = "结束价格", name = "endPrice", required = false)
    private Integer endPrice = null;

    @JsonProperty("forwardId")
    @ApiModelProperty(value = "朝向", name = "forwardId", required = false)
    private String forwardId = null;

    @JsonProperty("houseYearId")
    @ApiModelProperty(value = "楼龄[0-5]", name = "houseYearId", required = false)
    private String houseYearId = null;

    @JsonProperty("isCutPrice")
    @ApiModelProperty(value = "标签-降价房", name = "isCutPrice", required = false)
    private Integer isCutPrice = null;

    @JsonProperty("isLowPrice")
    @ApiModelProperty(value = "标签-抢手", name = "isLowPrice", required = false)
    private Integer isLowPrice = null;

    @JsonProperty("isMustRob")
    @ApiModelProperty(value = "标签-逢出毕抢", name = "isMustRob", required = false)
    private Integer isMustRob = null;

    @JsonProperty("labelId")
    @ApiModelProperty(value = "标签", name = "labelId", required = false)
    private String labelId = null;

    @JsonProperty("layoutId")
    @ApiModelProperty(value = "户型", name = "layoutId", required = false)
    private String layoutId = null;

    @JsonProperty("subwayLineId")
    @ApiModelProperty(value = "地铁线id", name = "subwayLineId", required = false)
    private String subwayLineId = null;

    @JsonProperty("subwayLineName")
    @ApiModelProperty(value = "地铁线", name = "subwayLineName", required = false)
    private String subwayLineName = null;

    @JsonProperty("subwayStationId")
    @ApiModelProperty(value = "地铁站id", name = "subwayStationId", required = false)
    private String subwayStationId = null;

    @JsonProperty("subwayStationName")
    @ApiModelProperty(value = "地铁站", name = "subwayStationName", required = false)
    private String subwayStationName = null;

    public ConditionSubscribeRequest areaId(String areaId) {
        this.areaId = areaId;
        return this;
    }

    /**
     * Get areaId
     * @return areaId
     **/
    @ApiModelProperty(value = "")


    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public ConditionSubscribeRequest areaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    /**
     * Get areaName
     * @return areaName
     **/
    @ApiModelProperty(value = "")


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public ConditionSubscribeRequest beginArea(Double beginArea) {
        this.beginArea = beginArea;
        return this;
    }

    /**
     * Get beginArea
     * @return beginArea
     **/
    @ApiModelProperty(value = "")


    public Double getBeginArea() {
        return beginArea;
    }

    public void setBeginArea(Double beginArea) {
        this.beginArea = beginArea;
    }

    public ConditionSubscribeRequest beginPrice(Integer beginPrice) {
        this.beginPrice = beginPrice;
        return this;
    }

    /**
     * Get beginPrice
     * @return beginPrice
     **/
    @ApiModelProperty(value = "")


    public Integer getBeginPrice() {
        return beginPrice;
    }

    public void setBeginPrice(Integer beginPrice) {
        this.beginPrice = beginPrice;
    }

    public ConditionSubscribeRequest districtId(String districtId) {
        this.districtId = districtId;
        return this;
    }

    /**
     * Get districtId
     * @return districtId
     **/
    @ApiModelProperty(value = "")


    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public ConditionSubscribeRequest districtName(String districtName) {
        this.districtName = districtName;
        return this;
    }

    /**
     * Get districtName
     * @return districtName
     **/
    @ApiModelProperty(value = "")


    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public ConditionSubscribeRequest endArea(Double endArea) {
        this.endArea = endArea;
        return this;
    }

    /**
     * Get endArea
     * @return endArea
     **/
    @ApiModelProperty(value = "")


    public Double getEndArea() {
        return endArea;
    }

    public void setEndArea(Double endArea) {
        this.endArea = endArea;
    }

    public ConditionSubscribeRequest endPrice(Integer endPrice) {
        this.endPrice = endPrice;
        return this;
    }

    /**
     * Get endPrice
     * @return endPrice
     **/
    @ApiModelProperty(value = "")


    public Integer getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Integer endPrice) {
        this.endPrice = endPrice;
    }

    public ConditionSubscribeRequest forwardId(String forwardId) {
        this.forwardId = forwardId;
        return this;
    }

    /**
     * Get forwardId
     * @return forwardId
     **/
    @ApiModelProperty(value = "")


    public String getForwardId() {
        return forwardId;
    }

    public void setForwardId(String forwardId) {
        this.forwardId = forwardId;
    }

    public ConditionSubscribeRequest houseYearId(String houseYearId) {
        this.houseYearId = houseYearId;
        return this;
    }

    /**
     * Get houseYearId
     * @return houseYearId
     **/
    @ApiModelProperty(value = "")


    public String getHouseYearId() {
        return houseYearId;
    }

    public void setHouseYearId(String houseYearId) {
        this.houseYearId = houseYearId;
    }

    public ConditionSubscribeRequest isCutPrice(Integer isCutPrice) {
        this.isCutPrice = isCutPrice;
        return this;
    }

    /**
     * Get isCutPrice
     * @return isCutPrice
     **/
    @ApiModelProperty(value = "")


    public Integer getIsCutPrice() {
        return isCutPrice;
    }

    public void setIsCutPrice(Integer isCutPrice) {
        this.isCutPrice = isCutPrice;
    }

    public ConditionSubscribeRequest isLowPrice(Integer isLowPrice) {
        this.isLowPrice = isLowPrice;
        return this;
    }

    /**
     * Get isLowPrice
     * @return isLowPrice
     **/
    @ApiModelProperty(value = "")


    public Integer getIsLowPrice() {
        return isLowPrice;
    }

    public void setIsLowPrice(Integer isLowPrice) {
        this.isLowPrice = isLowPrice;
    }

    public ConditionSubscribeRequest isMustRob(Integer isMustRob) {
        this.isMustRob = isMustRob;
        return this;
    }

    /**
     * Get isMustRob
     * @return isMustRob
     **/
    @ApiModelProperty(value = "")


    public Integer getIsMustRob() {
        return isMustRob;
    }

    public void setIsMustRob(Integer isMustRob) {
        this.isMustRob = isMustRob;
    }

    public ConditionSubscribeRequest labelId(String labelId) {
        this.labelId = labelId;
        return this;
    }

    /**
     * Get labelId
     * @return labelId
     **/
    @ApiModelProperty(value = "")


    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public ConditionSubscribeRequest layoutId(String layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    /**
     * Get layoutId
     * @return layoutId
     **/
    @ApiModelProperty(value = "")


    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    public ConditionSubscribeRequest subwayLineId(String subwayLineId) {
        this.subwayLineId = subwayLineId;
        return this;
    }

    /**
     * Get subwayLineId
     * @return subwayLineId
     **/
    @ApiModelProperty(value = "")


    public String getSubwayLineId() {
        return subwayLineId;
    }

    public void setSubwayLineId(String subwayLineId) {
        this.subwayLineId = subwayLineId;
    }

    public ConditionSubscribeRequest subwayLineName(String subwayLineName) {
        this.subwayLineName = subwayLineName;
        return this;
    }

    /**
     * Get subwayLineName
     * @return subwayLineName
     **/
    @ApiModelProperty(value = "")


    public String getSubwayLineName() {
        return subwayLineName;
    }

    public void setSubwayLineName(String subwayLineName) {
        this.subwayLineName = subwayLineName;
    }

    public ConditionSubscribeRequest subwayStationId(String subwayStationId) {
        this.subwayStationId = subwayStationId;
        return this;
    }

    /**
     * Get subwayStationId
     * @return subwayStationId
     **/
    @ApiModelProperty(value = "")


    public String getSubwayStationId() {
        return subwayStationId;
    }

    public void setSubwayStationId(String subwayStationId) {
        this.subwayStationId = subwayStationId;
    }

    public ConditionSubscribeRequest subwayStationName(String subwayStationName) {
        this.subwayStationName = subwayStationName;
        return this;
    }

    /**
     * Get subwayStationName
     * @return subwayStationName
     **/
    @ApiModelProperty(value = "")


    public String getSubwayStationName() {
        return subwayStationName;
    }

    public void setSubwayStationName(String subwayStationName) {
        this.subwayStationName = subwayStationName;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConditionSubscribeRequest conditionSubscribeRequest = (ConditionSubscribeRequest) o;
        return Objects.equals(this.areaId, conditionSubscribeRequest.areaId) &&
                Objects.equals(this.areaName, conditionSubscribeRequest.areaName) &&
                Objects.equals(this.beginArea, conditionSubscribeRequest.beginArea) &&
                Objects.equals(this.beginPrice, conditionSubscribeRequest.beginPrice) &&
                Objects.equals(this.districtId, conditionSubscribeRequest.districtId) &&
                Objects.equals(this.districtName, conditionSubscribeRequest.districtName) &&
                Objects.equals(this.endArea, conditionSubscribeRequest.endArea) &&
                Objects.equals(this.endPrice, conditionSubscribeRequest.endPrice) &&
                Objects.equals(this.forwardId, conditionSubscribeRequest.forwardId) &&
                Objects.equals(this.houseYearId, conditionSubscribeRequest.houseYearId) &&
                Objects.equals(this.isCutPrice, conditionSubscribeRequest.isCutPrice) &&
                Objects.equals(this.isLowPrice, conditionSubscribeRequest.isLowPrice) &&
                Objects.equals(this.isMustRob, conditionSubscribeRequest.isMustRob) &&
                Objects.equals(this.labelId, conditionSubscribeRequest.labelId) &&
                Objects.equals(this.layoutId, conditionSubscribeRequest.layoutId) &&
                Objects.equals(this.subwayLineId, conditionSubscribeRequest.subwayLineId) &&
                Objects.equals(this.subwayLineName, conditionSubscribeRequest.subwayLineName) &&
                Objects.equals(this.subwayStationId, conditionSubscribeRequest.subwayStationId) &&
                Objects.equals(this.subwayStationName, conditionSubscribeRequest.subwayStationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaId, areaName, beginArea, beginPrice, districtId, districtName, endArea, endPrice, forwardId, houseYearId, isCutPrice, isLowPrice, isMustRob, labelId, layoutId, subwayLineId, subwayLineName, subwayStationId, subwayStationName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConditionSubscribeRequest {\n");

        sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
        sb.append("    areaName: ").append(toIndentedString(areaName)).append("\n");
        sb.append("    beginArea: ").append(toIndentedString(beginArea)).append("\n");
        sb.append("    beginPrice: ").append(toIndentedString(beginPrice)).append("\n");
        sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
        sb.append("    districtName: ").append(toIndentedString(districtName)).append("\n");
        sb.append("    endArea: ").append(toIndentedString(endArea)).append("\n");
        sb.append("    endPrice: ").append(toIndentedString(endPrice)).append("\n");
        sb.append("    forwardId: ").append(toIndentedString(forwardId)).append("\n");
        sb.append("    houseYearId: ").append(toIndentedString(houseYearId)).append("\n");
        sb.append("    isCutPrice: ").append(toIndentedString(isCutPrice)).append("\n");
        sb.append("    isLowPrice: ").append(toIndentedString(isLowPrice)).append("\n");
        sb.append("    isMustRob: ").append(toIndentedString(isMustRob)).append("\n");
        sb.append("    labelId: ").append(toIndentedString(labelId)).append("\n");
        sb.append("    layoutId: ").append(toIndentedString(layoutId)).append("\n");
        sb.append("    subwayLineId: ").append(toIndentedString(subwayLineId)).append("\n");
        sb.append("    subwayLineName: ").append(toIndentedString(subwayLineName)).append("\n");
        sb.append("    subwayStationId: ").append(toIndentedString(subwayStationId)).append("\n");
        sb.append("    subwayStationName: ").append(toIndentedString(subwayStationName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

