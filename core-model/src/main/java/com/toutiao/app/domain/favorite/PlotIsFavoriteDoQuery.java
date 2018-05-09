package com.toutiao.app.domain.favorite;

import lombok.Data;

@Data
public class PlotIsFavoriteDoQuery {
    /**
     * 小区id
     */
    private Integer buildingId;
    /**
     * 用户id
     */
    private Integer userId;
}
