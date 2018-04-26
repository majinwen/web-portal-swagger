package com.toutiao.app.service.homepage;

import com.toutiao.app.dao.homepage.HomePageEsDao;
import com.toutiao.app.domain.homepage.HomePageEsfDo;

import java.util.List;

public interface HomePageRestService {

    /**
     * 获取首页二手房5条
     */

    List<HomePageEsfDo> getHomePageEsf(Integer cityId);

}
