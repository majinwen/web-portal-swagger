package com.toutiao.appV2.apiimpl.mapSearch;

import com.toutiao.app.domain.mapSearch.NewHouseMapSearchBuildDomain;
import com.toutiao.app.domain.mapSearch.NewHouseMapSearchDistrictDomain;
import com.toutiao.app.domain.mapSearch.NewHouseMapSearchDoQuery;
import com.toutiao.app.service.mapSearch.NewHouseMapSearchRestService;
import com.toutiao.appV2.api.mapSearch.NewHouseMapSearchApi;
import com.toutiao.appV2.model.mapSearch.NewHouseMapSearchBuildResponse;
import com.toutiao.appV2.model.mapSearch.NewHouseMapSearchDistrictResponse;
import com.toutiao.appV2.model.mapSearch.NewHouseMapSearchRequest;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T10:59:38.870Z")

@Controller
public class NewHouseMapSearchController implements NewHouseMapSearchApi {

    private static final Logger log = LoggerFactory.getLogger(NewHouseMapSearchController.class);

    @Autowired
    private NewHouseMapSearchRestService newHouseMapSearchRestService;



    @Override
    public ResponseEntity<NewHouseMapSearchDistrictResponse> getNewHouseMapSearchByDistrict(@ApiParam(value = "newHouseMapSearchRequest" ,required=true )  @Valid NewHouseMapSearchRequest newHouseMapSearchRequest) {

        NewHouseMapSearchDoQuery newHouseMapSearchDoQuery = new NewHouseMapSearchDoQuery();
        BeanUtils.copyProperties(newHouseMapSearchRequest,newHouseMapSearchDoQuery);
        NewHouseMapSearchDistrictDomain newHouseMapSearchDistrictDomain = newHouseMapSearchRestService.newHouseMapSearchByDistrict(newHouseMapSearchDoQuery, CityUtils.getCity());
        NewHouseMapSearchDistrictResponse newHouseMapSearchDistrictResponse = new NewHouseMapSearchDistrictResponse();
        BeanUtils.copyProperties(newHouseMapSearchDistrictDomain, newHouseMapSearchDistrictResponse);
        return new ResponseEntity<>(newHouseMapSearchDistrictResponse,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<NewHouseMapSearchDistrictResponse> getNewHouseSubway(@ApiParam(value = "newHouseMapSearchRequest" ,required=true )  @Valid NewHouseMapSearchRequest newHouseMapSearchRequest) {
        NewHouseMapSearchDoQuery newHouseMapSearchDoQuery = new NewHouseMapSearchDoQuery();
        BeanUtils.copyProperties(newHouseMapSearchRequest,newHouseMapSearchDoQuery);
        NewHouseMapSearchDistrictDomain newHouseMapSearchDistrictDomain = newHouseMapSearchRestService.newHouseMapSubwaySearch(newHouseMapSearchDoQuery, CityUtils.getCity());
        NewHouseMapSearchDistrictResponse newHouseMapSearchDistrictResponse = new NewHouseMapSearchDistrictResponse();
        BeanUtils.copyProperties(newHouseMapSearchDistrictDomain, newHouseMapSearchDistrictResponse);
        return new ResponseEntity<>(newHouseMapSearchDistrictResponse,HttpStatus.OK);
    }


    @Override
    public ResponseEntity<NewHouseMapSearchBuildResponse> getNewHouseMapSearchByBuild(@ApiParam(value = "newHouseMapSearchRequest" ,required=true )  @Valid NewHouseMapSearchRequest newHouseMapSearchRequest) {

        NewHouseMapSearchDoQuery newHouseMapSearchDoQuery = new NewHouseMapSearchDoQuery();
        BeanUtils.copyProperties(newHouseMapSearchRequest,newHouseMapSearchDoQuery);
        NewHouseMapSearchBuildDomain newHouseMapSearchBuildDomain = newHouseMapSearchRestService.newHouseMapSearchByBuild(newHouseMapSearchDoQuery, CityUtils.getCity());
        NewHouseMapSearchBuildResponse newHouseMapSearchBuildResponse = new NewHouseMapSearchBuildResponse();
        BeanUtils.copyProperties(newHouseMapSearchBuildDomain, newHouseMapSearchBuildResponse);
        return new ResponseEntity<>(newHouseMapSearchBuildResponse,HttpStatus.OK);

    }






}
