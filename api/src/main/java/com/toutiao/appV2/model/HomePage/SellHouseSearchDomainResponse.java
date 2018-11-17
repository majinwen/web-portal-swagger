package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.toutiao.app.domain.sellhouse.SellHousesSearchDo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SellHouseSearchDomainResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T09:20:13.068Z")

public class SellHouseSearchDomainResponse   {
  @JsonProperty("data")
  @Valid
  private List<SellHousesSearchDo> data = null;

  @JsonProperty("totalNum")
  private Integer totalNum = null;

  public SellHouseSearchDomainResponse data(List<SellHousesSearchDo> data) {
    this.data = data;
    return this;
  }

  public SellHouseSearchDomainResponse addDataItem(SellHousesSearchDo dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<SellHousesSearchDo>();
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

  public List<SellHousesSearchDo> getData() {
    return data;
  }

  public void setData(List<SellHousesSearchDo> data) {
    this.data = data;
  }

  public SellHouseSearchDomainResponse totalNum(Integer totalNum) {
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
    SellHouseSearchDomainResponse sellHouseSearchDomainResponse = (SellHouseSearchDomainResponse) o;
    return Objects.equals(this.data, sellHouseSearchDomainResponse.data) &&
        Objects.equals(this.totalNum, sellHouseSearchDomainResponse.totalNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, totalNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SellHouseSearchDomainResponse {\n");
    
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

