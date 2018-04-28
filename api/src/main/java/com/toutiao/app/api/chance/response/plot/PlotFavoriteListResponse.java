package com.toutiao.app.api.chance.response.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotFavoriteListResponse {

    /**
     * 小区附近列表
     */
    private List<PlotDetailsFewResponse> plotFavoriteList;

    /**
     * 查询结果总量
     */
    private Long totalCount;

}
