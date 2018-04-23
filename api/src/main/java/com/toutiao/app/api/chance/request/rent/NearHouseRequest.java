package com.toutiao.app.api.chance.request.rent;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class NearHouseRequest {
    /**
     * 维度
     */
    @NotNull
    private Double lat;
    /**
     * 经度
     */
    @NotNull
    private Double lon;
    /**
     * 当前页
     */
    private Integer pageNum = 1;
}
