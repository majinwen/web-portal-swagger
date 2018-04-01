package com.toutiao.web.apiimpl.impl.plot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.MapInfo;
import com.toutiao.web.domain.query.*;
import com.toutiao.web.service.PriceTrendService;
import com.toutiao.web.service.map.MapService;
import com.toutiao.web.service.newhouse.NewHouseService;
import com.toutiao.web.service.plot.PlotService;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
import com.toutiao.web.service.rent.RentHouseService;
import org.apache.commons.lang3.StringUtils;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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
    @Autowired
    private MapService mapService;
    @Autowired
    private RentHouseService rentHouseService;

    //(查询附近小区和(距离))
    @RequestMapping("/fingNearVillageAndDistance")
    @ResponseBody
    public String GetNearByhHouseAndDistance(String lon, String lat, Model model) {
        List villageList = null;
        Double lonx = Double.valueOf(lon);
        Double laty = Double.valueOf(lat);
        villageList = plotService.GetNearByhHouseAndDistance(laty, lonx);
        model.addAttribute("villageList", villageList);
        return "plot-list";
    }

    //根据条件查询小区
    @RequestMapping("")
    public String findVillageByConditions(VillageRequest villageRequest, Model model) {
        if (villageRequest.getSort() != null) {
            model.addAttribute("sort", Integer.parseInt(villageRequest.getSort()));
        } else {
            model.addAttribute("sort", 0);
        }
        List villageList = null;
        if(villageRequest.getLat() != 0 && villageRequest.getLon() != 0){
            villageList = plotService.findNearByVillageByConditions(villageRequest);
        }else{
            villageList = plotService.findVillageByConditions(villageRequest);
        }
        model.addAttribute("villageList", villageList);
//        model.addAttribute("searchType", "plot");
        return "plot/plot-list";
    }


    //小区分页
    @RequestMapping(value = {""},produces="application/json") //villagePage
    @ResponseBody
    public NashResult villagePage(VillageRequest villageRequest) {
        List<VillageResponse> villageList = null;
        if(villageRequest.getLat() != 0 && villageRequest.getLon() != 0){
            villageList = plotService.findNearByVillageByConditions(villageRequest);
        }else{
            villageList = plotService.findVillageByConditions(villageRequest);
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

            //推荐小区二手房
            ProjHouseInfoQuery projHouseInfoQuery = new ProjHouseInfoQuery();
            projHouseInfoQuery.setNewcode(String.valueOf(village.getId()));
//            List reViHouse = projHouseInfoService.queryProjHouseInfo(projHouseInfoQuery);
            List reViHouse = projHouseInfoService.queryProjHouseInfoByVillageId(projHouseInfoQuery);
            model.addAttribute("reViHouse", reViHouse);

            //推荐小区(普租+公寓)
            RentHouseQuery rentHouseQuery = new RentHouseQuery();
            rentHouseQuery.setZuFangId(String.valueOf(villageRequest.getId()));
//            rentHouseQuery.setRentSign(0);
            Map rent = rentHouseService.queryHouseByparentId(rentHouseQuery);
            if (rent!=null){
                model.addAttribute("rent",rent);
            }

            //推荐小区公寓
//            RentHouseQuery rentApartmentQuery = new RentHouseQuery();
//            rentApartmentQuery.setZuFangId(String.valueOf(villageRequest.getId()));
//            rentApartmentQuery.setRentSign(1);
//            Map apartment = rentHouseService.queryHouseByparentId(rentApartmentQuery);
//            if (apartment!=null){
//                model.addAttribute("apartment",apartment);
//            }

            //推荐新房
//            newHouseQuery.setSort(0);
//            newHouseQuery.setPageNum(1);
//            newHouseQuery.setPageSize(4);
//            Map<String, Object> builds = newHouseService.getNewHouse(newHouseQuery);
//            List<Object> newbuildrecomed = (List<Object>) builds.get("data");
//            model.addAttribute("newbuilds", newbuildrecomed);
            //查询地图信息
            MapInfo mapInfo = mapService.getMapInfo(villageRequest.getId());
            JSONObject datainfo= JSON.parseObject(((PGobject) mapInfo.getDataInfo()).getValue());
            if(StringTool.isNotBlank(mapInfo)&&StringTool.isNotBlank(datainfo)){
                model.addAttribute("mapInfo", mapInfo);
                model.addAttribute("datainfo",datainfo);
            }
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
        if (null!=villageList&&villageList.size()!=0){
            model.addAttribute("villageList", villageList);
            return "plot/plot-parameter";
        }
        return "404";
    }

    //获取小区地图
    @RequestMapping("/{id}/map.html")
    public String plotMap(VillageRequest villageRequest, Model model) {
        List villageList = plotService.findVillageByConditions(villageRequest);
        if (null!= villageList&&villageList.size()!=0){
        VillageResponse village = (VillageResponse) villageList.get(0);
            model.addAttribute("build", village);
            return "map";
        }
        return "404";
    }

    /**
     *  
     * @Description：小区收藏
     *
     * @Param
     * @Return 
     * @Author zengqingzhou
     * @Date 2018/3/3 12:11
     */
    @RequestMapping("/collectPlot")
    @ResponseBody
    public NashResult collectPlot(HttpServletRequest request, HttpServletResponse response, Model model,
                                  @RequestParam("plotId") String plotId){
        if(StringUtils.isNotBlank(plotId)){
            String userPhone = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);
            //是否登录
            if (StringUtils.isBlank(userPhone)){
                //跳转登录页面
                return NashResult.Fail("no-login","");
            }else {
                //TODO
                //保存用户电话(标志)和小区信息

            }
        }
        return  null;
    }
}
