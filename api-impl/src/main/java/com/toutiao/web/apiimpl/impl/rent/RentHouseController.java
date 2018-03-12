package com.toutiao.web.apiimpl.impl.rent;


import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.domain.query.RentHouseQuery;
import com.toutiao.web.service.rent.RentHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 头条租房
 *
 */

@Controller
@RequestMapping("/{citypath}")
public class RentHouseController {

    @Autowired
    private RentHouseService rentHouseService;

//    @RequestMapping("/zufang")
//    public String queryRentHouseList(RentHouseQuery rentHouseQuery, Model model) {
//
//        Map<String,Object> rentHouseList =rentHouseService.getRentHouseList(rentHouseQuery);
//
//        model.addAttribute("rent",rentHouseList);
//
//        return "newhouse/new-index";
//    }

    @RequestMapping("/zufang")
    @ResponseBody
    public NashResult queryRentHouseList(RentHouseQuery rentHouseQuery, Model model) {

        Map<String,Object> rentHouseList =rentHouseService.getRentHouseList(rentHouseQuery);

       // model.addAttribute("rent",rentHouseList);

        return NashResult.build(rentHouseList);
    }


}
