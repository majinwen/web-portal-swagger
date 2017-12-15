package com.toutiao.web.apiimpl.impl.newhouse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        PriceTrend priceTrend=new PriceTrend();
        priceTrend.setBuildId(buildingId);
        priceTrend.setPropertyType((short)1);

        List<PriceTrend> priceTrendList = priceTrendService.priceTrendList(priceTrend);

        List<PriceTrend>ptCD0=new  ArrayList<>();
        List<PriceTrend>ptCD1=new  ArrayList<>();
        List<PriceTrend>ptCD2=new  ArrayList<>();
        for (int i=0;i<priceTrendList.size();i++){
             PriceTrend ptitem= priceTrendList.get(i);
             if (ptitem.getContrastDA()==0){
                  ptCD0.add(ptitem);
             }else if (ptitem.getContrastDA()==1){
                  ptCD1.add(ptitem);
             }else if (ptitem.getContrastDA()==2){
                 ptCD2.add(ptitem);
             }
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
        ArrayList<String>dateList=new ArrayList<>();
        for (int i = 0; i < 12; i++){
            int currentMonth=cal.get(Calendar.MONTH);
            int currentYear=cal.get(Calendar.YEAR);
            dateList.add(currentYear+"年"+(currentMonth+1)+"月");
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        }

        String detailBuild = (String) details.get("build");
        JSONObject build=JSON.parseObject(detailBuild);
        model.addAttribute("build",build);
        model.addAttribute("layout", details.get("layout"));
        model.addAttribute("nearbybuild",details.get("nearbybuild"));
        model.addAttribute("ptCD0",ptCD0);
        model.addAttribute("ptCD1",ptCD1);
        model.addAttribute("ptCD2",ptCD2);
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
