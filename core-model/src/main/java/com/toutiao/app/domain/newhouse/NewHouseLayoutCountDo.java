package com.toutiao.app.domain.newhouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewHouseLayoutCountDo {

    @ApiModelProperty(value = "户型")
    private Object room;

    @ApiModelProperty(value = "户型数量")
    private Long count;



}
