package com.toutiao.web.apiimpl.rest.sellhouse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.sellhouse.AgentSellHouseRequest;
import com.toutiao.app.api.chance.request.sellhouse.NearBySellHousesRequest;
import com.toutiao.app.api.chance.request.sellhouse.SellHouseRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListResponse;
import com.toutiao.app.api.chance.response.sellhouse.*;
import com.toutiao.app.domain.sellhouse.*;

import com.toutiao.app.api.chance.request.sellhouse.SellHouseDetailsRequest;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSONArray.*;


@RestController
@RequestMapping("/rest/esf")
public class SellHouseRestController {


    @Autowired
    private SellHouseService sellHouseService;


    /**
     *  二手房房源详情
     * @param sellHouseDetailsRequest
     * @return
     */
    @RequestMapping(value = "/getSellHouseByHouseId",method = RequestMethod.GET)
    public NashResult getSellHouseByHouseId(@Validated SellHouseDetailsRequest sellHouseDetailsRequest) {
        SellHouseDetailsResponse sellHouseDetailsResponse = new SellHouseDetailsResponse();
        SellHouseDetailsDo sellHouseDetailsDo = sellHouseService.getSellHouseByHouseId(sellHouseDetailsRequest.getHouseId());
        BeanUtils.copyProperties(sellHouseDetailsDo, sellHouseDetailsResponse);
        return NashResult.build(sellHouseDetailsResponse);
    }

    /**
     * 二手房附近5km列表
     * @param nearBySellHousesRequest
     * @return
     */
    @RequestMapping("/getNearBySellHouses")
    @ResponseBody
    public NashResult getSellHouseByHouseIdAndLocation(NearBySellHousesRequest nearBySellHousesRequest) {

        NearBySellHousesDo nearBySellHousesDo=new NearBySellHousesDo();
        BeanUtils.copyProperties(nearBySellHousesRequest,nearBySellHousesDo);
       List<NearBySellHousesDo> nearBySellHousesDos =  sellHouseService.getSellHouseByHouseIdAndLocation(nearBySellHousesDo);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(nearBySellHousesDos));
        List<NearBySellHousesResponse> nearBySellHousesResponses=JSONObject.parseArray(json.toJSONString(),NearBySellHousesResponse.class);
        return NashResult.build(nearBySellHousesResponses);
    }

    /**
     * 认领二手房房源经纪人
     * @param agentSellHouseRequest
     * @return
     */
    @RequestMapping("/getAgentBySellHouseId")
    @ResponseBody
    public NashResult getAgentBySellHouseId(@Validated AgentSellHouseRequest agentSellHouseRequest) {
        AgentsBySellHouseResponse agentsBySellHouseResponse = new AgentsBySellHouseResponse();
        AgentsBySellHouseDo agentsBySellHouseDo = sellHouseService.getAgentByHouseId(agentSellHouseRequest.getHouseId());
        BeanUtils.copyProperties(agentsBySellHouseDo, agentsBySellHouseResponse);
        return NashResult.build(agentsBySellHouseResponse);
    }


    /**
     *  二手房房源列表
     * @param sellHouseRequest
     * @return
     */
    @RequestMapping("/getSellHouseByChoose")
    @ResponseBody
    public NashResult getSellHouse(@Validated SellHouseRequest sellHouseRequest) {
        ChooseSellHouseResponse chooseSellHouseResponse = new ChooseSellHouseResponse();
        ChooseSellHouseDo chooseSellHouseDo = new ChooseSellHouseDo();
        BeanUtils.copyProperties(sellHouseRequest, chooseSellHouseDo);
        ChooseSellHouseDomain chooseSellHouseDomain= sellHouseService.getSellHouseByChoose(chooseSellHouseDo);
        BeanUtils.copyProperties(chooseSellHouseDomain, chooseSellHouseResponse);
        return NashResult.build(chooseSellHouseResponse);
    }

}
