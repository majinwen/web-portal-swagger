package com.toutiao.appV2.api.message;

import com.toutiao.app.domain.message.MessagePushDomain;
import com.toutiao.appV2.model.message.HomeMessageResponse;
import com.toutiao.appV2.model.message.HomePageMessageRequest;
import com.toutiao.appV2.model.message.MessagePushRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:45:59.146Z")

@Api(value = "rest", description = "消息推送列表接口")
public interface MessagePushApi {

    @ApiOperation(value = "首页消息列表", nickname = "getHomeMessage", notes = "首页消息列表", response = HomeMessageResponse.class, tags={ "message-push-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HomeMessageResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/messagePush/getHomeMessage",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<HomeMessageResponse> getHomeMessage(@Validated HomePageMessageRequest homePageMessageRequest, HttpServletRequest request,
                                                       HttpServletResponse response);


    @ApiOperation(value = "房源类和专题类消息列表", nickname = "getHouseTypeMessage", notes = "房源类和专题类消息列表", response = MessagePushDomain.class, tags={ "message-push-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MessagePushDomain.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/messagePush/getHouseTypeMessage",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<MessagePushDomain> getHouseTypeMessage(@Validated MessagePushRequest messagePushRequest, HttpServletRequest
            request, HttpServletResponse response);

}
