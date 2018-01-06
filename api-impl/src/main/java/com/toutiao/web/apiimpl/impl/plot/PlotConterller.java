package com.toutiao.web.apiimpl.impl.plot;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.domain.query.VillageResponse;
import com.toutiao.web.service.PriceTrendService;
import com.toutiao.web.service.newhouse.NewHouseService;
import com.toutiao.web.service.plot.PlotService;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/{citypath}/xiaoqu")
public class PlotConterller {
    @Autowired
    private PlotService plotService;
    @Autowired
    private NewHouseService newHouseService;
    @Autowired
    private ProjHouseInfoService projHouseInfoService;
    @Autowired
    private PriceTrendService priceTrendService;

//    //(查询附近小区和(距离))
//    @RequestMapping("/fingNearVillageAndDistance")
//    @ResponseBody
//    public String GetNearByhHouseAndDistance(String lon, String lat, Model model) {
//        List villageList = null;
//        Double lonx = Double.valueOf(lon);
//        Double laty = Double.valueOf(lat);
//        villageList = plotService.GetNearByhHouseAndDistance(lonx, laty);
//        model.addAttribute("villageList", villageList);
//        return "plot-list";
//    }

    //根据条件查询小区
    @RequestMapping("")
    public String findVillageByConditions(VillageRequest villageRequest, Model model) {
        if (villageRequest.getSort() != null) {
            model.addAttribute("sort", Integer.parseInt(villageRequest.getSort()));
        } else {
            model.addAttribute("sort", 0);
        }
        List villageList = null;
        villageList = plotService.findVillageByConditions(villageRequest);
        model.addAttribute("villageList", villageList);
//        model.addAttribute("searchType", "plot");
        return "plot/plot-list";
    }


    //小区分页
    @RequestMapping(value = {""},produces="application/json") //villagePage
    @ResponseBody
    public NashResult villagePage(VillageRequest villageRequest) {
        List<VillageResponse> villageList = null;
        villageList = plotService.findVillageByConditions(villageRequest);
        if (null!=villageList&&villageList.size()!=0&&villageList.get(0).getKey()!=null){
            for (VillageResponse polt : villageList){
                if (null!=polt.getMetroWithPlotsDistance().get(polt.getKey())){
                    String[] str = ((String) polt.getMetroWithPlotsDistance().get(polt.getKey())).split("\\$");
                    polt.getMetroWithPlotsDistance().put(polt.getKey(),str);
                }
            }
        }
        return NashResult.build(villageList);
    }


    //小区详情页
    @RequestMapping("/{id}.html") //villageDetail
    public String villageDetail(VillageRequest villageRequest, NewHouseQuery newHouseQuery, Model model) {
        List villageList = plotService.findVillageByConditions(villageRequest);
        if (villageList != null && villageList.size() != 0) {
            VillageResponse village = (VillageResponse) villageList.get(0);
            model.addAttribute("village", village);

            //附近小区
            String[] latandlon = village.getLocation().split(",");
            Double lonx = Double.valueOf(latandlon[0]);
            Double laty = Double.valueOf(latandlon[1]);
            List nearvillage = plotService.GetNearByhHouseAndDistance(lonx, laty);
            model.addAttribute("nearvillage", nearvillage);

            //走势图
            Integer discId=null;
            String sdiscId = village.getAreaId();
            if (sdiscId != null){
                discId = Integer.parseInt(sdiscId);
            }

            Integer areaId=null;
            String sareaId = village.getTradingAreaId();
            if (sareaId != null){
                areaId = Integer.parseInt(sareaId);
            }

            Map<String, Object> stringListMap = priceTrendService.priceTrendList(village.getId(),discId,areaId);
            model.addAttribute("tradeline", stringListMap);

            //推荐小区好房
            ProjHouseInfoQuery projHouseInfoQuery = new ProjHouseInfoQuery();
            projHouseInfoQuery.setNewcode(String.valueOf(village.getId()));
            List reViHouse = projHouseInfoService.queryProjHouseInfo(projHouseInfoQuery);
            model.addAttribute("reViHouse", reViHouse);

            newHouseQuery.setSort(0);
            newHouseQuery.setPageNum(1);
            newHouseQuery.setPageSize(4);
            Map<String, Object> builds = newHouseService.getNewHouse(newHouseQuery);
            List<Object> newbuildrecomed = (List<Object>) builds.get("data");
            model.addAttribute("newbuilds", newbuildrecomed);
            return "plot/plot-detail";
        }
        return "404";
    }


//    /**
//     * 小区待售页
//     *
//     * @param model
//     * @return
//     */
//    @RequestMapping("/plotSale")
//    public String sale(Model model) {
//        model.addAttribute("user", "asds");
//        return "plot/plot-sale";
//    }

    //基本信息
    @RequestMapping("/{id}/desc.html")
    public String parameter(VillageRequest villageRequest, Model model) {
        List villageList = null;
        villageList = plotService.findVillageByConditions(villageRequest);
        model.addAttribute("villageList", villageList);
        return "plot/plot-parameter";
    }

    //获取小区地图
    @RequestMapping("/{id}/map.html")
    public String plotMap(VillageRequest villageRequest, Model model) {
        List villageList = plotService.findVillageByConditions(villageRequest);
        VillageResponse village = (VillageResponse) villageList.get(0);
        model.addAttribute("build", village);
        return "map";
    }
}
