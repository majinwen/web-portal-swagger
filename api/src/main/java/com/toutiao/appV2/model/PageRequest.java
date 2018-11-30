package com.toutiao.appV2.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageRequest {
    @ApiModelProperty(value = "页码默认1", name = "pageNum")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量默认10", name = "pageSize")
    private Integer pageSize = 10;
}
