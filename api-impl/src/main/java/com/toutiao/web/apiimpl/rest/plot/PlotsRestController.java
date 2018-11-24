//package com.toutiao.web.apiimpl.rest.plot;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.toutiao.app.api.chance.request.BaseQueryRequest;
//import com.toutiao.app.api.chance.request.homepage.UserFavoriteConditionRequest;
//import com.toutiao.app.api.chance.request.plot.PlotAroundInfoRequest;
//import com.toutiao.app.api.chance.request.plot.PlotAroundPlotRequest;
//import com.toutiao.app.api.chance.request.plot.PlotDetailsRequest;
//import com.toutiao.app.api.chance.request.plot.PlotListRequest;
//import com.toutiao.app.api.chance.response.plot.*;
//import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
//import com.toutiao.app.domain.plot.*;
//import com.toutiao.app.service.plot.PlotsRestService;
//import com.toutiao.web.common.restmodel.NashResult;
//import com.toutiao.web.common.util.city.CityUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/rest/plot")
//public class PlotsRestController {
//    @Autowired
//    private PlotsRestService appPlotService;
//
//    /**
//     * 获取小区详情信息
//     * @param plotDetailsRequest
//     * @return
//     */
//    @RequestMapping(value = "/getPlotDetailByPlotId", method = RequestMethod.GET)
//    @ResponseBody
//    public PlotDetailsResponse getPlotDetailByPlotId(@Validated PlotDetailsRequest plotDetailsRequest) {
//        PlotDetailsDo plotDetailsDo = appPlotService.queryPlotDetailByPlotId(plotDetailsRequest.getPlotId(),CityUtils.getCity());
//        PlotDetailsResponse plotDetailsResponse = new PlotDetailsResponse();
//        BeanUtils.copyProperties(plotDetailsDo,plotDetailsResponse);
//        return plotDetailsResponse;
//    }
//
//    /**
//     * 获取周边小区
//     * @param plotAroundPlotRequest
//     * @return
//     */
//    @RequestMapping(value ="/getAroundPlotByLocation", method = RequestMethod.GET)
//    @ResponseBody
//    public PlotDetailsFewListResponse getPlotAroundByLocation(@Validated PlotAroundPlotRequest plotAroundPlotRequest){
//        List<PlotDetailsFewDo> plotDetailsFewDoList = appPlotService.queryAroundPlotByLocation(plotAroundPlotRequest.getLat(),
//                plotAroundPlotRequest.getLon(), plotAroundPlotRequest.getPlotId(),CityUtils.getCity());
//        JSONArray json = JSONArray.parseArray(JSON.toJSONString(plotDetailsFewDoList));
//        List<PlotDetailsFewResponse> plotDetailsFewResponseList = JSONObject.parseArray(json.toJSONString(), PlotDetailsFewResponse.class);
//        PlotDetailsFewListResponse plotDetailsFewListResponse = new PlotDetailsFewListResponse();
//        plotDetailsFewListResponse.setPlotDetailsFewResponseList(plotDetailsFewResponseList);
//        plotDetailsFewListResponse.setTotalNum(plotDetailsFewResponseList.size());
//        return plotDetailsFewListResponse;
//    }
//
//    /**
//     * 根据条件获取小区列表
//     * @param plotListRequest
//     * @return
//     */
//    @RequestMapping(value = "/getPlotListByRequirement",method = RequestMethod.GET)
//    @ResponseBody
//    public PlotListResponse getPlotListByRequirement(@Validated PlotListRequest plotListRequest){
//        PlotListDoQuery plotListDoQuery = new PlotListDoQuery();
//        BeanUtils.copyProperties(plotListRequest, plotListDoQuery);
//        PlotListDo plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery,CityUtils.getCity());
//        return JSON.parseObject(JSON.toJSONString(plotListDo), PlotListResponse.class);
//    }
//
//    /**
//     * 小区周边配套
//     * @param plotAroundInfoRequest
//     * @return
//     * @throws InvocationTargetException
//     * @throws IllegalAccessException
//     *  获取小区交通配套
//     */
//
//    @RequestMapping(value = "/getAroundInfoByPlotId",method = RequestMethod.GET)
//    @ResponseBody
//    public PlotTrafficResponse getAroundInfoByPlotId(@Validated PlotAroundInfoRequest plotAroundInfoRequest) throws InvocationTargetException, IllegalAccessException {
//        PlotTrafficResponse plotTrafficResponse=new PlotTrafficResponse();
//        PlotTrafficDo plotTrafficDo = appPlotService.queryPlotDataInfo(plotAroundInfoRequest.getPlotId());
//        BeanUtils.copyProperties(plotTrafficDo,plotTrafficResponse);
//        return plotTrafficResponse;
//    }
//
//
//    /**
//     * 小区top50查询
//     */
//    @RequestMapping(value = "/getTop50List",method = RequestMethod.GET)
//    @ResponseBody
//    public  PlotTop50ListResponse getTop50List(BaseQueryRequest baseQueryRequest)
//    {
//        List<PlotTop50Response> plotTop50Responses=new ArrayList<>();
//        PlotTop50ListDoQuery plotTop50ListDoQuery=new PlotTop50ListDoQuery();
//        BeanUtils.copyProperties(baseQueryRequest,plotTop50ListDoQuery);
//        List<PlotTop50Do> plotTop50Dos= appPlotService.getPlotTop50List(plotTop50ListDoQuery,CityUtils.getCity());
//        JSONArray json = JSONArray.parseArray(JSON.toJSONString(plotTop50Dos));
//        plotTop50Responses=JSONObject.parseArray(json.toJSONString(), PlotTop50Response.class);
//        PlotTop50ListResponse plotTop50ListResponse = new PlotTop50ListResponse();
//        plotTop50ListResponse.setPlotTop50ResponseList(plotTop50Responses);
//        plotTop50ListResponse.setTotalNum(plotTop50Responses.size());
//        return plotTop50ListResponse;
//    }
//
//    /**
//     * 获取推荐小区(返回值随机取4条)
//     */
//    @ResponseBody
//    @RequestMapping(value = "getPlotByRecommendCondition",method = RequestMethod.GET)
//    public PlotDetailsFewListResponse getPlotByRecommendCondition(UserFavoriteConditionRequest userFavoriteConditionRequest){
//        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
//        BeanUtils.copyProperties(userFavoriteConditionRequest,userFavoriteConditionDoQuery);
//        List<PlotDetailsDo> restlt = appPlotService.getPlotByRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
//        JSONArray json = JSONArray.parseArray(JSON.toJSONString(restlt));
//        List<PlotDetailsFewResponse> plotDetailsFewDos = JSONObject.parseArray(json.toJSONString(), PlotDetailsFewResponse.class);
//        PlotDetailsFewListResponse plotDetailsFewListResponse = new PlotDetailsFewListResponse();
//        plotDetailsFewListResponse.setPlotDetailsFewResponseList(plotDetailsFewDos);
//        plotDetailsFewListResponse.setTotalNum(plotDetailsFewDos.size());
//        return plotDetailsFewListResponse;
//    }
//
//}
