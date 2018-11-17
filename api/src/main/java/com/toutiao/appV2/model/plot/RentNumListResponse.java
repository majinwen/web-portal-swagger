package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * RentNumListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class RentNumListResponse   {
  @JsonProperty("data")
  @Valid
  private List<RentNumResponse> data = null;

  @JsonProperty("totalNum")
  private Integer totalNum = null;

  public RentNumListResponse data(List<RentNumResponse> data) {
    this.data = data;
    return this;
  }

  public RentNumListResponse addDataItem(RentNumResponse dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<RentNumResponse>();
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

  public List<RentNumResponse> getData() {
    return data;
  }

  public void setData(List<RentNumResponse> data) {
    this.data = data;
  }

  public RentNumListResponse totalNum(Integer totalNum) {
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
    RentNumListResponse rentNumListResponse = (RentNumListResponse) o;
    return Objects.equals(this.data, rentNumListResponse.data) &&
        Objects.equals(this.totalNum, rentNumListResponse.totalNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, totalNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RentNumListResponse {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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

