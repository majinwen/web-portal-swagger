package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import com.toutiao.app.domain.Intelligence.PriceRatioDo;
import com.toutiao.app.domain.Intelligence.PriceTrendDo;

import javax.validation.Valid;
import java.util.Objects;

/**
 * IntelligenceResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T08:35:17.892Z")

public class IntelligenceResponse   {
  @ApiModelProperty(name = "backUrl", value = "回调url")
  @JsonProperty("backUrl")
  private String backUrl = null;

  @ApiModelProperty(name = "collectStatus", value = "收藏状态")
  @JsonProperty("collectStatus")
  private Integer collectStatus = null;

  @ApiModelProperty(name = "datajson", value = "结果")
  @JsonProperty("datajson")
  private String datajson = null;

  @ApiModelProperty(name = "district", value = "区域")
  @JsonProperty("district")
  private String district = null;

  @ApiModelProperty(name = "fhpt", value = "价格趋势")
  @JsonProperty("fhpt")
  private PriceTrendDo fhpt = null;

  @ApiModelProperty(name = "fhtp", value = "价格比率")
  @JsonProperty("fhtp")
  private PriceRatioDo fhtp = null;

  @ApiModelProperty(name = "layout", value = "户型")
  @JsonProperty("layout")
  private String layout = null;

  @ApiModelProperty(name = "totalPrice", value = "总价")
  @JsonProperty("totalPrice")
  private Integer totalPrice = null;

  public IntelligenceResponse backUrl(String backUrl) {
    this.backUrl = backUrl;
    return this;
  }

  /**
   * Get backUrl
   * @return backUrl
  **/
  @ApiModelProperty(value = "")


  public String getBackUrl() {
    return backUrl;
  }

  public void setBackUrl(String backUrl) {
    this.backUrl = backUrl;
  }

  public IntelligenceResponse collectStatus(Integer collectStatus) {
    this.collectStatus = collectStatus;
    return this;
  }

  /**
   * Get collectStatus
   * @return collectStatus
  **/
  @ApiModelProperty(value = "")


  public Integer getCollectStatus() {
    return collectStatus;
  }

  public void setCollectStatus(Integer collectStatus) {
    this.collectStatus = collectStatus;
  }

  public IntelligenceResponse datajson(String datajson) {
    this.datajson = datajson;
    return this;
  }

  /**
   * Get datajson
   * @return datajson
  **/
  @ApiModelProperty(value = "")


  public String getDatajson() {
    return datajson;
  }

  public void setDatajson(String datajson) {
    this.datajson = datajson;
  }

  public IntelligenceResponse district(String district) {
    this.district = district;
    return this;
  }

  /**
   * Get district
   * @return district
  **/
  @ApiModelProperty(value = "")


  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public IntelligenceResponse fhpt(PriceTrendDo fhpt) {
    this.fhpt = fhpt;
    return this;
  }

  /**
   * Get fhpt
   * @return fhpt
  **/
  @ApiModelProperty(value = "")

  @Valid

  public PriceTrendDo getFhpt() {
    return fhpt;
  }

  public void setFhpt(PriceTrendDo fhpt) {
    this.fhpt = fhpt;
  }

  public IntelligenceResponse fhtp(PriceRatioDo fhtp) {
    this.fhtp = fhtp;
    return this;
  }

  /**
   * Get fhtp
   * @return fhtp
  **/
  @ApiModelProperty(value = "")

  @Valid

  public PriceRatioDo getFhtp() {
    return fhtp;
  }

  public void setFhtp(PriceRatioDo fhtp) {
    this.fhtp = fhtp;
  }

  public IntelligenceResponse layout(String layout) {
    this.layout = layout;
    return this;
  }

  /**
   * Get layout
   * @return layout
  **/
  @ApiModelProperty(value = "")


  public String getLayout() {
    return layout;
  }

  public void setLayout(String layout) {
    this.layout = layout;
  }

  public IntelligenceResponse totalPrice(Integer totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  /**
   * Get totalPrice
   * @return totalPrice
  **/
  @ApiModelProperty(value = "")


  public Integer getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(Integer totalPrice) {
    this.totalPrice = totalPrice;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntelligenceResponse intelligenceResponse = (IntelligenceResponse) o;
    return Objects.equals(this.backUrl, intelligenceResponse.backUrl) &&
        Objects.equals(this.collectStatus, intelligenceResponse.collectStatus) &&
        Objects.equals(this.datajson, intelligenceResponse.datajson) &&
        Objects.equals(this.district, intelligenceResponse.district) &&
        Objects.equals(this.fhpt, intelligenceResponse.fhpt) &&
        Objects.equals(this.fhtp, intelligenceResponse.fhtp) &&
        Objects.equals(this.layout, intelligenceResponse.layout) &&
        Objects.equals(this.totalPrice, intelligenceResponse.totalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(backUrl, collectStatus, datajson, district, fhpt, fhtp, layout, totalPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IntelligenceResponse {\n");
    
    sb.append("    backUrl: ").append(toIndentedString(backUrl)).append("\n");
    sb.append("    collectStatus: ").append(toIndentedString(collectStatus)).append("\n");
    sb.append("    datajson: ").append(toIndentedString(datajson)).append("\n");
    sb.append("    district: ").append(toIndentedString(district)).append("\n");
    sb.append("    fhpt: ").append(toIndentedString(fhpt)).append("\n");
    sb.append("    fhtp: ").append(toIndentedString(fhtp)).append("\n");
    sb.append("    layout: ").append(toIndentedString(layout)).append("\n");
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
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

