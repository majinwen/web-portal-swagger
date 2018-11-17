package com.toutiao.appV2.apiimpl.rent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.response.rent.RentDetailFewResponse;
import com.toutiao.app.api.chance.response.rent.RentDetailsListResponse;
import com.toutiao.app.domain.rent.NearHouseDo;
import com.toutiao.app.domain.rent.NearHouseListDoQuery;
import com.toutiao.app.domain.rent.RentDetailsFewDo;
import com.toutiao.app.domain.rent.RentDetailsListDo;
import com.toutiao.app.service.rent.NearRentHouseRestService;
import com.toutiao.appV2.api.rent.NearRentHouseApi;
import com.toutiao.appV2.model.rent.NearHouseListRequest;
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

/**
 * Created by CuiShihao on 2018/11/16
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

@Controller
public class NearRentHouseRestController implements NearRentHouseApi {

    @Autowired
    private NearRentHouseRestService nearRentHouseRestService;

    private static final Logger log = LoggerFactory.getLogger(NearRentHouseRestController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public NearRentHouseRestController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    @Override
    public ResponseEntity<RentDetailsListDo> getNearRentHouseByLocation(@Validated NearHouseListRequest nearHouseListRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NearHouseListDoQuery nearHouseListDoQuery = new NearHouseListDoQuery();
                RentDetailsListResponse rentDetailsListResponse = new RentDetailsListResponse();
                BeanUtils.copyProperties(nearHouseListRequest,nearHouseListDoQuery);
                RentDetailsListDo rentDetailsListDo = nearRentHouseRestService.queryNearHouseByLocation(nearHouseListDoQuery, CityUtils.getCity());
                BeanUtils.copyProperties(rentDetailsListDo,rentDetailsListResponse);
                return new ResponseEntity<>(rentDetailsListDo, HttpStatus.OK);
            } catch (Exception e) {
                log.error("服务端错误", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
