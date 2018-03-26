package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.plot.PlotDao;
import com.toutiao.app.dao.rent.RentDao;
import com.toutiao.app.service.plot.PlotService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.query.PlotRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlotServiceImpl implements PlotService {

    @Autowired
    private PlotDao plotDao;
    @Autowired
    private RentDao rentDao;

    @Override
    public NashResult queryPlotDetaalByPlotId(PlotRequest plotRequest) {
        try {
            Map result = new HashMap();
            String plotDetail = plotDao.queryPlotDetail(plotRequest.getPlotId());
            JSONObject jsonObject = JSONObject.parseObject(plotDetail);
            String[] location = ((String) jsonObject.get("location")).split(",");
            List list = plotDao.queryNearPlotByLocationAndDistance(Double.valueOf(location[0]), Double.valueOf(location[1]), Integer.valueOf((Integer) jsonObject.get("id")));
            Map houseList = rentDao.queryHouseByPlotId(plotRequest.getPlotId());
            if (plotDetail!=null){
                result.put("plotDetail",plotDetail);
                result.put("houseList",houseList);
                return NashResult.build(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return NashResult.Fail("101","未找到该小区");
    }
}
