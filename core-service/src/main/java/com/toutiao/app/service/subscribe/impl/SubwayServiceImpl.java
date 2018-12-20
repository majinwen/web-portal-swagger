package com.toutiao.app.service.subscribe.impl;

import com.toutiao.app.service.subscribe.SubwayService;
import com.toutiao.web.dao.entity.subscribe.SubwayLineData;
import com.toutiao.web.dao.mapper.subscribe.SubwayLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
@Service
public class SubwayServiceImpl implements SubwayService {

    @Autowired
    private SubwayLineDao subwayLineDao;

    @Override
    public List<SubwayLineData> getSubwayLineInfos(Integer cityId) {
        List<SubwayLineData> subwayLineDatas = subwayLineDao.getSubwayLineInfoByCityId(cityId);
        return subwayLineDatas;
    }
}
