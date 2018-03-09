package com.toutiao.web.apiimpl.impl.advertisement;


import com.toutiao.web.api.chance.request.advertisement.AggAdLandingRequest;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.advertisement.AggAdLandingDo;
import com.toutiao.web.service.advertisement.AggAdLandingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 广告位
 * Created by 18710 on 2018/3/9.
 */

@Controller
@RequestMapping("/agg/ad")
public class AggAdLandingController {

    @Autowired
    private AggAdLandingService aggAdLandingService;


    @RequestMapping(value = "/queryAggAdLanding")
    @ResponseBody
    public NashResult queryAggAdLanding(AggAdLandingRequest aggAdLandingRequest){

        AggAdLandingDo aggAdLandingDo = new AggAdLandingDo();
        BeanUtils.copyProperties(aggAdLandingRequest, aggAdLandingDo);
        Map<String, Object> esfMap = aggAdLandingService.getAdLanding(aggAdLandingDo);

        return NashResult.build(esfMap.get("data"));
    }


}
