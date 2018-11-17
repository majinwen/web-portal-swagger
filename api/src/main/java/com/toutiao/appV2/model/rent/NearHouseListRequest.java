package com.toutiao.appV2.model.rent;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * NearHouseListRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

public class NearHouseListRequest   {
  @JsonProperty("distance")
  @ApiParam(value = "附近距离", defaultValue = "5")
  private Integer distance = 5;

  @JsonProperty("rentType")
  @ApiParam("整租:1/合租:2/未知:3")
  private String rentType = null;

  @JsonProperty("source")
  @ApiParam("来源id")
  private String source = null;

  @JsonProperty("lat")
  @NotNull(message = "维度不能为空")
  @ApiParam(value = "维度", required = true)
  private Double lat = null;

  @JsonProperty("lon")
  @NotNull(message = "经度不能为空")
  @ApiParam(value = "经度", required = true)
  private Double lon = null;

  public NearHouseListRequest distance(Integer distance) {
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

  public NearHouseListRequest rentType(String rentType) {
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

  public NearHouseListRequest source(String source) {
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

  public NearHouseListRequest lat(Double lat) {
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

  public NearHouseListRequest lon(Double lon) {
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
    NearHouseListRequest nearHouseListRequest = (NearHouseListRequest) o;
    return Objects.equals(this.distance, nearHouseListRequest.distance) &&
        Objects.equals(this.rentType, nearHouseListRequest.rentType) &&
        Objects.equals(this.source, nearHouseListRequest.source) &&
        Objects.equals(this.lat, nearHouseListRequest.lat) &&
        Objects.equals(this.lon, nearHouseListRequest.lon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(distance, rentType, source, lat, lon);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NearHouseListRequest {\n");
    
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    rentType: ").append(toIndentedString(rentType)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
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

