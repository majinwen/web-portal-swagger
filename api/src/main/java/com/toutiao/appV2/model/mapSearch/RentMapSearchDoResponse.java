package com.toutiao.appV2.model.mapSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * RentMapSearchDoResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T10:54:50.943Z")
@Data
public class RentMapSearchDoResponse   {
  @ApiModelProperty(value = "数量")
  @JsonProperty("count")
  private Integer count = null;

  @ApiModelProperty(value = "描述")
  @JsonProperty("desc")
  private String desc = null;

  @ApiModelProperty(value = "编号")
  @JsonProperty("id")
  private Integer id = null;

  @ApiModelProperty(value = "维度")
  @JsonProperty("latitude")
  private Double latitude = null;

  @ApiModelProperty(value = "经度")
  @JsonProperty("longitude")
  private Double longitude = null;

  @ApiModelProperty(value = "名称")
  @JsonProperty("name")
  private String name = null;

  @ApiModelProperty(value = "价格")
  @JsonProperty("price")
  private Double price = null;
}

