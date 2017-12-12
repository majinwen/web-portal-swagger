package com.toutiao.web.apiimpl.controller;


import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Map;

/**
 * 二手房管理
 */
@Controller
@RequestMapping("/")
public class ProjHouseInfoController {

    @Autowired
    private ProjHouseInfoService projHouseInfoService;


    /**
     * 根据房源的id以及小区的经度，维度查询房源详情，以及附近好房信息
     *
     * @return
     */
    @RequestMapping(value = "/queryByHouseIdandLocation/{houseId}/{lat}/{lon}")
    public String queryProjHouseByhouseIdandLocation(Model model, @PathVariable("houseId") String houseId
            ,@PathVariable("lat") String lat,@PathVariable("lon") String lon ) {
        //房源详情
        Map<String, Object>  houseDetails=projHouseInfoService.queryByHouseId( Integer.valueOf(houseId));
        //小区信息
        /*Map<String, Object> builds = projHouseInfoService.
                queryProjHouseByhouseIdandLocation(Double.valueOf(lat)  ,Double.valueOf(lon));*/
        model.addAttribute("houseDetail", houseDetails.get("data_house"));
        ArrayList<Object> list= (ArrayList<Object>) houseDetails.get("data_house");
        model.addAttribute("houseData", houseDetails.get("total_house"));
        /*model.addAttribute("plot", builds.get("data_plot"));
        model.addAttribute("plotTotal", builds.get("total_plot"));*/
        return "esf/esf-detail";
    }

    /**
     * 二手房列表
     *
     * @param ProjHouseInfoQuery
     * @param model
     * @return
     */
    @RequestMapping("/findProjHouseInfo")
    public String searchNewHouse(ProjHouseInfoQuery ProjHouseInfoQuery, Model model) {
//        Map<String,Object> builds =  projHouseInfoService.queryProjHouseInfo(projHouseInfoRequest);
//        model.addAttribute("builds",builds.get("data"));
//        model.addAttribute("total",builds.get("total"));
        Map<String, Object> builds = projHouseInfoService.queryProjHouseInfo(ProjHouseInfoQuery);
        model.addAttribute("builds", builds.get("data"));
        ArrayList<Object> list= (ArrayList<Object>) builds.get("data");
        model.addAttribute("total", builds.get("total"));
        return "esf/esf-list";

    }
}
