package com.toutiao.web.apiimpl.impl.advertisement;

import com.toutiao.web.service.advertisement.AdvertisementLandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 广告位
 * Created by 18710 on 2018/3/2.
 */

@Controller
@RequestMapping("/advertisement")
public class AdvertisementLandingController {


    @Autowired
    private AdvertisementLandingService advertisementLandingService;

    //提升核心页pv】cpc广告3
    @RequestMapping("/cpc3")
    @ResponseBody
    public Map<String,Object> advertisementThree(Model model) {
        Map<String,Object> advertisementResult = advertisementLandingService.advertisement();
        model.addAttribute("adcpc",advertisementResult);
        return advertisementResult;
    }





}
