package com.toutiao.app.api.chance.request.sellhouse;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SellHouseDetailsRequest {

    @NotNull(message = "缺少房源Id")
    private Integer houseId;

}
