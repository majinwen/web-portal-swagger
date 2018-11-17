package com.toutiao.appV2.apiimpl.Intelligence;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.response.user.UserLoginResponse;
import com.toutiao.app.domain.Intelligence.IntelligenceDo;
import com.toutiao.app.domain.plot.PlotDetailsDo;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.appV2.api.Intelligence.CitypathApi;
import com.toutiao.appV2.model.Intelligence.*;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.domain.query.IntelligenceQuery;
import com.toutiao.web.service.intelligence.IntelligenceFhPricetrendService;
import com.toutiao.web.service.intelligence.IntelligenceFhResService;
import com.toutiao.web.service.intelligence.IntelligenceFhTdService;
import com.toutiao.web.service.intelligence.IntelligenceFindHouseService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T08:35:17.892Z")

@Controller
public class CitypathApiController implements CitypathApi {

    private static final Logger log = LoggerFactory.getLogger(CitypathApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public CitypathApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private IntelligenceFhTdService intelligenceFhTdService;
    @Autowired
    private IntelligenceFhResService intelligenceFhResService;
    @Autowired
    private IntelligenceFindHouseService intelligenceFindHouseService;
    @Autowired
    private IntelligenceFhPricetrendService intelligenceFhPricetrendService;
    @Autowired
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private PlotService plotService;

    @Override
    public ResponseEntity<String> cancleMyReport(@ApiParam(value = "reportId",required=true) @PathVariable("reportId") String reportId) {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
                        UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);
                        UserBasicDo userBasic = null;
                        if(null != userLoginResponse){
                            userBasic =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
                        }
                        int count = intelligenceFhResService.deleteMyReport(reportId);
                        if(count != 0){
                            return new ResponseEntity<String>(HttpStatus.OK);
                        }else{
                            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<String> collectMyReport(@NotNull @ApiParam(value = "reportId", required = true) @Valid @RequestParam(value = "reportId", required = true) String reportId) {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        if (StringTool.isNotBlank(reportId)) {
                            String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
                            UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);
                            UserBasicDo userBasic = null;
                            if(null != userLoginResponse){
                                userBasic =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
                            }
                            if (StringTool.isBlank(userBasic)) {
                                //前台判断状态 然后跳转到登陆页面
                                return new ResponseEntity<String>("no-login", HttpStatus.INTERNAL_SERVER_ERROR);
                            }else {
                                //更改当前报告的状态
                                int result = intelligenceFhResService.updateMyReportCollectStatus(reportId, userBasic.getPhone());
                                if (result != 0) {
                                    //收藏成功
                                    return new ResponseEntity<String>(HttpStatus.OK);
                                }else{
                                    return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
                                }
                            }
                        }else {
                            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
                        }

                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<ReportResponse> getMyReport() {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        ReportResponse reportResponse = new ReportResponse();
                        reportResponse.setBackUrl(request.getRequestURI());
                        //从cookie中获取用户手机号码
                        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
                        UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);
                        UserBasicDo userBasic = null;
                        List userReport = new ArrayList<>();
                        if(null != userLoginResponse){
                            userBasic =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
                            //查询用户是否有报告数据
                            userReport = intelligenceFhResService.queryUserReport(userBasic.getPhone());
                        }
                        if (StringTool.isNotBlank(userReport)&&userReport.size()>0) {
                            reportResponse.setUserReport(userReport);
                        }
                        return new ResponseEntity<ReportResponse>(reportResponse, HttpStatus.OK);
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<ReportResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<ReportResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<String> goToStartRobot() {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        return new ResponseEntity<String>("app/intelligent-find", HttpStatus.OK);
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<IntelligenceFh> queryPlotCountByDistrict(@ApiParam(value = "intelligenceRequest" ,required=true )  @Valid @RequestBody IntelligenceRequest intelligenceRequest) {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
                        BeanUtils.copyProperties(intelligenceRequest,intelligenceQuery);
                        //通过页面传递过来的区域等信息赛选小区数量
                        com.toutiao.web.domain.intelligenceFh.IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryPlotCountByDistrict(intelligenceQuery);
                        IntelligenceFh intelligenceFh1 = new IntelligenceFh();
                        BeanUtils.copyProperties(intelligenceFh,intelligenceFh1);
                        //报告生成页
                        return new ResponseEntity<IntelligenceFh>(intelligenceFh1, HttpStatus.OK);
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<IntelligenceFh>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<IntelligenceFh>(HttpStatus.NOT_IMPLEMENTED);
    }

//    @Override
//    public ResponseEntity<PriceTrendDo> queryPt(@ApiParam(value = "intelligenceRequest" ,required=true )  @Valid @RequestBody IntelligenceRequest intelligenceRequest) {
//                String accept = request.getHeader("Accept");
//                if (accept != null && accept.contains("application/json")) {
//                    try {
//                        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
//                        BeanUtils.copyProperties(intelligenceRequest,intelligenceQuery);
//                        Integer totalPrice = 500;
//                        com.toutiao.app.domain.Intelligence.PriceTrendDo fhpt = intelligenceFhPricetrendService.queryPriceTrend(totalPrice);
//                        PriceTrendDo priceTrendDo = new PriceTrendDo();
//                        BeanUtils.copyProperties(fhpt,priceTrendDo);
//                        return new ResponseEntity<PriceTrendDo>(priceTrendDo, HttpStatus.OK);
//                    } catch (Exception e) {
//                        log.error("Couldn't serialize response for content type application/json", e);
//                        return new ResponseEntity<PriceTrendDo>(HttpStatus.INTERNAL_SERVER_ERROR);
//                    }
//                }
//
//                return new ResponseEntity<PriceTrendDo>(HttpStatus.NOT_IMPLEMENTED);
//    }

//    @Override
//    public ResponseEntity<PriceRatioDo> queryTd(@ApiParam(value = "intelligenceRequest" ,required=true )  @Valid @RequestBody IntelligenceRequest intelligenceRequest) {
//                String accept = request.getHeader("Accept");
//                if (accept != null && accept.contains("application/json")) {
//                    try {
//                        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
//                        BeanUtils.copyProperties(intelligenceRequest,intelligenceQuery);
//                        Integer totalPrice = 500;
//                        com.toutiao.app.domain.Intelligence.PriceRatioDo fhtp = intelligenceFhTdService.queryTd(totalPrice);
//                        PriceRatioDo priceRatioDo = new PriceRatioDo();
//                        BeanUtils.copyProperties(fhtp,priceRatioDo);
//                        return new ResponseEntity<PriceRatioDo>(priceRatioDo, HttpStatus.OK);
//                    } catch (Exception e) {
//                        log.error("Couldn't serialize response for content type application/json", e);
//                        return new ResponseEntity<PriceRatioDo>(HttpStatus.INTERNAL_SERVER_ERROR);
//                    }
//                }
//
//                return new ResponseEntity<PriceRatioDo>(HttpStatus.NOT_IMPLEMENTED);
//    }

    @Override
    public ResponseEntity<IntelligenceFh> queryUserChoice(@ApiParam(value = "intelligenceRequest" ,required=true )  @Valid @RequestBody IntelligenceRequest intelligenceRequest) {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
                        BeanUtils.copyProperties(intelligenceRequest,intelligenceQuery);
                        com.toutiao.web.domain.intelligenceFh.IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryUserChoice(intelligenceQuery);
                        if(StringTool.isNotBlank(intelligenceFh)){
                            if(5>intelligenceFh.getPlotCount()){//intelligenceFh.getPlotCount()-5<5
                                intelligenceFh.setPlotCount(0);
                            }
                        }
                        IntelligenceFh intelligenceFh1 = new IntelligenceFh();
                        BeanUtils.copyProperties(intelligenceFh,intelligenceFh1);
                        return new ResponseEntity<IntelligenceFh>(intelligenceFh1, HttpStatus.OK);
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<IntelligenceFh>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<IntelligenceFh>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<IntelligenceResponse> showUserPortrayal(@NotNull @ApiParam(value = "reportId", required = true) @Valid @RequestParam(value = "reportId", required = true) String reportId) {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        IntelligenceDo intelligenceDo = new IntelligenceDo();
                        com.toutiao.web.domain.intelligenceFh.IntelligenceFhTdRatio intelligenceFhTdRatio = new com.toutiao.web.domain.intelligenceFh.IntelligenceFhTdRatio();
                        com.toutiao.app.domain.Intelligence.PriceTrendDo fhpt = new com.toutiao.app.domain.Intelligence.PriceTrendDo();
                        com.toutiao.app.domain.Intelligence.PriceRatioDo fhtp = new com.toutiao.app.domain.Intelligence.PriceRatioDo();
                        fhtp.setRatio(intelligenceFhTdRatio);
                        IntelligenceResponse intelligenceResponse = new IntelligenceResponse();
                        if(StringTool.isNotBlank(reportId)){

//                            IntelligenceFhRes intelligenceFhRes = intelligenceFhResService.queryResById(Integer.valueOf(reportId));
                            com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes intelligenceFhRes = intelligenceFhResService.queryResById(Integer.valueOf(reportId));

                            if (StringTool.isNotBlank(intelligenceFhRes)) {

                                List list = JSONObject.parseArray(((PGobject) intelligenceFhRes.getFhResult()).getValue());
                                for (int i = 0; i < list.size(); i++) {
                                    if (StringTool.isNotEmpty(((JSONObject) list.get(i)).get("newcode"))) {
                                        String plotId = ((JSONObject) list.get(i)).get("newcode").toString();

                                        PlotDetailsDo plotDetailsDo = plotService.queryPlotByPlotId(plotId);
                                        ((JSONObject) list.get(i)).put("esfPrice", plotDetailsDo.getAvgPrice());
                                        ((JSONObject) list.get(i)).put("plotImage", plotDetailsDo.getPhoto().toString().replaceAll("[\\[\\]]", ""));

                                    }
                                }
                                intelligenceFhRes.setFhResult(JSONObject.toJSONString(list));
                                String datajson = list.toString();
//                                fhpt = intelligenceFhPricetrendService.queryPriceTrend(intelligenceFhRes.getTotalPrice());
                                fhpt = intelligenceFhPricetrendService.queryPriceTrend(intelligenceFhRes.getTotalPrice());
//                                fhtp = intelligenceFhTdService.queryTd(intelligenceFhRes.getTotalPrice());
                                fhtp = intelligenceFhTdService.queryTd(intelligenceFhRes.getTotalPrice());
                                intelligenceDo.setDatajson(datajson);
                                intelligenceDo.setTotalPrice(intelligenceFhRes.getTotalPrice());
                                if(StringTool.isNotEmpty(intelligenceFhRes.getLayoutArray())){
                                    intelligenceDo.setLayout(intelligenceFhRes.getLayoutArray());
                                }else{
                                    intelligenceDo.setLayout(intelligenceFhRes.getLayoutArray());
                                }

                                if(StringTool.isNotEmpty(intelligenceFhRes.getDistrictArray())){
                                    intelligenceDo.setDistrict(intelligenceFhRes.getDistrictArray());
                                }else{
                                    intelligenceDo.setDistrict(intelligenceFhRes.getDistrictArray());
                                }

                                intelligenceDo.setCollectStatus(intelligenceFhRes.getCollectStatus());
                                intelligenceDo.setBackUrl(request.getRequestURI());
                            }
                        }
                        intelligenceDo.setFhpt(fhpt);
                        intelligenceDo.setFhtp(fhtp);
                        BeanUtils.copyProperties(intelligenceDo,intelligenceResponse);
                        return new ResponseEntity<IntelligenceResponse>(intelligenceResponse, HttpStatus.OK);
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<IntelligenceResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<IntelligenceResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<IntelligenceFhRes> showUserPortrayal(@ApiParam(value = "intelligenceRequest" ,required=true )  @Valid @RequestBody IntelligenceRequest intelligenceRequest) {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        String userPhone = null;
                        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
                        BeanUtils.copyProperties(intelligenceRequest,intelligenceQuery);
                        com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes intelligenceFhRes = new com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes();
                        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
                        UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);

                        if(null != userLoginResponse){
                            UserBasicDo userBasic  =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
                            userPhone = userBasic.getPhone();
                        }
                        //调用生成报告页展示数据接口
                        //通过相关数据获取报告生成都数据 保存到相应的数据表中

                        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis()));
                        intelligenceQuery.setCreateTime(date);
                        intelligenceFhRes = intelligenceFindHouseService.intelligenceFindHouseServiceByType(intelligenceQuery,userPhone);
                        IntelligenceFhRes intelligenceFhRes1 = new IntelligenceFhRes();
                        BeanUtils.copyProperties(intelligenceFhRes,intelligenceFhRes1);
                        return new ResponseEntity<IntelligenceFhRes>(intelligenceFhRes1, HttpStatus.OK);
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<IntelligenceFhRes>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<IntelligenceFhRes>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<String> xuanZeLeiXing(@NotNull @ApiParam(value = "userType", required = true) @Valid @RequestParam(value = "userType", required = true) String userType) {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {

                        return new ResponseEntity<String>(userType, HttpStatus.OK);
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

}
