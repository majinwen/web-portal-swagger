package com.toutiao.appV2.model.mapSearch;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * NewHouseMapSearchRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T10:59:38.870Z")
@Data
public class NewHouseMapSearchRequest   {
  @JsonProperty("areaId")
  private List<Integer> areaId = null;

  @JsonProperty("beginArea")
  private double beginArea;

  @JsonProperty("beginPrice")
  private double beginPrice;

  @JsonProperty("beginTotalPrice")
  private double beginTotalPrice;

  @JsonProperty("cityId")
  private Integer cityId = null;

  @JsonProperty("districtId")
  private Integer districtId = null;

  @JsonProperty("endArea")
  private double endArea;

  @JsonProperty("endPrice")
  private double endPrice;

  @JsonProperty("endTotalPrice")
  private double endTotalPrice;

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

  @JsonProperty("saleStatusId")
  private List<Integer> saleStatusId = null;

  @JsonProperty("subwayLineId")
  private Integer subwayLineId = null;

  @JsonProperty("subwayStationId")
  private Integer[] subwayStationId = null;


}

