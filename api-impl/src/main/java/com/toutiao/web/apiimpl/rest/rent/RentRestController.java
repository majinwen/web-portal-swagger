package com.toutiao.web.apiimpl.rest.rent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.plot.PlotDetailsRequest;
import com.toutiao.app.api.chance.request.rent.NearHouseRequest;
import com.toutiao.app.api.chance.request.rent.RentDetailsRequest;
import com.toutiao.app.api.chance.response.rent.RentAgentResponse;
import com.toutiao.app.api.chance.response.rent.RentDetailFewResponse;
import com.toutiao.app.api.chance.response.rent.RentDetailResponse;
import com.toutiao.app.domain.rent.RentAgentDo;
import com.toutiao.app.domain.rent.RentDetailsDo;
import com.toutiao.app.domain.rent.RentDetailsFewDo;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/rent")
public class RentRestController {
    @Autowired
    private RentRestService appRentRestService;

    /**
     * 查找出租房源详情信息
     * @param rentDetailsRequest
     * @return
     */
    @RequestMapping(value = "getRentDetailByRentId",method = RequestMethod.GET)
    public NashResult getRentDetailByRentId(@Validated RentDetailsRequest rentDetailsRequest){
        RentDetailsDo rentDetailsDo = appRentRestService.queryRentDetailByHouseId(rentDetailsRequest.getRentId());
        RentDetailResponse rentDetailResponse = new RentDetailResponse();
        BeanUtils.copyProperties(rentDetailsDo,rentDetailResponse);
        return NashResult.build(rentDetailResponse);
    }

    /**
     * 查询小区下出租房
     * @param plotDetailsRequest
     * @return
     */
    @RequestMapping(value = "getRentOfPlotByPlotId",method = RequestMethod.GET)
    public NashResult getRentListByPlotId(@Validated PlotDetailsRequest plotDetailsRequest){
        List<RentDetailsFewDo> rentDetailsFewDoList = appRentRestService.queryRentListByPlotId(plotDetailsRequest.getPlotId(),plotDetailsRequest.getRentType(),plotDetailsRequest.getPageNum());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(rentDetailsFewDoList));
        List<RentDetailFewResponse> rentDetailFewResponses = JSONObject.parseArray(json.toJSONString(), RentDetailFewResponse.class);
        return NashResult.build(rentDetailFewResponses);
    }

    /**
     * 根据id获取该出租房源对应的经纪人
     * @param rentDetailsRequest
     * @return
     */
    @RequestMapping(value = "getRentAgentByRentId",method = RequestMethod.GET)
    public NashResult getRentAgentByRentId(@Validated RentDetailsRequest rentDetailsRequest){
        RentAgentDo rentAgentDo = appRentRestService.queryRentAgentByRentId(rentDetailsRequest.getRentId());
        RentAgentResponse rentAgentResponse = new RentAgentResponse();
        BeanUtils.copyProperties(rentAgentDo,rentAgentResponse);
        return NashResult.build(rentAgentResponse);
    }

    /**
     * 附近5km出租房源(app的是吧，那就优先三公里的录入房源由近到远)
     * @param nearHouseRequest
     * @return
     */
    @RequestMapping(value = "getNearRentHouseByLocation",method = RequestMethod.GET)
    public NashResult getNearRentHouseByLocation(@Validated NearHouseRequest nearHouseRequest){
        List<RentDetailsFewDo> list = appRentRestService.queryNearHouseByLocation(nearHouseRequest.getLat(), nearHouseRequest.getLon(), nearHouseRequest.getPageNum());
        JSONArray objects = JSONArray.parseArray(JSON.toJSONString(list));
        List<RentDetailFewResponse> rentDetailFewResponses = JSONObject.parseArray(objects.toJSONString(), RentDetailFewResponse.class);
        return NashResult.build(rentDetailFewResponses);
    }

}
