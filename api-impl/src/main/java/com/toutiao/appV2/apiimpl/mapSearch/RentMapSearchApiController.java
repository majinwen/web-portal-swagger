package com.toutiao.appV2.apiimpl.mapSearch;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.response.mapSearch.RentOfPlotListResPonse;
import com.toutiao.app.domain.mapSearch.RentMapSearchDoQuery;
import com.toutiao.app.domain.mapSearch.RentMapSearchDomain;
import com.toutiao.app.domain.mapSearch.RentOfPlotListDo;
import com.toutiao.app.service.mapSearch.RentMapSearchRestService;
import com.toutiao.appV2.api.mapSearch.RentMapSearchApi;
import com.toutiao.appV2.model.mapSearch.RentMapSearchDoRequest;
import com.toutiao.appV2.model.mapSearch.RentMapSearchDomainResponse;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-24T10:54:50.943Z")

@Controller
public class RentMapSearchApiController implements RentMapSearchApi {

    private static final Logger log = LoggerFactory.getLogger(RentMapSearchApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RentMapSearchApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private RentMapSearchRestService rentMapSearchRestService;

    @Override
    public ResponseEntity<RentMapSearchDomainResponse> mapRentSearch(@ApiParam(value = "rentMapSearchDoRequest" ,required=true )  @Valid @RequestBody RentMapSearchDoRequest rentMapSearchDoRequest) {
        RentMapSearchDoQuery rentMapSearchDoQuery = new RentMapSearchDoQuery();
        BeanUtils.copyProperties(rentMapSearchDoRequest,rentMapSearchDoQuery);
        RentMapSearchDomain rentMapSearchDomain = rentMapSearchRestService.rentMapSearch(rentMapSearchDoQuery, CityUtils.getCity());
        RentMapSearchDomainResponse rentMapSearchDomainResponse = JSON.parseObject(JSON.toJSONString(rentMapSearchDomain), RentMapSearchDomainResponse.class);
        return new ResponseEntity<RentMapSearchDomainResponse>(rentMapSearchDomainResponse, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<RentOfPlotListResPonse> getRentOfPlot(@ApiParam(value = "rentMapSearchDoRequest" ,required=true )  @Valid  RentMapSearchDoRequest rentMapSearchDoRequest){
        RentMapSearchDoQuery rentMapSearchDoQuery = JSON.parseObject(JSON.toJSONString(rentMapSearchDoRequest), RentMapSearchDoQuery.class);
        RentOfPlotListDo rentOfPlot = rentMapSearchRestService.getRentOfPlot(rentMapSearchDoQuery, CityUtils.getCity());
        RentOfPlotListResPonse rentOfPlotListResPonse = JSON.parseObject(JSON.toJSONString(rentOfPlot), RentOfPlotListResPonse.class);
        return new ResponseEntity<RentOfPlotListResPonse>(rentOfPlotListResPonse, HttpStatus.OK);
    }

}
