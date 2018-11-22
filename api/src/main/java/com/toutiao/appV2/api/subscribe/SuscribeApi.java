/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.toutiao.appV2.api.subscribe;
/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */

import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.appV2.model.ConditionSubscribeRequest;
import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.appV2.model.UserSubscribeList;
import com.toutiao.appV2.model.UserSubscribeListDoList;
import com.toutiao.appV2.model.subscribe.CityAllInfoMap;
import com.toutiao.appV2.model.subscribe.CityConditionDoList;
import com.toutiao.appV2.model.subscribe.WapCityList;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:27:32.320Z")

@Api(value = "订阅", description = "查询订阅相关接口")
public interface SuscribeApi {

    @ApiOperation(value = "删除订阅信息", nickname = "deleteConditionSubscribe", notes = "", response = Integer.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 201, message = "Created", response = Integer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/deleteConditionSubscribe",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<StringDataResponse> deleteConditionSubscribe(@NotNull @ApiParam(value = "id", required = true) @Valid @RequestParam(value = "id", required = true) Integer id);


    @ApiOperation(value = "删除订阅信息", nickname = "deleteSubscribe", notes = "", response = Integer.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 201, message = "Created", response = Integer.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/deleteSubscribe",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<StringDataResponse> deleteSubscribe(@NotNull @ApiParam(value = "id", required = true) @Valid @RequestParam(value = "id", required = true) Integer id);


    @ApiOperation(value = "用户获取订阅信息列表", nickname = "listConditionSubscribe", notes = "", response = UserSubscribeList.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserSubscribeList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/listConditionSubscribe",
            produces = { "application/json" },
            //consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<UserSubscribeList> listConditionSubscribe();


    @ApiOperation(value = "用户获取订阅信息列表", nickname = "listIndexSubscribe", notes = "", response = UserSubscribeListDoList.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserSubscribeListDoList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/listIndexSubscribe",
            produces = { "application/json" },
            //consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<UserSubscribeListDoList> listIndexSubscribe();


    @ApiOperation(value = "用户获取订阅信息列表", nickname = "listSubscribe", notes = "", response = UserSubscribeListDoList.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserSubscribeListDoList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/listSubscribe",
            produces = { "application/json" },
            //consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<UserSubscribeListDoList> listSubscribe();


    @ApiOperation(value = "新增条件订阅", nickname = "saveConditionSubscribe", notes = "", response = UserSubscribe.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserSubscribe.class),
            @ApiResponse(code = 201, message = "Created", response = UserSubscribe.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/saveConditionSubscribe",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UserSubscribe> saveConditionSubscribe(@ApiParam(value = "conditionSubscribeRequest", required = true) @Valid @RequestBody ConditionSubscribeRequest conditionSubscribeRequest);


    @ApiOperation(value = "新增订阅信息", nickname = "saveSubscribe", notes = "", response = UserSubscribe.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserSubscribe.class),
            @ApiResponse(code = 201, message = "Created", response = UserSubscribe.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/saveSubscribe",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UserSubscribe> saveSubscribe(@ApiParam(value = "userSubscribeDetailDo", required = true) @Valid @RequestBody UserSubscribeDetailDo userSubscribeDetailDo);


    @ApiOperation(value = "判断订阅信息是否存在", nickname = "selectByUserConditionSubscribeMap", notes = "", response = UserSubscribe.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserSubscribe.class),
            @ApiResponse(code = 201, message = "Created", response = UserSubscribe.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/selectByUserConditionSubscribeMap",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UserSubscribe> selectByUserConditionSubscribeMap(@ApiParam(value = "ConditionSubscribeRequest", required = true) @Valid ConditionSubscribeRequest conditionSubscribeRequest);


    @ApiOperation(value = "查询城市所有信息", nickname = "getCityAllInfo", notes = "", response = CityAllInfoMap.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CityAllInfoMap.class),
            @ApiResponse(code = 201, message = "Created", response = CityAllInfoMap.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/getCityAllInfo",
            produces = { "application/json" },
            //consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<CityAllInfoMap> getCityAllInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId",required = false,defaultValue = "0") Integer cityId,
                                                  @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain",required = false,defaultValue = "") String cityDomain);




    @ApiOperation(value = "查询城市信息", nickname = "getWapCity", notes = "", response = WapCityList.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = WapCityList.class),
            @ApiResponse(code = 201, message = "Created", response = WapCityList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/getWapCity",
            produces = { "application/json" },
            //consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<WapCityList> getWapCity();


    @ApiOperation(value = "根据城市id和type查询citycondition", nickname = "getCityconditionByIdAndType", notes = "", response = CityConditionDoList.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CityConditionDoList.class),
            @ApiResponse(code = 201, message = "Created", response = CityConditionDoList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/getCityconditionByIdAndType",
            produces = { "application/json" },
            //consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<CityConditionDoList> getCityconditionByIdAndType(@ApiParam(value = "cityId", required = true) @Valid @RequestParam(value = "cityId",required = true) Integer cityId,
                                                                    @ApiParam(value = "type", required = true) @Valid @RequestParam(value = "type",required = true) String type);


}
