package com.toutiao.web.apiimpl.rest.compared;

import com.toutiao.app.api.chance.request.compared.ComparedRequest;
import com.toutiao.app.api.chance.request.favorite.SellHouseFavoriteListRequest;
import com.toutiao.app.api.chance.response.favorite.SellHouseFavoriteListResponse;
import com.toutiao.app.domain.compared.HouseComparedDetailDo;
import com.toutiao.app.domain.compared.HouseComparedListDo;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.app.service.compared.ComparedService;
import com.toutiao.web.apiimpl.authentication.IgnoreLogin;
import com.toutiao.web.common.restmodel.NashResult;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.dao.entity.compared.HouseCompared;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/compared")
public class ComparedRestController {
    @Autowired
    private ComparedService comparedService;

    private Integer maxComparedCount = 5;

    /**
     * 登录用户新增房源对比信息
     *
     * @param comparedRequest
     * @return
     */
    @RequestMapping(value = "/saveCompared", method = RequestMethod.POST)
    public NashResult saveCompared(@Validated ComparedRequest comparedRequest) {
        UserBasic userBasic = UserBasic.getCurrent();
        HouseCompared houseCompared = new HouseCompared();
        houseCompared.setCreateTime(DateTime.now().toDate());
        houseCompared.setHouseId(comparedRequest.getHouseId());
        houseCompared.setHouseStatus((short) 0);
        houseCompared.setIsDel((short) 0);
        houseCompared.setUserId(Integer.parseInt(userBasic.getUserId()));

        if (StringUtil.isNotNullString(houseCompared.getHouseId()) && houseCompared.getUserId() != null) {
            HouseCompared currHouseCompared = comparedService.selectByUserIdAndHouseId(houseCompared.getUserId(), houseCompared.getHouseId());
            // 未加入过对比
            if (currHouseCompared == null) {
                comparedService.insertSelective(houseCompared);
                return NashResult.build(houseCompared);
            } else {
                // 已加入过对比，状态为已删除
                if (currHouseCompared.getIsDel() == 1) {
                    currHouseCompared.setIsDel((short) 0);
                    comparedService.updateByPrimaryKeySelective(currHouseCompared);
                    return NashResult.build(currHouseCompared);
                }
                // 已加入过对比，状态为未删除
                else {
                    return NashResult.Fail("该房源已加入对比列表");
                }
            }
        } else {
            return NashResult.build("参数错误");
        }
    }

    /**
     * 登录用户删除房源对比信息
     *
     * @param comparedRequest
     * @return
     */
    @RequestMapping(value = "/deleteCompared", method = RequestMethod.POST)
    public NashResult deleteCompared(ComparedRequest comparedRequest) {
        HouseCompared houseCompared = comparedService.selectByPrimaryKey(comparedRequest.getId());
        houseCompared.setIsDel((short) 1);
        int i = comparedService.updateByPrimaryKeySelective(houseCompared);
        if (i > 0) {
            return NashResult.build(houseCompared);
        } else {
            return NashResult.Fail("删除失败");
        }
    }

    /**
     * 用户获取房源对比信息列表
     *
     * @return
     */
    @RequestMapping(value = "/listCompared", method = RequestMethod.GET)
    public NashResult listCompared() {
        UserBasic userBasic = UserBasic.getCurrent();
        List<HouseCompared> houseComparedList = comparedService.selectByUserId(Integer.parseInt(userBasic.getUserId()));
        return NashResult.build(comparedService.selectComparedByHouseCompareds(houseComparedList));
    }

    /**
     * 未登录用户新增房源对比信息
     *
     * @param comparedRequest
     * @return
     */
    @IgnoreLogin
    @RequestMapping(value = "/saveTempCompared", method = RequestMethod.POST)
    public NashResult saveTempCompared(HttpServletRequest request, HttpServletResponse response, @Validated ComparedRequest comparedRequest) {
        String currHouseId = CookieUtils.getCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED);
        if (StringUtil.isNotNullString(currHouseId)) {
            String[] currHouseIdArray = currHouseId.split("_");
            List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
            if (!currHouseIdList.contains(comparedRequest.getHouseId())) {
                currHouseId = currHouseId + "_" + comparedRequest.getHouseId();
            }
        } else {
            currHouseId = comparedRequest.getHouseId();
        }
        CookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED, currHouseId);
        return NashResult.build(currHouseId);
    }

    /**
     * 未登录用户删除房源对比信息
     *
     * @param comparedRequest
     * @return
     */
    @IgnoreLogin
    @RequestMapping(value = "/deleteTempCompared", method = RequestMethod.POST)
    public NashResult deleteTempCompared(HttpServletRequest request, HttpServletResponse response, @Validated ComparedRequest comparedRequest) {
        String currHouseId = CookieUtils.getCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED);
        if (StringUtil.isNotNullString(currHouseId)) {
            String[] currHouseIdArray = currHouseId.split("_");
            List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
            currHouseIdList = StringUtil.removeAll(currHouseIdList, comparedRequest.getHouseId());
            currHouseId = StringUtil.join(currHouseIdList, "_");
        } else {
            currHouseId = "";
        }
        CookieUtils.setCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED, currHouseId);
        return NashResult.build(currHouseId);
    }

    /**
     * 未登录用户获取房源对比信息列表
     *
     * @return
     */
    @IgnoreLogin
    @RequestMapping(value = "/listTempCompared", method = RequestMethod.GET)
    public NashResult listTempCompared(HttpServletRequest request, HttpServletResponse response) {
        String currHouseId = CookieUtils.getCookie(request, response, CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED);
        List<HouseComparedListDo> houseComparedListDoList = new ArrayList<>();
        if (StringUtil.isNotNullString(currHouseId)) {
            String[] currHouseIdArray = currHouseId.split("_");
            List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
            houseComparedListDoList = comparedService.selectTempComparedByIds(currHouseIdList);
            return NashResult.build(houseComparedListDoList);
        } else {
            currHouseId = "";
        }
        return NashResult.build(houseComparedListDoList);
    }

    /**
     * 房源对比页
     *
     * @return
     */
    @IgnoreLogin
    @RequestMapping(value = "/listComparedDetail", method = RequestMethod.GET)
    public NashResult listComparedDetail(@RequestParam(value = "ids") String comparedHouseIds) {
        List<HouseComparedDetailDo> houseComparedDetailDoList = new ArrayList<>();
        if (StringUtil.isNotNullString(comparedHouseIds)) {
            String[] currHouseIdArray = comparedHouseIds.split(",");
            List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
            if (currHouseIdList.size() > maxComparedCount) {
                currHouseIdList = currHouseIdList.subList(0, 5);
            }
            houseComparedDetailDoList = comparedService.selectComparedDetailByHouseIds(currHouseIdList);
            return NashResult.build(houseComparedDetailDoList);
        }
        return NashResult.build(houseComparedDetailDoList);
    }

    /**
     * 比对列表
     */
    @RequestMapping(value = "/getComparedList",method = RequestMethod.GET)
    public NashResult getComparedList(@Validated SellHouseFavoriteListRequest sellHouseFavoriteListRequest){
        SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery = new SellHouseFavoriteListDoQuery();
        BeanUtils.copyProperties(sellHouseFavoriteListRequest,sellHouseFavoriteListDoQuery);
        SellHouseFavoriteDomain sellHouseFavoriteDomain = comparedService.queryComparedList(sellHouseFavoriteListDoQuery);
        SellHouseFavoriteListResponse sellHouseFavoriteListResponse = new SellHouseFavoriteListResponse();
        BeanUtils.copyProperties(sellHouseFavoriteDomain,sellHouseFavoriteListResponse);
        return NashResult.build(sellHouseFavoriteListResponse);
    }

}
