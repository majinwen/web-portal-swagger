package com.toutiao.web.apiimpl.controller.auth;


import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.toutiao.web.apiimpl.authentication.IgnoreLogin;
import com.toutiao.web.apiimpl.authentication.Permission;
import com.toutiao.web.apiimpl.conf.interceptor.LoginAndPermissionConfig;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.MD5Util;
import com.toutiao.web.common.util.RedisUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.service.repository.admin.SysMenuService;
import com.toutiao.web.service.repository.admin.SysRoleService;
import com.toutiao.web.service.repository.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Login {

    @Autowired
    private LoginAndPermissionConfig loginAndPermissionConfig;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    @ResponseBody
    @IgnoreLogin
    @Permission(ignore = true)
    public NashResult login(@RequestParam(value = "phone") String phone,
                            @RequestParam(value = "code") String code,
                            HttpServletResponse response, HttpServletRequest request,
                            @RequestParam(value = "imageCode", required = false) String imageCode) {
        //判断页面传递过来的电话号码与短信验证码是否为空
        if (StringTool.isBlank(phone) || StringTool.isBlank(code)) {
            return NashResult.Fail("fail", "手机号/验证码错误！");
        }
        //判断传输过来的电话号码是否全是数字并且是否是手机号码
        if (!StringUtil.isInt(phone) || StringUtil.isCellphoneNo(phone)) {
            return NashResult.Fail("fail", "手机号输入有误,请重新输入手机号码！");
        }
        //获取当前用户电话号码在缓存中的次数
        int count = Integer.parseInt(RedisUtils.getValue(phone + "@@@@count"));
        //获取缓存中的手机号码判断是否有效
        if (RedisUtils.exists(phone)) {
            //判断相关信息数据是否一致
            if (RedisUtils.getValue(phone).equalsIgnoreCase(MD5Util.computeUTF(MD5Util.computeUTF(code)))) {
                //从cookie中获取图片验证码与页面传递过来的验证码进行对比
                if (count > 3) {

                    if (StringTool.isBlank(imageCode)) {
                        return NashResult.Fail("fail", "请输入图片验证码", count);
                    }
                    Cookie[] cookie = request.getCookies();

                    for (int i = 0; i < cookie.length; i++) {

                        Cookie cook = cookie[i];

                        if (cook.getName().equalsIgnoreCase("imageCode")) {

                            if (!cook.getValue().equalsIgnoreCase(code)) {
                                return NashResult.Fail("fail", "图片验证码有误！", count);
                            }

                        }
                    }
                }
                //查询该电话号是否存在
                int i = sysUserService.selectByPhone(phone);
                if (i == 0) {
                    //首次登陆
                    //登陆成功后需要将用户手机号插入的数据库
                    sysUserService.insertPhone(phone);
                    return NashResult.build(phone);
                }
                return NashResult.build(phone);
            }
        }
        return NashResult.Fail("fail", "填写的信息有误，请重新填写！", count);
    }


    /**
     * 用户退出功能
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = {"/logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    @Permission(ignore = true)
    public NashResult logout(HttpServletResponse response,
                             @RequestParam(value="phone",required = true) String phone) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        //清空cookie中的数据
        Cookie cookie = new Cookie("imageCode", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        //清空redis中的数据
        RedisUtils.delKey(phone);
        RedisUtils.delKey(phone+"@@@@count");
        return NashResult.build(0);
    }

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
        ConfigurableCaptchaService cs = this.getCs();
        try {
            OutputStream os = response.getOutputStream();
            patchca = EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将验证码放置到cookie缓存中
        Cookie cookie = new Cookie("imageCode", patchca);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        return NashResult.build(patchca);
    }

    /**
     * 验证码
     *
     * @return
     */
    private ConfigurableCaptchaService getCs() {
        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        RandomFontFactory r = new RandomFontFactory();
        r.setMaxSize(16);
        r.setMaxSize(12);
        r.setRandomStyle(true);
        List<String> list = new ArrayList<String>();
        list.add("Cursive");
        r.setFamilies(list);
        cs.setFontFactory(r);
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        cs.setWidth(120);
        cs.setHeight(60);
        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        // AdaptiveRandomWordFactory t = new AdaptiveRandomWordFactory();
        RandomWordFactory t = new RandomWordFactory();
        //0123456789abcdefghijklmnopqrstuvwsyz
        t.setCharacters("ABCDEFGHJKLMNOPQRSTUVWSYZ123456789");
        t.setMaxLength(4);
        t.setMinLength(4);
        cs.setWordFactory(t);
        return cs;
    }

}
