package com.toutiao.web.apiimpl.rest.rent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.plot.PlotDetailsRequest;
import com.toutiao.app.api.chance.request.rent.NearHouseRequest;
import com.toutiao.app.api.chance.request.rent.RecommendRentRequest;
import com.toutiao.app.api.chance.request.rent.RentDetailsRequest;
import com.toutiao.app.api.chance.request.rent.RentHouseRequest;
import com.toutiao.app.api.chance.response.rent.*;
import com.toutiao.app.domain.rent.*;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/getRentDetailByRentId",method = RequestMethod.GET)
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
    @RequestMapping(value = "/getRentOfPlotByPlotId",method = RequestMethod.GET)
    public NashResult getRentListByPlotId(@Validated PlotDetailsRequest plotDetailsRequest){
        List<RentDetailsFewDo> rentDetailsFewDoList = appRentRestService.queryRentListByPlotId(plotDetailsRequest.getPlotId(),plotDetailsRequest.getRentType(),plotDetailsRequest.getPageNum());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(rentDetailsFewDoList));
        List<RentDetailFewResponse> rentDetailFewResponses = JSONObject.parseArray(json.toJSONString(), RentDetailFewResponse.class);
        return NashResult.build(rentDetailFewResponses);
    }

    /**
     * 查询小区下出租房的个数
     * @param plotId
     * @return
     */
    @RequestMapping(value = "/queryRentNumByPlotId",method = RequestMethod.GET)
    public NashResult getRentNumByPlotId(@RequestParam(value = "plotId",required =true) Integer plotId){
        RentNumListResponse rentNumResponses = new RentNumListResponse();
        RentNumListDo rentNumListDo = appRentRestService.queryRentNumByPlotId(plotId);
        BeanUtils.copyProperties(rentNumListDo,rentNumResponses);
        return NashResult.build(rentNumResponses);
    }

    /**
     * 根据id获取该出租房源对应的经纪人
     * @param rentDetailsRequest
     * @return
     */
    @RequestMapping(value = "/getRentAgentByRentId",method = RequestMethod.GET)
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
    @RequestMapping(value = "/getNearRentHouseByLocation",method = RequestMethod.GET)
    public NashResult getNearRentHouseByLocation(@Validated NearHouseRequest nearHouseRequest){
        NearHouseDo nearHouseDo = new NearHouseDo();
        BeanUtils.copyProperties(nearHouseRequest,nearHouseDo);
        List<RentDetailsFewDo> list = appRentRestService.queryNearHouseByLocation(nearHouseDo);
        JSONArray objects = JSONArray.parseArray(JSON.toJSONString(list));
        List<RentDetailFewResponse> rentDetailFewResponses = JSONObject.parseArray(objects.toJSONString(), RentDetailFewResponse.class);
        return NashResult.build(rentDetailFewResponses);
    }

    /**
     * 租房推进列表
     * @param rentHouseRequest
     * @return
     */
    @RequestMapping(value = "/getRentList",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getRentList(@Validated RentHouseRequest rentHouseRequest){
        NearHouseDo nearHouseDo = new NearHouseDo();
        BeanUtils.copyProperties(rentHouseRequest,nearHouseDo);
        RentDetailsDoList rentDetailsDoList = appRentRestService.getRentList(nearHouseDo);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(rentDetailsDoList);
        RentDetailResponseList rentDetailResponseList = JSONObject.parseObject(String.valueOf(jsonObject),RentDetailResponseList.class);
        return NashResult.build(rentDetailResponseList);
    }




    @RequestMapping(value = "/getRecommendRent",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getRecommendRent(@Validated(First.class) RentHouseRequest rentHouseRequest){
//        NearHouseDo nearHouseDo = new NearHouseDo();
//        BeanUtils.copyProperties(rentHouseRequest,nearHouseDo);
//        RentDetailsDoList rentDetailsDoList = appRentRestService.getRentList(nearHouseDo);
//        JSONObject jsonObject = (JSONObject) JSON.toJSON(rentDetailsDoList);
//        RentDetailResponseList rentDetailResponseList = JSONObject.parseObject(String.valueOf(jsonObject),RentDetailResponseList.class);

        RentHouseDo rentHouseDo = new RentHouseDo();
        BeanUtils.copyProperties(rentHouseRequest,rentHouseDo);
        RentDetailsFewDo rentDetailsFewDo = appRentRestService.queryRecommendRent(rentHouseDo);

        return NashResult.build("");
    }


}
