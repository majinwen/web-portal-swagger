package com.toutiao.app.api.chance.request.compared;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ComparedRequest {
    /**
     * id
     */
    private Integer id;
    /**
     * 房源id
     */
    @NotNull(message = "房源id")
    private String houseId;
}
