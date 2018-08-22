package com.toutiao.app.service.plot;


import com.toutiao.app.domain.hotplot.SearchHotProjDomain;


/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-22
 * Time:   17:19
 * Theme:
 */
public interface HotPlotsRestService {

    /**
     * 根据城市id获取城市热门小区
     * @param city
     * @return
     */
    SearchHotProjDomain getHotPlotsByCityId(String city);

}
