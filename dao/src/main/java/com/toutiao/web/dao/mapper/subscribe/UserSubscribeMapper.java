package com.toutiao.web.dao.mapper.subscribe;

import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscribeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSubscribe record);

    int insertSelective(UserSubscribe record);

    UserSubscribe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSubscribe record);

    int updateByPrimaryKey(UserSubscribe record);
}