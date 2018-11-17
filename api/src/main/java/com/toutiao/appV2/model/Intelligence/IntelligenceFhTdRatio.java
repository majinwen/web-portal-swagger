package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * IntelligenceFhTdRatio
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T08:35:17.892Z")

public class IntelligenceFhTdRatio   {
  @ApiModelProperty(name = "averageVolume", value = "月度平均成交量")
  @JsonProperty("averageVolume")
  private String averageVolume = null;

  @ApiModelProperty(name = "averageVolumeRatio", value = "月度平均成交量占市场百分比")
  @JsonProperty("averageVolumeRatio")
  private String averageVolumeRatio = null;

  @ApiModelProperty(name = "maxVolume", value = "月度最高成交量")
  @JsonProperty("maxVolume")
  private Integer maxVolume = null;

  @ApiModelProperty(name = "maxVolumeRatio", value = "月度最高成交量")
  @JsonProperty("maxVolumeRatio")
  private String maxVolumeRatio = null;

  @ApiModelProperty(name = "minVolume", value = "月度最低成交量")
  @JsonProperty("minVolume")
  private Integer minVolume = null;

  @ApiModelProperty(name = "minVolumeRatio", value = "月度最低成交量占市场百分比")
  @JsonProperty("minVolumeRatio")
  private String minVolumeRatio = null;

  @ApiModelProperty(name = "sumAllSd", value = "12个月目标市场成交套数总和")
  @JsonProperty("sumAllSd")
  private Integer sumAllSd = null;

  @ApiModelProperty(name = "sumTargetSd", value = "12个月市场成交套数总和")
  @JsonProperty("sumTargetSd")
  private Integer sumTargetSd = null;

  public IntelligenceFhTdRatio averageVolume(String averageVolume) {
    this.averageVolume = averageVolume;
    return this;
  }

  /**
   * Get averageVolume
   * @return averageVolume
  **/
  @ApiModelProperty(value = "")


  public String getAverageVolume() {
    return averageVolume;
  }

  public void setAverageVolume(String averageVolume) {
    this.averageVolume = averageVolume;
  }

  public IntelligenceFhTdRatio averageVolumeRatio(String averageVolumeRatio) {
    this.averageVolumeRatio = averageVolumeRatio;
    return this;
  }

  /**
   * Get averageVolumeRatio
   * @return averageVolumeRatio
  **/
  @ApiModelProperty(value = "")


  public String getAverageVolumeRatio() {
    return averageVolumeRatio;
  }

  public void setAverageVolumeRatio(String averageVolumeRatio) {
    this.averageVolumeRatio = averageVolumeRatio;
  }

  public IntelligenceFhTdRatio maxVolume(Integer maxVolume) {
    this.maxVolume = maxVolume;
    return this;
  }

  /**
   * Get maxVolume
   * @return maxVolume
  **/
  @ApiModelProperty(value = "")


  public Integer getMaxVolume() {
    return maxVolume;
  }

  public void setMaxVolume(Integer maxVolume) {
    this.maxVolume = maxVolume;
  }

  public IntelligenceFhTdRatio maxVolumeRatio(String maxVolumeRatio) {
    this.maxVolumeRatio = maxVolumeRatio;
    return this;
  }

  /**
   * Get maxVolumeRatio
   * @return maxVolumeRatio
  **/
  @ApiModelProperty(value = "")


  public String getMaxVolumeRatio() {
    return maxVolumeRatio;
  }

  public void setMaxVolumeRatio(String maxVolumeRatio) {
    this.maxVolumeRatio = maxVolumeRatio;
  }

  public IntelligenceFhTdRatio minVolume(Integer minVolume) {
    this.minVolume = minVolume;
    return this;
  }

  /**
   * Get minVolume
   * @return minVolume
  **/
  @ApiModelProperty(value = "")


  public Integer getMinVolume() {
    return minVolume;
  }

  public void setMinVolume(Integer minVolume) {
    this.minVolume = minVolume;
  }

  public IntelligenceFhTdRatio minVolumeRatio(String minVolumeRatio) {
    this.minVolumeRatio = minVolumeRatio;
    return this;
  }

  /**
   * Get minVolumeRatio
   * @return minVolumeRatio
  **/
  @ApiModelProperty(value = "")


  public String getMinVolumeRatio() {
    return minVolumeRatio;
  }

  public void setMinVolumeRatio(String minVolumeRatio) {
    this.minVolumeRatio = minVolumeRatio;
  }

  public IntelligenceFhTdRatio sumAllSd(Integer sumAllSd) {
    this.sumAllSd = sumAllSd;
    return this;
  }

  /**
   * Get sumAllSd
   * @return sumAllSd
  **/
  @ApiModelProperty(value = "")


  public Integer getSumAllSd() {
    return sumAllSd;
  }

  public void setSumAllSd(Integer sumAllSd) {
    this.sumAllSd = sumAllSd;
  }

  public IntelligenceFhTdRatio sumTargetSd(Integer sumTargetSd) {
    this.sumTargetSd = sumTargetSd;
    return this;
  }

  /**
   * Get sumTargetSd
   * @return sumTargetSd
  **/
  @ApiModelProperty(value = "")


  public Integer getSumTargetSd() {
    return sumTargetSd;
  }

  public void setSumTargetSd(Integer sumTargetSd) {
    this.sumTargetSd = sumTargetSd;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntelligenceFhTdRatio intelligenceFhTdRatio = (IntelligenceFhTdRatio) o;
    return Objects.equals(this.averageVolume, intelligenceFhTdRatio.averageVolume) &&
        Objects.equals(this.averageVolumeRatio, intelligenceFhTdRatio.averageVolumeRatio) &&
        Objects.equals(this.maxVolume, intelligenceFhTdRatio.maxVolume) &&
        Objects.equals(this.maxVolumeRatio, intelligenceFhTdRatio.maxVolumeRatio) &&
        Objects.equals(this.minVolume, intelligenceFhTdRatio.minVolume) &&
        Objects.equals(this.minVolumeRatio, intelligenceFhTdRatio.minVolumeRatio) &&
        Objects.equals(this.sumAllSd, intelligenceFhTdRatio.sumAllSd) &&
        Objects.equals(this.sumTargetSd, intelligenceFhTdRatio.sumTargetSd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(averageVolume, averageVolumeRatio, maxVolume, maxVolumeRatio, minVolume, minVolumeRatio, sumAllSd, sumTargetSd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntelligenceFhTdRatio {\n");
    
    sb.append("    averageVolume: ").append(toIndentedString(averageVolume)).append("\n");
    sb.append("    averageVolumeRatio: ").append(toIndentedString(averageVolumeRatio)).append("\n");
    sb.append("    maxVolume: ").append(toIndentedString(maxVolume)).append("\n");
    sb.append("    maxVolumeRatio: ").append(toIndentedString(maxVolumeRatio)).append("\n");
    sb.append("    minVolume: ").append(toIndentedString(minVolume)).append("\n");
    sb.append("    minVolumeRatio: ").append(toIndentedString(minVolumeRatio)).append("\n");
    sb.append("    sumAllSd: ").append(toIndentedString(sumAllSd)).append("\n");
    sb.append("    sumTargetSd: ").append(toIndentedString(sumTargetSd)).append("\n");
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

