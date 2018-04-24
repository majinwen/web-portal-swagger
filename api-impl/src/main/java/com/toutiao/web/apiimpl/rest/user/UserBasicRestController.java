package com.toutiao.web.apiimpl.rest.user;


import com.toutiao.app.api.chance.request.user.QueryUserBasicRequest;
import com.toutiao.app.api.chance.request.user.UploadUserAvatarRequest;
import com.toutiao.app.api.chance.response.user.UserBasicResponse;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.restmodel.InvokeResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/user/basic")
public class UserBasicRestController {

    @Autowired
    private UserBasicInfoService userBasicInfoService;

    /**
     * 用户更新头像信息
     * @param uploadUserAvatarRequest
     * @return
     */
    @RequestMapping(value = "/updateUserAvatar", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public InvokeResult updateUserAvatar(@Validated UploadUserAvatarRequest uploadUserAvatarRequest) {

        UserBasicDo userBasicDo = userBasicInfoService.updateUserAvatar(uploadUserAvatarRequest.getUserId(),uploadUserAvatarRequest.getFile());
        UserBasicResponse userBasicResponse = new UserBasicResponse();
        BeanUtils.copyProperties(userBasicDo,userBasicResponse);
        return InvokeResult.build(userBasicResponse);
    }


    @RequestMapping(value = "/queryUserBasic", method = RequestMethod.GET)
    @ResponseBody
    public InvokeResult queryUserBasic(@Validated QueryUserBasicRequest queryUserBasicRequest) {

        UserBasicDo userBasicDo = userBasicInfoService.queryUserBasic(queryUserBasicRequest.getUserId());
        UserBasicResponse userBasicResponse = new UserBasicResponse();
        BeanUtils.copyProperties(userBasicDo,userBasicResponse);
        return InvokeResult.build(userBasicResponse);
    }

}
