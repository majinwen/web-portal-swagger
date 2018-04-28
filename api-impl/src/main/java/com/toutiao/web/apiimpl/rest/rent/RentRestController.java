package com.toutiao.web.apiimpl.rest.rent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.rent.NearHouseListRequest;
import com.toutiao.app.api.chance.request.rent.RentDetailsRequest;
import com.toutiao.app.api.chance.request.rent.RentHouseRequest;
import com.toutiao.app.api.chance.response.rent.*;
import com.toutiao.app.domain.rent.*;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param nearHouseListRequest
     * @return
     */
    @RequestMapping(value = "/getNearRentHouseByLocation",method = RequestMethod.GET)
    public NashResult getNearRentHouseByLocation(@Validated NearHouseListRequest nearHouseListRequest){
        NearHouseDo nearHouseDo = new NearHouseDo();
        BeanUtils.copyProperties(nearHouseListRequest,nearHouseDo);
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
        RentDetailsListDo rentDetailsListDo = appRentRestService.getRentList(nearHouseDo);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(rentDetailsListDo);
        RentListResponse rentListResponse = JSONObject.parseObject(String.valueOf(jsonObject),RentListResponse.class);
        return NashResult.build(rentListResponse);
    }


    /**
     * 租房推优房源
     * @param rentHouseRequest
     * @return
     */
    @RequestMapping(value = "/getRecommendRent",method = RequestMethod.POST)
    @ResponseBody
    public NashResult getRecommendRent(@Validated(First.class) @RequestBody  RentHouseRequest rentHouseRequest){

        RentHouseDo rentHouseDo = new RentHouseDo();
        BeanUtils.copyProperties(rentHouseRequest,rentHouseDo);
        RentDetailsFewDo rentDetailsFewDo = appRentRestService.queryRecommendRent(rentHouseDo);
        RecommendRentResponse recommendRentResponse = new RecommendRentResponse();
        BeanUtils.copyProperties(rentDetailsFewDo,recommendRentResponse);
        return NashResult.build(recommendRentResponse);
    }


}
