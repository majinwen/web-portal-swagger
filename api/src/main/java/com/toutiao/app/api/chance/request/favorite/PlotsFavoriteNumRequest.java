package com.toutiao.app.api.chance.request.favorite;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlotsFavoriteNumRequest {
    /**
     * 小区di
     */
    @NotNull(message = "小区id不能为空")
    private Integer plotId;
}
