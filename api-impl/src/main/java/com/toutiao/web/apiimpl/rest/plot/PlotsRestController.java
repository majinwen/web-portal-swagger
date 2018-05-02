package com.toutiao.web.apiimpl.rest.plot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.plot.PlotAroundInfoRequest;
import com.toutiao.app.api.chance.request.plot.PlotAroundPlotRequest;
import com.toutiao.app.api.chance.request.plot.PlotDetailsRequest;
import com.toutiao.app.api.chance.request.plot.PlotListRequest;
import com.toutiao.app.api.chance.response.plot.PlotDetailsFewResponse;
import com.toutiao.app.api.chance.response.plot.PlotDetailsResponse;
import com.toutiao.app.api.chance.response.plot.PlotListResponse;
import com.toutiao.app.api.chance.response.plot.PlotTrafficResponse;
import com.toutiao.app.domain.plot.*;
import com.toutiao.app.service.plot.PlotsRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


@RestController
@RequestMapping("/rest/plot")
public class PlotsRestController {
    @Autowired
    private PlotsRestService appPlotService;

    /**
     * 获取小区详情信息
     * @param plotDetailsRequest
     * @return
     */
    @RequestMapping("/getPlotDetailByPlotId")
    @ResponseBody
    public NashResult getPlotDetailByPlotId(@Validated PlotDetailsRequest plotDetailsRequest) {
        PlotDetailsDo plotDetailsDo = appPlotService.queryPlotDetailByPlotId(plotDetailsRequest.getPlotId());
        PlotDetailsResponse plotDetailsResponse = new PlotDetailsResponse();
        BeanUtils.copyProperties(plotDetailsDo,plotDetailsResponse);
        return NashResult.build(plotDetailsResponse);
    }

    /**
     * 获取周边小区
     * @param plotAroundPlotRequest
     * @return
     */
    @RequestMapping("/getAroundPlotByLocation")
    @ResponseBody
    public NashResult getPlotAroundByLocation(@Validated PlotAroundPlotRequest plotAroundPlotRequest){
        List<PlotDetailsFewDo> plotDetailsFewDoList = appPlotService.queryAroundPlotByLocation(plotAroundPlotRequest.getLat(), plotAroundPlotRequest.getLon(), plotAroundPlotRequest.getPlotId());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(plotDetailsFewDoList));
        List<PlotDetailsFewResponse> plotDetailsFewResponseList = JSONObject.parseArray(json.toJSONString(), PlotDetailsFewResponse.class);
        return NashResult.build(plotDetailsFewResponseList);
    }

    /**
     * 根据条件获取小区列表
     * @param plotListRequest
     * @return
     */
    @RequestMapping(value = "/getPlotListByRequirement",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getPlotListByRequirement(@Validated PlotListRequest plotListRequest){
        PlotListDoQuery plotListDoQuery = new PlotListDoQuery();
        BeanUtils.copyProperties(plotListRequest, plotListDoQuery);
        PlotListDo plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery);
        PlotListResponse plotListResponse = JSON.parseObject(JSON.toJSONString(plotListDo), PlotListResponse.class);
        return NashResult.build(plotListResponse);
    }

    /**
     *
     * @param plotAroundInfoRequest
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     *  获取小区交通配套
     */

    @RequestMapping(value = "/getAroundInfoByPlotId",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getAroundInfoByPlotId(@Validated PlotAroundInfoRequest plotAroundInfoRequest) throws InvocationTargetException, IllegalAccessException {
        PlotTrafficResponse plotTrafficResponse=new PlotTrafficResponse();
        PlotTrafficDo plotTrafficDo = appPlotService.queryPlotDataInfo(plotAroundInfoRequest.getPlotId());
        BeanUtils.copyProperties(plotTrafficDo,plotTrafficResponse);
        return NashResult.build(plotTrafficResponse);
    }


    }
