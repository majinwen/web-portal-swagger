package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.service.repository.admin.SysBuildIndexMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysBuildIndexMappingController {

    @Autowired
    private SysBuildIndexMappingService sysBuildIndexMappingService;

    @RequestMapping("/BuildIndexMapping")
    public void BuildIndexMapping(String index,String type){
        try {
            sysBuildIndexMappingService.buildIndexMapping(index,type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
