package com.toutiao.web.apiimpl.rest.activity;

import com.github.pagehelper.PageInfo;
import com.toutiao.app.api.chance.request.activity.NewHouseActivityRequest;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDo;
import com.toutiao.app.domain.activity.UserNewBuildingActivityDoQuery;
import com.toutiao.app.service.activity.NewHouseActivityRestService;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.restmodel.NashResult;
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

    @ResponseBody
    @RequestMapping(value = "/saveUserActivityMsg",method = RequestMethod.POST)
    public NashResult saveUserActivityMsg(@Validated(First.class) NewHouseActivityRequest newHouseActivityRequest) {

        UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
        BeanUtils.copyProperties(newHouseActivityRequest,userNewBuildingActivityDoQuery);

        return newHouseActivityRestService.saveUserActivityMsg(userNewBuildingActivityDoQuery);
    }



    @ResponseBody
    @RequestMapping(value = "/queryUserActivityMsg",method = RequestMethod.GET)
    public NashResult queryUserActivityMsg(NewHouseActivityRequest newHouseActivityRequest) {

        UserNewBuildingActivityDoQuery userNewBuildingActivityDoQuery = new UserNewBuildingActivityDoQuery();
        BeanUtils.copyProperties(newHouseActivityRequest,userNewBuildingActivityDoQuery);


        PageInfo<UserNewBuildingActivityDo> userNewBuildingActivityDoPageInfo = newHouseActivityRestService.listUserActivityMsg(userNewBuildingActivityDoQuery);

        return NashResult.build(userNewBuildingActivityDoPageInfo);
    }


}
