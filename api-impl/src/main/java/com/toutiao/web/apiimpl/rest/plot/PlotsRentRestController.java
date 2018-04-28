package com.toutiao.web.apiimpl.rest.plot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.plot.PlotDetailsRequest;
import com.toutiao.app.api.chance.request.plot.PlotsRentRequest;
import com.toutiao.app.api.chance.request.rent.RentDetailsRequest;
import com.toutiao.app.api.chance.response.rent.RentDetailFewResponse;
import com.toutiao.app.api.chance.response.rent.RentDetailResponse;
import com.toutiao.app.api.chance.response.rent.RentNumListResponse;
import com.toutiao.app.domain.rent.RentDetailsDo;
import com.toutiao.app.domain.rent.RentDetailsFewDo;
import com.toutiao.app.domain.rent.RentNumListDo;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/plot/rent")
public class PlotsRentRestController {
    @Autowired
    private RentRestService appRentRestService;

    /**
     * 查询小区下出租房
     * @param plotsRentRequest
     * @return
     */
    @RequestMapping(value = "/getRentOfPlotByPlotId",method = RequestMethod.GET)
    public NashResult getRentListByPlotId(@Validated PlotsRentRequest plotsRentRequest){
        List<RentDetailsFewDo> rentDetailsFewDoList = appRentRestService.queryRentListByPlotId(plotsRentRequest.getPlotId(),plotsRentRequest.getRentType(),plotsRentRequest.getPageNum());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(rentDetailsFewDoList));
        List<RentDetailFewResponse> rentDetailFewResponses = JSONObject.parseArray(json.toJSONString(), RentDetailFewResponse.class);
        return NashResult.build(rentDetailFewResponses);
    }

    /**
     * 查询小区下出租房的个数
     * @param plotsRentRequest
     * @return
     */
    @RequestMapping(value = "/queryRentNumByPlotId",method = RequestMethod.GET)
    public NashResult getRentNumByPlotId(@Validated PlotsRentRequest plotsRentRequest){
        RentNumListResponse rentNumResponses = new RentNumListResponse();
        RentNumListDo rentNumListDo = appRentRestService.queryRentNumByPlotId(plotsRentRequest.getPlotId());
        BeanUtils.copyProperties(rentNumListDo,rentNumResponses);
        return NashResult.build(rentNumResponses);
    }
}
