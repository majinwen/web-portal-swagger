package com.toutiao.appV2.model.mapSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

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
  @ApiModelProperty(value = "商圈")
  private Integer[] areaId = null;

  @JsonProperty("beginArea")
  @ApiModelProperty(value = "商圈")
  private double beginArea;

  @JsonProperty("beginPrice")
  private double beginPrice;

  @JsonProperty("cityId")
  private Integer cityId = null;

  @JsonProperty("distance")
  private Integer distance = null;

  @JsonProperty("districtId")
  private Integer districtId = null;

  @JsonProperty("endArea")
  private double endArea;

  @JsonProperty("endPrice")
  private double endPrice;

  @JsonProperty("forwardId")
  private List<Integer> forwardId = null;

  @JsonProperty("groupType")
  private String groupType = null;

  @JsonProperty("houseYearId")
  private String houseYearId = null;

  @JsonProperty("keyword")
  private String keyword = null;

  @JsonProperty("labelId")
  private List<Integer> labelId = null;

  @JsonProperty("lat")
  private Double lat = null;

  @JsonProperty("layoutId")
  private List<Integer> layoutId = null;

  @JsonProperty("lon")
  private Double lon = null;

  @JsonProperty("maxLatitude")
  private Double maxLatitude = null;

  @JsonProperty("maxLongitude")
  private Double maxLongitude = null;

  @JsonProperty("minLatitude")
  private Double minLatitude = null;

  @JsonProperty("minLongitude")
  private Double minLongitude = null;

  @JsonProperty("pageNum")
  private Integer pageNum = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("subwayLineId")
  private Integer subwayLineId = null;

  @JsonProperty("subwayStationId")
  private Integer[] subwayStationId = null;

}

