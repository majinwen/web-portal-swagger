package com.toutiao.web.apiimpl.impl.advertisement;


import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.query.IntelligenceQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 广告位
 * Created by 18710 on 2018/3/9.
 */

@Controller
@RequestMapping("/agg/ad")
public class AggAdLandingController {


    @RequestMapping(value = "/queryUserChoice")
    @ResponseBody
    public NashResult queryUserChoice(IntelligenceQuery intelligenceQuery){


        return NashResult.build("");
    }


}
