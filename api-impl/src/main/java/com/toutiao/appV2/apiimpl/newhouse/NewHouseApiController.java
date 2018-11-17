package com.toutiao.appV2.apiimpl.newhouse;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.request.newhouse.NewHouseDetailsRequest;
import com.toutiao.app.api.chance.request.newhouse.NewHouseDynamicRequest;
import com.toutiao.app.api.chance.request.newhouse.NewHouseListRequest;
import com.toutiao.app.api.chance.request.newhouse.NewHouseTrafficRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHouseDetailResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDomainResponse;
import com.toutiao.app.api.chance.response.newhouse.NewHouseTrafficResponse;
import com.toutiao.app.domain.newhouse.*;
import com.toutiao.app.service.newhouse.NewHouseRestService;
import com.toutiao.appV2.api.newhouse.NewHouseApi;
import com.toutiao.appV2.model.newhouse.GetNewHouseDynamicResponse;
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

/**
 * @author zym
 */
@Controller
public class NewHouseApiController implements NewHouseApi {

    private static final Logger log = LoggerFactory.getLogger(NewHouseApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private NewHouseRestService newHouseService;

    @Autowired
    public NewHouseApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<NewHouseDetailResponse> getNewHouseDetailByNewCode(@Validated NewHouseDetailsRequest newHouseDetailsRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseDetailDo newHouseDetailDo = newHouseService.getNewHouseBuildByNewCode(newHouseDetailsRequest.getNewCode(), CityUtils.getCity());
                NewHouseDetailResponse newHouseDetailResponse = JSON.parseObject(JSON.toJSONString(newHouseDetailDo), NewHouseDetailResponse.class);
                return new ResponseEntity<>(newHouseDetailResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<GetNewHouseDynamicResponse> getNewHouseDynamicByNewCode(@Validated NewHouseDynamicRequest newHouseDynamicRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseDynamicDoQuery newHouseDynamicDoQuery = new NewHouseDynamicDoQuery();
                BeanUtils.copyProperties(newHouseDynamicRequest, newHouseDynamicDoQuery);
                List<NewHouseDynamicDo> newHouseDynamicDoList = newHouseService.getNewHouseDynamicByNewCode(newHouseDynamicDoQuery, CityUtils.getCity());
                GetNewHouseDynamicResponse response = GetNewHouseDynamicResponse.builder()
                        .data(newHouseDynamicDoList).totalNum(newHouseDynamicDoList.size()).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<NewHouseListDomainResponse> getNewHouseList(NewHouseListRequest newHouseListRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
                NewHouseDoQuery newHouseDoQuery = new NewHouseDoQuery();
                BeanUtils.copyProperties(newHouseListRequest, newHouseDoQuery);
                NewHouseListDomain newHouseListVo = newHouseService.getNewHouseList(newHouseDoQuery, CityUtils.getCity());
                BeanUtils.copyProperties(newHouseListVo, newHouseListDomainResponse);
                return new ResponseEntity<>(newHouseListDomainResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<NewHouseTrafficResponse> getNewHouseTraffic(NewHouseTrafficRequest newHouseTrafficRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseTrafficResponse newHouseTrafficResponse = new NewHouseTrafficResponse();
                NewHouseTrafficDo newHouseTrafficDo = newHouseService.getNewHouseTrafficByNewCode(newHouseTrafficRequest.getNewCode(), CityUtils.getCity());
                BeanUtils.copyProperties(newHouseTrafficDo, newHouseTrafficResponse);
                return new ResponseEntity<>(newHouseTrafficResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
