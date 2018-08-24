package com.toutiao.web.apiimpl.rest.message;

import com.toutiao.app.domain.message.MessagePushDoQuery;
import com.toutiao.app.domain.message.MessagePushDomain;
import com.toutiao.app.service.message.MessagePushService;
import com.toutiao.web.api.chance.request.message.MessagePushRequest;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息推送接口
 */
@RestController
@RequestMapping("/rest/messagePush")
public class MessagePushController {
    @Autowired
    private MessagePushService messagePushService;

    /**
     * 获取消息推送
     */
    @ResponseBody
    @RequestMapping(value = "/getMessage")
    public NashResult getMessage(MessagePushRequest messagePushRequest) {
        MessagePushDoQuery messagePushQuery = new MessagePushDoQuery();
        BeanUtils.copyProperties(messagePushRequest, messagePushQuery);
        MessagePushDomain message = messagePushService.getMessage(messagePushQuery);
        return NashResult.build(message);
    }
}
