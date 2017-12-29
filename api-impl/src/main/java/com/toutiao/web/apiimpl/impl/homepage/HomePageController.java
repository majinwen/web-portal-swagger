package com.toutiao.web.apiimpl.impl.homepage;


import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.service.newhouse.NewHouseService;
import com.toutiao.web.service.plot.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomePageController {


    @Autowired
    private NewHouseService newHouseService;
    @Autowired
    private PlotService plotService;

    @RequestMapping(value={""})
    public String index(){
        return "redirect:/bj";
    }
    /**
     * 大首页
     * @param model
     * @return
     */
    @RequestMapping(value={"{citypath}"})
    public String index(@PathVariable("citypath")String citypath, Model model, VillageRequest villageRequest){
        NewHouseQuery newHouseQuery=new NewHouseQuery();
        newHouseQuery.setSort(0);
        newHouseQuery.setPageNum(1);
        newHouseQuery.setPageSize(4);
        Map<String,Object> builds = newHouseService.getNewHouse(newHouseQuery);
        List villageList = plotService.findVillageByConditions(villageRequest);
        model.addAttribute("villageList", villageList);
        model.addAttribute("newbuilds",builds);
        model.addAttribute("user","asds");
//        model.addAttribute("searchType","projhouse");
        return "index";
    }
}
