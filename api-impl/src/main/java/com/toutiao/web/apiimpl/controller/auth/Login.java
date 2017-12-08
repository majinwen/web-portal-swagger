package com.toutiao.web.apiimpl.controller.auth;


import com.toutiao.web.apiimpl.authentication.IgnoreLogin;
import com.toutiao.web.apiimpl.authentication.Permission;
import com.toutiao.web.apiimpl.conf.interceptor.LoginAndPermissionConfig;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.*;
import com.toutiao.web.service.repository.admin.SysMenuService;
import com.toutiao.web.service.repository.admin.SysRoleService;
import com.toutiao.web.service.repository.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        int count = Integer.parseInt(RedisUtils.getValue(phone + "_@@@@count"));
        //获取缓存中的手机号码判断是否有效
        if (RedisUtils.exists(phone)) {
            //判断相关信息数据是否一致
            if (RedisUtils.getValue(phone).equalsIgnoreCase(MD5Util.computeUTF(MD5Util.computeUTF(code)))) {
                //从cookie中获取图片验证码与页面传递过来的验证码进行对比
                if (count > 3) {
                    if (StringTool.isBlank(imageCode)) {
                        return NashResult.Fail("fail", "请输入图片验证码", count);
                    }
                    //从cookie中获取图片验证码
                   String value= CookieUtils.getCookie(request,response,"imageCode");
                    if (!value.equalsIgnoreCase(code)) {
                        return NashResult.Fail("fail", "图片验证码有误！", count);
                    }
                }
                //查询该电话号是否存在
                int i = sysUserService.selectByPhone(phone);
                if (i == 0) {
                    //首次登陆
                    //登陆成功后需要将用户手机号插入的数据库
                    sysUserService.insertPhone(phone);
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
    public NashResult logout(HttpServletResponse response,HttpServletRequest request,
                             @RequestParam(value="phone",required = true) String phone) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        //清空cookie中的数据
        CookieUtils.deleteCookie(request,response,CookieUtils.imageCodeName);
        //清空redis中的数据
        RedisUtils.delKey(phone);
        RedisUtils.delKey(phone+"_@@@@count");
        return NashResult.build(0);
    }



}
