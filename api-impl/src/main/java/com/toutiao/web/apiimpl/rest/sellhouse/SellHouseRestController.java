package com.toutiao.web.apiimpl.rest.sellhouse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.sellhouse.AgentSellHouseRequest;
import com.toutiao.app.api.chance.request.sellhouse.SellHouseBeSureToSnatchRequest;
import com.toutiao.app.api.chance.request.sellhouse.SellHouseDerailsRequest;
import com.toutiao.app.api.chance.request.sellhouse.SellHouseRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHouseDynamicResponse;
import com.toutiao.app.api.chance.response.sellhouse.*;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/esf")
public class SellHouseRestController {


    @Autowired
    private SellHouseService sellHouseService;


    /**
     *  二手房房源详情
     * @param sellHouseDerailsRequest
     * @return
     */
    @RequestMapping(value = "/getSellHouseByHouseId",method = RequestMethod.GET)
    public NashResult getSellHouseByHouseId(@Validated SellHouseDerailsRequest sellHouseDerailsRequest) {
        SellHouseDetailsResponse sellHouseDetailsResponse = new SellHouseDetailsResponse();
        SellHouseDetailsDo sellHouseByHouse = sellHouseService.getSellHouseByHouseId(sellHouseDerailsRequest.getHouseId());
        BeanUtils.copyProperties(sellHouseByHouse, sellHouseDetailsResponse);
        return NashResult.build(sellHouseDetailsResponse);
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
     *  二手房房源默认列表
     * @param sellHouseRequest
     * @return
     */
    @RequestMapping("/getSellHouseByChoose")
    @ResponseBody
    public NashResult getSellHouse(@Validated SellHouseRequest sellHouseRequest) {
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseService.getSellHouseByChoose(sellHouseDoQuery);
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);

        return NashResult.build(sellHouseResponse);
    }

    /**
     * 二手房默认列表推荐（广告）
     * @param sellHouseRequest
     * @return
     */
    @RequestMapping(value ="/getRecommendSellHouse", method = RequestMethod.POST)
    @ResponseBody
    public NashResult getRecommendSellHouse(@Validated(First.class) @RequestBody SellHouseRequest sellHouseRequest) {
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseService.getRecommendSellHouse(sellHouseDoQuery);
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);

        return NashResult.build(sellHouseResponse);
    }

    /**
     * 二手房搜索结果列表
     * @param sellHouseRequest
     * @return
     */
    @RequestMapping("/getSellHouseList")
    @ResponseBody
    public NashResult getSellHouseList(@Validated SellHouseRequest sellHouseRequest) {

        SellHouseSearchDomainResponse sellHouseSearchDomainResponse =  new SellHouseSearchDomainResponse();

        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getSellHouseList(sellHouseDoQuery);
        BeanUtils.copyProperties(sellHouseSearchDomain,sellHouseSearchDomainResponse);
        return NashResult.build(sellHouseSearchDomainResponse);
    }


    /**
     * 缝出必抢专题页
     */
    @RequestMapping(value = "/getBeSureToSnatchList",method = RequestMethod.GET)
    @ResponseBody
    public  NashResult getBeSureToSnatchList(SellHouseBeSureToSnatchRequest sellHouseBeSureToSnatchRequest)
    {

        SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery=new SellHouseBeSureToSnatchDoQuery();
        BeanUtils.copyProperties(sellHouseBeSureToSnatchRequest,sellHouseBeSureToSnatchDoQuery);
        List<SellHouseBeSureToSnatchDo> sellHouseBeSureToSnatchDos= sellHouseService.getBeSureToSnatchList(sellHouseBeSureToSnatchDoQuery);
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(sellHouseBeSureToSnatchDos));
        List<SellHouseBeSureToSnatchResponse> sellHouseBeSureToSnatchResponses= JSONObject.parseArray(json.toJSONString(), SellHouseBeSureToSnatchResponse.class);
        return  NashResult.build(sellHouseBeSureToSnatchResponses);
    }

}
