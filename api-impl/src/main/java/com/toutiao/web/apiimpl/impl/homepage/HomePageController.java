package com.toutiao.web.apiimpl.impl.homepage;


import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.service.newhouse.NewHouseService;
import com.toutiao.web.service.plot.SysVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomePageController {


    @Autowired
    private NewHouseService newHouseService;
    @Autowired
    private SysVillageService sysVillageService;


    /**
     * 大首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model, VillageRequest villageRequest){
        NewHouseQuery newHouseQuery=new NewHouseQuery();
        villageRequest.setAreaSize("80");
        newHouseQuery.setSort(0);
        newHouseQuery.setPageNum(1);
        newHouseQuery.setPageSize(4);
        Map<String,Object> builds = newHouseService.getNewHouse(newHouseQuery);
        List villageList = sysVillageService.findVillageByConditions(villageRequest);
        model.addAttribute("villageList", villageList);
        model.addAttribute("newbuilds",builds);
        model.addAttribute("user","asds");
        return "index";
    }
}
