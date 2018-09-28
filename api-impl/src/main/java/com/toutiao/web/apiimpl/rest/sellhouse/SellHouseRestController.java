package com.toutiao.web.apiimpl.rest.sellhouse;


import com.alibaba.fastjson.JSON;
import com.toutiao.app.api.chance.request.homepage.UserFavoriteConditionRequest;
import com.toutiao.app.api.chance.request.sellhouse.*;
import com.toutiao.app.api.chance.response.sellhouse.*;
import com.toutiao.app.domain.message.MessageSellHouseDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import org.apache.ibatis.annotations.Param;
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
     * 消息推送二手房列表信息
     */
    @RequestMapping(value = "/querySellHouseByHouseId",method = RequestMethod.GET)
    public NashResult querySellHouseByHouseId(@Param("houseId") String houseId){
        List<MessageSellHouseDo> messageSellHouseDos = sellHouseService.querySellHouseByHouseId(houseId.split(","));
        if (StringTool.isNotEmpty(messageSellHouseDos)) {
            return NashResult.build(messageSellHouseDos);
        } else {
            return NashResult.Fail("获取消息推送失败");
        }
    }

    /**
     *  二手房房源详情
     * @param sellHouseDerailsRequest
     * @return
     */
    @RequestMapping(value = "/getSellHouseByHouseId",method = RequestMethod.GET)
    public NashResult getSellHouseByHouseId(@Validated SellHouseDerailsRequest sellHouseDerailsRequest) {
        SellHouseDetailsResponse sellHouseDetailsResponse = new SellHouseDetailsResponse();
        SellHouseDetailsDo sellHouseByHouse = sellHouseService.getSellHouseByHouseId(sellHouseDerailsRequest.getHouseId(),CityUtils.getCity());
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
        SellHouseDomain sellHouseDomain = sellHouseService.getSellHouseByChoose(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);

        return NashResult.build(sellHouseResponse);
    }
    /**
     *  二手房房源默认列表V1
     * @param userFavoriteConditionRequest
     * @return
     */
    @RequestMapping("/getSellHouseByChooseV1")
    @ResponseBody
    public NashResult getSellHouseV1(UserFavoriteConditionRequest userFavoriteConditionRequest){
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = JSON.parseObject(JSON.toJSONString(userFavoriteConditionRequest), UserFavoriteConditionDoQuery.class);
        SellHouseDomain sellHouseDomain = sellHouseService.getSellHouseByChooseV1(userFavoriteConditionDoQuery, CityUtils.getCity());
        SellHouseResponse sellHouseResponse = JSON.parseObject(JSON.toJSONString(sellHouseDomain), SellHouseResponse.class);
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
        SellHouseDomain sellHouseDomain = sellHouseService.getRecommendSellHouse(sellHouseDoQuery, CityUtils.getCity());
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
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getSellHouseList(sellHouseDoQuery,CityUtils.getCity());
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
        SellHouseBeSureToSnatchResponse sellHouseBeSureToSnatchResponses=new SellHouseBeSureToSnatchResponse();
        SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery=new SellHouseBeSureToSnatchDoQuery();
        BeanUtils.copyProperties(sellHouseBeSureToSnatchRequest,sellHouseBeSureToSnatchDoQuery);
        SellHouseBeSureToSnatchDomain sellHouseBeSureToSnatchDos= sellHouseService.getBeSureToSnatchList(sellHouseBeSureToSnatchDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseBeSureToSnatchDos,sellHouseBeSureToSnatchResponses);
        return  NashResult.build(sellHouseBeSureToSnatchResponses);
    }

    /**
     * 获取推荐房源5条
     *
     * @param recommendEsf5Request
     * @return
     */
    @RequestMapping(value = "/getRecommendEsf5", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getRecommendEsf5(RecommendEsf5Request recommendEsf5Request) {
        SellHouseSearchDomainResponse sellHouseSearchDomainResponse = new SellHouseSearchDomainResponse();
        RecommendEsf5DoQuery recommendEsf5DoQuery = new RecommendEsf5DoQuery();
        BeanUtils.copyProperties(recommendEsf5Request, recommendEsf5DoQuery);
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getRecommendEsf5(recommendEsf5DoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseSearchDomain, sellHouseSearchDomainResponse);
        return NashResult.build(sellHouseSearchDomainResponse);
    }

}
