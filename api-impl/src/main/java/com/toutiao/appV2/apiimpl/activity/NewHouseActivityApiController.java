/*
package com.toutiao.appV2.apiimpl.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.toutiao.app.api.chance.request.activity.NewHouseActivityRequest;
import com.toutiao.app.api.chance.response.user.UserInfoActivityResponse;
import com.toutiao.app.domain.activity.ActivityStatisticsDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDoQuery;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.activity.NewHouseActivityRestService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.appV2.api.activity.NewHouseActivityApi;
import com.toutiao.appV2.model.newhouse.ActivityMsgResponse;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;

*/
/**
 * @author zym
 *//*

@Controller
public class NewHouseActivityApiController implements NewHouseActivityApi {

    private static final Logger log = LoggerFactory.getLogger(NewHouseActivityApiController.class);

    private final HttpServletRequest request;

    @Autowired
    public NewHouseActivityApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    private NewHouseActivityRestService newHouseActivityRestService;

    @Autowired
    private UserBasicInfoService userBasicInfoService;

    @Override
    public ResponseEntity<NashResult> isAttendedActivity(@Validated(Second.class) NewHouseActivityRequest
                                                                     newHouseActivityRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {

                if (StringTool.isBlank(newHouseActivityRequest.getCityId())) {
                    newHouseActivityRequest.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
                }
                UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
                BeanUtils.copyProperties(newHouseActivityRequest, userNewBuildingActivityDoQuery);
                NashResult respose = newHouseActivityRestService.isAttendedActivity(userNewBuildingActivityDoQuery);
                return new ResponseEntity<>(respose, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<ActivityMsgResponse> queryActivityMsg(@Validated(Second.class) NewHouseActivityRequest
                                                                 newHouseActivityRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
                BeanUtils.copyProperties(newHouseActivityRequest, userNewBuildingActivityDoQuery);

                PageInfo<UserNewBuildingActivityDo> userNewBuildingActivityDoPageInfo = newHouseActivityRestService.listActivityMsg(userNewBuildingActivityDoQuery);
                ActivityMsgResponse response = ActivityMsgResponse.builder()
                        .data(userNewBuildingActivityDoPageInfo.getList())
                        .totalNum(userNewBuildingActivityDoPageInfo.getSize()).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<ActivityStatisticsDo> queryActivityMsgCount() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                ActivityStatisticsDo activityCount = newHouseActivityRestService.getActivityCount();
                return new ResponseEntity<>(activityCount, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<ActivityMsgResponse> queryUserActivityMsg(NewHouseActivityRequest newHouseActivityRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
                BeanUtils.copyProperties(newHouseActivityRequest, userNewBuildingActivityDoQuery);
                PageInfo<UserNewBuildingActivityDo> userNewBuildingActivityDoPageInfo = newHouseActivityRestService.listUserActivityMsg(userNewBuildingActivityDoQuery);
                ActivityMsgResponse response = ActivityMsgResponse.builder()
                        .data(userNewBuildingActivityDoPageInfo.getList())
                        .totalNum(userNewBuildingActivityDoPageInfo.getSize()).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<UserInfoActivityResponse> queryUserMsg(@Validated(Second.class) NewHouseActivityRequest
                                                                             newHouseActivityRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserBasicDo userBasicDo = userBasicInfoService.queryUserBasic(newHouseActivityRequest.getUserId().toString());
                if (StringUtil.isNullString(userBasicDo.getUserCallName())) {
                    userBasicDo.setUserCallName("");
                }
                UserInfoActivityResponse userInfoActivityResponse = new UserInfoActivityResponse();
                BeanUtils.copyProperties(userBasicDo, userInfoActivityResponse);
                return new ResponseEntity<>(userInfoActivityResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<NashResult> saveUserActivityMsg(@Validated(First.class) NewHouseActivityRequest
                                                                      newHouseActivityRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                if (StringTool.isBlank(newHouseActivityRequest.getCityId())) {
                    newHouseActivityRequest.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
                }

                UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
                BeanUtils.copyProperties(newHouseActivityRequest, userNewBuildingActivityDoQuery);
                NashResult respose = newHouseActivityRestService.saveUserActivityMsg(userNewBuildingActivityDoQuery);
                return new ResponseEntity<>(respose, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
*/
