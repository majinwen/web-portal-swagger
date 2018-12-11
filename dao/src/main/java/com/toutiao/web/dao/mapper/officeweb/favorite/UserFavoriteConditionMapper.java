package com.toutiao.web.dao.mapper.officeweb.favorite;

import com.toutiao.app.domain.favorite.UserFavoriteCondition;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteConditionMapper extends BaseDao {
    int insert(UserFavoriteCondition record);

    int insertSelective(UserFavoriteCondition record);

    UserFavoriteCondition getRecommendCondition(UserFavoriteCondition record);

    int updateRecommendCondition(UserFavoriteCondition record);

    int deleteRecommendCondition(@Param("userId") Integer userId, @Param("cityId") Integer cityId);
}