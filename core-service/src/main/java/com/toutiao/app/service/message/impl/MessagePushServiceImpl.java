package com.toutiao.app.service.message.impl;

import com.toutiao.app.domain.message.MessagePushDoQuery;
import com.toutiao.app.service.message.MessagePushService;
import com.toutiao.web.dao.entity.message.MessagePush;
import com.toutiao.web.dao.entity.message.MessagePushExample;
import com.toutiao.web.dao.mapper.message.MessagePushMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessagePushServiceImpl implements MessagePushService {
    @Autowired
    private MessagePushMapper messagePushMapper;

    /**
     * 获取消息
     *
     * @param messagePushQuery
     */
    @Override
    public List<MessagePush> getMessage(MessagePushDoQuery messagePushQuery) {
        MessagePushExample example = new MessagePushExample();
        MessagePushExample.Criteria criteria = example.createCriteria();
        if (messagePushQuery.getUserId() != null) {
            criteria.andUserIdEqualTo(messagePushQuery.getUserId());
        }

        if (messagePushQuery.getCreateTime() != null) {
            criteria.andCreateTimeLessThanOrEqualTo(messagePushQuery.getCreateTime());
        } else {
            Date date = new Date();
            criteria.andCreateTimeGreaterThanOrEqualTo(date);
        }

        if (messagePushQuery.getContentType() != null) {
            criteria.andContentTypeEqualTo(messagePushQuery.getContentType());
        }

        if (messagePushQuery.getMessageType() != null) {
            criteria.andMessageTypeEqualTo(messagePushQuery.getMessageType());
        }

        if (messagePushQuery.getPushType() != null) {
            criteria.andPushTypeEqualTo(messagePushQuery.getPushType());
        }
        List<MessagePush> messagePushes = messagePushMapper.selectByExample(example);
        return messagePushes;
    }
}
