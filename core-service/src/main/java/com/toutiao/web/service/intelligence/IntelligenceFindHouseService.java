package com.toutiao.web.service.intelligence;


import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFh;
import com.toutiao.web.domain.query.IntelligenceQuery;

public interface IntelligenceFindHouseService {


//    IntelligenceFh queryUserCheckPriceAndCategory(IntelligenceQuery intelligenceQuery);

//    IntelligenceFh queryUserCheckPrice(IntelligenceQuery intelligenceQuery);

    IntelligenceFhRes intelligenceFindHouseServiceByType(IntelligenceQuery intelligenceQuery,String userPhone);

    IntelligenceFh queryPlotCountByDistrict(IntelligenceQuery intelligenceQuery);

    /**
     * 智能找房--用户筛选
     * @param intelligenceQuery
     * @return
     */
    IntelligenceFh queryUserChoice(IntelligenceQuery intelligenceQuery);
}
