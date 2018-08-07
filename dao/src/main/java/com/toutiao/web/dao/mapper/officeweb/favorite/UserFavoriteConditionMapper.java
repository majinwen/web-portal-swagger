package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.UserFavoriteCondition;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

public interface UserFavoriteConditionMapper extends BaseDao {
    int insert(UserFavoriteCondition record);

    int insertSelective(UserFavoriteCondition record);

    UserFavoriteCondition getRecommendCondition(@Param("userId") Integer userId);
}