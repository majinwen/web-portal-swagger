package com.toutiao.web.apiimpl.rest.sys;


import com.toutiao.app.api.chance.request.sys.LoginVerifyCodeRequest;
import com.toutiao.app.service.sys.ShortMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rest/sys")
@RestController
public class ShortMessageRestController {

    @Autowired
    private ShortMessageService shortMessageService;


    /**
     * 登录时发送手机验证码
     *
     * @param loginVerifyCodeRequest
     * @return
     */

    @RequestMapping(value = "/sendLoginVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public String sendLoginVerifyCode(@Validated @RequestBody LoginVerifyCodeRequest loginVerifyCodeRequest) {

        String phone = loginVerifyCodeRequest.getPhone();
        Integer type = loginVerifyCodeRequest.getType();
        String nashResult =shortMessageService.sendVerifyCode(phone,type);
        return nashResult;
    }


}


