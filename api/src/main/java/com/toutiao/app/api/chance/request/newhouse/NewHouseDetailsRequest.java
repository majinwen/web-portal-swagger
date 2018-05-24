package com.toutiao.app.api.chance.request.newhouse;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseDetailsRequest {
    /**
     * 楼盘id
     */
    @NotNull(message = "楼盘id不能为空")
    private Integer newCode;
}
