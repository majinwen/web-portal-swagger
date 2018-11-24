package com.toutiao.appV2.model.newhouse;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseDetailsRequest {

    @NotNull(message = "楼盘id不能为空")
    @ApiParam(value = "楼盘id", name = "newCode", required = true)
    private Integer newCode;
}
