package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.web.common.assertUtils.First;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UserFavoriteConditionRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:50:01.627Z")

public class UserFavoriteConditionRequest   {
  @ApiModelProperty(name = "beginPrice", value = "起始价格")
  @JsonProperty("beginPrice")
  private Double beginPrice = 0.0;

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

  @ApiModelProperty(name = "flag", value = "是否有预设条件(0:无,1:有 默认0)")
  @JsonProperty("flag")
  private Integer flag = 0;

  @ApiModelProperty(name = "layoutId", value = "户型id")
  @JsonProperty("layoutId")
  @Valid
  private List<String> layoutId = null;

  @ApiModelProperty(name = "pageNum", value = "当前页")
  @JsonProperty("pageNum")
  private Integer pageNum = 1;

  @ApiModelProperty(name = "pageSize", value = "每页大小")
  @JsonProperty("pageSize")
  private Integer pageSize = 10;

  @ApiModelProperty(name = "userId", value = "用户id")
  @JsonProperty("userId")
  @NotNull(groups = {First.class},message = "用户id不能为空")
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

