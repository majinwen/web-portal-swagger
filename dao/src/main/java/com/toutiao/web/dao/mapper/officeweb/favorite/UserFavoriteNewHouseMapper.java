package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.userFavoriteNewHouse;
import com.toutiao.web.dao.BaseDao;

public interface UserFavoriteNewHouseMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(userFavoriteNewHouse record);

    int insertSelective(userFavoriteNewHouse record);

    userFavoriteNewHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(userFavoriteNewHouse record);

    int updateByPrimaryKey(userFavoriteNewHouse record);

    Integer newHouseFavoriteByNewCode(Integer newCode);
}