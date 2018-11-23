package com.toutiao.appV2.apiimpl.mapSearch;

import com.toutiao.appV2.api.mapSearch.EsfMapSearchApi;
import com.toutiao.appV2.model.mapSearch.EsfMapSearchDistrictResponse;
import com.toutiao.appV2.model.mapSearch.EsfMapSearchDoRequest;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-23T06:53:25.085Z")

@Controller
public class EsfMapSearchApiController implements EsfMapSearchApi {

    private static final Logger log = LoggerFactory.getLogger(EsfMapSearchApiController.class);




    public ResponseEntity<Object> mapEsfSearch(@ApiParam(value = "esfMapSearchDoRequest" ,required=true )  @Valid EsfMapSearchDoRequest esfMapSearchDoRequest) {

        EsfMapSearchDistrictResponse esfMapSearchDistrictResponse = new EsfMapSearchDistrictResponse();




        return new ResponseEntity<>(esfMapSearchDistrictResponse, HttpStatus.OK);

    }
}
