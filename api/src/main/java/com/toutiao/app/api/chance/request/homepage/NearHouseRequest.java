package com.toutiao.app.api.chance.request.homepage;

import com.toutiao.app.api.chance.request.BaseQueryRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NearHouseRequest extends BaseQueryRequest{
    /**
     * y坐标
     */
    @NotNull(message = "缺少坐标y")
    private Double lat;
    /**
     * x坐标
     */
    @NotNull(message = "缺少坐标x")
    private Double lon;

    /**
     * 城市id
     */
    @NotNull(message = "缺少城市id")
    private Integer cityId;
}
