package com.toutiao.appV2.model.rent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * RentHouseRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

@Data
public class RentHouseRequest   {
  @JsonProperty("areaId")
  @ApiParam("商圈id")
  @ApiModelProperty("商圈id")
  private Integer areaId = null;

  @JsonProperty("beginArea")
  @ApiParam("起始面积")
  @ApiModelProperty("起始面积")
  private double beginArea;

  @JsonProperty("beginPrice")
  @ApiParam("起始价格")
  @ApiModelProperty("起始价格")
  private double beginPrice;

  @JsonProperty("cityId")
  @ApiParam("城市id")
  @ApiModelProperty("城市id")
  private Integer cityId = null;

  @JsonProperty("distance")
  @ApiParam("附近距离")
  @ApiModelProperty("附近距离")
  private Integer distance = null;

  @JsonProperty("districtId")
  @ApiParam("区域")
  @ApiModelProperty("区域")
  private Integer districtId = null;

  @JsonProperty("elo")
  @ApiParam("整租户型")
  @ApiModelProperty("整租户型")
  private String elo = null;

  @JsonProperty("endArea")
  @ApiParam("结束面积")
  @ApiModelProperty("结束面积")
  private double endArea;

  @JsonProperty("endPrice")
  @ApiParam("结束价格")
  @ApiModelProperty("结束价格")
  private double endPrice;

  @JsonProperty("forwardId")
  @ApiParam("朝向")
  @ApiModelProperty("朝向")
  private List<Integer> forwardId = null;

  @JsonProperty("houseYearId")
  @ApiParam("楼龄[0-5]")
  @ApiModelProperty("楼龄[0-5]")
  private String houseYearId = null;

  @JsonProperty("jlo")
  @ApiParam("合租户型")
  @ApiModelProperty("合租户型")
  private String jlo = null;

  @JsonProperty("keyword")
  @ApiParam("关键字")
  @ApiModelProperty("关键字")
  private String keyword = null;

  @JsonProperty("labelId")
  @ApiParam("标签")
  @ApiModelProperty("标签")
  private List<Integer> labelId = null;

  @JsonProperty("lat")
  @ApiParam("维度")
  @ApiModelProperty("维度")
  private Double lat = null;

  @JsonProperty("layoutId")
  @ApiParam("居室")
  @ApiModelProperty("居室")
  private List<Integer> layoutId = null;

  @JsonProperty("lon")
  @ApiParam("经度")
  @ApiModelProperty("经度")
  private Double lon = null;

  @JsonProperty("pageNum")
  @ApiParam(value = "页码", defaultValue = "1")
  @ApiModelProperty(value = "页码")
  private Integer pageNum = 1;

  @JsonProperty("pageSize")
  @ApiParam(value = "页容量", defaultValue = "10")
  @ApiModelProperty(value = "页容量")
  private Integer pageSize = 10;

  @JsonProperty("rentType")
  @ApiParam("整租:1/合租:2/未知:3")
  @ApiModelProperty("整租:1/合租:2/未知:3")
  private String rentType = null;

  @JsonProperty("source")
  @ApiParam("来源id")
  @ApiModelProperty("来源id")
  private String source = null;

  @JsonProperty("subwayLineId")
  @ApiParam("地铁线Id")
  @ApiModelProperty("地铁线Id")
  private Integer subwayLineId = null;

  @JsonProperty("subwayStationId")
  @ApiParam("地铁站id")
  @ApiModelProperty("地铁站id")
  private Integer subwayStationId = null;

  @JsonProperty("uid")
  @NotEmpty(groups = {First.class},message = "缺少查询uid")
  @ApiParam(value = "租房推优查询uid")
  @ApiModelProperty(value = "租房推优查询uid")
  private String uid = null;

  @JsonProperty("time")
  @ApiParam(value = "时间")
  @ApiModelProperty(value = "时间(单位:min)")
  @NotEmpty(groups = {Second.class},message = "缺少时间")
  private String time;

  @ApiParam(value = "交通类型(0:步行,1:骑车,2:公交,3:驾车)")
  @ApiModelProperty(value = "交通类型(0:不行,1:骑车,2:公交,3:驾车)")
  @JsonProperty("trafficType")
  @NotEmpty(groups = {Second.class},message = "缺少交通类型")
  private String trafficType;

}

