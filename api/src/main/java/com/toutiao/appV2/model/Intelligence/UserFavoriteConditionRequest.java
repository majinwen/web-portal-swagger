package com.toutiao.appV2.model.Intelligence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.web.common.assertUtils.First;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UserFavoriteConditionRequest
 */

@Data
public class UserFavoriteConditionRequest   {
  @ApiModelProperty(name = "beginPrice", value = "起始价格")
  @JsonProperty("beginPrice")
  private Double beginPrice = 0.0;

  @ApiModelProperty(name = "city", value = "城市")
  @JsonProperty("city")
  private String city = null;

  @ApiModelProperty(name = "districtId", value = "区域id")
  @JsonProperty("districtId")
  private String[] districtId = null;

  @ApiModelProperty(name = "endPrice", value = "结束价格")
  @JsonProperty("endPrice")
  private Double endPrice = null;

  @ApiModelProperty(name = "flag", value = "是否有预设条件(0:无,1:有 默认0)")
  @JsonProperty("flag")
  private Integer flag = 0;

  @ApiModelProperty(name = "layoutId", value = "户型id")
  @JsonProperty("layoutId")
  private String[] layoutId;

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

  @ApiModelProperty(name = "subwayLineId", value = "地铁线id")
  @JsonProperty("subwayLineId")
  private String[] subwayLineId;

//  @ApiModelProperty(name = "rentType", value = "租房类型(1:整租，2:合租)")
//  @JsonProperty("rentType")
//  private Integer rentType;

  @ApiModelProperty(name = "conditionType", value = "定制条件类型(0:二手房,1:租房)")
  @JsonProperty("conditionType")
  private Integer conditionType;

  @JsonProperty("elo")
  @ApiParam("整租户型")
  private String[] elo;

  @JsonProperty("jlo")
  @ApiParam("合租户型")
  private String[] jlo;


}

