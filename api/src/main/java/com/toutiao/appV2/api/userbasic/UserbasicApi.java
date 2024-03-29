/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.0-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.toutiao.appV2.api.userbasic;

import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.appV2.model.userbasic.*;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:40:39.438Z")

@RequestMapping("/rest")
@Api(value = "用户基本信息", description = "查询用户基本信息接口")
public interface UserbasicApi {

    @ApiOperation(value = "获取用户缓存", nickname = "getUserCache", notes = "", response = UserLoginResponse.class,
            tags = {"用户基本信息",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserLoginResponse.class),
            @ApiResponse(code = 404, message = "未找到用户")})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/getUserCache",
            produces = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<UserLoginResponse> getUserCache();


    @ApiOperation(value = "登出", nickname = "logout", notes = "", response = String.class, tags = {"用户基本信息",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class), @ApiResponse(code = 404, message = "未找到用户")})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/logout",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.POST)
    ResponseEntity<StringDataResponse> logout() throws Exception;


    @ApiOperation(value = "查询基本信息", nickname = "queryUserBasic", notes = "", response = UserBasicResponse.class,
            tags = {"用户基本信息",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserBasicResponse.class),
            @ApiResponse(code = 404, message = "未找到用户")})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/queryUserBasic",
            produces = "application/json",
            params = "userId",
            method = RequestMethod.GET)
    ResponseEntity<UserBasicResponse> queryUserBasic(@ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) String userId);


    @ApiOperation(value = "通过融云ID获取基本信息", nickname = "queryUserBasicByRcId", notes = "",
            response = UserBasicResponse.class, tags = {"用户基本信息",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserBasicResponse.class),
            @ApiResponse(code = 404, message = "未找到用户")
    })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/queryUserBasicByRcId",
            produces = "application/json",
            params = "userId",
            method = RequestMethod.GET)
    ResponseEntity<UserBasicResponse> queryUserBasicByRcId(@ApiParam(value = "") @Valid @RequestParam(value = "rcId", required = false) Optional<String> rcId);


    @ApiOperation(value = "更新用户头像", nickname = "updateUserAvatar", notes = "", response = UserBasicResponse.class,
            tags = {"用户基本信息",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserBasicResponse.class),})
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/updateUserAvatar",
            produces = "application/json",
            consumes = "multipart/form-data",
            method = RequestMethod.POST)
    ResponseEntity<UserBasicResponse> updateUserAvatar(@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file, @ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) Optional<String> userId);


    @ApiOperation(value = "用户登录", nickname = "userVerifyCodeLogin", notes = "", response = UserLoginResponse.class,
            tags = {"用户基本信息",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserLoginResponse.class),
            @ApiResponse(code = 404, message = "未找到用户")
    })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/userVerifyCodeLogin",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.POST)
    ResponseEntity<UserLoginResponse> userVerifyCodeLogin(@ApiParam(value = "loginRequest", required = true) @Valid @RequestBody UserVerifyCodeRequest loginRequest);

    @ApiOperation(value = "生成图片验证码", nickname = "produceImageCode", notes = "", tags={ "用户基本信息", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/imageCode",
            produces = "application/json",
//            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<Void> produceImageCode(@ApiParam(value = "0:普通, 1:微信", defaultValue = "0") @Valid @RequestParam(value = "type", required = true, defaultValue="0") String type);


    @ApiOperation(value = "登录时发送手机验证码", nickname = "sendLoginVerifyCode", notes = "", response = LoginVerifyCodeResponse.class, tags={ "用户基本信息", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LoginVerifyCodeResponse.class),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/sendLoginVerifyCode",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.POST)
    ResponseEntity<LoginVerifyCodeResponse> sendLoginVerifyCode(@ApiParam(value = "loginVerifyCodeRequest" ,required=true )  @Valid @RequestBody LoginVerifyCodeRequest loginVerifyCodeRequest);


    @ApiOperation(value = "验证验证码是否正确", nickname = "validateImageCode", notes = "", tags={ "用户基本信息", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/getCode",
            produces = "application/json",
//            consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<Void> validateImageCode(@ApiParam(value = "pageCode") @Valid @RequestParam(value = "pageCode") String pageCode, @ApiParam(value = "type",defaultValue = "0") @Valid @RequestParam(value = "type") String type);


    @ApiOperation(value = "获取用户订阅,关注等统计数量", nickname = "getUserSubscribeEtcCount", notes = "", response = UserSubscribeEtcCountResponse.class, tags={ "用户基本信息", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserSubscribeEtcCountResponse.class),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/UserSubscribeEtcCountResponse",
            produces = "application/json",
            //consumes = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<UserSubscribeEtcCountResponse> getUserSubscribeEtcCount(Integer flag);

    @ApiOperation(value = "解除微信绑定", nickname = "unbindweixin", notes = "", tags={ "用户基本信息", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/unbindweixin",
            produces = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<UnbindResponse> unbindweixin();

    @ApiOperation(value = "微信登录", nickname = "weixinLogin", notes = "", tags={ "用户基本信息", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/weixinLogin",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.POST)
    ResponseEntity<UserLoginResponse> weixinLogin(@ApiParam(value = "wxUserLoginRequest") @Valid @RequestBody WXUserLoginRequest wxUserLoginRequest);

    @ApiOperation(value = "获取微信用户信息", nickname = "getWXUserBasic", notes = "", tags={ "用户基本信息", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/getWXUserBasic",
            produces = "application/json",
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<WXUserBasicResponse> getWXUserBasic(@ApiParam(value = "code") @Valid @RequestParam(value = "code") String code);


    @ApiOperation(value = "小程序登录", nickname = "smallProgramLogin", notes = "", tags={ "用户基本信息", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found") })
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/userbasic/smallProgramLogin",
            produces = "application/json",
            method = RequestMethod.GET)
    ResponseEntity<UserLoginResponse> smallProgramLogin(@ApiParam(value = "code") @Valid @RequestParam(value = "code") String code	,
                                                                 @ApiParam(value = "iv") @Valid @RequestParam(value = "iv") String iv,
                                                                 @ApiParam(value = "rawData") @Valid @RequestParam(value = "rawData") String rawData);


}
