package com.toutiao.app.api.chance.response.newhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewHouseTrafficResponse {

    @ApiModelProperty(value = "公交站")
    private String busStation;

    @ApiModelProperty(value = "公交线路")
    private  Integer busLines;

    @ApiModelProperty(value = "环线")
    private String ringRoadName;

    @ApiModelProperty(value = "环线距离")
    private  Double ringRoadDistance;

    @ApiModelProperty(value = "地铁站")
    private  String subwayStation;

    @ApiModelProperty(value = "地铁线")
    private  String subwayLine;

    @ApiModelProperty(value = "地铁站距离")
    private  Double subwayDistance;
}
