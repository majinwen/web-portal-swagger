package com.toutiao.web.service.intelligence.impl;


import com.toutiao.web.dao.entity.officeweb.IntelligenceFhTd;
import com.toutiao.web.dao.mapper.officeweb.IntelligenceFhTdMapper;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFhTdRatio;
import com.toutiao.web.service.intelligence.IntelligenceFhTdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IntelligenceFhTdServiceImpl implements IntelligenceFhTdService {


    @Autowired
    private IntelligenceFhTdMapper intelligenceFhTdMapper;


    /**
     * 根据总价获取目标市场供需走势
     * @param totalPrice
     * @return
     */
    @Override
    public Map<String,Object> queryTd(Integer totalPrice) {

        List<IntelligenceFhTd> lists = intelligenceFhTdMapper.queryTd(totalPrice);

        IntelligenceFhTdRatio intelligenceFhTdRatio = intelligenceFhTdMapper.queryTdRatio(totalPrice);

        //月最低成交套数占目标市场比率
        Double minratio = Double.valueOf(intelligenceFhTdRatio.getMinVolume()) / Double.valueOf(intelligenceFhTdRatio.getSumAllSd()) * 100;
        //月最高成交套数占目标市场比率
        Double maxratio = Double.valueOf(intelligenceFhTdRatio.getMaxVolume()) / Double.valueOf(intelligenceFhTdRatio.getSumAllSd()) * 100;
        //年平均成交量
        Double average = Double.valueOf(intelligenceFhTdRatio.getSumTargetSd()) /12;
        //年平均成交量占目标市场比率
        Double averageratio = average / Double.valueOf(intelligenceFhTdRatio.getSumAllSd()) * 100;

        intelligenceFhTdRatio.setMinVolumeRatio(String.format("%.2f",minratio)+"%");
        intelligenceFhTdRatio.setMaxVolumeRatio(String.format("%.2f",maxratio)+"%");
        intelligenceFhTdRatio.setAverageVolume(String.format("%.2f",average));
        intelligenceFhTdRatio.setAverageVolumeRatio(String.format("%.2f",averageratio)+"%");
        Map<String,Object> result = new HashMap<>();
        result.put("ratio",intelligenceFhTdRatio);
        result.put("trend",lists);
        return result;
    }
}
