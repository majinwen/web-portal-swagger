package com.toutiao.web.apiimpl.impl.admin;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.admin.SysRoleEntity;
import com.toutiao.web.service.repository.admin.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserRolePermissionController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/rolespage")
    public String rolespage(HttpServletRequest request
    ) {
        return "admin/roles";
    }

    @RequestMapping("/roles")
    @ResponseBody
    public NashResult roles(@RequestParam(value = "name",required = false) String name, HttpServletRequest request) {
        List<SysRoleEntity> sysRoleEntities = sysRoleService.selectByName(name);
        return NashResult.build(sysRoleEntities);
    }

    @RequestMapping(value = {"/roles/detail"})
    public String rolesdetail(@RequestParam("code")String code, Model model, HttpServletRequest request) {
        if(StringUtils.isNoneBlank(code)) {
            SysRoleEntity sysRoleEntity = sysRoleService.selectByCode(code);
            model.addAttribute("name", sysRoleEntity.getName());
            model.addAttribute("code", sysRoleEntity.getCode());
            model.addAttribute("operation","modify");
        }
        else {
            model.addAttribute("operation","create");
        }
        return "admin/roles_detail";
    }

    @RequestMapping("/roles/save")
    @ResponseBody
    public NashResult rolessave( HttpServletRequest request) {
//        SysRoleEntity sysRoleEntity = sysRoleService.selectByCode(code);
//        model.addAttribute("name",sysRoleEntity.getName());
//        model.addAttribute("code",sysRoleEntity.getCode());
        return NashResult.build(0);
    }
}
