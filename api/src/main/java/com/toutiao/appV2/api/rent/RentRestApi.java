package com.toutiao.appV2.api.rent;

import com.toutiao.app.api.chance.response.rent.*;
import com.toutiao.appV2.model.rent.NearRentHouseResponse;
import com.toutiao.appV2.model.rent.NearHouseListRequest;
import com.toutiao.appV2.model.rent.RentDetailsRequest;
import com.toutiao.appV2.model.rent.RentHouseRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by CuiShihao on 2018/11/16
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

@Api(value = "租房", description = "租房房源接口")
public interface RentRestApi {

    @ApiOperation(value = "附近5km出租房源(app的是吧，那就优先三公里的录入房源由近到远)", nickname = "getNearRentByLocation", notes = "", response = NearRentHouseResponse.class, tags={ "租房", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NearRentHouseResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/rent/getNearRentHouseByLocation",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<NearRentHouseResponse> getNearRentHouseByLocationUsingGET(@ApiParam(value = "nearHouseListRequest"  )  @Valid @RequestBody NearHouseListRequest nearHouseListRequest);


    @ApiOperation(value = "租房推优房源", nickname = "getRecommendRent", notes = "", response = RecommendRentResponse.class, tags={ "租房", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RecommendRentResponse.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/rent/getRecommendRent",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<RecommendRentResponse> getRecommendRent(@ApiParam(value = "rentHouseRequest" ,required=true )  @Valid @RequestBody RentHouseRequest rentHouseRequest);


//    @ApiOperation(value = "根据id获取该出租房源对应的经纪人(该接口已停用)", nickname = "getRentAgentByRentId", notes = "改接口已停用", response = RentAgentResponse.class, tags={ "rent-rest-controller", })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = RentAgentResponse.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found") })
//    @RequestMapping(value = "/rest/rent/getRentAgentByRentId",
//            produces = { "application/json" },
//            method = RequestMethod.GET)
//    ResponseEntity<RentAgentResponse> getRentAgentByRentId(@ApiParam(value = "rentDetailsRequest" ,required=true )  @Valid @RequestBody RentDetailsRequest rentDetailsRequest);


    @ApiOperation(value = "查找出租房源详细信息", nickname = "getRentDetailByRentId", notes = "", response = RentDetailResponse.class, tags={ "租房", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RentDetailResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/rent/getRentDetailByRentId",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<RentDetailResponse> getRentDetailByRentId(@ApiParam(value = "rentDetailsRequest" ,required=true )  @Valid @RequestBody RentDetailsRequest rentDetailsRequest);


    @ApiOperation(value = "出租房源列表", nickname = "getRentHouseSearchList", notes = "", response = RentDetailFewResponseList.class, tags={ "租房", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RentDetailFewResponseList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/rent/getRentHouseSearchList",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<RentDetailFewResponseList> getRentHouseSearchList(@ApiParam(value = "rentHouseRequest" ,required=true )  @Valid @RequestBody RentHouseRequest rentHouseRequest);


    @ApiOperation(value = "租房推荐列表", nickname = "getRentList", notes = "", response = RentListResponse.class, tags={ "租房", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RentListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/rent/getRentList",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<RentListResponse> getRentList(@ApiParam(value = "rentHouseRequest" ,required=true )  @Valid @RequestBody RentHouseRequest rentHouseRequest);

    @ApiOperation(value = "猜你喜欢:出租房源列表", nickname = "getGuessList", notes = "", response = RentDetailFewResponseList.class, tags={ "租房", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RentDetailFewResponseList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/rent/getGuessList",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<RentDetailFewResponseList> getGuessList(@ApiParam(value = "rentHouseRequest" ,required=true )  @Valid @RequestBody RentHouseRequest rentHouseRequest);
}
