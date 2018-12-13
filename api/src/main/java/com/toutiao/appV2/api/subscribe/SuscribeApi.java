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
import com.toutiao.app.domain.subscribe.UserTopSubscribeDetailDo;
import com.toutiao.app.domain.subscribe.UserTopicSubscribeDetailDo;
import com.toutiao.appV2.model.subscribe.UserSubscribeInfoT3;
import com.toutiao.appV2.model.subscribe.UserSubscribeT3DoList;
import com.toutiao.appV2.model.subscribe.UserSubscribeT3Do;
import com.toutiao.appV2.model.ConditionSubscribeRequest;
import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.appV2.model.UserSubscribeList;
import com.toutiao.appV2.model.UserSubscribeListDoList;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    @ApiIgnore
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
            method = RequestMethod.GET)
    ResponseEntity<StringDataResponse> deleteSubscribe(@RequestParam(value = "id") Integer id);


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
    @ApiIgnore
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
    @ApiIgnore
    ResponseEntity<UserSubscribe> saveConditionSubscribe(@ApiParam(value = "conditionSubscribeRequest", required = true) @Valid @RequestBody ConditionSubscribeRequest conditionSubscribeRequest);


    @ApiOperation(value = "新增专题订阅", nickname = "saveTopicSubscribe", notes = "", response = UserSubscribe.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserSubscribe.class),
            @ApiResponse(code = 201, message = "Created", response = UserSubscribe.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/saveTopicSubscribe",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UserSubscribe> saveTopicSubscribe(@ApiParam(value = "UserTopicSubscribeDetailDo", required = true) @Valid @RequestBody UserTopicSubscribeDetailDo userSubscribeDetailDo);

    @ApiOperation(value = "新增排行榜订阅", nickname = "saveTopSubscribe", notes = "", response = UserSubscribe.class, tags={ "订阅", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserSubscribe.class),
            @ApiResponse(code = 201, message = "Created", response = UserSubscribe.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/subscribe/saveTopSubscribe",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UserSubscribe> saveTopSubscribe(@ApiParam(value = "UserTopSubscribeDetailDo", required = true) @Valid @RequestBody UserTopSubscribeDetailDo userSubscribeDetailDo);


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
    @ApiIgnore
    ResponseEntity<UserSubscribe> selectByUserConditionSubscribeMap(@ApiParam(value = "ConditionSubscribeRequest", required = true) @Valid ConditionSubscribeRequest conditionSubscribeRequest);
}
