package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.FavoriteHouseCountDto;
import com.toutiao.app.domain.favorite.FavoriteHouseDo;
import com.toutiao.app.domain.favorite.FavoriteIdDo;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wk on 2018/11/28.
 */
@Repository
public interface FavoriteRestMapper extends BaseDao {

    List<FavoriteHouseDo> queryFavoriteList(@Param("userId") Integer userId);

    FavoriteHouseCountDto queryFavoriteHouseCount(@Param("userId") Integer userId);

    FavoriteIdDo queryFavoriteId(@Param("type") Integer type);
}
