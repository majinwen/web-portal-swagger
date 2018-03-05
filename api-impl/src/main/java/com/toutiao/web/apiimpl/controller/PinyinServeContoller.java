package com.toutiao.web.apiimpl.controller;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.pinyin.Pinyin;
import com.toutiao.web.service.newhouse.NewHouseService;
import com.toutiao.web.service.pinyin.PinyinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value= "pinyin")
public class PinyinServeContoller {

    @Autowired
    private PinyinService pinyinService;


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
     * 404
     *
     * @return
     */
    @RequestMapping("/404")
    public String empty(Model model){

        return "404";
    }

    /**
     * cpc2
     *
     * @return
     */
    @RequestMapping("/cpc2")
    public String cpc2(Model model){

        return "/cpc/cpcPage2";
    }

    /**
     * cpc3
     *
     * @return
     */
    @RequestMapping("/cpc3")
    public String cpc3(Model model){

        return "/cpc/cpcPage3";
    }
}
