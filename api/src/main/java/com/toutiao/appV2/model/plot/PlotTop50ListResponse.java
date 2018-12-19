package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotTop50ListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotTop50ListResponse   {
  @JsonProperty("plotTop50ResponseList")
  @Valid
  private List<PlotTop50Response> plotTop50ResponseList = null;

  @JsonProperty("totalNum")
  private Integer totalNum = null;

  public PlotTop50ListResponse plotTop50ResponseList(List<PlotTop50Response> plotTop50ResponseList) {
    this.plotTop50ResponseList = plotTop50ResponseList;
    return this;
  }

  public PlotTop50ListResponse addPlotTop50ResponseListItem(PlotTop50Response plotTop50ResponseListItem) {
    if (this.plotTop50ResponseList == null) {
      this.plotTop50ResponseList = new ArrayList<PlotTop50Response>();
    }
    this.plotTop50ResponseList.add(plotTop50ResponseListItem);
    return this;
  }

  /**
   * Get plotTop50ResponseList
   * @return plotTop50ResponseList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<PlotTop50Response> getPlotTop50ResponseList() {
    return plotTop50ResponseList;
  }

  public void setPlotTop50ResponseList(List<PlotTop50Response> plotTop50ResponseList) {
    this.plotTop50ResponseList = plotTop50ResponseList;
  }

  public PlotTop50ListResponse totalNum(Integer totalNum) {
    this.totalNum = totalNum;
    return this;
  }

  /**
   * Get totalNum
   * @return totalNum
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalNum() {
    return totalNum;
  }

  public void setTotalNum(Integer totalNum) {
    this.totalNum = totalNum;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotTop50ListResponse plotTop50ListResponse = (PlotTop50ListResponse) o;
    return Objects.equals(this.plotTop50ResponseList, plotTop50ListResponse.plotTop50ResponseList) &&
        Objects.equals(this.totalNum, plotTop50ListResponse.totalNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(plotTop50ResponseList, totalNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotTop50ListResponse {\n");
    
    sb.append("    plotTop50ResponseList: ").append(toIndentedString(plotTop50ResponseList)).append("\n");
    sb.append("    totalNum: ").append(toIndentedString(totalNum)).append("\n");
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

