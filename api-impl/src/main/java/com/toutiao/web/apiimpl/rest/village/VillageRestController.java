package com.toutiao.web.apiimpl.rest.village;

import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/village")
public class VillageRestController {

    @Autowired
    private


    /**
     * 获取小区详情
     */
    @RequestMapping(value = "/getVillageByNewCode",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getVillageByNewCode(@RequestParam("newcode") Integer newcode )
    {

        return NashResult.build(0);
    }

}
