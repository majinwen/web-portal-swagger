package com.toutiao.web.apiimpl.controller;


import com.toutiao.web.domain.query.ProjHouseInfoQuery;
import com.toutiao.web.service.projhouse.ProjHouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping(value = "/findProjHouseInfo")
    public String queryProjHouseByhouseIdandLocation(Model model,ProjHouseInfoQuery projHouseInfoQuery) {
        //小区信息
        Map<String, Object> builds = projHouseInfoService.
                queryProjHouseByhouseIdandLocation(projHouseInfoQuery.getLat(), projHouseInfoQuery.getLon());
        projHouseInfoService.queryByHouseId(projHouseInfoQuery.getHouseId());
        //房源详情
        Map<String, Object>  houseDetails=projHouseInfoService.queryByHouseId(projHouseInfoQuery.getHouseId());
        model.addAttribute("houseDetail", builds.get("data_house"));
        model.addAttribute("houseData", builds.get("total_house"));
        model.addAttribute("plot", builds.get("data_plot"));
        model.addAttribute("plotTotal", builds.get("total_plot"));
        return "esf/esf-detail";
    }

    /**
     * 二手房列表
     *
     * @param ProjHouseInfoQuery
     * @param model
     * @return
     */
    @RequestMapping("/findProjHouseInfo1")
    public String searchNewHouse(ProjHouseInfoQuery ProjHouseInfoQuery, Model model) {
//        Map<String,Object> builds =  projHouseInfoService.queryProjHouseInfo(projHouseInfoRequest);
//        model.addAttribute("builds",builds.get("data"));
//        model.addAttribute("total",builds.get("total"));
        Map<String, Object> builds = projHouseInfoService.queryProjHouseInfo(ProjHouseInfoQuery);
        model.addAttribute("builds", builds.get("data"));
        model.addAttribute("total", builds.get("total"));
        return "esf/esf-list";

    }
}
