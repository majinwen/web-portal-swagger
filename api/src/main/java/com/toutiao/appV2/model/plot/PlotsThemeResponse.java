package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotsThemeResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotsThemeResponse   {
  @JsonProperty("data")
  @Valid
  private List<PlotsThemeDo> data = null;

  @JsonProperty("totalCount")
  private Long totalCount = null;

  public PlotsThemeResponse data(List<PlotsThemeDo> data) {
    this.data = data;
    return this;
  }

  public PlotsThemeResponse addDataItem(PlotsThemeDo dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<PlotsThemeDo>();
    }
    this.data.add(dataItem);
    return this;
  }

  /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<PlotsThemeDo> getData() {
    return data;
  }

  public void setData(List<PlotsThemeDo> data) {
    this.data = data;
  }

  public PlotsThemeResponse totalCount(Long totalCount) {
    this.totalCount = totalCount;
    return this;
  }

  /**
   * Get totalCount
   * @return totalCount
  **/
  @ApiModelProperty(value = "")


  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
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
    PlotsThemeResponse plotsThemeResponse = (PlotsThemeResponse) o;
    return Objects.equals(this.data, plotsThemeResponse.data) &&
        Objects.equals(this.totalCount, plotsThemeResponse.totalCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, totalCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotsThemeResponse {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

