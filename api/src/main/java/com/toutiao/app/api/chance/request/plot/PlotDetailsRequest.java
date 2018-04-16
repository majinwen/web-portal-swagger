package com.toutiao.app.api.chance.request.plot;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlotDetailsRequest {
    /**
     * 小区id
     */
    @NotNull(message = "缺少小区Id")
    private Integer plotId;
}
