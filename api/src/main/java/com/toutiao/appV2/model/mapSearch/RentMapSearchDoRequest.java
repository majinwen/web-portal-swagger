package com.toutiao.appV2.model.mapSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * RentMapSearchDoRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T10:54:50.943Z")
@Data
public class RentMapSearchDoRequest   {

  @ApiModelProperty(value = "商圈id")
  @JsonProperty("areaId")
  private Integer[] areaId = null;

  @ApiModelProperty(value = "起始面积")
  @JsonProperty("beginArea")
  private Double beginArea = null;

  @ApiModelProperty(value = "起始价格")
  @JsonProperty("beginPrice")
  private Double beginPrice = null;

  @ApiModelProperty(value = "右上维度")
  @JsonProperty("maxLatitude")
  private Double maxLatitude = null;

  @ApiModelProperty(value = "右上经度")
  @JsonProperty("maxLongitude")
  private Double maxLongitude = null;

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
  private Integer[] forwardId = null;

  @ApiModelProperty(value = "组类型：区域district，商圈bizcircle，社区community, 楼盘build, 地铁线line, 地铁站station")
  @JsonProperty("groupType")
//  @HouseTypeValidator(value = "district,bizcircle,community",message = "组类型错误")
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
  private Integer[] labelId = null;

  @ApiModelProperty(value = "居室")
  @JsonProperty("layoutId")
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
  private Integer[] subwayStationId = null;

  @ApiModelProperty(value = "左下维度")
  @JsonProperty("minLatitude")
  private Double minLatitude = null;

  @ApiModelProperty(value = "左下经度")
  @JsonProperty("minLongitude")
  private Double minLongitude = null;

  @ApiModelProperty(value = "中心维度")
  @JsonProperty("centerLat")
  private Double centerLat = null;

  @ApiModelProperty(value = "中心经度")
  @JsonProperty("centertLon")
  private Double centertLon = null;

  @ApiModelProperty(value = "小区id")
  @JsonProperty("newcode")
  private Integer newcode;
  }

