package com.toutiao.web.apiimpl.impl.Intelligence;


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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/{citypath}/findhouse")
public class IntelligenceFindHouseController {

    @Autowired
    private IntelligenceFindHouseService intelligenceFindHouseService;
    @Autowired
    private IntelligenceFhResService intelligenceFhResService;
    @Autowired
    private IntelligenceFhTdService intelligenceFhTdService;
    @Autowired
    private IntelligenceFhPricetrendService intelligenceFhPricetrendService;


    /**
     * 功能描述：查找我的报告
     *
     * @param
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/26 13:57
     */
    @RequestMapping("/queryMyReport")
    public String getMyReport(HttpServletRequest request, Model model) {

        //从cookie中获取用户手机号码
        String usePhone = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);
        if (StringTool.isNotBlank(usePhone)) {
            //查询用户是否有报告数据
            IntelligenceFhRes userReport = intelligenceFhResService.queryUserReport(usePhone);
            if (StringTool.isNotBlank(userReport)) {
                model.addAttribute("userReport", userReport);
            }
            model.addAttribute("message", "没有报告记录！");
        } else {
            model.addAttribute("message", "登陆后才能显示相应的报告信息！");
        }
        //跳转到报告页
        Integer totalPrice = 500;
        List<IntelligenceFhPricetrend> fhpt = intelligenceFhPricetrendService.queryPriceTrend(totalPrice);
        List<IntelligenceFhTd> fhrd = intelligenceFhTdService.queryTd(totalPrice);
        model.addAttribute("fhpt",fhpt);
        model.addAttribute("fhrd",fhrd);
        return "";
    }
    /**
     * 功能描述：跳转功能，跳转到选择类型页面
     *
     * @param
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/18 18:28
     */
    @RequestMapping(value ="")
    public String goToStartRobot() {
        return "intelligent-find";
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

        String[] split = intelligenceQuery.getPreconcTotal().split("万");
        intelligenceQuery.setPreconcTotal(split[0]);
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
    public List<IntelligenceFindhouse> intelligenceFindHouseByType(IntelligenceQuery intelligenceQuery){
        IntelligenceQuery intelligenceQuery1 = new IntelligenceQuery();
        /*intelligenceQuery1.setUserPortrayalType(7);
        intelligenceQuery1.setMinTotalPrice(4500000);
        intelligenceQuery1.setMaxTotalPrice(5500000);*/
        intelligenceQuery1.setDistrictId("105040,105035,105034");
        intelligenceQuery1.setLayOut(3);
        intelligenceQuery1.setHospital("1");
        intelligenceQuery1.setSchool("KG");
        List<IntelligenceFindhouse> list = intelligenceFindHouseService.intelligenceFindHouseServiceByType(intelligenceQuery1);
        return null;
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
        String[] split = intelligenceQuery.getPreconcTotal().split("万");
        intelligenceQuery.setPreconcTotal(split[0]);
        //根据户型与总价条件赛选条件
        IntelligenceFh IntelligenceFh = intelligenceFindHouseService.queryUserCheckPriceAndCategory(intelligenceQuery);

        return NashResult.build(IntelligenceFh);
    }

    /**
     * 功能描述：用户选择区域页面
     *
     * @param intelligenceQuery, model
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/26 21:42
     */
    //选择区域
   /* @RequestMapping("/chooseDistinct")
    public String checkDistrict(IntelligenceQuery intelligenceQuery, Model model) {
        //复制数据信息
        IntelligenceFh intelligenceFh = new IntelligenceFh();
        BeanUtils.copyProperties(intelligenceQuery, intelligenceFh);
        model.addAttribute("intelligenceFh", intelligenceFh);
        //选择非一居的用户，才出现此问题；
        if(intelligenceFh.getLayOut()==1){

            //跳转过渡页，生成画像
            return "";
        }


        //去家庭页面选择小孩和老人
        return "";
    }*/

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
        String[] split = intelligenceQuery.getPreconcTotal().split("万");
        intelligenceQuery.setPreconcTotal(split[0]);
        //通过页面传递过来的区域等信息赛选小区数量
        IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryPlotCountByDistrict(intelligenceQuery);
        //报告生成页
        return  NashResult.build(intelligenceFh);
    }

    /**
     * 功能描述：8完成，生成报告
     *
     * @param intelligenceQuery, model
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/27 12:33
     */
    @RequestMapping("/goCreateReport")
    public String goCreateMyReport(IntelligenceQuery intelligenceQuery, Model model) {
        //复制数据信息
        IntelligenceFh intelligenceFh = new IntelligenceFh();
        BeanUtils.copyProperties(intelligenceQuery, intelligenceFh);
        //若用户选择“无小孩”或“18岁以上”，则去掉页面3中的教育配套标签；
        if ("0".equalsIgnoreCase(intelligenceFh.getUserType()) || "5".equalsIgnoreCase(intelligenceFh.getUserType())) {
            intelligenceFh.setSchoolFlag(0);
            intelligenceFh.setHospitalFlag(0);
        }
        model.addAttribute("intelligenceFh", intelligenceFh);
        //过渡页vs封面
        return "";
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
     * 功能描述：报告页
     *
     * @param model
     */
    @RequestMapping("/showUserPortrayal")
    public String showUserPortrayal(Model model) {
        return "intelligent-report";
    }


}
