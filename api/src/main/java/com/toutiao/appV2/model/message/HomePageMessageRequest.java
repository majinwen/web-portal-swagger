package com.toutiao.appV2.model.message;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * HomePageMessageRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T04:11:04.091Z")

public class HomePageMessageRequest   {
  @JsonProperty("conditionHouseDate")
  @ApiParam(value = " 符合找房条件的房源上新消息最后阅读时间")
  private long conditionHouseDate;

  @JsonProperty("favoritePlotDate")
  @ApiParam(value = " 关注小区房源上新消息最后阅读时间")
  private long favoritePlotDate;

  @JsonProperty("favoriteHouseDate")
  @ApiParam(value = " 关注房源价格变动消息最后阅读时间")
  private long favoriteHouseDate;

  @JsonProperty("subscribeThemeDate")
  @ApiParam(value = " 订阅的主题有更新消息最后阅读时间")
  private long subscribeThemeDate;

  public HomePageMessageRequest conditionHouseDate(Long conditionHouseDate) {
    this.conditionHouseDate = conditionHouseDate;
    return this;
  }

  /**
   * Get conditionHouseDate
   * @return conditionHouseDate
  **/
  @ApiModelProperty(value = "")


  public Long getConditionHouseDate() {
    return conditionHouseDate;
  }

  public void setConditionHouseDate(Long conditionHouseDate) {
    this.conditionHouseDate = conditionHouseDate;
  }

  public HomePageMessageRequest favoritePlotDate(Long favoritePlotDate) {
    this.favoritePlotDate = favoritePlotDate;
    return this;
  }

  /**
   * Get favoritePlotDate
   * @return favoritePlotDate
  **/
  @ApiModelProperty(value = "")


  public Long getFavoritePlotDate() {
    return favoritePlotDate;
  }

  public void setFavoritePlotDate(Long favoritePlotDate) {
    this.favoritePlotDate = favoritePlotDate;
  }

  public HomePageMessageRequest favoriteHouseDate(Long favoriteHouseDate) {
    this.favoriteHouseDate = favoriteHouseDate;
    return this;
  }

  /**
   * Get favoriteHouseDate
   * @return favoriteHouseDate
  **/
  @ApiModelProperty(value = "")


  public Long getFavoriteHouseDate() {
    return favoriteHouseDate;
  }

  public void setFavoriteHouseDate(Long favoriteHouseDate) {
    this.favoriteHouseDate = favoriteHouseDate;
  }

  public HomePageMessageRequest subscribeThemeDate(Long subscribeThemeDate) {
    this.subscribeThemeDate = subscribeThemeDate;
    return this;
  }

  /**
   * Get subscribeThemeDate
   * @return subscribeThemeDate
  **/
  @ApiModelProperty(value = "")


  public Long getSubscribeThemeDate() {
    return subscribeThemeDate;
  }

  public void setSubscribeThemeDate(Long subscribeThemeDate) {
    this.subscribeThemeDate = subscribeThemeDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HomePageMessageRequest homePageMessageRequest = (HomePageMessageRequest) o;
    return Objects.equals(this.conditionHouseDate, homePageMessageRequest.conditionHouseDate) &&
        Objects.equals(this.favoritePlotDate, homePageMessageRequest.favoritePlotDate) &&
        Objects.equals(this.favoriteHouseDate, homePageMessageRequest.favoriteHouseDate) &&
        Objects.equals(this.subscribeThemeDate, homePageMessageRequest.subscribeThemeDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conditionHouseDate, favoritePlotDate, favoriteHouseDate, subscribeThemeDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HomePageMessageRequest {\n");
    
    sb.append("    conditionHouseDate: ").append(toIndentedString(conditionHouseDate)).append("\n");
    sb.append("    favoritePlotDate: ").append(toIndentedString(favoritePlotDate)).append("\n");
    sb.append("    favoriteHouseDate: ").append(toIndentedString(favoriteHouseDate)).append("\n");
    sb.append("    subscribeThemeDate: ").append(toIndentedString(subscribeThemeDate)).append("\n");
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

