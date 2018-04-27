package com.toutiao.web.apiimpl.rest.plot;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.plot.PlotsEsfRequest;
import com.toutiao.app.api.chance.response.plot.PlotsEsfListResponse;
import com.toutiao.app.api.chance.response.plot.PlotsEsfRoomCountResponse;
import com.toutiao.app.domain.plot.PlotsEsfRoomCountDomain;
import com.toutiao.app.domain.sellhouse.SellHouseDo;
import com.toutiao.app.service.plot.PlotsEsfRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/plot/esf")
public class PlotsEsfRestController {

    @Autowired
    private PlotsEsfRestService plotsEsfRestService;


    @RequestMapping("/getPlotsEsfList")
    @ResponseBody
    public InvokeResult getPlotsEsfList(@Validated PlotsEsfRequest plotsEsfRequest) {

        Integer plotsId = plotsEsfRequest.getPlotsId();
        PlotsEsfRoomCountResponse plotsEsfRoomCountResponse = new PlotsEsfRoomCountResponse();
        PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryPlotsEsfByPlotsId(plotsId);
        BeanUtils.copyProperties(plotsEsfRoomCountDomain, plotsEsfRoomCountResponse);
        return InvokeResult.build(plotsEsfRoomCountResponse);
    }



    @ResponseBody
    @RequestMapping(value = "/getEsfByPlotsIdAndRoom")
    public InvokeResult getEsfByPlotsIdAndRoom (@Validated(First.class) PlotsEsfRequest plotsEsfRequest) {

        Integer plotsId = plotsEsfRequest.getPlotsId();
        Integer room = plotsEsfRequest.getRoom();
        Integer pageNum = plotsEsfRequest.getPageNum();
        Integer pageSize = plotsEsfRequest.getPageSize();
        List<SellHouseDo> sellHouseDoList = plotsEsfRestService.getEsfByPlotsIdAndRoom(plotsId,room,pageNum,pageSize);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(sellHouseDoList));

        List<PlotsEsfListResponse> plotsEsfListResponses = JSONObject.parseArray(json.toJSONString(),PlotsEsfListResponse.class);

        return InvokeResult.build(plotsEsfListResponses);
    }


}
