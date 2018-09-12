package com.toutiao.web.apiimpl.rest.sellhouse;


import com.toutiao.app.api.chance.request.sellhouse.SellHouseRequest;
import com.toutiao.app.api.chance.response.sellhouse.SellHouseResponse;
import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDomain;
import com.toutiao.app.service.sellhouse.SellHouseDetailTopicsService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.city.CityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 专题房源详情页
 *
 */
@RestController
@RequestMapping("/rest/esf/topic")
public class SellHouseDetailTopicsRestController {

    @Autowired
    private SellHouseDetailTopicsService sellHouseDetailTopicsService;


    /**
     * 小区附近专题
     * @param sellHouseRequest
     * @return
     */
    @RequestMapping(value ="/getNearbyTopicsSellHouseDetail", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getNearbyTopicsSellHouseDetail(@Validated(First.class)  SellHouseRequest sellHouseRequest) {

        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getNearbyTopicsSellHouse(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);
        return NashResult.build(sellHouseResponse);
    }


    /**
     * 降价专题
     * @param sellHouseRequest
     * @return
     */
    @RequestMapping(value ="/getCutPriceTopicsSellHouseDetail", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getCutPriceTopicsSellHouseDetail(@Validated(First.class)  SellHouseRequest sellHouseRequest) {

        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getCutPriceTopicsSellHouse(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);
        return NashResult.build(sellHouseResponse);
    }


    /**
     * 洼地专题
     * @param sellHouseRequest
     * @return
     */
    @RequestMapping(value ="/getLowPriceTopicsSellHouseDetail", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getLowPriceTopicsSellHouseDetail(@Validated(First.class)  SellHouseRequest sellHouseRequest) {

        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getLowPriceTopicsSellHouse(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);
        return NashResult.build(sellHouseResponse);
    }


    /**
     * 逢出毕抢
     * @param sellHouseRequest
     * @return
     *
     */
    @RequestMapping(value ="/getMustRobTopicsSellHouseDetail", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getMustRobTopicsSellHouseDetail(@Validated(First.class)  SellHouseRequest sellHouseRequest) {

        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getMustRobTopicsSellHouseDetail(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);
        return NashResult.build(sellHouseResponse);
    }

    /**
     * 商圈户型
     * @param sellHouseRequest
     * @return
     *
     */
    @RequestMapping(value ="/getAreaRoomTopicsSellHouseDetail", method = RequestMethod.GET)
    @ResponseBody
    public NashResult getAreaRoomTopicsSellHouseDetail(@Validated(First.class)  SellHouseRequest sellHouseRequest) {

        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getAreaRoomTopicsSellHouseDetail(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain,sellHouseResponse);
        return NashResult.build(sellHouseResponse);
    }

}
