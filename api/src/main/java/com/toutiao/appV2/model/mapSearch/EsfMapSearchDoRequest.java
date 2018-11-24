package com.toutiao.appV2.model.mapSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * EsfMapSearchDoRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-23T06:53:25.085Z")
@Data
public class EsfMapSearchDoRequest   {
  @JsonProperty("areaId")
  private Integer areaId = null;

  @JsonProperty("beginArea")
  private Double beginArea = null;

  @JsonProperty("beginPrice")
  private Double beginPrice = null;

  @JsonProperty("cityId")
  private Integer cityId = null;

  @JsonProperty("distance")
  private Integer distance = null;

  @JsonProperty("districtId")
  private Integer districtId = null;

  @JsonProperty("endArea")
  private Double endArea = null;

  @JsonProperty("endPrice")
  private Double endPrice = null;

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

  @JsonProperty("layoutId")
  private List<Integer> layoutId = null;

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
  private Integer subwayStationId = null;


}

