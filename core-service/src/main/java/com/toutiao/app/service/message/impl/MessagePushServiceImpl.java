package com.toutiao.app.service.message.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.message.MessagePushDo;
import com.toutiao.app.domain.message.MessagePushDoQuery;
import com.toutiao.app.domain.message.MessagePushDomain;
import com.toutiao.app.domain.message.MessageSellHouseDo;
import com.toutiao.app.service.message.MessagePushService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.dao.entity.message.MessagePush;
import com.toutiao.web.dao.entity.message.MessagePushExample;
import com.toutiao.web.dao.mapper.message.MessagePushMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MessagePushServiceImpl implements MessagePushService {
    @Autowired
    private MessagePushMapper messagePushMapper;

    @Autowired
    private SellHouseService sellHouseService;

    /**
     * 获取消息
     * @param messagePushQuery
     *
     */
    @Override
    public MessagePushDomain getMessage(MessagePushDoQuery messagePushQuery) {
        MessagePushExample example = new MessagePushExample();
        example.setOrderByClause("create_time DESC");
        MessagePushExample.Criteria criteria = example.createCriteria();
        if (messagePushQuery.getUserId() != null) {
            criteria.andUserIdEqualTo(messagePushQuery.getUserId());
        }

        if (messagePushQuery.getCreateTime() != null) {
            criteria.andCreateTimeLessThan(messagePushQuery.getCreateTime());
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date zero = calendar.getTime();
            criteria.andCreateTimeGreaterThanOrEqualTo(zero);
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

        //分页并统计总数
//        Page<MessagePush> page = PageHelper.startPage(messagePushQuery.getPageNum(), messagePushQuery.getPageSize(), true);

        List<MessagePush> messagePushes = messagePushMapper.selectByExample(example);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(messagePushes));
        List<MessagePushDo> messagePushDos = JSONObject.parseArray(json.toJSONString(), MessagePushDo.class);
        List<MessagePushDo> result = new ArrayList<>();
        for (MessagePushDo messagePushDo : messagePushDos) {
            String houseIds = messagePushDo.getHouseId();
            if (!"{}".equals(houseIds)) {
                String[] split = houseIds.substring(1, houseIds.length() - 1).split(",");
                List<MessageSellHouseDo> messageSellHouseDos = sellHouseService.querySellHouseByHouseId(split);
                if (!CollectionUtils.isEmpty(messageSellHouseDos)) {
                    for (MessageSellHouseDo messageSellHouseDo : messageSellHouseDos) {
                        MessagePushDo message = new MessagePushDo();
                        BeanUtils.copyProperties(messagePushDo, message);
                        message.setMessageSellHouseDo(messageSellHouseDo);
                        result.add(message);
                    }
                }
            }
        }
        MessagePushDomain messagePushDomain = new MessagePushDomain();
        messagePushDomain.setData(result);
//        messagePushDomain.setTotalCount(page.getTotal());

        return messagePushDomain;
    }
}
