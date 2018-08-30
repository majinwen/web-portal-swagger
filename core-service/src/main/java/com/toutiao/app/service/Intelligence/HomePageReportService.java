package com.toutiao.app.service.Intelligence;

import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.domain.query.IntelligenceQuery;

import javax.servlet.http.HttpServletRequest;

public interface HomePageReportService {
    /**
     * 保存报告页
     * @param userFavoriteConditionDoQuery
     * @return
     */
    Integer saveHomePageReport(HttpServletRequest request, UserFavoriteConditionDoQuery userFavoriteConditionDoQuery);

    /**
     * 获取用户画像
     * @param intelligenceQuery
     * @return
     */
    IntelligenceQuery getPersonas(IntelligenceQuery intelligenceQuery);

    /**
     * 查找报告
     * @param intelligenceQuery
     * @param userPhone
     * @return
     */
    IntelligenceFhRes intelligenceFindHouseServiceByType1(IntelligenceQuery intelligenceQuery, String userPhone);
}
