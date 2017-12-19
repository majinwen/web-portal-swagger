package com.toutiao.web.apiimpl.controller;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.pinyin.Pinyin;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.service.newhouse.NewHouseService;
import com.toutiao.web.service.pinyin.PinyinService;
import com.toutiao.web.service.plot.SysVillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value= "pinyin")
public class PinyinServeContoller {

    @Autowired
    private NewHouseService newHouseService;
    @Autowired
    private PinyinService pinyinService;
    @Autowired
    private SysVillageService sysVillageService;


    /**
     * 根据名字转换拼音及首字大写
     * @param name
     * @return
     */
    @RequestMapping("/changepinyin")
    @ResponseBody
    public NashResult getChangepinyin(@RequestParam("name") String name){

        Pinyin pinyin = pinyinService.getPinyin(name);
        return NashResult.build(pinyin);

    }

//    /**
//     * 大首页
//     * @param model
//     * @return
//     */
//    @RequestMapping("/index")
//    public String index( Model model,VillageRequest villageRequest){
//        NewHouseQuery newHouseQuery=new NewHouseQuery();
//        villageRequest.setAreaSize("80");
//        newHouseQuery.setSort(0);
//        newHouseQuery.setPageNum(1);
//        newHouseQuery.setPageSize(4);
//        Map<String,Object> builds = newHouseService.getNewHouse(newHouseQuery);
//        List villageList = sysVillageService.findVillageByConditions(villageRequest);
//        model.addAttribute("villageList", villageList);
//        model.addAttribute("newbuilds",builds);
//        model.addAttribute("user","asds");
//        return "index";
//    }
}
