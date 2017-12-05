package com.toutiao.web.apiimpl.impl;

import com.github.pagehelper.Page;
import com.toutiao.web.common.restmodel.NashResult;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("test")
public class testController {


    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/test")
    public String findLeaseChanceListByRange(HttpServletRequest request
            ) {
//        SysUserEntity sysUserEntity = new SysUserEntity();
//        sysUserEntity.setCreateId(0);
//        sysUserEntity.setCreateTime(DateTime.now().toDate());
//        sysUserEntity.setLoginName("");
//        sysUserEntity.setModifyId(0);
//        sysUserEntity.setModifyTime(DateTime.now().toDate());
//        sysUserEntity.setPassword("");
//        sysUserEntity.setUserName("");
//        sysUserEntityMapper.insertSelective(sysUserEntity);
//        System.out.println(sysUserEntity.getId());
        return "ListpageDemo";
    }
    @RequestMapping("/test1")
    @ResponseBody
    public NashResult findLeaseChanceListByRange1(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "1") int pageSize
    ) {
//        SysUserEntity sysUserEntity = new SysUserEntity();
//        sysUserEntity.setCreateId(0);
//        sysUserEntity.setCreateTime(DateTime.now().toDate());
//        sysUserEntity.setLoginName("");
//        sysUserEntity.setModifyId(0);
//        sysUserEntity.setModifyTime(DateTime.now().toDate());
//        sysUserEntity.setPassword("");
//        sysUserEntity.setUserName("");
//        Page<SysUserEntity> res=new Page<>();
//        res.setPageNum(pageNum);
//        res.setPageSize(pageSize);
//        res.setTotal(32);
//        res.add(sysUserEntity);
        return NashResult.build(0);//"demo1";
    }
}
