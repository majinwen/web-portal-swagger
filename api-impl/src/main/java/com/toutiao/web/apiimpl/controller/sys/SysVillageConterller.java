package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.service.repository.admin.SysVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SysVillageConterller {
    @Autowired
    private SysVillageService sysVillageService;

    //(查询附近小区和(距离))
    @RequestMapping("/fingNearVillageAndDistance")
    @ResponseBody
    public String GetNearByhHouseAndDistance(double lat, double lon, Model model) {
        List villageList = null;
        try {
            villageList = sysVillageService.GetNearByhHouseAndDistance(lat, lon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("villageList", villageList);
        return "plot-list";
    }

    //根据条件查询小区
    @RequestMapping("/findVillageByConditions")
    public String findVillageByConditions(VillageRequest villageRequest, Model model) {
        VillageRequest villageRequest1 = new VillageRequest();
//        villageRequest1.setId(001);
//        villageRequest1.setSearchSubwayLineId("001");
//        villageRequest1.setSearchMetroStationId("001");
//        String[] a = {"0","80"};
//        villageRequest1.setSearchAreaSize(a);
//        Integer[] ap ={0,70000,70000,80000};
//        villageRequest1.setSearchAvgPrice(ap);
//        villageRequest1.setId(1);
//        villageRequest1.setAreaId("003");
//        villageRequest1.setAreaNameId("002");
//        villageRequest1.setId(3);
//        Integer[] pr = {50000,80000};
//        villageRequest1.setSearchAvgPrice(pr);
//        Integer[] ag = {16,16};
//        villageRequest1.setSearchAge(ag);
//        villageRequest1.setId(1);
//        villageRequest1.setAreaId("001");
        List villageList = null;
        try {
            villageList = sysVillageService.findVillageByConditions(villageRequest1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("villageList", villageList);
        return "plot/plot-list";
    }
}
