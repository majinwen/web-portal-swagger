package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotTop50Response
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

public class PlotTop50Response   {
  @JsonProperty("area")
  private String area = null;

  @JsonProperty("avgPrice")
  private Double avgPrice = null;

  @JsonProperty("houseCount")
  private Integer houseCount = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("lowestPrice")
  private Double lowestPrice = null;

  @JsonProperty("photo")
  @Valid
  private List<String> photo = null;

  @JsonProperty("rankAvgInBizcircle")
  private Integer rankAvgInBizcircle = null;

  @JsonProperty("rc")
  private String rc = null;

  @JsonProperty("tradingArea")
  private String tradingArea = null;

  @JsonProperty("trafficInformation")
  private String trafficInformation = null;

  public PlotTop50Response area(String area) {
    this.area = area;
    return this;
  }

  /**
   * Get area
   * @return area
  **/
  @ApiModelProperty(value = "")


  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public PlotTop50Response avgPrice(Double avgPrice) {
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

  public PlotTop50Response houseCount(Integer houseCount) {
    this.houseCount = houseCount;
    return this;
  }

  /**
   * Get houseCount
   * @return houseCount
  **/
  @ApiModelProperty(value = "")


  public Integer getHouseCount() {
    return houseCount;
  }

  public void setHouseCount(Integer houseCount) {
    this.houseCount = houseCount;
  }

  public PlotTop50Response id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PlotTop50Response lowestPrice(Double lowestPrice) {
    this.lowestPrice = lowestPrice;
    return this;
  }

  /**
   * Get lowestPrice
   * @return lowestPrice
  **/
  @ApiModelProperty(value = "")


  public Double getLowestPrice() {
    return lowestPrice;
  }

  public void setLowestPrice(Double lowestPrice) {
    this.lowestPrice = lowestPrice;
  }

  public PlotTop50Response photo(List<String> photo) {
    this.photo = photo;
    return this;
  }

  public PlotTop50Response addPhotoItem(String photoItem) {
    if (this.photo == null) {
      this.photo = new ArrayList<String>();
    }
    this.photo.add(photoItem);
    return this;
  }

  /**
   * Get photo
   * @return photo
  **/
  @ApiModelProperty(value = "")


  public List<String> getPhoto() {
    return photo;
  }

  public void setPhoto(List<String> photo) {
    this.photo = photo;
  }

  public PlotTop50Response rankAvgInBizcircle(Integer rankAvgInBizcircle) {
    this.rankAvgInBizcircle = rankAvgInBizcircle;
    return this;
  }

  /**
   * Get rankAvgInBizcircle
   * @return rankAvgInBizcircle
  **/
  @ApiModelProperty(value = "")


  public Integer getRankAvgInBizcircle() {
    return rankAvgInBizcircle;
  }

  public void setRankAvgInBizcircle(Integer rankAvgInBizcircle) {
    this.rankAvgInBizcircle = rankAvgInBizcircle;
  }

  public PlotTop50Response rc(String rc) {
    this.rc = rc;
    return this;
  }

  /**
   * Get rc
   * @return rc
  **/
  @ApiModelProperty(value = "")


  public String getRc() {
    return rc;
  }

  public void setRc(String rc) {
    this.rc = rc;
  }

  public PlotTop50Response tradingArea(String tradingArea) {
    this.tradingArea = tradingArea;
    return this;
  }

  /**
   * Get tradingArea
   * @return tradingArea
  **/
  @ApiModelProperty(value = "")


  public String getTradingArea() {
    return tradingArea;
  }

  public void setTradingArea(String tradingArea) {
    this.tradingArea = tradingArea;
  }

  public PlotTop50Response trafficInformation(String trafficInformation) {
    this.trafficInformation = trafficInformation;
    return this;
  }

  /**
   * Get trafficInformation
   * @return trafficInformation
  **/
  @ApiModelProperty(value = "")


  public String getTrafficInformation() {
    return trafficInformation;
  }

  public void setTrafficInformation(String trafficInformation) {
    this.trafficInformation = trafficInformation;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlotTop50Response plotTop50Response = (PlotTop50Response) o;
    return Objects.equals(this.area, plotTop50Response.area) &&
        Objects.equals(this.avgPrice, plotTop50Response.avgPrice) &&
        Objects.equals(this.houseCount, plotTop50Response.houseCount) &&
        Objects.equals(this.id, plotTop50Response.id) &&
        Objects.equals(this.lowestPrice, plotTop50Response.lowestPrice) &&
        Objects.equals(this.photo, plotTop50Response.photo) &&
        Objects.equals(this.rankAvgInBizcircle, plotTop50Response.rankAvgInBizcircle) &&
        Objects.equals(this.rc, plotTop50Response.rc) &&
        Objects.equals(this.tradingArea, plotTop50Response.tradingArea) &&
        Objects.equals(this.trafficInformation, plotTop50Response.trafficInformation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(area, avgPrice, houseCount, id, lowestPrice, photo, rankAvgInBizcircle, rc, tradingArea, trafficInformation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlotTop50Response {\n");
    
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    avgPrice: ").append(toIndentedString(avgPrice)).append("\n");
    sb.append("    houseCount: ").append(toIndentedString(houseCount)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lowestPrice: ").append(toIndentedString(lowestPrice)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    rankAvgInBizcircle: ").append(toIndentedString(rankAvgInBizcircle)).append("\n");
    sb.append("    rc: ").append(toIndentedString(rc)).append("\n");
    sb.append("    tradingArea: ").append(toIndentedString(tradingArea)).append("\n");
    sb.append("    trafficInformation: ").append(toIndentedString(trafficInformation)).append("\n");
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

