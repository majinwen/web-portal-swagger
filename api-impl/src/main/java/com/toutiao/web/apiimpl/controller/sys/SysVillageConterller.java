package com.toutiao.web.apiimpl.controller.sys;

import com.toutiao.web.domain.app.VillageRequest;
import com.toutiao.web.service.repository.admin.SysVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SysVillageConterller {
    @Autowired
    private SysVillageService sysVillageService;

    //小区列表首页展示(查询附近小区和(距离))
    @RequestMapping("/fingNearVillageAndDistance")
    public String GetNearByhHouseAndDistance(String index, String type, double lat, double lon, String distance, Model model) {
        List villageList = null;
        try {
            villageList = sysVillageService.GetNearByhHouseAndDistance(index, type, lat, lon, distance);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        model.addAttribute("villageList", villageList);
        return "plot-list";
    }

    //根据条件查询小区
    @RequestMapping("/findVillageByConditions")
    public String findVillageByConditions(String index, String type, VillageRequest villageRequest, Model model) {
        VillageRequest villageRequest1 = new VillageRequest();
//        villageRequest1.setId(1);
//        villageRequest1.setAreaId("003");
//        villageRequest1.setAreaNameId("002");
          String[] mto = {"001","005"};
          villageRequest1.setMetroStationId(mto);
//          String[] lin = {"005","003"};
//          villageRequest1.setSubwayLineId(lin);
//        villageRequest1.setId(3);
//        Integer[] pr = {50000,80000};
//        villageRequest1.setSearchAvgPrice(pr);
//        Integer[] ag = {16,16};
//        villageRequest1.setSearchAge(ag);
//        villageRequest1.setId(1);
//        villageRequest1.setAreaId("001");
        List villageList;
        try {
            villageList = sysVillageService.findVillageByConditions(index, type, villageRequest1);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        model.addAttribute("villageList", villageList);
        return null;
    }
}
