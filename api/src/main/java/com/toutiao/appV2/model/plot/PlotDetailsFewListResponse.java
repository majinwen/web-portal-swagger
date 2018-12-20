package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotDetailsFewListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotDetailsFewListResponse   {
  @JsonProperty("plotDetailsFewResponseList")
  @Valid
  private List<PlotDetailsFewResponse> plotDetailsFewResponseList = null;

  @JsonProperty("totalNum")
  private Integer totalNum = null;

  public PlotDetailsFewListResponse plotDetailsFewResponseList(List<PlotDetailsFewResponse> plotDetailsFewResponseList) {
    this.plotDetailsFewResponseList = plotDetailsFewResponseList;
    return this;
  }

  public PlotDetailsFewListResponse addPlotDetailsFewResponseListItem(PlotDetailsFewResponse plotDetailsFewResponseListItem) {
    if (this.plotDetailsFewResponseList == null) {
      this.plotDetailsFewResponseList = new ArrayList<PlotDetailsFewResponse>();
    }
    this.plotDetailsFewResponseList.add(plotDetailsFewResponseListItem);
    return this;
  }

  /**
   * Get plotDetailsFewResponseList
   * @return plotDetailsFewResponseList
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<PlotDetailsFewResponse> getPlotDetailsFewResponseList() {
    return plotDetailsFewResponseList;
  }

  public void setPlotDetailsFewResponseList(List<PlotDetailsFewResponse> plotDetailsFewResponseList) {
    this.plotDetailsFewResponseList = plotDetailsFewResponseList;
  }

  public PlotDetailsFewListResponse totalNum(Integer totalNum) {
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
    PlotDetailsFewListResponse plotDetailsFewListResponse = (PlotDetailsFewListResponse) o;
    return Objects.equals(this.plotDetailsFewResponseList, plotDetailsFewListResponse.plotDetailsFewResponseList) &&
        Objects.equals(this.totalNum, plotDetailsFewListResponse.totalNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(plotDetailsFewResponseList, totalNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotDetailsFewListResponse {\n");
    
    sb.append("    plotDetailsFewResponseList: ").append(toIndentedString(plotDetailsFewResponseList)).append("\n");
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

