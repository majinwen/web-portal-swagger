package com.toutiao.appV2.apiimpl.Intelligence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDo;
import com.toutiao.app.domain.newhouse.UserFavoriteConditionDoQuery;
import com.toutiao.app.service.homepage.HomePageRestService;
import com.toutiao.appV2.api.Intelligence.ConditionApi;
import com.toutiao.appV2.model.Intelligence.UserFavoriteConditionRequest;
import com.toutiao.appV2.model.Intelligence.UserFavoriteConditionResponse;
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
    public ResponseEntity<Integer> deleteRecommendCondition(@ApiParam(value = "用户id" ,required=true )  @Valid @RequestParam(value = "用户id" ,required=true ) Integer userId) {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        Integer city = CityUtils.returnCityId(CityUtils.getCity());
                        Integer integer = homePageRestService.deleteRecommendCondition(userId, city);
                        if (integer==1){
                            return new ResponseEntity<Integer>(integer, HttpStatus.OK);
                        }else {
                            return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<Integer>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<UserFavoriteConditionResponse> getRecommendCondition(@ApiParam(value = "用户id" ,required=true )  @Valid @RequestParam(value = "用户id" ,required=true ) Integer userId) {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        UserFavoriteConditionResponse userFavoriteConditionResponse = new UserFavoriteConditionResponse();
                        UserFavoriteConditionDo recommendCondition = homePageRestService.getRecommendCondition(userId, CityUtils.getCity());
                        BeanUtils.copyProperties(recommendCondition,userFavoriteConditionResponse);
                        return new ResponseEntity<UserFavoriteConditionResponse>(userFavoriteConditionResponse, HttpStatus.OK);
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<UserFavoriteConditionResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<UserFavoriteConditionResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<Integer> saveRecommendCondition(@ApiParam(value = "推荐条件" ,required=true )  @Valid @RequestBody UserFavoriteConditionRequest userFavoriteConditionRequest) {
                String accept = request.getHeader("Accept");
                if (accept != null && accept.contains("application/json")) {
                    try {
                        UserFavoriteConditionDoQuery userFavoriteConditionDoQuery = new UserFavoriteConditionDoQuery();
                        BeanUtils.copyProperties(userFavoriteConditionRequest,userFavoriteConditionDoQuery);
                        Integer integer = homePageRestService.saveRecommendCondition(userFavoriteConditionDoQuery, CityUtils.getCity());
                        if (integer==1){
                            return new ResponseEntity<Integer>(integer, HttpStatus.OK);
                        }else {
                            return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                }

                return new ResponseEntity<Integer>(HttpStatus.NOT_IMPLEMENTED);
    }

}
