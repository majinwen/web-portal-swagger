package com.toutiao.web.apiimpl.rest.user;


import com.toutiao.app.api.chance.request.user.QueryUserBasicRequest;
import com.toutiao.app.api.chance.request.user.UploadUserAvatarRequest;
import com.toutiao.app.api.chance.response.user.UserBasicResponse;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.Constant;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.RedisObjectType;
import com.toutiao.web.common.util.RedisSessionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rest/user/basic")
public class UserBasicRestController {

    @Autowired
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private RedisSessionUtils redis;

    /**
     * 用户更新头像信息
     * @param uploadUserAvatarRequest
     * @return
     */
    @RequestMapping(value = "/updateUserAvatar", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public NashResult updateUserAvatar(@Validated UploadUserAvatarRequest uploadUserAvatarRequest,
                                       HttpServletRequest request, HttpServletResponse response) {

        UserBasicDo userBasicDo = userBasicInfoService.updateUserAvatar(uploadUserAvatarRequest.getUserId(),
                uploadUserAvatarRequest.getFile(),request,response);
        UserBasicResponse userBasicResponse = new UserBasicResponse();
        BeanUtils.copyProperties(userBasicDo,userBasicResponse);
        return NashResult.build(userBasicResponse);
    }


    @RequestMapping(value = "/queryUserBasic", method = RequestMethod.GET)
    @ResponseBody
    public NashResult queryUserBasic(@Validated QueryUserBasicRequest queryUserBasicRequest) {

        UserBasicDo userBasicDo = userBasicInfoService.queryUserBasic(queryUserBasicRequest.getUserId());
        UserBasicResponse userBasicResponse = new UserBasicResponse();
        BeanUtils.copyProperties(userBasicDo,userBasicResponse);
        return NashResult.build(userBasicResponse);
    }

    /**
     * 根据rcId获取用户信息
     * @param queryUserBasicRequest
     * @return
     */
    @RequestMapping(value = "/queryUserBasicByRcId", method = RequestMethod.GET)
    @ResponseBody
    public NashResult queryUserBasicByRcId(@Validated(First.class) QueryUserBasicRequest queryUserBasicRequest) {

        UserBasicDo userBasicDo = userBasicInfoService.queryUserBasicByRcId(queryUserBasicRequest.getRcId());
        UserBasicResponse userBasicResponse = new UserBasicResponse();
        BeanUtils.copyProperties(userBasicDo,userBasicResponse);
        return NashResult.build(userBasicResponse);
    }



    /**
     * 用户退出功能
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/logout")
    @ResponseBody
    public NashResult logout(HttpServletResponse response, HttpServletRequest request,
                         @RequestParam(value = "phone", required = true) String phone) throws Exception {

        clearCookieAndCache(request, response, phone);
        return NashResult.build("");
    }


    /**
     * 清除缓存数据
     *
     * @param request
     * @param response
     * @throws Exception
     */
    private void clearCookieAndCache(HttpServletRequest request, HttpServletResponse response, String phone) throws Exception {
        //从cookie中删除用户数据
        CookieUtils.deleteCookie(request, response, CookieUtils.COOKIE_NAME_User_LOGIN);
        //删除redis中的用户数据
        redis.delKey(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                + phone);
    }

}
