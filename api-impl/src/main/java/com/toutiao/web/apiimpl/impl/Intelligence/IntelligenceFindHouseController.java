package com.toutiao.web.apiimpl.impl.Intelligence;


import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.apiimpl.authentication.GetUserMethod;
import com.toutiao.web.common.restmodel.NashResult;
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

        //从cookie中获取用户手机号码
        //String usePhone = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);
        String usePhone="15601676403";
        if (StringTool.isNotBlank(usePhone)) {
            //查询用户是否有报告数据
            List<IntelligenceFhRes> userReport = intelligenceFhResService.queryUserReport(usePhone);
            if (StringTool.isNotBlank(userReport)) {
                model.addAttribute("userReport", userReport);
            }
            model.addAttribute("message", "没有报告记录！");
        } else {
            model.addAttribute("message", "登陆后才能显示相应的报告信息！");
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
            //判断用户是否登陆
            //从cookie中获取用户手机号码
            String usePhone = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);
            if (StringTool.isBlank(usePhone)) {
                //前台判断状态 然后跳转到登陆页面
                return NashResult.Fail("fail","未登录!");
            }
            //更改当前报告的状态
            int result = intelligenceFhResService.updateMyReportCollectStatus(reportId, usePhone);
            if (result == 0) {
                //收藏成功
                return NashResult.build("ok");
            }
        }
        return NashResult.Fail("fail","收藏失败!");
    }


    /**
     * 功能描述：删除报告
     *
     * @return java.lang.String
     * @author zhw
     * @date 2018/1/4 20:06
     */
    @RequestMapping("/deleteMyReport/{reportId}/{phone}")
    public String deleteMyReport(@PathVariable("reportId") String reportId, @PathVariable("phone") String phone, Model model) {
        int count = intelligenceFhResService.deleteMyReport(reportId, phone);
        if (count != 0) {
            model.addAttribute("message", "删除失败！");
        }
        return "redirect:/{citypath}/findhouse/queryMyReport";
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
     * 功能描述：异步根据价钱获取小区数量与相应的比率
     *
     * @param intelligenceQuery
     * @return com.toutiao.web.common.restmodel.NashResult
     * @author zhw
     * @date 2017/12/18 21:05
     */
    @RequestMapping("/goCheckPrice")
    @ResponseBody
    public NashResult plotCountByTotalPrice(IntelligenceQuery intelligenceQuery) {
        IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryUserCheckPrice(intelligenceQuery);
        //获取根据用户条件筛选的小区数量和相应比率
        return NashResult.build(intelligenceFh);
    }

    /**
     * 测试
     *
     * @param intelligenceQuery
     * @return
     */
    @RequestMapping("/intelligenceFindHouseByType")
    @ResponseBody
    public String intelligenceFindHouseByType(IntelligenceQuery intelligenceQuery, Model model) {
        Double plotTotalFirst = null;
        Double plotTotalEnd = null;
        intelligenceQuery.setPreconcTotal("450");
        intelligenceQuery.setUserType("1");
        intelligenceQuery.setUserPortrayalType(5);
        intelligenceQuery.setLayOut(3);
        intelligenceQuery.setDistrictId("105037");
        intelligenceQuery.setSchoolFlag(1);
        intelligenceQuery.setHospitalFlag(1);
        String preconcTotal = intelligenceQuery.getPreconcTotal();
        intelligenceQuery.setHasChild(1);
        intelligenceQuery.setHasOldman(1);
        Integer AIID = intelligenceFindHouseService.intelligenceFindHouseServiceByType(intelligenceQuery);
        model.addAttribute("AIId", AIID);
        return "intelligent-report";
    }

    /**
     * 功能描述：根据户型和总价查询小区数量
     *
     * @param intelligenceQuery, model
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/26 20:56
     */
    @RequestMapping("/userCheckCategoryPage")
    @ResponseBody
    public NashResult queryPlotCountByCategory(IntelligenceQuery intelligenceQuery, Model model) {
        //根据户型与总价条件赛选条件
        IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryUserCheckPriceAndCategory(intelligenceQuery);
        if (StringTool.isNotBlank(intelligenceFh)) {
            intelligenceFh.setRatio(intelligenceFh.getRatio() / 1000);
        }
        return NashResult.build(intelligenceFh);
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
     * 功能描述：报告页-用户画像
     *
     * @param intelligenceQuery, model
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/27 15:17
     */
    /*@RequestMapping("/showUserPortrayal")
    public String showUserPortrayal(IntelligenceQuery intelligenceQuery, Model model) {

        Integer userPortrayalType = intelligenceQuery.getUserPortrayalType();

        if (StringTool.isNotBlank(userPortrayalType)) {
            //根据用户画像，查询用户画像介绍

        }


        return "intelligent-report";
    }*/

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
        Integer reportId = intelligenceFindHouseService.intelligenceFindHouseServiceByType(intelligenceQuery);
        if (StringTool.isNotBlank(reportId)) {
            return NashResult.build(reportId);
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
    public String showUserPortrayal(@PathVariable("reportId") String reportId, Model model) {
        if (StringTool.isNotBlank(reportId)) {
            //查询用户是否有报告数据
            Map map = new HashMap();
            IntelligenceFhRes intelligenceFhRes = intelligenceFhResService.queryResById(Integer.valueOf(reportId));
            if (StringTool.isNotBlank(intelligenceFhRes)) {
                //String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(Long.parseLong(intelligenceFhRes.getCreateTime())));
                //intelligenceFhRes.setCreateTime(date);
                Integer  plotTotal=null;
                if(StringTool.isNotBlank(intelligenceFhRes.getDownPayment())&&StringTool.isNotBlank(intelligenceFhRes.getMonthPayment())){
                      plotTotal = Integer.valueOf(intelligenceFhRes.getDownPayment()) + (Integer.valueOf(intelligenceFhRes.getMonthPayment()) * 12 * 30/10000);
                }else{
                    plotTotal = intelligenceFhRes.getTotalPrice();
                }
                intelligenceFhRes.setTotalPrice(plotTotal);
                Map<String, Object> fhpt = intelligenceFhPricetrendService.queryPriceTrend(intelligenceFhRes.getTotalPrice());
                Map<String, Object> fhtp = intelligenceFhTdService.queryTd(intelligenceFhRes.getTotalPrice());
                model.addAttribute("fhpt", fhpt);
                model.addAttribute("fhtp", fhtp);
                model.addAttribute("intelligenceFhRes", intelligenceFhRes);
            }
            model.addAttribute("message", "没有报告记录！");
        } else {
            model.addAttribute("message", "登陆后才能显示相应的报告信息！");
        }
        return "intelligent-report";
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


    //测试

    /**
     * 功能描述：根据id查询报告页数据
     *
     * @param
     * @return
     * @author zengqingzhou
     * @date 2018/1/3 17:45
     */
    @RequestMapping("/showMyReportData/{reportId}")
    @ResponseBody
    public NashResult find(@PathVariable("reportId") Integer id) {
        Map map = new HashMap();
        IntelligenceFhRes intelligenceFhRes = intelligenceFhResService.queryResById(id);
        Map<String, Object> fhpt = intelligenceFhPricetrendService.queryPriceTrend(intelligenceFhRes.getTotalPrice());
        Map<String, Object> fhtp = intelligenceFhTdService.queryTd(intelligenceFhRes.getTotalPrice());
        map.put("fhpt", fhpt);
        map.put("fhtp", fhtp);
        map.put("intelligenceFhRes", intelligenceFhRes);
        return NashResult.build(map);
    }
}
