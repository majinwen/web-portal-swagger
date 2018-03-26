package com.toutiao.app.service.plot.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.dao.plot.AppPlotDao;
import com.toutiao.app.dao.rent.AppRentDao;
import com.toutiao.app.service.plot.AppPlotService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.dao.mapper.officeweb.MapInfoMapper;
import com.toutiao.web.domain.query.PlotRequest;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppPlotServiceImpl implements AppPlotService {

    @Autowired
    private AppPlotDao appPlotDao;
    @Autowired
    private AppRentDao appRentDao;
    @Autowired
    private MapInfoMapper mapInfoMapper;

    @Override
    public NashResult queryPlotDetaalByPlotId(PlotRequest plotRequest) {
        try {
            Map result = new HashMap();
            Map plotDetail = appPlotDao.queryPlotDetail(plotRequest.getPlotId());
            String[] location = ((String) plotDetail.get("location")).split(",");
            List nearList = appPlotDao.queryNearPlotByLocationAndDistance(Double.valueOf(location[0]), Double.valueOf(location[1]), Integer.valueOf((Integer) plotDetail.get("id")));
            Map rentList = appRentDao.queryHouseByPlotId(plotRequest.getPlotId());
            MapInfo mapInfo = mapInfoMapper.selectByNewCode((Integer) plotDetail.get("id"));
            JSONObject dataInfo= JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
            if (plotDetail!=null){
                result.put("plotDetail",plotDetail);
                result.put("rentList",rentList);
                result.put("nearList",nearList);
                result.put("dataInfo",dataInfo);
                return NashResult.build(result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return NashResult.Fail("101","未找到该小区");
    }
}
