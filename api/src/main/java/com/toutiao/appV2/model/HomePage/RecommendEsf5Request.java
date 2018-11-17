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
 * RecommendEsf5Request
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T09:20:13.068Z")

public class RecommendEsf5Request   {
  @JsonProperty("areaId")
  private Integer areaId = null;

  @JsonProperty("beginArea")
  private Double beginArea = null;

  @JsonProperty("beginPrice")
  private Double beginPrice = null;

  @JsonProperty("cityId")
  private Integer cityId = null;

  @JsonProperty("districtId")
  private Integer districtId = null;

  @JsonProperty("districtIds")
  @Valid
  private List<Integer> districtIds = null;

  @JsonProperty("endArea")
  private Double endArea = null;

  @JsonProperty("endPrice")
  private Double endPrice = null;

  @JsonProperty("forwardId")
  @Valid
  private List<Integer> forwardId = null;

  @JsonProperty("houseYearId")
  private String houseYearId = null;

  @JsonProperty("keyword")
  private String keyword = null;

  @JsonProperty("labelId")
  @Valid
  private List<Integer> labelId = null;

  @JsonProperty("layoutId")
  @Valid
  private List<Integer> layoutId = null;

  @JsonProperty("pageNum")
  private Integer pageNum = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("sort")
  private Integer sort = null;

  @JsonProperty("sortFile")
  private String sortFile = null;

  @JsonProperty("subwayLineId")
  private Integer subwayLineId = null;

  @JsonProperty("subwayStationId")
  private Integer subwayStationId = null;

  public RecommendEsf5Request areaId(Integer areaId) {
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

  public RecommendEsf5Request beginArea(Double beginArea) {
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

  public RecommendEsf5Request beginPrice(Double beginPrice) {
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

  public RecommendEsf5Request cityId(Integer cityId) {
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

  public RecommendEsf5Request districtId(Integer districtId) {
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

  public RecommendEsf5Request districtIds(List<Integer> districtIds) {
    this.districtIds = districtIds;
    return this;
  }

  public RecommendEsf5Request addDistrictIdsItem(Integer districtIdsItem) {
    if (this.districtIds == null) {
      this.districtIds = new ArrayList<Integer>();
    }
    this.districtIds.add(districtIdsItem);
    return this;
  }

  /**
   * Get districtIds
   * @return districtIds
  **/
  @ApiModelProperty(value = "")


  public List<Integer> getDistrictIds() {
    return districtIds;
  }

  public void setDistrictIds(List<Integer> districtIds) {
    this.districtIds = districtIds;
  }

  public RecommendEsf5Request endArea(Double endArea) {
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

  public RecommendEsf5Request endPrice(Double endPrice) {
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

  public RecommendEsf5Request forwardId(List<Integer> forwardId) {
    this.forwardId = forwardId;
    return this;
  }

  public RecommendEsf5Request addForwardIdItem(Integer forwardIdItem) {
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

  public RecommendEsf5Request houseYearId(String houseYearId) {
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

  public RecommendEsf5Request keyword(String keyword) {
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

  public RecommendEsf5Request labelId(List<Integer> labelId) {
    this.labelId = labelId;
    return this;
  }

  public RecommendEsf5Request addLabelIdItem(Integer labelIdItem) {
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

  public RecommendEsf5Request layoutId(List<Integer> layoutId) {
    this.layoutId = layoutId;
    return this;
  }

  public RecommendEsf5Request addLayoutIdItem(Integer layoutIdItem) {
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

  public RecommendEsf5Request pageNum(Integer pageNum) {
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

  public RecommendEsf5Request pageSize(Integer pageSize) {
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

  public RecommendEsf5Request sort(Integer sort) {
    this.sort = sort;
    return this;
  }

  /**
   * Get sort
   * @return sort
  **/
  @ApiModelProperty(value = "")


  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

  public RecommendEsf5Request sortFile(String sortFile) {
    this.sortFile = sortFile;
    return this;
  }

  /**
   * Get sortFile
   * @return sortFile
  **/
  @ApiModelProperty(value = "")


  public String getSortFile() {
    return sortFile;
  }

  public void setSortFile(String sortFile) {
    this.sortFile = sortFile;
  }

  public RecommendEsf5Request subwayLineId(Integer subwayLineId) {
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

  public RecommendEsf5Request subwayStationId(Integer subwayStationId) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecommendEsf5Request recommendEsf5Request = (RecommendEsf5Request) o;
    return Objects.equals(this.areaId, recommendEsf5Request.areaId) &&
        Objects.equals(this.beginArea, recommendEsf5Request.beginArea) &&
        Objects.equals(this.beginPrice, recommendEsf5Request.beginPrice) &&
        Objects.equals(this.cityId, recommendEsf5Request.cityId) &&
        Objects.equals(this.districtId, recommendEsf5Request.districtId) &&
        Objects.equals(this.districtIds, recommendEsf5Request.districtIds) &&
        Objects.equals(this.endArea, recommendEsf5Request.endArea) &&
        Objects.equals(this.endPrice, recommendEsf5Request.endPrice) &&
        Objects.equals(this.forwardId, recommendEsf5Request.forwardId) &&
        Objects.equals(this.houseYearId, recommendEsf5Request.houseYearId) &&
        Objects.equals(this.keyword, recommendEsf5Request.keyword) &&
        Objects.equals(this.labelId, recommendEsf5Request.labelId) &&
        Objects.equals(this.layoutId, recommendEsf5Request.layoutId) &&
        Objects.equals(this.pageNum, recommendEsf5Request.pageNum) &&
        Objects.equals(this.pageSize, recommendEsf5Request.pageSize) &&
        Objects.equals(this.sort, recommendEsf5Request.sort) &&
        Objects.equals(this.sortFile, recommendEsf5Request.sortFile) &&
        Objects.equals(this.subwayLineId, recommendEsf5Request.subwayLineId) &&
        Objects.equals(this.subwayStationId, recommendEsf5Request.subwayStationId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(areaId, beginArea, beginPrice, cityId, districtId, districtIds, endArea, endPrice, forwardId, houseYearId, keyword, labelId, layoutId, pageNum, pageSize, sort, sortFile, subwayLineId, subwayStationId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecommendEsf5Request {\n");
    
    sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
    sb.append("    beginArea: ").append(toIndentedString(beginArea)).append("\n");
    sb.append("    beginPrice: ").append(toIndentedString(beginPrice)).append("\n");
    sb.append("    cityId: ").append(toIndentedString(cityId)).append("\n");
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
    sb.append("    districtIds: ").append(toIndentedString(districtIds)).append("\n");
    sb.append("    endArea: ").append(toIndentedString(endArea)).append("\n");
    sb.append("    endPrice: ").append(toIndentedString(endPrice)).append("\n");
    sb.append("    forwardId: ").append(toIndentedString(forwardId)).append("\n");
    sb.append("    houseYearId: ").append(toIndentedString(houseYearId)).append("\n");
    sb.append("    keyword: ").append(toIndentedString(keyword)).append("\n");
    sb.append("    labelId: ").append(toIndentedString(labelId)).append("\n");
    sb.append("    layoutId: ").append(toIndentedString(layoutId)).append("\n");
    sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    sortFile: ").append(toIndentedString(sortFile)).append("\n");
    sb.append("    subwayLineId: ").append(toIndentedString(subwayLineId)).append("\n");
    sb.append("    subwayStationId: ").append(toIndentedString(subwayStationId)).append("\n");
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

