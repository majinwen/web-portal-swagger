package com.toutiao.appV2.apiimpl.newhouse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.toutiao.app.api.chance.request.activity.NewHouseActivityRequest;
import com.toutiao.app.api.chance.request.advertisement.AdNewHouse;
import com.toutiao.app.api.chance.response.homepage.HomePageNewHouseResponse;
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
import com.toutiao.app.service.advertisement.AdNewHouseRestService;
import com.toutiao.app.service.newhouse.NewHouseLayoutService;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.app.service.newhouse.NewHouseTopicsRestService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.appV2.api.newhouse.NewHouseApi;
import com.toutiao.appV2.model.PageRequest;
import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.appV2.model.newhouse.*;
import com.toutiao.appV2.model.userbasic.UserLoginResponse;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.JSONUtil;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

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
    private AdNewHouseRestService adNewHouseRestService;

    @Autowired
    private UserBasicInfoService userBasicInfoService;

    @Autowired
    public NewHouseApiController(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<NewHouseLayoutCountResponse> getLayoutCountByNewHouseId(@Validated NewHouseLayoutRequest newHouseLayoutRequest) {

        Integer newHouseId = newHouseLayoutRequest.getNewCode();
        NewHouseLayoutCountResponse newHouseLayoutCountResponse = new NewHouseLayoutCountResponse();
        NewHouseLayoutCountDomain newHouseLayoutCountDomain = newHouseLayoutService.getNewHouseLayoutByNewHouseId(newHouseId, CityUtils.getCity());
        BeanUtils.copyProperties(newHouseLayoutCountDomain, newHouseLayoutCountResponse);
        return new ResponseEntity<>(newHouseLayoutCountResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<GetNewHouseLayoutResponse> getNewHouseLayoutByNewCode(@Validated NewHouseLayoutRequest newHouseLayoutRequest) {

        Integer newHouseId = newHouseLayoutRequest.getNewCode();
        Integer room = newHouseLayoutRequest.getRoom();
        List<NewHouseLayoutDo> newHouseLayoutDoList = newHouseLayoutService.getNewHouseLayoutList(newHouseId,
                room, CityUtils.getCity());
        GetNewHouseLayoutResponse response = GetNewHouseLayoutResponse.builder().data(newHouseLayoutDoList)
                .totalNum(newHouseLayoutDoList.size()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<NewHouseListDomainResponse> getNewHouseTopic(NewHouseListRequest newHouseListRequest) {

        NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
        NewHouseDoQuery newHouseDoQuery = new NewHouseDoQuery();
        BeanUtils.copyProperties(newHouseListRequest, newHouseDoQuery);
        NewHouseListDomain newHouseListDomain = newHouseTopicsRestService.getNewHouseTopic(newHouseDoQuery);
        BeanUtils.copyProperties(newHouseListDomain, newHouseListDomainResponse);
        return new ResponseEntity<>(newHouseListDomainResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<NewHouseDetailResponse> getNewHouseDetailByNewCode(@Validated NewHouseDetailsRequest newHouseDetailsRequest) {

        NewHouseDetailDo newHouseDetailDo = newHouseService.getNewHouseBuildByNewCode(newHouseDetailsRequest.getNewCode(), CityUtils.getCity());
        NewHouseDetailResponse newHouseDetailResponse = JSON.parseObject(JSON.toJSONString(newHouseDetailDo), NewHouseDetailResponse.class);
        return new ResponseEntity<>(newHouseDetailResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetNewHouseDynamicResponse> getNewHouseDynamicByNewCode(@Validated NewHouseDynamicRequest newHouseDynamicRequest) {
        NewHouseDynamicDoQuery newHouseDynamicDoQuery = new NewHouseDynamicDoQuery();
        BeanUtils.copyProperties(newHouseDynamicRequest, newHouseDynamicDoQuery);
        List<NewHouseDynamicDo> newHouseDynamicDoList = newHouseService.getNewHouseDynamicByNewCode(newHouseDynamicDoQuery, CityUtils.getCity());
        GetNewHouseDynamicResponse response = GetNewHouseDynamicResponse.builder()
                .data(newHouseDynamicDoList).totalNum(newHouseDynamicDoList.size()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewHouseListDomainResponse> getNewHouseListGet(NewHouseListRequest newHouseListRequest) {
        return getNewHouseListDomainResponseResponseEntity(newHouseListRequest);

    }

    @Override
    public ResponseEntity<NewHouseListDomainResponse> getNewHouseListPost(@RequestBody NewHouseListRequest newHouseListRequest) {

        return getNewHouseListDomainResponseResponseEntity(newHouseListRequest);

    }

    private ResponseEntity<NewHouseListDomainResponse> getNewHouseListDomainResponseResponseEntity(@RequestBody NewHouseListRequest newHouseListRequest) {
        NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
        NewHouseDoQuery newHouseDoQuery = new NewHouseDoQuery();
        BeanUtils.copyProperties(newHouseListRequest, newHouseDoQuery);
        NewHouseListDomain newHouseListVo = newHouseService.getNewHouseList(newHouseDoQuery, CityUtils.getCity());
        if (newHouseListVo.getData().size() > 0) {
            newHouseListDomainResponse.setIsGuess(0);
        } else {
            //没有根据结果查询到数据,返回猜你喜欢的数据
            String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);
            Integer userId = null;
            if (null != user) {
                UserLoginResponse userLoginResponse = JSONObject.parseObject(user, UserLoginResponse.class);
                userId = Integer.valueOf(userLoginResponse.getUserId());
            }
            NewHouseGuessLikeRequest guessLikeRequest = new NewHouseGuessLikeRequest();
            BeanUtils.copyProperties(guessLikeRequest, newHouseDoQuery);
            newHouseListVo = newHouseService.queryGuessLikeNewHouseList(newHouseDoQuery, userId, CityUtils.getCity());
            newHouseListDomainResponse.setIsGuess(1);
        }
        BeanUtils.copyProperties(newHouseListVo, newHouseListDomainResponse);
        return new ResponseEntity<>(newHouseListDomainResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewHouseTrafficResponse> getNewHouseTraffic(NewHouseTrafficRequest newHouseTrafficRequest) {

        NewHouseTrafficResponse newHouseTrafficResponse = new NewHouseTrafficResponse();
        NewHouseTrafficDo newHouseTrafficDo = newHouseService.getNewHouseTrafficByNewCode(newHouseTrafficRequest.getNewCode(), CityUtils.getCity());
        BeanUtils.copyProperties(newHouseTrafficDo, newHouseTrafficResponse);
        return new ResponseEntity<>(newHouseTrafficResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<IsAttendeActivityResponse> isAttendedActivity(@RequestBody @Validated(Second.class) NewHouseActivityRequest
                                                                                newHouseActivityRequest) {

        if (StringTool.isBlank(newHouseActivityRequest.getCityId())) {
            newHouseActivityRequest.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
        }
        UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
        BeanUtils.copyProperties(newHouseActivityRequest, userNewBuildingActivityDoQuery);
        List<UserNewBuildingActivity> attendedActivitys = newHouseActivityRestService.isAttendedActivity(userNewBuildingActivityDoQuery);
        IsAttendeActivityResponse response = IsAttendeActivityResponse.builder().data(attendedActivitys)
                .totalNum(attendedActivitys.size()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ActivityMsgResponse> queryActivityMsg(PageRequest pageRequest) {
        UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
        BeanUtils.copyProperties(pageRequest, userNewBuildingActivityDoQuery);
        UserBasic current = UserBasic.getCurrent();
        userNewBuildingActivityDoQuery.setUserId(Integer.parseInt(current.getUserId()));
        PageInfo<UserNewBuildingActivityDo> userNewBuildingActivityDoPageInfo = newHouseActivityRestService.listActivityMsg(userNewBuildingActivityDoQuery);
        ActivityMsgResponse response = ActivityMsgResponse.builder()
                .data(userNewBuildingActivityDoPageInfo.getList())
                .totalNum(userNewBuildingActivityDoPageInfo.getSize()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ActivityStatisticsDo> queryActivityMsgCount() {

        ActivityStatisticsDo activityCount = newHouseActivityRestService.getActivityCount();
        return new ResponseEntity<>(activityCount, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<ActivityMsgResponse> queryUserActivityMsg(NewHouseActivityRequest newHouseActivityRequest) {

        UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
        BeanUtils.copyProperties(newHouseActivityRequest, userNewBuildingActivityDoQuery);
        PageInfo<UserNewBuildingActivityDo> userNewBuildingActivityDoPageInfo = newHouseActivityRestService.listUserActivityMsg(userNewBuildingActivityDoQuery);
        ActivityMsgResponse response = ActivityMsgResponse.builder()
                .data(userNewBuildingActivityDoPageInfo.getList())
                .totalNum(userNewBuildingActivityDoPageInfo.getSize()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserInfoActivityResponse> queryUserMsg(@Validated(Second.class) NewHouseActivityRequest
                                                                         newHouseActivityRequest) {
        UserBasicDo userBasicDo = userBasicInfoService.queryUserBasic(newHouseActivityRequest.getUserId().toString());
        if (StringUtil.isNullString(userBasicDo.getUserCallName())) {
            userBasicDo.setUserCallName("");
        }
        UserInfoActivityResponse userInfoActivityResponse = new UserInfoActivityResponse();
        BeanUtils.copyProperties(userBasicDo, userInfoActivityResponse);
        return new ResponseEntity<>(userInfoActivityResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StringDataResponse> saveUserActivityMsg(@RequestBody @Validated(First.class) NewHouseActivityRequest
                                                                          newHouseActivityRequest) {
        if (StringTool.isBlank(newHouseActivityRequest.getCityId())) {
            newHouseActivityRequest.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
        }

        UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
        BeanUtils.copyProperties(newHouseActivityRequest, userNewBuildingActivityDoQuery);
        String respose = newHouseActivityRestService.saveUserActivityMsg(userNewBuildingActivityDoQuery);
        StringDataResponse stringDataResponse = new StringDataResponse();
        stringDataResponse.setData("保存成功");
        return new ResponseEntity<StringDataResponse>(stringDataResponse, HttpStatus.OK);
    }

    /**
     * 获取新房猜你喜欢
     * @param newHouseGuessLikeRequest
     * @return
     */
    @Override
    public ResponseEntity<NewHouseGuessLikeResponse> getGuessList(@RequestBody NewHouseGuessLikeRequest newHouseGuessLikeRequest) {

        NewHouseGuessLikeResponse newHouseGuessLikeResponse = new NewHouseGuessLikeResponse();
        NewHouseDoQuery newHouseDoQuery = new NewHouseDoQuery();
        // 如果用户登录获取用户
        String user = CookieUtils.validCookieValue1(request, CookieUtils.COOKIE_NAME_USER);

        Integer userId = null;
        if (null != user) {
            UserLoginResponse userLoginResponse = JSONObject.parseObject(user, UserLoginResponse.class);
            userId = Integer.valueOf(userLoginResponse.getUserId());
        }
        BeanUtils.copyProperties(newHouseGuessLikeRequest, newHouseDoQuery);
        NewHouseListDomain newHouseListDomain = newHouseService.queryGuessLikeNewHouseList(newHouseDoQuery, userId, CityUtils.getCity());

        BeanUtils.copyProperties(newHouseListDomain, newHouseGuessLikeResponse);
        newHouseGuessLikeResponse.setTotalNum(newHouseListDomain.getTotalCount());
//        log.info("返回结果集:{}", JSONUtil.stringfy(newHouseGuessLikeResponse));
        return new ResponseEntity<>(newHouseGuessLikeResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NewHouseListDomainResponse> getAdNewHouseListByIds(@Validated(First.class) AdNewHouse adNewHouse) {
        NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
        NewHouseListDomain newHouseListDomain = adNewHouseRestService.getAdNewHouseList(adNewHouse.getNewHouseIds(), CityUtils.getCity());
        BeanUtils.copyProperties(newHouseListDomain, newHouseListDomainResponse);
        return new ResponseEntity<>(newHouseListDomainResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdRecommendNewRespose> getAdRecommendNewHouseByIds(AdNewHouse adNewHouse) {
        NewHouseListDomain adNewHouseHomePage = adNewHouseRestService.getAdNewHouseHomePage(adNewHouse.getNewHouseIds(), CityUtils.getCity());
        JSONArray json = JSONArray.parseArray(JSON.toJSONString(adNewHouseHomePage.getData()));
        List<HomePageNewHouseResponse> newHouseListResponses = JSONObject.parseArray(json.toJSONString(), HomePageNewHouseResponse.class);
        AdRecommendNewRespose response = AdRecommendNewRespose.builder().data(newHouseListResponses).totalNum(newHouseListResponses.size()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
