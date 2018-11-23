package com.toutiao.appV2.model.rent;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.web.common.assertUtils.First;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

/**
 * RentHouseRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

public class RentHouseRequest   {
  @JsonProperty("areaId")
  @ApiParam("商圈id")
  private Integer[] areaId = null;

  @JsonProperty("beginArea")
  @ApiParam("起始面积")
  private double beginArea;

  @JsonProperty("beginPrice")
  @ApiParam("起始价格")
  private double beginPrice;

  @JsonProperty("cityId")
  @ApiParam("城市id")
  private Integer cityId = null;

  @JsonProperty("distance")
  @ApiParam("附近距离")
  private Integer distance = null;

  @JsonProperty("districtId")
  @ApiParam("区域")
  private Integer districtId = null;

  @JsonProperty("elo")
  @ApiParam("整租户型")
  private String elo = null;

  @JsonProperty("endArea")
  @ApiParam("结束面积")
  private double endArea;

  @JsonProperty("endPrice")
  @ApiParam("结束价格")
  private double endPrice;

  @JsonProperty("forwardId")
  @ApiParam("朝向")
  private List<Integer> forwardId = null;

  @JsonProperty("houseYearId")
  @ApiParam("楼龄[0-5]")
  private String houseYearId = null;

  @JsonProperty("jlo")
  @ApiParam("合租户型")
  private String jlo = null;

  @JsonProperty("keyword")
  @ApiParam("关键字")
  private String keyword = null;

  @JsonProperty("labelId")
  @ApiParam("标签")
  private List<Integer> labelId = null;

  @JsonProperty("lat")
  @ApiParam("维度")
  private Double lat = null;

  @JsonProperty("layoutId")
  @ApiParam("居室")
  private List<Integer> layoutId = null;

  @JsonProperty("lon")
  @ApiParam("经度")
  private Double lon = null;

  @JsonProperty("pageNum")
  @ApiParam(value = "页码", defaultValue = "1")
  private Integer pageNum = 1;

  @JsonProperty("pageSize")
  @ApiParam(value = "页容量", defaultValue = "10")
  private Integer pageSize = 10;

  @JsonProperty("rentType")
  @ApiParam("整租:1/合租:2/未知:3")
  private String rentType = null;

  @JsonProperty("source")
  @ApiParam("来源id")
  private String source = null;

  @JsonProperty("subwayLineId")
  @ApiParam("地铁线Id")
  private Integer subwayLineId = null;

  @JsonProperty("subwayStationId")
  @ApiParam("地铁站id")
  private Integer[] subwayStationId = null;

  @JsonProperty("uid")
  @ApiParam(value = "租房推优查询uid")
  private String uid = null;

  public RentHouseRequest areaId(Integer[] areaId) {
    this.areaId = areaId;
    return this;
  }

  /**
   * Get areaId
   * @return areaId
  **/
  @ApiModelProperty(value = "")


  public Integer[] getAreaId() {
    return areaId;
  }

  public void setAreaId(Integer[] areaId) {
    this.areaId = areaId;
  }

  public RentHouseRequest beginArea(Double beginArea) {
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

  public RentHouseRequest beginPrice(Double beginPrice) {
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

  public RentHouseRequest cityId(Integer cityId) {
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

  public RentHouseRequest distance(Integer distance) {
    this.distance = distance;
    return this;
  }

  /**
   * Get distance
   * @return distance
  **/
  @ApiModelProperty(value = "")


  public Integer getDistance() {
    return distance;
  }

  public void setDistance(Integer distance) {
    this.distance = distance;
  }

  public RentHouseRequest districtId(Integer districtId) {
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

  public RentHouseRequest elo(String elo) {
    this.elo = elo;
    return this;
  }

  /**
   * Get elo
   * @return elo
  **/
  @ApiModelProperty(value = "")


  public String getElo() {
    return elo;
  }

  public void setElo(String elo) {
    this.elo = elo;
  }

  public RentHouseRequest endArea(Double endArea) {
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

  public RentHouseRequest endPrice(Double endPrice) {
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

  public RentHouseRequest forwardId(List<Integer> forwardId) {
    this.forwardId = forwardId;
    return this;
  }

  public RentHouseRequest addForwardIdItem(Integer forwardIdItem) {
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

  public RentHouseRequest houseYearId(String houseYearId) {
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

  public RentHouseRequest jlo(String jlo) {
    this.jlo = jlo;
    return this;
  }

  /**
   * Get jlo
   * @return jlo
  **/
  @ApiModelProperty(value = "")


  public String getJlo() {
    return jlo;
  }

  public void setJlo(String jlo) {
    this.jlo = jlo;
  }

  public RentHouseRequest keyword(String keyword) {
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

  public RentHouseRequest labelId(List<Integer> labelId) {
    this.labelId = labelId;
    return this;
  }

  public RentHouseRequest addLabelIdItem(Integer labelIdItem) {
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

  public RentHouseRequest lat(Double lat) {
    this.lat = lat;
    return this;
  }

  /**
   * Get lat
   * @return lat
  **/
  @ApiModelProperty(value = "")


  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public RentHouseRequest layoutId(List<Integer> layoutId) {
    this.layoutId = layoutId;
    return this;
  }

  public RentHouseRequest addLayoutIdItem(Integer layoutIdItem) {
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

  public RentHouseRequest lon(Double lon) {
    this.lon = lon;
    return this;
  }

  /**
   * Get lon
   * @return lon
  **/
  @ApiModelProperty(value = "")


  public Double getLon() {
    return lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }

  public RentHouseRequest pageNum(Integer pageNum) {
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

  public RentHouseRequest pageSize(Integer pageSize) {
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

  public RentHouseRequest rentType(String rentType) {
    this.rentType = rentType;
    return this;
  }

  /**
   * Get rentType
   * @return rentType
  **/
  @ApiModelProperty(value = "")


  public String getRentType() {
    return rentType;
  }

  public void setRentType(String rentType) {
    this.rentType = rentType;
  }

  public RentHouseRequest source(String source) {
    this.source = source;
    return this;
  }

  /**
   * Get source
   * @return source
  **/
  @ApiModelProperty(value = "")


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public RentHouseRequest subwayLineId(Integer subwayLineId) {
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

  public RentHouseRequest subwayStationId(Integer[] subwayStationId) {
    this.subwayStationId = subwayStationId;
    return this;
  }

  /**
   * Get subwayStationId
   * @return subwayStationId
  **/
  @ApiModelProperty(value = "")


  public Integer[] getSubwayStationId() {
    return subwayStationId;
  }

  public void setSubwayStationId(Integer[] subwayStationId) {
    this.subwayStationId = subwayStationId;
  }

  public RentHouseRequest uid(String uid) {
    this.uid = uid;
    return this;
  }

  /**
   * Get uid
   * @return uid
  **/
  @ApiModelProperty(value = "")


  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RentHouseRequest rentHouseRequest = (RentHouseRequest) o;
    return Objects.equals(this.areaId, rentHouseRequest.areaId) &&
        Objects.equals(this.beginArea, rentHouseRequest.beginArea) &&
        Objects.equals(this.beginPrice, rentHouseRequest.beginPrice) &&
        Objects.equals(this.cityId, rentHouseRequest.cityId) &&
        Objects.equals(this.distance, rentHouseRequest.distance) &&
        Objects.equals(this.districtId, rentHouseRequest.districtId) &&
        Objects.equals(this.elo, rentHouseRequest.elo) &&
        Objects.equals(this.endArea, rentHouseRequest.endArea) &&
        Objects.equals(this.endPrice, rentHouseRequest.endPrice) &&
        Objects.equals(this.forwardId, rentHouseRequest.forwardId) &&
        Objects.equals(this.houseYearId, rentHouseRequest.houseYearId) &&
        Objects.equals(this.jlo, rentHouseRequest.jlo) &&
        Objects.equals(this.keyword, rentHouseRequest.keyword) &&
        Objects.equals(this.labelId, rentHouseRequest.labelId) &&
        Objects.equals(this.lat, rentHouseRequest.lat) &&
        Objects.equals(this.layoutId, rentHouseRequest.layoutId) &&
        Objects.equals(this.lon, rentHouseRequest.lon) &&
        Objects.equals(this.pageNum, rentHouseRequest.pageNum) &&
        Objects.equals(this.pageSize, rentHouseRequest.pageSize) &&
        Objects.equals(this.rentType, rentHouseRequest.rentType) &&
        Objects.equals(this.source, rentHouseRequest.source) &&
        Objects.equals(this.subwayLineId, rentHouseRequest.subwayLineId) &&
        Objects.equals(this.subwayStationId, rentHouseRequest.subwayStationId) &&
        Objects.equals(this.uid, rentHouseRequest.uid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(areaId, beginArea, beginPrice, cityId, distance, districtId, elo, endArea, endPrice, forwardId, houseYearId, jlo, keyword, labelId, lat, layoutId, lon, pageNum, pageSize, rentType, source, subwayLineId, subwayStationId, uid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RentHouseRequest {\n");
    
    sb.append("    areaId: ").append(toIndentedString(areaId)).append("\n");
    sb.append("    beginArea: ").append(toIndentedString(beginArea)).append("\n");
    sb.append("    beginPrice: ").append(toIndentedString(beginPrice)).append("\n");
    sb.append("    cityId: ").append(toIndentedString(cityId)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
    sb.append("    elo: ").append(toIndentedString(elo)).append("\n");
    sb.append("    endArea: ").append(toIndentedString(endArea)).append("\n");
    sb.append("    endPrice: ").append(toIndentedString(endPrice)).append("\n");
    sb.append("    forwardId: ").append(toIndentedString(forwardId)).append("\n");
    sb.append("    houseYearId: ").append(toIndentedString(houseYearId)).append("\n");
    sb.append("    jlo: ").append(toIndentedString(jlo)).append("\n");
    sb.append("    keyword: ").append(toIndentedString(keyword)).append("\n");
    sb.append("    labelId: ").append(toIndentedString(labelId)).append("\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    layoutId: ").append(toIndentedString(layoutId)).append("\n");
    sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
    sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    rentType: ").append(toIndentedString(rentType)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    subwayLineId: ").append(toIndentedString(subwayLineId)).append("\n");
    sb.append("    subwayStationId: ").append(toIndentedString(subwayStationId)).append("\n");
    sb.append("    uid: ").append(toIndentedString(uid)).append("\n");
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

