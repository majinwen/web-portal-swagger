package com.toutiao.web.apiimpl.rest.subscribe;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/rest/subscribe")
public class SubscribeRestController {
    @Autowired
    private SubscribeService subscribeService;

    /**
     * 新增订阅信息
     *
     * @param userSubscribeDetailDo
     * @return
     */
    @RequestMapping(value = "/saveSubscribe", method = RequestMethod.POST)
    public NashResult saveCompared(@Validated UserSubscribeDetailDo userSubscribeDetailDo) {
        //对区域id排序
        userSubscribeDetailDo.setDistrictId(idsSort(userSubscribeDetailDo.getDistrictId()));
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribe userSubscribe = new UserSubscribe();
        userSubscribe.setCreateTime(DateTime.now().toDate());
        userSubscribe.setUpdateTime(DateTime.now().toDate());
        userSubscribe.setUserId(Integer.parseInt(userBasic.getUserId()));
        userSubscribe.setUserSubscribeMap(JSONObject.toJSONString(userSubscribeDetailDo, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero));
        try {
            subscribeService.insertSelective(userSubscribe);
            return NashResult.build(userSubscribe);
        } catch (Exception e) {
            return NashResult.Fail("新增订阅失败");
        }
    }

    /**
     * 删除订阅信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteSubscribe", method = RequestMethod.POST)
    public NashResult deleteSubscribe(@RequestParam(value = "id") Integer id) {
        int i = subscribeService.deleteByPrimaryKey(id);
        if (i > 0) {
            return NashResult.build(0);
        } else {
            return NashResult.Fail("删除失败");
        }
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    @RequestMapping(value = "/listSubscribe", method = RequestMethod.GET)
    public NashResult listSubscribe() {
        UserBasic userBasic = UserBasic.getCurrent();
        return NashResult.build(subscribeService.getMySubscribeInfo(Integer.parseInt(userBasic.getUserId()), CityUtils.getCity()));
    }


    /**
     * 用户获取订阅信息列表
     *
     * @return
     */
    @RequestMapping(value = "/listIndexSubscribe", method = RequestMethod.GET)
    public NashResult listIndexSubscribe() {
        UserBasic userBasic = UserBasic.getCurrent();
        return NashResult.build(subscribeService.getIndexSubscribeInfo(Integer.parseInt(userBasic.getUserId()), CityUtils.getCity()));
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

//    /**
//     * 判断订阅信息是否存在
//     *
//     * @return
//     */
//    @RequestMapping(value = "/selectByUserSubscribeMap", method = RequestMethod.POST)
//    public NashResult selectByUserSubscribeMap(UserSubscribeDetailDo userSubscribeDetailDo) {
//        UserBasic userBasic = UserBasic.getCurrent();
//        UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo, Integer.parseInt(userBasic.getUserId()));
//        return NashResult.build(userSubscribe);
//    }

}
