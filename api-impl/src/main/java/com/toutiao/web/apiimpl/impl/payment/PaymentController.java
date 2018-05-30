package com.toutiao.web.apiimpl.impl.payment;


import com.toutiao.web.domain.query.NewHouseQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping("/{citypath}/payment")
public class PaymentController {


//    String json = "{\"userId\":11,\"userName\":\"userName111\"}";
//    //JSONObject jsons = JSON.parseObject(json);
//    String jwttoken = JsonWebTokenUtil.createJWT("xxoo112",json,60000);
//
//    //http://47.95.10.4:8087/order/saveOrder  toutiaopc
//
//    String url = "http://47.95.10.4:8087/order/saveOrder";
//    Map<String,String> header = new HashMap<>();
//    header.put("toutiaopc",jwttoken);
//
//    Map<String, Object> paramsMap = new HashMap<>();
//    paramsMap.put("userId","11");
//    paramsMap.put("userName","userName111");
//    paramsMap.put("phone","phone111");
//    paramsMap.put("type","2");
//    paramsMap.put("comment","comment111");
//    paramsMap.put("productNo","2");
//    String sss = HttpUtils.post(url,header,paramsMap);
//
//
//    System.out.println(sss);


    @RequestMapping(value = "/order/saveOrder", method = RequestMethod.POST)
    public String index(NewHouseQuery newHouseQuery, Model model) {
//        newHouseQuery.setSort(0);
//        newHouseQuery.setPageNum(2);
//        newHouseQuery.setPageSize(5);
//        Map<String,Object> builds = newHouseService.getNewHouse(newHouseQuery);
        model.addAttribute("saveOrder","");
        return "";
    }

}
