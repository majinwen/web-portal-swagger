package com.toutiao.appV2.apiimpl.suggest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.suggest.SuggestDo;
import com.toutiao.app.service.suggest.SuggestService;
import com.toutiao.appV2.api.suggest.SuggestRestApi;
import com.toutiao.appV2.model.suggest.SuggestRequest;
import com.toutiao.appV2.model.suggest.SuggestResponse;
import com.toutiao.web.common.util.city.CityUtils;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:37:50.619Z")

@RestController
public class SuggestRestController implements SuggestRestApi {

    private static final Logger log = LoggerFactory.getLogger(SuggestRestController.class);

    private final HttpServletRequest request;

    @Autowired
    public SuggestRestController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    private SuggestService suggestService;

    @Override
    public ResponseEntity<SuggestResponse> getSuggestByKeyword(@ApiParam(value = "suggestRequest", required = true) @Valid SuggestRequest suggestRequest) {
        SuggestResponse suggestResponse = new SuggestResponse();
        SuggestDo suggest = suggestService.suggest(suggestRequest.getKeyword(), suggestRequest.getProperty(), CityUtils.getCity());
        BeanUtils.copyProperties(suggest, suggestResponse);
        return new ResponseEntity<SuggestResponse>(suggestResponse, HttpStatus.OK);
    }

}
