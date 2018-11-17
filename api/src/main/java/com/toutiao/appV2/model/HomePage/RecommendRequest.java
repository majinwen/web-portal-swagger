package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RecommendRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:33:40.959Z")

public class RecommendRequest   {
  @JsonProperty("beginPrice")
  private Double beginPrice = null;

  @JsonProperty("endPrice")
  private Double endPrice = null;

  @JsonProperty("districtId")
  @Valid
  private List<Integer> districtId = null;

  public RecommendRequest beginPrice(Double beginPrice) {
    this.beginPrice = beginPrice;
    return this;
  }

  /**
   * Get beginPrice
   * @return beginPrice
  **/
  @ApiModelProperty(value = "")


  public Double getBeginPrice() {
    return beginPrice;
  }

  public void setBeginPrice(Double beginPrice) {
    this.beginPrice = beginPrice;
  }

  public RecommendRequest endPrice(Double endPrice) {
    this.endPrice = endPrice;
    return this;
  }

  /**
   * Get endPrice
   * @return endPrice
  **/
  @ApiModelProperty(value = "")


  public Double getEndPrice() {
    return endPrice;
  }

  public void setEndPrice(Double endPrice) {
    this.endPrice = endPrice;
  }

  public RecommendRequest districtId(List<Integer> districtId) {
    this.districtId = districtId;
    return this;
  }

  public RecommendRequest addDistrictIdItem(Integer districtIdItem) {
    if (this.districtId == null) {
      this.districtId = new ArrayList<Integer>();
    }
    this.districtId.add(districtIdItem);
    return this;
  }

  /**
   * Get districtId
   * @return districtId
  **/
  @ApiModelProperty(value = "")


  public List<Integer> getDistrictId() {
    return districtId;
  }

  public void setDistrictId(List<Integer> districtId) {
    this.districtId = districtId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecommendRequest recommendRequest = (RecommendRequest) o;
    return Objects.equals(this.beginPrice, recommendRequest.beginPrice) &&
        Objects.equals(this.endPrice, recommendRequest.endPrice) &&
        Objects.equals(this.districtId, recommendRequest.districtId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beginPrice, endPrice, districtId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecommendRequest {\n");
    
    sb.append("    beginPrice: ").append(toIndentedString(beginPrice)).append("\n");
    sb.append("    endPrice: ").append(toIndentedString(endPrice)).append("\n");
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
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

