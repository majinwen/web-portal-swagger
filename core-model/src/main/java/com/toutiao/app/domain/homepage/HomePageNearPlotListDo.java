package com.toutiao.app.domain.homepage;

import lombok.Data;

import java.util.List;

@Data
public class HomePageNearPlotListDo {
    /**
     * 小区
     */
    private List<HomePageNearPlotDo> data;
    /**
     * 房源总数
     */
    private Integer totalNum;
}
