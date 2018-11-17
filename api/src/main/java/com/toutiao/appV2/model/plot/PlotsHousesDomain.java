package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PlotsHousesDomain
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotsHousesDomain   {
  @JsonProperty("avgPrice")
  private Double avgPrice = null;

  @JsonProperty("rentCount")
  private Long rentCount = null;

  @JsonProperty("sellHouseCount")
  private Long sellHouseCount = null;

  @JsonProperty("startPrice")
  private Double startPrice = null;

  public PlotsHousesDomain avgPrice(Double avgPrice) {
    this.avgPrice = avgPrice;
    return this;
  }

  /**
   * Get avgPrice
   * @return avgPrice
  **/
  @ApiModelProperty(value = "")


  public Double getAvgPrice() {
    return avgPrice;
  }

  public void setAvgPrice(Double avgPrice) {
    this.avgPrice = avgPrice;
  }

  public PlotsHousesDomain rentCount(Long rentCount) {
    this.rentCount = rentCount;
    return this;
  }

  /**
   * Get rentCount
   * @return rentCount
  **/
  @ApiModelProperty(value = "")


  public Long getRentCount() {
    return rentCount;
  }

  public void setRentCount(Long rentCount) {
    this.rentCount = rentCount;
  }

  public PlotsHousesDomain sellHouseCount(Long sellHouseCount) {
    this.sellHouseCount = sellHouseCount;
    return this;
  }

  /**
   * Get sellHouseCount
   * @return sellHouseCount
  **/
  @ApiModelProperty(value = "")


  public Long getSellHouseCount() {
    return sellHouseCount;
  }

  public void setSellHouseCount(Long sellHouseCount) {
    this.sellHouseCount = sellHouseCount;
  }

  public PlotsHousesDomain startPrice(Double startPrice) {
    this.startPrice = startPrice;
    return this;
  }

  /**
   * Get startPrice
   * @return startPrice
  **/
  @ApiModelProperty(value = "")


  public Double getStartPrice() {
    return startPrice;
  }

  public void setStartPrice(Double startPrice) {
    this.startPrice = startPrice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotsHousesDomain plotsHousesDomain = (PlotsHousesDomain) o;
    return Objects.equals(this.avgPrice, plotsHousesDomain.avgPrice) &&
        Objects.equals(this.rentCount, plotsHousesDomain.rentCount) &&
        Objects.equals(this.sellHouseCount, plotsHousesDomain.sellHouseCount) &&
        Objects.equals(this.startPrice, plotsHousesDomain.startPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(avgPrice, rentCount, sellHouseCount, startPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotsHousesDomain {\n");
    
    sb.append("    avgPrice: ").append(toIndentedString(avgPrice)).append("\n");
    sb.append("    rentCount: ").append(toIndentedString(rentCount)).append("\n");
    sb.append("    sellHouseCount: ").append(toIndentedString(sellHouseCount)).append("\n");
    sb.append("    startPrice: ").append(toIndentedString(startPrice)).append("\n");
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

