package com.toutiao.web.apiimpl.controller.sys;

import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.service.Captcha;
import com.toutiao.web.common.util.Constant;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.PatchcaImageUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片验证码
 */
@RestController
@RequestMapping(value = "/code")
public class imageCodeController {

    /**
     * 生成登陆验证码
     *
     * @return
     * @author
     */
    @RequestMapping(value = "/imageCode", method = RequestMethod.GET)
    @ResponseBody
    public void buidloginValifyCodeImage(ModelMap map, HttpServletRequest request,
                                               HttpServletResponse response) {
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 输出图象到页面
        String patchca = "";
        ConfigurableCaptchaService cs = PatchcaImageUtils.getCs();
        try {
            OutputStream os = response.getOutputStream();
            Captcha captcha = cs.getCaptcha();
            //获取验证码
            patchca = captcha.getChallenge();
            //将验证码数据放到cookie中
            Cookie cookie = CookieUtils.setCookie(request, response, "imageCode", patchca);
            response.addCookie(cookie);
            //将验证码传输到页面中
            ImageIO.write(captcha.getImage(), "png", os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    // 验证验证码是否正确
    @RequestMapping("/getCode")
    public void validateCode(HttpServletRequest request,HttpServletResponse response,String pageCode) {
        try {
            String code = CookieUtils.getCookie(request, response, "imageCode");

            String info = "";
            if(code.toLowerCase().equals(pageCode.toLowerCase())){
                info = Constant.YES;
            }else{
                info = Constant.NO;
            }
            response.getOutputStream().print(info);
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
