package com.toutiao.web.apiimpl.rest.plot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.plot.PlotDetailsRequest;
import com.toutiao.app.api.chance.request.plot.PlotsRentRequest;
import com.toutiao.app.api.chance.request.rent.RentDetailsRequest;
import com.toutiao.app.api.chance.response.rent.RentDetailFewResponse;
import com.toutiao.app.api.chance.response.rent.RentDetailResponse;
import com.toutiao.app.api.chance.response.rent.RentDetailsListResponse;
import com.toutiao.app.api.chance.response.rent.RentNumListResponse;
import com.toutiao.app.domain.rent.RentDetailsDo;
import com.toutiao.app.domain.rent.RentDetailsFewDo;
import com.toutiao.app.domain.rent.RentDetailsListDo;
import com.toutiao.app.domain.rent.RentNumListDo;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public NashResult getRentListByPlotId(@Validated PlotsRentRequest plotsRentRequest,@RequestHeader("User-Agent") String userAgent, @CookieValue("select_city")String city){
        RentDetailsListDo rentDetailsListDo = appRentRestService.queryRentListByPlotId(plotsRentRequest.getPlotId(), plotsRentRequest.getRentType(), plotsRentRequest.getPageNum(),userAgent,city);
        RentDetailsListResponse rentDetailsListResponse = new RentDetailsListResponse();

        BeanUtils.copyProperties(rentDetailsListDo,rentDetailsListResponse);
        return NashResult.build(rentDetailsListResponse);
    }

    /**
     * 查询小区下出租房的个数
     * @param plotsRentRequest
     * @return
     */
    @RequestMapping(value = "/queryRentNumByPlotId",method = RequestMethod.GET)
    public NashResult getRentNumByPlotId(@Validated PlotsRentRequest plotsRentRequest,@RequestHeader("User-Agent") String userAgent, @CookieValue("select_city")String city){
        RentNumListDo rentNumListDo = appRentRestService.queryRentNumByPlotId(plotsRentRequest.getPlotId(),userAgent,city);
        RentNumListResponse rentNumListResponse = JSON.parseObject(JSON.toJSONString(rentNumListDo), RentNumListResponse.class);
        return NashResult.build(rentNumListResponse);
    }
}
