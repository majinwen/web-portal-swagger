package com.toutiao.web.apiimpl.controller;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.pinyin.Pinyin;
import com.toutiao.web.domain.query.NewHouseQuery;
import com.toutiao.web.domain.query.VillageRequest;
import com.toutiao.web.service.newhouse.NewHouseService;
import com.toutiao.web.service.pinyin.PinyinService;
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
//    @Autowired
//    private SysVillageService sysVillageService;


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

    /**
     * 智能找房
     * @param model
     * @return
     */
    @RequestMapping("/intelligent")
    public String sale(Model model){
        model.addAttribute("user","asds");
        return "/intelligent-find";
    }
}
