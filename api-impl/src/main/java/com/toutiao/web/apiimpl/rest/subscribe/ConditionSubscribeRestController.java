package com.toutiao.web.apiimpl.rest.subscribe;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.subscribe.UserConditionSubscribeDetailDo;

import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.appV2.model.ConditionSubscribeRequest;
import com.toutiao.appV2.model.UserSubscribeList;
import com.toutiao.appV2.model.UserSubscribeListDoList;
import com.toutiao.web.apiimpl.rest.subscribe.api.RestApi;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-21
 * Time:   17:17
 * Theme:
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:27:32.320Z")
@Controller
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

    public ResponseEntity<UserSubscribe> saveConditionSubscribe(@ApiParam(value = "conditionSubscribeRequest" ,required=true )  @Valid @RequestBody ConditionSubscribeRequest conditionSubscribeRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
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
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<UserSubscribe>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserSubscribe>(HttpStatus.NOT_IMPLEMENTED);
    }



    /**
     * 删除订阅信息
     *
     * @param id
     * @return
     */

    public ResponseEntity<Integer> deleteConditionSubscribe(@NotNull @ApiParam(value = "id", required = true) @Valid @RequestParam(value = "id", required = true) Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
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
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Integer>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */

    public ResponseEntity<UserSubscribeList> listConditionSubscribe() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserBasic userBasic = UserBasic.getCurrent();
                UserSubscribeList userSubscribeList = new UserSubscribeList();
                    userSubscribeList.setUserSubscribeData(subscribeService.getMyConditionSubscribeInfo(Integer.parseInt(userBasic.getUserId())));
                    userSubscribeList.setTotal(userSubscribeList.getUserSubscribeData().size());
                    return new ResponseEntity<UserSubscribeList>(userSubscribeList, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<UserSubscribeList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserSubscribeList>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 判断订阅信息是否存在
     *
     * @return
     */
    public ResponseEntity<UserSubscribe> selectByUserConditionSubscribeMap(@ApiParam(value = "conditionSubscribeRequest" ,required=true )  @Valid @RequestBody ConditionSubscribeRequest conditionSubscribeRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserBasic userBasic = UserBasic.getCurrent();
                UserConditionSubscribeDetailDo userConditionSubscribeDetailDo = new UserConditionSubscribeDetailDo();
                BeanUtils.copyProperties(conditionSubscribeRequest, userConditionSubscribeDetailDo);
                UserSubscribe userSubscribe = subscribeService.selectConditionSubscribeByUserSubscribeMap(userConditionSubscribeDetailDo,
                        Integer.parseInt(userBasic.getUserId()), CityUtils.getCity());
                return new ResponseEntity<UserSubscribe>(userSubscribe, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<UserSubscribe>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserSubscribe>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 新增订阅信息
     *
     * @param userSubscribeDetailDo
     * @return
     */
    public ResponseEntity<UserSubscribe> saveSubscribe(@ApiParam(value = "userSubscribeDetailDo" ,required=true )  @Valid @RequestBody UserSubscribeDetailDo userSubscribeDetailDo) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
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
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<UserSubscribe>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserSubscribe>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * 删除订阅信息
     *
     * @param id
     * @return
     */
    public ResponseEntity<Integer> deleteSubscribe(@NotNull @ApiParam(value = "id", required = true) @Valid @RequestParam(value = "id", required = true) Integer id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                int i = subscribeService.deleteByPrimaryKey(id);
                if (i > 0) {
                    return new ResponseEntity<Integer>(0, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Integer>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    public ResponseEntity<UserSubscribeListDoList> listSubscribe() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserBasic userBasic = UserBasic.getCurrent();
                UserSubscribeListDoList userSubscribeListDoList =new UserSubscribeListDoList();
                userSubscribeListDoList.setUserSubscribeListDoData(subscribeService.getMySubscribeInfo(Integer.parseInt(userBasic.getUserId()), CityUtils.getCity()));
                return new ResponseEntity<UserSubscribeListDoList>(userSubscribeListDoList, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<UserSubscribeListDoList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserSubscribeListDoList>(HttpStatus.NOT_IMPLEMENTED);
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    public ResponseEntity<UserSubscribeListDoList> listIndexSubscribe() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserBasic userBasic = UserBasic.getCurrent();
                UserSubscribeListDoList userSubscribeListDoList =new UserSubscribeListDoList();
                userSubscribeListDoList.setUserSubscribeListDoData(subscribeService.getIndexSubscribeInfo(Integer.parseInt(userBasic.getUserId()), CityUtils.getCity()));
                return new ResponseEntity<UserSubscribeListDoList>(userSubscribeListDoList, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<UserSubscribeListDoList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<UserSubscribeListDoList>(HttpStatus.NOT_IMPLEMENTED);
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
