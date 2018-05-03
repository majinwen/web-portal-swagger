package com.toutiao.app.api.chance.response.plot;

import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.web.common.assertUtils.ChangeName;
import lombok.Data;

import java.util.List;

@Data
public class NearbyPlotsListResponse {

    /**
     * 小区附近列表
     */
    @ChangeName("data")
    private List<PlotDetailsFewDo> nearbyPlots;

    /**
     * 查询结果总量
     */
    @ChangeName("totalNum")
    private Long totals;

}
