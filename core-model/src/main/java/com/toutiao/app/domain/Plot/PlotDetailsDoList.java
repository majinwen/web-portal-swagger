package com.toutiao.app.domain.Plot;

import lombok.Data;

import java.util.List;

@Data
public class PlotDetailsDoList {

    /**
     * 小区详情列表
     */
    private List<PlotDetailsFewDo> plotDetailsDoList;
    /**
     * 总数
     */
    private Integer totalNum;
}
