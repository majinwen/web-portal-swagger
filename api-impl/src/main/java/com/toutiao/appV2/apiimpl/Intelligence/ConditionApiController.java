package com.toutiao.appV2.apiimpl.Intelligence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.appV2.api.Intelligence.ConditionApi;
import com.toutiao.appV2.model.Intelligence.UserFavoriteConditionRequest;
import com.toutiao.appV2.model.Intelligence.UserFavoriteConditionResponse;
import com.toutiao.appV2.model.StringDataResponse;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:50:01.627Z")

@Controller
public class ConditionApiController implements ConditionApi {

    private static final Logger log = LoggerFactory.getLogger(ConditionApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private HomePageRestService homePageRestService;

    @org.springframework.beans.factory.annotation.Autowired
    public ConditionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<StringDataResponse> deleteRecommendCondition(@ApiParam(value = "用户id", required = true) @Valid @RequestParam(value = "用户id", required = true) Integer userId) {
        Integer city = CityUtils.returnCityId(CityUtils.getCity());
        Integer integer = homePageRestService.deleteRecommendCondition(userId, city);
        StringDataResponse stringDataResponse = new StringDataResponse();
        stringDataResponse.setData("删除推荐条件成功");
        return new ResponseEntity<StringDataResponse>(stringDataResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserFavoriteConditionResponse> getRecommendCondition(@ApiParam(value = "用户id", required = true) @Valid @RequestParam(value = "用户id", required = true) Integer userId) {
        UserFavoriteConditionResponse userFavoriteConditionResponse = new UserFavoriteConditionResponse();
        UserFavoriteConditionDo recommendCondition = homePageRestService.getRecommendCondition(userId, CityUtils.getCity());
        BeanUtils.copyProperties(recommendCondition, userFavoriteConditionResponse);
        return new ResponseEntity<UserFavoriteConditionResponse>(userFavoriteConditionResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StringDataResponse> saveRecommendCondition(@ApiParam(value = "推荐条件", required = true) @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest) {
        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
        BeanUtils.copyProperties(userFavoriteConditionRequest, userFavoriteConditionDoQuery);
        Integer integer = homePageRestService.saveRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
        StringDataResponse stringDataResponse = new StringDataResponse();
        stringDataResponse.setData("保存推荐条件成功");
        return new ResponseEntity<StringDataResponse>(stringDataResponse, HttpStatus.OK);
    }

}
