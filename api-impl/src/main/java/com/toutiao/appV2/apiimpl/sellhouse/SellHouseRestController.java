package com.toutiao.appV2.apiimpl.sellhouse;


import com.alibaba.fastjson.JSON;
import com.toutiao.app.api.chance.request.homepage.UserFavoriteConditionRequest;
import com.toutiao.app.domain.message.MessageSellHouseDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.appV2.api.sellhouse.SellHouseRestApi;
import com.toutiao.appV2.model.sellhouse.*;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
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
    public ResponseEntity<List<MessageSellHouseDo>> querySellHouseByHouseId(@NotNull @ApiParam(value = "houseId", required = true) @Validated @RequestParam(value = "houseId", required = true) String houseId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                List<MessageSellHouseDo> messageSellHouseDos = sellHouseService.querySellHouseByHouseId(houseId.split(","));
                if (StringTool.isNotEmpty(messageSellHouseDos)) {
                    return new ResponseEntity<List<MessageSellHouseDo>>(messageSellHouseDos, HttpStatus.OK);
                } else {
                    return new ResponseEntity<List<MessageSellHouseDo>>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<List<MessageSellHouseDo>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<MessageSellHouseDo>>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 二手房房源详情
     *
     * @param sellHouseDerailsRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseDetailsResponse> getSellHouseByHouseId(@ApiParam(value = "sellHouseDerailsRequest", required = true) @Validated SellHouseDetailsRequest sellHouseDerailsRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SellHouseDetailsResponse sellHouseDetailsResponse = new SellHouseDetailsResponse();
                SellHouseDetailsDo sellHouseByHouse = sellHouseService.getSellHouseByHouseId(sellHouseDerailsRequest.getHouseId(), CityUtils.getCity());
                BeanUtils.copyProperties(sellHouseByHouse, sellHouseDetailsResponse);
                return new ResponseEntity<SellHouseDetailsResponse>(sellHouseDetailsResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<SellHouseDetailsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SellHouseDetailsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 认领二手房房源经纪人
     *
     * @param agentSellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<AgentsBySellHouseResponse> getAgentBySellHouseId(@ApiParam(value = "agentSellHouseRequest", required = true) @Validated AgentSellHouseRequest agentSellHouseRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                AgentsBySellHouseResponse agentsBySellHouseResponse = new AgentsBySellHouseResponse();
                AgentsBySellHouseDo agentsBySellHouseDo = sellHouseService.getAgentByHouseId(agentSellHouseRequest.getHouseId());
                BeanUtils.copyProperties(agentsBySellHouseDo, agentsBySellHouseResponse);
                return new ResponseEntity<AgentsBySellHouseResponse>(agentsBySellHouseResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<AgentsBySellHouseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<AgentsBySellHouseResponse>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 二手房房源默认列表
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseResponse> getSellHouseByChoose(@ApiParam(value = "sellHouseRequest", required = true) @Validated SellHouseRequest sellHouseRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SellHouseResponse sellHouseResponse = new SellHouseResponse();
                SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
                BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
                SellHouseDomain sellHouseDomain = sellHouseService.getSellHouseByChoose(sellHouseDoQuery, CityUtils.getCity());
                BeanUtils.copyProperties(sellHouseDomain, sellHouseResponse);
                return new ResponseEntity<SellHouseResponse>(sellHouseResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<SellHouseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SellHouseResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 二手房房源默认列表V1
     *
     * @param userFavoriteConditionRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseResponse> getSellHouseByChooseV1(@ApiParam(value = "userFavoriteConditionRequest", required = true) @Validated UserFavoriteConditionRequest userFavoriteConditionRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = JSON.parseObject(JSON.toJSONString(userFavoriteConditionRequest), UserFavoriteConditionDoQuery.class);
                SellHouseDomain sellHouseDomain = sellHouseService.getSellHouseByChooseV1(userFavoriteConditionDoQuery, CityUtils.getCity());
                SellHouseResponse sellHouseResponse = JSON.parseObject(JSON.toJSONString(sellHouseDomain), SellHouseResponse.class);
                return new ResponseEntity<SellHouseResponse>(sellHouseResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<SellHouseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SellHouseResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 二手房默认列表推荐（广告）
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseResponse> getRecommendSellHouse(@ApiParam(value = "sellHouseRequest", required = true) @Validated @RequestBody SellHouseRequest sellHouseRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SellHouseResponse sellHouseResponse = new SellHouseResponse();
                SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
                BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
                SellHouseDomain sellHouseDomain = sellHouseService.getRecommendSellHouse(sellHouseDoQuery, CityUtils.getCity());
                BeanUtils.copyProperties(sellHouseDomain, sellHouseResponse);
                return new ResponseEntity<SellHouseResponse>(sellHouseResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<SellHouseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SellHouseResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 二手房搜索结果列表
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseSearchDomainResponse> getSellHouseList(@ApiParam(value = "sellHouseRequest", required = true) @Validated SellHouseRequest sellHouseRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SellHouseSearchDomainResponse sellHouseSearchDomainResponse = new SellHouseSearchDomainResponse();

                SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
                BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
                SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getSellHouseList(sellHouseDoQuery, CityUtils.getCity());
                BeanUtils.copyProperties(sellHouseSearchDomain, sellHouseSearchDomainResponse);
                return new ResponseEntity<SellHouseSearchDomainResponse>(sellHouseSearchDomainResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<SellHouseSearchDomainResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SellHouseSearchDomainResponse>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 逢出必抢专题页
     *
     * @param sellHouseBeSureToSnatchRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseBeSureToSnatchResponse> getBeSureToSnatchList(@ApiParam(value = "sellHouseBeSureToSnatchRequest", required = true) @Validated SellHouseBeSureToSnatchRequest sellHouseBeSureToSnatchRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SellHouseBeSureToSnatchResponse sellHouseBeSureToSnatchResponses = new SellHouseBeSureToSnatchResponse();
                SellHouseBeSureToSnatchDoQuery sellHouseBeSureToSnatchDoQuery = new SellHouseBeSureToSnatchDoQuery();
                BeanUtils.copyProperties(sellHouseBeSureToSnatchRequest, sellHouseBeSureToSnatchDoQuery);
                SellHouseBeSureToSnatchDomain sellHouseBeSureToSnatchDos = sellHouseService.getBeSureToSnatchList(sellHouseBeSureToSnatchDoQuery, CityUtils.getCity());
                BeanUtils.copyProperties(sellHouseBeSureToSnatchDos, sellHouseBeSureToSnatchResponses);
                return new ResponseEntity<SellHouseBeSureToSnatchResponse>(sellHouseBeSureToSnatchResponses, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<SellHouseBeSureToSnatchResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SellHouseBeSureToSnatchResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 获取推荐房源5条
     *
     * @param recommendEsf5Request
     * @return
     */
    @Override
    public ResponseEntity<SellHouseSearchDomainResponse> getRecommendEsf5(@ApiParam(value = "recommendEsf5Request", required = true) @Validated RecommendEsf5Request recommendEsf5Request) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SellHouseSearchDomainResponse sellHouseSearchDomainResponse = new SellHouseSearchDomainResponse();
                RecommendEsf5DoQuery recommendEsf5DoQuery = new RecommendEsf5DoQuery();
                BeanUtils.copyProperties(recommendEsf5Request, recommendEsf5DoQuery);
                SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getRecommendEsf5(recommendEsf5DoQuery, CityUtils.getCity());
                BeanUtils.copyProperties(sellHouseSearchDomain, sellHouseSearchDomainResponse);
                return new ResponseEntity<SellHouseSearchDomainResponse>(HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<SellHouseSearchDomainResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SellHouseSearchDomainResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
