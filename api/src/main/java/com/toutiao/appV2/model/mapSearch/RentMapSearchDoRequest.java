package com.toutiao.appV2.model.mapSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * RentMapSearchDoRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T10:54:50.943Z")
@Data
public class RentMapSearchDoRequest   {

  @ApiModelProperty(value = "商圈id")
  @JsonProperty("areaId")
  private Integer areaId = null;

  @ApiModelProperty(value = "起始面积")
  @JsonProperty("beginArea")
  private Double beginArea = null;

  @ApiModelProperty(value = "起始价格")
  @JsonProperty("beginPrice")
  private Double beginPrice = null;

  @ApiModelProperty(value = "右下维度")
  @JsonProperty("bottomRightLat")
  private Double bottomRightLat = null;

  @ApiModelProperty(value = "右下经度")
  @JsonProperty("bottomRightLon")
  private Double bottomRightLon = null;

  @ApiModelProperty(value = "城市id")
  @JsonProperty("cityId")
  private Integer cityId = null;

  @ApiModelProperty(value = "附近距离")
  @JsonProperty("distance")
  private Integer distance = null;

  @ApiModelProperty(value = "区域")
  @JsonProperty("districtId")
  private Integer districtId = null;

  @ApiModelProperty(value = "整租户型")
  @JsonProperty("elo")
  private String elo = null;

  @ApiModelProperty(value = "结束面积")
  @JsonProperty("endArea")
  private Double endArea = null;

  @ApiModelProperty(value = "结束价格")
  @JsonProperty("endPrice")
  private Double endPrice = null;

  @ApiModelProperty(value = "朝向")
  @JsonProperty("forwardId")
  @Valid
  private Integer[] forwardId = null;

  @ApiModelProperty(value = "组类型：区域district，商圈bizcircle，社区community")
  @JsonProperty("groupType")
  private String groupType = null;

  @ApiModelProperty(value = "楼龄[0-5]")
  @JsonProperty("houseYearId")
  private String houseYearId = null;

  @ApiModelProperty(value = "合租户型")
  @JsonProperty("jlo")
  private String jlo = null;

  @ApiModelProperty(value = "关键字")
  @JsonProperty("keyword")
  private String keyword = null;

  @ApiModelProperty(value = "标签")
  @JsonProperty("labelId")
  @Valid
  private List<Integer> labelId = null;

  @ApiModelProperty(value = "居室")
  @JsonProperty("layoutId")
  @Valid
  private Integer[] layoutId = null;

  @ApiModelProperty(value = "页码")
  @JsonProperty("pageNum")
  private Integer pageNum = 1;

  @ApiModelProperty(value = "每页数量")
  @JsonProperty("pageSize")
  private Integer pageSize = 10;

  @ApiModelProperty(value = "整租:1/合租:2/未知:3")
  @JsonProperty("rentType")
  private String rentType = null;

  @ApiModelProperty(value = "地铁线Id")
  @JsonProperty("subwayLineId")
  private Integer subwayLineId = null;

  @ApiModelProperty(value = "地铁站id")
  @JsonProperty("subwayStationId")
  private Integer subwayStationId = null;

  @ApiModelProperty(value = "左上维度")
  @JsonProperty("topLeftLat")
  private Double topLeftLat = null;

  @ApiModelProperty(value = "左上经度")
  @JsonProperty("topLeftLon")
  private Double topLeftLon = null;

  }

