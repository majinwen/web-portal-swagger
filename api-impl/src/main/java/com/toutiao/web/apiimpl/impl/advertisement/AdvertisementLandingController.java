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
@RequestMapping("/{citypath}/advertisement")
public class AdvertisementLandingController {


    @Autowired
    private AdvertisementLandingService advertisementLandingService;

    //提升核心页pv】cpc广告3
    @RequestMapping("/cpc3")
    public String advertisementCpc3(Model model) {
        Map<String,Object> advertisementResult = advertisementLandingService.advertisementCpc_3();
        model.addAttribute("adcpc",advertisementResult);
        return "/cpc/cpcPage3";
    }


    //提升核心页pv】cpc广告1
    @RequestMapping("/cpc2")
    public String advertisementCpc1(Model model) {
        Map<String,Object> advertisementResult = advertisementLandingService.advertisementCpc_1();
        model.addAttribute("adcpc",advertisementResult);
        return "/cpc/cpcPage2";
    }


}
