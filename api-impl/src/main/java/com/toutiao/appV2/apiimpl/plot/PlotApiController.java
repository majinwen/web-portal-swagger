package com.toutiao.appV2.apiimpl.plot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.plot.*;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.app.domain.rent.RentDetailsListDo;
import com.toutiao.app.domain.rent.RentNumListDo;
import com.toutiao.app.domain.sellhouse.SellAndClaimHouseDetailsDo;
import com.toutiao.app.service.plot.*;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.appV2.api.plot.PlotApi;
import com.toutiao.appV2.model.plot.*;
import com.toutiao.appV2.model.plot.PlotsHousesDomain;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:42:20.134Z")

@Controller
public class PlotApiController implements PlotApi {

    private static final Logger log = LoggerFactory.getLogger(PlotApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private HotPlotsRestService hotPlotsRestService;

    @Autowired
    private NearbyPlotsRestService nearbyPlotsRestService;

    @Autowired
    private PlotsEsfRestService plotsEsfRestService;

    @Autowired
    private RentRestService appRentRestService;

    @Autowired
    private PlotsThemeRestService plotsThemeRestService;

    @Autowired
    private PlotsRestService appPlotService;

    @org.springframework.beans.factory.annotation.Autowired
    public PlotApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<PlotTrafficResponse> getAroundInfoByPlotId(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId) throws InvocationTargetException, IllegalAccessException {

        PlotTrafficResponse plotTrafficResponse = new PlotTrafficResponse();
        PlotTrafficDo plotTrafficDo = appPlotService.queryPlotDataInfo(plotId.get());
        BeanUtils.copyProperties(plotTrafficDo, plotTrafficResponse);
        return new ResponseEntity<PlotTrafficResponse>(plotTrafficResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotEsfListResponse> getEsfByPlotsIdAndRoom(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId, @ApiParam(value = "") @Valid @RequestParam(value = "room", required = false) Optional<Integer> room, @ApiParam(value = "") @Valid @RequestParam(value = "pageNum", required = false) Optional<Integer> pageNum, @ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Optional<Integer> pageSize) {

        Integer plotsId = plotId.get();
        Integer roomQuery = room.get();
        Integer pageNumQuery = pageNum.get();
        Integer pageSizeQuery = pageSize.get();
        List<SellAndClaimHouseDetailsDo> sellHouseDoList = plotsEsfRestService.getEsfByPlotsIdAndRoom(plotsId, roomQuery, pageNumQuery, pageSizeQuery, CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(sellHouseDoList));
        List<PlotEsfResponse> plotEsfResponses = JSONObject.parseArray(json.toJSONString(), PlotEsfResponse.class);
        PlotEsfListResponse plotEsfListResponse = new PlotEsfListResponse();
        plotEsfListResponse.setPlotEsfResponseList(plotEsfResponses);
        plotEsfListResponse.setTotalNum(plotEsfResponses.size());
        return new ResponseEntity<PlotEsfListResponse>(plotEsfListResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<NearbyPlotsListResponse> getNearbyPlotsList(@ApiParam(value = "plotListRequest") @Valid PlotListRequest plotListRequest) {
        NearbyPlotsDoQuery nearbyPlotsDoQuery = new NearbyPlotsDoQuery();
        BeanUtils.copyProperties(plotListRequest,nearbyPlotsDoQuery);
        PlotDetailsFewDomain plotDetailsFewDomain = nearbyPlotsRestService.queryNearbyPlotsListByUserCoordinate(nearbyPlotsDoQuery, CityUtils.getCity());
        NearbyPlotsListResponse newHouseLayoutCountResponse = new NearbyPlotsListResponse();
        BeanUtils.copyProperties(plotDetailsFewDomain, newHouseLayoutCountResponse);

        return new ResponseEntity<NearbyPlotsListResponse>(newHouseLayoutCountResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotDetailsFewListResponse> getPlotAroundByLocation(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId, @ApiParam(value = "") @Valid @RequestParam(value = "lat", required = false) Optional<Double> lat, @ApiParam(value = "") @Valid @RequestParam(value = "lon", required = false) Optional<Double> lon) {

        List<PlotDetailsFewDo> plotDetailsFewDoList = appPlotService.queryAroundPlotByLocation(lat.get(),
                lon.get(), plotId.get(), CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(plotDetailsFewDoList));
        List<PlotDetailsFewResponse> plotDetailsFewResponseList = JSONObject.parseArray(json.toJSONString(), PlotDetailsFewResponse.class);
        PlotDetailsFewListResponse plotDetailsFewListResponse = new PlotDetailsFewListResponse();
        plotDetailsFewListResponse.setPlotDetailsFewResponseList(plotDetailsFewResponseList);
        plotDetailsFewListResponse.setTotalNum(plotDetailsFewResponseList.size());
        return new ResponseEntity<PlotDetailsFewListResponse>(plotDetailsFewListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotDetailsFewListResponse> getPlotByRecommendCondition(@ApiParam(value = "") @Valid @RequestParam(value = "districtId", required = false) Optional<List<String>> districtId, @ApiParam(value = "") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<String>> layoutId, @ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) Optional<Integer> userId, @ApiParam(value = "") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "") @Valid @RequestParam(value = "city", required = false) Optional<String> city, @ApiParam(value = "") @Valid @RequestParam(value = "pageNum", required = false) Optional<Integer> pageNum, @ApiParam(value = "") @Valid @RequestParam(value = "pageSize", required = false) Optional<Integer> pageSize, @ApiParam(value = "") @Valid @RequestParam(value = "flag", required = false) Optional<Integer> flag) {
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        userFavoriteConditionDoQuery.setBeginPrice(beginPrice.get());
        userFavoriteConditionDoQuery.setCity(city.get());
        userFavoriteConditionDoQuery.setDistrictIds((String[]) districtId.get().toArray());
        userFavoriteConditionDoQuery.setEndPrice(endPrice.get());
        userFavoriteConditionDoQuery.setFlag(flag.get());
        userFavoriteConditionDoQuery.setLayoutId((String[]) layoutId.get().toArray());
        userFavoriteConditionDoQuery.setPageNum(pageNum.get());
        userFavoriteConditionDoQuery.setPageSize(pageSize.get());
        userFavoriteConditionDoQuery.setUserId(userId.get());
        List<PlotDetailsDo> restlt = appPlotService.getPlotByRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(restlt));
        List<PlotDetailsFewResponse> plotDetailsFewDos = JSONObject.parseArray(json.toJSONString(), PlotDetailsFewResponse.class);
        PlotDetailsFewListResponse plotDetailsFewListResponse = new PlotDetailsFewListResponse();
        plotDetailsFewListResponse.setPlotDetailsFewResponseList(plotDetailsFewDos);
        plotDetailsFewListResponse.setTotalNum(plotDetailsFewDos.size());
        return new ResponseEntity<PlotDetailsFewListResponse>(plotDetailsFewListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotDetailsResponse> getPlotDetailByPlotId(@ApiParam(value = "plotId", required = true) @Valid @RequestParam(value = "plotId", required = true) Integer plotId) {

        PlotDetailsDo plotDetailsDo = appPlotService.queryPlotDetailByPlotId(plotId, CityUtils.getCity());
        PlotDetailsResponse plotDetailsResponse = new PlotDetailsResponse();
        BeanUtils.copyProperties(plotDetailsDo, plotDetailsResponse);

        PlotsHousesDomain plotsHousesDomain = new PlotsHousesDomain();

        if (null != plotDetailsDo.getPlotsHousesDomain()) {
            BeanUtils.copyProperties(plotDetailsDo.getPlotsHousesDomain(),plotsHousesDomain);
        }
        plotDetailsResponse.setPlotsHousesDomain(plotsHousesDomain);
        return new ResponseEntity<PlotDetailsResponse>(plotDetailsResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotListResponse> getPlotListByRequirementGet(@ApiParam(value = "plotListRequest") @Valid PlotListRequest plotListRequest) {
        PlotListDoQuery plotListDoQuery = new PlotListDoQuery();
        BeanUtils.copyProperties(plotListRequest, plotListDoQuery);
        PlotListDo plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery, CityUtils.getCity());
        PlotListResponse plotListResponse = new PlotListResponse();
        if (plotListDo.getPlotList().size() > 0) {
            plotListResponse.setIsGuess(0);
        } else {
            //没有根据结果查询到数据,返回猜你喜欢的数据
            plotListDoQuery = new PlotListDoQuery();
            plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery, CityUtils.getCity());
            plotListResponse.setIsGuess(1);
        }
        BeanUtils.copyProperties(plotListDo, plotListResponse);
        return new ResponseEntity<PlotListResponse>(plotListResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PlotListResponse> getPlotListByRequirementPost(@ApiParam(value = "plotListRequest") @Valid @RequestBody PlotListRequest plotListRequest) {
        PlotListDoQuery plotListDoQuery = new PlotListDoQuery();
        BeanUtils.copyProperties(plotListRequest, plotListDoQuery);
        PlotListDo plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery, CityUtils.getCity());
        PlotListResponse plotListResponse = new PlotListResponse();
        if (plotListDo.getPlotList().size() > 0) {
            plotListResponse.setIsGuess(0);
        } else {
            //没有根据结果查询到数据,返回猜你喜欢的数据
            plotListDoQuery = new PlotListDoQuery();
            plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery, CityUtils.getCity());
            plotListResponse.setIsGuess(1);
        }
        BeanUtils.copyProperties(plotListDo, plotListResponse);
        return new ResponseEntity<PlotListResponse>(plotListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotsEsfRoomCountResponse> getPlotsEsfList
            (@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId) {

        PlotsEsfRoomCountDomain plotsEsfRoomCountDomain = plotsEsfRestService.queryPlotsEsfByPlotsId(plotId.get(), CityUtils.getCity());
        PlotsEsfRoomCountResponse plotsEsfRoomCountResponse = new PlotsEsfRoomCountResponse();
        BeanUtils.copyProperties(plotsEsfRoomCountDomain, plotsEsfRoomCountResponse);
        return new ResponseEntity<PlotsEsfRoomCountResponse>(plotsEsfRoomCountResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PlotsThemeResponse> getPlotsTheme(@ApiParam(value = "plotListRequest") @Valid PlotListRequest plotListRequest) {
        PlotsThemeDoQuery plotsThemeDoQuery = new PlotsThemeDoQuery();
        BeanUtils.copyProperties(plotListRequest,plotsThemeDoQuery);
        PlotsThemeDomain plotsThemeDos = plotsThemeRestService.getPlotsThemeList(plotsThemeDoQuery, CityUtils.getCity());
        PlotsThemeResponse plotsThemeResponse = new PlotsThemeResponse();
        BeanUtils.copyProperties(plotsThemeDos, plotsThemeResponse);
        return new ResponseEntity<PlotsThemeResponse>(plotsThemeResponse, HttpStatus.OK);


    }

    @Override
    public ResponseEntity<RentDetailsListResponse> getRentListByPlotId
            (@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId, @ApiParam(value = "") @Valid @RequestParam(value = "rentType", required = false) Optional<Integer> rentType, @ApiParam(value = "") @Valid @RequestParam(value = "pageNum", required = false) Optional<Integer> pageNum) {

        RentDetailsListDo rentDetailsListDo = appRentRestService.queryRentListByPlotId(plotId.get(),
                rentType.get(), pageNum.get(), CityUtils.getCity());
        RentDetailsListResponse rentDetailsListResponse = new RentDetailsListResponse();
        BeanUtils.copyProperties(rentDetailsListDo, rentDetailsListResponse);
        return new ResponseEntity<RentDetailsListResponse>(rentDetailsListResponse, HttpStatus.OK);


    }

    @Override
    public ResponseEntity<RentNumListResponse> getRentNumByPlotId
            (@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId) {

        RentNumListDo rentNumListDo = appRentRestService.queryRentNumByPlotId(plotId.get(), CityUtils.getCity());
        return new ResponseEntity<RentNumListResponse>(JSON.parseObject(JSON.toJSONString(rentNumListDo), RentNumListResponse.class), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<SearchHotProjDomain> getHotPlotByHouseType
            (@ApiParam(value = "houseType") @Valid @RequestParam(value = "houseType", required = true) Integer houseType) {
        String city = "";
        if (StringTool.isNotEmpty(CityUtils.getCity())) {
            city = CityUtils.returnCityId(CityUtils.getCity()).toString();
        }
        com.toutiao.app.domain.hotplot.SearchHotProjDomain hotPlotsByCityId = hotPlotsRestService.getHotPlotsByCityId(houseType, city);
        SearchHotProjDomain searchHotProjDomain = new SearchHotProjDomain();
        BeanUtils.copyProperties(hotPlotsByCityId, searchHotProjDomain);
        return new ResponseEntity<SearchHotProjDomain>(searchHotProjDomain, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotTop50ListResponse> getTop50List
            (@ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Integer districtId, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        PlotTop50ListDoQuery plotTop50ListDoQuery = new PlotTop50ListDoQuery();
        plotTop50ListDoQuery.setDistrictId(districtId);
        plotTop50ListDoQuery.setPageNum(pageNum);
        plotTop50ListDoQuery.setPageSize(pageSize);
        List<PlotTop50Do> plotTop50Dos = appPlotService.getPlotTop50List(plotTop50ListDoQuery, CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(plotTop50Dos));
        PlotTop50ListResponse plotTop50ListResponse = new PlotTop50ListResponse();
        List<PlotTop50Response> plotTop50Responses = JSONObject.parseArray(json.toJSONString(), PlotTop50Response.class);
        plotTop50ListResponse.setPlotTop50ResponseList(plotTop50Responses);
        plotTop50ListResponse.setTotalNum(plotTop50Responses.size());
        return new ResponseEntity<PlotTop50ListResponse>(plotTop50ListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotListResponse> getGuessList(PlotListRequest plotListRequest) {

        PlotListDoQuery plotListDoQuery = new PlotListDoQuery();
        BeanUtils.copyProperties(plotListRequest, plotListDoQuery);
        PlotListDo plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery, CityUtils.getCity());
        return new ResponseEntity<PlotListResponse>(JSON.parseObject(JSON.toJSONString(plotListDo), PlotListResponse.class), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> getFoldLineInfo(@ApiParam(value = "小区id", required = true) @Valid @RequestParam(value = "newcode") String newcode,
                                                  @ApiParam(value = "区域id", required = true) @Valid @RequestParam(value = "areaId") String areaId){
        JSONArray jsonArray = appPlotService.getFoldLineInfo(newcode,areaId);
        return new ResponseEntity<String>(JSONObject.toJSONString(jsonArray), HttpStatus.OK);
    }
}
