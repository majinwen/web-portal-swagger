package com.toutiao.web.apiimpl.rest.plot;


import com.toutiao.app.api.chance.request.plot.NearbyPlotsListRequest;
import com.toutiao.app.domain.plot.NearbyPlotsListDo;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *  小区附近
 *
 */
@RestController
@RequestMapping("/rest/plot/nearby")
public class NearbyPlotsRestController {



    @RequestMapping("/getNearbyPlotsList")
    @ResponseBody
    public NashResult getNearbyPlotsList(@Validated(First.class) NearbyPlotsListRequest nearbyPlotsListRequest) {


        NearbyPlotsListDo nearbyPlotsListDo = new NearbyPlotsListDo();
        BeanUtils.copyProperties(nearbyPlotsListRequest,nearbyPlotsListDo);







        return NashResult.build("");
    }

}
