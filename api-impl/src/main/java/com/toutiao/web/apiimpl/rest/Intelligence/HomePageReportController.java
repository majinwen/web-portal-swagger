package com.toutiao.web.apiimpl.rest.Intelligence;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.homepage.UserFavoriteConditionRequest;
import com.toutiao.app.api.chance.response.user.UserLoginResponse;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.Intelligence.HomePageReportService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.service.intelligence.IntelligenceFhPricetrendService;
import com.toutiao.web.service.intelligence.IntelligenceFhResService;
import com.toutiao.web.service.intelligence.IntelligenceFhTdService;
import org.postgresql.util.PGobject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/findhouse/byHouseReport")
public class HomePageReportController {
    @Autowired
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private IntelligenceFhTdService intelligenceFhTdService;
    @Autowired
    private HomePageReportService homePageReportService;
    @Autowired
    private IntelligenceFhResService intelligenceFhResService;
    @Autowired
    private IntelligenceFhPricetrendService intelligenceFhPricetrendService;

    /**
     * 保存报告
     * @param userFavoriteConditionRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveHomePageReport",method = RequestMethod.GET)
    public NashResult saveHomePageReport(HttpServletRequest request, UserFavoriteConditionRequest userFavoriteConditionRequest){
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest,userFavoriteConditionDoQuery);
        Integer result = homePageReportService.saveHomePageReport(request, userFavoriteConditionDoQuery);
        return NashResult.build(result);
    }

    /**
     * 查询报告
     * @param reportId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getHomePageReport",method = RequestMethod.GET)
    public NashResult getHomePageReport(@RequestParam("reportId") String reportId,@RequestParam(value = "title",required = false) String title,HttpServletRequest request, HttpServletResponse response) {
        Map<Object,Object> map = new HashMap();
        if(StringTool.isNotBlank(reportId)){
            if(StringTool.isNotBlank(title)){
                String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
                UserLoginResponse userLoginResponse = JSONObject.parseObject(user,UserLoginResponse.class);
                UserBasicDo userBasic =userBasicInfoService.queryUserBasic(userLoginResponse.getUserId());
                intelligenceFhResService.updateMyReportCollectStatus(reportId, userBasic.getPhone());
            }
            IntelligenceFhRes intelligenceFhRes = intelligenceFhResService.queryResById(Integer.valueOf(reportId));
            String datajson = ((PGobject) intelligenceFhRes.getFhResult()).getValue();
//            JSONArray objects = JSONArray.parseArray(datajson);
//            List<IntelligenceFindhouse> intelligenceFindhouses = JSONObject.parseArray(JSON.toJSONString(objects), IntelligenceFindhouse.class);
            Map<String, Object> fhpt = intelligenceFhPricetrendService.queryPriceTrend(intelligenceFhRes.getTotalPrice());
            Map<String, Object> fhtp = intelligenceFhTdService.queryTd(intelligenceFhRes.getTotalPrice());
            map.put("datajson",datajson);
            map.put("totalPrice",intelligenceFhRes.getTotalPrice());
            map.put("layout",intelligenceFhRes.getLayoutArray());
            map.put("district",intelligenceFhRes.getDistrictArray());
            map.put("fhpt",fhpt);
            map.put("fhtp",fhtp);
            map.put("collectStatus",intelligenceFhRes.getCollectStatus());
            map.put("backUrl", request.getRequestURI());
        }
        return NashResult.build(map);
    }
}
