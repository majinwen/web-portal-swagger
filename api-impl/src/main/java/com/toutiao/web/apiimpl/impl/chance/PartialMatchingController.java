package com.toutiao.web.apiimpl.impl.chance;

import com.toutiao.web.service.chance.PartialMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/{citypath}")
public class PartialMatchingController {
    @Autowired
    private PartialMatchingService partialMatchingService;

    Map searchMap = new HashMap();

    @RequestMapping("/search")
    @ResponseBody
    public Map indexPartialMatching(String keyword,String houseProperty){
        try {
            searchMap = partialMatchingService.search(keyword,houseProperty);
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchMap;
    }

    @RequestMapping("/xiaoqu/search")
    @ResponseBody
    public Map plotPartialMatching(String keyword,String houseProperty){
        try {
            searchMap = partialMatchingService.search(keyword,houseProperty);
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchMap;
    }

    @RequestMapping("/esf/search")
    @ResponseBody
    public Map esfPartialMatching(String keyword,String houseProperty){
        try {
            searchMap = partialMatchingService.search(keyword,houseProperty);
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchMap;
    }

    @RequestMapping("/xinfang/search")
    @ResponseBody
    public Map newHousePartialMatching(String keyword,String houseProperty){
        try {
            searchMap = partialMatchingService.search(keyword,houseProperty);
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchMap;
    }
}
