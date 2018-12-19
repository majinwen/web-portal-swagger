package com.toutiao.appV2.model.rent;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * RentDetailFewResponseList
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T07:33:42.688Z")

@Data
public class RentDetailFewResponseList   {
  @JsonProperty("rentDetailsList")
  @Valid
  @ApiModelProperty("出租房源详情")
  private List<RentDetailFewResponse> rentDetailsList = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  @ApiModelProperty(value = "是否为猜你喜欢的数据", name = "isGuess")
  private Integer isGuess;
}

