package com.toutiao.web.apiimpl.controller.auth;


import com.toutiao.web.apiimpl.authentication.RedisSession;
import com.toutiao.web.common.util.*;
import com.toutiao.web.dao.entity.admin.SysUserEntity;
import com.toutiao.web.service.rediscache.RedisAndCookieService;
import com.toutiao.web.service.repository.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class Login {


    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisAndCookieService redisAndCookieService;

    private CookieUtils cookieUtils;

    @Autowired
    private RedisSession redisSession;

    /**
     * 功能描述：去登陆页面
     *
     * @param []
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/20 10:54
     */
    @RequestMapping("/login")
    public String goLoginPage(Model model) {
        return "login";
    }

    @RequestMapping(value = {"/tologin"}, method = {RequestMethod.POST})
    public String login(@RequestParam(value = "phone") String phone,
                        @RequestParam(value = "code") String code,
                        HttpServletResponse response, HttpServletRequest request,
                        @RequestParam(value = "imageCode", required = false) String imageCode, ModelMap modelMap) {
        try {
            //获取当前用户电话号码在缓存中的次数
            int count = StringTool.getInteger(redisSession.getValue(phone + RedisNameUtil.separativeSignCount));

            modelMap.addAttribute("phone", phone);
            modelMap.addAttribute("count", count);

            //判断页面传递过来的电话号码与短信验证码是否为空
            if (StringTool.isBlank(phone) || StringTool.isBlank(code)) {
                modelMap.addAttribute("message", "短信验证码输入有误！");
                return "login";
            }
            //判断传输过来的电话号码是否全是数字并且是否是手机号码
            if (!StringUtil.isCellphoneNo(phone)) {
                modelMap.addAttribute("message", "手机号输入有误！");
                return "login";
            }
            //获取缓存中的手机号码判断是否有效
            boolean flag = redisSession.exists(phone);
            if (!flag) {
                modelMap.addAttribute("message", "短信验证码失效！");
                return "login";
            }

            if (!redisSession.getValue(phone).equalsIgnoreCase(
                    MD5Util.computeUTF(MD5Util.computeUTF(RedisObjectType.USER_PHONE_VALIDATECODE.getPrefix() + RedisNameUtil.separativeSign
                    + code)))) {
                modelMap.addAttribute("message", "验证码输入有误！");
                return "login";
            }
            //从cookie中获取图片验证码与页面传递过来的验证码进行对比
            if (count > Constant.LOGIN_ERROR_TIMES) {
                if (StringUtils.isEmpty(imageCode) || StringTool.isEmpty(CookieUtils.getCookie(request, response,
                        "imageCode")) || CookieUtils.getCookie(request, response,
                        "imageCode").equalsIgnoreCase(imageCode)) {
                    modelMap.addAttribute("imageCode", imageCode);
                    modelMap.addAttribute("message", "图片验证码有误！");
                    return "login";
                }
            }
            //查询该电话号是否存在
            SysUserEntity sysUser = sysUserService.selectByPhone(phone);
            if (StringTool.isBlank(sysUser)) {
                //首次登陆
                //登陆成功后需要将用户手机号插入的数据库
                sysUserService.insertPhone(phone);
            }
            //将用户登录信息放置到cookie中判断用户登录状态
            setCookieAndCache(phone, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //去下一页
        return "index";

    }


    /**
     * 用户退出功能
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request,
                         @RequestParam(value = "phone", required = true) String phone) throws Exception {
        clearCookieAndCache(request, response,phone);
        return "index";
    }

    /**
     * 保存用户信息到cookie中
     *
     * @param request
     * @param response
     * @throws Exception
     */
    private void setCookieAndCache(String phone,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        //清空redis中该手机号的失败次数
        redisSession.delKey(phone + RedisNameUtil.separativeSignCount);
        //删除保存的短信验证码
        //清空redis中该手机号的失败次数
        redisSession.delKey(phone);
        // 设置登录会员的cookie信息
        StringBuilder sb = new StringBuilder();
        sb.append(phone)
                .append(RedisNameUtil.separativeSign);
        //用户信息加密
        String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
        cookieUtils.setCookie(request, response,
                CookieUtils.COOKIE_NAME_User_LOGIN, str);
        // 将登录用户放入缓存（此处缓存的数据及数据结构值得推敲，暂时先全部缓存起来）
        redisSession.set2(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                        + phone,
                phone, RedisObjectType.SYS_USER_MANAGER.getExpiredTime());
    }

    /**
     * 清除缓存数据
     *
     * @param request
     * @param response
     * @throws Exception
     */
    private void clearCookieAndCache(HttpServletRequest request, HttpServletResponse response,String phone) throws Exception {
        //从cookie中删除用户数据
        CookieUtils.deleteCookie(request, response,CookieUtils.COOKIE_NAME_User_LOGIN);
        //删除redis中的用户数据
        redisSession.delKey(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                + phone);
    }

}
