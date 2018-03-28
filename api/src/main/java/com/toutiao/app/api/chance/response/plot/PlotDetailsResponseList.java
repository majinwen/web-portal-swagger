package com.toutiao.app.api.chance.response.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotDetailsResponseList {
    /**
     * 小区列表
     */
    private List<PlotDetailsFewResponse> plotDetailsResponseList;
    /**
     * 总数
     */
    private Integer totalNum;
}
