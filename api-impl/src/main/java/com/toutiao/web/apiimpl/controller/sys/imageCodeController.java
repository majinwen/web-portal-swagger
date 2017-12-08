package com.toutiao.web.apiimpl.controller.sys;

import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.PatchcaImageUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片验证码
 */
@RestController
@RequestMapping(value = "/admin/code")
public class imageCodeController {

    /**
     * 生成登陆验证码
     *
     * @return
     * @author
     */
    @RequestMapping(value = "/imageCode", method = RequestMethod.GET)
    @ResponseBody
    public NashResult buidloginValifyCodeImage(ModelMap map, HttpServletRequest request,
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
            patchca = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将验证码放置到cookie中
        CookieUtils.setCookie(request,response,"imageCode",patchca,-1);
        return NashResult.build(patchca);
    }
}
