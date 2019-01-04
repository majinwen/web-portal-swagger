//package com.toutiao.web.apiimpl.rest.Intelligence;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.toutiao.app.api.chance.request.Intelligence.IntelligenceRequest;
//import com.toutiao.app.api.chance.response.Intelligence.IntelligenceResponse;
//import com.toutiao.app.api.chance.response.Intelligence.ReportResponse;
//import com.toutiao.app.api.chance.response.user.UserLoginResponse;
//import com.toutiao.app.domain.Intelligence.IntelligenceDo;
//import com.toutiao.app.domain.Intelligence.PriceRatioDo;
//import com.toutiao.app.domain.Intelligence.PriceTrendDo;
//import com.toutiao.app.domain.plot.PlotDetailsDo;
//import com.toutiao.app.domain.user.UserBasicDo;
//import com.toutiao.app.service.user.UserBasicInfoService;
//import com.toutiao.web.common.util.CookieUtils;
//import com.toutiao.web.common.util.StringTool;
//import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
//import com.toutiao.web.domain.intelligenceFh.IntelligenceFh;
//import com.toutiao.web.domain.intelligenceFh.IntelligenceFhTdRatio;
//import com.toutiao.web.domain.query.IntelligenceQuery;
//import com.toutiao.web.service.intelligence.IntelligenceFhPricetrendService;
//import com.toutiao.web.service.intelligence.IntelligenceFhResService;
//import com.toutiao.web.service.intelligence.IntelligenceFhTdService;
//import com.toutiao.web.service.intelligence.IntelligenceFindHouseService;
//import com.toutiao.web.service.plot.PlotService;
//import org.postgresql.util.PGobject;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//@Controller
//@RequestMapping("/{citypath}/findhouse/dongfangdi")
//public class IntelligenceFindHouseRestController {
//
//    @Autowired
//    private IntelligenceFhTdService intelligenceFhTdService;
//    @Autowired
//    private IntelligenceFhResService intelligenceFhResService;
//    @Autowired
//    private IntelligenceFindHouseService intelligenceFindHouseService;
//    @Autowired
//    private IntelligenceFhPricetrendService intelligenceFhPricetrendService;
//    @Autowired
//    private UserBasicInfoService userBasicInfoService;
//    @Autowired
//    private PlotService plotService;
//
//
//
//    /**
//     * 功能描述：跳转功能，跳转到选择类型页面
//     *
//     * @param
//     * @return java.lang.String
//     * @author zhw
//     * @date 2017/12/18 18:28
//     */
//    @RequestMapping(value = "",method = RequestMethod.GET)
//    public String goToStartRobot() {
//        return "app/intelligent-find";
//    }
//
//
//    /**
//     * 功能描述：打开报告页数据信息
//     *
//     * @return java.lang.String
//     * @author
//     * @date 2018/1/4 11:39
//     */
//    @RequestMapping(value = "/showMyReport/{reportId}",method = RequestMethod.GET)
//    public IntelligenceResponse showUserPortrayal(@RequestParam("reportId") String reportId, HttpServletRequest request) {
//        IntelligenceDo intelligenceDo = new IntelligenceDo();
//        IntelligenceFhTdRatio intelligenceFhTdRatio = new IntelligenceFhTdRatio();
//        PriceTrendDo fhpt = new PriceTrendDo();
//        PriceRatioDo fhtp = new PriceRatioDo();
//        fhtp.setRatio(intelligenceFhTdRatio);
//        IntelligenceResponse intelligenceResponse = new IntelligenceResponse();
//        if(StringTool.isNotBlank(reportId)){
//
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
//                intelligenceFhRes.setFhResult(JSONObject.toJSONString(list));
//                String datajson = list.toString();
//                fhpt = intelligenceFhPricetrendService.queryPriceTrend(intelligenceFhRes.getTotalPrice());
//                fhtp = intelligenceFhTdService.queryTd(intelligenceFhRes.getTotalPrice());
//                intelligenceDo.setDatajson(datajson);
//                intelligenceDo.setTotalPrice(intelligenceFhRes.getTotalPrice());
//                if(StringTool.isNotEmpty(intelligenceFhRes.getLayoutArray())){
//                    intelligenceDo.setLayout(intelligenceFhRes.getLayoutArray());
//                }else{
//                    intelligenceDo.setLayout(intelligenceFhRes.getLayoutArray());
//                }
//
//                if(StringTool.isNotEmpty(intelligenceFhRes.getDistrictArray())){
//                    intelligenceDo.setDistrict(intelligenceFhRes.getDistrictArray());
//                }else{
//                    intelligenceDo.setDistrict(intelligenceFhRes.getDistrictArray());
//                }
//
//                intelligenceDo.setCollectStatus(intelligenceFhRes.getCollectStatus());
//                intelligenceDo.setBackUrl(request.getRequestURI());
//            }
//        }
//        intelligenceDo.setFhpt(fhpt);
//        intelligenceDo.setFhtp(fhtp);
//        BeanUtils.copyProperties(intelligenceDo,intelligenceResponse);
//        return intelligenceResponse;
//    }
//
//
//    /**
//     * 功能描述：收藏报告
//     *
//     * @return com.toutiao.web.common.restmodel.NashResult
//     * @author zhw
//     * @date 2018/1/5 10:24
//     */
//    @RequestMapping(value = "/collectMyReport",method = RequestMethod.GET)
//    @ResponseBody
//    public String collectMyReport(HttpServletRequest request,@RequestParam("reportId") String reportId) {
//
//        if (StringTool.isNotBlank(reportId)) {
//            String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
//            UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);
//            UserBasicDo userBasic = null;
//            if(null != userLoginResponse){
//                userBasic =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
//            }
//            if (StringTool.isBlank(userBasic)) {
//                //前台判断状态 然后跳转到登录页面
//                return "no-login";
//            }else {
//                //更改当前报告的状态
//                int result = intelligenceFhResService.updateMyReportCollectStatus(reportId, userBasic.getPhone());
//                if (result != 0) {
//                    //收藏成功
//                    return "ok";
//                }else{
//                    return "cancel";
//                }
//            }
//        }
//        return "cancel";
//    }
//
//    /**
//     * 功能描述：取消收藏
//     *
//     * @return java.lang.String
//     * @author zhw
//     * @date 2018/1/4 20:06
//     */
//    @RequestMapping(value = "/cancleMyReport/{reportId}",method = RequestMethod.GET)
//    @ResponseBody
//    public String cancleMyReport(HttpServletRequest request,@PathVariable("reportId") String reportId){
//
//        try {
//            String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
//            UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);
//            UserBasicDo userBasic = null;
//            if(null != userLoginResponse){
//                userBasic =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
//            }
//            int count = intelligenceFhResService.deleteMyReport(reportId);
//            if(count != 0){
//                return "ok";
//            }else{
//                return "fail";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "fail";
//        }
//    }
//
//
//
//
//    /**
//     * 功能描述：查看我的报告
//     *
//     * @param
//     * @return java.lang.String
//     * @author zhw
//     * @date 2017/12/26 13:57
//     */
//    @RequestMapping(value = "/queryMyReport",method = RequestMethod.GET)
//    public ReportResponse getMyReport(HttpServletRequest request) {
//        ReportResponse reportResponse = new ReportResponse();
//        reportResponse.setBackUrl(request.getRequestURI());
//        //从cookie中获取用户手机号码
//        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
//        UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);
//        UserBasicDo userBasic = null;
//        List userReport = new ArrayList<>();
//        if(null != userLoginResponse){
//            userBasic =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
//            //查询用户是否有报告数据
//            userReport = intelligenceFhResService.queryUserReport(userBasic.getPhone());
//        }
//        if (StringTool.isNotBlank(userReport)&&userReport.size()>0) {
//            reportResponse.setUserReport(userReport);
//        }
//        return reportResponse;
//    }
//
//
//
//
//
//    /**
//     * 智能找房--用户筛选
//     * @param intelligenceRequest
//     * @return
//     */
//    @RequestMapping(value = "/queryUserChoice",method = RequestMethod.POST)
//    @ResponseBody
//    public IntelligenceFh queryUserChoice(@RequestBody IntelligenceRequest intelligenceRequest){
//        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
//        BeanUtils.copyProperties(intelligenceRequest,intelligenceQuery);
//        IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryUserChoice(intelligenceQuery);
//        if(StringTool.isNotBlank(intelligenceFh)){
//            if(5>intelligenceFh.getPlotCount()){//intelligenceFh.getPlotCount()-5<5
//                intelligenceFh.setPlotCount(0);
//            }
//        }
//        return intelligenceFh;
//    }
//
//
//
//
//    /**
//     * 功能描述：判断选择的类型，进行跳转
//     *
//     * @param userType
//     * @return java.lang.String
//     * @author zhw
//     * @date 2017/12/18 18:44
//     */
//    @RequestMapping(value = "/xuanzeleixing",method = RequestMethod.GET)
//    @ResponseBody
//    public String xuanZeLeiXing(@RequestParam(value = "userType", required = true) String userType) {
//        return userType;
//    }
//
//
//
//
//    /**
//     * 功能描述：区域筛选小区数量
//     *
//     * @param intelligenceRequest
//     * @return com.toutiao.web.common.restmodel.NashResult
//     * @author zhw
//     * @date 2017/12/26 21:45
//     */
//    @RequestMapping(value = "/queryPlotCountByDistrict",method = RequestMethod.POST)
//    @ResponseBody
//    public IntelligenceFh queryPlotCountByDistrict(@RequestBody IntelligenceRequest intelligenceRequest) {
//        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
//        BeanUtils.copyProperties(intelligenceRequest,intelligenceQuery);
//        //通过页面传递过来的区域等信息赛选小区数量
//        IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryPlotCountByDistrict(intelligenceQuery);
//        //报告生成页
//        return intelligenceFh;
//    }
//
//    /**
//     * 功能描述：过渡页
//     *
//     * @param intelligenceRequest
//     */
//    @RequestMapping(value = "/showUserPortrayal",method = RequestMethod.POST)
//    @ResponseBody
//    public IntelligenceFhRes showUserPortrayal(HttpServletRequest request, @RequestBody IntelligenceRequest intelligenceRequest) {
//        String userPhone = null;
//        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
//        BeanUtils.copyProperties(intelligenceRequest,intelligenceQuery);
//        IntelligenceFhRes intelligenceFhRes = new IntelligenceFhRes();
//        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
//        UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);
//
//        if(null != userLoginResponse){
//            UserBasicDo userBasic  =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
//            userPhone = userBasic.getPhone();
//        }
//        //调用生成报告页展示数据接口
//        //通过相关数据获取报告生成都数据 保存到相应的数据表中
//
//        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis()));
//        intelligenceQuery.setCreateTime(date);
//        intelligenceFhRes = intelligenceFindHouseService.intelligenceFindHouseServiceByType(intelligenceQuery,userPhone);
//        return intelligenceFhRes;
//    }
//
//
////
////    /**
////     * 报告页价格趋势
////     *
////     * @param intelligenceRequest
////     * @return
////     */
////    @RequestMapping(value = "/queryPt",method = RequestMethod.POST)
////    @ResponseBody
////    public PriceTrendDo queryPt(@RequestBody IntelligenceRequest intelligenceRequest) {
////        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
////        BeanUtils.copyProperties(intelligenceRequest,intelligenceQuery);
////        Integer totalPrice = 500;
//////        Integer totalPrice = intelligenceQuery.getPreconcTotal();
//////        Map<String, Object> fhpt = intelligenceFhPricetrendService.queryPriceTrend(totalPrice);
////        PriceTrendDo fhpt = intelligenceFhPricetrendService.queryPriceTrend(totalPrice);
////        return fhpt;
////    }
////
////    /**
////     * 报告页供需趋势
////     *
////     * @param intelligenceRequest
////     * @return
////     */
////    @RequestMapping(value = "/queryTd",method = RequestMethod.POST)
////    @ResponseBody
////    public PriceRatioDo queryTd(@RequestBody IntelligenceRequest intelligenceRequest) {
////        IntelligenceQuery intelligenceQuery = new IntelligenceQuery();
////        BeanUtils.copyProperties(intelligenceRequest,intelligenceQuery);
////        Integer totalPrice = 500;
//////        Integer totalPrice = intelligenceQuery.getPreconcTotal();
//////        Map<String, Object> fhtp = intelligenceFhTdService.queryTd(totalPrice);
////        PriceRatioDo fhtp = intelligenceFhTdService.queryTd(totalPrice);
////        return fhtp;
////    }
//
//
//}
