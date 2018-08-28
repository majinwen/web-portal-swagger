package com.toutiao.app.service.message.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.message.*;
import com.toutiao.app.service.message.MessagePushService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.message.MessagePush;
import com.toutiao.web.dao.entity.message.MessagePushExample;
import com.toutiao.web.dao.mapper.message.MessagePushMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessagePushServiceImpl implements MessagePushService {
    @Autowired
    private MessagePushMapper messagePushMapper;

    @Autowired
    private SellHouseService sellHouseService;


    /**
     * 房源类消息列表
     *
     * @param messagePushQuery
     * @param userId
     * @return
     */
    @Override
    public MessagePushDomain getHouseTypeMessage(MessagePushDoQuery messagePushQuery, String userId) {
        MessagePushExample example = new MessagePushExample();
        example.setOrderByClause("create_time DESC");
        MessagePushExample.Criteria criteria = example.createCriteria();
        if (StringTool.isNotEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }

        //内容类型(3-符合找房条件的房源上新, 4-关注小区房源上新, 5-关注房源价格变动)
        if (messagePushQuery.getContentType() != null) {
            criteria.andContentTypeEqualTo(messagePushQuery.getContentType());
        }
        //消息类型(0-资讯类, 1-系统消息, 2-房源类, 3-专题类)
        criteria.andMessageTypeEqualTo(2);
        //推送类型(0-系统消息, 1-定向推送)
        criteria.andPushTypeEqualTo(1);

        if (messagePushQuery.getLastMessageId() != null) {
            criteria.andIdGreaterThanOrEqualTo(messagePushQuery.getLastMessageId());
        }

        List<MessagePush> messagePushes = messagePushMapper.selectByExample(example);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(messagePushes));
        List<MessagePushDo> messagePushDos = JSONObject.parseArray(json.toJSONString(), MessagePushDo.class);
        MessagePushDomain messagePushDomain = new MessagePushDomain();
        if (CollectionUtils.isEmpty(messagePushDos)) {
            return messagePushDomain;
        }

        int messageHouseCount = 0;
        Integer lastMessageId = null;
        List<MessagePushDo> message = new ArrayList<>();
        for (MessagePushDo messagePushDo : messagePushDos) {
            String houseIds = messagePushDo.getHouseId();
            if (!"{}".equals(houseIds)) {
                String[] split = houseIds.substring(1, houseIds.length() - 1).split(",");
                List<MessageSellHouseDo> messageSellHouseDos = sellHouseService.querySellHouseByHouseId(split);
                messagePushDo.setMessageSellHouseDos(messageSellHouseDos);
                messageHouseCount += split.length;
                message.add(messagePushDo);
                if (messageHouseCount > 5) {
                    lastMessageId = messagePushDo.getId();
                    break;
                } else {
                    lastMessageId = messagePushDo.getId();
                }
            }
        }
        messagePushDomain.setData(message);
        messagePushDomain.setTotalCount(message.size());
        messagePushDomain.setLastMessageId(lastMessageId);

        return messagePushDomain;
    }

    /**
     * 专题类消息列表
     *
     * @param messagePushQuery
     * @param userId
     * @return
     */
    @Override
    public MessagePushDomain getThemeTypeMessage(MessagePushDoQuery messagePushQuery, String userId) {
        MessagePushExample example = new MessagePushExample();
        example.setOrderByClause("create_time DESC");
        MessagePushExample.Criteria criteria = example.createCriteria();
        if (StringTool.isNotEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        //内容类型(6-订阅的主题有更新)
        criteria.andContentTypeEqualTo(6);
        //消息类型(0-资讯类, 1-系统消息, 2-房源类, 3-专题类)
        criteria.andMessageTypeEqualTo(3);
        //推送类型(0-系统消息, 1-定向推送)
        criteria.andPushTypeEqualTo(1);

        if (messagePushQuery.getLastMessageId() != null) {
            criteria.andIdGreaterThanOrEqualTo(messagePushQuery.getLastMessageId());
        }

        List<MessagePush> messagePushes = messagePushMapper.selectByExample(example);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(messagePushes));
        List<MessagePushDo> messagePushDos = JSONObject.parseArray(json.toJSONString(), MessagePushDo.class);
        MessagePushDomain messagePushDomain = new MessagePushDomain();
        if (CollectionUtils.isEmpty(messagePushDos)) {
            return messagePushDomain;
        }

        Integer lastMessageId = null;
        if (messagePushDos.size() > 5) {
            List<MessagePushDo> message = messagePushDos.subList(0, 5);
            messagePushDomain.setData(message);
            lastMessageId = message.get(message.size() - 1).getId();
        } else {
            messagePushDomain.setData(messagePushDos);
            lastMessageId = messagePushDos.get(messagePushDos.size() - 1).getId();
        }
        messagePushDomain.setLastMessageId(lastMessageId);
        messagePushDomain.setTotalCount(messagePushDomain.getData().size());
        return messagePushDomain;
    }

    /**
     * 首页消息列表
     *
     * @param homeMessageDoQuery
     * @param userId
     * @return
     */
    @Override
    public List<HomeMessageDo> getHomeMessage(HomeMessageDoQuery homeMessageDoQuery, String userId) {
        ArrayList<HomeMessageDo> homeMessageDos = new ArrayList<>();
        for (int i = 3; i < 7; i++) {
            MessagePushExample example = new MessagePushExample();
            example.setOrderByClause("create_time DESC");
            MessagePushExample.Criteria criteria = example.createCriteria();
            if (StringTool.isNotEmpty(userId)) {
                criteria.andUserIdEqualTo(Integer.valueOf(userId));
            }
            //推送类型(0-系统消息, 1-定向推送)
            criteria.andPushTypeEqualTo(1);
            criteria.andContentTypeEqualTo(i);
            if (i == 3 && homeMessageDoQuery.getConditionHouseDate() != null) {
                criteria.andCreateTimeGreaterThanOrEqualTo(homeMessageDoQuery.getConditionHouseDate());
            } else if (i == 4 && homeMessageDoQuery.getFavoritePlotDate() != null) {
                criteria.andCreateTimeGreaterThanOrEqualTo(homeMessageDoQuery.getFavoritePlotDate());
            } else if (i == 5 && homeMessageDoQuery.getFavoriteHouseDate() != null) {
                criteria.andCreateTimeGreaterThanOrEqualTo(homeMessageDoQuery.getFavoriteHouseDate());
            } else if (i == 6 && homeMessageDoQuery.getSubscribeThemeDate() != null) {
                criteria.andCreateTimeGreaterThanOrEqualTo(homeMessageDoQuery.getSubscribeThemeDate());
            }
            Date date = new Date();
            criteria.andCreateTimeLessThan(date);
            List<MessagePush> messagePushes = messagePushMapper.selectByExample(example);
            HomeMessageDo homeMessageDo = new HomeMessageDo();
            homeMessageDo.setContentType(i);
            if (CollectionUtils.isEmpty(messagePushes)) {
                continue;
            }
            JSONArray json = JSONArray.parseArray(JSON.toJSONString(messagePushes));
            List<MessagePushDo> messagePushDos = JSONObject.parseArray(json.toJSONString(), MessagePushDo.class);
            homeMessageDo.setCount(messagePushDos.size());
            JSONObject jsonObject = JSON.parseObject(messagePushDos.get(0).getMessageTheme());
            homeMessageDo.setContent(jsonObject);
            homeMessageDo.setCreateTime(messagePushDos.get(0).getCreateTime().getTime());
            homeMessageDos.add(homeMessageDo);
        }
        return homeMessageDos;
    }
}
