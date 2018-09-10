package com.toutiao.app.service.compared;

import com.toutiao.app.domain.compared.HouseComparedDetailDo;
import com.toutiao.app.domain.compared.HouseComparedListDo;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.web.dao.entity.compared.HouseCompared;

import java.util.List;

public interface ComparedService {
    int deleteByPrimaryKey(Integer id);

    int insert(HouseCompared record);

    int insertSelective(HouseCompared record);

    HouseCompared selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseCompared record);

    int updateByPrimaryKey(HouseCompared record);

    HouseCompared selectByUserIdAndHouseId(Integer userId, String houseId, Integer cityId);

    List<HouseComparedListDo> selectTempComparedByIds(List<String> ids, String city);

    List<HouseComparedListDo> selectComparedByHouseCompareds(List<HouseCompared> houseCompareds, String city);

    List<HouseCompared> selectByUserId (Integer userId, Integer cityId);

    List<HouseComparedDetailDo> selectComparedDetailByHouseIds (List<String> ids, String city);

    /**
     * 比对列表
     * @param sellHouseFavoriteListDoQuery
     * @return
     */
    SellHouseFavoriteDomain queryComparedList(SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery);

}
