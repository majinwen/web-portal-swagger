package com.toutiao.web.apiimpl.controller.auth;


import com.alibaba.fastjson.JSON;
import com.toutiao.web.apiimpl.authentication.IgnoreLogin;
import com.toutiao.web.apiimpl.authentication.Permission;
import com.toutiao.web.apiimpl.authentication.SerializableData;
import com.toutiao.web.apiimpl.authentication.User;
import com.toutiao.web.common.authentication.menu;
import com.toutiao.web.apiimpl.conf.interceptor.LoginAndPermissionConfig;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.NashBeanUtils;
import com.toutiao.web.common.util.crypto.AES;
import com.toutiao.web.dao.entity.admin.SysMenuEntity;
import com.toutiao.web.dao.entity.admin.SysRoleEntity;
import com.toutiao.web.service.repository.admin.SysMenuService;
import com.toutiao.web.service.repository.admin.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @IgnoreLogin
    @Permission(ignore = true)
    public NashResult login(@RequestParam(value = "email",required = false)String email, @RequestParam(value = "password",required = false)String password, HttpServletResponse response){

//        if(StringUtils.isBlank(email) || StringUtils.isBlank(password)){
//            throw new NashRequestException(CommonResultCode.COMM_ARG_ILLEGAL.getInfoCode(),"登录信息不能为空");
//        }

//        SysUser sysUser = sysUserRepository.selectByEmail(email.trim());
//        if(sysUser==null){
//            return NashResult.Fail("fail","请先开通此账号");
//        }
//        if(sysUser.getEnableLogin()==1){
//            return NashResult.Fail("fail","账号已被冻结");
//        }
//        SysUserApiResponse sysUserApiResponse = checkEmailPassword(email, password);
//        if(sysUserApiResponse==null){
//            return NashResult.Fail("fail","用户名/密码,错误");
//        }
        SerializableData d=new SerializableData();
        d.setUserId(1);
        d.setUserName("系统管理员");
        d.setVersion(SerializableData.GLOBAL_VERSION);
        List<SysRoleEntity> sysRoleEntities = sysRoleService.loginRoleList(d.getUserId());
        SysRoleEntity currentRole=null;
        for(SysRoleEntity item : sysRoleEntities){
            currentRole = item;
            break;
        }
        d.setCurrentRole(currentRole);
        List<SysMenuEntity> sysMenuEntities = sysMenuService.selectByRoleID(currentRole.getId());
        List<menu> menus=new ArrayList<>();
        for(SysMenuEntity item : sysMenuEntities){
            menu m=new menu();
            NashBeanUtils.copyProperties(m,item);
            menus.add(m);
        }
        d.setMenus(menus);




//        /**
//         * 查数据权限
//         */
//        List<UserRoleDatapermission> userRoleDatapermissions = sysUserRepository.selectRoleAndDatapermissionByUserNo(d.getUserNo());
//        if(userRoleDatapermissions.size()==0){
//            return NashResult.Fail("fail","未分配权限,请联系管理员");
//        }
//        Integer datapermissionId = userRoleDatapermissions.get(0).getDatapermissionId();
//        /**
//         * 查功能权限
//         */
//        List<UserRolePermission> userRolePermissions = sysUserRepository.selectUserRolePermission(datapermissionId);
//        List<Integer> permissionCodes=new ArrayList<>();
//        for (UserRolePermission permission:userRolePermissions) {
//            permissionCodes.add(permission.getPermissionId());
//        }
//
//        d.setCurrentRoleDatapermission(userRoleDatapermissions.get(0));
//        d.setPermissionCodes(permissionCodes);
//        d.setVersion(SerializableData.GLOBAL_VERSION);
        String cookievalue = AES.encrypt2(d.toJson(), "apollocrm1234567");
        Cookie cookie = new Cookie(loginAndPermissionConfig.getCookiename(),cookievalue);
        cookie.setPath("/");
        response.addCookie(cookie);
//        redisSession.login(sysUser.getUserNo());
        return NashResult.build(0);
    }

    @RequestMapping(value = {"/logout"}, method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @Permission(ignore = true)
    public void logout(HttpServletResponse response) throws Exception{
//        Integer userId = User.getCurrent().getUserId();
        response.setContentType("application/json;charset=utf-8");
        Cookie clearcookie = new Cookie(loginAndPermissionConfig.getCookiename(),null);
        clearcookie.setMaxAge(0);
        clearcookie.setPath("/");
        response.addCookie(clearcookie);

        PrintWriter writer = response.getWriter();
        writer.print(JSON.toJSONString(NashResult.Fail("success","")));
        writer.flush();
//        redisSession.logout(userId);

    }
}
