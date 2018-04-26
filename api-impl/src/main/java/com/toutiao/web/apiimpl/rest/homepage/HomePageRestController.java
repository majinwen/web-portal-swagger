package com.toutiao.web.apiimpl.rest.homepage;


import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/homePage")
public class HomePageRestController {

    @Autowired
    private HomePageRestService homePageRestService;


    @RequestMapping(value = "getHomePageEsf",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getHomePageEsf()
    {
        homePageRestService.getHomePageEsf();
        return  NashResult.build(0);
    }
}
