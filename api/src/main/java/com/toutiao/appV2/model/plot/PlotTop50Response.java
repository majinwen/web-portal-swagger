package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.plot.CommunityReviewDo;
import com.toutiao.app.domain.plot.PlotMarketDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PlotTop50Response
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")
@Data
public class PlotTop50Response   {
  @JsonProperty("area")
  @ApiModelProperty("区域")
  private String area = null;

  @JsonProperty("avgPrice")
  @ApiModelProperty("均价")
  private Double avgPrice = null;

  @JsonProperty("houseCount")
  @ApiModelProperty("待售二手房数量")
  private Integer houseCount = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("lowestPrice")
  @ApiModelProperty("最低价")
  private Double lowestPrice = null;

  @JsonProperty("photo")
  @Valid
  @ApiModelProperty("小区照片")
  private List<String> photo = null;

  @JsonProperty("rankAvgInBizcircle")
  @ApiModelProperty("在商圈种排名")
  private Integer rankAvgInBizcircle = null;

  @JsonProperty("rc")
  @ApiModelProperty("小区名称")
  private String rc = null;

  @JsonProperty("tradingArea")
  @ApiModelProperty("商圈")
  private String tradingArea = null;

  @JsonProperty("trafficInformation")
  @ApiModelProperty("近地铁描述")
  private String trafficInformation = null;

//  @JsonProperty("avgDeal")
//  @ApiModelProperty("平均成交周期")
//  private Integer avgDeal;

  @JsonProperty("districtHotSort")
  @ApiModelProperty("小区所在区域热度榜")
  private Integer districtHotSort;

  @JsonProperty("recommendReason")
  @ApiModelProperty("推荐理由")
  private CommunityReviewDo recommendReason;

  @JsonProperty("recommendReason")
  @ApiModelProperty("小区推荐标签")
  private List<String> recommendBuildTagsName;

  @JsonProperty("label")
  @ApiModelProperty("小区自带标签")
  private List<String> label;

  @JsonProperty("titlePhoto")
  @ApiModelProperty("标题图")
  private String titlePhoto;

  @JsonProperty("plotMarketDomain")
  @ApiModelProperty("小区行情")
  private PlotMarketDomain plotMarketDomain;

  @JsonProperty("tagsName")
  @ApiModelProperty("标签")
  private String tagsName;



}

