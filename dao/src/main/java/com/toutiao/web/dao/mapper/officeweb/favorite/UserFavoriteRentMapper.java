package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.DeleteRentFavoriteDo;
import com.toutiao.app.domain.favorite.UserFavoriteRent;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

public interface UserFavoriteRentMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavoriteRent record);

    int insertSelective(UserFavoriteRent record);

    UserFavoriteRent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavoriteRent record);

    int updateByPrimaryKey(UserFavoriteRent record);

    Integer selectRentFavoriteByUserId(Integer userId);

    Integer isRentFavoriteByRentIdAndUserId(@Param("houseId") String houseId, @Param("userId") Integer userId);

    Integer updateRentFavoriteByRentIdAndUserId(DeleteRentFavoriteDo deleteRentFavoriteDo);
}