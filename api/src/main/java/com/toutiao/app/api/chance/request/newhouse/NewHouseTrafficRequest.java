package com.toutiao.app.api.chance.request.newhouse;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseTrafficRequest {

    @NotNull(message = "楼盘Id不能为空不能为空")
    @ApiParam(value = "楼盘id", name = "newCode", required = true)
    private  Integer newCode;
}
