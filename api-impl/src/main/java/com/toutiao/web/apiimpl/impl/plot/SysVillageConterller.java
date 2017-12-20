package com.toutiao.web.apiimpl.impl.plot;

import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.domain.query.VillageResponse;
import com.toutiao.web.service.newhouse.NewHouseService;
import com.toutiao.web.service.plot.SysVillageService;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class SysVillageConterller {
    @Autowired
    private SysVillageService sysVillageService;
    @Autowired
    private NewHouseService newHouseService;
    @Autowired
    private ProjHouseInfoService projHouseInfoService;

    //(查询附近小区和(距离))
    @RequestMapping("/fingNearVillageAndDistance")
    @ResponseBody
    public String GetNearByhHouseAndDistance(double lat, double lon, Model model) {
        List villageList = null;
        villageList = sysVillageService.GetNearByhHouseAndDistance(lat, lon);
        model.addAttribute("villageList", villageList);
        return "plot-list";
    }

    //根据条件查询小区
    @RequestMapping("/findVillageByConditions")
    public String findVillageByConditions(VillageRequest villageRequest, Model model) {
        VillageRequest villageRequest1 = new VillageRequest();
        villageRequest1.setAreaSize("80");
        List villageList = null;
        villageList = sysVillageService.findVillageByConditions(villageRequest1);
        model.addAttribute("villageList", villageList);
        return "plot/plot-list";
    }


    //小区详情页
    @RequestMapping("/villageDetail")
    public String villageDetail(VillageRequest villageRequest, NewHouseQuery newHouseQuery, Model model) {
        List villageList = sysVillageService.findVillageByConditions(villageRequest);
        VillageResponse village= (VillageResponse) villageList.get(0);
        model.addAttribute("village", village);

        //附近小区
        Double [] latandlon = village.getLocation();
        double lat = latandlon[0];
        double lon = latandlon[1];
        List nearvillage = sysVillageService.GetNearByhHouseAndDistance(lat, lon);
        model.addAttribute("nearvillage",nearvillage);

        //推荐小区好房
        ProjHouseInfoQuery projHouseInfoQuery = new ProjHouseInfoQuery();
        projHouseInfoQuery.setHousePlotName(village.getRc());
        List reViHouse = projHouseInfoService.queryProjHouseInfo(projHouseInfoQuery);
        model.addAttribute("reViHouse",reViHouse);

        newHouseQuery.setSort(0);
        newHouseQuery.setPageNum(1);
        newHouseQuery.setPageSize(4);
        Map<String,Object> builds = newHouseService.getNewHouse(newHouseQuery);
        List<Object> newbuildrecomed= (List<Object>) builds.get("data");
        model.addAttribute("newbuilds",newbuildrecomed);
        return "plot/plot-detail";
    }



    /**
     * 小区待售页
     * @param model
     * @return
     */
    @RequestMapping("/plotSale")
    public String sale(Model model){
        model.addAttribute("user","asds");
        return "plot/plot-sale";
    }
}
