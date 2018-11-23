package com.toutiao.appV2.apiimpl.sellhouse;


import com.alibaba.fastjson.JSON;
import com.toutiao.app.domain.message.MessageSellHouseDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.appV2.api.sellhouse.SellHouseRestApi;
import com.toutiao.appV2.model.sellhouse.*;
import com.toutiao.web.common.constant.syserror.SellHouseInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.util.JSONUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@Slf4j
public class SellHouseRestController implements SellHouseRestApi {


    @Autowired
    private SellHouseService sellHouseService;
    private final HttpServletRequest request;

    @Autowired
    public SellHouseRestController(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 消息推送二手房列表信息
     *
     * @param houseId
     * @return
     */
    @Override
    public ResponseEntity<MessageSellHouseResponse> querySellHouseByHouseId(@ApiParam(value = "houseId", required = true) @RequestParam(value = "houseId", required = false) String houseId) {

                List<MessageSellHouseDo> messageSellHouseDos = sellHouseService.querySellHouseByHouseId(houseId.split(","));
                if (StringTool.isNotEmpty(messageSellHouseDos)) {
                    log.info("返回结果集:{}", JSONUtil.stringfy(messageSellHouseDos));
                    MessageSellHouseResponse messageSellHouseResponse = new MessageSellHouseResponse();
                    messageSellHouseResponse.setSellHouseList(messageSellHouseDos);
                    messageSellHouseResponse.setTotal(messageSellHouseDos.size());
                    return new ResponseEntity<MessageSellHouseResponse>(messageSellHouseResponse, HttpStatus.OK);
                } else {
                    throw new BaseException(SellHouseInterfaceErrorCodeEnum.ESF_NOT_FOUND);
                }
    }

    /**
     * 二手房房源详情
     *
     * @param sellHouseDerailsRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseDetailsResponse> getSellHouseByHouseId(@ApiParam(value = "sellHouseDerailsRequest", required = true) @Valid SellHouseDetailsRequest sellHouseDerailsRequest, BindingResult bindingResult) {
        SellHouseDetailsResponse sellHouseDetailsResponse = new SellHouseDetailsResponse();
        SellHouseDetailsDo sellHouseByHouse = sellHouseService.getSellHouseByHouseId(sellHouseDerailsRequest.getHouseId(), CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseByHouse, sellHouseDetailsResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseDetailsResponse));
        return new ResponseEntity<SellHouseDetailsResponse>(sellHouseDetailsResponse, HttpStatus.OK);
    }


//    /**
//     * 认领二手房房源经纪人
//     *
//     * @param agentSellHouseRequest
//     * @return
//     */
//    @Override
//    public ResponseEntity<AgentsBySellHouseResponse> getAgentBySellHouseId(@ApiParam(value = "agentSellHouseRequest", required = true) @Valid AgentSellHouseRequest agentSellHouseRequest, BindingResult bindingResult) {
//        AgentsBySellHouseResponse agentsBySellHouseResponse = new AgentsBySellHouseResponse();
//        AgentsBySellHouseDo agentsBySellHouseDo = sellHouseService.getAgentByHouseId(agentSellHouseRequest.getHouseId());
//        BeanUtils.copyProperties(agentsBySellHouseDo, agentsBySellHouseResponse);
//        log.info("返回结果集:{}", JSONUtil.stringfy(agentsBySellHouseResponse));
//        return new ResponseEntity<AgentsBySellHouseResponse>(agentsBySellHouseResponse, HttpStatus.OK);
//    }

    /**
     * 二手房房源默认列表
     *
     * @param userFavoriteConditionRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseResponse> getSellHouseByChoose(@ApiParam(value = "userFavoriteConditionRequest", required = true) @Valid UserFavoriteConditionRequest userFavoriteConditionRequest, BindingResult bindingResult) {
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = JSON.parseObject(JSON.toJSONString(userFavoriteConditionRequest), UserFavoriteConditionDoQuery.class);
        SellHouseDomain sellHouseDomain = sellHouseService.getSellHouseByChooseV1(userFavoriteConditionDoQuery, CityUtils.getCity());
        SellHouseResponse sellHouseResponse = JSON.parseObject(JSON.toJSONString(sellHouseDomain), SellHouseResponse.class);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseResponse));
        return new ResponseEntity<SellHouseResponse>(sellHouseResponse, HttpStatus.OK);
    }

    /**
     * 二手房默认列表推荐（广告）
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseResponse> getRecommendSellHouse(@ApiParam(value = "sellHouseRequest", required = true) @Valid @RequestBody SellHouseRequest sellHouseRequest, BindingResult bindingResult) {
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseService.getRecommendSellHouse(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain, sellHouseResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseResponse));
        return new ResponseEntity<SellHouseResponse>(sellHouseResponse, HttpStatus.OK);
    }

    /**
     * 二手房搜索结果列表
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseSearchDomainResponse> getSellHouseList(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult) {
        SellHouseSearchDomainResponse sellHouseSearchDomainResponse = new SellHouseSearchDomainResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getSellHouseList(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseSearchDomain, sellHouseSearchDomainResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseSearchDomainResponse));
        return new ResponseEntity<SellHouseSearchDomainResponse>(sellHouseSearchDomainResponse, HttpStatus.OK);

    }


    /**
     * 逢出必抢专题页
     *
     * @param sellHouseBeSureToSnatchRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseBeSureToSnatchResponse> getBeSureToSnatchList(@ApiParam(value = "sellHouseBeSureToSnatchRequest", required = true) @Valid SellHouseBeSureToSnatchRequest sellHouseBeSureToSnatchRequest, BindingResult bindingResult) {
        SellHouseBeSureToSnatchResponse sellHouseBeSureToSnatchResponses = new SellHouseBeSureToSnatchResponse();
        SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery = new SellHouseBeSureToSnatchDoQuery();
        BeanUtils.copyProperties(sellHouseBeSureToSnatchRequest, sellHouseBeSureToSnatchDoQuery);
        SellHouseBeSureToSnatchDomain sellHouseBeSureToSnatchDos = sellHouseService.getBeSureToSnatchList(sellHouseBeSureToSnatchDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseBeSureToSnatchDos, sellHouseBeSureToSnatchResponses);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseBeSureToSnatchResponses));
        return new ResponseEntity<SellHouseBeSureToSnatchResponse>(sellHouseBeSureToSnatchResponses, HttpStatus.OK);
    }

    /**
     * 获取推荐房源5条
     *
     * @param recommendEsf5Request
     * @return
     */
    @Override
    public ResponseEntity<SellHouseSearchDomainResponse> getRecommendEsf5(@ApiParam(value = "recommendEsf5Request", required = true) @Valid RecommendEsf5Request recommendEsf5Request, BindingResult bindingResult) {
        SellHouseSearchDomainResponse sellHouseSearchDomainResponse = new SellHouseSearchDomainResponse();
        RecommendEsf5DoQuery recommendEsf5DoQuery = new RecommendEsf5DoQuery();
        BeanUtils.copyProperties(recommendEsf5Request, recommendEsf5DoQuery);
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getRecommendEsf5(recommendEsf5DoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseSearchDomain, sellHouseSearchDomainResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseSearchDomainResponse));
        return new ResponseEntity<SellHouseSearchDomainResponse>(sellHouseSearchDomainResponse, HttpStatus.OK);
    }

    /**
     * 猜你喜欢:二手房列表
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseSearchDomainResponse> getGuessList(SellHouseRequest sellHouseRequest, BindingResult bindingResult) {
        SellHouseSearchDomainResponse sellHouseSearchDomainResponse = new SellHouseSearchDomainResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getSellHouseList(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseSearchDomain, sellHouseSearchDomainResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseSearchDomainResponse));
        return new ResponseEntity<SellHouseSearchDomainResponse>(sellHouseSearchDomainResponse, HttpStatus.OK);
    }

}
