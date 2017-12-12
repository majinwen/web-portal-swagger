package com.toutiao.web.apiimpl.controller;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.pinyin.Pinyin;
import com.toutiao.web.service.pinyin.PinyinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping("/changepinyin11")
    public String getChangepinyin11(Model model){

        model.addAttribute("user","asds");

        return "/newhouse/new-detail";

    }

    @RequestMapping("/changepinyin22")
    public String getChangepinyin22(Model model){

        model.addAttribute("user","asds");

        return "/esf/esf-detail";

    }
    @RequestMapping("/changepinyin33")
    public String getChangepinyin33(Model model){

        model.addAttribute("user","asds");

        return "/newhouse/new-parameter";

    }
}
