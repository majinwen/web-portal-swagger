package com.toutiao.app.service.plot;

import com.toutiao.app.domain.plot.PlotsThemeDoQuery;
import com.toutiao.app.domain.plot.PlotsThemeDomain;

/**
 * 小区主题落地页服务层
 */
public interface PlotsThemeRestService {

    /**
     * 获取小区主题数据
     */
    PlotsThemeDomain getPlotsThemeList(PlotsThemeDoQuery plotsThemeDoQuery, String city);
}
