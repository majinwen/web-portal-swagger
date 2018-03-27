package com.toutiao.app.api.chance.request.rent;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class RentDetailsRequest {
    /**
     * 房源id
     */
    @NotNull(message = "缺少房源Id")
    private String rentId;
}
