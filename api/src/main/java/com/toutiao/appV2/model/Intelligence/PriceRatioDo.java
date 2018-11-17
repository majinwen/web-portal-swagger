package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PriceRatioDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T08:35:17.892Z")

public class PriceRatioDo   {
  @ApiModelProperty(name = "ratio", value = "价格比率")
  @JsonProperty("ratio")
  private IntelligenceFhTdRatio ratio = null;

  @ApiModelProperty(name = "searchPrice", value = "总价")
  @JsonProperty("searchPrice")
  private Integer searchPrice = null;

  @ApiModelProperty(name = "trend", value = "价格趋势")
  @JsonProperty("trend")
  @Valid
  private List<Object> trend = null;

  public PriceRatioDo ratio(IntelligenceFhTdRatio ratio) {
    this.ratio = ratio;
    return this;
  }

  /**
   * Get ratio
   * @return ratio
  **/
  @ApiModelProperty(value = "")

  @Valid

  public IntelligenceFhTdRatio getRatio() {
    return ratio;
  }

  public void setRatio(IntelligenceFhTdRatio ratio) {
    this.ratio = ratio;
  }

  public PriceRatioDo searchPrice(Integer searchPrice) {
    this.searchPrice = searchPrice;
    return this;
  }

  /**
   * Get searchPrice
   * @return searchPrice
  **/
  @ApiModelProperty(value = "")


  public Integer getSearchPrice() {
    return searchPrice;
  }

  public void setSearchPrice(Integer searchPrice) {
    this.searchPrice = searchPrice;
  }

  public PriceRatioDo trend(List<Object> trend) {
    this.trend = trend;
    return this;
  }

  public PriceRatioDo addTrendItem(Object trendItem) {
    if (this.trend == null) {
      this.trend = new ArrayList<Object>();
    }
    this.trend.add(trendItem);
    return this;
  }

  /**
   * Get trend
   * @return trend
  **/
  @ApiModelProperty(value = "")


  public List<Object> getTrend() {
    return trend;
  }

  public void setTrend(List<Object> trend) {
    this.trend = trend;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PriceRatioDo priceRatioDo = (PriceRatioDo) o;
    return Objects.equals(this.ratio, priceRatioDo.ratio) &&
        Objects.equals(this.searchPrice, priceRatioDo.searchPrice) &&
        Objects.equals(this.trend, priceRatioDo.trend);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ratio, searchPrice, trend);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PriceRatioDo {\n");
    
    sb.append("    ratio: ").append(toIndentedString(ratio)).append("\n");
    sb.append("    searchPrice: ").append(toIndentedString(searchPrice)).append("\n");
    sb.append("    trend: ").append(toIndentedString(trend)).append("\n");
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

