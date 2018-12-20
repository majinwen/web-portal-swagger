//package com.toutiao.web.apiimpl.rest.Intelligence;
//
//import com.alibaba.fastjson.JSONObject;
//import com.toutiao.app.api.chance.request.homepage.UserFavoriteConditionRequest;
//import com.toutiao.app.api.chance.response.Intelligence.IntelligenceResponse;
//import com.toutiao.app.api.chance.response.user.UserLoginResponse;
//import com.toutiao.app.domain.Intelligence.IntelligenceDo;
//import com.toutiao.app.domain.Intelligence.PriceRatioDo;
//import com.toutiao.app.domain.Intelligence.PriceTrendDo;
//import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
//import com.toutiao.app.domain.plot.PlotDetailsDo;
//import com.toutiao.app.domain.user.UserBasicDo;
//import com.toutiao.app.service.Intelligence.HomePageReportService;
//import com.toutiao.app.service.user.UserBasicInfoService;
//import com.toutiao.web.common.util.CookieUtils;
//import com.toutiao.web.common.util.StringTool;
//import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
//import com.toutiao.web.domain.intelligenceFh.IntelligenceFhTdRatio;
//import com.toutiao.web.service.intelligence.IntelligenceFhPricetrendService;
//import com.toutiao.web.service.intelligence.IntelligenceFhResService;
//import com.toutiao.web.service.intelligence.IntelligenceFhTdService;
//import com.toutiao.web.service.plot.PlotService;
//import org.postgresql.util.PGobject;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
//@RestController
//@RequestMapping("/rest/findhouse/byHouseReport")
//public class HomePageReportController {
//    @Autowired
//    private UserBasicInfoService userBasicInfoService;
//    @Autowired
//    private IntelligenceFhTdService intelligenceFhTdService;
//    @Autowired
//    private HomePageReportService homePageReportService;
//    @Autowired
//    private IntelligenceFhResService intelligenceFhResService;
//    @Autowired
//    private IntelligenceFhPricetrendService intelligenceFhPricetrendService;
//    @Autowired
//    private PlotService plotService;
//
//    /**
//     * 保存报告
//     * @param userFavoriteConditionRequest
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/saveHomePageReport",method = RequestMethod.POST)
//    public Integer saveHomePageReport(HttpServletRequest request, @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest){
//        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
//        BeanUtils.copyProperties(userFavoriteConditionRequest,userFavoriteConditionDoQuery);
//        Integer result = homePageReportService.saveHomePageReport(request, userFavoriteConditionDoQuery);
//        return result;
//    }
//
//    /**
//     * 查询报告
//     * @param reportId
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/getHomePageReport",method = RequestMethod.GET)
//    public IntelligenceResponse getHomePageReport(@RequestParam("reportId") String reportId,@RequestParam(value = "title",required = false) String title,HttpServletRequest request, HttpServletResponse response) {
//        IntelligenceDo intelligenceDo = new IntelligenceDo();
//        IntelligenceFhTdRatio intelligenceFhTdRatio = new IntelligenceFhTdRatio();
//        PriceTrendDo fhpt = new PriceTrendDo();
//        PriceRatioDo fhtp = new PriceRatioDo();
//        fhtp.setRatio(intelligenceFhTdRatio);
//        IntelligenceResponse intelligenceResponse = new IntelligenceResponse();
//        if(StringTool.isNotBlank(reportId)){
//
//            if(StringTool.isNotBlank(title)){
//                String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
//                UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);
//                UserBasicDo userBasic =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
//                intelligenceFhResService.updateMyReportCollectStatus(reportId, userBasic.getPhone());
//            }
//            IntelligenceFhRes intelligenceFhRes = intelligenceFhResService.queryResById(Integer.valueOf(reportId));
//
//            if (StringTool.isNotBlank(intelligenceFhRes)) {
//
//                List list = JSONObject.parseArray(((PGobject) intelligenceFhRes.getFhResult()).getValue());
//                for (int i = 0; i < list.size(); i++) {
//                    if (StringTool.isNotEmpty(((JSONObject) list.get(i)).get("newcode"))) {
//                        String plotId = ((JSONObject) list.get(i)).get("newcode").toString();
//
//                        PlotDetailsDo plotDetailsDo = plotService.queryPlotByPlotId(plotId);
//                        ((JSONObject) list.get(i)).put("esfPrice", plotDetailsDo.getAvgPrice());
//                        ((JSONObject) list.get(i)).put("plotImage", plotDetailsDo.getPhoto().toString().replaceAll("[\\[\\]]", ""));
//
//                    }
//                }
//
//                intelligenceFhRes.setFhResult(JSONObject.toJSONString(list));
//
//            String datajson = list.toString();
//                fhpt = intelligenceFhPricetrendService.queryPriceTrend(intelligenceFhRes.getTotalPrice());
//                fhtp = intelligenceFhTdService.queryTd(intelligenceFhRes.getTotalPrice());
//                intelligenceDo.setDatajson(datajson);
//            intelligenceDo.setTotalPrice(intelligenceFhRes.getTotalPrice());
//            if(StringTool.isNotEmpty(intelligenceFhRes.getLayoutArray())){
//                intelligenceDo.setLayout(intelligenceFhRes.getLayoutArray());
//            }else{
//                intelligenceDo.setLayout(intelligenceFhRes.getLayoutArray());
//            }
//
//            if(StringTool.isNotEmpty(intelligenceFhRes.getDistrictArray())){
//                intelligenceDo.setDistrict(intelligenceFhRes.getDistrictArray());
//            }else{
//                intelligenceDo.setDistrict(intelligenceFhRes.getDistrictArray());
//            }
//            intelligenceDo.setCollectStatus(intelligenceFhRes.getCollectStatus());
//            intelligenceDo.setBackUrl(request.getRequestURI());
//            }
//        }
//        intelligenceDo.setFhpt(fhpt);
//        intelligenceDo.setFhtp(fhtp);
//        BeanUtils.copyProperties(intelligenceDo,intelligenceResponse);
//        return intelligenceResponse;
//
//    }
//}
