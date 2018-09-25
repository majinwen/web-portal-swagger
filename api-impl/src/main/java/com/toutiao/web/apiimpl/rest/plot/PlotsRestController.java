package com.toutiao.web.apiimpl.rest.plot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.BaseQueryRequest;
import com.toutiao.app.api.chance.request.homepage.UserFavoriteConditionRequest;
import com.toutiao.app.api.chance.request.plot.PlotAroundInfoRequest;
import com.toutiao.app.api.chance.request.plot.PlotAroundPlotRequest;
import com.toutiao.app.api.chance.request.plot.PlotDetailsRequest;
import com.toutiao.app.api.chance.request.plot.PlotListRequest;
import com.toutiao.app.api.chance.response.plot.*;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.plot.*;
import com.toutiao.app.service.plot.PlotsRestService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.city.CityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
        PlotDetailsDo plotDetailsDo = appPlotService.queryPlotDetailByPlotId(plotDetailsRequest.getPlotId(),CityUtils.getCity());
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
        List<PlotDetailsFewDo> plotDetailsFewDoList = appPlotService.queryAroundPlotByLocation(plotAroundPlotRequest.getLat(),
                plotAroundPlotRequest.getLon(), plotAroundPlotRequest.getPlotId(),CityUtils.getCity());
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
        PlotListDo plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery,CityUtils.getCity());
        PlotListResponse plotListResponse = JSON.parseObject(JSON.toJSONString(plotListDo), PlotListResponse.class);
        return NashResult.build(plotListResponse);
    }

    /**
     * 小区周边配套
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


    /**
     * 小区top50查询
     */
    @RequestMapping(value = "/getTop50List",method = RequestMethod.GET)
    @ResponseBody
    public  NashResult getTop50List(BaseQueryRequest baseQueryRequest)
    {
        List<PlotTop50Response> plotTop50Responses=new ArrayList<>();
        PlotTop50ListDoQuery plotTop50ListDoQuery=new PlotTop50ListDoQuery();
        BeanUtils.copyProperties(baseQueryRequest,plotTop50ListDoQuery);
        List<PlotTop50Do> plotTop50Dos= appPlotService.getPlotTop50List(plotTop50ListDoQuery,CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(plotTop50Dos));
        plotTop50Responses=JSONObject.parseArray(json.toJSONString(), PlotTop50Response.class);
        return  NashResult.build(plotTop50Responses);
    }

    /**
     * 获取推荐小区(返回值随机取4条)
     */
    @ResponseBody
    @RequestMapping(value = "getPlotByRecommendCondition",method = RequestMethod.GET)
    public NashResult getPlotByRecommendCondition(UserFavoriteConditionRequest userFavoriteConditionRequest){
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest,userFavoriteConditionDoQuery);
        List<PlotDetailsDo> restlt = appPlotService.getPlotByRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(restlt));
        List<PlotDetailsFewDo> plotDetailsFewDos = JSONObject.parseArray(json.toJSONString(), PlotDetailsFewDo.class);
        return NashResult.build(plotDetailsFewDos);
    }

}
