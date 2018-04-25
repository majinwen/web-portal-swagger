package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.UserFavoritePlot;
import com.toutiao.web.dao.BaseDao;

public interface UserFavoritePlotMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavoritePlot record);

    int insertSelective(UserFavoritePlot record);

    UserFavoritePlot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavoritePlot record);

    int updateByPrimaryKey(UserFavoritePlot record);

    Integer selectPlotFavoriteByUserId(Integer userId);

    Integer queryPlotFavoriteByPlotId(Integer plotId);
}