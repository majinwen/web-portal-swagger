package com.toutiao.app.service.subway.impl;

import com.toutiao.app.service.subway.SubwayLineService;
import com.toutiao.web.dao.entity.subway.SubwayLineDo;
import com.toutiao.web.dao.mapper.subway.SubwayLineInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CuiShihao on 2018/12/13
 */
@Service
public class SubwayLineServiceImpl implements SubwayLineService {

    @Autowired
    private SubwayLineInfoDao subwayLineDao;
    @Override
    public SubwayLineDo selectLineInfoByLineId(Integer lineId) {

        return subwayLineDao.selectLineInfoByLineId(lineId);
    }
}
