package com.toutiao.web.apiimpl.impl.chance;

import com.toutiao.web.service.chance.PartialMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/{citypath}")
public class PartialMatchingController {

    @Autowired
    private PartialMatchingService partialMatchingService;

    @RequestMapping("/search")
    @ResponseBody
    public Map PartialMatching(String keyword,String houseProperty){
        Map searchMap = null;
        try {
            searchMap = partialMatchingService.Search(keyword,houseProperty);
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchMap;
    }

    @RequestMapping("/xiaoqu/search")
    @ResponseBody
    public Map PartialMatchingXiaoqu(String keyword,String houseProperty){
        Map searchMap = null;
        try {
            searchMap = partialMatchingService.Search(keyword,houseProperty);
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchMap;
    }

    @RequestMapping("/esf/search")
    @ResponseBody
    public Map PartialMatchingEsf(String keyword,String houseProperty){
        Map searchMap = null;
        try {
            searchMap = partialMatchingService.Search(keyword,houseProperty);
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchMap;
    }

    @RequestMapping("/xinfang/search")
    @ResponseBody
    public Map PartialMatchingLoupan(String keyword,String houseProperty){
        Map searchMap = null;
        try {
            searchMap = partialMatchingService.Search(keyword,houseProperty);
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchMap;
    }
}
