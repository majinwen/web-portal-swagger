package com.toutiao.appV2.apiimpl.message;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.response.user.UserLoginResponse;
import com.toutiao.app.domain.message.HomeMessageDo;
import com.toutiao.app.domain.message.HomeMessageDoQuery;
import com.toutiao.app.domain.message.MessagePushDoQuery;
import com.toutiao.app.domain.message.MessagePushDomain;
import com.toutiao.app.service.message.MessagePushService;
import com.toutiao.appV2.api.message.MessagePushApi;
import com.toutiao.appV2.model.message.HomeMessageResponse;
import com.toutiao.appV2.model.message.HomePageMessageRequest;
import com.toutiao.appV2.model.message.MessagePushRequest;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:45:59.146Z")
/**
 * 消息推送接口
 */
@Controller
public class MessagePushController implements MessagePushApi {

    private static final Logger log = LoggerFactory.getLogger(MessagePushController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public MessagePushController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    @Autowired
    private MessagePushService messagePushService;

    /**
     * 房源类消息列表(新版本所有消息都调用此接口)
     */
//    @RequestMapping(value = "/getHouseTypeMessageNew", method = RequestMethod.GET)
//    @ResponseBody
//    public NashResult getHouseTypeMessageNew(@Validated MessagePushRequest messagePushRequest, HttpServletRequest
//            request, HttpServletResponse response) {
//        String userId = getUserIdByCookie(request, response);
//        if (StringTool.isEmpty(userId)) {
//            return NashResult.Fail("用户未登录");
//        }
//        MessagePushDoQuery messagePushQuery = new MessagePushDoQuery();
//        BeanUtils.copyProperties(messagePushRequest, messagePushQuery);
//        MessagePushDomain message = messagePushService.getHouseTypeMessageNew(messagePushQuery, userId, request);
//        return NashResult.build(message);
//    }

    /**
     * 首页消息列表(新版本)
     *
     * @param homePageMessageRequest
     * @return
     */
//    @RequestMapping(value = "/getHomeMessageNew", method = RequestMethod.GET)
//    @ResponseBody
//    public NashResult getHomeMessageNew(HomePageMessageRequest homePageMessageRequest, HttpServletRequest request,
//                                      HttpServletResponse response) {
//        String userId = getUserIdByCookie(request, response);
//        if (StringTool.isEmpty(userId)) {
//            return NashResult.Fail("用户未登录");
//        }
//        HomeMessageDoQuery homeMessageDoQuery = new HomeMessageDoQuery();
//        BeanUtils.copyProperties(homePageMessageRequest, homeMessageDoQuery);
////        List<HomeMessageDo> homePageMessage = messagePushService.getHomeMessageNew(homeMessageDoQuery, userId);
//        //新版本也暂时隐掉二手房动态消息
//        List<HomeMessageDo> homePageMessage = messagePushService.getHomeMessage(homeMessageDoQuery, userId);
//        return NashResult.build(homePageMessage);
//    }

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

    /**
     * 修改消息已读
     *
     * @param messageIsReadRequest
     * @param request
     * @param response
     * @return
     */
//    @RequestMapping(value = "/updateMessageRead", method = RequestMethod.GET)
//    @ResponseBody
//    public NashResult updateMessageRead(@Validated MessageIsReadRequest messageIsReadRequest, HttpServletRequest request,
//                                        HttpServletResponse response) {
//        //TODO 仍有地方调用，但是接口无效
//        return NashResult.build("消息已读!");
//    }

    @Override
    public ResponseEntity<HomeMessageResponse> getHomeMessage(@ApiParam(value = "homePageMessageRequest") HomePageMessageRequest homePageMessageRequest, HttpServletRequest req,
                                                              HttpServletResponse response) {
        String userId = getUserIdByCookie(req, response);
        if (StringTool.isEmpty(userId)) {
            return new ResponseEntity("用户未登录", HttpStatus.BAD_REQUEST);
        }
        HomeMessageDoQuery homeMessageDoQuery = new HomeMessageDoQuery();
        BeanUtils.copyProperties(homePageMessageRequest, homeMessageDoQuery);
//        List<HomeMessageDo> homePageMessage = messagePushService.getHomeMessageNew(homeMessageDoQuery, userId);
        //新版本也暂时隐掉二手房动态消息
        List<HomeMessageDo> homePageMessage = messagePushService.getHomeMessageNew(homeMessageDoQuery, userId);
        HomeMessageResponse homeMessageResponse = new HomeMessageResponse();
        homeMessageResponse.setData(homePageMessage);
        homeMessageResponse.setTotalNum(homePageMessage.size());
        return new ResponseEntity<>(homeMessageResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessagePushDomain> getHouseTypeMessage(@ApiParam(value = "messagePushRequest")@Validated MessagePushRequest messagePushRequest, HttpServletRequest
            req, HttpServletResponse response) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                String userId = getUserIdByCookie(req, response);
                if (StringTool.isEmpty(userId)) {
                    return new ResponseEntity("用户未登录", HttpStatus.BAD_REQUEST);
                }
                MessagePushDoQuery messagePushQuery = new MessagePushDoQuery();
                BeanUtils.copyProperties(messagePushRequest, messagePushQuery);
                MessagePushDomain message = messagePushService.getHouseTypeMessageNew(messagePushQuery, userId, request);
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端错误", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<MessagePushDomain> getThemeTypeMessage(@ApiParam(value = "messagePushRequest")@Validated MessagePushRequest messagePushRequest, HttpServletRequest
            req, HttpServletResponse response) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                String userId = getUserIdByCookie(req, response);
                if (StringTool.isEmpty(userId)) {
                    return new ResponseEntity("用户未登录", HttpStatus.BAD_REQUEST);
                }
                MessagePushDoQuery messagePushQuery = new MessagePushDoQuery();
                BeanUtils.copyProperties(messagePushRequest, messagePushQuery);
                MessagePushDomain message = messagePushService.getThemeTypeMessage(messagePushQuery, userId, request);
                return new ResponseEntity<>(message, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端错误", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


}
