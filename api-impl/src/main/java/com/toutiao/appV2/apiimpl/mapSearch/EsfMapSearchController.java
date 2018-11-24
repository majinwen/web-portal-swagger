package com.toutiao.appV2.apiimpl.mapSearch;

import com.toutiao.app.domain.mapSearch.EsfMapSearchDoQuery;
import com.toutiao.app.domain.mapSearch.EsfMapSearchDomain;
import com.toutiao.app.service.mapSearch.EsfMapSearchRestService;
import com.toutiao.appV2.api.mapSearch.EsfMapSearchApi;
import com.toutiao.appV2.model.mapSearch.EsfMapSearchRequest;
import com.toutiao.appV2.model.mapSearch.EsfMapSearchResponse;
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


}
