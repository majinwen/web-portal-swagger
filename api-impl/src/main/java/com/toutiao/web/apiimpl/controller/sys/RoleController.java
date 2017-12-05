package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.api.chance.request.sys.RoleRequest;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.admin.SysRoleEntity;
import com.toutiao.web.service.repository.admin.SysRoleService;
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
@RequestMapping(value = "/admin/roles")
public class RoleController {

    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping(value = "/rolelistbyName")
    @ResponseBody
   public NashResult rolelistnyName(String name){
        SysRoleEntity role=new SysRoleEntity();
        List<SysRoleEntity> projInfoList = sysRoleService.selectByName(name);
        return NashResult.build(projInfoList);

    }


    @RequestMapping(value = "/rolelistbyCode")
    @ResponseBody
    public NashResult rolelistnyCode(String code){
        SysRoleEntity roleEntity = sysRoleService.selectByCode(code);
        return NashResult.build(roleEntity);

    }


    @RequestMapping(value = "/roleadd")
    @ResponseBody
    public NashResult roleinsert(@Validated({First.class,Second.class})RoleRequest record){
        SysRoleEntity role=new SysRoleEntity();
        BeanUtils.copyProperties(record,role);
        int result = sysRoleService.insert(role);
        return NashResult.build(result);

    }

    @RequestMapping(value = "/roleupdate")
    @ResponseBody
    public NashResult roleupdate( @Validated({First.class})RoleRequest record){
        SysRoleEntity role=new SysRoleEntity();
        BeanUtils.copyProperties(record,role);
        int result=sysRoleService.updateByPrimaryKeySelective(role);
        return  NashResult.build(result);
    }




    @RequestMapping(value = "/roledelete")
    @ResponseBody
    public NashResult roledelete(String code){
             int result=sysRoleService.deleteByPrimaryKey(code);
             return NashResult.build(result);
    }

    @RequestMapping(value = "/loginRoleList")
    @ResponseBody
    public NashResult loginRoleList(int id){
              List<SysRoleEntity> roleEntityList=sysRoleService.loginRoleList(id);
              return NashResult.build(roleEntityList);
    }


}
