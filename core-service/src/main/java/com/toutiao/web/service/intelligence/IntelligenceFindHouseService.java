package com.toutiao.web.service.intelligence;


import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.domain.query.IntelligenceQuery;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface IntelligenceFindHouseService {



    IntelligenceFhRes queryUserReport(String usePhone);


    Map<String,String> queryUserCheckPrice(IntelligenceQuery intelligenceQuery);


    List<IntelligenceFindhouse> intelligenceFindHouseServiceTypeTwo(IntelligenceQuery intelligenceQuery);

}
