package com.toutiao.appV2.apiimpl.newhouse;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.toutiao.app.api.chance.request.activity.NewHouseActivityRequest;
import com.toutiao.app.api.chance.request.newhouse.*;
import com.toutiao.app.api.chance.response.newhouse.NewHouseDetailResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseLayoutCountResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDomainResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseTrafficResponse;
import com.toutiao.app.api.chance.response.user.UserInfoActivityResponse;
import com.toutiao.app.domain.activity.ActivityStatisticsDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivity;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDoQuery;
import com.toutiao.app.domain.newhouse.*;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.activity.NewHouseActivityRestService;
import com.toutiao.app.service.newhouse.NewHouseLayoutService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.app.service.newhouse.NewHouseTopicsRestService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.appV2.api.newhouse.NewHouseApi;
import com.toutiao.appV2.model.newhouse.ActivityMsgResponse;
import com.toutiao.appV2.model.newhouse.GetNewHouseDynamicResponse;
import com.toutiao.appV2.model.newhouse.GetNewHouseLayoutResponse;
import com.toutiao.appV2.model.newhouse.IsAttendeActivityResponse;
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
import java.util.List;

/**
 * @author zym
 */
@Controller
public class NewHouseApiController implements NewHouseApi {

    private static final Logger log = LoggerFactory.getLogger(NewHouseApiController.class);

    private final HttpServletRequest request;

    @Autowired
    private NewHouseLayoutService newHouseLayoutService;

    @Autowired
    private NewHouseTopicsRestService newHouseTopicsRestService;

    @Autowired
    private NewHouseRestService newHouseService;

    @Autowired
    private NewHouseActivityRestService newHouseActivityRestService;

    @Autowired
    private UserBasicInfoService userBasicInfoService;

    @Autowired
    public NewHouseApiController(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<NewHouseLayoutCountResponse> getLayoutCountByNewHouseId(@Validated NewHouseLayoutRequest newHouseLayoutRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                Integer newHouseId = newHouseLayoutRequest.getNewCode();
                NewHouseLayoutCountResponse newHouseLayoutCountResponse = new NewHouseLayoutCountResponse();
                NewHouseLayoutCountDomain newHouseLayoutCountDomain = newHouseLayoutService.getNewHouseLayoutByNewHouseId(newHouseId, CityUtils.getCity());
                BeanUtils.copyProperties(newHouseLayoutCountDomain, newHouseLayoutCountResponse);
                return new ResponseEntity<>(newHouseLayoutCountResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<GetNewHouseLayoutResponse> getNewHouseLayoutByNewCode(@Validated NewHouseLayoutRequest newHouseLayoutRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                Integer newHouseId = newHouseLayoutRequest.getNewCode();
                Integer room = newHouseLayoutRequest.getRoom();
                List<NewHouseLayoutDo> newHouseLayoutDoList = newHouseLayoutService.getNewHouseLayoutList(newHouseId,
                        room, CityUtils.getCity());
                GetNewHouseLayoutResponse response = GetNewHouseLayoutResponse.builder().data(newHouseLayoutDoList)
                        .totalNum(newHouseLayoutDoList.size()).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<NewHouseListDomainResponse> getNewHouseTopic(NewHouseListRequest newHouseListRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
                NewHouseDoQuery newHouseDoQuery = new NewHouseDoQuery();
                BeanUtils.copyProperties(newHouseListRequest, newHouseDoQuery);
                NewHouseListDomain newHouseListDomain = newHouseTopicsRestService.getNewHouseTopic(newHouseDoQuery);
                BeanUtils.copyProperties(newHouseListDomain, newHouseListDomainResponse);
                return new ResponseEntity<>(newHouseListDomainResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<NewHouseDetailResponse> getNewHouseDetailByNewCode(@Validated NewHouseDetailsRequest newHouseDetailsRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseDetailDo newHouseDetailDo = newHouseService.getNewHouseBuildByNewCode(newHouseDetailsRequest.getNewCode(), CityUtils.getCity());
                NewHouseDetailResponse newHouseDetailResponse = JSON.parseObject(JSON.toJSONString(newHouseDetailDo), NewHouseDetailResponse.class);
                return new ResponseEntity<>(newHouseDetailResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<GetNewHouseDynamicResponse> getNewHouseDynamicByNewCode(@Validated NewHouseDynamicRequest newHouseDynamicRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseDynamicDoQuery newHouseDynamicDoQuery = new NewHouseDynamicDoQuery();
                BeanUtils.copyProperties(newHouseDynamicRequest, newHouseDynamicDoQuery);
                List<NewHouseDynamicDo> newHouseDynamicDoList = newHouseService.getNewHouseDynamicByNewCode(newHouseDynamicDoQuery, CityUtils.getCity());
                GetNewHouseDynamicResponse response = GetNewHouseDynamicResponse.builder()
                        .data(newHouseDynamicDoList).totalNum(newHouseDynamicDoList.size()).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<NewHouseListDomainResponse> getNewHouseList(NewHouseListRequest newHouseListRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
                NewHouseDoQuery newHouseDoQuery = new NewHouseDoQuery();
                BeanUtils.copyProperties(newHouseListRequest, newHouseDoQuery);
                NewHouseListDomain newHouseListVo = newHouseService.getNewHouseList(newHouseDoQuery, CityUtils.getCity());
                BeanUtils.copyProperties(newHouseListVo, newHouseListDomainResponse);
                return new ResponseEntity<>(newHouseListDomainResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<NewHouseTrafficResponse> getNewHouseTraffic(NewHouseTrafficRequest newHouseTrafficRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseTrafficResponse newHouseTrafficResponse = new NewHouseTrafficResponse();
                NewHouseTrafficDo newHouseTrafficDo = newHouseService.getNewHouseTrafficByNewCode(newHouseTrafficRequest.getNewCode(), CityUtils.getCity());
                BeanUtils.copyProperties(newHouseTrafficDo, newHouseTrafficResponse);
                return new ResponseEntity<>(newHouseTrafficResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<IsAttendeActivityResponse> isAttendedActivity(@Validated(Second.class) NewHouseActivityRequest
                                                                 newHouseActivityRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {

                if (StringTool.isBlank(newHouseActivityRequest.getCityId())) {
                    newHouseActivityRequest.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
                }
                UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
                BeanUtils.copyProperties(newHouseActivityRequest, userNewBuildingActivityDoQuery);
                List<UserNewBuildingActivity> attendedActivitys = newHouseActivityRestService.isAttendedActivity(userNewBuildingActivityDoQuery);
                IsAttendeActivityResponse response = IsAttendeActivityResponse.builder().data(attendedActivitys)
                        .totalNum(attendedActivitys.size()).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
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
