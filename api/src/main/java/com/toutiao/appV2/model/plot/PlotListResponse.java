package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotListResponse   {
  @JsonProperty("plotList")
  @Valid
  private List<PlotDetailsFewDo> plotList = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  public PlotListResponse plotList(List<PlotDetailsFewDo> plotList) {
    this.plotList = plotList;
    return this;
  }

  public PlotListResponse addPlotListItem(PlotDetailsFewDo plotListItem) {
    if (this.plotList == null) {
      this.plotList = new ArrayList<PlotDetailsFewDo>();
    }
    this.plotList.add(plotListItem);
    return this;
  }

  /**
   * Get plotList
   * @return plotList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<PlotDetailsFewDo> getPlotList() {
    return plotList;
  }

  public void setPlotList(List<PlotDetailsFewDo> plotList) {
    this.plotList = plotList;
  }

  public PlotListResponse totalCount(Integer totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * Get totalCount
   * @return totalCount
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotListResponse plotListResponse = (PlotListResponse) o;
    return Objects.equals(this.plotList, plotListResponse.plotList) &&
        Objects.equals(this.totalCount, plotListResponse.totalCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(plotList, totalCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotListResponse {\n");
    
    sb.append("    plotList: ").append(toIndentedString(plotList)).append("\n");
    sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
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

