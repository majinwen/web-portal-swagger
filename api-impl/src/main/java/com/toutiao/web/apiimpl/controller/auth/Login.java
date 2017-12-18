package com.toutiao.web.apiimpl.controller.auth;


import com.toutiao.web.apiimpl.authentication.IgnoreLogin;
import com.toutiao.web.apiimpl.authentication.Permission;
import com.toutiao.web.apiimpl.conf.interceptor.LoginAndPermissionConfig;
import com.toutiao.web.common.util.*;
import com.toutiao.web.dao.entity.admin.SysUserEntity;
import com.toutiao.web.service.rediscache.RedisAndCookieService;
import com.toutiao.web.service.repository.admin.SysMenuService;
import com.toutiao.web.service.repository.admin.SysRoleService;
import com.toutiao.web.service.repository.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @Autowired
    private RedisAndCookieService redisAndCookieService;
    private CookieUtils cookieUtils;

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    @ResponseBody
    @IgnoreLogin
    @Permission(ignore = true)
    public String login(@RequestParam(value = "phone") String phone,
                        @RequestParam(value = "code") String code,
                        HttpServletResponse response, HttpServletRequest request,
                        @RequestParam(value = "imageCode", required = false) String imageCode, ModelMap modelMap) {

        try {
            //获取当前用户电话号码在缓存中的次数
            int count = StringTool.getInteger(RedisUtils.getValue(phone + RedisNameUtil.separativeSignCount));
            //判断页面传递过来的电话号码与短信验证码是否为空
            if (StringTool.isBlank(phone) || StringTool.isBlank(code)) {
                modelMap.addAttribute("count", count);
                modelMap.addAttribute("phone", phone);
                modelMap.addAttribute("message", "短信验证码输入有误！");
                return "login";
            }
            //判断传输过来的电话号码是否全是数字并且是否是手机号码
            if (!StringUtil.isCellphoneNo(phone)) {
                modelMap.addAttribute("count", count);
                modelMap.addAttribute("phone", phone);
                modelMap.addAttribute("message", "手机号输入有误！");
                return "login";
            }
            //获取缓存中的手机号码判断是否有效
            boolean flag = RedisUtils.exists(phone);
            if (!flag) {
                modelMap.addAttribute("phone", phone);
                modelMap.addAttribute("count", count);
                modelMap.addAttribute("message", "验证码失效！");
                return "login";
            }
            if (!RedisUtils.getValue(phone).equalsIgnoreCase(MD5Util.computeUTF(MD5Util.computeUTF(code)))) {
                modelMap.addAttribute("phone", phone);
                modelMap.addAttribute("count", count);
                modelMap.addAttribute("message", "验证码输入有误！");
                return "login";
            }
            //从cookie中获取图片验证码与页面传递过来的验证码进行对比
            if (count > Constant.LOGIN_ERROR_TIMES) {
                if (StringUtils.isEmpty(imageCode) || StringTool.isEmpty(CookieUtils.getCookie(request, response,
                        RedisObjectType.USER_IMAGE_VALIDATECODE.getPrefix())) || CookieUtils.getCookie(request, response,
                        RedisObjectType.USER_IMAGE_VALIDATECODE.getPrefix()).equalsIgnoreCase(imageCode)) {
                    modelMap.addAttribute("count", count);
                    modelMap.addAttribute("phone", phone);
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
        return "redirect:/";

    }
    /**
     * 用户退出功能
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = {"/logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    @Permission(ignore = true)
    public String logout(HttpServletResponse response, HttpServletRequest request,
                         @RequestParam(value = "phone", required = true) String phone) throws Exception {
        clearCookieAndCache(request, response);
        return "redirect:/login";
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
        RedisUtils.delKey(phone + RedisNameUtil.separativeSignCount);
        // 设置登录会员的cookie信息
        StringBuilder sb = new StringBuilder();
        sb.append(phone)
                .append(RedisNameUtil.separativeSign);
        //用户信息加密
        String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
        cookieUtils.setCookie(request, response,
                CookieUtils.COOKIE_NAME_User_LOGIN, str);
        // 将登录用户放入缓存（此处缓存的数据及数据结构值得推敲，暂时先全部缓存起来）
        RedisUtils.set2(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                        + phone,
                phone,RedisObjectType.SYS_USER_MANAGER.getExpiredTime());
    }

    /**
     * 清除缓存数据
     *
     * @param request
     * @param response
     * @throws Exception
     */
    private void clearCookieAndCache(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cookie[] =redisAndCookieService
                .getAdminCookie(request,response);
        if (cookie != null && cookie.length > 1) {
            String phone = cookie[0];
            RedisUtils.delKey(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                    + phone);
            redisAndCookieService.deleteAdminStatus(request, response);
        }
    }

}
