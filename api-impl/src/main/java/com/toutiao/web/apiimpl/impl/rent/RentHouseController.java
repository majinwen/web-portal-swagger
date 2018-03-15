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
            Integer house_id = (Integer) map.get("house_id");
            //附近相似好房/好房推荐
            if((Integer) map.get("rent_sign")==1){
                queryNearHouse.setNear("3");
                queryNearHouse.setRentSign(1);
                queryNearHouse.setHouseId(String.valueOf(house_id));
                queryNearHouse.setBeginPrice((Double) map.get("rent_house_price")*0.8);
                queryNearHouse.setEndPrice((Double) map.get("rent_house_price")*1.2);
                queryNearHouse.setLat(Double.parseDouble(map.get("location").toString().split(",")[0]));
                queryNearHouse.setLon(Double.parseDouble(map.get("location").toString().split(",")[1]));
                Map nearHouse = rentHouseService.queryNearHouseByDistance(queryNearHouse);
                if (nearHouse!=null){
                    model.addAttribute("nearHouse",nearHouse.get("nearHouse"));
                }
                //附近小区总数
                model.addAttribute("total",nearHouse.get("total"));
            }else {
                queryNearHouse.setHouseId(String.valueOf(house_id));
                queryNearHouse.setApartmentParentId((String) map.get("apartment_parent_id"));
                Map nearHouse = rentHouseService.queryHouseByparentId(queryNearHouse);
                if (nearHouse!=null){
                    model.addAttribute("nearHouse",nearHouse.get("nearHouse"));
                }
                //小区待租房源总数
                model.addAttribute("total",nearHouse.get("total"));
            }

            //小区详情信息
            Map plot = plotService.queryPlotByRentId((String) map.get("apartment_parent_id"));
            if (plot!=null){
                model.addAttribute("plot",plot);
            }
            //房源经纪人
//            Map agent = rentHouseService.queryAgentByHouseId(rentHouseQuery.getHouseId());
//            if (agent!=null){
//                List agentList = (List) agent.get("agent");
//                model.addAttribute("agentList",agentList);
//            }

            return "/rent/rent-detail";
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


//    @RequestMapping("/zufang")
//    public String queryRentHouseList(RentHouseQuery rentHouseQuery, Model model) {
//
//        Map<String,Object> rentHouseList =rentHouseService.getRentHouseList(rentHouseQuery);
//
//        model.addAttribute("rent",rentHouseList);
//
//        return "newhouse/new-index";
//    }


    @RequestMapping("/list")
    @ResponseBody
    public NashResult queryRentHouseList(RentHouseQuery rentHouseQuery, Model model) {

        Map<String, Object> rentHouseList =rentHouseService.getRentHouseList(rentHouseQuery);

       // model.addAttribute("rent",rentHouseList);

        return NashResult.build(rentHouseList);
    }

    @RequestMapping("")
    public String empty(Model model){

        return "/rent/rent-list";
    }
    @RequestMapping("/detail")
    public String detail(Model model){

        return "/rent/rent-detail";
    }

}
