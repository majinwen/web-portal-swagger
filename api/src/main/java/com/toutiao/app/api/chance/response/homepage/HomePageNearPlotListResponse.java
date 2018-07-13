package com.toutiao.app.api.chance.response.homepage;

import com.toutiao.app.domain.homepage.HomePageNearPlotDo;
import lombok.Data;

import java.util.List;

@Data
public class HomePageNearPlotListResponse {
    /**
     * 小区
     */
    private List<HomePageNearPlotDo> data;
    /**
     * 房源总数
     */
    private Integer totalNum;
}
