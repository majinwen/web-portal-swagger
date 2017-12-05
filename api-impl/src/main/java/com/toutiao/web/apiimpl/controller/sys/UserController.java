package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.api.chance.request.sys.UserRequest;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.admin.SysUserEntity;
import com.toutiao.web.service.repository.admin.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 18710 on 2017/11/21.
 */
@RestController
@RequestMapping(value = "/admin/users")
public class UserController {
    @Autowired
    SysUserService userService;


    @RequestMapping(value = "/userListByKey")
    @ResponseBody
    public NashResult userListByKey(SysUserEntity entity){
        List<SysUserEntity>userList=userService.selectByKey(entity);
        return NashResult.build(userList);
    }

    @RequestMapping(value = "/userListByPK")
    @ResponseBody
    public NashResult userListByPK(int id){
       SysUserEntity user=userService.selectByPrimaryKey(id);
        return NashResult.build(user);
    }

    @RequestMapping(value = "/userDeleteByPK")
    @ResponseBody
    public NashResult  userDeleteByPK(int id){
       int result=userService.deleteByPrimaryKey(id);
       return NashResult.build(result);
    }

    @RequestMapping(value = "/userAdd")
    @ResponseBody
    public NashResult userAdd(@Validated({First.class,Second.class})UserRequest record){
          SysUserEntity userEntity=new SysUserEntity();
        BeanUtils.copyProperties(record,userEntity);
        int result=userService.insertSelective(userEntity);
       return NashResult.build(result);
    }

    @RequestMapping(value = "/userUpdate")
    @ResponseBody
    public NashResult  userUpdate(@Validated({First.class})UserRequest record){
        SysUserEntity userEntity=new SysUserEntity();
        BeanUtils.copyProperties(record,userEntity);
        int result=userService.updateByPrimaryKeySelective(userEntity);
       return NashResult.build(result);
    }

}
