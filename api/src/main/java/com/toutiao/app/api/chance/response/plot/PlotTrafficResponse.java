package com.toutiao.app.api.chance.response.plot;

import lombok.Data;

@Data
public class PlotTrafficResponse {

    /**
     * 公交站
     */
    private String busStation;

    /**
     * 公交线路
     */
    private  Integer busLines;

    /**
     * 环线
     */
    private String ringRoadName;

    /**
     * 环线距离
     */
    private  Double ringRoadDistance;

    /**
     * 地铁站
     */
    private  String subwayStation;

    /**
     * 地铁线
     */
    private  String subwayLine;

    /**
     * 地铁站距离
     */
    private  Double subwayDistance;

}
