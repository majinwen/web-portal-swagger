package com.toutiao.appV2.model.rent;

import java.util.Map;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * RentDetailFewResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T07:33:42.688Z")

@Data
public class RentDetailFewResponse   {
  /**
   * 出租房源Id
   */
  @ApiModelProperty("出租房源Id")
  private String houseId;
  /**
   * 小区Id
   */
  @ApiModelProperty("小区Id")
  private Integer zufangId;
  /**
   * 小区名称
   */
  @ApiModelProperty("小区名称")
  private String zufangName;
  /**
   * 房源面积
   */
  @ApiModelProperty("房源面积")
  private Double houseArea;
  /**
   * 几室
   */
  @ApiModelProperty("几室")
  private String room;
  /**
   * 几厅
   */
  @ApiModelProperty("几厅")
  private Integer hall;
  /**
   * 朝向ID（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）
   */
  @ApiModelProperty("朝向ID（1-东,2-西,3-南,4-北,5-东南,6-西南,7-东北,8-西北,9-东西,10-南北,11-其他）")
  private String forward;
  /**
   * 商圈名称
   */
  @ApiModelProperty("商圈名称")
  private String areaName;
  /**
   * 区域名称
   */
  @ApiModelProperty("区域名称")
  private String districtName;
  /**
   * 租赁方式名称
   */
  @ApiModelProperty("租赁方式名称")
  private String rentTypeName;
  /**
   * 出租房源标签名称
   */
  @ApiModelProperty("出租房源标签名称")
  private String[] rentHouseTagsName;
  /**
   * 租金(元/月)
   */
  @ApiModelProperty("租金(元/月)")
  private Double rentHousePrice;
  /**
   * 总数
   */
  @ApiModelProperty("总数")
  private Integer totalNum;
  /**
   * 房源标题图
   */
  @ApiModelProperty("房源标题图")
  private String houseTitleImg;
  /**
   * 录入/导入房源标题
   */
  @ApiModelProperty("录入/导入房源标题")
  private String houseTitle;
  /**
   * 房源来源类型(录入/导入)
   */
  @ApiModelProperty("房源来源类型(录入/导入)")
  private Integer rentHouseType;
  /**
   * 租房推优查询uid
   */
  @ApiModelProperty("租房推优查询uid")
  private String uid;

  private AgentBaseDo agentBaseDo;

  private Integer userId;
  /**
   * 最近的地铁
   */
  @ApiModelProperty("最近的地铁")
  private String nearestSubway;
  /**
   * 车位配比
   */
  @ApiModelProperty("车位配比")
  private String parkRatio;
  /**
   * 出租类型
   */
  @ApiModelProperty("出租类型(1整租2合租)")
  private  Integer rentType;
  /**
   * 地铁信息
   */
  private Map<String,String> nearbySubway;
  /**
   * 地铁到房子间的距离
   */
  @ApiModelProperty("地铁到房子间的距离")
  private  String subwayDistanceInfo;
  /**
   * 房源图片
   */
  @ApiModelProperty("房源图片")
  private String[] housePhoto;
  /**
   * 房源描述
   */
  @ApiModelProperty("房源描述")
  private String houseDesc;
  /**
   * 入库时间
   */
  @ApiModelProperty("入库时间")
  private String createTime;
  /**
   * 是否显示默认图片标志
   */
  @ApiModelProperty("是否显示默认图片标志")
  private Integer isDefaultImage = 0;

  @ApiModelProperty("附近距离xxkm")
  private String nearbyDistance;
}

