//package com.toutiao.appV2.apiimpl.sellhouse;
//
//import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDoQuery;
//import com.toutiao.app.domain.sellhouse.MustBuyShellHouseDomain;
//import com.toutiao.app.service.sellhouse.MustBuySellHouseRestService;
//import com.toutiao.appV2.api.sellhouse.LowerPriceSellHouseRestApi;
//import com.toutiao.appV2.model.sellhouse.MustBuyShellHouseRequest;
//import com.toutiao.appV2.model.sellhouse.MustBuyShellHouseResponse;
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
///**
// * 捡漏房专题页控制器
// *
// * @author zym
// */
//@RestController
//@Slf4j
//public class LowerPriceSellHouseRestController implements LowerPriceSellHouseRestApi {
//
//    @Autowired
//    private MustBuySellHouseRestService mustBuySellHouseRestService;
//    private final HttpServletRequest request;
//
//    @Autowired
//    public LowerPriceSellHouseRestController(HttpServletRequest request) {
//        this.request = request;
//    }
//
//    /**
//     * 获取捡漏房数据
//     *
//     * @param mustBuyShellHouseRequest
//     * @return
//     */
//    @Override
//    public ResponseEntity<MustBuyShellHouseResponse> getLowerPriceShellHouse(@ApiParam(value = "mustBuyShellHouseRequest", required = true) @Valid MustBuyShellHouseRequest mustBuyShellHouseRequest, BindingResult bindingResult) {
//        MustBuyShellHouseDoQuery mustBuyShellHouseDoQuery = new MustBuyShellHouseDoQuery();
//        BeanUtils.copyProperties(mustBuyShellHouseRequest, mustBuyShellHouseDoQuery);
//        MustBuyShellHouseDomain lowerPriceShellHouses = mustBuySellHouseRestService.getMustBuySellHouse(mustBuyShellHouseDoQuery, 2, CityUtils.getCity());
//        MustBuyShellHouseResponse lowerPriceShellHouseResponse = new MustBuyShellHouseResponse();
//        BeanUtils.copyProperties(lowerPriceShellHouses, lowerPriceShellHouseResponse);
//        log.info("返回结果集:{}", JSONUtil.stringfy(lowerPriceShellHouseResponse));
//        return new ResponseEntity<MustBuyShellHouseResponse>(lowerPriceShellHouseResponse, HttpStatus.OK);
//    }
//}
