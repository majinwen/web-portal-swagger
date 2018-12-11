package com.toutiao.appV2.apiimpl.rent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.appV2.api.rent.UserFavoriteRentApi;
import com.toutiao.appV2.model.rent.UserFavoriteRentListResponse;
import com.toutiao.appV2.model.sellhouse.UserFavoriteConditionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CuiShihao on 2018/12/11
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

@Controller
public class UserFavoriteRentListController implements UserFavoriteRentApi {

    private static final Logger log = LoggerFactory.getLogger(UserFavoriteRentListController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UserFavoriteRentListController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    @Override
    public ResponseEntity<UserFavoriteRentListResponse> getNearRentHouseByLocation(UserFavoriteConditionRequest userFavoriteConditionRequest) {
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest, userFavoriteConditionDoQuery);

        return null;
    }
}
