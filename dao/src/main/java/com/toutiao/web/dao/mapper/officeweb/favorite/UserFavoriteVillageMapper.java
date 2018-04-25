package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.UserFavoriteVillage;
import com.toutiao.web.dao.BaseDao;

public interface UserFavoriteVillageMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavoriteVillage record);

    int insertSelective(UserFavoriteVillage record);

    UserFavoriteVillage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavoriteVillage record);

    int updateByPrimaryKey(UserFavoriteVillage record);

    Integer selectVillageFavoriteByUserId(Integer userId);
}