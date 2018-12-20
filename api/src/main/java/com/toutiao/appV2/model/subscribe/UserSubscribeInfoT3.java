package com.toutiao.appV2.model.subscribe;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserSubscribeInfoT3 {

    /**
     * 商圈id
     */
    @ApiModelProperty(value = "商圈id", name = "areaId")
    private String areaId;

    /**
     * 商圈名称
     */
    @ApiModelProperty(value = "商圈名称", name = "areaName")
    private String areaName;

    /**
     * 居
     */
    @ApiModelProperty(value = "居", name = "room")
    private Integer room;

    /**
     * 室
     */
    @ApiModelProperty(value = "室", name = "hall")
    private Integer hall;

}
