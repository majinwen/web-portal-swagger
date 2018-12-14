package com.toutiao.appV2.model.HomePage;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * RecommendEsf5Request
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T09:20:13.068Z")
@Data
public class RecommendEsf5Request   {
  @JsonProperty("areaId")
  @ApiModelProperty(value = "商圈Id(必传)", name = "areaId", required = true)
  private Integer areaId;

  @JsonProperty("beginArea")
  @ApiModelProperty(value = "最小建筑面积(必传)", name = "beginArea", required = true)
  private double beginArea;

  @JsonProperty("beginPrice")
  @ApiModelProperty(value = "起始价格(必传)", name = "beginPrice", required = true)
  private double beginPrice;

  @JsonProperty("cityId")
  @ApiModelProperty(value = "城市id(必传)", name = "cityId", required = true)
  private Integer cityId;

  @JsonProperty("districtId")
  @ApiModelProperty(value = "区域id", name = "districtId", required = false)
  private Integer districtId ;

  @JsonProperty("districtIds")
  @ApiModelProperty(value = "区域id集", name = "districtId", required = false)
  @Valid
  private Integer[] districtIds;

  @JsonProperty("endArea")
  @ApiModelProperty(value = "最大建筑面积(必传)", name = "endArea", required = true)
  private double endArea;

  @JsonProperty("endPrice")
  @ApiModelProperty(value = "结束价格(必传)", name = "endPrice", required = true)
  private double endPrice;

  @JsonProperty("forwardId")
  @ApiModelProperty(value = "朝向集", name = "forwardId", required = false)
  @Valid
  private Integer[] forwardId;

  @JsonProperty("houseYearId")
  @ApiModelProperty(value = "楼龄", name = "houseYearId", required = false)
  private String houseYearId;

  @JsonProperty("keyword")
  @ApiModelProperty(value = "关键字", name = "keyword", required = false)
  private String keyword;

  @JsonProperty("labelId")
  @Valid
  @ApiModelProperty(value = "标签集", name = "labelId", required = false)
  private Integer[] labelId;

  @JsonProperty("layoutId")
  @Valid
  @ApiModelProperty(value = "户型集", name = "layoutId", required = false)
  private Integer[] layoutId;

  @JsonProperty("pageNum")
  @ApiModelProperty(value = "页数", name = "pageNum", required = false)
  private Integer pageNum = 1;

  @JsonProperty("pageSize")
  @ApiModelProperty(value = "每页数量", name = "pageSize", required = false)
  private Integer pageSize = 10;

  @JsonProperty("sort")
  @ApiModelProperty(value = "排序", name = "sort", required = false)
  private String sort;

//  @JsonProperty("sortFile")
//  @ApiModelProperty(value = "排序字段", name = "sortFile", required = false)
//  private String sortFile = "recommendHouseTopicSort";

  @JsonProperty("subwayLineId")
  @ApiModelProperty(value = "地铁线id", name = "subwayLineId", required = false)
  private Integer subwayLineId;

  @JsonProperty("subwayStationId")
  @ApiModelProperty(value = "地铁站id", name = "subwayStationId", required = false)
  private Integer subwayStationId;

}

