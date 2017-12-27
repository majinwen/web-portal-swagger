package com.toutiao.web.apiimpl.impl.Intelligence;


import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFindhouse;
import com.toutiao.web.dao.entity.robot.QueryFindByRobot;
import com.toutiao.web.domain.query.IntelligenceQuery;
import com.toutiao.web.service.intelligence.IntelligenceFindHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ifh")
public class IntelligenceFindHouseController {

    @Autowired
    private IntelligenceFindHouseService intelligenceFindHouseService;

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
            IntelligenceFhRes userReport = intelligenceFindHouseService.queryUserReport(usePhone);
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
        Map<String,String> map=intelligenceFindHouseService.queryUserCheckPrice(intelligenceQuery);

        //获取根据用户条件筛选的小区数量和相应比率
        return NashResult.build(intelligenceQuery);
    }
    @RequestMapping("/intelligenceFindHouseTypeTwo")
    @ResponseBody
    public List<IntelligenceFindhouse> intelligenceFindHouseTypeTwo(IntelligenceQuery intelligenceQuery){
        IntelligenceQuery intelligenceQuery1 = new IntelligenceQuery();
        intelligenceQuery1.setUserPortrayalType(4);
        List<IntelligenceFindhouse> list = intelligenceFindHouseService.intelligenceFindHouseServiceTypeTwo(intelligenceQuery1);
        return null;
    }











}
