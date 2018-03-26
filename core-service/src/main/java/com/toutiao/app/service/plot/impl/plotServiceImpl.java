package com.toutiao.app.service.plot.impl;

import com.toutiao.app.dao.plot.plotDao;
import com.toutiao.app.dao.rent.rentDao;
import com.toutiao.app.service.plot.plotService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class plotServiceImpl implements plotService {

    @Autowired
    private plotDao plotDao;
    @Autowired
    private rentDao rentDao;

    @Override
    public NashResult queryPlotDetaalByPlotId(Integer plotId) {
        Map result = new HashMap();
        Map plotDetail = plotDao.queryPlotDetail(plotId);
        Map houseList = rentDao.queryHouseByPlotId(plotId);
        if (plotDetail!=null&&houseList!=null){
            result.put("plotDetail",plotDetail);
            result.put("houseList",houseList);
            return NashResult.build(result);
        }
        return NashResult.Fail("");
    }
}
