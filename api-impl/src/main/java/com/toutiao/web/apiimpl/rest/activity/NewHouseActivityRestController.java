package com.toutiao.web.apiimpl.rest.activity;

import com.github.pagehelper.PageInfo;
import com.toutiao.app.api.chance.request.activity.NewHouseActivityRequest;
import com.toutiao.app.api.chance.response.user.UserInfoActivityResponse;
import com.toutiao.app.domain.activity.ActivityStatisticsDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDoQuery;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.activity.NewHouseActivityRestService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ Idea
 * Author: Jiang Weilong
 * Date:   2018-09-14
 * Time:   13:57
 * Theme:
 */

@RestController
@RequestMapping("/rest/activity/newHouse")
public class NewHouseActivityRestController {

    @Autowired
    private NewHouseActivityRestService newHouseActivityRestService;
    @Autowired
    private UserBasicInfoService userBasicInfoService;

    /**
     * 提交活动表单
     * @param newHouseActivityRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveUserActivityMsg",method = RequestMethod.POST)
    public NashResult saveUserActivityMsg(@Validated(First.class) NewHouseActivityRequest newHouseActivityRequest) {

        if(StringTool.isBlank(newHouseActivityRequest.getCityId())){
            newHouseActivityRequest.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
        }

        UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
        BeanUtils.copyProperties(newHouseActivityRequest,userNewBuildingActivityDoQuery);

        return newHouseActivityRestService.saveUserActivityMsg(userNewBuildingActivityDoQuery);
    }


    /**
     * 查询活动信息--后台使用
     * @param newHouseActivityRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryUserActivityMsg",method = RequestMethod.GET)
    public NashResult queryUserActivityMsg(NewHouseActivityRequest newHouseActivityRequest) {

        UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
        BeanUtils.copyProperties(newHouseActivityRequest,userNewBuildingActivityDoQuery);


        PageInfo<UserNewBuildingActivityDo> userNewBuildingActivityDoPageInfo = newHouseActivityRestService.listUserActivityMsg(userNewBuildingActivityDoQuery);

        return NashResult.build(userNewBuildingActivityDoPageInfo);
    }

    /**
     * 查询用户信息
     * @param newHouseActivityRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryUserMsg",method = RequestMethod.GET)
    public NashResult queryUserMsg(@Validated(Second.class) NewHouseActivityRequest newHouseActivityRequest) {

        UserBasicDo userBasicDo = userBasicInfoService.queryUserBasic(newHouseActivityRequest.getUserId().toString());
        if(StringUtil.isNullString(userBasicDo.getUserCallName())){
            userBasicDo.setUserCallName("");
        }
        UserInfoActivityResponse userInfoActivityResponse = new UserInfoActivityResponse();
        BeanUtils.copyProperties(userBasicDo,userInfoActivityResponse);
        return NashResult.build(userInfoActivityResponse);
    }


    /**
     * 查询活动信息--前台个人中心使用
     * @param newHouseActivityRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryActivityMsg",method = RequestMethod.GET)
    public NashResult queryActivityMsg(@Validated(Second.class) NewHouseActivityRequest newHouseActivityRequest) {

        UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
        BeanUtils.copyProperties(newHouseActivityRequest,userNewBuildingActivityDoQuery);


        PageInfo<UserNewBuildingActivityDo> userNewBuildingActivityDoPageInfo = newHouseActivityRestService.listActivityMsg(userNewBuildingActivityDoQuery);

        return NashResult.build(userNewBuildingActivityDoPageInfo);
    }

    /**
     * 个人中心l累计数量
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryActivityMsgCount",method = RequestMethod.GET)
    public NashResult queryActivityMsgCount() {


        ActivityStatisticsDo activityCount = newHouseActivityRestService.getActivityCount();

        return NashResult.build(activityCount);
    }








}
