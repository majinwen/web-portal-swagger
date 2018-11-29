package com.toutiao.appV2.model.mapSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * EsfMapSearchRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T09:35:01.637Z")
@Data
public class EsfMapSearchRequest   {
  @JsonProperty("areaId")
  @ApiParam("商圈id")
  private Integer[] areaId = null;

  @JsonProperty("beginArea")
  @ApiParam("起始面积")
  private double beginArea;

  @JsonProperty("beginPrice")
  @ApiParam("起始价格")
  private double beginPrice;

  @JsonProperty("cityId")
  @ApiParam("城市id")
  private Integer cityId = null;

  @JsonProperty("distance")
  @ApiParam("附近1,3,5km")
  private Integer distance = null;

  @JsonProperty("districtId")
  @ApiParam("区县id")
  private Integer districtId = null;

  @JsonProperty("endArea")
  @ApiParam("结束面积")
  private double endArea;

  @JsonProperty("endPrice")
  @ApiParam("结束价格")
  private double endPrice;

  @JsonProperty("forwardId")
  @ApiParam("朝向")
  private List<Integer> forwardId = null;

  @JsonProperty("groupType")
  @ApiParam("组类型：区域district，商圈bizcircle，社区community")
  private String groupType = null;

  @JsonProperty("houseYearId")
  @ApiParam("楼龄[0-5]")
  private String houseYearId = null;

  @JsonProperty("keyword")
  @ApiParam("关键字")
  private String keyword = null;

  @JsonProperty("labelId")
  @ApiParam("标签")
  private List<Integer> labelId = null;

  @JsonProperty("lat")
  @ApiParam("y坐标")
  private Double lat = null;

  @JsonProperty("layoutId")
  @ApiParam("居室")
  private List<Integer> layoutId = null;

  @JsonProperty("lon")
  @ApiParam("x坐标")
  private Double lon = null;

  @JsonProperty("maxLatitude")
  @ApiParam("右上 纬度")
  private Double maxLatitude = null;

  @JsonProperty("maxLongitude")
  @ApiParam("右上 经度")
  private Double maxLongitude = null;

  @JsonProperty("minLatitude")
  @ApiParam("左下 纬度")
  private Double minLatitude = null;

  @JsonProperty("minLongitude")
  @ApiParam("左下 经度")
  private Double minLongitude = null;

  @JsonProperty("pageNum")
  @ApiParam(value = "每页的数量", defaultValue = "10")
  private Integer pageNum = 10;

  @JsonProperty("pageSize")
  @ApiParam(value = "页码", defaultValue = "1")
  private Integer pageSize = 1;

  @JsonProperty("subwayLineId")
  @ApiParam("地铁线id")
  private Integer subwayLineId = null;

  @JsonProperty("subwayStationId")
  @ApiParam("地铁站id")
  private Integer[] subwayStationId = null;

  @JsonProperty("newcode")
  @ApiParam("小区id")
  private Integer newcode = null;
}

