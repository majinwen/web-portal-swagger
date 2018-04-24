package com.toutiao.app.api.chance.request.newhouse;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewHouseTrafficRequest {

    @NotNull(message = "新房id不能为空")
    private  Integer newCode;
}
