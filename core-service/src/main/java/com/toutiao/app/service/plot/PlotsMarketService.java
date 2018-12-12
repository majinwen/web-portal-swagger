package com.toutiao.app.service.plot;

import com.toutiao.app.domain.plot.PlotMarketDo;

/**
 * Created by wk on 2018/12/12.
 */
public interface PlotsMarketService {

    PlotMarketDo queryPlotMarketByPlotId(Integer plotId);

}
