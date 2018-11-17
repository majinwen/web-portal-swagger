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
 * UserFavoriteConditionRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T09:20:13.068Z")

public class UserFavoriteConditionRequest   {
  @JsonProperty("beginPrice")
  @ApiModelProperty(value = "起始价格", name = "beginPrice", required = false)
  private Double beginPrice = null;

  @JsonProperty("city")
  @ApiModelProperty(value = "城市", name = "city", required = false)
  private String city = null;

  @JsonProperty("districtId")
  @ApiModelProperty(value = "区域id", name = "districtId", required = false)
  @Valid
  private List<String> districtId = null;

  @JsonProperty("endPrice")
  @ApiModelProperty(value = "结束价格", name = "endPrice", required = false)
  private Double endPrice = null;

  @JsonProperty("flag")
  @ApiModelProperty(value = "标志id", name = "flag", required = false)
  private Integer flag = null;

  @JsonProperty("layoutId")
  @Valid
  @ApiModelProperty(value = "户型集", name = "layoutId", required = false)
  private List<String> layoutId = null;

  @JsonProperty("pageNum")
  @ApiModelProperty(value = "页数", name = "pageNum", required = false)
  private Integer pageNum = 1;

  @JsonProperty("pageSize")
  @ApiModelProperty(value = "每页数量", name = "pageSize", required = false)
  private Integer pageSize = 10;

  @JsonProperty("userId")
  @ApiModelProperty(value = "用户id", name = "userId", required = false)
  private Integer userId = null;

  public UserFavoriteConditionRequest beginPrice(Double beginPrice) {
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

  public UserFavoriteConditionRequest city(String city) {
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

  public UserFavoriteConditionRequest districtId(List<String> districtId) {
    this.districtId = districtId;
    return this;
  }

  public UserFavoriteConditionRequest addDistrictIdItem(String districtIdItem) {
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

  public UserFavoriteConditionRequest endPrice(Double endPrice) {
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

  public UserFavoriteConditionRequest flag(Integer flag) {
    this.flag = flag;
    return this;
  }

  /**
   * Get flag
   * @return flag
  **/
  @ApiModelProperty(value = "")


  public Integer getFlag() {
    return flag;
  }

  public void setFlag(Integer flag) {
    this.flag = flag;
  }

  public UserFavoriteConditionRequest layoutId(List<String> layoutId) {
    this.layoutId = layoutId;
    return this;
  }

  public UserFavoriteConditionRequest addLayoutIdItem(String layoutIdItem) {
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

  public UserFavoriteConditionRequest pageNum(Integer pageNum) {
    this.pageNum = pageNum;
    return this;
  }

  /**
   * Get pageNum
   * @return pageNum
  **/
  @ApiModelProperty(value = "")


  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public UserFavoriteConditionRequest pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * Get pageSize
   * @return pageSize
  **/
  @ApiModelProperty(value = "")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public UserFavoriteConditionRequest userId(Integer userId) {
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
    UserFavoriteConditionRequest userFavoriteConditionRequest = (UserFavoriteConditionRequest) o;
    return Objects.equals(this.beginPrice, userFavoriteConditionRequest.beginPrice) &&
        Objects.equals(this.city, userFavoriteConditionRequest.city) &&
        Objects.equals(this.districtId, userFavoriteConditionRequest.districtId) &&
        Objects.equals(this.endPrice, userFavoriteConditionRequest.endPrice) &&
        Objects.equals(this.flag, userFavoriteConditionRequest.flag) &&
        Objects.equals(this.layoutId, userFavoriteConditionRequest.layoutId) &&
        Objects.equals(this.pageNum, userFavoriteConditionRequest.pageNum) &&
        Objects.equals(this.pageSize, userFavoriteConditionRequest.pageSize) &&
        Objects.equals(this.userId, userFavoriteConditionRequest.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beginPrice, city, districtId, endPrice, flag, layoutId, pageNum, pageSize, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserFavoriteConditionRequest {\n");
    
    sb.append("    beginPrice: ").append(toIndentedString(beginPrice)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    districtId: ").append(toIndentedString(districtId)).append("\n");
    sb.append("    endPrice: ").append(toIndentedString(endPrice)).append("\n");
    sb.append("    flag: ").append(toIndentedString(flag)).append("\n");
    sb.append("    layoutId: ").append(toIndentedString(layoutId)).append("\n");
    sb.append("    pageNum: ").append(toIndentedString(pageNum)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
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

