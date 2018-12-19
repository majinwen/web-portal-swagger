package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.DeleteRentFavoriteDoQuery;
import com.toutiao.app.domain.favorite.UserFavoriteRent;
import com.toutiao.app.domain.favorite.UserFavoriteRentDoQuery;
import com.toutiao.app.domain.favorite.rent.RentFavoriteDo;
import com.toutiao.app.domain.favorite.rent.RentFavoriteListDoQuery;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoriteRentMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavoriteRent record);

    int insertSelective(UserFavoriteRent record);

    Integer insertRentHouseSelective(UserFavoriteRentDoQuery userFavoriteRentDoQuery);

    UserFavoriteRent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavoriteRent record);

    int updateByPrimaryKey(UserFavoriteRent record);

    Integer selectRentFavoriteByUserId(Integer userId);

    Integer isRentFavoriteByRentIdAndUserId(@Param("houseId") String houseId, @Param("userId") Integer userId);

    Integer updateRentFavoriteByHouseIdAndUserId(DeleteRentFavoriteDoQuery deleteRentFavoriteDoQuery);

    /**
     * 租房收藏列表
     * @param rentFavoriteListDoQuery
     * @return
     */
    List<RentFavoriteDo> selectRentFavoritesByUserId(RentFavoriteListDoQuery rentFavoriteListDoQuery);
}