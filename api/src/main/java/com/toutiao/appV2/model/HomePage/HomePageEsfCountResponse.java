package com.toutiao.appV2.model.HomePage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HomePageEsfCountResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T09:20:13.068Z")

public class HomePageEsfCountResponse   {
  @JsonProperty("forSaleCount")
  private Long forSaleCount = null;

  @JsonProperty("hotSaleCount")
  private Long hotSaleCount = null;

  @JsonProperty("missingCount")
  private Long missingCount = null;

  @JsonProperty("reduceCount")
  private Long reduceCount = null;

  public HomePageEsfCountResponse forSaleCount(Long forSaleCount) {
    this.forSaleCount = forSaleCount;
    return this;
  }

  /**
   * Get forSaleCount
   * @return forSaleCount
  **/
  @ApiModelProperty(value = "")


  public Long getForSaleCount() {
    return forSaleCount;
  }

  public void setForSaleCount(Long forSaleCount) {
    this.forSaleCount = forSaleCount;
  }

  public HomePageEsfCountResponse hotSaleCount(Long hotSaleCount) {
    this.hotSaleCount = hotSaleCount;
    return this;
  }

  /**
   * Get hotSaleCount
   * @return hotSaleCount
  **/
  @ApiModelProperty(value = "")


  public Long getHotSaleCount() {
    return hotSaleCount;
  }

  public void setHotSaleCount(Long hotSaleCount) {
    this.hotSaleCount = hotSaleCount;
  }

  public HomePageEsfCountResponse missingCount(Long missingCount) {
    this.missingCount = missingCount;
    return this;
  }

  /**
   * Get missingCount
   * @return missingCount
  **/
  @ApiModelProperty(value = "")


  public Long getMissingCount() {
    return missingCount;
  }

  public void setMissingCount(Long missingCount) {
    this.missingCount = missingCount;
  }

  public HomePageEsfCountResponse reduceCount(Long reduceCount) {
    this.reduceCount = reduceCount;
    return this;
  }

  /**
   * Get reduceCount
   * @return reduceCount
  **/
  @ApiModelProperty(value = "")


  public Long getReduceCount() {
    return reduceCount;
  }

  public void setReduceCount(Long reduceCount) {
    this.reduceCount = reduceCount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HomePageEsfCountResponse homePageEsfCountResponse = (HomePageEsfCountResponse) o;
    return Objects.equals(this.forSaleCount, homePageEsfCountResponse.forSaleCount) &&
        Objects.equals(this.hotSaleCount, homePageEsfCountResponse.hotSaleCount) &&
        Objects.equals(this.missingCount, homePageEsfCountResponse.missingCount) &&
        Objects.equals(this.reduceCount, homePageEsfCountResponse.reduceCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(forSaleCount, hotSaleCount, missingCount, reduceCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HomePageEsfCountResponse {\n");
    
    sb.append("    forSaleCount: ").append(toIndentedString(forSaleCount)).append("\n");
    sb.append("    hotSaleCount: ").append(toIndentedString(hotSaleCount)).append("\n");
    sb.append("    missingCount: ").append(toIndentedString(missingCount)).append("\n");
    sb.append("    reduceCount: ").append(toIndentedString(reduceCount)).append("\n");
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

