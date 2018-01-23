package com.toutiao.web.apiimpl.impl.homepage;


import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.apiimpl.authentication.RedisSession;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.service.newhouse.NewHouseService;
import com.toutiao.web.service.plot.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomePageController {


    @Autowired
    private NewHouseService newHouseService;
    @Autowired
    private PlotService plotService;
    @Autowired
    private RedisSession redisSession;


    @RequestMapping(value={""})
    public String index(){
        return "redirect:/bj";
    }
    /**
     * 大首页
     * @param model
     * @return
     */
    @RequestMapping(value={"{citypath}"})
    public String index(@PathVariable("citypath")String citypath, Model model, VillageRequest villageRequest){
        Date data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tradeQuotations = redisSession.getValue("TradeQuotations");
        JSONObject jsonObject = JSONObject.parseObject(tradeQuotations);
        model.addAttribute("TradeQuotations",jsonObject);
        NewHouseQuery newHouseQuery=new NewHouseQuery();
        newHouseQuery.setSort(0);
//        newHouseQuery.setPageNum(1);
//        newHouseQuery.setPageSize(5);
        Map<String,Object> builds = newHouseService.getNewHouse(newHouseQuery);
        List villageList = plotService.findVillageByConditions(villageRequest);
        model.addAttribute("villageList", villageList);
        model.addAttribute("newbuilds",builds);
        model.addAttribute("user","asds");
        model.addAttribute("currentTime",sdf.format(data));
//        model.addAttribute("searchType","projhouse");
        return "index";

    }
}
