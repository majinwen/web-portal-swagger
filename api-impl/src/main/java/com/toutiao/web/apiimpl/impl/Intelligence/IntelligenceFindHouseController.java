package com.toutiao.web.apiimpl.impl.Intelligence;


import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFh;
import com.toutiao.web.domain.query.IntelligenceQuery;
import com.toutiao.web.service.intelligence.IntelligenceFhResService;
import com.toutiao.web.service.intelligence.IntelligenceFindHouseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("ifh")
public class IntelligenceFindHouseController {

    @Autowired
    private IntelligenceFindHouseService intelligenceFindHouseService;
    @Autowired
    private IntelligenceFhResService intelligenceFhResService;

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
        return "";
    }


    /**
     * 功能描述：跳转功能，跳转到选择类型页面
     *
     * @param model
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/18 18:28
     */
    @RequestMapping("/goCheckType")
    public String goCheckType() {

        return "";
    }

    /**
     * 功能描述：判断选择的类型，进行跳转
     *
     * @param userType, model
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/18 18:44
     */
    @RequestMapping("/checkUserType")
    public String goCheckPrice(@RequestParam(value = "userType", required = true) String userType, Model model) {

        //判断是否选择类型
        if (StringTool.isBlank(userType)) {
            model.addAttribute("message", "请选择类型！");
        }
        //将数据传递到页面
        model.addAttribute("userType", userType);
        //去价格页面
        return "";
    }

    /**
     * 功能描述：异步根据首付与月付获取小区数量与相应的比率
     *
     * @param intelligenceQuery
     * @return com.toutiao.web.common.restmodel.NashResult
     * @author zhw
     * @date 2017/12/18 21:05
     */
    @RequestMapping("/goCheckPrice")
    @ResponseBody
    public NashResult plotCountByTotalPrice(IntelligenceQuery intelligenceQuery) {
        //判断页面传递所需的参数是否为空
        if (StringTool.isBlank(intelligenceQuery.getDownPayMent())
                || StringTool.isBlank(intelligenceQuery.getMonthPayMent())
                || StringTool.isBlank(intelligenceQuery.getPreconcTotal())) {
            return NashResult.Fail("message", "请选择首付/月供/");
        }
        IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryUserCheckPrice(intelligenceQuery);
        //获取根据用户条件筛选的小区数量和相应比率
        return NashResult.build(intelligenceFh);
    }

    @RequestMapping("/intelligenceFindHouseByType")
    @ResponseBody
    public List<IntelligenceFindhouse> intelligenceFindHouseByType(IntelligenceQuery intelligenceQuery){
        IntelligenceQuery intelligenceQuery1 = new IntelligenceQuery();
        intelligenceQuery1.setUserPortrayalType(7);
        intelligenceQuery1.setMinTotalPrice(4500000);
        intelligenceQuery1.setMaxTotalPrice(5500000);
        intelligenceQuery1.setDistrictId("105040,105035,105034");
        intelligenceQuery1.setLayOut(3);
        List<IntelligenceFindhouse> list = intelligenceFindHouseService.intelligenceFindHouseServiceByType(intelligenceQuery1);
        return null;
    }
    /**
     * 功能描述：跳转到用户选择户型页面controller
     *
     * @param intelligenceQuery
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/26 20:40
     */
    @RequestMapping("/queryUserCheckCategory")
    public String userCheckCategory(IntelligenceQuery intelligenceQuery, Model model) {

        //复制数据信息
        IntelligenceFh intelligenceFh = new IntelligenceFh();
        BeanUtils.copyProperties(intelligenceQuery, intelligenceFh);
        if ("3".equals(intelligenceFh.getUserType())) {
            //将第七种画像附给当前用户
            intelligenceFh.setUserPortrayalType(7);
            model.addAttribute("intelligenceFh", intelligenceFh);
            //直接生成报表
            //调用接口

            return "";
        }
        model.addAttribute("intelligenceFh", intelligenceFh);
        //户型页面
        return "";
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
    @RequestMapping("/chooseDistinct")
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
    }

    /**
     * 功能描述：家庭页面
     *
     * @param intelligenceQuery
     * @return com.toutiao.web.common.restmodel.NashResult
     * @author zhw
     * @date 2017/12/26 21:45
     */
    @RequestMapping("/queryPlotCountByDistrict")
    public String queryPlotCountByDistrict(IntelligenceQuery intelligenceQuery, Model model) {

        //通过页面传递过来的区域等信息赛选小区数量
        IntelligenceFh intelligenceFh = intelligenceFindHouseService.queryPlotCountByDistrict(intelligenceQuery);
        model.addAttribute("intelligenceFh", intelligenceFh);
        //报告生成页
        return "";
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
            intelligenceFh.setSchoolFlag(false);
            intelligenceFh.setHospitalFlag(false);
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
    @RequestMapping("/showUserPortrayal")
    public String showUserPortrayal(IntelligenceQuery intelligenceQuery, Model model) {

        Integer userPortrayalType = intelligenceQuery.getUserPortrayalType();

        if (StringTool.isNotBlank(userPortrayalType)) {
            //根据用户画像，查询用户画像介绍

        }


        return "";
    }


}
