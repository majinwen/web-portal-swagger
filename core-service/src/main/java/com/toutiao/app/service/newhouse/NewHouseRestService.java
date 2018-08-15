package com.toutiao.app.service.newhouse;


import com.toutiao.app.domain.newhouse.*;

import java.util.List;

public interface NewHouseRestService {

    /**
     * 根据newCode获取新房数据
     * @param newCode
     * @return
     */
    NewHouseDetailDo getNewHouseBuildByNewCode(Integer newCode, String city);


    /**
     * 新房列表
     * @param newHouseQueryDo
     * @return
     */
    NewHouseListDomain getNewHouseList(NewHouseDoQuery newHouseQueryDo, String city);

    /**
     *  新房动态
     */
    List<NewHouseDynamicDo> getNewHouseDynamicByNewCode(NewHouseDynamicDoQuery newHouseDynamicDoQuery, String city);


    /**
     * 新房交通配套
     */
    NewHouseTrafficDo getNewHouseTrafficByNewCode(Integer newCode, String userAgent, String city);

    /**
     * 根据推荐条件查询一条推荐房源
     */
    NewHouseDetailDo getOneNewHouseByRecommendCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery);



}
