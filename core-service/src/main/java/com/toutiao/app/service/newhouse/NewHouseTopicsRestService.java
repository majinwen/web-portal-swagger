package com.toutiao.app.service.newhouse;

import com.toutiao.app.domain.newhouse.NewHouseDoQuery;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;

public interface NewHouseTopicsRestService {


    /**
     * 获取五环内最美新房
     * @param newHouseDoQuery
     * @return
     */
    NewHouseListDomain getNewHouseTopic(NewHouseDoQuery newHouseDoQuery);

}
