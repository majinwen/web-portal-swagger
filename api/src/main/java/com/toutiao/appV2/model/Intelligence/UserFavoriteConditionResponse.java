package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UserFavoriteConditionResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:50:01.627Z")

public class UserFavoriteConditionResponse   {
  @ApiModelProperty(name = "beginPrice", value = "起始价格")
  @JsonProperty("beginPrice")
  private Double beginPrice = null;

  @ApiModelProperty(name = "city", value = "城市")
  @JsonProperty("city")
  private String city = null;

  @ApiModelProperty(name = "districtId", value = "区域id")
  @JsonProperty("districtId")
  @Valid
  private List<String> districtId = null;

  @ApiModelProperty(name = "endPrice", value = "结束价格")
  @JsonProperty("endPrice")
  private Double endPrice = null;

  @ApiModelProperty(name = "layoutId", value = "户型id")
  @JsonProperty("layoutId")
  @Valid
  private List<String> layoutId = null;

  @ApiModelProperty(name = "userId", value = "用户id")
  @JsonProperty("userId")
  private Integer userId = null;

  public UserFavoriteConditionResponse beginPrice(Double beginPrice) {
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

  public UserFavoriteConditionResponse city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  **/
  @ApiModelProperty(value = "")


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public UserFavoriteConditionResponse districtId(List<String> districtId) {
    this.districtId = districtId;
    return this;
  }

  public UserFavoriteConditionResponse addDistrictIdItem(String districtIdItem) {
    if (this.districtId == null) {
      this.districtId = new ArrayList<String>();
    }
    this.districtId.add(districtIdItem);
    return this;
  }

  /**
   * Get districtId
   * @return districtId
  **/
  @ApiModelProperty(value = "")


  public List<String> getDistrictId() {
    return districtId;
  }

  public void setDistrictId(List<String> districtId) {
    this.districtId = districtId;
  }

  public UserFavoriteConditionResponse endPrice(Double endPrice) {
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

  public UserFavoriteConditionResponse layoutId(List<String> layoutId) {
    this.layoutId = layoutId;
    return this;
  }

  public UserFavoriteConditionResponse addLayoutIdItem(String layoutIdItem) {
    if (this.layoutId == null) {
      this.layoutId = new ArrayList<String>();
    }
    this.layoutId.add(layoutIdItem);
    return this;
  }

  /**
   * Get layoutId
   * @return layoutId
  **/
  @ApiModelProperty(value = "")


  public List<String> getLayoutId() {
    return layoutId;
  }

  public void setLayoutId(List<String> layoutId) {
    this.layoutId = layoutId;
  }

  public UserFavoriteConditionResponse userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserFavoriteConditionResponse userFavoriteConditionResponse = (UserFavoriteConditionResponse) o;
    return Objects.equals(this.beginPrice, userFavoriteConditionResponse.beginPrice) &&
        Objects.equals(this.city, userFavoriteConditionResponse.city) &&
        Objects.equals(this.districtId, userFavoriteConditionResponse.districtId) &&
        Objects.equals(this.endPrice, userFavoriteConditionResponse.endPrice) &&
        Objects.equals(this.layoutId, userFavoriteConditionResponse.layoutId) &&
        Objects.equals(this.userId, userFavoriteConditionResponse.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beginPrice, city, districtId, endPrice, layoutId, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserFavoriteConditionResponse {\n");
    
    sb.append("    beginPrice: ").append(toIndentedString(beginPrice)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
    sb.append("    endPrice: ").append(toIndentedString(endPrice)).append("\n");
    sb.append("    layoutId: ").append(toIndentedString(layoutId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

