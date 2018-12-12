package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@Data
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
  private String[] districtId = null;

  @ApiModelProperty(name = "endPrice", value = "结束价格")
  @JsonProperty("endPrice")
  private Double endPrice = null;

  @ApiModelProperty(name = "layoutId", value = "户型id")
  @JsonProperty("layoutId")
  @Valid
  private String[] layoutId = null;

  @ApiModelProperty(name = "userId", value = "用户id")
  @JsonProperty("userId")
  private Integer userId = null;

  @ApiModelProperty(name = "subwayLineId", value = "地铁线id")
  @JsonProperty("subwayLineId")
  @Valid
  private String[] subwayLineId = null;

  @ApiModelProperty(name = "conditionType", value = "定制找房类型")
  @JsonProperty("conditionType")
  private Integer conditionType = null;

  @ApiModelProperty(name = "rentType", value = "租房类型1整租2合租")
  @JsonProperty("rentType")
  private Integer rentType = null;


}

