package com.toutiao.app.api.chance.request.newhouse;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseDynamicRequest extends BaseQueryRequest {

    @NotNull(message = "楼盘id不能为空")
    @ApiParam(value = "楼盘id", name = "newCode", required = true)
    private Integer newCode;


}
