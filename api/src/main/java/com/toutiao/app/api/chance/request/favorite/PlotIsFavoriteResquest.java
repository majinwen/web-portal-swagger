package com.toutiao.app.api.chance.request.favorite;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlotIsFavoriteResquest {
    /**
     * 小区id
     */
    @NotNull(message = "小区id不能为空")
    private Integer plotId;
    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;
}
