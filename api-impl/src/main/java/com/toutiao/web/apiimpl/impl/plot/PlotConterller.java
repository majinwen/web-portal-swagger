package com.toutiao.web.apiimpl.impl.plot;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.officeweb.PriceTrend;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PlotConterller {
    @Autowired
    private PlotService plotService;
    @Autowired
    private NewHouseService newHouseService;
    @Autowired
    private ProjHouseInfoService projHouseInfoService;
    @Autowired
    private PriceTrendService priceTrendService;

    //(查询附近小区和(距离))
    @RequestMapping("/fingNearVillageAndDistance")
    @ResponseBody
    public String GetNearByhHouseAndDistance(String lon, String lat, Model model) {
        List villageList = null;
        Double lonx = Double.valueOf(lon);
        Double laty = Double.valueOf(lat);
        villageList = plotService.GetNearByhHouseAndDistance(lonx, laty);
        model.addAttribute("villageList", villageList);
        return "plot-list";
    }

    //根据条件查询小区
    @RequestMapping("/findVillageByConditions")
    public String findVillageByConditions(VillageRequest villageRequest, Model model) {
        if (villageRequest.getSort() != null) {
            model.addAttribute("sort", Integer.parseInt(villageRequest.getSort()));
        } else {
            model.addAttribute("sort", 0);
        }
        List villageList = null;
        villageList = plotService.findVillageByConditions(villageRequest);
        model.addAttribute("villageList", villageList);
        model.addAttribute("searchType", "plot");
        return "plot/plot-list";
    }


    //小区分页
    @RequestMapping("/villagePage")
    @ResponseBody
    public NashResult villagePage(VillageRequest villageRequest) {
        List villageList = null;
        villageList = plotService.findVillageByConditions(villageRequest);

        for (int i = 0; i < villageList.size(); i++) {
            HashMap<String, Object> itemMap = (HashMap<String, Object>) villageList.get(i);
            String imginfo = (String) itemMap.get("building_imgs");
            if (StringUtil.isNotNullString(imginfo)) {
                String[] imgs = imginfo.split(",");
                itemMap.put("building_imgs", imgs[0]);
                villageList.set(i, itemMap);
            }
        }

        return NashResult.build(villageList);
    }


    //小区详情页
    @RequestMapping("/villageDetail")
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
            PriceTrend priceTrend = new PriceTrend();
            priceTrend.setBuildingId(village.getId());
            priceTrend.setPropertyType((short) 0);
            Map<String, List<PriceTrend>> stringListMap = priceTrendService.priceTrendList(priceTrend);
            model.addAttribute("tradeline", stringListMap);

            //月份
            List<String>dateList= DateUtil.oneYearList();
            model.addAttribute("xlist",dateList);


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


    /**
     * 小区待售页
     *
     * @param model
     * @return
     */
    @RequestMapping("/plotSale")
    public String sale(Model model) {
        model.addAttribute("user", "asds");
        return "plot/plot-sale";
    }

    //基本信息
    @RequestMapping("/plotParameter")
    public String parameter(VillageRequest villageRequest, Model model) {
        List villageList = null;
        villageList = plotService.findVillageByConditions(villageRequest);
        model.addAttribute("villageList", villageList);
        return "plot/plot-parameter";
    }

    //获取小区地图
    @RequestMapping("/getPlotMap")
    public String plotMap(VillageRequest villageRequest, Model model) {
        List villageList = plotService.findVillageByConditions(villageRequest);
        VillageResponse village = (VillageResponse) villageList.get(0);
        model.addAttribute("build", village);
        return "map";
    }
}
