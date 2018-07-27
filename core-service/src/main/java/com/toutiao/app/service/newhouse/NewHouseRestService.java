package com.toutiao.app.service.newhouse;


import com.toutiao.app.domain.newhouse.*;

import java.util.List;

public interface NewHouseRestService {

    /**
     * 根据newCode获取新房数据
     * @param newCode
     * @return
     */
    NewHouseDetailDo getNewHouseBuildByNewCode(Integer newCode, String userAgent, String city);


    /**
     * 新房列表
     * @param newHouseQueryDo
     * @return
     */
    NewHouseListDomain getNewHouseList(NewHouseDoQuery newHouseQueryDo, String userAgent, String city);

    /**
     *  新房动态
     */
    List<NewHouseDynamicDo> getNewHouseDynamicByNewCode(NewHouseDynamicDoQuery newHouseDynamicDoQuery);


    /**
     * 新房交通配套
     */
    NewHouseTrafficDo getNewHouseTrafficByNewCode(Integer newCode, String userAgent, String city);



}
