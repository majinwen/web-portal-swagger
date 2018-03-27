package com.toutiao.app.api.chance.request.sellhouse;


import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class NearBySellHousesRequest {

    /**
     * 小区id
     */
    @NotEmpty(message = "缺少小区Id")
    private String newhouse;
    /**
     * 小区坐标y
     */
    @NotNull(message = "缺少坐标y")
    private Double lat;
    /**
     * 小区坐标x
     */
    @NotNull(message = "缺少坐标x")
    private Double lon;
    /**
     * 附件距离
     */
    @NotEmpty(message = "缺少附近距离")
    private String distance;



}
