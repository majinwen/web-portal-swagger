package com.toutiao.app.service.plot;

import com.toutiao.app.domain.plot.PlotsHousesDomain;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-07-31
 * Time:   19:01
 * Theme:
 */
public interface PlotsHomesRestService {

    /**
     * 根据小区id获取小区房源信息
     * @param plotId
     * @return
     */
    PlotsHousesDomain queryPlotsHomesByPlotId(Integer plotId);

}
