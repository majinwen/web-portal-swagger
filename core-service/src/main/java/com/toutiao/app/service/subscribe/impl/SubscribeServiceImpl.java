package com.toutiao.app.service.subscribe.impl;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.domain.subscribe.UserSubscribeListDo;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import com.toutiao.web.dao.mapper.subscribe.UserSubscribeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<UserSubscribeListDo> selectByUserId(Integer userId) {

        List<UserSubscribeListDo> userSubscribeListDoList = new ArrayList<>();
        List<UserSubscribe> userSubscribeList = userSubscribeMapper.selectByUserId(userId);
        for (UserSubscribe userSubscribe : userSubscribeList) {
            UserSubscribeListDo userSubscribeListDo = new UserSubscribeListDo();
            BeanUtils.copyProperties(userSubscribe, userSubscribeListDo);
            userSubscribeListDo.setUserSubscribeDetail(JSONObject.parseObject(userSubscribe.getUserSubscribeMap(), UserSubscribeDetailDo.class));
            userSubscribeListDoList.add(userSubscribeListDo);
        }
        return userSubscribeListDoList;
    }
}
