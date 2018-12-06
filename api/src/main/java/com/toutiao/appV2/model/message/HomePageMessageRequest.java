package com.toutiao.appV2.model.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * HomePageMessageRequest
 */
@Validated
@Data
public class HomePageMessageRequest   {
  @JsonProperty("conditionHouseDate")
  @ApiParam(value = " 符合找房条件的房源上新消息最后阅读时间")
  private long conditionHouseDate;

  @JsonProperty("favoritePlotDate")
  @ApiParam(value = " 关注小区房源上新消息最后阅读时间")
  private long favoritePlotDate;

  @JsonProperty("favoriteHouseDate")
  @ApiParam(value = " 关注二手房价格变动消息最后阅读时间")
  private long favoriteHouseDate;

  @JsonProperty("subscribeThemeDate")
  @ApiParam(value = " 订阅的主题有更新消息最后阅读时间")
  private long subscribeThemeDate;

  @JsonProperty("favoriteVillageEsfDate")
  @ApiParam(value = " 关注房源小区二手房新上消息最后阅读时间")
  private long favoriteVillageEsfDate;

  @JsonProperty("favoriteVillageRentDate")
  @ApiParam(value = " 关注房源小区租房新上消息最后阅读时间")
  private long favoriteVillageRentDate;

  @JsonProperty("esfShelvesDate")
  @ApiParam(value = " 关注二手房下架消息最后阅读时间")
  private long esfShelvesDate;

  @JsonProperty("rentShelvesDate")
  @ApiParam(value = " 关注租房下架消息最后阅读时间")
  private long rentShelvesDate;

  @JsonProperty("rentChangePriceDate")
  @ApiParam(value = " 关注租房价格变动消息最后阅读时间")
  private long rentChangePriceDate;

}

