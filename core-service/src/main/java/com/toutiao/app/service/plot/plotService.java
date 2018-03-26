package com.toutiao.app.service.plot;

import com.toutiao.web.common.restmodel.NashResult;

public interface plotService {
    /**
     *
     * @param plotId
     * @return
     */
    NashResult queryPlotDetaalByPlotId(Integer plotId);
}
