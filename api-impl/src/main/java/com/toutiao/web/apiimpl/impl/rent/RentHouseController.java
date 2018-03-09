package com.toutiao.web.apiimpl.impl.rent;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 头条租房
 *
 */

@Controller
@RequestMapping("/{citypath}/zufang")
public class RentHouseController {

    @RequestMapping("")
    public String empty(Model model){

        return "/rent/rent-list";
    }
    @RequestMapping("/detail")
    public String detail(Model model){

        return "/rent/rent-detail";
    }
}
