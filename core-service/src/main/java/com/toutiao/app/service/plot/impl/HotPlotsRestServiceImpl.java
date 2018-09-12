package com.toutiao.app.service.plot.impl;

import com.toutiao.app.domain.hotplot.SearchHotProj;
import com.toutiao.app.domain.hotplot.SearchHotProjDo;
import com.toutiao.app.domain.hotplot.SearchHotProjDomain;
import com.toutiao.app.service.plot.HotPlotsRestService;
import com.toutiao.web.dao.mapper.officeweb.hotplot.SearchHotProjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-22
 * Time:   17:20
 * Theme:
 */

@Service
public class HotPlotsRestServiceImpl implements HotPlotsRestService {

    @Autowired
    private SearchHotProjMapper searchHotProjMapper;

    /**
     * 根据城市id获取城市热门小区
     * @param city
     * @return
     */
    @Override
    public SearchHotProjDomain getHotPlotsByCityId(String city) {
        SearchHotProjDomain searchHotProjDomain = new SearchHotProjDomain();
        if(!"".equals(city)){
            Integer cityId = Integer.valueOf(city);//暂时写死
            List<SearchHotProjDo> searchHotProjs = searchHotProjMapper.queryHotPlotsByCityId(cityId);
            searchHotProjDomain.setData(searchHotProjs);
        }
        return searchHotProjDomain;
    }
}
