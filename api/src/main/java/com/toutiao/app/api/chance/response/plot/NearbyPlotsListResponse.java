package com.toutiao.app.api.chance.response.plot;

import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import lombok.Data;

import java.util.List;

@Data
public class NearbyPlotsListResponse {

    /**
     * 小区附近列表
     */
    private List<PlotDetailsFewDo> nearbyPlots;

    /**
     * 查询结果总量
     */
    private Long totals;
}
