package com.toutiao.web.apiimpl.conf.interceptor;

import com.alibaba.fastjson.JSON;
import com.toutiao.web.apiimpl.authentication.IgnoreLogin;
import com.toutiao.web.apiimpl.authentication.RedisSession;
import com.toutiao.web.apiimpl.authentication.SerializableData;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.Com35Aes;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.RedisNameUtil;
import com.toutiao.web.common.util.crypto.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * zhangjinglei 2017/8/31 上午10:50
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisSession redisSession;

    @Autowired
    private LoginAndPermissionConfig config;
    @Autowired
    private LoginAndPermissionConfig loginAndPermissionConfig;

    private HashMap<String, String> codes = new HashMap<>();

    public LoginInterceptor() {
//        List<SysPermissionInfo> sysPermissionInfos = sysPermissionCoreService.selectAll();
//        for(SysPermissionInfo info:sysPermissionInfos){
//            codes.put(info.getId().toString(),info.getName());
//        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 本请求忽略登录验证
         */
        IgnoreLogin methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreLogin.class);
        if (methodAnnotation != null) {
            return true;
        }
        /**
         * 验证cookie
         */
        /*SerializableData serializableData = validCookieValue(request, config.getCookiename());*/
        //获取解密后的cookie中的用户数据

//        String validCookieValue1 = validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);
//        if (validCookieValue1!=null){
//            request.setAttribute("userphone", validCookieValue1);
//        }else{
//            request.setAttribute("userphone", "请登录");
//        }

        String validCookieValue = validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
        if (validCookieValue!=null){
            request.setAttribute("userLogin", validCookieValue);
        }else{
            request.setAttribute("userLogin", "请登录");
        }
        /*if (serializableData == null) {
            response.setContentType("application/json;charset=utf-8");
            Cookie clearcookie = new Cookie(config.getCookiename(), null);
            clearcookie.setMaxAge(0);
            clearcookie.setPath("/");
            response.addCookie(clearcookie);

            PrintWriter writer = response.getWriter();
            writer.print(JSON.toJSONString(NashResult.Fail("no-login", "没有登录")));
            writer.flush();
            return false;
        }
        request.setAttribute("_serializabledata_", serializableData);*/
        return true;
//        else {
//            /**
//             *
//             */
//            if(!redisSession.hasLogin(serializableData.getUserNo())){
//                response.setContentType("application/json;charset=utf-8");
//                PrintWriter writer = response.getWriter();
//                writer.print(JSON.toJSONString(NashResult.Fail("no-login","没有登录")));
//                writer.flush();
//                return false;
//            }
//            request.setAttribute("_serializabledata_", serializableData);
//
//        }
        //todo 暂时注释

//        Permission permissionAnnotation = ((HandlerMethod) handler).getMethodAnnotation(Permission.class);
//        if(permissionAnnotation==null){
//            throw new BaseException("no-permission","没有开放此功能，请联系管理员。");
//        }
//        else {
//            if(!permissionAnnotation.ignore()){
//                SerializableData serializabledata_ = (SerializableData) request.getAttribute("_serializabledata_");
//                if(serializabledata_==null){
//                    throw new BaseException("no-permission","请选择角色");
//                }
//                String tip="";
//                for(String annotationCode :permissionAnnotation.value()){
//                    if(serializabledata_.getPermissionCodes().contains(Integer.parseInt(annotationCode))){
//                        return true;
//                    }
//                    String permissionname = codes.get(annotationCode.trim());
//                    if(StringUtils.isNotBlank(permissionname)){
//                        tip += permissionname+"，";
//                    }
//                }
//                if(StringUtils.isNotBlank(tip)){
//                    tip="你缺少权限："+tip;
//                }
//                else {
//                    tip="你没有权限使用此功能";
//                }
//                throw new BaseException("no-permission",tip);
//            }
//            else {
//                return true;
//            }
//
//        }

//        return true;
    }

    private String validCookieValue1(HttpServletRequest request, String cookieName) {
        cookieName = cookieName.trim();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {

                if (c.getName().equals(cookieName)) {
                    try {
                        //用户信息解密
                        String str = Com35Aes.decrypt(Com35Aes.KEYCODE, c.getValue());
                        if (str != null) {
                            //截取电话号码
                            String[] strings = str.split(RedisNameUtil.separativeSign);
                            return strings[0];
                        }else{
                            return null;
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }


    private SerializableData validCookieValue(HttpServletRequest request, String cookieName) {
        cookieName = cookieName.trim();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(cookieName)) {
                    try {
                        String cookievalue = AES.decrypt2(c.getValue(), "apollocrm1234567");
                        SerializableData serializableData = SerializableData.fromString(cookievalue);
                        if (!serializableData.checkVersion()) {
                            return null;
                        }

                        return serializableData;
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
