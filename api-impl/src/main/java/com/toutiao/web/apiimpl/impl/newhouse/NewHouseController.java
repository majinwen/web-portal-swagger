package com.toutiao.web.apiimpl.impl.newhouse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.common.util.DateUtil;
import com.toutiao.web.dao.entity.officeweb.PriceTrend;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.service.PriceTrendService;
import com.toutiao.web.service.newhouse.NewHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("newhouse")
public class NewHouseController {

    @Autowired
    private NewHouseService newHouseService;
    @Autowired
    private PriceTrendService priceTrendService;

    /**
     * 新房首页
     * @return
     */
    @RequestMapping("/newhouseindex")
    public String index(NewHouseQuery newHouseQuery, Model model) {
         newHouseQuery.setSort(0);
         newHouseQuery.setPageNum(1);
         System.out.println("aaa");
         newHouseQuery.setPageSize(4);
         Map<String,Object> builds = newHouseService.getNewHouse(newHouseQuery);
         model.addAttribute("newbuilds",builds);
         return "newhouse/new-index";
    }

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
     * 搜索新房
     *
     * */

    @RequestMapping("/searchNewHouseByKey")
    public String searchNewHouseByKey(@RequestParam("keyword") String text, Model model){
        ArrayList<HashMap<String,Object>> build= (ArrayList<HashMap<String, Object>>) newHouseService.searchNewHouse(text);
        model.addAttribute("builds",build);
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
        PriceTrend priceTrend=new PriceTrend();
        priceTrend.setBuildId(buildingId);
        priceTrend.setPropertyType((short)1);

        Map<String ,List<PriceTrend>> priceTrendList = priceTrendService.priceTrendList(priceTrend);

        List<String>dateList= DateUtil.oneYearList();

        String detailBuild = (String) details.get("build");
        JSONObject build=JSON.parseObject(detailBuild);
        model.addAttribute("build",build);
        model.addAttribute("layout", details.get("layout"));
        model.addAttribute("nearbybuild",details.get("nearbybuild"));
        model.addAttribute("tradeline",priceTrendList);
        model.addAttribute("xlist",dateList);
        return "newhouse/new-detail";

    }

    /**
     * 楼盘户型详情
     * @param buildingId
     * @param tags
     * @param model
     * @return
     */
    @RequestMapping("/getNewHouseLayoutDetails")
    public String getNewHouseLayoutDetails(@RequestParam("id") Integer buildingId,@RequestParam("tags") Integer tags, Model model){
        Map<String,Object> details = newHouseService.getNewHouseLayoutDetails(buildingId,tags);
        model.addAttribute("layoutDetails", details.get("layouts"));
        return "";

    }

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
        return "newhouse/new- parameter";
    }

    /**
     * 新房首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){

        model.addAttribute("user","asds");
        return "newhouse/new-index";
    }

}
