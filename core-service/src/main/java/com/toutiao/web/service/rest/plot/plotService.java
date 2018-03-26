package com.toutiao.web.service.rest.plot;

import com.toutiao.web.common.restmodel.NashResult;

public interface plotService {
    /**
     * 根据小区id查询小区详情信息
     * @param PlotId
     * @return
     */
    NashResult queryPlotDetailByPlotId(Integer PlotId);
}
