package com.toutiao.app.domain.rent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by CuiShihao on 2018/12/13
 */
@Data
public class RentCustomConditionDo {

    /**
     * 区域ID
     */
    @ApiModelProperty(name = "id", value = "id")
    private Integer id;

    /**
     * 区域名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     * 坐标 纬度
     */
    @ApiModelProperty(name = "latitude", value = "坐标 纬度")
    private Double latitude;

    /**
     * 坐标 经度
     */
    @ApiModelProperty(name = "longitude", value = "坐标 经度")
    private Double longitude;

    /**
     * 描述
     */
    @ApiModelProperty(name = "desc", value = "描述")
    private String desc;

    /**
     * 小区数量
     */
    @ApiModelProperty(name = "communityCount", value = "小区数量")
    private Integer communityCount;

    /**
     * 房源数量
     */
    @ApiModelProperty(name = "houseCount", value = "房源数量")
    private Integer houseCount;
}
