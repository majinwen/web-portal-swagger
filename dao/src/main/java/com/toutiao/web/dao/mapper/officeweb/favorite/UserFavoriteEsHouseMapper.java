package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.DeleteEsfFavoriteDo;
import com.toutiao.app.domain.favorite.UserFavoriteEsHouse;
import com.toutiao.app.domain.favorite.UserFavoriteEsHouseDoQuery;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDo;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavoriteEsHouseMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFavoriteEsHouse record);

    int insertSelective(UserFavoriteEsHouse record);

    Integer insertSellHouseSelective(UserFavoriteEsHouseDoQuery record);

    UserFavoriteEsHouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFavoriteEsHouse record);

    int updateByPrimaryKey(UserFavoriteEsHouse record);

    Integer selectEsHouseFavoriteByUserId(Integer userId);

    Integer selectEsHouseFavoriteByUserIdAndCityId(@Param("userId") Integer userId, @Param("cityId") Integer cityId);

    Integer isEsfFavoriteByHouseIdAndUserId(@Param("houseId") String houseId,@Param("userId") Integer userId);

    Integer updateEsfFavoriteByEsfIdAndUserId(DeleteEsfFavoriteDo deleteEsfFavoriteDo);

    /**
     * 二手房收藏列表
     * @param sellHouseFavoriteListDoQuery
     * @return
     */
    List<SellHouseFavoriteDo> selectSellHouseFavoriteByUserId(SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery);

    List<SellHouseFavoriteDo> selectSellHouseFavoriteByUserIdAndCityId(SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery);

    /**
     * 比对列表
     * @param sellHouseFavoriteListDoQuery
     * @return
     */
    List<SellHouseFavoriteDo> selectComparedList(SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery);

}