package com.toutiao.web.apiimpl.rest.subscribe;

import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.domain.subscribe.UserSubscribeDetailDo;
import com.toutiao.app.service.subscribe.SubscribeService;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.entity.subscribe.UserSubscribe;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribe userSubscribe = new UserSubscribe();
        userSubscribe.setCreateTime(DateTime.now().toDate());
        userSubscribe.setUpdateTime(DateTime.now().toDate());
        userSubscribe.setUserId(Integer.parseInt(userBasic.getUserId()));
        userSubscribe.setUserSubscribeMap(JSONObject.toJSONString(userSubscribeDetailDo));
        try {
            subscribeService.insertSelective(userSubscribe);
            return NashResult.build(userSubscribe);
        } catch (Exception e) {
            return NashResult.build("新增订阅失败");
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
//        UserBasic userBasic = UserBasic.getCurrent();
//        List<HouseCompared> houseComparedList = comparedService.selectByUserId(Integer.parseInt(userBasic.getUserId()));
//        return NashResult.build(comparedService.selectComparedByHouseCompareds(houseComparedList));
        return NashResult.build(0);
    }


    /**
     * 判断订阅信息是否存在
     *
     * @return
     */
    @RequestMapping(value = "/listCompared", method = RequestMethod.POST)
    public NashResult listCompared(UserSubscribeDetailDo userSubscribeDetailDo) {
        UserBasic userBasic = UserBasic.getCurrent();
        UserSubscribe userSubscribe = subscribeService.selectByUserSubscribeMap(userSubscribeDetailDo, Integer.parseInt(userBasic.getUserId()));
        return NashResult.build(userSubscribe);
    }
//
//    /**
//     * 未登录用户新增房源对比信息
//     *
//     * @param comparedRequest
//     * @return
//     */
//    @IgnoreLogin
//    @RequestMapping(value = "/saveTempCompared", method = RequestMethod.POST)
//    public NashResult saveTempCompared(HttpServletRequest request, HttpServletResponse response, @Validated ComparedRequest comparedRequest) {
//        String currHouseId = CookieUtils.getCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED);
//        if (StringUtil.isNotNullString(currHouseId)) {
//            String[] currHouseIdArray = currHouseId.split("_");
//            List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
//            if (!currHouseIdList.contains(comparedRequest.getHouseId())) {
//                currHouseId = currHouseId + "_" + comparedRequest.getHouseId();
//            }
//        } else {
//            currHouseId = comparedRequest.getHouseId();
//        }
//        CookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED, currHouseId);
//        return NashResult.build(currHouseId);
//    }
//
//    /**
//     * 未登录用户删除房源对比信息
//     *
//     * @param comparedRequest
//     * @return
//     */
//    @IgnoreLogin
//    @RequestMapping(value = "/deleteTempCompared", method = RequestMethod.POST)
//    public NashResult deleteTempCompared(HttpServletRequest request, HttpServletResponse response, @Validated ComparedRequest comparedRequest) {
//        String currHouseId = CookieUtils.getCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED);
//        if (StringUtil.isNotNullString(currHouseId)) {
//            String[] currHouseIdArray = currHouseId.split("_");
//            List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
//            currHouseIdList = StringUtil.removeAll(currHouseIdList, comparedRequest.getHouseId());
//            currHouseId = StringUtil.join(currHouseIdList, "_");
//        } else {
//            currHouseId = "";
//        }
//        CookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED, currHouseId);
//        return NashResult.build(currHouseId);
//    }
//
//    /**
//     * 未登录用户获取房源对比信息列表
//     *
//     * @return
//     */
//    @IgnoreLogin
//    @RequestMapping(value = "/listTempCompared", method = RequestMethod.GET)
//    public NashResult listTempCompared(HttpServletRequest request, HttpServletResponse response) {
//        String currHouseId = CookieUtils.getCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED);
//        List<HouseComparedListDo> houseComparedListDoList = new ArrayList<>();
//        if (StringUtil.isNotNullString(currHouseId)) {
//            String[] currHouseIdArray = currHouseId.split("_");
//            List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
//            houseComparedListDoList = comparedService.selectTempComparedByIds(currHouseIdList);
//            return NashResult.build(houseComparedListDoList);
//        } else {
//            currHouseId = "";
//        }
//        return NashResult.build(houseComparedListDoList);
//    }
//
//    /**
//     * 房源对比页
//     *
//     * @return
//     */
//    @IgnoreLogin
//    @RequestMapping(value = "/listComparedDetail", method = RequestMethod.GET)
//    public NashResult listComparedDetail(@RequestParam(value = "ids") String comparedHouseIds) {
//        List<HouseComparedDetailDo> houseComparedDetailDoList = new ArrayList<>();
//        if (StringUtil.isNotNullString(comparedHouseIds)) {
//            String[] currHouseIdArray = comparedHouseIds.split(",");
//            List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
//            if (currHouseIdList.size() > 5) {
//                currHouseIdList = currHouseIdList.subList(0, 5);
//            }
//            houseComparedDetailDoList = comparedService.selectComparedDetailByHouseIds(currHouseIdList);
//            return NashResult.build(houseComparedDetailDoList);
//        }
//        return NashResult.build(houseComparedDetailDoList);
//    }
}
