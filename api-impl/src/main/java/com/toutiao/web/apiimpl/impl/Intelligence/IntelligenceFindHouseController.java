package com.toutiao.web.apiimpl.impl.Intelligence;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import com.toutiao.web.apiimpl.authentication.GetUserMethod;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.Com35Aes;
import com.toutiao.web.common.util.Constant;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhPricetrend;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhTd;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFh;
import com.toutiao.web.domain.query.IntelligenceQuery;
import com.toutiao.web.service.intelligence.IntelligenceFhPricetrendService;
import com.toutiao.web.service.intelligence.IntelligenceFhResService;
import com.toutiao.web.service.intelligence.IntelligenceFhTdService;
import com.toutiao.web.service.intelligence.IntelligenceFindHouseService;
import freemarker.template.TemplateModelException;
import org.apache.poi.ss.formula.functions.Na;
import org.postgresql.util.PGobject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/{citypath}/findhouse")
public class IntelligenceFindHouseController {

    @Autowired
    private IntelligenceFhTdService intelligenceFhTdService;
    @Autowired
    private IntelligenceFhResService intelligenceFhResService;
    @Autowired
    private IntelligenceFindHouseService intelligenceFindHouseService;
    @Autowired
    private IntelligenceFhPricetrendService intelligenceFhPricetrendService;


    /**
     * 功能描述：查看我的报告
     *
     * @param
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/26 13:57
     */
    @RequestMapping("/queryMyReport")
    public String getMyReport(HttpServletRequest request, Model model) {
        model.addAttribute("backUrl", request.getRequestURI());
        //从cookie中获取用户手机号码
        String usePhone = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);
        if (StringTool.isNotBlank(usePhone)) {
            //查询用户是否有报告数据
            List<IntelligenceFhRes> userReport = intelligenceFhResService.queryUserReport(usePhone);
            if (StringTool.isNotBlank(userReport)&&userReport.size()>0) {
                model.addAttribute("userReport", userReport);
            }else{
                return "emptyReport";
            }
        } else {
           return "/user/login";
        }
        //跳转到报告页
        return "myReport";
    }

    /**
     * 功能描述：收藏报告
     *
     * @return com.toutiao.web.common.restmodel.NashResult
     * @author zhw
     * @date 2018/1/5 10:24
     */
    @RequestMapping("/collectMyReport")
    @ResponseBody
    public NashResult collectMyReport(HttpServletRequest request,HttpServletResponse response, Model model,
                                      @RequestParam("reportId") String reportId) {

        if (StringTool.isNotBlank(reportId)) {
            String usePhone = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);
            if (StringTool.isBlank(usePhone)) {
                //前台判断状态 然后跳转到登陆页面
                 return NashResult.Fail("no-login","");
            }else {
                //更改当前报告的状态
                int result = intelligenceFhResService.updateMyReportCollectStatus(reportId, usePhone);
                if (result != 0) {
                    //收藏成功
                    return NashResult.build("ok");
                }else{
                    return NashResult.Fail("cancel","");
                }
            }
        }
        return NashResult.Fail("cancel","");
    }

    /**
     * 功能描述：取消收藏
     *
     * @return java.lang.String
     * @author zhw
     * @date 2018/1/4 20:06
     */
    @RequestMapping("/cancleMyReport/{reportId}")
    @ResponseBody
    public NashResult cancleMyReport(HttpServletRequest request,@PathVariable("reportId") String reportId){

        try {
            String phone = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);
            int count = intelligenceFhResService.deleteMyReport(reportId, phone);
            if(count != 0){
                return NashResult.build("ok");
            }else{
                return NashResult.Fail("fail","");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return NashResult.Fail("fail","");
        }
    }
    /**
     * 功能描述：跳转功能，跳转到选择类型页面
     *
     * @param
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/18 18:28
     */
    @RequestMapping(value = "")
    public String goToStartRobot() {
        return "intelligent-find";
    }

    /**
     * 智能找房--用户筛选
     * @param intelligenceQuery
     * @return
     */
    @RequestMapping(value = "/queryUserChoice")
    @ResponseBody
    public NashResult queryUserChoice(IntelligenceQuery intelligenceQuery){

        IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryUserChoice(intelligenceQuery);
        if(StringTool.isNotBlank(intelligenceFh)){
            if(5>intelligenceFh.getPlotCount()){//intelligenceFh.getPlotCount()-5<5
                intelligenceFh.setPlotCount(0);
            }
        }
        return NashResult.build(intelligenceFh);
    }




    /**
     * 功能描述：判断选择的类型，进行跳转
     *
     * @param userType, model
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/18 18:44
     */
    @RequestMapping(value = "/xuanzeleixing")
    @ResponseBody
    public NashResult xuanZeLeiXing(@RequestParam(value = "userType", required = true) String userType) {
        return NashResult.build(userType);
    }




    /**
     * 功能描述：区域筛选小区数量
     *
     * @param intelligenceQuery
     * @return com.toutiao.web.common.restmodel.NashResult
     * @author zhw
     * @date 2017/12/26 21:45
     */
    @RequestMapping("/queryPlotCountByDistrict")
    @ResponseBody
    public NashResult queryPlotCountByDistrict(IntelligenceQuery intelligenceQuery, Model model) {

        //通过页面传递过来的区域等信息赛选小区数量
        IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryPlotCountByDistrict(intelligenceQuery);
        //报告生成页
        return NashResult.build(intelligenceFh);
    }

    /**
     * 功能描述：过渡页
     *
     * @param model
     */
    @RequestMapping("/showUserPortrayal")
    @ResponseBody
    public NashResult showUserPortrayal(Model model, IntelligenceQuery intelligenceQuery) {
        //调用生成报告页展示数据接口
        //通过相关数据获取报告生成都数据 保存到相应的数据表中

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis()));
        intelligenceQuery.setCreateTime(date);
        IntelligenceFhRes intelligenceFhRes = intelligenceFindHouseService.intelligenceFindHouseServiceByType(intelligenceQuery);

        if (StringTool.isNotBlank(intelligenceFhRes)) {
            return NashResult.build(intelligenceFhRes);
        }
        return NashResult.build(0);
    }

    /**
     * 功能描述：打开报告页数据信息
     *
     * @return java.lang.String
     * @author
     * @date 2018/1/4 11:39
     */
    @RequestMapping("/showMyReport/{reportId}")
    public String showUserPortrayal(@PathVariable("reportId") String reportId,
                                    @RequestParam(value = "title",required = false) String title,
                                    Model model,HttpServletRequest request,HttpServletResponse response) {
        try {
            if (StringTool.isNotBlank(reportId)) {
                if(StringTool.isNotBlank(title)){
                    String usePhone = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);
                    intelligenceFhResService.updateMyReportCollectStatus(reportId, usePhone);
                }
                //查询用户是否有报告数据
                IntelligenceFhRes intelligenceFhRes = intelligenceFhResService.queryResById(Integer.valueOf(reportId));
                if (StringTool.isNotBlank(intelligenceFhRes)) {
                    //String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(Long.parseLong(intelligenceFhRes.getCreateTime())));
                    //intelligenceFhRes.setCreateTime(date);
                    Integer plotTotal = null;
                    if (StringTool.isNotBlank(intelligenceFhRes.getDownPayment()) && StringTool.isNotBlank(intelligenceFhRes.getMonthPayment())) {
                        plotTotal = Integer.valueOf(intelligenceFhRes.getDownPayment()) + (Integer.valueOf(intelligenceFhRes.getMonthPayment()) * 12 * 30 / 10000);
                    } else {
                        plotTotal = intelligenceFhRes.getTotalPrice();
                    }
                    Map<String, Object> fhpt = intelligenceFhPricetrendService.queryPriceTrend(intelligenceFhRes.getTotalPrice());
                    Map<String, Object> fhtp = intelligenceFhTdService.queryTd(intelligenceFhRes.getTotalPrice());
                    model.addAttribute("fhpt", fhpt);
                    model.addAttribute("trend", JSON.toJSON(fhtp.getOrDefault("trend", new ArrayList<String>())).toString());
                    String datajson = ((PGobject) intelligenceFhRes.getFhResult()).getValue();
                    model.addAttribute("ptlists", JSON.toJSON(fhpt.getOrDefault("ptlists", new ArrayList<String>())).toString());
                    model.addAttribute("datajson", datajson);
                    model.addAttribute("fhtp", fhtp);
                    model.addAttribute("reportId", reportId);
                    model.addAttribute("intelligenceFhRes", intelligenceFhRes);
                    model.addAttribute("backUrl", request.getRequestURI());
                    return "intelligent-report";
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "404";
        }
        return "404";
    }

    /**
     * 报告页价格趋势
     *
     * @param intelligenceQuery
     * @return
     */
    @RequestMapping("/queryPt")
    @ResponseBody
    public NashResult queryPt(IntelligenceQuery intelligenceQuery) {

        Integer totalPrice = 500;
//        Integer totalPrice = intelligenceQuery.getPreconcTotal();
        Map<String, Object> fhpt = intelligenceFhPricetrendService.queryPriceTrend(totalPrice);
        return NashResult.build(fhpt);
    }

    /**
     * 报告页供需趋势
     *
     * @param intelligenceQuery
     * @return
     */
    @RequestMapping("/queryTd")
    @ResponseBody
    public NashResult queryTd(IntelligenceQuery intelligenceQuery) {

        Integer totalPrice = 500;
//        Integer totalPrice = intelligenceQuery.getPreconcTotal();
        Map<String, Object> fhtp = intelligenceFhTdService.queryTd(totalPrice);
        return NashResult.build(fhtp);
    }


}
