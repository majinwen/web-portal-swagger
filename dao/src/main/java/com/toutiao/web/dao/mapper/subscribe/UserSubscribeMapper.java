package com.toutiao.web.dao.mapper.subscribe;

import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscribeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSubscribe record);

    int insertSelective(UserSubscribe record);

    UserSubscribe selectByPrimaryKey(Integer id);

    UserSubscribe selectByUserSubscribeMap(@Param(value = "record")UserSubscribeDetailDo userSubscribeDetailDo,@Param(value = "userId")Integer userId);

    int updateByPrimaryKeySelective(UserSubscribe record);

    int updateByPrimaryKey(UserSubscribe record);
}