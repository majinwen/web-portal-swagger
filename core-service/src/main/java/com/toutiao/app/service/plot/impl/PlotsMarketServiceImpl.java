package com.toutiao.app.service.plot.impl;

import com.toutiao.app.domain.plot.PlotMarketDo;
import com.toutiao.app.service.plot.PlotsMarketService;
import com.toutiao.web.dao.mapper.officeweb.PlotsMarketMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wk on 2018/12/12.
 */
@Service
@Slf4j
public class PlotsMarketServiceImpl implements PlotsMarketService {

    @Autowired
    private PlotsMarketMapper plotsMarketMapper;

    /**
     * 查询小区行情信息
     *
     * @param plotId
     * @return
     */
    @Override
    public PlotMarketDo queryPlotMarketByPlotId(Integer plotId) {

        log.info("查询小区行情plotId:{}", plotId);
        PlotMarketDo plotMarketDo = plotsMarketMapper.queryPlotMarketByPlotId(plotId);
        log.info("查询小区行情结果集:{}", plotMarketDo);
        return plotMarketDo;
    }

}
