package com.toutiao.app.service.subscribe.impl;

import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import com.toutiao.web.dao.mapper.subscribe.UserSubscribeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeServiceImpl implements SubscribeService {
    @Autowired
    UserSubscribeMapper userSubscribeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userSubscribeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(UserSubscribe record) {
        return userSubscribeMapper.insertSelective(record);
    }

    @Override
    public UserSubscribe selectByPrimaryKey(Integer id) {
        return userSubscribeMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserSubscribe selectByUserSubscribeMap(UserSubscribeDetailDo userSubscribeDetailDo, Integer userId) {
        return userSubscribeMapper.selectByUserSubscribeMap(userSubscribeDetailDo, userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserSubscribe record) {
        return userSubscribeMapper.updateByPrimaryKeySelective(record);
    }
}
