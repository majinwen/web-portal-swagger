package com.toutiao.web.apiimpl.impl.rent;


import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.domain.query.RentHouseQuery;
import com.toutiao.web.service.rent.RentHouseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 头条租房
 *
 */

@Controller
@RequestMapping("/{citypath}/rent")
public class RentHouseController {
    @Autowired
    private RentHouseService rentHouseService;

    /**
     *
     * @Description：小区详情页
     *
     * @Param [rentHouseQuery, model]
     * @Return java.lang.String
     * @Author zengqingzhou
     * @Date 2018/3/7 14:27
     */
    @RequestMapping("/{houseId}.html")
    public String rentDetail(RentHouseQuery rentHouseQuery, Model model){
        NashResult rentDetail = rentHouseService.queryHouseById(rentHouseQuery);
        if(rentDetail.getCode().equals("success")){
            Map rentHouse = (Map)rentDetail.getData();
            model.addAttribute("rentHouse",rentHouse);

            RentHouseQuery queryNearHouse = new RentHouseQuery();
            queryNearHouse.setLat(Double.parseDouble(rentHouse.get("location").toString().split(",")[0]));
            queryNearHouse.setLon(Double.parseDouble(rentHouse.get("location").toString().split(",")[1]));
            queryNearHouse.setNearbyKm("3");
            //附近相似好房/好房推荐
            NashResult NearHouse = rentHouseService.queryNearHouseByDistance(queryNearHouse);
            return null;
        }
        return "404";
    }

    /**
     *
     * @Description：获取地图信息
     *
     * @Param [rentHouseQuery, model]
     * @Return java.lang.String
     * @Author zengqingzhou
     * @Date 2018/3/7 14:26
     */
    @RequestMapping("/{houseId}/map.html")
    public String plotMap(RentHouseQuery rentHouseQuery, Model model) {
        NashResult rentDetail = rentHouseService.queryHouseById(rentHouseQuery);
        if(rentDetail.getCode().equals("success")){
            Map rentHouse = (Map)rentDetail.getData();
            model.addAttribute("build", rentHouse);
            return "map";
        }
        return "404";
    }

    /**
     *
     * @Description：出租房屋收藏
     *
     * @Param [request, response, model, plotId]
     * @Return com.toutiao.web.common.restmodel.NashResult
     * @Author zengqingzhou
     * @Date 2018/3/7 14:28
     */
    @RequestMapping("/collectRent")
    @ResponseBody
    public NashResult collectPlot(HttpServletRequest request, HttpServletResponse response, Model model,
                                  @RequestParam("houseId") String houseId){
        if(StringUtils.isNotBlank(houseId)){
            String userPhone = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_User_LOGIN);
            //是否登录
            if (StringUtils.isBlank(userPhone)){
                //跳转登录页面
                return NashResult.Fail("no-login","");
            }else {
                //保存用户电话(标志)和房源信息

            }
        }
        return  null;
    }


}
