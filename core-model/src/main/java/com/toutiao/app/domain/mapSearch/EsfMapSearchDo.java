package com.toutiao.app.domain.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName EsfMapFindHouseCommunityDo
 * @Author jiangweilong
 * @Date 2018/11/22 9:32 PM
 * @Description:
 **/

@Data
public class EsfMapSearchDo {

    /**
     * 社区名称
     */
    @ApiModelProperty("区县或商圈或小区或地铁站的名称")
    private String name;

    /**
     * 社区ID
     */
    @ApiModelProperty("区县或商圈或小区或地铁站的id")
    private Integer id;

    /**
     * 坐标 纬度
     */
    @ApiModelProperty("区县或商圈或小区或地铁站的纬度")
    private Double latitude;

    /**
     * 坐标 经度
     */
    @ApiModelProperty("区县或商圈或小区或地铁站的经度")
    private Double longitude;

    /**
     * 描述
     */
    @ApiModelProperty("搜索结果描述")
    private String desc;

    /**
     * 房源数量
     */
    @ApiModelProperty("房源数量")
    private Integer count;

    /**
     * 均价
     */
    @ApiModelProperty("区县或商圈或小区或地铁站的房源均价")
    private Double price;
}
