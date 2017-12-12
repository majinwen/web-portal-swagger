package com.toutiao.web.apiimpl.impl.newhouse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        Map<String,Object> details = newHouseService.getNewHouseDetails(buildingId);

        String detailBuild = (String) details.get("build");
        String listLayout = (String) details.get("layout");
        String nearbybuild = (String) details.get("nearbybuild");
        JSONObject build=JSON.parseObject(detailBuild);
        JSONObject layout=JSON.parseObject(listLayout);
        model.addAttribute("build",build);
        model.addAttribute("layout",layout);
        model.addAttribute("nearby",nearbybuild);
        return "";

    }


}
