package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.PlotIsFavoriteDo;
import com.toutiao.app.domain.favorite.UserFavoriteVillage;
import com.toutiao.web.dao.BaseDao;

import java.util.List;

public interface UserFavoriteVillageMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavoriteVillage record);

    int insertSelective(UserFavoriteVillage record);

    UserFavoriteVillage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavoriteVillage record);

    int updateByPrimaryKey(UserFavoriteVillage record);

    Integer selectVillageFavoriteByUserId(Integer userId);

    int  cancelVillageByVillageIdAndUserId(UserFavoriteVillage userFavoriteVillage);

    Integer selectPlotFavoriteCountByPlotId(Integer plotId);

    Integer selectPlotIsFavorite(PlotIsFavoriteDo plotIsFavoriteDo);
}