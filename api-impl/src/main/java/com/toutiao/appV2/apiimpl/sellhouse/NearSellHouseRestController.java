package com.toutiao.appV2.apiimpl.sellhouse;


import com.toutiao.app.domain.sellhouse.NearBySellHouseDomain;
import com.toutiao.app.domain.sellhouse.NearBySellHouseQueryDo;
import com.toutiao.app.service.sellhouse.NearSellHouseRestService;
import com.toutiao.appV2.api.sellhouse.NearSellHouseRestApi;
import com.toutiao.appV2.model.sellhouse.NearBySellHouseDomainResponse;
import com.toutiao.appV2.model.sellhouse.NearBySellHousesRequest;
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


@RestController
@Slf4j
public class NearSellHouseRestController implements NearSellHouseRestApi {

    @Autowired
    private NearSellHouseRestService nearSellHouseRestService;
    private final HttpServletRequest request;

    @Autowired
    public NearSellHouseRestController(HttpServletRequest request) {
        this.request = request;
    }


    /**
     * 二手房附近5km列表
     *
     * @param nearBySellHousesRequest
     * @return
     */
    @Override
    public ResponseEntity<NearBySellHouseDomainResponse> getNearBySellHouses(@ApiParam(value = "nearBySellHousesRequest", required = true) @Validated NearBySellHousesRequest nearBySellHousesRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(nearBySellHousesRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NearBySellHouseDomainResponse nearBySellHouseDomainResponse = new NearBySellHouseDomainResponse();
                NearBySellHouseQueryDo nearBySellHouseQueryDo = new NearBySellHouseQueryDo();
                BeanUtils.copyProperties(nearBySellHousesRequest, nearBySellHouseQueryDo);
                NearBySellHouseDomain nearBySellHouseDomain = nearSellHouseRestService.getSellHouseByHouseIdAndLocation(nearBySellHouseQueryDo, CityUtils.getCity());
                BeanUtils.copyProperties(nearBySellHouseDomain, nearBySellHouseDomainResponse);
                log.info("返回结果集:{}", JSONUtil.stringfy(nearBySellHouseDomainResponse));
                return new ResponseEntity<NearBySellHouseDomainResponse>(nearBySellHouseDomainResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<NearBySellHouseDomainResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<NearBySellHouseDomainResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
