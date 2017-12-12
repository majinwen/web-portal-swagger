package com.toutiao.web.apiimpl.impl.newhouse;


import com.alibaba.fastjson.JSON;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.service.newhouse.NewHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("newhouse")
public class NewHouseController {

    @Autowired
    private NewHouseService newHouseService;

    /**
     * 新房列表
     * @param newHouseQuery
     * @param model
     * @return
     */

    @RequestMapping("/searchNewHouse")
    public String searchNewHouse(NewHouseQuery newHouseQuery, Model model){
        Map<String,Object> builds =  newHouseService.getNewHouse(newHouseQuery);
        model.addAttribute("builds",builds.get("data"));
        model.addAttribute("total",builds.get("total"));
        return "newhouse/new-list";

    }


    /**
     * 楼盘详情信息
     * @param buildingId
     * @param model
     * @return
     */
    @RequestMapping("/getNewHouseDetails")
    public String getNewHouseDetails(@RequestParam("id") Integer buildingId, Model model){

     //   Map<String, Object>houseinfo=newHouseService.getNewHouseDetails(121);
        Map <String,Object>nearHouseList=null;
        try {
            nearHouseList=newHouseService.getNearHouse(121,"beijing1","building1",11.12,21.23,"30000000");
        } catch (Exception e) {
            e.printStackTrace();
        }
            model.addAttribute("nearHouseList",nearHouseList);
       // model.addAttribute ("houseinfo",houseinfo);
        return "";

    }

/*    *//**
     * 附近楼盘
     * @return
     *//*

    @RequestMapping("/getNearHouse")
    public String getNearHouse(String index, String type, double lat, double lon, String distance){
        try {
            Map<String,Object> builds =  newHouseService.getNearHouse(index,type,lat,lon,distance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }*/

}
