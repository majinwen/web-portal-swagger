package com.toutiao.web.apiimpl.impl.newhouse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.service.newhouse.NewHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
       ArrayList<HashMap<String,Object>> build= (ArrayList<HashMap<String, Object>>) builds.get("data");
        model.addAttribute("builds",build);
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
        Map<String,Object> details = newHouseService.getNewHouseDetails(buildingId);
        List<Integer> zuobiao=new ArrayList();
        zuobiao.add(2);
        zuobiao.add(15);
        zuobiao.add(22);
        zuobiao.add(10);
        model.addAttribute("zz",zuobiao);

        String detailBuild = (String) details.get("build");
        JSONObject build=JSON.parseObject(detailBuild);
        model.addAttribute("build",build);
        model.addAttribute("layout", details.get("layout"));
        model.addAttribute("nearbybuild",details.get("nearbybuild"));
        return "newhouse/new-detail";

    }

    /**
     * 楼盘户型详情
     * @param buildingId
     * @param tags
     * @param model
     * @return
     */
/*
    @RequestMapping("/getNewHouseLayoutDetails")
    public String getNewHouseLayoutDetails(@RequestParam("id") Integer buildingId,@RequestParam("tags") Integer tags, Model model){
        Map<String,Object> details = newHouseService.getNewHouseLayoutDetails(buildingId,tags);
        model.addAttribute("layoutDetails", details.get("layouts"));
        return "";

    }
*/

    /**
     * 根据楼盘计算不同户型数量
     * @param buildingId
     * @param model
     * @return
     */
    @RequestMapping("/getNewHouseLayoutCountByRoom")
    public String getNewHouseLayoutCountByRoom(@RequestParam("id") Integer buildingId,@RequestParam("tags") Integer tags,  Model model){
        List<Map<String,Object>> room = newHouseService.getNewHouseLayoutCountByRoom(buildingId);
        Map<String,Object> details = newHouseService.getNewHouseLayoutDetails(buildingId,tags);
        model.addAttribute("layoutDetails", details.get("layouts"));
        model.addAttribute("room",room);
        model.addAttribute("bid",buildingId);
        model.addAttribute("tags",tags);
        return "newhouse/new-house-type";

    }


    /**
     * 楼盘全部描述
     * @param buildingId
     * @param model
     * @return
     */
    @RequestMapping("/getNewHouseDiscript")
    public String getNewHouseDiscript(@RequestParam("id") Integer buildingId, Model model){
        List<Map<String,Object>> discripts=newHouseService.getNewHouseDiscript(buildingId);
        model.addAttribute("discript",discripts);
        return "newhouse/new-parameter";
    }


}
