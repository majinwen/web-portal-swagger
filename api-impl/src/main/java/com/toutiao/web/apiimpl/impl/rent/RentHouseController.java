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
            //附近相似好房/好房推荐
            RentHouseQuery queryNearHouse = new RentHouseQuery();
            NashResult nearHouse = new NashResult();
            nearHouse.setCode("fail");
            if((Integer) rentHouse.get("Integer")==1){
                queryNearHouse.setNearbyKm("3");
                queryNearHouse.setBeginPrice((Double) rentHouse.get("rent_house_price")*0.8);
                queryNearHouse.setEndPrice((Double) rentHouse.get("rent_house_price")*1.2);
                queryNearHouse.setLat(Double.parseDouble(rentHouse.get("location").toString().split(",")[0]));
                queryNearHouse.setLon(Double.parseDouble(rentHouse.get("location").toString().split(",")[1]));
                nearHouse = rentHouseService.queryNearHouseByDistance(queryNearHouse);
            }else {
                queryNearHouse.setApartmentParentId((String) rentHouse.get("apartment_parent_id"));
                nearHouse = rentHouseService.queryHouseById(queryNearHouse);
            }
            if (nearHouse.getCode().equals("success")){
                Map data = (Map) nearHouse.getData();
                model.addAttribute("nearHouse",data);
            }
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
                //TODO
                //保存用户电话(标志)和房源信息

            }
        }
        return null;
    }


}
