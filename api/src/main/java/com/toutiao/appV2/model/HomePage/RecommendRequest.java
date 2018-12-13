package com.toutiao.appV2.model.HomePage;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * RecommendRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:33:40.959Z")
@Data
public class RecommendRequest   {
  @JsonProperty("beginPrice")
  @ApiModelProperty(value = "起始价格", name = "beginPrice", required = false)
  private Double beginPrice = null;

  @JsonProperty("endPrice")
  @ApiModelProperty(value = "结束价格", name = "endPrice", required = false)
  private Double endPrice = null;

  @JsonProperty("districtId")
  @ApiModelProperty(value = "区域id", name = "districtId", required = false)
  @Valid
  private Integer[] districtId = null;


}

