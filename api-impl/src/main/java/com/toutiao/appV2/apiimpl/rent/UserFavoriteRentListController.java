package com.toutiao.appV2.apiimpl.rent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.rent.UserFavoriteRentListDoQuery;
import com.toutiao.app.domain.rent.UserFavoriteRentListDomain;
import com.toutiao.app.service.rent.UserFavoriteRentService;
import com.toutiao.appV2.api.rent.UserFavoriteRentApi;
import com.toutiao.appV2.model.rent.UserFavoriteRentListRequest;
import com.toutiao.appV2.model.rent.UserFavoriteRentListResponse;
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
 * Created by CuiShihao on 2018/12/11
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T06:17:44.199Z")

@Controller
public class UserFavoriteRentListController implements UserFavoriteRentApi {

    @Autowired
    private UserFavoriteRentService userFavoriteRentService;

    private static final Logger log = LoggerFactory.getLogger(UserFavoriteRentListController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UserFavoriteRentListController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }
    @Override
    public ResponseEntity<UserFavoriteRentListResponse> getRentHouseListByUserFavorite(@Validated UserFavoriteRentListRequest userFavoriteRentListRequest) {
        UserFavoriteRentListDoQuery userFavoriteRentListDoQuery = new UserFavoriteRentListDoQuery();
        BeanUtils.copyProperties(userFavoriteRentListRequest, userFavoriteRentListDoQuery);

        UserFavoriteRentListDomain userFavoriteRentListDomain = userFavoriteRentService.queryRentListByUserFavorite(userFavoriteRentListDoQuery, CityUtils.getCity());

        UserFavoriteRentListResponse userFavoriteRentListResponse = new UserFavoriteRentListResponse();
        BeanUtils.copyProperties(userFavoriteRentListDomain, userFavoriteRentListResponse);

        return new ResponseEntity<>(userFavoriteRentListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserFavoriteRentListResponse> getHouseCountBySubway(@Validated UserFavoriteRentListRequest userFavoriteRentListRequest) {
        UserFavoriteRentListDoQuery userFavoriteRentListDoQuery = new UserFavoriteRentListDoQuery();
        BeanUtils.copyProperties(userFavoriteRentListRequest, userFavoriteRentListDoQuery);
        return null;
    }
}
