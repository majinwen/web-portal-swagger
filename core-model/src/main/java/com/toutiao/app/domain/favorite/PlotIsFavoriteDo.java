package com.toutiao.app.domain.favorite;

import lombok.Data;

@Data
public class PlotIsFavoriteDo {
    /**
     * 小区id
     */
    private Integer plotId;
    /**
     * 用户id
     */
    private Integer userId;
}
