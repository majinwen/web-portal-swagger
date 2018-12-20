package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * DistictInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T08:35:17.892Z")

public class DistictInfo   {
  @ApiModelProperty(value = "区域id",name = "districtId")
  @JsonProperty("districtId")
  private String districtId = null;

  @ApiModelProperty(value = "区域名称",name = "districtName")
  @JsonProperty("districtName")
  private String districtName = null;

  public DistictInfo districtId(String districtId) {
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

  public DistictInfo districtName(String districtName) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DistictInfo distictInfo = (DistictInfo) o;
    return Objects.equals(this.districtId, distictInfo.districtId) &&
        Objects.equals(this.districtName, distictInfo.districtName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(districtId, districtName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DistictInfo {\n");
    
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
    sb.append("    districtName: ").append(toIndentedString(districtName)).append("\n");
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

