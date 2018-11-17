package com.toutiao.appV2.api.newhouse;

import com.toutiao.app.api.chance.request.activity.NewHouseActivityRequest;
import com.toutiao.app.api.chance.request.newhouse.*;
import com.toutiao.app.api.chance.response.newhouse.NewHouseDetailResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseLayoutCountResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDomainResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseTrafficResponse;
import com.toutiao.app.api.chance.response.user.UserInfoActivityResponse;
import com.toutiao.app.domain.activity.ActivityStatisticsDo;
import com.toutiao.appV2.model.newhouse.ActivityMsgResponse;
import com.toutiao.appV2.model.newhouse.GetNewHouseDynamicResponse;
import com.toutiao.appV2.model.newhouse.GetNewHouseLayoutResponse;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.restmodel.NashResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zym
 */
@Api(value = "NewHouseApi", description = "新房控制层")
public interface NewHouseApi {

    //楼盘户型
    @ApiOperation(value = "获取楼盘下户型数", nickname = "getLayoutCountByNewHouseId", notes = "获取楼盘下户型数",
            response = NewHouseLayoutCountResponse.class,
            tags={ "new-house-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NewHouseLayoutCountResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/getLayoutCountByNewHouseId",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<NewHouseLayoutCountResponse> getLayoutCountByNewHouseId(NewHouseLayoutRequest newHouseLayoutRequest);


    @ApiOperation(value = "获取楼盘下某户型房源", nickname = "getNewHouseLayoutByNewCode", notes = "获取楼盘下某户型房源",
            response = GetNewHouseLayoutResponse.class, tags={ "new-house-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GetNewHouseLayoutResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/getLayoutByNewHouseId",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<GetNewHouseLayoutResponse> getNewHouseLayoutByNewCode(NewHouseLayoutRequest newHouseLayoutRequest);


    //五环最美新房专题
    @ApiOperation(value = "五环最美新房专题", nickname = "getNewHouseTopic", notes = "五环最美新房专题",
            response = NewHouseListDomainResponse.class, tags={ "new-house-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NewHouseListDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/getNewHouseTopic",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<NewHouseListDomainResponse> getNewHouseTopic(NewHouseListRequest newHouseListRequest);


    //新房
    @ApiOperation(value = "根据newcode获取新房数据", nickname = "getNewHouseDetailByNewCode",
            notes = "根据newcode获取新房数据", response = NewHouseDetailResponse.class, tags={ "new-house-api-controller", })
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


    @ApiOperation(value = "根据newcode获取新房动态", nickname = "getNewHouseDynamicByNewCode",
            notes = "根据newcode获取新房动态", response = GetNewHouseDynamicResponse.class, tags={ "new-house-api-controller", })
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


    @ApiOperation(value = "获取新房列表页", nickname = "getNewHouseList", notes = "获取新房列表页",
            response = NewHouseListDomainResponse.class, tags={ "new-house-api-controller", })
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


    @ApiOperation(value = "根据newcode获取新房交通信息", nickname = "getNewHouseTraffic", notes = "根据newcode获取新房交通信息",
            response = NewHouseTrafficResponse.class, tags={ "new-house-api-controller", })
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


    //新房活动
    @ApiOperation(value = "是否参加活动", nickname = "isAttendedActivity", notes = "是否参加活动",
            response = NashResult.class, tags={ "new-house-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NashResult.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/isAttendedActivity",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<NashResult> isAttendedActivity(@Validated(Second.class) NewHouseActivityRequest newHouseActivityRequest);


    @ApiOperation(value = "查询活动信息", nickname = "queryActivityMsg", notes = "查询活动信息",
            response = ActivityMsgResponse.class, tags={ "new-house-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActivityMsgResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/queryActivityMsg",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ActivityMsgResponse> queryActivityMsg(@Validated(Second.class) NewHouseActivityRequest

                                                                 newHouseActivityRequest);
    @ApiOperation(value = "个人中心累计数量", nickname = "queryActivityMsgCount", notes = "个人中心累计数量",
            response = ActivityStatisticsDo.class, tags={ "new-house-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActivityStatisticsDo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/queryActivityMsgCount",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ActivityStatisticsDo> queryActivityMsgCount();


    @ApiOperation(value = "查询活动信息", nickname = "queryUserActivityMsg", notes = "查询活动信息",
            response = ActivityMsgResponse.class, tags={ "new-house-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActivityMsgResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/queryUserActivityMsg",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ActivityMsgResponse> queryUserActivityMsg(NewHouseActivityRequest newHouseActivityRequest);


    @ApiOperation(value = "查询用户信息", nickname = "queryUserMsg", notes = "查询用户信息",
            response = UserInfoActivityResponse.class, tags={ "new-house-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserInfoActivityResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/queryUserMsg",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<UserInfoActivityResponse> queryUserMsg(@Validated(Second.class) NewHouseActivityRequest newHouseActivityRequest);


    @ApiOperation(value = "提交活动表单", nickname = "saveUserActivityMsg", notes = "提交活动表单",
            response = NashResult.class, tags={ "new-house-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NashResult.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/saveUserActivityMsg",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<NashResult> saveUserActivityMsg(@Validated(First.class) NewHouseActivityRequest newHouseActivityRequest);
}
