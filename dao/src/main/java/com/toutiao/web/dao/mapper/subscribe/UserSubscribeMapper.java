package com.toutiao.web.dao.mapper.subscribe;

import com.toutiao.app.domain.subscribe.UserConditionSubscribeDetailDo;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSubscribeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSubscribe record);

    int insertSelective(UserSubscribe record);

    UserSubscribe selectByPrimaryKey(Integer id);

    UserSubscribe selectByUserSubscribeMap(@Param(value = "record")UserSubscribeDetailDo userSubscribeDetailDo,@Param(value = "userId")Integer userId);

    int updateByPrimaryKeySelective(UserSubscribe record);

    int updateByPrimaryKey(UserSubscribe record);

    List<UserSubscribe> selectByUserId(@Param("userId")Integer userId, @Param("cityId")Integer cityId);

    List<UserSubscribe> selectConditionSubscribeByUserId(@Param("userId")Integer userId);

    UserSubscribe selectConditionSubscribeByUserSubscribeMap(@Param(value = "record")UserConditionSubscribeDetailDo userConditionSubscribeDetailDo,
                                                             @Param(value = "userId")Integer userId, @Param(value = "cityId")Integer cityId);

}