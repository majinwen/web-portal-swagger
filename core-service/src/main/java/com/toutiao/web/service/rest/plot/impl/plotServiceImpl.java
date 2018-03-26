package com.toutiao.web.service.rest.plot.impl;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.rest.plot.plotDao;
import com.toutiao.web.dao.rest.rent.rentDao;
import com.toutiao.web.service.rest.plot.plotService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class plotServiceImpl implements plotService {
    @Autowired
    private plotDao plotDao;
    @Autowired
    private rentDao rentDao;

    @Override
    public NashResult queryPlotDetailByPlotId(Integer PlotId) {
        Map plotDetail = plotDao.queryPlotDetail(PlotId);
        Map houseList = rentDao.queryHouseByPlotId(PlotId);
        return null;
    }
}
