package com.toutiao.appV2.apiimpl.mapSearch;

import com.toutiao.app.domain.mapSearch.EsfMapSearchDoQuery;
import com.toutiao.app.service.mapSearch.EsfMapSearchRestService;
import com.toutiao.appV2.api.mapSearch.EsfMapSearchApi;
import com.toutiao.appV2.model.mapSearch.EsfMapSearchDoRequest;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-23T06:53:25.085Z")

@Controller
public class EsfMapSearchApiController implements EsfMapSearchApi {

    private static final Logger log = LoggerFactory.getLogger(EsfMapSearchApiController.class);

    @Autowired
    private EsfMapSearchRestService esfMapSearchRestService;



    @Override
    public ResponseEntity<Object> mapEsfSearch(@ApiParam(value = "esfMapSearchDoRequest" ,required=true )  @Valid EsfMapSearchDoRequest esfMapSearchDoRequest) {


        EsfMapSearchDoQuery esfMapSearchDoQuery = new EsfMapSearchDoQuery();
        BeanUtils.copyProperties(esfMapSearchDoRequest,esfMapSearchDoQuery);

        Object o = esfMapSearchRestService.esfMapSearch(esfMapSearchDoQuery, CityUtils.getCity());


        return new ResponseEntity<>(o, HttpStatus.OK);

    }
}
