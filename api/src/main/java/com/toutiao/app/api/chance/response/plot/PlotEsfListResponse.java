package com.toutiao.app.api.chance.response.plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotEsfListResponse {

    /**
     * 小区房源列表
     */
    private List<PlotEsfResponse> plotEsfResponseList;

    /**
     * 查询结果总量
     */
    private Integer totalNum;

}
