package com.toutiao.app.api.chance.request.plot;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlotAroundPlot {
    /**
     * 小区id
     */
    @NotNull
    private Integer plotId;
    /**
     * 维度
     */
    @NotNull(message = "缺少维度")
    private Double lat;
    /**
     * 经度
     */
    @NotNull(message = "缺少经度")
    private Double lon;

}
