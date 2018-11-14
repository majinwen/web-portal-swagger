package com.toutiao.web.apiimpl.rest.homepage;

import com.toutiao.app.api.chance.response.homepage.HomePageEsfCountResponse;
import com.toutiao.app.api.chance.response.homepage.HomePageNewCountResponse;
import com.toutiao.app.domain.homepage.HomePageEsfCountDo;
import com.toutiao.app.domain.homepage.HomePageNewCountDo;
import com.toutiao.app.service.homepage.HomePageCountService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.city.CityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by CuiShihao on 2018/10/19
 * <p>
 * 统计新房、二手房相应的数量
 */
@RestController
@RequestMapping("/rest/homepage/count")
public class HomePageHouseCountController {

    @Autowired
    private HomePageCountService countService;

    /**
     * 统计新房的相应数量
     */
    @RequestMapping(value = "/getNew")
    @ResponseBody
    public NashResult getNewCount() {
        HomePageNewCountDo pageNewCountDo = countService.getNewCount(CityUtils.getCity());
        HomePageNewCountResponse response = new HomePageNewCountResponse();
        BeanUtils.copyProperties(pageNewCountDo, response);
        return NashResult.build(response);
    }

    /**
     * 统计二手房的相应数量
     */
    @RequestMapping(value = "/getEsf")
    @ResponseBody
    public NashResult getEsfCount() {
        HomePageEsfCountDo pageEsfCountDo = countService.getEsfCount(CityUtils.getCity());
        HomePageEsfCountResponse response = new HomePageEsfCountResponse();
        BeanUtils.copyProperties(pageEsfCountDo, response);
        return NashResult.build(response);
    }
}
