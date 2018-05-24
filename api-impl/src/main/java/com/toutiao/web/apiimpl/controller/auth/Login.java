package com.toutiao.web.apiimpl.controller.auth;


import com.alibaba.fastjson.JSON;
import com.toutiao.app.api.chance.response.user.UserLoginResponse;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.sys.IMService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.apiimpl.authentication.RedisSession;
import com.toutiao.web.common.util.*;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.officeweb.user.UserBasicMapper;
import com.toutiao.web.service.rediscache.RedisAndCookieService;
import com.toutiao.web.service.repository.admin.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

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

    @Autowired
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private IMService imService;
    @Value("${qiniu.headpic_directory}")
    public String headPicDirectory;
    @Value("${qiniu.img_wapapp_domain}")
    public String headPicPath;
    @Autowired
    private UserBasicMapper userBasicMapper;

    /**
     * 功能描述：去登陆页面
     *
     * @param []
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/20 10:54
     */
    @RequestMapping("/login")
    public String goLoginPage(Model model,@RequestParam(value = "backUrl",required = false) String backUrl,
                                          @RequestParam(value = "title",required = false) String title,
                              HttpServletRequest request) {
        if(StringTool.isNotBlank(backUrl)){
            model.addAttribute("backUrl", backUrl);
        }
        if(StringTool.isNotBlank(title)){
            model.addAttribute("title", title);
        }
        return "login";
    }

    @RequestMapping(value = {"/tologin"}, method = {RequestMethod.POST})
    public String login(@RequestParam(value = "phone") String phone,
                        @RequestParam(value = "backUrl",required = false) String backUrl,
                        @RequestParam(value = "title",required = false) String title,
                        @RequestParam(value = "code") String code,
                        HttpServletResponse response, HttpServletRequest request,
                        @RequestParam(value = "imageCode", required = false) String imageCode, ModelMap modelMap) {
        try {
            modelMap.addAttribute("phone", phone);
            modelMap.addAttribute("count", StringTool.getInteger(redisSession.getValue(phone + RedisNameUtil.separativeSignCount)));
            if(StringTool.isNotBlank(backUrl)){
                modelMap.addAttribute("backUrl", backUrl);
            }
            if(StringTool.isNotBlank(title)){
                modelMap.addAttribute("title", title);
            }
            //判断页面传递过来的电话号码与
            if (StringTool.isBlank(phone)) {
                modelMap.addAttribute("message", "请输入手机号码！");
                return "login";
            }
            //短信验证码是否为空
            if (StringTool.isBlank(code)) {
                modelMap.addAttribute("message", "请输入短信验证码！");
                return "login";
            }
            //判断传输过来的电话号码是否全是数字并且是否是手机号码
            if (!StringUtil.isCellphoneNo(phone)) {
                modelMap.addAttribute("message", "输入的手机号格式有误！");
                return "login";
            }
            //获取缓存中的手机号码判断是否有效
            boolean flag = redisSession.exists(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER + "_" + phone);
            if (!flag) {
                modelMap.addAttribute("message", "短信验证码失效！");
                return "login";
            }
            if (!redisSession.getValue(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER + "_" + phone).equalsIgnoreCase(code)) {
                modelMap.addAttribute("message", "短信验证码输入有误！");
                return "login";
            }
            //从cookie中获取图片验证码与页面传递过来的验证码进行对比
            if (StringTool.getInteger(redisSession.getValue(phone + RedisNameUtil.separativeSignCount)) > Constant.LOGIN_ERROR_TIMES) {

                if (StringTool.isNotBlank(imageCode) && StringTool.isNotBlank(CookieUtils.getCookie(request, response,
                        "imageCode")) && !CookieUtils.getCookie(request, response,
                        "imageCode").equalsIgnoreCase(imageCode)) {
                    modelMap.addAttribute("imageCode", imageCode);
                    modelMap.addAttribute("message", "图片验证码有误！");
                    return "login";
                }

            }
            //查询该电话号是否存在
//            SysUserEntity sysUser = sysUserService.selectByPhone(phone);
//            if (StringTool.isBlank(sysUser)) {
//                //首次登陆
//                //登陆成功后需要将用户手机号插入的数据库
//                sysUserService.insertPhone(phone);
//            }

            UserBasicDo userBasicDo =userBasicInfoService.queryUserBasicByPhone(phone);
            UserBasic insertUserBasic = new UserBasic();
            if(StringTool.isEmpty(userBasicDo)){
                //注册新用户

                Date date = new Date();
                insertUserBasic.setUserName(phone.substring(0,2)+"*****"+phone.substring(9,11));

                String[] userAvatar = ServiceStateConstant.SYS_USER_AVATAR;
                int avatarNum = new Random().nextInt(ServiceStateConstant.RANDOM_AVATAR);
                insertUserBasic.setAvatar(headPicDirectory+"/"+userAvatar[avatarNum]);
                insertUserBasic.setCreateTime(date);
                insertUserBasic.setLoginTime(date);
                insertUserBasic.setPhone(phone);
                insertUserBasic.setUpdateTime(date);
                insertUserBasic.setUserStatus(ServiceStateConstant.USER_BASIC_STATUS);
                insertUserBasic.setRegisterSource(ServiceStateConstant.USER_REGISTER_SOURCE_WAP);
                insertUserBasic.setIdentityType(ServiceStateConstant.USER_REGISTER_IDENTITY_PHONE);
                insertUserBasic.setIdentifier(phone);
                Date d = new Date();
                insertUserBasic.setUserOnlySign(d.getTime()+insertUserBasic.getPhone());
                //用户注册融云信息
                String rcToken = imService.queryRongCloudTokenByUser(insertUserBasic.getUserOnlySign(), insertUserBasic.getPhone(), headPicPath+"/"+headPicDirectory+"/"+insertUserBasic.getAvatar());
                insertUserBasic.setRongCloudToken(rcToken);

                userBasicInfoService.addUserBasic(insertUserBasic);
            }else {
//                UserBasic user = new UserBasic();
                BeanUtils.copyProperties(userBasicDo,insertUserBasic);
                insertUserBasic.setUserId(userBasicDo.getUserId());
                insertUserBasic.setLoginTime(new Date());
                if(userBasicDo.getRongCloudToken()==null || "".equals(userBasicDo.getRongCloudToken())){
                    Date date = new Date();
                    insertUserBasic.setUserOnlySign(date.getTime()+userBasicDo.getPhone());
                    if(userBasicDo.getAvatar()==null || "".equals(userBasicDo.getAvatar())){
                        int avatarNum = new Random().nextInt(ServiceStateConstant.RANDOM_AVATAR);
                        String[] userAvatar = ServiceStateConstant.SYS_USER_AVATAR;
                        insertUserBasic.setAvatar(headPicDirectory+"/"+userAvatar[avatarNum]);
                    }
                    String rcToken = imService.queryRongCloudTokenByUser(insertUserBasic.getUserOnlySign(), userBasicDo.getPhone(),
                            headPicPath+"/"+headPicDirectory+"/"+userBasicDo.getAvatar());
                    insertUserBasic.setRongCloudToken(rcToken);
                    userBasicMapper.updateByPrimaryKeySelective(insertUserBasic);
                }

            }
            //将用户登录信息放置到cookie中判断用户登录状态

            String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
            if(StringTool.isEmpty(user)){
                UserLoginResponse userLoginResponse = new UserLoginResponse();
                BeanUtils.copyProperties(insertUserBasic,userLoginResponse);
                setCookieAndCache(insertUserBasic.getPhone(),userLoginResponse, request, response);
            }
            if(StringTool.isNotBlank(backUrl)&&StringTool.isNotBlank(title)){
                return "redirect:"+backUrl+"?title="+title;
            }
            if(StringTool.isNotBlank(backUrl)){
                return "redirect:"+backUrl;
            }
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
    @RequestMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request,
                         @RequestParam(value = "uid", required = true) String uid) throws Exception {
        UserBasic userBasic = userBasicMapper.selectByPrimaryKey(uid);
        clearCookieAndCache(request, response, userBasic.getPhone());
        return "redirect:/";
    }

    /**
     * 保存用户信息到cookie中
     *
     * @param request
     * @param response
     * @throws Exception
     */
//    private void setCookieAndCache(String phone,
//                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
//        //清空redis中该手机号的失败次数
//        redisSession.delKey(phone + RedisNameUtil.separativeSignCount);
//        //删除保存的短信验证码
//        redisSession.delKey(phone);
//        // 设置登录会员的cookie信息
//        StringBuilder sb = new StringBuilder();
//        sb.append(phone)
//                .append(RedisNameUtil.separativeSign);
//        //用户信息加密
//        String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
//        cookieUtils.setCookie(request, response,
//                CookieUtils.COOKIE_NAME_User_LOGIN, str);
//        // 将登录用户放入缓存（此处缓存的数据及数据结构值得推敲，暂时先全部缓存起来）
//        redisSession.set2(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
//                        + phone,
//                phone, RedisObjectType.SYS_USER_MANAGER.getExpiredTime());
//    }

    private void setCookieAndCache(String phone,UserLoginResponse userLoginResponse,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        //清空redis中该手机号的失败次数
        redisSession.delKey(phone + RedisNameUtil.separativeSignCount);
        //删除保存的短信验证码
        redisSession.delKey(ServiceStateConstant.ALIYUN_SHORT_MESSAGE_LOGIN_REGISTER+"_"+phone);
        // 设置登录会员的cookie信息
        StringBuilder sb = new StringBuilder();
        String userJson = JSON.toJSONString(userLoginResponse);
        sb.append(userJson).append(RedisNameUtil.separativeSign);
        //用户信息加密
        String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
        cookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_USER, str);
        // 将登录用户放入缓存（此处缓存的数据及数据结构值得推敲，暂时先全部缓存起来）
        redisSession.set2(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                + phone, userJson, RedisObjectType.SYS_USER_MANAGER.getExpiredTime());
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
        CookieUtils.deleteCookie(request, response, CookieUtils.COOKIE_NAME_USER);
        //删除redis中的用户数据
        redisSession.delKey(RedisObjectType.SYS_USER_MANAGER.getPrefix() + Constant.SYS_FLAGS
                + phone);
    }

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("15601676403")
                .append(RedisNameUtil.separativeSign);
        //用户信息加密
        String str = Com35Aes.encrypt(Com35Aes.KEYCODE, sb.toString());
        System.out.println(str);

        //解密
        String decrypt = Com35Aes.decrypt(Com35Aes.KEYCODE, str);

        String[] split = decrypt.split(RedisNameUtil.separativeSign);

        System.out.println(split);


    }


}
