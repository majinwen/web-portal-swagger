package com.toutiao.web.apiimpl.impl.Intelligence;


import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
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

@RestController
@RequestMapping("ifh")
public class IntelligenceFindHouseController {

    @Autowired
    private IntelligenceFindHouseService intelligenceFindHouseService;
    /**
     *
     * 功能描述：查找我的报告
     * @author zhw
     * @date 2017/12/26 13:57
     * @param []
     * @return java.lang.String
     */
    @RequestMapping("/queryMyReport")
    public String getMyReport(HttpServletRequest request){

        //从cookie中获取用户手机号码
        String usePhone = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);

        if(StringTool.isNotBlank(usePhone)){
            //查询用户是否有报告数据
            IntelligenceFhRes userReport = intelligenceFindHouseService.queryUserReport(usePhone);

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
    public String goCheckPrice(@RequestParam(value = "userType",required = true) String userType, Model model) {

        //判断是否选择类型
        if (StringTool.isBlank(userType)) {
            model.addAttribute("message", "请选择类型！");
        }
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
        if (StringTool.isBlank(intelligenceQuery)) {
            return NashResult.Fail("message", "请选择首付/月供");
        }
        List queryPlotCount = intelligenceFindHouseService.queryPlotCount(intelligenceQuery);

        intelligenceQuery = (IntelligenceQuery) queryPlotCount.get(0);

        return NashResult.build(intelligenceQuery);
    }

    /**
     * 功能描述：到户型页面
     *
     * @param [intelligenceQuery]
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/18 21:41
     */
    @RequestMapping("/goCategoryPage")
    public String goCategoryPage(IntelligenceQuery intelligenceQuery, Model model) {

        //判断用户选择的类型是否是第三种类型
        if ("3".equals(intelligenceQuery.getUserType())) {
            //传递用户信息
            List list = intelligenceFindHouseService.queryPlotInfoByUserType(intelligenceQuery);
            //跳转到生成报告页
            return "";
        }
        //传递用户信息
        model.addAttribute("intelligenceQuery", intelligenceQuery);

        return "";
    }

    /**
     * 功能描述：异步根据户型赛选小区数量与用户比率
     *
     * @param [intelligenceQuery]
     * @return com.toutiao.web.common.restmodel.NashResult
     * @author zhw
     * @date 2017/12/18 22:03
     */
    @RequestMapping("/plotCountByCategory")
    @ResponseBody
    public NashResult plotCountByCategory(IntelligenceQuery intelligenceQuery) {

        if (StringTool.isBlank(intelligenceQuery)) {
            return NashResult.Fail("message", "请选择首付/月供");
        }
        //根据户型/总价查找相应数据
        List queryByCategory = intelligenceFindHouseService.queryByCategory(intelligenceQuery);

        intelligenceQuery = (IntelligenceQuery) queryByCategory.get(0);

        return NashResult.build(intelligenceQuery);
    }

    /**
     * 功能描述：到区域页面
     *
     * @param [intelligenceQuery]
     * @return java.lang.String
     * @author zhw
     * @date 2017/12/18 21:41
     */
    @RequestMapping("/goRegionPage")
    public String goRegionPage(IntelligenceQuery intelligenceQuery, Model model) {

        //通过总价/户型过滤无小区的区域
        List<QueryFindByRobot> regionName = intelligenceFindHouseService.filterByTotalAndCategory(intelligenceQuery);
        //取前三条数据信息
        model.addAttribute("regionList1", regionName.get(0));
        model.addAttribute("regionList2", regionName.get(1));
        model.addAttribute("regionList3", regionName.get(2));
        if (intelligenceQuery.getUserType().equals("1")) {

            //去过渡页
            return "";
        }
        //标签页
        return "";
    }

    /**
     * 功能描述：在上述条件的前提下根据孩子的年龄段查询小区的数量
     *
     * @param [intelligenceQuery, model]
     * @return com.toutiao.web.common.restmodel.NashResult
     * @author zhw
     * @date 2017/12/19 15:05
     */
    @RequestMapping("/checkAgeOfChildren")
    @ResponseBody
    public NashResult checkAgeOfChildren(IntelligenceQuery intelligenceQuery, Model model) {

        String schoolTypeName = intelligenceQuery.getSchoolTypeName();
        //判断孩子学校类型是否为null ''
        if (StringTool.isBlank(schoolTypeName)) {
            schoolTypeName = "KG";
            intelligenceQuery.setSchoolTypeName(schoolTypeName);
        }
        //如果18+则需要去掉教育配套信息

        //缺少相关代码

        //通过学校类型查询相应小区个数
        List<Long> list = intelligenceFindHouseService.queryPlotCountBySchoolType(intelligenceQuery);
        Long count = list.get(0);
        model.addAttribute("intelligenceQuery", intelligenceQuery);
        return NashResult.build(count);

    }

    /**
     * 功能描述：生成报告页数据
     *
     * @param [intelligenceQuery]
     * @return com.toutiao.web.common.restmodel.NashResult
     * @author zhw
     * @date 2017/12/19 17:42
     */
    @RequestMapping("/produceReport")
    public String produceReport(IntelligenceQuery intelligenceQuery) {

        List list = intelligenceFindHouseService.queryPlotInfoByUserType(intelligenceQuery);

        return "";
    }




}
