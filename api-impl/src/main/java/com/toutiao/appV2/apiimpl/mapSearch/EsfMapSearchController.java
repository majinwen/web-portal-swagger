package com.toutiao.appV2.apiimpl.mapSearch;

import com.toutiao.app.domain.mapSearch.*;
import com.toutiao.app.service.mapSearch.EsfMapSearchRestService;
import com.toutiao.appV2.api.mapSearch.EsfMapSearchApi;
import com.toutiao.appV2.model.mapSearch.*;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T09:35:01.637Z")

@Controller
public class EsfMapSearchController implements EsfMapSearchApi {

    private static final Logger log = LoggerFactory.getLogger(EsfMapSearchController.class);
    @Autowired
    private EsfMapSearchRestService esfMapSearchRestService;


    public ResponseEntity<EsfMapSearchResponse> mapEsfSearch(@ApiParam(value = "esfMapSearchRequest" ,required=true )  @Valid EsfMapSearchRequest esfMapSearchRequest) {

        EsfMapSearchDoQuery esfMapSearchDoQuery = new EsfMapSearchDoQuery();
        BeanUtils.copyProperties(esfMapSearchRequest,esfMapSearchDoQuery);

        EsfMapSearchDomain esfMapSearchDomain = esfMapSearchRestService.esfMapSearch(esfMapSearchDoQuery, CityUtils.getCity());
        EsfMapSearchResponse esfMapSearchResponse = new EsfMapSearchResponse();
        BeanUtils.copyProperties(esfMapSearchDomain, esfMapSearchResponse);
        return new ResponseEntity<>(esfMapSearchResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EsfHouseListResponse> mapEsfHouseList(EsfMapSearchRequest esfMapSearchRequest) {

        EsfMapSearchDoQuery esfMapSearchDoQuery = new EsfMapSearchDoQuery();
        BeanUtils.copyProperties(esfMapSearchRequest, esfMapSearchDoQuery);

        EsfHouseListDomain esfHouseListDomain = esfMapSearchRestService.esfMapSearchHouseList(esfMapSearchDoQuery, CityUtils.getCity());
        EsfHouseListResponse esfHouseListResponse = new EsfHouseListResponse();
        BeanUtils.copyProperties(esfHouseListDomain, esfHouseListResponse);
        return new ResponseEntity<>(esfHouseListResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EsfMapSubwayResponse> mapEsfSubwaySearch(EsfMapSearchRequest esfMapSearchRequest) {

        EsfMapSearchDoQuery esfMapSearchDoQuery = new EsfMapSearchDoQuery();
        BeanUtils.copyProperties(esfMapSearchRequest, esfMapSearchDoQuery);

        EsfMapStationDomain esfMapStationDomain = esfMapSearchRestService.esfMapSubwaySearch(esfMapSearchDoQuery, CityUtils.getCity());
        EsfMapSubwayResponse subwayResponse = new EsfMapSubwayResponse();
        BeanUtils.copyProperties(esfMapStationDomain, subwayResponse);
        return new ResponseEntity<>(subwayResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EsfCircleListResponse> mapEsfDrawCircleList(EsfMapSearchRequest esfMapSearchRequest) {

        EsfMapSearchDoQuery esfMapSearchDoQuery = new EsfMapSearchDoQuery();
        BeanUtils.copyProperties(esfMapSearchRequest, esfMapSearchDoQuery);

        EsfCircleListDomain esfCircleListDomain = esfMapSearchRestService.esfMapDrawCircleList(esfMapSearchDoQuery, CityUtils.getCity());
        EsfCircleListResponse esfCircleListResponse = new EsfCircleListResponse();
        BeanUtils.copyProperties(esfCircleListDomain, esfCircleListResponse);
        return new ResponseEntity<>(esfCircleListResponse, HttpStatus.OK);
    }


}
