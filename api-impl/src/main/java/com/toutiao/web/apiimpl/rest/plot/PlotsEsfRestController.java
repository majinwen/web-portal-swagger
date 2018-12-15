package com.toutiao.web.apiimpl.rest.plot;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.plot.PlotsEsfRequest;
import com.toutiao.app.api.chance.response.plot.PlotEsfListResponse;
import com.toutiao.app.api.chance.response.plot.PlotEsfResponse;
import com.toutiao.app.api.chance.response.plot.PlotsEsfRoomCountResponse;
import com.toutiao.app.domain.plot.PlotsEsfRoomCountDomain;
import com.toutiao.app.domain.sellhouse.SellAndClaimHouseDetailsDo;
import com.toutiao.app.domain.sellhouse.SellAndClaimHouseDetailsDomain;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.city.CityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/plot/esf")
public class PlotsEsfRestController {

    @Autowired
    private PlotsEsfRestService plotsEsfRestService;


    @RequestMapping(value = "/getPlotsEsfList", method = RequestMethod.GET)
    @ResponseBody
    public PlotsEsfRoomCountResponse getPlotsEsfList(@Validated PlotsEsfRequest plotsEsfRequest) {

        Integer plotsId = plotsEsfRequest.getPlotId();
        PlotsEsfRoomCountResponse plotsEsfRoomCountResponse = new PlotsEsfRoomCountResponse();
        PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryPlotsEsfByPlotsId(plotsId, CityUtils.getCity());
        BeanUtils.copyProperties(plotsEsfRoomCountDomain, plotsEsfRoomCountResponse);
        return plotsEsfRoomCountResponse;
    }



    @ResponseBody
    @RequestMapping(value = "/getEsfByPlotsIdAndRoom", method = RequestMethod.GET)
    public PlotEsfListResponse getEsfByPlotsIdAndRoom (@Validated(First.class) PlotsEsfRequest plotsEsfRequest) {

        Integer plotsId = plotsEsfRequest.getPlotId();
        Integer room = plotsEsfRequest.getRoom();
        Integer pageNum = plotsEsfRequest.getPageNum();
        Integer pageSize = plotsEsfRequest.getPageSize();
        SellAndClaimHouseDetailsDomain esfByPlotsIdAndRoom = plotsEsfRestService.getEsfByPlotsIdAndRoom(plotsId, room, pageNum, pageSize, CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(esfByPlotsIdAndRoom.getData()));
        List<PlotEsfResponse> plotEsfResponses = JSONObject.parseArray(json.toJSONString(),PlotEsfResponse.class);
        PlotEsfListResponse plotEsfListResponse = new PlotEsfListResponse();
        plotEsfListResponse.setPlotEsfResponseList(plotEsfResponses);
        plotEsfListResponse.setTotalNum(esfByPlotsIdAndRoom.getTotalNum());
        return plotEsfListResponse;
    }


}
