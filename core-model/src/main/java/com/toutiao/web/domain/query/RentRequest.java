package com.toutiao.web.domain.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RentRequest {
    /**
     * 房源id
     */
    @NotNull(message = "缺少房源id")
    private Integer houseId;
}
