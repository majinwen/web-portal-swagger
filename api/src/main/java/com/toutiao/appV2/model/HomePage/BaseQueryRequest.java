package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BaseQueryRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:33:40.959Z")

public class BaseQueryRequest   {
  @JsonProperty("beginPrice")
  @ApiModelProperty(value = "起始价格(必传)", name = "beginPrice", required = true)
  private Double beginPrice = null;

  @JsonProperty("endPrice")
  @ApiModelProperty(value = "结束价格(必传)", name = "endPrice", required = true)
  private Double endPrice = null;

  @JsonProperty("beginArea")
  @ApiModelProperty(value = "最小建筑面积(必传)", name = "beginArea", required = true)
  private Double beginArea = null;

  @JsonProperty("endArea")
  @ApiModelProperty(value = "最大建筑面积(必传)", name = "endArea", required = true)
  private Double endArea = null;

  @JsonProperty("layoutId")
  @Valid
  @ApiModelProperty(value = "户型集", name = "layoutId", required = false)
  private List<Integer> layoutId = null;

  @JsonProperty("forwardId")
  @Valid
  @ApiModelProperty(value = "朝向集", name = "forwardId", required = false)
  private List<Integer> forwardId = null;

  @JsonProperty("houseYearId")
  @ApiModelProperty(value = "楼龄", name = "houseYearId", required = false)
  private String houseYearId = null;

  @JsonProperty("labelId")
  @Valid
  @ApiModelProperty(value = "标签集", name = "labelId", required = false)
  private List<Integer> labelId = null;

  @JsonProperty("districtId")
  @ApiModelProperty(value = "区域id", name = "districtId", required = false)
  private Integer districtId = null;

  @JsonProperty("areaId")
  @ApiModelProperty(value = "商圈id", name = "areaId", required = false)
  private Integer areaId = null;

  @JsonProperty("subwayLineId")
  @ApiModelProperty(value = "地铁线id", name = "subwayLineId", required = false)
  private Integer subwayLineId = null;

  @JsonProperty("subwayStationId")
  @ApiModelProperty(value = "地铁站id", name = "subwayStationId", required = false)
  private Integer subwayStationId = null;

  @JsonProperty("keyword")
  @ApiModelProperty(value = "关键词", name = "keyword", required = false)
  private String keyword = null;

  @JsonProperty("pageNum")
  @ApiModelProperty(value = "页数", name = "pageNum", required = false)
  private Integer pageNum = 1;

  @JsonProperty("pageSize")
  @ApiModelProperty(value = "每页数量", name = "pageSize", required = false)
  private Integer pageSize = 10;

  @JsonProperty("cityId")
  @ApiModelProperty(value = "城市id", name = "cityId", required = false)
  private Integer cityId = null;

  public BaseQueryRequest beginPrice(Double beginPrice) {
    this.beginPrice = beginPrice;
    return this;
  }

  /**
   * Get beginPrice
   * @return beginPrice
  **/
  @ApiModelProperty(value = "")


  public Double getBeginPrice() {
    return beginPrice;
  }

  public void setBeginPrice(Double beginPrice) {
    this.beginPrice = beginPrice;
  }

  public BaseQueryRequest endPrice(Double endPrice) {
    this.endPrice = endPrice;
    return this;
  }

  /**
   * Get endPrice
   * @return endPrice
  **/
  @ApiModelProperty(value = "")


  public Double getEndPrice() {
    return endPrice;
  }

  public void setEndPrice(Double endPrice) {
    this.endPrice = endPrice;
  }

  public BaseQueryRequest beginArea(Double beginArea) {
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

  public BaseQueryRequest endArea(Double endArea) {
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

  public BaseQueryRequest layoutId(List<Integer> layoutId) {
    this.layoutId = layoutId;
    return this;
  }

  public BaseQueryRequest addLayoutIdItem(Integer layoutIdItem) {
    if (this.layoutId == null) {
      this.layoutId = new ArrayList<Integer>();
    }
    this.layoutId.add(layoutIdItem);
    return this;
  }

  /**
   * Get layoutId
   * @return layoutId
  **/
  @ApiModelProperty(value = "")


  public List<Integer> getLayoutId() {
    return layoutId;
  }

  public void setLayoutId(List<Integer> layoutId) {
    this.layoutId = layoutId;
  }

  public BaseQueryRequest forwardId(List<Integer> forwardId) {
    this.forwardId = forwardId;
    return this;
  }

  public BaseQueryRequest addForwardIdItem(Integer forwardIdItem) {
    if (this.forwardId == null) {
      this.forwardId = new ArrayList<Integer>();
    }
    this.forwardId.add(forwardIdItem);
    return this;
  }

  /**
   * Get forwardId
   * @return forwardId
  **/
  @ApiModelProperty(value = "")


  public List<Integer> getForwardId() {
    return forwardId;
  }

  public void setForwardId(List<Integer> forwardId) {
    this.forwardId = forwardId;
  }

  public BaseQueryRequest houseYearId(String houseYearId) {
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

  public BaseQueryRequest labelId(List<Integer> labelId) {
    this.labelId = labelId;
    return this;
  }

  public BaseQueryRequest addLabelIdItem(Integer labelIdItem) {
    if (this.labelId == null) {
      this.labelId = new ArrayList<Integer>();
    }
    this.labelId.add(labelIdItem);
    return this;
  }

  /**
   * Get labelId
   * @return labelId
  **/
  @ApiModelProperty(value = "")


  public List<Integer> getLabelId() {
    return labelId;
  }

  public void setLabelId(List<Integer> labelId) {
    this.labelId = labelId;
  }

  public BaseQueryRequest districtId(Integer districtId) {
    this.districtId = districtId;
    return this;
  }

  /**
   * Get districtId
   * @return districtId
  **/
  @ApiModelProperty(value = "")


  public Integer getDistrictId() {
    return districtId;
  }

  public void setDistrictId(Integer districtId) {
    this.districtId = districtId;
  }

  public BaseQueryRequest areaId(Integer areaId) {
    this.areaId = areaId;
    return this;
  }

  /**
   * Get areaId
   * @return areaId
  **/
  @ApiModelProperty(value = "")


  public Integer getAreaId() {
    return areaId;
  }

  public void setAreaId(Integer areaId) {
    this.areaId = areaId;
  }

  public BaseQueryRequest subwayLineId(Integer subwayLineId) {
    this.subwayLineId = subwayLineId;
    return this;
  }

  /**
   * Get subwayLineId
   * @return subwayLineId
  **/
  @ApiModelProperty(value = "")


  public Integer getSubwayLineId() {
    return subwayLineId;
  }

  public void setSubwayLineId(Integer subwayLineId) {
    this.subwayLineId = subwayLineId;
  }

  public BaseQueryRequest subwayStationId(Integer subwayStationId) {
    this.subwayStationId = subwayStationId;
    return this;
  }

  /**
   * Get subwayStationId
   * @return subwayStationId
  **/
  @ApiModelProperty(value = "")


  public Integer getSubwayStationId() {
    return subwayStationId;
  }

  public void setSubwayStationId(Integer subwayStationId) {
    this.subwayStationId = subwayStationId;
  }

  public BaseQueryRequest keyword(String keyword) {
    this.keyword = keyword;
    return this;
  }

  /**
   * Get keyword
   * @return keyword
  **/
  @ApiModelProperty(value = "")


  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public BaseQueryRequest pageNum(Integer pageNum) {
    this.pageNum = pageNum;
    return this;
  }

  /**
   * Get pageNum
   * @return pageNum
  **/
  @ApiModelProperty(value = "")


  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public BaseQueryRequest pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * Get pageSize
   * @return pageSize
  **/
  @ApiModelProperty(value = "")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public BaseQueryRequest cityId(Integer cityId) {
    this.cityId = cityId;
    return this;
  }

  /**
   * Get cityId
   * @return cityId
  **/
  @ApiModelProperty(value = "")


  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseQueryRequest baseQueryRequest = (BaseQueryRequest) o;
    return Objects.equals(this.beginPrice, baseQueryRequest.beginPrice) &&
        Objects.equals(this.endPrice, baseQueryRequest.endPrice) &&
        Objects.equals(this.beginArea, baseQueryRequest.beginArea) &&
        Objects.equals(this.endArea, baseQueryRequest.endArea) &&
        Objects.equals(this.layoutId, baseQueryRequest.layoutId) &&
        Objects.equals(this.forwardId, baseQueryRequest.forwardId) &&
        Objects.equals(this.houseYearId, baseQueryRequest.houseYearId) &&
        Objects.equals(this.labelId, baseQueryRequest.labelId) &&
        Objects.equals(this.districtId, baseQueryRequest.districtId) &&
        Objects.equals(this.areaId, baseQueryRequest.areaId) &&
        Objects.equals(this.subwayLineId, baseQueryRequest.subwayLineId) &&
        Objects.equals(this.subwayStationId, baseQueryRequest.subwayStationId) &&
        Objects.equals(this.keyword, baseQueryRequest.keyword) &&
        Objects.equals(this.pageNum, baseQueryRequest.pageNum) &&
        Objects.equals(this.pageSize, baseQueryRequest.pageSize) &&
        Objects.equals(this.cityId, baseQueryRequest.cityId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beginPrice, endPrice, beginArea, endArea, layoutId, forwardId, houseYearId, labelId, districtId, areaId, subwayLineId, subwayStationId, keyword, pageNum, pageSize, cityId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BaseQueryRequest {\n");
    
    sb.append("    beginPrice: ").append(toIndentedString(beginPrice)).append("\n");
    sb.append("    endPrice: ").append(toIndentedString(endPrice)).append("\n");
    sb.append("    beginArea: ").append(toIndentedString(beginArea)).append("\n");
    sb.append("    endArea: ").append(toIndentedString(endArea)).append("\n");
    sb.append("    layoutId: ").append(toIndentedString(layoutId)).append("\n");
    sb.append("    forwardId: ").append(toIndentedString(forwardId)).append("\n");
    sb.append("    houseYearId: ").append(toIndentedString(houseYearId)).append("\n");
    sb.append("    labelId: ").append(toIndentedString(labelId)).append("\n");
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
    sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
    sb.append("    subwayLineId: ").append(toIndentedString(subwayLineId)).append("\n");
    sb.append("    subwayStationId: ").append(toIndentedString(subwayStationId)).append("\n");
    sb.append("    keyword: ").append(toIndentedString(keyword)).append("\n");
    sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    cityId: ").append(toIndentedString(cityId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

