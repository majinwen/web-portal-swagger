package com.toutiao.appV2.apiimpl.compared;

import com.toutiao.app.domain.compared.HouseComparedDetailDo;
import com.toutiao.app.domain.compared.HouseComparedListDo;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteDomain;
import com.toutiao.app.domain.favorite.sellhouse.SellHouseFavoriteListDoQuery;
import com.toutiao.app.service.compared.ComparedService;
import com.toutiao.appV2.api.compared.ComparedRestApi;
import com.toutiao.appV2.model.compared.ComparedRequest;
import com.toutiao.appV2.model.compared.HouseComparedDetailDoListResponse;
import com.toutiao.appV2.model.compared.HouseComparedListDoListResponse;
import com.toutiao.appV2.model.favorite.SellHouseFavoriteListRequest;
import com.toutiao.appV2.model.favorite.SellHouseFavoriteListResponse;
import com.toutiao.web.common.constant.city.CityConstant;
import com.toutiao.web.common.util.CookieUtils;
import com.toutiao.web.common.util.JSONUtil;
import com.toutiao.web.common.util.StringUtil;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.compared.HouseCompared;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:35:29.105Z")

@Controller
public class ComparedRestController implements ComparedRestApi {

    private static final Logger log = LoggerFactory.getLogger(ComparedRestController.class);

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    @Autowired
    public ComparedRestController(HttpServletRequest request, HttpServletResponse response) {
        this.response = response;
        this.request = request;
    }

    @Autowired
    private ComparedService comparedService;
    private Integer maxComparedCount = 5;

    @Override
    public ResponseEntity<HouseCompared> deleteCompared(@ApiParam(value = "comparedRequest" ,required=true )  @Valid @RequestBody ComparedRequest comparedRequest) {
        String thisMethodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("调用方法:{}", thisMethodName);
        log.info("接收参数:{}", JSONUtil.stringfy(comparedRequest));
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                HouseCompared houseCompared = comparedService.selectByPrimaryKey(comparedRequest.getId());
                houseCompared.setIsDel((short) 1);
                int i = comparedService.updateByPrimaryKeySelective(houseCompared);
                if (i > 0) {
                    log.info("删除房源成功");
                    return new ResponseEntity<HouseCompared>(houseCompared, HttpStatus.OK);
                } else {
                    log.info("删除房源失败");
                    return new ResponseEntity<HouseCompared>(houseCompared, HttpStatus.INTERNAL_SERVER_ERROR);
                }

            } catch (Exception e) {
                log.error("服务端异常", e);
                return new ResponseEntity<HouseCompared>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<HouseCompared>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<String> deleteTempCompared(@ApiParam(value = "comparedRequest" ,required=true )  @Valid @RequestBody ComparedRequest comparedRequest) {
        String cookieHouseCompared = getCookieHouseCompared();
        String currHouseId = CookieUtils.getCookie(request, response, cookieHouseCompared);
        if (StringUtil.isNotNullString(currHouseId)) {
            String[] currHouseIdArray = currHouseId.split("_");
            List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
            currHouseIdList = StringUtil.removeAll(currHouseIdList, comparedRequest.getHouseId());
            currHouseId = StringUtil.join(currHouseIdList, "_");
            CookieUtils.setCookie(request, response, cookieHouseCompared, currHouseId);
            return new ResponseEntity<String>(currHouseId,HttpStatus.OK);
        } else {
            currHouseId = "";
            CookieUtils.setCookie(request, response, cookieHouseCompared, currHouseId);
            return new ResponseEntity<String>(currHouseId,HttpStatus.NOT_IMPLEMENTED);
        }
    }

    public ResponseEntity<SellHouseFavoriteListResponse> getComparedList(@ApiParam(value = "sellHouseFavoriteListRequest" ,required=true )  @Valid SellHouseFavoriteListRequest sellHouseFavoriteListRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SellHouseFavoriteListDoQuery sellHouseFavoriteListDoQuery = new SellHouseFavoriteListDoQuery();
                BeanUtils.copyProperties(sellHouseFavoriteListRequest,sellHouseFavoriteListDoQuery);
                SellHouseFavoriteDomain sellHouseFavoriteDomain = comparedService.queryComparedList(sellHouseFavoriteListDoQuery);
                SellHouseFavoriteListResponse sellHouseFavoriteListResponse = new SellHouseFavoriteListResponse();
                BeanUtils.copyProperties(sellHouseFavoriteDomain,sellHouseFavoriteListResponse);
                return new  ResponseEntity<SellHouseFavoriteListResponse>(sellHouseFavoriteListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<SellHouseFavoriteListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SellHouseFavoriteListResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<HouseComparedListDoListResponse> listCompared() {
        String accept = request.getHeader("Accept");
        HouseComparedListDoListResponse houseComparedListDoListResponse = new HouseComparedListDoListResponse();
        if (accept != null && accept.contains("")) {
            try {
                UserBasic userBasic = UserBasic.getCurrent();
                String city = CityUtils.getCity();
                List<HouseCompared> houseComparedList = comparedService.selectByUserId(Integer.parseInt(userBasic.getUserId()),
                        CityUtils.returnCityId(city));
                List<HouseComparedListDo> houseComparedListDoList = comparedService.selectComparedByHouseCompareds(houseComparedList, city);

                houseComparedListDoListResponse.setData(houseComparedListDoList);
                houseComparedListDoListResponse.setTotalNum(houseComparedListDoList.size());
                return new ResponseEntity<HouseComparedListDoListResponse>(houseComparedListDoListResponse,HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<HouseComparedListDoListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<HouseComparedListDoListResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<HouseComparedDetailDoListResponse> listComparedDetail(@NotNull @ApiParam(value = "ids", required = true) @Valid @RequestParam(value = "ids", required = true) String ids) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {

                List<HouseComparedDetailDo> houseComparedDetailDoList = new ArrayList<>();
                HouseComparedDetailDoListResponse houseComparedDetailDoListResponse = new HouseComparedDetailDoListResponse();
                if (StringUtil.isNotNullString(ids)) {
                    String[] currHouseIdArray = ids.split(",");
                    List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
                    if (currHouseIdList.size() > maxComparedCount) {
                        currHouseIdList = currHouseIdList.subList(0, 5);
                    }
                    houseComparedDetailDoList = comparedService.selectComparedDetailByHouseIds(currHouseIdList, CityUtils.getCity());
                    houseComparedDetailDoListResponse.setData(houseComparedDetailDoList);
                    houseComparedDetailDoListResponse.setTotalNum(houseComparedDetailDoList.size());
                }

                return new ResponseEntity<HouseComparedDetailDoListResponse>(houseComparedDetailDoListResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<HouseComparedDetailDoListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<HouseComparedDetailDoListResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<HouseComparedListDoListResponse> listTempCompared() {
        String accept = request.getHeader("Accept");
        HouseComparedListDoListResponse houseComparedListDoListResponse = new HouseComparedListDoListResponse();
        if (accept != null && accept.contains("")) {
            try {
                String cookieHouseCompared = getCookieHouseCompared();
                String city = CityUtils.getCity();
                String currHouseId = CookieUtils.getCookie(request, response, cookieHouseCompared);
                List<HouseComparedListDo> houseComparedListDoList = new ArrayList<>();

                if (StringUtil.isNotNullString(currHouseId)) {
                    String[] currHouseIdArray = currHouseId.split("_");
                    List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
                    houseComparedListDoList = comparedService.selectTempComparedByIds(currHouseIdList, city);

                    houseComparedListDoListResponse.setData(houseComparedListDoList);
                    houseComparedListDoListResponse.setTotalNum(houseComparedListDoList.size());
                    return new ResponseEntity<HouseComparedListDoListResponse>(houseComparedListDoListResponse,HttpStatus.OK);
                } else {
                    currHouseId = "";
                }

            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<HouseComparedListDoListResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<HouseComparedListDoListResponse>(houseComparedListDoListResponse,HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<HouseCompared> saveCompared(@ApiParam(value = "comparedRequest" ,required=true )  @Valid @RequestBody ComparedRequest comparedRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                UserBasic userBasic = UserBasic.getCurrent();
                HouseCompared houseCompared = new HouseCompared();
                houseCompared.setCreateTime(DateTime.now().toDate());
                houseCompared.setHouseId(comparedRequest.getHouseId());
                houseCompared.setHouseStatus((short) 0);
                houseCompared.setIsDel((short) 0);
                houseCompared.setUserId(Integer.parseInt(userBasic.getUserId()));
                houseCompared.setCityId(CityUtils.returnCityId(CityUtils.getCity()));
                if (StringUtil.isNotNullString(houseCompared.getHouseId()) && houseCompared.getUserId() != null) {
                    HouseCompared currHouseCompared = comparedService.selectByUserIdAndHouseId(houseCompared.getUserId(), houseCompared.getHouseId(), houseCompared.getCityId());
                    // 未加入过对比
                    if (currHouseCompared == null) {
                        comparedService.insertSelective(houseCompared);

                        log.info("添加房源对比列表成功");
                        return new ResponseEntity<HouseCompared>(houseCompared,HttpStatus.OK);
                    } else {
                        // 已加入过对比，状态为已删除
                        if (currHouseCompared.getIsDel() == 1) {
                            currHouseCompared.setIsDel((short) 0);
                            currHouseCompared.setCreateTime(DateTime.now().toDate());
                            comparedService.updateByPrimaryKeySelective(currHouseCompared);

                            log.info("添加房源对比列表成功");
                            return new ResponseEntity<HouseCompared>(currHouseCompared,HttpStatus.OK);
                        }
                        // 已加入过对比，状态为未删除
                        else {
                            currHouseCompared.setCreateTime(DateTime.now().toDate());
                            comparedService.updateByPrimaryKeySelective(currHouseCompared);

                            log.info("该房源已加入对比列表");
                            return new ResponseEntity<HouseCompared>(HttpStatus.NO_CONTENT);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<HouseCompared>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<HouseCompared>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<String> saveTempCompared(@ApiParam(value = "comparedRequest" ,required=true )  @Valid @RequestBody ComparedRequest comparedRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                String cookieHouseCompared = getCookieHouseCompared();

                String currHouseId = CookieUtils.getCookie(request, response, cookieHouseCompared);
                if (StringUtil.isNotNullString(currHouseId)) {
                    String[] currHouseIdArray = currHouseId.split("_");
                    List<String> currHouseIdList = Arrays.asList(currHouseIdArray);
                    if (!currHouseIdList.contains(comparedRequest.getHouseId())) {
                        currHouseId = comparedRequest.getHouseId() + "_" + currHouseId;
                    } else {
                        List<String> newCurrHouseIdList = new ArrayList<>();
                        newCurrHouseIdList.add(comparedRequest.getHouseId());
                        for (String houseId : currHouseIdList) {
                            if (!Objects.equals(houseId, comparedRequest.getHouseId())) {
                                newCurrHouseIdList.add(houseId);
                            }
                        }
                        currHouseId = StringUtils.collectionToDelimitedString(newCurrHouseIdList, "_");
                    }
                } else {
                    currHouseId = comparedRequest.getHouseId();
                }
                CookieUtils.setCookie(request, response, cookieHouseCompared, currHouseId);
                return new ResponseEntity<String>(currHouseId, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * cookie中获取对比列表房源id
     *
     * @return
     */
    public String getCookieHouseCompared() {
        String city = CityUtils.getCity();
        String cookieHouseCompared = "";
        if (CityConstant.ABBREVIATION_BEIJING.equals(city)) {
            cookieHouseCompared = CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED; //默认北京
        } else {
            cookieHouseCompared = CookieUtils.COOKIE_NAME_TEMP_HOUSE_COMPARED + "_" + city; //分站
        }
        return cookieHouseCompared;
    }

}
