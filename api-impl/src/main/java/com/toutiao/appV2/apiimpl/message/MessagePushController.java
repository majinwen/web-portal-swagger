package com.toutiao.appV2.apiimpl.message;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.response.user.UserLoginResponse;
import com.toutiao.app.domain.message.*;
import com.toutiao.app.service.message.MessagePushService;
import com.toutiao.appV2.api.message.MessagePushApi;
import com.toutiao.appV2.model.message.HomeMessageResponse;
import com.toutiao.appV2.model.message.HomePageMessageRequest;
import com.toutiao.appV2.model.message.HouseMessageV2Request;
import com.toutiao.appV2.model.message.MessagePushRequest;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 消息推送接口
 */
@Controller
public class MessagePushController implements MessagePushApi {

    private static final Logger log = LoggerFactory.getLogger(MessagePushController.class);

    private final HttpServletRequest request;

    @Autowired
    public MessagePushController(HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    private MessagePushService messagePushService;

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

    @Override
    public ResponseEntity<HomeMessageResponse> getHomeMessage( HttpServletRequest req,
                                                              HttpServletResponse response) {
        UserBasic current = UserBasic.getCurrent();

//        List<HomeMessageDo> homePageMessage = messagePushService.getHomeMessageNew(homeMessageDoQuery, userId);
        //新版本也暂时隐掉二手房动态消息
        List<HomeMessageDo> homePageMessage = messagePushService.getHomeMessageNew(current.getUserId());
        HomeMessageResponse homeMessageResponse = new HomeMessageResponse();
        homeMessageResponse.setData(homePageMessage);
        homeMessageResponse.setTotalNum(homePageMessage.size());
        return new ResponseEntity<>(homeMessageResponse, HttpStatus.OK);

    }

  /*  @Override
    public ResponseEntity<HomeMessageResponse> getHomeMessage(@ApiParam(value = "homePageMessageRequest") HomePageMessageRequest homePageMessageRequest, HttpServletRequest req,
                                                              HttpServletResponse response) {
        UserBasic current = UserBasic.getCurrent();
        HomeMessageDoQuery homeMessageDoQuery = new HomeMessageDoQuery();
        BeanUtils.copyProperties(homePageMessageRequest, homeMessageDoQuery);
//        List<HomeMessageDo> homePageMessage = messagePushService.getHomeMessageNew(homeMessageDoQuery, userId);
        //新版本也暂时隐掉二手房动态消息
        List<HomeMessageDo> homePageMessage = messagePushService.getHomeMessageNew(homeMessageDoQuery, current
                .getUserId());
        HomeMessageResponse homeMessageResponse = new HomeMessageResponse();
        homeMessageResponse.setData(homePageMessage);
        homeMessageResponse.setTotalNum(homePageMessage.size());
        return new ResponseEntity<>(homeMessageResponse, HttpStatus.OK);

    }*/


    @Override
    public ResponseEntity<MessagePushDomain> getHouseTypeMessage(@ApiParam(value = "messagePushRequest") @Validated MessagePushRequest messagePushRequest, HttpServletRequest
            req, HttpServletResponse response) {
        UserBasic current = UserBasic.getCurrent();
        MessagePushDoQuery messagePushQuery = new MessagePushDoQuery();
        BeanUtils.copyProperties(messagePushRequest, messagePushQuery);
        MessagePushDomain message = messagePushService.getHouseTypeMessageNew(messagePushQuery, current.getUserId(), request);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessagePushDomain> getHouseTypeMessageV2(@Validated HouseMessageV2Request houseMessageV2Request,
                                                                   HttpServletRequest request) {
        UserBasic current = UserBasic.getCurrent();
        HouseMessageV2Query houseMessageV2Query = new HouseMessageV2Query();
        BeanUtils.copyProperties(houseMessageV2Request, houseMessageV2Query);
        MessagePushDomain message = messagePushService.getHouseTypeMessageV2(houseMessageV2Query, current.getUserId(), request);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessagePushDomain> getThemeTypeMessage(@ApiParam(value = "messagePushRequest") @Validated MessagePushRequest messagePushRequest, HttpServletRequest
            req, HttpServletResponse response) {
        UserBasic current = UserBasic.getCurrent();
        MessagePushDoQuery messagePushQuery = new MessagePushDoQuery();
        BeanUtils.copyProperties(messagePushRequest, messagePushQuery);
        MessagePushDomain message = messagePushService.getThemeTypeMessage(messagePushQuery, current.getUserId(), request);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
