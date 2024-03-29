//package com.toutiao.appV2.api.rent;
//
//import com.toutiao.appV2.model.rent.RentCustomConditionResponse;
//import com.toutiao.appV2.model.rent.UserFavoriteRentListRequest;
//import com.toutiao.appV2.model.rent.UserFavoriteRentListResponse;
//import io.swagger.annotations.*;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.validation.Valid;
//
///**
// * Created by CuiShihao on 2018/12/11
// */
//@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")
//
//@Api(value = "租房", description = "租房定制条件房源接口")
//public interface UserFavoriteRentApi {
//    @ApiOperation(value = "定制条件租房列表", nickname = "getRentHouseListByUserFavorite", notes = "", response = UserFavoriteRentListResponse.class, tags={ "租房", })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = UserFavoriteRentListResponse.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found") })
//    @RequestMapping(value = "/rest/rent/getRentByUserFavorite",
//            produces = { "application/json" },
//            method = RequestMethod.GET)
//    ResponseEntity<UserFavoriteRentListResponse> getRentHouseListByUserFavorite(@ApiParam(value = "userFavoriteRentListRequest"  )  @Valid UserFavoriteRentListRequest userFavoriteRentListRequest);
//
//    @ApiOperation(value = "根据地铁线获取相应的小区数量和房源数量", nickname = "getHouseCountBySubway", notes = "", response = RentCustomConditionResponse.class, tags={ "租房", })
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "OK", response = RentCustomConditionResponse.class),
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Not Found") })
//    @RequestMapping(value = "/rest/rent/getHouseCountByCondition",
//            produces = { "application/json" },
//            method = RequestMethod.GET)
//    ResponseEntity<RentCustomConditionResponse> getHouseCountBySubway(@ApiParam(value = "userFavoriteRentListRequest"  )  @Valid UserFavoriteRentListRequest userFavoriteRentListRequest);
//
//}
