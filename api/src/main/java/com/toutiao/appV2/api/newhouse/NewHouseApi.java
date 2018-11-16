package com.toutiao.appV2.api.newhouse;

import com.toutiao.app.api.chance.request.newhouse.NewHouseDetailsRequest;
import com.toutiao.app.api.chance.request.newhouse.NewHouseDynamicRequest;
import com.toutiao.app.api.chance.request.newhouse.NewHouseListRequest;
import com.toutiao.app.api.chance.request.newhouse.NewHouseTrafficRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHouseDetailResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDomainResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseTrafficResponse;
import com.toutiao.appV2.model.newhouse.GetNewHouseDynamicResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zym
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:27:28.552Z")

@Api(value = "NewHouseApi", description = "新房控制层")
public interface NewHouseApi {
    @ApiOperation(value = "根据newcode获取新房数据", nickname = "getNewHouseDetailByNewCode", notes = "根据newcode获取新房数据", response = NewHouseDetailResponse.class, tags={ "new-house-rest-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NewHouseDetailResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/getDetailByNewCode",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<NewHouseDetailResponse> getNewHouseDetailByNewCode(NewHouseDetailsRequest newHouseDetailsRequest);


    @ApiOperation(value = "根据newcode获取新房动态", nickname = "getNewHouseDynamicByNewCode", notes = "根据newcode获取新房动态", response = GetNewHouseDynamicResponse.class, tags={ "new-house-rest-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GetNewHouseDynamicResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/getNewHouseDynamic",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<GetNewHouseDynamicResponse> getNewHouseDynamicByNewCode(NewHouseDynamicRequest newHouseDynamicRequest);

    @ApiOperation(value = "获取新房列表页", nickname = "getNewHouseList", notes = "获取新房列表页", response = NewHouseListDomainResponse.class, tags={ "new-house-rest-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NewHouseListDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/getNewHouseList",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<NewHouseListDomainResponse> getNewHouseList(NewHouseListRequest newHouseListRequest);

    @ApiOperation(value = "根据newcode获取新房交通信息", nickname = "getNewHouseTraffic", notes = "根据newcode获取新房交通信息", response = NewHouseTrafficResponse.class, tags={ "new-house-rest-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NewHouseTrafficResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/getNewHouseTraffic",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<NewHouseTrafficResponse> getNewHouseTraffic(NewHouseTrafficRequest newHouseTrafficRequest);
}
