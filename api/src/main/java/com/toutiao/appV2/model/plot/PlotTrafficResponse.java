package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PlotTrafficResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotTrafficResponse   {
  @JsonProperty("busLines")
  @ApiModelProperty("公交线路")
  private Integer busLines = null;

  @JsonProperty("busStation")
  @ApiModelProperty("公交站")
  private String busStation = null;

  @JsonProperty("ringRoadDistance")
  @ApiModelProperty("环线距离")
  private Double ringRoadDistance = null;

  @JsonProperty("ringRoadName")
  @ApiModelProperty("环线")
  private String ringRoadName = null;

  @JsonProperty("subwayDistance")
  @ApiModelProperty("地铁站距离")
  private Double subwayDistance = null;

  @JsonProperty("subwayLine")
  @ApiModelProperty("地铁线")
  private String subwayLine = null;

  @JsonProperty("subwayStation")
  @ApiModelProperty("地铁站")
  private String subwayStation = null;

  public PlotTrafficResponse busLines(Integer busLines) {
    this.busLines = busLines;
    return this;
  }

  /**
   * Get busLines
   * @return busLines
  **/
  @ApiModelProperty(value = "")


  public Integer getBusLines() {
    return busLines;
  }

  public void setBusLines(Integer busLines) {
    this.busLines = busLines;
  }

  public PlotTrafficResponse busStation(String busStation) {
    this.busStation = busStation;
    return this;
  }

  /**
   * Get busStation
   * @return busStation
  **/
  @ApiModelProperty(value = "")


  public String getBusStation() {
    return busStation;
  }

  public void setBusStation(String busStation) {
    this.busStation = busStation;
  }

  public PlotTrafficResponse ringRoadDistance(Double ringRoadDistance) {
    this.ringRoadDistance = ringRoadDistance;
    return this;
  }

  /**
   * Get ringRoadDistance
   * @return ringRoadDistance
  **/
  @ApiModelProperty(value = "")


  public Double getRingRoadDistance() {
    return ringRoadDistance;
  }

  public void setRingRoadDistance(Double ringRoadDistance) {
    this.ringRoadDistance = ringRoadDistance;
  }

  public PlotTrafficResponse ringRoadName(String ringRoadName) {
    this.ringRoadName = ringRoadName;
    return this;
  }

  /**
   * Get ringRoadName
   * @return ringRoadName
  **/
  @ApiModelProperty(value = "")


  public String getRingRoadName() {
    return ringRoadName;
  }

  public void setRingRoadName(String ringRoadName) {
    this.ringRoadName = ringRoadName;
  }

  public PlotTrafficResponse subwayDistance(Double subwayDistance) {
    this.subwayDistance = subwayDistance;
    return this;
  }

  /**
   * Get subwayDistance
   * @return subwayDistance
  **/
  @ApiModelProperty(value = "")


  public Double getSubwayDistance() {
    return subwayDistance;
  }

  public void setSubwayDistance(Double subwayDistance) {
    this.subwayDistance = subwayDistance;
  }

  public PlotTrafficResponse subwayLine(String subwayLine) {
    this.subwayLine = subwayLine;
    return this;
  }

  /**
   * Get subwayLine
   * @return subwayLine
  **/
  @ApiModelProperty(value = "")


  public String getSubwayLine() {
    return subwayLine;
  }

  public void setSubwayLine(String subwayLine) {
    this.subwayLine = subwayLine;
  }

  public PlotTrafficResponse subwayStation(String subwayStation) {
    this.subwayStation = subwayStation;
    return this;
  }

  /**
   * Get subwayStation
   * @return subwayStation
  **/
  @ApiModelProperty(value = "")


  public String getSubwayStation() {
    return subwayStation;
  }

  public void setSubwayStation(String subwayStation) {
    this.subwayStation = subwayStation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotTrafficResponse plotTrafficResponse = (PlotTrafficResponse) o;
    return Objects.equals(this.busLines, plotTrafficResponse.busLines) &&
        Objects.equals(this.busStation, plotTrafficResponse.busStation) &&
        Objects.equals(this.ringRoadDistance, plotTrafficResponse.ringRoadDistance) &&
        Objects.equals(this.ringRoadName, plotTrafficResponse.ringRoadName) &&
        Objects.equals(this.subwayDistance, plotTrafficResponse.subwayDistance) &&
        Objects.equals(this.subwayLine, plotTrafficResponse.subwayLine) &&
        Objects.equals(this.subwayStation, plotTrafficResponse.subwayStation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(busLines, busStation, ringRoadDistance, ringRoadName, subwayDistance, subwayLine, subwayStation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotTrafficResponse {\n");
    
    sb.append("    busLines: ").append(toIndentedString(busLines)).append("\n");
    sb.append("    busStation: ").append(toIndentedString(busStation)).append("\n");
    sb.append("    ringRoadDistance: ").append(toIndentedString(ringRoadDistance)).append("\n");
    sb.append("    ringRoadName: ").append(toIndentedString(ringRoadName)).append("\n");
    sb.append("    subwayDistance: ").append(toIndentedString(subwayDistance)).append("\n");
    sb.append("    subwayLine: ").append(toIndentedString(subwayLine)).append("\n");
    sb.append("    subwayStation: ").append(toIndentedString(subwayStation)).append("\n");
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

