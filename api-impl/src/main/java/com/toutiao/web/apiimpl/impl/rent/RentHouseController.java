package com.toutiao.web.apiimpl.impl.rent;


import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.domain.query.RentHouseQuery;
import com.toutiao.web.service.plot.PlotService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 头条租房
 *
 */

@Controller
@RequestMapping("/{citypath}/zufang")
public class RentHouseController {
    @Autowired
    private RentHouseService rentHouseService;
    @Autowired
    private PlotService plotService;

    /**
     *
     * @Description：租房详情页
     *
     * @Param [rentHouseQuery, model]
     * @Return java.lang.String
     * @Author zengqingzhou
     * @Date 2018/3/7 14:27
     */
    @RequestMapping("/{houseId}.html")
    public String rentDetail(RentHouseQuery rentHouseQuery, Model model){
        //房源详情
        Map map = rentHouseService.queryHouseById(rentHouseQuery);
        if(map!=null){
            model.addAttribute("rentHouse",map);
            RentHouseQuery queryNearHouse = new RentHouseQuery();
            String house_id = map.get("house_id").toString();
            String zufang_id = map.get("zufang_id").toString();
            String zufang_name = map.get("zufang_name").toString();
            //附近相似好房/好房推荐
            if((Integer) map.get("rent_sign")==0){
                queryNearHouse.setNear("3");
                queryNearHouse.setRentSign((Integer) map.get("rent_sign"));
                queryNearHouse.setHouseId(house_id);
                queryNearHouse.setBeginPrice((Double) map.get("rent_house_price")*0.8);
                queryNearHouse.setEndPrice((Double) map.get("rent_house_price")*1.2);
                queryNearHouse.setLat(Double.parseDouble(map.get("location").toString().split(",")[0]));
                queryNearHouse.setLon(Double.parseDouble(map.get("location").toString().split(",")[1]));
                Map nearHouse = rentHouseService.queryNearHouseByDistance(queryNearHouse);
                if (nearHouse!=null&&((ArrayList)nearHouse.get("nearHouse")).size()>0){
                    model.addAttribute("nearHouse",nearHouse.get("nearHouse"));
                }

                //小区待租房源总数
                RentHouseQuery queryNearHouse1 = new RentHouseQuery();
                queryNearHouse1.setZuFangId(zufang_id);
                queryNearHouse1.setRentSign((Integer) map.get("rent_sign"));
                Map nearHouseApartment = rentHouseService.queryHouseByparentId(queryNearHouse1);
                if (nearHouseApartment!=null){
                    model.addAttribute("total",nearHouseApartment.get("total"));
                }
                //小区详情信息
                Map plot = plotService.queryPlotByRentId(zufang_id);
                if (plot!=null){
                    model.addAttribute("plot",plot);
                }
            }else {
                queryNearHouse.setHouseId(house_id);
                queryNearHouse.setZuFangId(zufang_id);
                queryNearHouse.setZuFangName(zufang_name);
                queryNearHouse.setRentSign((Integer) map.get("rent_sign"));
                Map nearHouse = rentHouseService.queryHouseByparentId(queryNearHouse);
                if (nearHouse!=null&&((ArrayList)nearHouse.get("nearHouse")).size()>0){
                    model.addAttribute("nearHouse",nearHouse.get("nearHouse"));
                }
                //小区待租房源总数
                model.addAttribute("total",nearHouse.get("total"));
            }


            //房源经纪人
            Map agent = rentHouseService.queryAgentByHouseId(rentHouseQuery.getHouseId());
            if (agent!=null){
                model.addAttribute("agent",agent);
            }
            return "rent/rent-detail";
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
        Map rentDetail = rentHouseService.queryHouseById(rentHouseQuery);
        if(rentDetail!=null){
            model.addAttribute("build", rentDetail);
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


    @RequestMapping("")
    public String queryRentHouseList(RentHouseQuery rentHouseQuery, Model model) {
//        Map<String,Object> rentHouseList =rentHouseService.getRentHouseList(rentHouseQuery);
//        model.addAttribute("rent",rentHouseList);
        return "rent/rent-list";
    }

    @RequestMapping(value = {""},produces="application/json")
    @ResponseBody
    public NashResult queryRentHousePageList(RentHouseQuery rentHouseQuery) {
        Map<String,Object> rentHouseList =rentHouseService.getRentHouseList(rentHouseQuery);
        return NashResult.build(rentHouseList);
    }
}
