package com.toutiao.app.domain.mapSearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SubwayStationDo
 * @Author jiangweilong
 * @Date 2018/12/15 3:22 PM
 * @Description:
 **/

@Data
public class SubwayStationDo {

    @ApiModelProperty("地铁站id")
    private Integer stationId;

    @ApiModelProperty("地铁站名称")
    private String stationName;

    @ApiModelProperty("地铁站的纬度")
    private Double latitude;

    @ApiModelProperty("地铁站的经度")
    private Double longitude;
}
