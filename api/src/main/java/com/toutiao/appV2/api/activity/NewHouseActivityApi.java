package com.toutiao.appV2.api.activity;

import com.toutiao.app.api.chance.request.activity.NewHouseActivityRequest;
import com.toutiao.app.api.chance.response.user.UserInfoActivityResponse;
import com.toutiao.app.domain.activity.ActivityStatisticsDo;
import com.toutiao.appV2.model.activity.ActivityMsgResponse;
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
@Api(value = "NewHouseActivityApi", description = "新房活动控制层")
public interface NewHouseActivityApi {

    @ApiOperation(value = "是否参加活动", nickname = "isAttendedActivity", notes = "是否参加活动",
            response = NashResult.class, tags={ "new-house-activity-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NashResult.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/activity/newHouse/isAttendedActivity",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<NashResult> isAttendedActivity(@Validated(Second.class) NewHouseActivityRequest newHouseActivityRequest);


    @ApiOperation(value = "查询活动信息", nickname = "queryActivityMsg", notes = "查询活动信息",
            response = ActivityMsgResponse.class, tags={ "new-house-activity-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActivityMsgResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/activity/newHouse/queryActivityMsg",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ActivityMsgResponse> queryActivityMsg(@Validated(Second.class) NewHouseActivityRequest
                                                       newHouseActivityRequest);


    @ApiOperation(value = "个人中心l累计数量", nickname = "queryActivityMsgCount", notes = "个人中心l累计数量",
            response = ActivityStatisticsDo.class, tags={ "new-house-activity-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActivityStatisticsDo.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/activity/newHouse/queryActivityMsgCount",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ActivityStatisticsDo> queryActivityMsgCount();


    @ApiOperation(value = "查询活动信息", nickname = "queryUserActivityMsg", notes = "查询活动信息",
            response = ActivityMsgResponse.class, tags={ "new-house-activity-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ActivityMsgResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/activity/newHouse/queryUserActivityMsg",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<ActivityMsgResponse> queryUserActivityMsg(NewHouseActivityRequest newHouseActivityRequest);


    @ApiOperation(value = "查询用户信息", nickname = "queryUserMsg", notes = "查询用户信息",
            response = UserInfoActivityResponse.class, tags={ "new-house-activity-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserInfoActivityResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/activity/newHouse/queryUserMsg",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<UserInfoActivityResponse> queryUserMsg(@Validated(Second.class) NewHouseActivityRequest newHouseActivityRequest);


    @ApiOperation(value = "提交活动表单", nickname = "saveUserActivityMsg", notes = "提交活动表单",
            response = NashResult.class, tags={ "new-house-activity-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NashResult.class),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/activity/newHouse/saveUserActivityMsg",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<NashResult> saveUserActivityMsg(@Validated(First.class) NewHouseActivityRequest newHouseActivityRequest);

}
