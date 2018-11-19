package com.toutiao.appV2.api.compared;


import com.toutiao.appV2.model.compared.ComparedRequest;
import com.toutiao.appV2.model.compared.HouseComparedDetailDoListResponse;
import com.toutiao.appV2.model.compared.HouseComparedListDoListResponse;
import com.toutiao.appV2.model.favorite.SellHouseFavoriteListRequest;
import com.toutiao.appV2.model.favorite.SellHouseFavoriteListResponse;
import com.toutiao.web.dao.entity.compared.HouseCompared;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:35:29.105Z")

@Api(value = "房源对比接口", description = "房源对比接口")
public interface ComparedRestApi {

    @ApiOperation(value = "登录用户删除房源对比信息", nickname = "deleteCompared", notes = "", response = HouseCompared.class, tags = {"房源对比接口",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HouseCompared.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/compared/deleteCompared",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HouseCompared> deleteCompared(@ApiParam(value = "comparedRequest", required = true) @Valid @RequestBody ComparedRequest comparedRequest);


    @ApiOperation(value = "未登录用户删除房源对比信息", nickname = "deleteTempCompared", notes = "", response = String.class, tags = {"房源对比接口",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/compared/deleteTempCompared",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> deleteTempCompared(@ApiParam(value = "comparedRequest", required = true) @Valid @RequestBody ComparedRequest comparedRequest);


    @ApiOperation(value = "比对列表", nickname = "getComparedList", notes = "", response = SellHouseFavoriteListResponse.class, tags = {"房源对比接口",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SellHouseFavoriteListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/compared/getComparedList",
            produces = {"application/json"},
//            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SellHouseFavoriteListResponse> getComparedList(@ApiParam(value = "sellHouseFavoriteListRequest", required = true) @Valid  SellHouseFavoriteListRequest sellHouseFavoriteListRequest);


    @ApiOperation(value = "用户获取房源对比信息列表", nickname = "listCompared", notes = "", response = HouseComparedListDoListResponse.class, tags = {"房源对比接口",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HouseComparedListDoListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/compared/listCompared",
            produces = {"application/json"},
//            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HouseComparedListDoListResponse> listCompared();


    @ApiOperation(value = "房源对比页", nickname = "listComparedDetail", notes = "", response = HouseComparedDetailDoListResponse.class, tags = {"房源对比接口",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HouseComparedDetailDoListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/compared/listComparedDetail",
            produces = {"application/json"},
//            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HouseComparedDetailDoListResponse> listComparedDetail(@NotNull @ApiParam(value = "ids", required = true) @Valid @RequestParam(value = "ids", required = true) String ids);


    @ApiOperation(value = "未登录用户获取房源对比信息列表", nickname = "listTempCompared", notes = "", response = HouseComparedListDoListResponse.class, tags = {"房源对比接口",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HouseComparedListDoListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/compared/listTempCompared",
            produces = {"application/json"},
//            consumes = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HouseComparedListDoListResponse> listTempCompared();


    @ApiOperation(value = "登录用户新增房源对比信息", nickname = "saveCompared", notes = "", response = HouseCompared.class, tags = {"房源对比接口",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HouseCompared.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/compared/saveCompared",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<HouseCompared> saveCompared(@ApiParam(value = "comparedRequest", required = true) @Valid @RequestBody ComparedRequest comparedRequest);


    @ApiOperation(value = "未登录用户新增房源对比信息", nickname = "saveTempCompared", notes = "", response = String.class, tags = {"房源对比接口",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/compared/saveTempCompared",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> saveTempCompared(@ApiParam(value = "comparedRequest", required = true) @Valid @RequestBody ComparedRequest comparedRequest);

}
