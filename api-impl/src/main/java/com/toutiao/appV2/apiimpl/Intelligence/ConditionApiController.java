package com.toutiao.appV2.apiimpl.Intelligence;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.Intelligence.IntelligenceDo;
import com.toutiao.app.domain.Intelligence.PriceRatioDo;
import com.toutiao.app.domain.homepage.RecommendTopicDoQuery;
import com.toutiao.app.domain.homepage.RecommendTopicDomain;
import com.toutiao.app.domain.newhouse.*;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.domain.rent.RentCustomConditionDomain;
import com.toutiao.app.domain.rent.UserFavoriteRentListDoQuery;
import com.toutiao.app.domain.rent.UserFavoriteRentListDomain;
import com.toutiao.app.domain.sellhouse.RecommendEsf5DoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseSearchDomain;
import com.toutiao.app.service.Intelligence.HomePageReportService;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.app.service.homepage.RecommendRestService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.app.service.plot.PlotsRestService;
import com.toutiao.app.service.rent.UserFavoriteRentService;
import com.toutiao.app.service.sellhouse.SellHouseService;
import com.toutiao.appV2.api.Intelligence.ConditionApi;
import com.toutiao.appV2.model.HomePage.*;
import com.toutiao.appV2.model.Intelligence.*;
import com.toutiao.appV2.model.Intelligence.UserFavoriteConditionRequest;
import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.appV2.model.plot.PlotDetailsFewDo;
import com.toutiao.appV2.model.plot.PlotListResponse;
import com.toutiao.appV2.model.rent.RentCustomConditionResponse;
import com.toutiao.appV2.model.rent.UserFavoriteRentListRequest;
import com.toutiao.appV2.model.rent.UserFavoriteRentListResponse;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFhTdRatio;
import com.toutiao.web.service.intelligence.IntelligenceFhPricetrendService;
import com.toutiao.web.service.intelligence.IntelligenceFhResService;
import com.toutiao.web.service.intelligence.IntelligenceFhTdService;
import io.swagger.annotations.ApiParam;
import org.postgresql.util.PGobject;
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
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:50:01.627Z")

@Controller
public class ConditionApiController implements ConditionApi {

    private static final Logger log = LoggerFactory.getLogger(ConditionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private HomePageRestService homePageRestService;

    @Autowired
    private PlotsRestService appPlotService;

    @Autowired
    private SellHouseService sellHouseService;

    @Autowired
    private NewHouseRestService newHouseService;

    @Autowired
    private IntelligenceFhTdService intelligenceFhTdService;
    @Autowired
    private HomePageReportService homePageReportService;

    @Autowired
    private IntelligenceFhResService intelligenceFhResService;
    @Autowired
    private IntelligenceFhPricetrendService intelligenceFhPricetrendService;

    @Autowired
    private PlotsRestService plotsRestService;

    @Autowired
    private RecommendRestService recommendRestService;

    @Autowired
    private UserFavoriteRentService userFavoriteRentService;

    @org.springframework.beans.factory.annotation.Autowired
    public ConditionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<StringDataResponse> deleteRecommendCondition(@ApiParam(value = "userId", required = true) @Valid @RequestParam(value = "userId", required = true) Integer userId,
                                                                       @ApiParam(value = "conditionType", required = true) @Valid @RequestParam(value = "conditionType", required = true) Integer conditionType) {
        Integer city = CityUtils.returnCityId(CityUtils.getCity());
        Integer integer = homePageRestService.deleteRecommendCondition(userId, conditionType, city);
        StringDataResponse stringDataResponse = new StringDataResponse();
        stringDataResponse.setData("删除推荐条件成功");
        return new ResponseEntity<StringDataResponse>(stringDataResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserFavoriteConditionResponse> getRecommendCondition(@ApiParam(value = "userId", required = true) @Valid @RequestParam(value = "userId", required = true) Integer userId,
                                                                               @ApiParam(value = "conditionType", required = true) @Valid @RequestParam(value = "conditionType", required = true) Integer conditionType) {
        UserFavoriteConditionResponse userFavoriteConditionResponse = new UserFavoriteConditionResponse();
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        userFavoriteConditionDoQuery.setUserId(userId);
        userFavoriteConditionDoQuery.setConditionType(conditionType);
        UserFavoriteConditionDo recommendCondition = homePageRestService.getRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(recommendCondition, userFavoriteConditionResponse);
        return new ResponseEntity<UserFavoriteConditionResponse>(userFavoriteConditionResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomConditionUserSampleResponse> saveRecommendCondition(@ApiParam(value = "userFavoriteConditionRequest") @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest) {
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest, userFavoriteConditionDoQuery);
        CustomConditionUserSampleDo conditionUserSampleDo = homePageRestService.saveRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        CustomConditionUserSampleResponse conditionUserSampleResponse = new CustomConditionUserSampleResponse();
        conditionUserSampleResponse.setData(conditionUserSampleDo);
        return new ResponseEntity<>(conditionUserSampleResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomConditionCountResponse> getCustomCondition(@ApiParam(value = "userFavoriteConditionRequest", required = true)  @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest) {

        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest, userFavoriteConditionDoQuery);
        CustomConditionCountDo customCondition = homePageRestService.getCustomCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        CustomConditionCountResponse customConditionCountResponse = new CustomConditionCountResponse();
        customConditionCountResponse.setData(customCondition);
        return new ResponseEntity<>(customConditionCountResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SellHouseSearchDomainResponse> getRecommendEsf5(@ApiParam(value = "RecommendEsf5Request", required = true) @Valid @RequestBody RecommendEsf5Request recommendEsf5Request) {
        SellHouseSearchDomainResponse sellHouseSearchDomainResponse = new SellHouseSearchDomainResponse();
        RecommendEsf5DoQuery recommendEsf5DoQuery = new RecommendEsf5DoQuery();
        BeanUtils.copyProperties(recommendEsf5Request, recommendEsf5DoQuery);
        SellHouseSearchDomain sellHouseSearchDomain = sellHouseService.getRecommendEsf5(recommendEsf5DoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseSearchDomain, sellHouseSearchDomainResponse);
        return new ResponseEntity<>(sellHouseSearchDomainResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PlotListResponse> getPlotByRecommendCondition(@ApiParam(value = "UserFavoriteConditionRequest", required = true) @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest) {
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest, userFavoriteConditionDoQuery);
        List<PlotDetailsDo> restlt = appPlotService.getPlotByRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(restlt));
        List<PlotDetailsFewDo> plotDetailsFewDos = JSONObject.parseArray(json.toJSONString(), PlotDetailsFewDo.class);
        PlotListResponse plotDetailsFewDoList = new PlotListResponse();
        plotDetailsFewDoList.setPlotList(plotDetailsFewDos);
        plotDetailsFewDoList.setTotalCount(plotDetailsFewDos.size());
        return new ResponseEntity<>(plotDetailsFewDoList, HttpStatus.OK);
    }


//    @Override
//    public ResponseEntity<NewHouseDetailResponse> getOneNewHouseByRecommendCondition(@ApiParam(value = "UserFavoriteConditionRequest", required = true) @Valid com.toutiao.appV2.model.HomePage.UserFavoriteConditionRequest userFavoriteConditionRequest) {
//        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
//        BeanUtils.copyProperties(userFavoriteConditionRequest, userFavoriteConditionDoQuery);
//        NewHouseDetailDo newHouseDetailDo = newHouseService.getOneNewHouseByRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
//        NewHouseDetailResponse newHouseDetailResponse = JSON.parseObject(JSON.toJSONString(newHouseDetailDo), NewHouseDetailResponse.class);
//        return new ResponseEntity<>(newHouseDetailResponse, HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<CustomConditionDetailsResponse> getEsfCustomConditionDetails(@ApiParam(value = "UserFavoriteConditionRequest", required = true) @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest) {

        CustomConditionDetailsResponse conditionDetailsResponse = new CustomConditionDetailsResponse();
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest, userFavoriteConditionDoQuery);

        CustomConditionDetailsDomain conditionDetailsDomain = sellHouseService.getEsfCustomConditionDetails(userFavoriteConditionDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(conditionDetailsDomain, conditionDetailsResponse);
        return new ResponseEntity<>(conditionDetailsResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IntelligenceResponse> getHomePageReport(@NotNull @ApiParam(value = "reportId", required = true) @Valid @RequestParam(value = "reportId", required = true) String reportId) {
        IntelligenceDo intelligenceDo = new IntelligenceDo();
        com.toutiao.web.domain.intelligenceFh.IntelligenceFhTdRatio intelligenceFhTdRatio = new IntelligenceFhTdRatio();
        com.toutiao.app.domain.Intelligence.PriceTrendDo fhpt = new com.toutiao.app.domain.Intelligence.PriceTrendDo();
        com.toutiao.app.domain.Intelligence.PriceRatioDo fhtp = new PriceRatioDo();
        fhtp.setRatio(intelligenceFhTdRatio);
        IntelligenceResponse intelligenceResponse = new IntelligenceResponse();
        if (StringTool.isNotBlank(reportId)) {

            IntelligenceFhRes intelligenceFhRes = intelligenceFhResService.queryResById(Integer.valueOf(reportId));

            if (StringTool.isNotBlank(intelligenceFhRes)) {

                List list = JSONObject.parseArray(((PGobject) intelligenceFhRes.getFhResult()).getValue());
                for (int i = 0; i < list.size(); i++) {
                    if (StringTool.isNotEmpty(((JSONObject) list.get(i)).get("newcode"))) {
                        String plotId = ((JSONObject) list.get(i)).get("newcode").toString();

                        PlotDetailsDo plotDetailsDo = plotsRestService.queryPlotByPlotId(plotId, CityUtils.getCity());
                        ((JSONObject) list.get(i)).put("esfPrice", plotDetailsDo.getAvgPrice());
                        ((JSONObject) list.get(i)).put("plotImage", plotDetailsDo.getPhoto().toString().replaceAll("[\\[\\]]", ""));

                    }
                }

                intelligenceFhRes.setFhResult(JSONObject.toJSONString(list));


                String datajson = list.toString();
                fhpt = intelligenceFhPricetrendService.queryPriceTrend(intelligenceFhRes.getTotalPrice());
                fhtp = intelligenceFhTdService.queryTd(intelligenceFhRes.getTotalPrice());
                intelligenceDo.setDatajson(datajson);
                intelligenceDo.setTotalPrice(intelligenceFhRes.getTotalPrice());
                if (StringTool.isNotEmpty(intelligenceFhRes.getLayoutArray())) {
                    intelligenceDo.setLayout(intelligenceFhRes.getLayoutArray());
                } else {
                    intelligenceDo.setLayout(intelligenceFhRes.getLayoutArray());
                }

                if (StringTool.isNotEmpty(intelligenceFhRes.getDistrictArray())) {
                    intelligenceDo.setDistrict(intelligenceFhRes.getDistrictArray());
                } else {
                    intelligenceDo.setDistrict(intelligenceFhRes.getDistrictArray());
                }

                intelligenceDo.setCollectStatus(intelligenceFhRes.getCollectStatus());
                intelligenceDo.setBackUrl(request.getRequestURI());
            }
        }
        intelligenceDo.setFhpt(fhpt);
        intelligenceDo.setFhtp(fhtp);
        BeanUtils.copyProperties(intelligenceDo, intelligenceResponse);
        intelligenceResponse.setFhpt(fhpt);
        return new ResponseEntity<IntelligenceResponse>(intelligenceResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StringDataResponse> saveHomePageReport(@ApiParam(value = "userFavoriteConditionRequest", required = true) @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest) {
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest, userFavoriteConditionDoQuery);
        Integer result = homePageReportService.saveHomePageReport(request, userFavoriteConditionDoQuery);
        StringDataResponse stringDataResponse = new StringDataResponse();
        stringDataResponse.setData(result.toString());
        return new ResponseEntity<StringDataResponse>(stringDataResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RecommendTopicDomain>  getRecommendTopic(@ApiParam(value = "userFavoriteConditionRequest", required = true) @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest) {
        RecommendTopicDoQuery recommendTopicDoQuery = new RecommendTopicDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest, recommendTopicDoQuery);
        RecommendTopicDomain recommendTopicDomain = recommendRestService.getRecommendTopic(recommendTopicDoQuery, CityUtils.getCity());
        return new ResponseEntity<>(recommendTopicDomain, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewHouseCustomConditionResponse> getCustomNewHouseRecommend(@ApiParam(value = "userFavoriteConditionRequest", required = true) @Valid NewHouseCustomConditionResquest newHouseCustomConditionResquest) {
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(newHouseCustomConditionResquest, userFavoriteConditionDoQuery);

        NewHouseCustomConditionDomain newHouseCustomConditionDomain = newHouseService.getNewHouseCustomList(userFavoriteConditionDoQuery, CityUtils.getCity());
        NewHouseCustomConditionResponse newHouseCustomConditionResponse = new NewHouseCustomConditionResponse();
        BeanUtils.copyProperties(newHouseCustomConditionDomain, newHouseCustomConditionResponse);

        return new ResponseEntity<>(newHouseCustomConditionResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserFavoriteRentListResponse> getRentHouseListByUserFavorite(@ApiParam(value = "userFavoriteRentListRequest", required = true) @Valid @RequestBody UserFavoriteRentListRequest userFavoriteRentListRequest) {
        UserFavoriteRentListDoQuery userFavoriteRentListDoQuery = new UserFavoriteRentListDoQuery();
        BeanUtils.copyProperties(userFavoriteRentListRequest, userFavoriteRentListDoQuery);

        UserFavoriteRentListDomain userFavoriteRentListDomain = userFavoriteRentService.queryRentListByUserFavorite(userFavoriteRentListDoQuery, CityUtils.getCity());

        UserFavoriteRentListResponse userFavoriteRentListResponse = new UserFavoriteRentListResponse();
        BeanUtils.copyProperties(userFavoriteRentListDomain, userFavoriteRentListResponse);

        return new ResponseEntity<>(userFavoriteRentListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RentCustomConditionResponse> getHouseCountBySubway(@ApiParam(value = "userFavoriteRentListRequest", required = true) @Valid @RequestBody UserFavoriteRentListRequest userFavoriteRentListRequest) {
        UserFavoriteRentListDoQuery userFavoriteRentListDoQuery = new UserFavoriteRentListDoQuery();
        BeanUtils.copyProperties(userFavoriteRentListRequest, userFavoriteRentListDoQuery);
        RentCustomConditionDomain rentCustomConditionDomain = userFavoriteRentService.querySubwayLineHouse(userFavoriteRentListDoQuery, CityUtils.getCity());
        RentCustomConditionResponse rentCustomConditionResponse = new RentCustomConditionResponse();
        BeanUtils.copyProperties(rentCustomConditionDomain, rentCustomConditionResponse);

        return new ResponseEntity<>(rentCustomConditionResponse, HttpStatus.OK);
    }

}
