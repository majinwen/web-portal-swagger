/*
package com.toutiao.appV2.api.advertisement;

import com.toutiao.app.api.chance.request.advertisement.AdNewHouse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDomainResponse;
import com.toutiao.appV2.model.newhouse.AdRecommendNewRespose;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "AdNewHouseApi", description = "新房广告控制层")
public interface AdNewHouseApi {

    @ApiOperation(value = "新房首页-获取推荐新房列表页面广告信息", nickname = "getAdNewHouseListByIds",
            notes = "新房首页-获取推荐新房列表页面广告信息", response = NewHouseListDomainResponse.class,
            tags={ "ad-new-house-api-controller", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = NewHouseListDomainResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/ad/newHouse/getAdNewHouseListByIds",
        produces = { "application/json" },
//        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<NewHouseListDomainResponse> getAdNewHouseListByIds(AdNewHouse adNewHouse);


    @ApiOperation(value = "新房列表广告信息", nickname = "getAdRecommendNewHouseByIds", notes = "新房列表广告信息",
            response = AdRecommendNewRespose.class, tags={ "ad-new-house-api-controller", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = AdRecommendNewRespose.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/ad/newHouse/getAdRecommendNewHouseByIds",
        produces = { "application/json" },
//        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<AdRecommendNewRespose> getAdRecommendNewHouseByIds(@Validated AdNewHouse adNewHouse);

}
*/
