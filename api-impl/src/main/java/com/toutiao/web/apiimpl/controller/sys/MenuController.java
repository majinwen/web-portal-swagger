package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.api.chance.request.sys.MenuRequest;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.admin.SysMenuEntity;
import com.toutiao.web.service.repository.admin.SysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 18710 on 2017/11/21.
 */
@RestController
@RequestMapping(value = "/admin/menus")
public class MenuController {
    @Autowired
    SysMenuService menuService;

    @RequestMapping(value = "/menuSelectByStatus")
    @ResponseBody
    public NashResult menuSelectByKey(@RequestParam(value = "status",required = false) Integer status) {
        List<SysMenuEntity> menuEntities=menuService.selectByStatus(status);
        return NashResult.build(menuEntities);
    }

    @RequestMapping(value = "/menuAddSelective")
    @ResponseBody
    public NashResult menuSelectByKey(@Validated({First.class,Second.class})MenuRequest record) {
       SysMenuEntity menuEntity=new SysMenuEntity();
        BeanUtils.copyProperties(record,menuEntity);
        int result=menuService.insertSelective(menuEntity);
        return NashResult.build(result);
    }

    @RequestMapping(value = "/menuUpdate")
    @ResponseBody
    public NashResult menuUpdate(@Validated({First.class})MenuRequest record){
        SysMenuEntity menuEntity=new SysMenuEntity();
        BeanUtils.copyProperties(record,menuEntity);
        int result=menuService.updateByPrimaryKeySelective(menuEntity);
        return NashResult.build(result);
    }

    @RequestMapping(value = "/menuDelete")
    @ResponseBody
    public NashResult menuDelete(String code){
        int result=menuService.deleteByPrimaryKey(code);
        return  NashResult.build(result);
    }

    @RequestMapping(value = "/menuListByPK")
    @ResponseBody
    public NashResult menuListByPK(String code){
        SysMenuEntity menuEntity=menuService.selectByPrimaryKey(code);
        return NashResult.build(menuEntity);
    }

}
