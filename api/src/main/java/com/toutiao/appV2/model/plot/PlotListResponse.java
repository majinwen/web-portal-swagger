package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotListResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

@Data
public class PlotListResponse   {
  @JsonProperty("plotList")
  @Valid
  private List<PlotDetailsFewDo> plotList = null;

  @JsonProperty("totalCount")
  private Integer totalCount = null;

  @ApiModelProperty(value = "是否为猜你喜欢的数据", name = "isGuess")
  private Integer isGuess;
}

