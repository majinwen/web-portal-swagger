package com.toutiao.web.apiimpl.impl.advertisement;

import com.toutiao.web.common.restmodel.NashResult;
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
@RequestMapping("/ad")
public class AdvertisementLandingController {


    @Autowired
    private AdvertisementLandingService advertisementLandingService;

    //提升核心页pv】cpc广告3
    @RequestMapping("/cpc3")
    @ResponseBody
    public NashResult advertisementCpc3() {
        Map<String,Object> advertisementResult = advertisementLandingService.advertisementCpc_3();

        return NashResult.build(advertisementResult.get("data"));
    }


    //提升核心页pv】cpc广告1
    @RequestMapping("/cpc2")
    @ResponseBody
    public NashResult advertisementCpc1() {
        Map<String,Object> advertisementResult = advertisementLandingService.advertisementCpc_1();

        return NashResult.build(advertisementResult.get("data"));
    }



    //提升核心页pv】cpc广告3
    @RequestMapping("/xxl3")
    @ResponseBody
    public NashResult advertisementXxl3() {
        Map<String,Object> advertisementResult = advertisementLandingService.advertisementCpc_3();
        return NashResult.build(advertisementResult.get("data"));
    }


    //提升核心页pv】cpc广告1
    @RequestMapping("/xxl2")
    public NashResult advertisementXxl1() {
        Map<String,Object> advertisementResult = advertisementLandingService.advertisementCpc_1();
        return NashResult.build(advertisementResult.get("data"));
    }


}
