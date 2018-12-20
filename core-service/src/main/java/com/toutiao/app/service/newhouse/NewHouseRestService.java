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
    NewHouseTrafficDo getNewHouseTrafficByNewCode(Integer newCode, String city);

    /**
     * 根据推荐条件查询一条推荐房源
     */
    NewHouseDetailDo getOneNewHouseByRecommendCondition(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city);


    /**
     * 查询新房猜你喜欢
     * @param newHouseDoQuery
     * @param userId
     * @param city
     * @return
     */
    NewHouseListDomain queryGuessLikeNewHouseList(NewHouseDoQuery newHouseDoQuery, Integer userId, String city);

    /**
     * 目标市场新房推荐
     * @param userFavoriteConditionDoQuery
     * @return
     */
    NewHouseCustomConditionDomain getNewHouseCustomList(UserFavoriteConditionDoQuery userFavoriteConditionDoQuery, String city);
}
