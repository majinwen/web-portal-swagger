package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PriceTrendDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T08:35:17.892Z")

public class PriceTrendDo   {
  @ApiModelProperty(name = "maxTarget", value = "最高涨幅")
  @JsonProperty("maxTarget")
  private Double maxTarget = null;

  @ApiModelProperty(name = "minTarget", value = "最高跌幅")
  @JsonProperty("minTarget")
  private Double minTarget = null;

  @ApiModelProperty(name = "ptlists", value = "结果")
  @JsonProperty("ptlists")
  @Valid
  private List<Object> ptlists = null;

  @ApiModelProperty(name = "searchPrice", value = "总价")
  @JsonProperty("searchPrice")
  private Integer searchPrice = null;

  @ApiModelProperty(name = "target", value = "")
  @JsonProperty("target")
  private Double target = null;

  public PriceTrendDo maxTarget(Double maxTarget) {
    this.maxTarget = maxTarget;
    return this;
  }

  /**
   * Get maxTarget
   * @return maxTarget
  **/
  @ApiModelProperty(value = "")


  public Double getMaxTarget() {
    return maxTarget;
  }

  public void setMaxTarget(Double maxTarget) {
    this.maxTarget = maxTarget;
  }

  public PriceTrendDo minTarget(Double minTarget) {
    this.minTarget = minTarget;
    return this;
  }

  /**
   * Get minTarget
   * @return minTarget
  **/
  @ApiModelProperty(value = "")


  public Double getMinTarget() {
    return minTarget;
  }

  public void setMinTarget(Double minTarget) {
    this.minTarget = minTarget;
  }

  public PriceTrendDo ptlists(List<Object> ptlists) {
    this.ptlists = ptlists;
    return this;
  }

  public PriceTrendDo addPtlistsItem(Object ptlistsItem) {
    if (this.ptlists == null) {
      this.ptlists = new ArrayList<Object>();
    }
    this.ptlists.add(ptlistsItem);
    return this;
  }

  /**
   * Get ptlists
   * @return ptlists
  **/
  @ApiModelProperty(value = "")


  public List<Object> getPtlists() {
    return ptlists;
  }

  public void setPtlists(List<Object> ptlists) {
    this.ptlists = ptlists;
  }

  public PriceTrendDo searchPrice(Integer searchPrice) {
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

  public PriceTrendDo target(Double target) {
    this.target = target;
    return this;
  }

  /**
   * Get target
   * @return target
  **/
  @ApiModelProperty(value = "")


  public Double getTarget() {
    return target;
  }

  public void setTarget(Double target) {
    this.target = target;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PriceTrendDo priceTrendDo = (PriceTrendDo) o;
    return Objects.equals(this.maxTarget, priceTrendDo.maxTarget) &&
        Objects.equals(this.minTarget, priceTrendDo.minTarget) &&
        Objects.equals(this.ptlists, priceTrendDo.ptlists) &&
        Objects.equals(this.searchPrice, priceTrendDo.searchPrice) &&
        Objects.equals(this.target, priceTrendDo.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxTarget, minTarget, ptlists, searchPrice, target);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PriceTrendDo {\n");
    
    sb.append("    maxTarget: ").append(toIndentedString(maxTarget)).append("\n");
    sb.append("    minTarget: ").append(toIndentedString(minTarget)).append("\n");
    sb.append("    ptlists: ").append(toIndentedString(ptlists)).append("\n");
    sb.append("    searchPrice: ").append(toIndentedString(searchPrice)).append("\n");
    sb.append("    target: ").append(toIndentedString(target)).append("\n");
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

