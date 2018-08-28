package com.toutiao.web.apiimpl.rest.message;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.response.user.UserLoginResponse;
import com.toutiao.app.domain.message.HomeMessageDo;
import com.toutiao.app.domain.message.HomeMessageDoQuery;
import com.toutiao.app.domain.message.MessagePushDoQuery;
import com.toutiao.app.domain.message.MessagePushDomain;
import com.toutiao.app.service.message.MessagePushService;
import com.toutiao.web.api.chance.request.message.HomePageMessageRequest;
import com.toutiao.web.api.chance.request.message.MessagePushRequest;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 消息推送接口
 */
@Controller
@RequestMapping("/rest/messagePush")
public class MessagePushController {
    @Autowired
    private MessagePushService messagePushService;

    /**
     * 房源类消息列表
     */
    @RequestMapping(value = "/getHouseTypeMessage", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getHouseTypeMessage(@Validated MessagePushRequest messagePushRequest, HttpServletRequest request, HttpServletResponse response) {
        String userId = getUserIdByCookie(request, response);
        if (StringTool.isEmpty(userId)) {
            return NashResult.Fail("用户未登录");
        }
        MessagePushDoQuery messagePushQuery = new MessagePushDoQuery();
        BeanUtils.copyProperties(messagePushRequest, messagePushQuery);
        MessagePushDomain message = messagePushService.getHouseTypeMessage(messagePushQuery, userId);
        return NashResult.build(message);
    }

    /**
     * 房源类消息列表
     */
    @RequestMapping(value = "/getThemeTypeMessage", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getThemeTypeMessage(@Validated MessagePushRequest messagePushRequest, HttpServletRequest request, HttpServletResponse response) {
        String userId = getUserIdByCookie(request, response);
        if (StringTool.isEmpty(userId)) {
            return NashResult.Fail("用户未登录");
        }
        MessagePushDoQuery messagePushQuery = new MessagePushDoQuery();
        BeanUtils.copyProperties(messagePushRequest, messagePushQuery);
        MessagePushDomain message = messagePushService.getThemeTypeMessage(messagePushQuery, userId);
        return NashResult.build(message);
    }

    /**
     * 首页消息列表
     *
     * @param homePageMessageRequest
     * @return
     */
    @RequestMapping(value = "/getHomeMessage", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getHomeMessage(HomePageMessageRequest homePageMessageRequest, HttpServletRequest request, HttpServletResponse response) {
        String userId = getUserIdByCookie(request, response);
        if (StringTool.isEmpty(userId)) {
            return NashResult.Fail("用户未登录");
        }
        HomeMessageDoQuery homeMessageDoQuery = new HomeMessageDoQuery();
        BeanUtils.copyProperties(homePageMessageRequest, homeMessageDoQuery);
        List<HomeMessageDo> homePageMessage = messagePushService.getHomeMessage(homeMessageDoQuery, userId);
        return NashResult.build(homePageMessage);
    }

    /**
     * 从cookie获取用户id
     *
     * @param request
     * @param response
     * @return
     */
    private String getUserIdByCookie(HttpServletRequest request, HttpServletResponse response) {
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        if (user != null) {
            UserLoginResponse userLoginResponse = JSONObject.parseObject(user, UserLoginResponse.class);
            return userLoginResponse.getUserId();
        } else {
            return null;
        }
    }
}
