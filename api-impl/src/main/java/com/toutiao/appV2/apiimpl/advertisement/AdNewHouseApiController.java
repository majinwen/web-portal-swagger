/*
package com.toutiao.appV2.apiimpl.advertisement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.api.chance.request.advertisement.AdNewHouse;
import com.toutiao.app.api.chance.response.homepage.HomePageNewHouseResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDomainResponse;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.service.advertisement.AdNewHouseRestService;
import com.toutiao.appV2.api.advertisement.AdNewHouseApi;
import com.toutiao.appV2.model.newhouse.AdRecommendNewRespose;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.util.city.CityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

*/
/**
 * @author : zym
 * @date : 2018/11/16 18:52
 * @desc :
 *//*

@Controller
public class AdNewHouseApiController implements AdNewHouseApi {

    private static final Logger log = LoggerFactory.getLogger(AdNewHouseApiController.class);

    private final HttpServletRequest request;

    @Autowired
    private AdNewHouseRestService adNewHouseRestService;

    @Autowired
    public AdNewHouseApiController(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<NewHouseListDomainResponse> getAdNewHouseListByIds(@Validated(First.class) AdNewHouse adNewHouse) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
                NewHouseListDomain newHouseListDomain = adNewHouseRestService.getAdNewHouseList(adNewHouse.getNewHouseIds(), CityUtils.getCity());
                BeanUtils.copyProperties(newHouseListDomain, newHouseListDomainResponse);
                return new ResponseEntity<>(newHouseListDomainResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<AdRecommendNewRespose> getAdRecommendNewHouseByIds(AdNewHouse adNewHouse) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseListDomain adNewHouseHomePage = adNewHouseRestService.getAdNewHouseHomePage(adNewHouse.getNewHouseIds(), CityUtils.getCity());
                JSONArray json = JSONArray.parseArray(JSON.toJSONString(adNewHouseHomePage.getData()));
                List<HomePageNewHouseResponse> newHouseListResponses = JSONObject.parseArray(json.toJSONString(), HomePageNewHouseResponse.class);
                AdRecommendNewRespose response = AdRecommendNewRespose.builder().data(newHouseListResponses)
                        .totalNum(newHouseListResponses.size()).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
*/
