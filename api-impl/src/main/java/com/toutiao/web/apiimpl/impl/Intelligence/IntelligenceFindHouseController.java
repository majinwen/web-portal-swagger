package com.toutiao.web.apiimpl.impl.Intelligence;


import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.domain.query.IntelligenceQuery;
import com.toutiao.web.service.intelligence.IntelligenceFhResService;
import com.toutiao.web.service.intelligence.IntelligenceFindHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
     * @param []
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
            model.addAttribute("userReport", userReport);
        }
        //跳转到报告页
        return "";
    }


    /**
     * 功能描述：跳转功能，跳转到选择类型页面
     *
     * @param [model]
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
     * @param [userType, model]
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
     * @param [intelligenceQuery]
     * @return com.toutiao.web.common.restmodel.NashResult
     * @author zhw
     * @date 2017/12/18 21:05
     */
    @RequestMapping("/goCheckPrice")
    @ResponseBody
    public NashResult plotCountByTotalPrice(IntelligenceQuery intelligenceQuery) {
        //判断页面传递所需的参数是否为空
        if (StringTool.isBlank(intelligenceQuery.getBeginDownPayment())
                || StringTool.isBlank(intelligenceQuery.getBeginDownPayment())
                || StringTool.isBlank(intelligenceQuery.getPreconcTotal())) {
            return NashResult.Fail("message", "请选择首付/月供/");
        }
        intelligenceQuery=intelligenceFindHouseService.queryUserCheckPrice(intelligenceQuery);
        //获取根据用户条件筛选的小区数量和相应比率
        return NashResult.build(intelligenceQuery);
    }
    /**
     *
     * 功能描述：跳转到用户选择户型页面controller
     * @author zhw
     * @date 2017/12/26 20:40
     * @param [intelligenceQuery]
     * @return java.lang.String
     */
    @RequestMapping("/queryUserCheckCategory")
    public String userCheckCategory(IntelligenceQuery intelligenceQuery,Model model){

        if ("3".equals(intelligenceQuery.getUserType())) {
            //将第七种画像附给当前用户
            intelligenceQuery.setUserPortrayalType(7);
            model.addAttribute("intelligenceQuery",intelligenceQuery);
            //直接生成报表
            //调用接口

            return "";
        }

        model.addAttribute("intelligenceQuery",intelligenceQuery);
        //户型页面
        return "";
    }
    /**
     *
     * 功能描述：根据户型和总价查询小区数量
     * @author zhw
     * @date 2017/12/26 20:56
     * @param [intelligenceQuery, model]
     * @return java.lang.String
     */
    @RequestMapping("/userCheckCategoryPage")
    @ResponseBody
    public NashResult  queryPlotCountByCategory(IntelligenceQuery intelligenceQuery,Model model){

        //根据户型与总价条件赛选条件
        intelligenceQuery=intelligenceFindHouseService.queryUserCheckPriceAndCaategory(intelligenceQuery);

        return NashResult.build(intelligenceQuery);
    }
    /**
     *
     * 功能描述：用户选择区域页面
     * @author zhw
     * @date 2017/12/26 21:42
     * @param [intelligenceQuery, model]
     * @return java.lang.String
     */
    //选择区域
    @RequestMapping("/chooseDistinct")
    public String checkDistrict(IntelligenceQuery intelligenceQuery,Model model){

        model.addAttribute("intelligenceQuery",intelligenceQuery);

        return "";
    }
    /**
     *
     * 功能描述：家庭页面
     * @author zhw
     * @date 2017/12/26 21:45
     * @param [intelligenceQuery]
     * @return com.toutiao.web.common.restmodel.NashResult
     */
    @RequestMapping("/queryPlotCountByDistrict")
    public String queryPlotCountByDistrict(IntelligenceQuery intelligenceQuery,Model model){

        //通过页面传递过来的区域等信息赛选小区数量
        intelligenceQuery = intelligenceQuery = intelligenceFindHouseService.queryPlotCountByDistrict(intelligenceQuery);
        model.addAttribute("intelligenceQuery",intelligenceQuery);
        //报告生成页
        return "";
    }







}
