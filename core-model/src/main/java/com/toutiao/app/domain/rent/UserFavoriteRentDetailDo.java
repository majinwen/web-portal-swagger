package com.toutiao.app.domain.rent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * Created by CuiShihao on 2018/12/11
 */
@Data
public class UserFavoriteRentDetailDo {

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
     * 最近的地铁
     */
    @ApiModelProperty("最近的地铁")
    private String nearestSubway;

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

    @ApiModelProperty("附近距离xxkm")
    private String nearbyDistance;
}
