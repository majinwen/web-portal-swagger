package com.toutiao.app.domain.plot;

import lombok.Data;

@Data
public class PlotFavoriteListDoQuery {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 当前页
     */
    private Integer pageNum ;
    /**
     * 每页大小
     */
    private Integer size ;
    /**
     * 起始位置
     */
    private Integer from;
}
