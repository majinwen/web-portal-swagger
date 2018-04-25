package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.UserFavoriteEsHouse;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

public interface UserFavoriteEsHouseMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavoriteEsHouse record);

    int insertSelective(UserFavoriteEsHouse record);

    UserFavoriteEsHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavoriteEsHouse record);

    int updateByPrimaryKey(UserFavoriteEsHouse record);


    Integer selectEsHouseFavoriteByUserId(Integer userId);

    Integer isEsfFavoriteByHouseIdAndUserId(@Param("houseId") String houseId,@Param("userId") Integer userId);
}