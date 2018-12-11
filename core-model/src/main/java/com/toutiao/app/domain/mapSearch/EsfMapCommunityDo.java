package com.toutiao.app.domain.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by CuiShihao on 2018/11/26
 */
@Data
public class EsfMapCommunityDo {

    /**
     * 区域名称
     */
    @ApiModelProperty("区域名称")
    private String districtName;

    /**
     * 商圈名称
     */
    @ApiModelProperty("商圈名称")
    private String areaName;

    /**
     * 建筑年份
     */
    private String buildYears;

    /**
     * 标题图
     */
    private String buildingImages;

    /**
     * 建筑结构
     */
    private String buildingStructure;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 上下架（0-上架，1-下架）
     */
    private Integer status;

    /**
     * 标签
     */
    private String[] tagsName;

    /**
     * 是否删除(0-未删除，1-已删除)
     */
    private Short isDel = 0;

    /**
     * 区域和商圈名称
     */
    @ApiModelProperty("区域和商圈名称")
    private String description;

    /**
     * 社区名称
     */
    @ApiModelProperty("社区名称")
    private String ploatName;

    /**
     * 社区ID
     */
    @ApiModelProperty("社区ID")
    private Integer newcode;

    /**
     * 坐标 纬度
     */
    @ApiModelProperty("坐标 纬度")
    private Double plotLatitude;

    /**
     * 坐标 经度
     */
    @ApiModelProperty("坐标 经度")
    private Double plotLongitude;

    /**
     * 房源数量
     */
    @ApiModelProperty("房源数量")
    private Integer count;

    /**
     * 均价
     */
    @ApiModelProperty("均价")
    private Double communityAvgPrice;
}
