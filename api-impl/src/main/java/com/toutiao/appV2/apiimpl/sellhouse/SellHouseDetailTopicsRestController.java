package com.toutiao.appV2.apiimpl.sellhouse;


import com.toutiao.app.domain.sellhouse.SellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.SellHouseDomain;
import com.toutiao.app.service.sellhouse.SellHouseDetailTopicsService;
import com.toutiao.appV2.api.sellhouse.SellHouseDetailTopicsRestApi;
import com.toutiao.appV2.model.sellhouse.SellHouseRequest;
import com.toutiao.appV2.model.sellhouse.SellHouseResponse;
import com.toutiao.web.common.util.JSONUtil;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 专题房源详情页
 */
@RestController
@Slf4j
public class SellHouseDetailTopicsRestController implements SellHouseDetailTopicsRestApi {

    @Autowired
    private SellHouseDetailTopicsService sellHouseDetailTopicsService;
    private final HttpServletRequest request;

    @Autowired
    public SellHouseDetailTopicsRestController(HttpServletRequest request) {
        this.request = request;
    }


    /**
     * 小区附近专题
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseResponse> getNearbyTopicsSellHouseDetail(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult) {
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getNearbyTopicsSellHouse(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain, sellHouseResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseResponse));
        return new ResponseEntity<SellHouseResponse>(sellHouseResponse, HttpStatus.OK);
    }


    /**
     * 降价专题
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseResponse> getCutPriceTopicsSellHouseDetail(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult) {
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getCutPriceTopicsSellHouse(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain, sellHouseResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseResponse));
        return new ResponseEntity<SellHouseResponse>(sellHouseResponse, HttpStatus.OK);
    }


    /**
     * 洼地专题
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseResponse> getLowPriceTopicsSellHouseDetail(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult) {
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getLowPriceTopicsSellHouse(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain, sellHouseResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseResponse));
        return new ResponseEntity<SellHouseResponse>(sellHouseResponse, HttpStatus.OK);
    }


    /**
     * 逢出必抢
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseResponse> getMustRobTopicsSellHouseDetail(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult) {
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getMustRobTopicsSellHouseDetail(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain, sellHouseResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseResponse));
        return new ResponseEntity<SellHouseResponse>(sellHouseResponse, HttpStatus.OK);
    }

    /**
     * 商圈户型
     *
     * @param sellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseResponse> getAreaRoomTopicsSellHouseDetail(@ApiParam(value = "sellHouseRequest", required = true) @Valid SellHouseRequest sellHouseRequest, BindingResult bindingResult) {
        SellHouseResponse sellHouseResponse = new SellHouseResponse();
        SellHouseDoQuery sellHouseDoQuery = new SellHouseDoQuery();
        BeanUtils.copyProperties(sellHouseRequest, sellHouseDoQuery);
        SellHouseDomain sellHouseDomain = sellHouseDetailTopicsService.getAreaRoomTopicsSellHouseDetail(sellHouseDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(sellHouseDomain, sellHouseResponse);
        log.info("返回结果集:{}", JSONUtil.stringfy(sellHouseResponse));
        return new ResponseEntity<SellHouseResponse>(sellHouseResponse, HttpStatus.OK);
    }

}
