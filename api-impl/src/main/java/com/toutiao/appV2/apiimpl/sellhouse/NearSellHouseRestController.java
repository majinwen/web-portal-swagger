//package com.toutiao.appV2.apiimpl.sellhouse;
//
//
//import com.toutiao.app.domain.sellhouse.NearBySellHouseDomain;
//import com.toutiao.app.domain.sellhouse.NearBySellHouseQueryDo;
//import com.toutiao.app.service.sellhouse.NearSellHouseRestService;
//import com.toutiao.appV2.api.sellhouse.NearSellHouseRestApi;
//import com.toutiao.appV2.model.sellhouse.NearBySellHouseDomainResponse;
//import com.toutiao.appV2.model.sellhouse.NearBySellHousesRequest;
//import com.toutiao.web.common.util.JSONUtil;
//import com.toutiao.web.common.util.city.CityUtils;
//import io.swagger.annotations.ApiParam;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//import java.util.List;
//
//
//@RestController
//@Slf4j
//public class NearSellHouseRestController implements NearSellHouseRestApi {
//
//    @Autowired
//    private NearSellHouseRestService nearSellHouseRestService;
//    private final HttpServletRequest request;
//
//    @Autowired
//    public NearSellHouseRestController(HttpServletRequest request) {
//        this.request = request;
//    }
//
//
//    /**
//     * 二手房附近5km列表
//     *
//     * @param nearBySellHousesRequest
//     * @return
//     */
//    @Override
//    public ResponseEntity<NearBySellHouseDomainResponse> getNearBySellHouses(@ApiParam(value = "nearBySellHousesRequest", required = true) @Valid NearBySellHousesRequest nearBySellHousesRequest, BindingResult bindingResult) {
//        NearBySellHouseDomainResponse nearBySellHouseDomainResponse = new NearBySellHouseDomainResponse();
//        NearBySellHouseQueryDo nearBySellHouseQueryDo = new NearBySellHouseQueryDo();
//        BeanUtils.copyProperties(nearBySellHousesRequest, nearBySellHouseQueryDo);
//        NearBySellHouseDomain nearBySellHouseDomain = nearSellHouseRestService.getSellHouseByHouseIdAndLocation(nearBySellHouseQueryDo, CityUtils.getCity());
//        BeanUtils.copyProperties(nearBySellHouseDomain, nearBySellHouseDomainResponse);
//        log.info("返回结果集:{}", JSONUtil.stringfy(nearBySellHouseDomainResponse));
//        return new ResponseEntity<NearBySellHouseDomainResponse>(nearBySellHouseDomainResponse, HttpStatus.OK);
//    }
//
//}
