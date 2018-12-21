package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.NewHouseAddFavoriteDoQuery;
import com.toutiao.app.domain.favorite.NewHouseIsFavoriteDoQuery;
import com.toutiao.app.domain.favorite.UserFavoriteNewHouse;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteDo;
import com.toutiao.app.domain.favorite.newhouse.NewHouseFavoriteListDoQuery;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoriteNewHouseMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavoriteNewHouse record);

    int insertSelective(UserFavoriteNewHouse record);

    UserFavoriteNewHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavoriteNewHouse record);

    int updateByPrimaryKey(UserFavoriteNewHouse record);

    Integer newHouseFavoriteByNewCode(Integer newCode);

    Integer selectFavoriteNewHouseByUserId(Integer userId);

    Integer selectFavoriteNewHouseByUserIdAndCityId(@Param("userId") Integer userId, @Param("cityId") Integer cityId);

    int  cancelNewHouseFavoriteByUserIdAndHouseId(UserFavoriteNewHouse userFavoriteNewHouse);

    Integer getNewHouseIsFavorite(NewHouseIsFavoriteDoQuery newHouseIsFavoriteDoQuery);

    Integer addNewHouseFavorite(NewHouseAddFavoriteDoQuery newHouseAddFavoriteDoQuery);

    Integer queryCountByUserIdAndHouseId(NewHouseAddFavoriteDoQuery newHouseAddFavoriteDoQuery);


    /**
     * 获取用户新房收藏列表
     * @param newHouseFavoriteListDoQuery
     * @return
     */
    List<NewHouseFavoriteDo> selectNewHouseFavoriteByUserId(NewHouseFavoriteListDoQuery newHouseFavoriteListDoQuery);

    List<NewHouseFavoriteDo> selectNewHouseFavoriteByUserIdAndCityId(NewHouseFavoriteListDoQuery newHouseFavoriteListDoQuery);
}