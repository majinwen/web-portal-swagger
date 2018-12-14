package com.toutiao.appV2.apiimpl.Intelligence;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.Intelligence.IntelligenceDo;
import com.toutiao.app.domain.Intelligence.PriceRatioDo;
import com.toutiao.app.domain.Intelligence.PriceTrendDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.service.Intelligence.HomePageReportService;
import com.toutiao.app.service.plot.PlotsRestService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.appV2.api.Intelligence.FindhouseApi;
import com.toutiao.appV2.model.Intelligence.IntelligenceResponse;
import com.toutiao.appV2.model.Intelligence.UserFavoriteConditionRequest;
import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFhTdRatio;
import com.toutiao.web.service.intelligence.IntelligenceFhPricetrendService;
import com.toutiao.web.service.intelligence.IntelligenceFhResService;
import com.toutiao.web.service.intelligence.IntelligenceFhTdService;
import com.toutiao.web.service.plot.PlotService;
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
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T02:44:29.266Z")

@Controller
public class FindhouseApiController implements FindhouseApi {

    private static final Logger log = LoggerFactory.getLogger(FindhouseApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private IntelligenceFhTdService intelligenceFhTdService;
    @Autowired
    private HomePageReportService homePageReportService;
    @Autowired
    private IntelligenceFhResService intelligenceFhResService;
    @Autowired
    private IntelligenceFhPricetrendService intelligenceFhPricetrendService;
//    @Autowired
//    private PlotService plotService;
    @Autowired
    private PlotsRestService plotsRestService;

    @org.springframework.beans.factory.annotation.Autowired
    public FindhouseApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<IntelligenceResponse> getHomePageReport(@NotNull @ApiParam(value = "reportId", required = true) @Valid @RequestParam(value = "reportId", required = true) String reportId) {
        IntelligenceDo intelligenceDo = new IntelligenceDo();
        IntelligenceFhTdRatio intelligenceFhTdRatio = new IntelligenceFhTdRatio();
        PriceTrendDo fhpt = new PriceTrendDo();
        PriceRatioDo fhtp = new PriceRatioDo();
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

}
