package com.toutiao.app.service.subway;

import com.toutiao.web.dao.entity.subway.SubwayLineDo;

/**
 * Created by CuiShihao on 2018/12/13
 */
public interface SubwayLineService {

    /**
     * 根据地铁线id获取地铁线信息
     * @param lineId
     * @return
     */
    SubwayLineDo selectLineInfoByLineId(Integer lineId);
}
