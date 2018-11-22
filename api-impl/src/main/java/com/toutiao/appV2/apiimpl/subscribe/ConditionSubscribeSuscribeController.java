package com.toutiao.appV2.apiimpl.subscribe;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.subscribe.UserConditionSubscribeDetailDo;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.subscribe.CityService;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.appV2.api.subscribe.SuscribeApi;
import com.toutiao.appV2.model.ConditionSubscribeRequest;
import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.appV2.model.UserSubscribeList;
import com.toutiao.appV2.model.UserSubscribeListDoList;
import com.toutiao.appV2.model.subscribe.CityAllInfoMap;
import com.toutiao.appV2.model.subscribe.CityConditionDoList;
import com.toutiao.appV2.model.subscribe.WapCityList;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.City;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-21
 * Time:   17:17
 * Theme:
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:27:32.320Z")
@Controller
public class ConditionSubscribeSuscribeController implements SuscribeApi {

    private static final Logger log = LoggerFactory.getLogger(ConditionSubscribeSuscribeController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public ConditionSubscribeSuscribeController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private CityService cityService;

    @Override
    public ResponseEntity<UserSubscribe> saveConditionSubscribe(@ApiParam(value = "conditionSubscribeRequest", required = true) @Valid @RequestBody ConditionSubscribeRequest conditionSubscribeRequest) {
        //获取用户信息
        UserBasic userBasic = UserBasic.getCurrent();
        UserConditionSubscribeDetailDo userConditionSubscribeDetailDo = new UserConditionSubscribeDetailDo();
        BeanUtils.copyProperties(conditionSubscribeRequest, userConditionSubscribeDetailDo);
        UserSubscribe userSubscribe = new UserSubscribe();
        userSubscribe.setCreateTime(DateTime.now().toDate());
        userSubscribe.setUpdateTime(DateTime.now().toDate());
        userSubscribe.setUserId(Integer.parseInt(userBasic.getUserId()));
        userSubscribe.setSubscribeType(1);
        userSubscribe.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
        userSubscribe.setUserSubscribeMap(JSONObject.toJSONString(userConditionSubscribeDetailDo, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero));
        subscribeService.insertSelective(userSubscribe);
        return new ResponseEntity<UserSubscribe>(userSubscribe, HttpStatus.OK);
    }


    /**
     * 删除订阅信息
     *
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<StringDataResponse> deleteConditionSubscribe(@NotNull @ApiParam(value = "id", required = true) @Valid @RequestParam(value = "id", required = true) Integer id) {
        subscribeService.deleteByPrimaryKey(id);
        StringDataResponse stringDataResponse = new StringDataResponse();
        stringDataResponse.setData("删除条件订阅成功");
        return new ResponseEntity<>(stringDataResponse, HttpStatus.OK);
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    @Override
    public ResponseEntity<UserSubscribeList> listConditionSubscribe() {
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribeList userSubscribeList = new UserSubscribeList();
        userSubscribeList.setUserSubscribeData(subscribeService.getMyConditionSubscribeInfo(Integer.parseInt(userBasic.getUserId())));
        userSubscribeList.setTotal(userSubscribeList.getUserSubscribeData().size());
        return new ResponseEntity<UserSubscribeList>(userSubscribeList, HttpStatus.OK);
    }


    /**
     * 判断订阅信息是否存在
     *
     * @return
     */
    @Override
    public ResponseEntity<UserSubscribe> selectByUserConditionSubscribeMap(@ApiParam(value = "conditionSubscribeRequest", required = true) @Valid @RequestBody ConditionSubscribeRequest conditionSubscribeRequest) {
        UserBasic userBasic = UserBasic.getCurrent();
        UserConditionSubscribeDetailDo userConditionSubscribeDetailDo = new UserConditionSubscribeDetailDo();
        BeanUtils.copyProperties(conditionSubscribeRequest, userConditionSubscribeDetailDo);
        UserSubscribe userSubscribe = subscribeService.selectConditionSubscribeByUserSubscribeMap(userConditionSubscribeDetailDo,
                Integer.parseInt(userBasic.getUserId()), CityUtils.getCity());
        return new ResponseEntity<UserSubscribe>(userSubscribe, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityAllInfoMap> getCityAllInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId", required = false, defaultValue = "0") Integer cityId, @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain", required = false, defaultValue = "") String cityDomain) {
        if (cityId == 0 && Objects.equals(cityDomain, "")) {
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cityId== 0)
        {
            City city = cityService.selectCityByDomain(cityDomain);
            if (city!=null)
            {
                cityId = city.getCityId();
            }
        }
        CityAllInfoMap cityAllInfoMap =new CityAllInfoMap();
        Map<String, Object> res = cityService.getCityAllInfo(cityId);
        cityAllInfoMap.setCityAllInfos(res);
        return new ResponseEntity<CityAllInfoMap>(cityAllInfoMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WapCityList> getWapCity() {
        WapCityList wapCityList =new WapCityList();
        wapCityList.setWapCityList(cityService.selectWapCity());
        return new ResponseEntity<WapCityList>(wapCityList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityConditionDoList> getCityconditionByIdAndType(@ApiParam(value = "cityId", required = true) @Valid @RequestParam(value = "cityId", required = true) Integer cityId, @ApiParam(value = "type", required = true) @Valid @RequestParam(value = "type", required = true) String type) {
        CityConditionDoList cityConditionDoList =new CityConditionDoList();
        cityConditionDoList.setCityConditionDos(cityService.getCityConditionByIdAndType(cityId,type));
        return new ResponseEntity<CityConditionDoList>(cityConditionDoList, HttpStatus.OK);
    }

    /**
     * 新增订阅信息
     *
     * @param userSubscribeDetailDo
     * @return
     */
    @Override
    public ResponseEntity<UserSubscribe> saveSubscribe(@ApiParam(value = "userSubscribeDetailDo", required = true) @Valid @RequestBody UserSubscribeDetailDo userSubscribeDetailDo) {
        //对区域id排序
        userSubscribeDetailDo.setDistrictId(idsSort(userSubscribeDetailDo.getDistrictId()));
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribe userSubscribe = new UserSubscribe();
        userSubscribe.setCreateTime(DateTime.now().toDate());
        userSubscribe.setUpdateTime(DateTime.now().toDate());
        userSubscribe.setUserId(Integer.parseInt(userBasic.getUserId()));
        userSubscribe.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
        userSubscribe.setUserSubscribeMap(JSONObject.toJSONString(userSubscribeDetailDo, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero));
        subscribeService.insertSelective(userSubscribe);
        return new ResponseEntity<UserSubscribe>(userSubscribe, HttpStatus.OK);
    }

    /**
     * 删除订阅信息
     *
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<StringDataResponse> deleteSubscribe(@NotNull @ApiParam(value = "id", required = true) @Valid @RequestParam(value = "id", required = true) Integer id) {

        int i = subscribeService.deleteByPrimaryKey(id);
        StringDataResponse stringDataResponse = new StringDataResponse();
        stringDataResponse.setData("删除订阅成功");
        return new ResponseEntity<>(stringDataResponse, HttpStatus.OK);
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    @Override
    public ResponseEntity<UserSubscribeListDoList> listSubscribe() {
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribeListDoList userSubscribeListDoList = new UserSubscribeListDoList();
        userSubscribeListDoList.setUserSubscribeListDoData(subscribeService.getMySubscribeInfo(Integer.parseInt(userBasic.getUserId()), CityUtils.getCity()));
        return new ResponseEntity<UserSubscribeListDoList>(userSubscribeListDoList, HttpStatus.OK);
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    @Override
    public ResponseEntity<UserSubscribeListDoList> listIndexSubscribe() {
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribeListDoList userSubscribeListDoList = new UserSubscribeListDoList();
        userSubscribeListDoList.setUserSubscribeListDoData(subscribeService.getIndexSubscribeInfo(Integer.parseInt(userBasic.getUserId()), CityUtils.getCity()));
        return new ResponseEntity<UserSubscribeListDoList>(userSubscribeListDoList, HttpStatus.OK);
    }

    /**
     * ids排序
     *
     * @param ids
     * @return
     */

    private String idsSort(String ids) {
        if (StringTool.isEmpty(ids)) {
            return "";
        }
        String[] split = ids.split(",");
        Integer[] integers = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            integers[i] = Integer.valueOf(split[i]);
        }
        Arrays.sort(integers);
        return StringTool.IntegerArrayToString(integers);
    }


}
