package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NearHouseSpecialPageRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:33:40.959Z")

public class NearHouseSpecialPageRequest   {
  @JsonProperty("plotId")
  private Integer plotId = null;

  @JsonProperty("pageNum")
  private Integer pageNum = null;

  @JsonProperty("size")
  private Integer size = null;

  @JsonProperty("cityId")
  private Integer cityId = null;

  @JsonProperty("lat")
  private Double lat = null;

  @JsonProperty("lon")
  private Double lon = null;

  public NearHouseSpecialPageRequest plotId(Integer plotId) {
    this.plotId = plotId;
    return this;
  }

  /**
   * Get plotId
   * @return plotId
  **/
  @ApiModelProperty(value = "")


  public Integer getPlotId() {
    return plotId;
  }

  public void setPlotId(Integer plotId) {
    this.plotId = plotId;
  }

  public NearHouseSpecialPageRequest pageNum(Integer pageNum) {
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

  public NearHouseSpecialPageRequest size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
  **/
  @ApiModelProperty(value = "")


  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public NearHouseSpecialPageRequest cityId(Integer cityId) {
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

  public NearHouseSpecialPageRequest lat(Double lat) {
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

  public NearHouseSpecialPageRequest lon(Double lon) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NearHouseSpecialPageRequest nearHouseSpecialPageRequest = (NearHouseSpecialPageRequest) o;
    return Objects.equals(this.plotId, nearHouseSpecialPageRequest.plotId) &&
        Objects.equals(this.pageNum, nearHouseSpecialPageRequest.pageNum) &&
        Objects.equals(this.size, nearHouseSpecialPageRequest.size) &&
        Objects.equals(this.cityId, nearHouseSpecialPageRequest.cityId) &&
        Objects.equals(this.lat, nearHouseSpecialPageRequest.lat) &&
        Objects.equals(this.lon, nearHouseSpecialPageRequest.lon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(plotId, pageNum, size, cityId, lat, lon);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NearHouseSpecialPageRequest {\n");
    
    sb.append("    plotId: ").append(toIndentedString(plotId)).append("\n");
    sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    cityId: ").append(toIndentedString(cityId)).append("\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
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

