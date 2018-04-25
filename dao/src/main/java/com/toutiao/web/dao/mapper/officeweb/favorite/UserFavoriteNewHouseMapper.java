package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.UserFavoriteNewHouse;
import com.toutiao.app.domain.favorite.UserFavoriteVillage;
import com.toutiao.web.dao.BaseDao;

public interface UserFavoriteNewHouseMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavoriteNewHouse record);

    int insertSelective(UserFavoriteNewHouse record);

    UserFavoriteNewHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavoriteNewHouse record);

    int updateByPrimaryKey(UserFavoriteNewHouse record);

    Integer newHouseFavoriteByNewCode(Integer newCode);

    Integer selectFavoriteNewHouseByUserId(Integer userId);

    int  cancelNewHouseFavoriteByUserIdAndHouseId(UserFavoriteNewHouse userFavoriteNewHouse);


}