package com.toutiao.appV2.apiimpl.rent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.response.rent.*;
import com.toutiao.app.domain.rent.*;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.appV2.api.rent.RentRestApi;
import com.toutiao.appV2.model.rent.NearHouseListRequest;
import com.toutiao.appV2.model.rent.NearRentHouseResponse;
import com.toutiao.appV2.model.rent.RentDetailsRequest;
import com.toutiao.appV2.model.rent.RentHouseRequest;
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
 * Created by CuiShihao on 2018/11/16
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

@Controller
public class RentRestController implements RentRestApi {

    @Autowired
    private RentRestService appRentRestService;

    private static final Logger log = LoggerFactory.getLogger(RentRestController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RentRestController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<NearRentHouseResponse> getNearRentHouseByLocationUsingGET(@Validated NearHouseListRequest nearHouseListRequest) {

        NearHouseDo nearHouseDo = new NearHouseDo();
        BeanUtils.copyProperties(nearHouseListRequest, nearHouseDo);
        List<RentDetailsFewDo> list = appRentRestService.queryNearHouseByLocation(nearHouseDo);
        JSONArray objects = JSONArray.parseArray(JSON.toJSONString(list));
        List<RentDetailFewResponse> rentDetailFewResponses = JSONObject.parseArray(objects.toJSONString(), RentDetailFewResponse.class);
        NearRentHouseResponse nearRentHouseResponse = new NearRentHouseResponse();
        nearRentHouseResponse.setData(rentDetailFewResponses);
        nearRentHouseResponse.setTotalNum(rentDetailFewResponses.size());
        return new ResponseEntity<>(nearRentHouseResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RecommendRentResponse> getRecommendRent(@Validated RentHouseRequest rentHouseRequest) {

        RentHouseDoQuery rentHouseDoQuery = new RentHouseDoQuery();
        BeanUtils.copyProperties(rentHouseRequest, rentHouseDoQuery);
        RentDetailsFewDo rentDetailsFewDo = appRentRestService.queryRecommendRent(rentHouseDoQuery, CityUtils.getCity());
        RecommendRentResponse recommendRentResponse = new RecommendRentResponse();
        BeanUtils.copyProperties(rentDetailsFewDo, recommendRentResponse);
        return new ResponseEntity<>(recommendRentResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RentAgentResponse> getRentAgentByRentId(@Validated RentDetailsRequest rentDetailsRequest) {

        RentAgentDo rentAgentDo = appRentRestService.queryRentAgentByRentId(rentDetailsRequest.getRentId());
        RentAgentResponse rentAgentResponse = new RentAgentResponse();
        BeanUtils.copyProperties(rentAgentDo, rentAgentResponse);
        return new ResponseEntity<>(rentAgentResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RentDetailResponse> getRentDetailByRentId(@Validated RentDetailsRequest rentDetailsRequest) {

        RentDetailsDo rentDetailsDo = appRentRestService.queryRentDetailByHouseId(rentDetailsRequest.getRentId(), CityUtils.getCity());
        RentDetailResponse rentDetailResponse = new RentDetailResponse();
        BeanUtils.copyProperties(rentDetailsDo, rentDetailResponse);
        return new ResponseEntity<>(rentDetailResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RentDetailFewResponseList> getRentHouseSearchList(@Validated RentHouseRequest rentHouseRequest) {

        RentHouseDoQuery rentHouseDoQuery = new RentHouseDoQuery();
        BeanUtils.copyProperties(rentHouseRequest, rentHouseDoQuery);
        RentDetailsListDo rentDetailsListDo = appRentRestService.getRentHouseSearchList(rentHouseDoQuery, CityUtils.getCity());
        RentDetailFewResponseList rentDetailFewResponseList = new RentDetailFewResponseList();
        BeanUtils.copyProperties(rentDetailsListDo, rentDetailFewResponseList);
        return new ResponseEntity<>(rentDetailFewResponseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RentListResponse> getRentList(@Validated RentHouseRequest rentHouseRequest) {
        RentHouseDoQuery rentHouseDoQuery = new RentHouseDoQuery();
        BeanUtils.copyProperties(rentHouseRequest, rentHouseDoQuery);
        RentDetailsListDo rentDetailsListDo = appRentRestService.getRentList(rentHouseDoQuery, CityUtils.getCity());
        JSONObject jsonObject = (JSONObject) JSON.toJSON(rentDetailsListDo);
        RentListResponse rentListResponse = JSONObject.parseObject(String.valueOf(jsonObject), RentListResponse.class);
        return new ResponseEntity<>(rentListResponse, HttpStatus.OK);
    }

}
