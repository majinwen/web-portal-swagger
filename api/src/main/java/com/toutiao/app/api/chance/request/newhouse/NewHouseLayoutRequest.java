package com.toutiao.app.api.chance.request.newhouse;


import com.toutiao.web.common.assertUtils.First;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseLayoutRequest {

    @NotNull(message = "缺少楼盘Id")
    @ApiParam(value = "楼盘id", name = "newCode", required = true)
    private Integer newCode;

    @NotNull(groups = {First.class},message = "缺少户型")
    @ApiParam(value = "户型", name = "room", required = true)
    private Integer room;


}
