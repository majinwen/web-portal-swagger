package com.toutiao.appV2.apiimpl.rent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.rent.*;
import com.toutiao.app.domain.rent.RentDetailsFewDo;
import com.toutiao.app.domain.rent.RentDetailsListDo;
import com.toutiao.app.service.rent.RentRestService;
import com.toutiao.appV2.api.rent.RentRestApi;
import com.toutiao.appV2.model.rent.*;
import com.toutiao.web.common.assertUtils.Second;
import com.toutiao.web.common.util.city.CityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//import com.toutiao.app.api.chance.response.rent.*;

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
    public ResponseEntity<RentDetailFewResponseList> getNearRentHouseByLocation(@Validated NearHouseListRequest nearHouseListRequest) {

        NearHouseDo nearHouseDo = new NearHouseDo();
        BeanUtils.copyProperties(nearHouseListRequest, nearHouseDo);
        List<RentDetailsFewDo> list = appRentRestService.queryNearHouseByLocation(nearHouseDo);
        JSONArray objects = JSONArray.parseArray(JSON.toJSONString(list));
        List<RentDetailFewResponse> rentDetailFewResponses = JSONObject.parseArray(objects.toJSONString(), RentDetailFewResponse.class);
        RentDetailFewResponseList nearRentHouseResponse = new RentDetailFewResponseList();
        nearRentHouseResponse.setRentDetailsList(rentDetailFewResponses);
        nearRentHouseResponse.setTotalCount(rentDetailFewResponses.size());
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
    public ResponseEntity<RentDetailResponse> getRentDetailByRentId(@Validated RentDetailsRequest rentDetailsRequest) {

        RentDetailsDo rentDetailsDo = appRentRestService.queryRentDetailByHouseId(rentDetailsRequest.getRentId(), CityUtils.getCity());
        RentDetailResponse rentDetailResponse = new RentDetailResponse();
        BeanUtils.copyProperties(rentDetailsDo, rentDetailResponse);
        return new ResponseEntity<>(rentDetailResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RentDetailFewResponseList> getRentHouseSearchListGet(@Validated RentHouseRequest rentHouseRequest) {
        return getRentDetailFewResponseListResponseEntity(rentHouseRequest);
    }

    private ResponseEntity<RentDetailFewResponseList> getRentDetailFewResponseListResponseEntity(@Validated RentHouseRequest rentHouseRequest) {
        if (rentHouseRequest.getSearchType() == 1) {
            RentHouseDoQuery rentHouseDoQuery = new RentHouseDoQuery();
            BeanUtils.copyProperties(rentHouseRequest, rentHouseDoQuery);
            RentDetailsListDo rentDetailsListDo = appRentRestService.getRentList(rentHouseDoQuery, CityUtils.getCity());
            JSONObject jsonObject = (JSONObject) JSON.toJSON(rentDetailsListDo);
            RentDetailFewResponseList rentListResponse = JSONObject.parseObject(String.valueOf(jsonObject), RentDetailFewResponseList.class);
            return new ResponseEntity<>(rentListResponse, HttpStatus.OK);
        } else if (rentHouseRequest.getSearchType() == 2) {
            NearHouseDo nearHouseDo = new NearHouseDo();
            BeanUtils.copyProperties(rentHouseRequest, nearHouseDo);
            List<RentDetailsFewDo> list = appRentRestService.queryNearHouseByLocation(nearHouseDo);
            JSONArray objects = JSONArray.parseArray(JSON.toJSONString(list));
            List<RentDetailFewResponse> rentDetailFewResponses = JSONObject.parseArray(objects.toJSONString(), RentDetailFewResponse.class);
            RentDetailFewResponseList nearRentHouseResponse = new RentDetailFewResponseList();
            nearRentHouseResponse.setRentDetailsList(rentDetailFewResponses);
            if (list.size() > 0) {
                nearRentHouseResponse.setTotalCount(list.get(0).getTotalNum());
            } else {
                nearRentHouseResponse.setTotalCount(0);
            }

            return new ResponseEntity<>(nearRentHouseResponse, HttpStatus.OK);
        } else {
            RentHouseDoQuery rentHouseDoQuery = new RentHouseDoQuery();
            RentDetailFewResponseList rentDetailFewResponseList = new RentDetailFewResponseList();
            BeanUtils.copyProperties(rentHouseRequest, rentHouseDoQuery);
            RentDetailsListDo rentDetailsListDo = appRentRestService.getRentHouseSearchList(rentHouseDoQuery, CityUtils.getCity());
            if (rentDetailsListDo.getRentDetailsList().size() > 0) {
                rentDetailFewResponseList.setIsGuess(0);
            } else {
                //没有根据结果查询到数据,返回猜你喜欢的数据
                rentHouseDoQuery = new RentHouseDoQuery();
                rentDetailsListDo = appRentRestService.getRentHouseSearchList(rentHouseDoQuery, CityUtils.getCity());
                rentDetailFewResponseList.setIsGuess(1);
            }
            BeanUtils.copyProperties(rentDetailsListDo, rentDetailFewResponseList);
            return new ResponseEntity<>(rentDetailFewResponseList, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<RentDetailFewResponseList> getRentHouseSearchListPost(@Validated @RequestBody RentHouseRequest rentHouseRequest) {
        return getRentDetailFewResponseListResponseEntity(rentHouseRequest);
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

    @Override
    public ResponseEntity<RentDetailFewResponseList> getCommuteRentList(@Validated(Second.class) @RequestBody RentHouseRequest rentHouseRequest) {
        RentHouseDoQuery rentHouseDoQuery = new RentHouseDoQuery();
        BeanUtils.copyProperties(rentHouseRequest, rentHouseDoQuery);
        RentDetailsListDo rentDetailsListDo = appRentRestService.getCommuteRentHouseSearchList(rentHouseDoQuery, CityUtils.getCity());
        RentDetailFewResponseList rentDetailFewResponseList = new RentDetailFewResponseList();
        BeanUtils.copyProperties(rentDetailsListDo, rentDetailFewResponseList);
        return new ResponseEntity<RentDetailFewResponseList>(rentDetailFewResponseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RentDetailFewResponseList> getGuessList(RentHouseRequest rentHouseRequest) {
        RentHouseDoQuery rentHouseDoQuery = new RentHouseDoQuery();
        BeanUtils.copyProperties(rentHouseRequest, rentHouseDoQuery);
        RentDetailsListDo rentDetailsListDo = appRentRestService.getRentHouseSearchList(rentHouseDoQuery, CityUtils.getCity());
        RentDetailFewResponseList rentDetailFewResponseList = new RentDetailFewResponseList();
        BeanUtils.copyProperties(rentDetailsListDo, rentDetailFewResponseList);
        return new ResponseEntity<>(rentDetailFewResponseList, HttpStatus.OK);
    }

}
