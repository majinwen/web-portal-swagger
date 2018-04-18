package com.toutiao.web.apiimpl.rest.village;

import com.toutiao.app.service.village.VillageRestService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/village")
public class VillageRestController {

    @Autowired
    private VillageRestService villageRestService;


    /**
     * 获取小区详情
     */
    @RequestMapping(value = "/getVillageDetailsByNewCode",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getVillageDetailsByNewCode(@RequestParam("newcode") Integer newcode)
    {
//        villageRestService

        return NashResult.build(0);
    }

}
