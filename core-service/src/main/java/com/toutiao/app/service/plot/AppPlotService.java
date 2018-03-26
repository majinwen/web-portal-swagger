package com.toutiao.app.service.plot;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.query.PlotRequest;

public interface AppPlotService {
    /**
     * 根据小区id查询小区详情
     * @param plotId
     * @return
     */
    NashResult queryPlotDetaalByPlotId(PlotRequest plotRequest);
}
