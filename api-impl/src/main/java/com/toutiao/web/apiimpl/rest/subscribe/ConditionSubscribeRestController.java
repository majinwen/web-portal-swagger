package com.toutiao.web.apiimpl.rest.subscribe;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.request.subscribe.ConditionSubscribeRequest;
import com.toutiao.app.domain.subscribe.UserConditionSubscribeDetailDo;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.api.chance.response.subscribeListModel.UserSubscribeList;
import com.toutiao.web.api.chance.response.subscribeListModel.UserSubscribeListDoList;
import com.toutiao.web.apiimpl.rest.subscribe.api.RestApi;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-21
 * Time:   17:17
 * Theme:
 */
@RestController
@RequestMapping("/rest/subscribe")
public class ConditionSubscribeRestController  implements RestApi {

    private static final Logger log = LoggerFactory.getLogger(ConditionSubscribeRestController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ConditionSubscribeRestController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
      private SubscribeService subscribeService;


    @RequestMapping(value = "/saveConditionSubscribe", method = RequestMethod.POST)
    public ResponseEntity<UserSubscribe> saveConditionSubscribe(@RequestBody ConditionSubscribeRequest conditionSubscribeRequest) {

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
        try {
            subscribeService.insertSelective(userSubscribe);
            return new ResponseEntity<UserSubscribe>(userSubscribe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除订阅信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteConditionSubscribe", method = RequestMethod.POST)
    public ResponseEntity<Integer> deleteConditionSubscribe(@RequestParam(value = "id") Integer id) {
        int i = 0;
        try {
            i=subscribeService.deleteByPrimaryKey(id);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (i > 0) {
            return new ResponseEntity<Integer>(0, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    @RequestMapping(value = "/listConditionSubscribe", method = RequestMethod.GET)
    public ResponseEntity<UserSubscribeList> listConditionSubscribe() {
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribeList userSubscribeList = new UserSubscribeList();
        try {
            userSubscribeList.setUserSubscribeData(subscribeService.getMyConditionSubscribeInfo(Integer.parseInt(userBasic.getUserId())));
            userSubscribeList.setTotal(userSubscribeList.getUserSubscribeData().size());
            return new ResponseEntity<UserSubscribeList>(userSubscribeList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * 判断订阅信息是否存在
     *
     * @return
     */
    @RequestMapping(value = "/selectByUserConditionSubscribeMap", method = RequestMethod.POST)
    public ResponseEntity<UserSubscribe> selectByUserConditionSubscribeMap(@RequestBody ConditionSubscribeRequest conditionSubscribeRequest) {
        UserBasic userBasic = UserBasic.getCurrent();
        UserConditionSubscribeDetailDo userConditionSubscribeDetailDo = new UserConditionSubscribeDetailDo();
        BeanUtils.copyProperties(conditionSubscribeRequest, userConditionSubscribeDetailDo);
        try {
            UserSubscribe userSubscribe = subscribeService.selectConditionSubscribeByUserSubscribeMap(userConditionSubscribeDetailDo,
                    Integer.parseInt(userBasic.getUserId()), CityUtils.getCity());
            return new ResponseEntity<UserSubscribe>(userSubscribe, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 新增订阅信息
     *
     * @param userSubscribeDetailDo
     * @return
     */
    @RequestMapping(value = "/saveSubscribe", method = RequestMethod.POST)
    public ResponseEntity<UserSubscribe> saveSubscribe(@RequestBody @Validated UserSubscribeDetailDo userSubscribeDetailDo) {
        //对区域id排序
        userSubscribeDetailDo.setDistrictId(idsSort(userSubscribeDetailDo.getDistrictId()));
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribe userSubscribe = new UserSubscribe();
        userSubscribe.setCreateTime(DateTime.now().toDate());
        userSubscribe.setUpdateTime(DateTime.now().toDate());
        userSubscribe.setUserId(Integer.parseInt(userBasic.getUserId()));
        userSubscribe.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
        userSubscribe.setUserSubscribeMap(JSONObject.toJSONString(userSubscribeDetailDo, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero));
        try {
            subscribeService.insertSelective(userSubscribe);
            return new ResponseEntity<UserSubscribe>(userSubscribe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 删除订阅信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteSubscribe", method = RequestMethod.POST)
    public ResponseEntity<Integer> deleteSubscribe(@RequestParam(value = "id") Integer id) {
        int i = subscribeService.deleteByPrimaryKey(id);
        if (i > 0) {
            return new ResponseEntity<Integer>(0, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    @RequestMapping(value = "/listSubscribe", method = RequestMethod.GET)
    public ResponseEntity<UserSubscribeListDoList> listSubscribe() {
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribeListDoList userSubscribeListDoList =new UserSubscribeListDoList();
       try {
           userSubscribeListDoList.setUserSubscribeListDoData(subscribeService.getMySubscribeInfo(Integer.parseInt(userBasic.getUserId()), CityUtils.getCity()));
           return new ResponseEntity<UserSubscribeListDoList>(userSubscribeListDoList, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    @RequestMapping(value = "/listIndexSubscribe", method = RequestMethod.GET)
    public ResponseEntity<UserSubscribeListDoList> listIndexSubscribe() {
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribeListDoList userSubscribeListDoList =new UserSubscribeListDoList();
        try {
            userSubscribeListDoList.setUserSubscribeListDoData(subscribeService.getIndexSubscribeInfo(Integer.parseInt(userBasic.getUserId()), CityUtils.getCity()));
            return new ResponseEntity<UserSubscribeListDoList>(userSubscribeListDoList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
