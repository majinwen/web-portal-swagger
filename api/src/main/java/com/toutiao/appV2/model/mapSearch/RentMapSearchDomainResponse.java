package com.toutiao.appV2.model.mapSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * RentMapSearchDomainResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T10:54:50.943Z")
@Data
public class RentMapSearchDomainResponse   {
  @ApiModelProperty(value = "当前可视界面描述")
  @JsonProperty("data")
  @Valid
  private List<RentMapSearchDoResponse> data = null;

  @ApiModelProperty(value = "返回结果")
  @JsonProperty("hit")
  private String hit = null;
}

