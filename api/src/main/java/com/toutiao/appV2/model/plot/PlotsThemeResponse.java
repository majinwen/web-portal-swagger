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
 * PlotsThemeResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")
@Data
public class PlotsThemeResponse   {

  /**
   * 社区数量
   */
  @JsonProperty("communityCount")
  private Integer communityCount;

  @JsonProperty("data")
  @Valid
  private List<PlotsThemeDo> data = null;

  @JsonProperty("totalCount")
  private Long totalCount = null;


}

