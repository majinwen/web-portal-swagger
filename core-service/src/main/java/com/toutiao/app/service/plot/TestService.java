package com.toutiao.app.service.plot;

import com.toutiao.app.domain.plot.PlotListDo;
import com.toutiao.app.domain.plot.PlotListDoQuery;

public interface TestService {

    void search() throws Exception;

    /**
     * 获取小区列表
     * @param plotListDoQuery
     * @return
     */
    PlotListDo queryPlotListByRequirement(PlotListDoQuery plotListDoQuery, String city) throws Exception;
}
