package com.toutiao.appV2.apiimpl.sellhouse;

import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDoQuery;
import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDomain;
import com.toutiao.app.service.sellhouse.MustBuySellHouseRestService;
import com.toutiao.appV2.api.sellhouse.CutPriceSellHouseRestApi;
import com.toutiao.appV2.model.sellhouse.MustBuyShellHouseRequest;
import com.toutiao.appV2.model.sellhouse.MustBuyShellHouseResponse;
import com.toutiao.web.common.util.JSONUtil;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 降价房专题页控制器
 *
 * @author zym
 */
@RestController
@Slf4j
public class CutPriceSellHouseRestController implements CutPriceSellHouseRestApi {


    @Autowired
    private MustBuySellHouseRestService mustBuySellHouseRestService;

    private final HttpServletRequest request;

    @Autowired
    public CutPriceSellHouseRestController(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 专题页获取降价房List
     *
     * @param mustBuyShellHouseRequest
     * @return
     */
    @Override
    public ResponseEntity<MustBuyShellHouseResponse> getCutPriceShellHouse(@ApiParam(value = "mustBuyShellHouseRequest", required = true) @Validated MustBuyShellHouseRequest mustBuyShellHouseRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(mustBuyShellHouseRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                MustBuyShellHouseDoQuery mustBuyShellHouseDoQuery = new MustBuyShellHouseDoQuery();
                BeanUtils.copyProperties(mustBuyShellHouseRequest, mustBuyShellHouseDoQuery);
                MustBuyShellHouseDomain cutPriceShellHouses = mustBuySellHouseRestService.getMustBuySellHouse(mustBuyShellHouseDoQuery, 1, CityUtils.getCity());
                MustBuyShellHouseResponse mustBuyShellHouseResponse = new MustBuyShellHouseResponse();
                BeanUtils.copyProperties(cutPriceShellHouses, mustBuyShellHouseResponse);
                log.info("返回结果集:{}", JSONUtil.stringfy(mustBuyShellHouseResponse));
                return new ResponseEntity<MustBuyShellHouseResponse>(mustBuyShellHouseResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<MustBuyShellHouseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<MustBuyShellHouseResponse>(HttpStatus.NOT_IMPLEMENTED);
    }
}
