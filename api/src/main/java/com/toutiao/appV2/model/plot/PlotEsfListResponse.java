package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotEsfListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotEsfListResponse   {
  @JsonProperty("plotEsfResponseList")
  @Valid
  private List<PlotEsfResponse> plotEsfResponseList = null;

  @JsonProperty("totalNum")
  private Integer totalNum = null;

  public PlotEsfListResponse plotEsfResponseList(List<PlotEsfResponse> plotEsfResponseList) {
    this.plotEsfResponseList = plotEsfResponseList;
    return this;
  }

  public PlotEsfListResponse addPlotEsfResponseListItem(PlotEsfResponse plotEsfResponseListItem) {
    if (this.plotEsfResponseList == null) {
      this.plotEsfResponseList = new ArrayList<PlotEsfResponse>();
    }
    this.plotEsfResponseList.add(plotEsfResponseListItem);
    return this;
  }

  /**
   * Get plotEsfResponseList
   * @return plotEsfResponseList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<PlotEsfResponse> getPlotEsfResponseList() {
    return plotEsfResponseList;
  }

  public void setPlotEsfResponseList(List<PlotEsfResponse> plotEsfResponseList) {
    this.plotEsfResponseList = plotEsfResponseList;
  }

  public PlotEsfListResponse totalNum(Integer totalNum) {
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
    PlotEsfListResponse plotEsfListResponse = (PlotEsfListResponse) o;
    return Objects.equals(this.plotEsfResponseList, plotEsfListResponse.plotEsfResponseList) &&
        Objects.equals(this.totalNum, plotEsfListResponse.totalNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(plotEsfResponseList, totalNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotEsfListResponse {\n");
    
    sb.append("    plotEsfResponseList: ").append(toIndentedString(plotEsfResponseList)).append("\n");
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

