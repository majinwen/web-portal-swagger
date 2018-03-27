package com.toutiao.web.apiimpl.rest.sellhouse;


import com.toutiao.app.api.chance.request.sellhouse.AgentSellHouseRequest;
import com.toutiao.app.api.chance.request.sellhouse.NearBySellHousesRequest;
import com.toutiao.app.api.chance.request.sellhouse.SellHouseRequest;
import com.toutiao.app.api.chance.response.sellhouse.*;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.app.api.chance.request.sellhouse.SellHouseDetailsRequest;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


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
    @RequestMapping("/getSellHouseByHouseId")
    @ResponseBody
    public NashResult getSellHouseByHouseId(@Validated SellHouseDetailsRequest sellHouseDetailsRequest) {
        SellHouseDetailsResponse sellHouseDetailsResponse = new SellHouseDetailsResponse();
        SellHouseDetailsDo sellHouseDetailsDo = sellHouseService.getSellHouseByHouseId(sellHouseDetailsRequest.getHouseId());
        BeanUtils.copyProperties(sellHouseDetailsDo, sellHouseDetailsResponse);
        return NashResult.build(sellHouseDetailsResponse);
    }

    /**
     * 二手房附近好房列表
     * @param nearBySellHousesRequest
     * @return
     */
    @RequestMapping("/getNearBySellHouses")
    @ResponseBody
    public NashResult getSellHouseByhouseIdAndLocation(@Validated NearBySellHousesRequest nearBySellHousesRequest) {
        List<NearBySellHousesResponse> nearBySellHousesResponses = new ArrayList<>();
        List<NearBySellHousesDo> nearBySellHousesDos =  sellHouseService.getSellHouseByhouseIdAndLocation(nearBySellHousesRequest.getNewhouse(),nearBySellHousesRequest.getLat(),
                nearBySellHousesRequest.getLon(),nearBySellHousesRequest.getDistance());
        BeanUtils.copyProperties(nearBySellHousesDos, nearBySellHousesResponses);
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
