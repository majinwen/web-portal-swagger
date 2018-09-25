package com.toutiao.web.apiimpl.rest.subscribe;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.toutiao.app.api.chance.request.subscribe.ConditionSubscribeRequest;
import com.toutiao.app.domain.subscribe.UserConditionSubscribeDetailDo;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-08-21
 * Time:   17:17
 * Theme:
 */
@RestController
@RequestMapping("/rest/subscribe")
public class ConditionSubscribeRestController {

    @Autowired
    private SubscribeService subscribeService;


    @RequestMapping(value = "/saveConditionSubscribe", method = RequestMethod.POST)
    public NashResult saveCompared(@RequestBody ConditionSubscribeRequest conditionSubscribeRequest) {

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
            return NashResult.build(userSubscribe);
        } catch (Exception e) {
            return NashResult.Fail("新增条件订阅失败");
        }
    }


    /**
     * 删除订阅信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteConditionSubscribe", method = RequestMethod.POST)
    public NashResult deleteSubscribe(@RequestParam(value = "id") Integer id) {
        int i = subscribeService.deleteByPrimaryKey(id);
        if (i > 0) {
            return NashResult.build(0);
        } else {
            return NashResult.Fail("删除条件订阅失败");
        }
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    @RequestMapping(value = "/listConditionSubscribe", method = RequestMethod.GET)
    public NashResult listSubscribe() {
        UserBasic userBasic = UserBasic.getCurrent();
        return NashResult.build(subscribeService.getMyConditionSubscribeInfo(Integer.parseInt(userBasic.getUserId())));
    }


    /**
     * 判断订阅信息是否存在
     *
     * @return
     */
    @RequestMapping(value = "/selectByUserConditionSubscribeMap", method = RequestMethod.POST)
    public NashResult selectByUserSubscribeMap(@RequestBody ConditionSubscribeRequest conditionSubscribeRequest) {
        UserBasic userBasic = UserBasic.getCurrent();
        UserConditionSubscribeDetailDo userConditionSubscribeDetailDo = new UserConditionSubscribeDetailDo();
        BeanUtils.copyProperties(conditionSubscribeRequest, userConditionSubscribeDetailDo);

        UserSubscribe userSubscribe = subscribeService.selectConditionSubscribeByUserSubscribeMap(userConditionSubscribeDetailDo,
                Integer.parseInt(userBasic.getUserId()), CityUtils.getCity());
        return NashResult.build(userSubscribe);
    }


}
