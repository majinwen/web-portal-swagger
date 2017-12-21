package com.toutiao.web.service.intelligence;


import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.domain.query.IntelligenceQuery;

import java.util.List;

public interface IntelligenceFindHouseService {

    List queryPlotCount(IntelligenceQuery intelligenceQuery);

    List queryByCategory(IntelligenceQuery intelligenceQuery);

    List<QueryFindByRobot> filterByTotalAndCategory(IntelligenceQuery intelligenceQuery);

    List<Long> queryPlotCountBySchoolType(IntelligenceQuery intelligenceQuery);

    List queryPlotInfoByUserType(IntelligenceQuery intelligenceQuery);

}
