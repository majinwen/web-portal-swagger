package com.toutiao.web.apiimpl.rest.rent;

import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.query.RentHouseQuery;
import com.toutiao.web.service.plot.PlotService;
import com.toutiao.web.service.rent.RentHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rest/zufang")
public class rent {
    @Autowired
    private RentHouseService rentHouseService;
    @Autowired
    private PlotService plotService;

    /**
     * 租房详情页
     */
    @RequestMapping("getbyid")
    @ResponseBody
    public NashResult rentDetail(RentHouseQuery rentHouseQuery){
        HashMap<Object, Object> hashMap = new HashMap<>();
        //房源详情
        Map map = rentHouseService.queryHouseById(rentHouseQuery);
        if(map!=null){
            hashMap.put("rentHouse",map);
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
                    hashMap.put("nearHouse",nearHouse.get("nearHouse"));
                }

                //小区待租房源总数
                RentHouseQuery queryNearHouse1 = new RentHouseQuery();
                queryNearHouse1.setZuFangId(zufang_id);
                queryNearHouse1.setRentSign((Integer) map.get("rent_sign"));
                Map nearHouseApartment = rentHouseService.queryHouseByparentId(queryNearHouse1);
                if (nearHouseApartment!=null){
                    hashMap.put("total",nearHouseApartment.get("total"));
                }
                //小区详情信息
                Map plot = plotService.queryPlotByRentId(zufang_id);
                if (plot!=null){
                    hashMap.put("plot",plot);
                }
            }else {
                queryNearHouse.setHouseId(house_id);
                queryNearHouse.setZuFangId(zufang_id);
                queryNearHouse.setZuFangName(zufang_name);
                queryNearHouse.setRentSign((Integer) map.get("rent_sign"));
                Map nearHouse = rentHouseService.queryHouseByparentId(queryNearHouse);
                if (nearHouse!=null&&((ArrayList)nearHouse.get("nearHouse")).size()>0){
                    hashMap.put("nearHouse",nearHouse.get("nearHouse"));
                }
                //小区待租房源总数
                if (nearHouse!=null){
                    hashMap.put("total",nearHouse.get("total"));
                }
            }


            //房源经纪人
            Map agent = rentHouseService.queryAgentByHouseId(rentHouseQuery.getHouseId());
            if (agent!=null){
                hashMap.put("agent",agent);
            }
            return NashResult.build(hashMap);
        }
        return NashResult.Fail("not-found","没找到这个出租房");
    }
}
