package com.toutiao.web.apiimpl.rest.plot;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.plot.PlotsEsfRequest;
import com.toutiao.app.api.chance.response.plot.PlotsEsfListResponse;
import com.toutiao.app.api.chance.response.plot.PlotsEsfRoomCountResponse;
import com.toutiao.app.domain.plot.PlotsEsfRoomCountDomain;
import com.toutiao.app.domain.sellhouse.SellAndClaimHouseDetailsDo;
import com.toutiao.app.domain.sellhouse.SellHouseDo;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;
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


    @RequestMapping("/getPlotsEsfList")
    @ResponseBody
    public NashResult getPlotsEsfList(@Validated PlotsEsfRequest plotsEsfRequest,@RequestHeader("User-Agent") String userAgent, @CookieValue("select_city")String city) {

        Integer plotsId = plotsEsfRequest.getPlotId();
        PlotsEsfRoomCountResponse plotsEsfRoomCountResponse = new PlotsEsfRoomCountResponse();
        PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryPlotsEsfByPlotsId(plotsId,userAgent,city);
        BeanUtils.copyProperties(plotsEsfRoomCountDomain, plotsEsfRoomCountResponse);
        return NashResult.build(plotsEsfRoomCountResponse);
    }



    @ResponseBody
    @RequestMapping(value = "/getEsfByPlotsIdAndRoom")
    public NashResult getEsfByPlotsIdAndRoom (@Validated(First.class) PlotsEsfRequest plotsEsfRequest, @RequestHeader("User-Agent") String userAgent, @CookieValue("select_city")String city) {

        Integer plotsId = plotsEsfRequest.getPlotId();
        Integer room = plotsEsfRequest.getRoom();
        Integer pageNum = plotsEsfRequest.getPageNum();
        Integer pageSize = plotsEsfRequest.getPageSize();
        List<SellAndClaimHouseDetailsDo> sellHouseDoList = plotsEsfRestService.getEsfByPlotsIdAndRoom(plotsId,room,pageNum,pageSize,userAgent,city);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(sellHouseDoList));

        List<PlotsEsfListResponse> plotsEsfListResponses = JSONObject.parseArray(json.toJSONString(),PlotsEsfListResponse.class);

        return NashResult.build(plotsEsfListResponses);
    }


}
