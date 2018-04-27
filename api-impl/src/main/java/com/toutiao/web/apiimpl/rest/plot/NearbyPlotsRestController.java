package com.toutiao.web.apiimpl.rest.plot;


import com.toutiao.app.api.chance.request.plot.NearbyPlotsListRequest;
import com.toutiao.app.api.chance.response.plot.NearbyPlotsListResponse;
import com.toutiao.app.domain.plot.NearbyPlotsListDo;
import com.toutiao.app.domain.plot.PlotDetailsFewDomain;
import com.toutiao.app.service.plot.NearbyPlotsRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private NearbyPlotsRestService nearbyPlotsRestService;

    /**
     * 查询小区附近列表
     * @param nearbyPlotsListRequest
     * @return
     */
    @RequestMapping("/getNearbyPlotsList")
    @ResponseBody
    public InvokeResult getNearbyPlotsList(@Validated(First.class) NearbyPlotsListRequest nearbyPlotsListRequest) {
        NearbyPlotsListDo nearbyPlotsListDo = new NearbyPlotsListDo();
        BeanUtils.copyProperties(nearbyPlotsListRequest,nearbyPlotsListDo);
        PlotDetailsFewDomain plotDetailsFewDomain = nearbyPlotsRestService.queryNearbyPlotsListByUserCoordinate(nearbyPlotsListDo);
        NearbyPlotsListResponse newHouseLayoutCountResponse = new NearbyPlotsListResponse();
        BeanUtils.copyProperties(plotDetailsFewDomain, newHouseLayoutCountResponse);
        return InvokeResult.build(newHouseLayoutCountResponse);
    }

}
