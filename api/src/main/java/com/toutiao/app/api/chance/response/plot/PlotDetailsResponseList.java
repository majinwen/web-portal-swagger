package com.toutiao.app.api.chance.response.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotDetailsResponseList {
    /**
     * 小区集合
     */
    private List<PlotDetailsResponse> plotDetailsResponseList;
    /**
     * 总数
     */
    private Integer totalNum;
}
