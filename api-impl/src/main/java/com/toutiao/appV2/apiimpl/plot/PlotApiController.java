package com.toutiao.appV2.apiimpl.plot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.response.hotplot.HotPlotListResponse;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.plot.*;
import com.toutiao.app.domain.plot.PlotDetailsFewDo;
import com.toutiao.app.domain.rent.RentDetailsListDo;
import com.toutiao.app.domain.rent.RentNumListDo;
import com.toutiao.app.service.plot.*;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.appV2.model.plot.*;
import com.toutiao.app.domain.sellhouse.SellAndClaimHouseDetailsDo;
import com.toutiao.appV2.api.plot.PlotApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<NearbyPlotsListResponse> getNearbyPlotsList(@ApiParam(value = "居室") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<Integer>> layoutId, @ApiParam(value = "朝向") @Valid @RequestParam(value = "forwardId", required = false) Optional<List<Integer>> forwardId, @ApiParam(value = "标签") @Valid @RequestParam(value = "labelId", required = false) Optional<List<Integer>> labelId, @ApiParam(value = "起始价格") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "结束价格") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "起始面积") @Valid @RequestParam(value = "beginArea", required = false) Optional<Double> beginArea, @ApiParam(value = "结束面积") @Valid @RequestParam(value = "endArea", required = false) Optional<Double> endArea, @ApiParam(value = "楼龄[0-5]") @Valid @RequestParam(value = "houseYearId", required = false) Optional<String> houseYearId, @ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Optional<Integer> districtId, @ApiParam(value = "商圈id") @Valid @RequestParam(value = "areaId", required = false) Optional<List<Integer>> areaId, @ApiParam(value = "地铁线Id") @Valid @RequestParam(value = "subwayLineId", required = false) Optional<Integer> subwayLineId, @ApiParam(value = "地铁站id") @Valid @RequestParam(value = "subwayStationId", required = false) Optional<List<Integer>> subwayStationId, @ApiParam(value = "关键字") @Valid @RequestParam(value = "keyword", required = false) Optional<String> keyword, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Optional<Integer> pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Optional<Integer> pageSize, @ApiParam(value = "城市id") @Valid @RequestParam(value = "cityId", required = false) Optional<Integer> cityId, @ApiParam(value = "") @Valid @RequestParam(value = "lat", required = false) Optional<Double> lat, @ApiParam(value = "") @Valid @RequestParam(value = "lon", required = false) Optional<Double> lon, @ApiParam(value = "") @Valid @RequestParam(value = "distance", required = false) Optional<String> distance) {
        NearbyPlotsDoQuery nearbyPlotsDoQuery = new NearbyPlotsDoQuery();
        nearbyPlotsDoQuery.setLayoutId((Integer[]) layoutId.get().toArray());
        nearbyPlotsDoQuery.setForwardId((Integer[]) forwardId.get().toArray());
        nearbyPlotsDoQuery.setDistance(distance.get());
        nearbyPlotsDoQuery.setLat(lat.get());
        nearbyPlotsDoQuery.setLon(lon.get());
        nearbyPlotsDoQuery.setAreaId((Integer[]) areaId.get().toArray());
        nearbyPlotsDoQuery.setBeginArea(beginArea.get());
        nearbyPlotsDoQuery.setBeginPrice(beginPrice.get());
        nearbyPlotsDoQuery.setCityId(cityId.get());
        nearbyPlotsDoQuery.setDistrictId(districtId.get());
        nearbyPlotsDoQuery.setEndArea(endArea.get());
        nearbyPlotsDoQuery.setEndPrice(endPrice.get());
        nearbyPlotsDoQuery.setHouseYearId(houseYearId.get());
        nearbyPlotsDoQuery.setKeyword(keyword.get());
        nearbyPlotsDoQuery.setLabelId((Integer[]) labelId.get().toArray());
        nearbyPlotsDoQuery.setPageNum(pageNum.get());
        nearbyPlotsDoQuery.setPageSize(pageSize.get());
        nearbyPlotsDoQuery.setSubwayLineId(subwayLineId.get());
        nearbyPlotsDoQuery.setSubwayStationId((Integer[]) subwayStationId.get().toArray());
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
        userFavoriteConditionDoQuery.setDistrictId((String[]) districtId.get().toArray());
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
    public ResponseEntity<PlotDetailsResponse> getPlotDetailByPlotId(@ApiParam(value = "") @Valid @RequestParam(value = "plotId", required = false) Optional<Integer> plotId) {

        PlotDetailsDo plotDetailsDo = appPlotService.queryPlotDetailByPlotId(plotId.get(), CityUtils.getCity());
        PlotDetailsResponse plotDetailsResponse = new PlotDetailsResponse();
        BeanUtils.copyProperties(plotDetailsDo, plotDetailsResponse);
        return new ResponseEntity<PlotDetailsResponse>(plotDetailsResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotListResponse> getPlotListByRequirement(@ApiParam(value = "居室") @Valid @RequestParam(value = "layoutId", required = false) List<Integer> layoutId, @ApiParam(value = "朝向") @Valid @RequestParam(value = "forwardId", required = false) List<Integer> forwardId, @ApiParam(value = "标签") @Valid @RequestParam(value = "labelId", required = false) List<Integer> labelId, @ApiParam(value = "起始价格") @Valid @RequestParam(value = "beginPrice", required = false) Double beginPrice, @ApiParam(value = "结束价格") @Valid @RequestParam(value = "endPrice", required = false) Double endPrice, @ApiParam(value = "起始面积") @Valid @RequestParam(value = "beginArea", required = false) Double beginArea, @ApiParam(value = "结束面积") @Valid @RequestParam(value = "endArea", required = false) Double endArea, @ApiParam(value = "楼龄[0-5]") @Valid @RequestParam(value = "houseYearId", required = false) String houseYearId, @ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Integer districtId, @ApiParam(value = "商圈id") @Valid @RequestParam(value = "areaId", required = false) List<Integer> areaId, @ApiParam(value = "地铁线Id") @Valid @RequestParam(value = "subwayLineId", required = false) Integer subwayLineId, @ApiParam(value = "地铁站id") @Valid @RequestParam(value = "subwayStationId", required = false) List<Integer> subwayStationId, @ApiParam(value = "关键字") @Valid @RequestParam(value = "keyword", required = false) String keyword, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize, @ApiParam(value = "城市id") @Valid @RequestParam(value = "cityId", required = false) Integer cityId, @ApiParam(value = "") @Valid @RequestParam(value = "lat", required = false) Double lat, @ApiParam(value = "") @Valid @RequestParam(value = "lon", required = false) Double lon, @ApiParam(value = "") @Valid @RequestParam(value = "isTop", required = false) Integer isTop, @ApiParam(value = "") @Valid @RequestParam(value = "distance", required = false) Double distance) {

        PlotListDoQuery plotListDoQuery = new PlotListDoQuery();
        plotListDoQuery.setIsTop(isTop);
        plotListDoQuery.setDistance(distance);
        plotListDoQuery.setLat(lat);
        plotListDoQuery.setLon(lon);
        plotListDoQuery.setAreaId((Integer[]) areaId.toArray());
        plotListDoQuery.setBeginArea(beginArea);
        plotListDoQuery.setBeginPrice(beginPrice);
        plotListDoQuery.setCityId(cityId);
        plotListDoQuery.setDistrictId(districtId);
        plotListDoQuery.setEndArea(endArea);
        plotListDoQuery.setEndPrice(endPrice);
        plotListDoQuery.setForwardId((Integer[]) forwardId.toArray());
        plotListDoQuery.setHouseYearId(houseYearId);
        plotListDoQuery.setKeyword(keyword);
        plotListDoQuery.setLabelId((Integer[]) labelId.toArray());
        plotListDoQuery.setLayoutId((Integer[]) layoutId.toArray());
        plotListDoQuery.setPageNum(pageNum);
        plotListDoQuery.setPageSize(pageSize);
        plotListDoQuery.setSubwayLineId(subwayLineId);
        plotListDoQuery.setSubwayStationId((Integer[]) subwayStationId.toArray());

        PlotListDo plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery, CityUtils.getCity());
        return new ResponseEntity<PlotListResponse>(JSON.parseObject(JSON.toJSONString(plotListDo), PlotListResponse.class), HttpStatus.OK);

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
    public ResponseEntity<PlotsThemeResponse> getPlotsTheme
            (@ApiParam(value = "居室") @Valid @RequestParam(value = "layoutId", required = false) Optional<List<Integer>> layoutId, @ApiParam(value = "朝向") @Valid @RequestParam(value = "forwardId", required = false) Optional<List<Integer>> forwardId, @ApiParam(value = "标签") @Valid @RequestParam(value = "labelId", required = false) Optional<List<Integer>> labelId, @ApiParam(value = "") @Valid @RequestParam(value = "districtIds", required = false) Optional<List<Integer>> districtIds, @ApiParam(value = "起始价格") @Valid @RequestParam(value = "beginPrice", required = false) Optional<Double> beginPrice, @ApiParam(value = "结束价格") @Valid @RequestParam(value = "endPrice", required = false) Optional<Double> endPrice, @ApiParam(value = "起始面积") @Valid @RequestParam(value = "beginArea", required = false) Optional<Double> beginArea, @ApiParam(value = "结束面积") @Valid @RequestParam(value = "endArea", required = false) Optional<Double> endArea, @ApiParam(value = "楼龄[0-5]") @Valid @RequestParam(value = "houseYearId", required = false) Optional<String> houseYearId, @ApiParam(value = "区域") @Valid @RequestParam(value = "districtId", required = false) Optional<Integer> districtId, @ApiParam(value = "商圈id") @Valid @RequestParam(value = "areaId", required = false) Optional<List<Integer>> areaId, @ApiParam(value = "地铁线Id") @Valid @RequestParam(value = "subwayLineId", required = false) Optional<Integer> subwayLineId, @ApiParam(value = "地铁站id") @Valid @RequestParam(value = "subwayStationId", required = false) Optional<List<Integer>> subwayStationId, @ApiParam(value = "关键字") @Valid @RequestParam(value = "keyword", required = false) Optional<String> keyword, @ApiParam(value = "页码", defaultValue = "1") @Valid @RequestParam(value = "pageNum", required = false, defaultValue = "1") Optional<Integer> pageNum, @ApiParam(value = "每页数量", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue = "10") Optional<Integer> pageSize, @ApiParam(value = "城市id") @Valid @RequestParam(value = "cityId", required = false) Optional<Integer> cityId, @ApiParam(value = "") @Valid @RequestParam(value = "nearestPark", required = false) Optional<String> nearestPark, @ApiParam(value = "") @Valid @RequestParam(value = "recommendBuildTagsId", required = false) Optional<Integer> recommendBuildTagsId) {

        PlotsThemeDoQuery plotsThemeDoQuery = new PlotsThemeDoQuery();
        plotsThemeDoQuery.setDistrictIds((Integer[]) districtIds.get().toArray());
        plotsThemeDoQuery.setDistrictId(districtId.get());
        plotsThemeDoQuery.setNearestPark(nearestPark.get());
        plotsThemeDoQuery.setRecommendBuildTagsId(recommendBuildTagsId.get());
        plotsThemeDoQuery.setAreaId((Integer[]) areaId.get().toArray());
        plotsThemeDoQuery.setBeginArea(beginArea.get());
        plotsThemeDoQuery.setBeginPrice(beginPrice.get());
        plotsThemeDoQuery.setCityId(cityId.get());
        plotsThemeDoQuery.setEndArea(endArea.get());
        plotsThemeDoQuery.setEndPrice(endPrice.get());
        plotsThemeDoQuery.setForwardId((Integer[]) forwardId.get().toArray());
        plotsThemeDoQuery.setHouseYearId(houseYearId.get());
        plotsThemeDoQuery.setKeyword(keyword.get());
        plotsThemeDoQuery.setLabelId((Integer[]) labelId.get().toArray());
        plotsThemeDoQuery.setLayoutId((Integer[]) layoutId.get().toArray());
        plotsThemeDoQuery.setPageNum(pageNum.get());
        plotsThemeDoQuery.setPageSize(pageSize.get());
        plotsThemeDoQuery.setSubwayLineId(subwayLineId.get());
        plotsThemeDoQuery.setSubwayStationId((Integer[]) subwayStationId.get().toArray());
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
    public ResponseEntity<SearchHotProjDomain> getHotPlotByCityId
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
    public ResponseEntity<PlotListResponse> getGuessList(Optional<List<Integer>> layoutId, Optional<List<Integer>> forwardId, Optional<List<Integer>> labelId, Optional<Double> beginPrice, Optional<Double> endPrice, Optional<Double> beginArea, Optional<Double> endArea, Optional<String> houseYearId, Optional<Integer> districtId, Optional<List<Integer>> areaId, Optional<Integer> subwayLineId, Optional<List<Integer>> subwayStationId, Optional<String> keyword, Optional<Integer> pageNum, Optional<Integer> pageSize, Optional<Integer> cityId, Optional<Double> lat, Optional<Double> lon, Optional<Integer> isTop, Optional<Double> distance) {
        PlotListDoQuery plotListDoQuery = new PlotListDoQuery();
        if (!isTop.equals(Optional.empty())) {
            plotListDoQuery.setIsTop(isTop.get());
        }
        if (!distance.equals(Optional.empty())) {
            plotListDoQuery.setDistance(distance.get());
        }
        if (!lat.equals(Optional.empty())) {
            plotListDoQuery.setLat(lat.get());
        }
        if (!lon.equals(Optional.empty())) {
            plotListDoQuery.setLon(lon.get());
        }
        if (!areaId.equals(Optional.empty())) {
            plotListDoQuery.setAreaId((Integer[]) areaId.get().toArray());
        }
        if (!beginArea.equals(Optional.empty())) {
            plotListDoQuery.setBeginArea(beginArea.get());
        }
        if (!beginPrice.equals(Optional.empty())) {
            plotListDoQuery.setBeginPrice(beginPrice.get());
        }
        if (!cityId.equals(Optional.empty())) {
            plotListDoQuery.setCityId(cityId.get());
        }
        if (!districtId.equals(Optional.empty())) {
            plotListDoQuery.setDistrictId(districtId.get());
        }
        if (!endArea.equals(Optional.empty())) {
            plotListDoQuery.setEndArea(endArea.get());
        }
        if (!endPrice.equals(Optional.empty())) {
            plotListDoQuery.setEndPrice(endPrice.get());
        }
        if (!forwardId.equals(Optional.empty())) {
            plotListDoQuery.setForwardId((Integer[]) forwardId.get().toArray());
        }
        if (!houseYearId.equals(Optional.empty())) {
            plotListDoQuery.setHouseYearId(houseYearId.get());
        }
        if (!keyword.equals(Optional.empty())) {
            plotListDoQuery.setKeyword(keyword.get());
        }
        if (!labelId.equals(Optional.empty())) {
            plotListDoQuery.setLabelId((Integer[]) labelId.get().toArray());
        }
        if (!layoutId.equals(Optional.empty())) {
            plotListDoQuery.setLayoutId((Integer[]) layoutId.get().toArray());
        }
        if (!pageNum.equals(Optional.empty())) {
            plotListDoQuery.setPageNum(pageNum.get());
        }
        if (!pageSize.equals(Optional.empty())) {
            plotListDoQuery.setPageSize(pageSize.get());
        }
        if (!subwayLineId.equals(Optional.empty())) {
            plotListDoQuery.setSubwayLineId(subwayLineId.get());
        }
        if (!subwayStationId.equals(Optional.empty())) {
            plotListDoQuery.setSubwayStationId((Integer[]) subwayStationId.get().toArray());
        }

        PlotListDo plotListDo = appPlotService.queryPlotListByRequirement(plotListDoQuery, CityUtils.getCity());
        return new ResponseEntity<PlotListResponse>(JSON.parseObject(JSON.toJSONString(plotListDo), PlotListResponse.class), HttpStatus.OK);

    }



}
