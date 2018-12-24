package com.toutiao.appV2.apiimpl.sellhouse;

import com.toutiao.app.domain.sellhouse.*;
import com.toutiao.app.service.sellhouse.SellHouseThemeRestService;
import com.toutiao.appV2.api.sellhouse.SellHouseThemeRestApi;
import com.toutiao.appV2.model.sellhouse.*;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 降价房专题页控制器
 *
 * @author zym
 */
@RestController
@Slf4j
public class SellHouseThemeRestController implements SellHouseThemeRestApi {


    @Autowired
    private SellHouseThemeRestService sellHouseThemeRestService;

    private final HttpServletRequest request;

    @Autowired
    public SellHouseThemeRestController(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 专题页获取降价房List
     *
     * @param sellHouseThemeRequest
     * @return
     */
    @Override
    public ResponseEntity<SellHouseThemeResponse> getCutPriceShellHouse(@ApiParam(value = "mustBuyShellHouseRequest", required = true) @Valid SellHouseThemeRequest sellHouseThemeRequest) {
        SellHouseThemeResponse sellHouseThemeResponse = new SellHouseThemeResponse();
        SellHouseThemeDoQuery sellHouseThemeDoQuery = new SellHouseThemeDoQuery();
        BeanUtils.copyProperties(sellHouseThemeRequest, sellHouseThemeDoQuery);
        SellHouseThemeDomain cutPriceShellHouse = sellHouseThemeRestService.getCutPriceShellHouse(sellHouseThemeDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(cutPriceShellHouse, sellHouseThemeResponse);
        return new ResponseEntity<>(sellHouseThemeResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SellHouseThemeResponse> getLowerPriceShellHouse(@ApiParam(value = "mustBuyShellHouseRequest", required = true) @Valid SellHouseThemeRequest sellHouseThemeRequest) {
        SellHouseThemeResponse sellHouseThemeResponse = new SellHouseThemeResponse();
        SellHouseThemeDoQuery sellHouseThemeDoQuery = new SellHouseThemeDoQuery();
        BeanUtils.copyProperties(sellHouseThemeRequest, sellHouseThemeDoQuery);
        SellHouseThemeDomain lowPriceShellHouse = sellHouseThemeRestService.getLowPriceShellHouse(sellHouseThemeDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(lowPriceShellHouse, sellHouseThemeResponse);
        return new ResponseEntity<>(sellHouseThemeResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SellHouseThemeResponse> getBeSureToSnatchShellHouse(@ApiParam(value = "sellHouseBeSureToSnatchRequest", required = true) @Valid SellHouseThemeRequest sellHouseThemeRequest) {
        SellHouseThemeResponse sellHouseThemeResponse = new SellHouseThemeResponse();
        SellHouseThemeDoQuery sellHouseThemeDoQuery = new SellHouseThemeDoQuery();
        BeanUtils.copyProperties(sellHouseThemeRequest, sellHouseThemeDoQuery);
        SellHouseThemeDomain beSureToSnatchShellHouse = sellHouseThemeRestService.getBeSureToSnatchShellHouse(sellHouseThemeDoQuery, CityUtils.getCity());
        BeanUtils.copyProperties(beSureToSnatchShellHouse, sellHouseThemeResponse);
        return new ResponseEntity<>(sellHouseThemeResponse, HttpStatus.OK);
    }
}
