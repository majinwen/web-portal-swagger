package com.toutiao.appV2.model.plot;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toutiao.app.domain.plot.CommunityReviewDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * PlotsThemeDo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

@Data
public class PlotsThemeDo   {
  /**
   * 小区编号
   */
  @ChangeName("buildingId")
  @ApiModelProperty("小区编号buildingId")
  private Integer id;

  /**
   * 小区名称
   */
  @ApiModelProperty("小区名称buildingName")
  @ChangeName("buildingName")
  private String rc;

  /**
   * 小区照片
   */
  @ChangeName("buildingImages")
  @ApiModelProperty("小区照片buildingImages")
  private String[] photo;

  /**
   * 区域编号
   */
  @ApiModelProperty("区域编号districtId")
  @ChangeName("districtId")
  private String areaId;

  /**
   * 区域
   */
  @ChangeName("districtName")
  @ApiModelProperty("区域districtName")
  private String area;

  /**
   * 商圈编号
   */
  @ChangeName("areaId")
  @ApiModelProperty("商圈编号areaId")
  private String tradingAreaId;

  /**
   * 商圈
   */
  @ChangeName("areaName")
  @ApiModelProperty("商圈areaName")
  private String tradingArea;

  /**
   * 地址
   */
  @ApiModelProperty("地址")
  private String address;

  /**
   * 坐标
   */
  @ApiModelProperty("坐标")
  private String location;

  /**
   * 绿化率
   */
  @ApiModelProperty("绿化率")
  private String avgGreening;

  /**
   * 地铁站编号
   */
  @ApiModelProperty("地铁站编号subwayStationId")
  @ChangeName("subwayStationId")
  private String[] metroStationId;

  /**
   * 地铁站
   */
  @ApiModelProperty("地铁站subwayStationName")
  @ChangeName("subwayStationName")
  private String[] metroStation;

  /**
   * 地铁站与小区的距离
   */
  @ApiModelProperty("地铁站与小区的距离nearbyStationsDistance")
  @ChangeName("nearbyStationsDistance")
  private Map metroWithPlotsDistance;

  /**
   * 近地铁描述
   */
  @ApiModelProperty("近地铁描述")
  private String trafficInformation;

  /**
   * 建筑面积
   */
  @ApiModelProperty("建筑面积")
  private Double buildingAreaSize;

  /**
   * 开发商
   */
  @ApiModelProperty("开发商")
  private String developers;

  /**
   * 均价
   */
  @ApiModelProperty("均价averagePrice")
  @ChangeName("averagePrice")
  private Double avgPrice;

  /**
   * 总价
   */
  @ApiModelProperty("总价totalPrice")
  @ChangeName("totalPrice")
  private Double sumPrice;

  /**
   * 建成年代
   */
  @ApiModelProperty("建成年代")
  private String abbreviatedAge;

  /**
   * 楼龄
   */
  @ApiModelProperty("楼龄")
  private Integer age;

  /**
   * 在售房源套数
   */
  @ApiModelProperty("在售房源套数")
  private Integer houseCount;

  /**
   * 距离最近的大型公园
   */
  @ApiModelProperty("距离最近的大型公园")
  private String nearestPark;

  /**
   * 距最近公园的距离
   */
  @ApiModelProperty("距最近公园的距离")
  private Double nearestParkDistance;

  /**
   * 小区推荐标签Id
   */
  @ApiModelProperty("小区推荐标签Id")
  private Integer[] recommendBuildTagsId;

  /**
   * 小区推荐标签名称
   */
  @ApiModelProperty("小区推荐标签名称")
  private String[] recommendBuildTagsName;

  /**
   * 是否top50小区
   */
  @ApiModelProperty("是否top50小区")
  private Integer isTop;

  /**
   * 最低价
   */
  @ApiModelProperty("最低价")
  private Double lowestPrice;

  /**
   * 在商圈种排名
   */
  @ApiModelProperty("在商圈种排名")
  private Integer rankAvgInBizcircle;

  /**
   * 最小面积
   */
  @ApiModelProperty("最小面积")
  private Double houseMinArea;

  /**
   * 最大面积
   */
  @ApiModelProperty("最大面积")
  private  Double houseMaxArea;

  /**
   * 车位配比
   */
  @ApiModelProperty("车位配比")
  private String carPositionRatio;

  /**
   * 推荐理由
   */
  @ApiModelProperty("推荐理由")
  private CommunityReviewDo recommendReason;

  /**
   * 租房个数
   */
  @ApiModelProperty("租房个数")
  private Integer rentCount;
}

