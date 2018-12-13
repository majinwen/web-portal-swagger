package com.toutiao.appV2.apiimpl.subway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.service.subway.SubwayLineService;
import com.toutiao.appV2.api.subway.SubwayInfoApi;
import com.toutiao.appV2.apiimpl.subscribe.ConditionSubscribeSuscribeController;
import com.toutiao.appV2.model.subway.SubwayLineResponse;
import com.toutiao.web.dao.entity.subway.SubwayLineDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CuiShihao on 2018/12/13
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-15T07:27:32.320Z")
@Controller
public class SubwayInfoController implements SubwayInfoApi {

    private static final Logger log = LoggerFactory.getLogger(SubwayInfoController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public SubwayInfoController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    @Autowired
    private SubwayLineService subwayLineService;
    @Override
    public ResponseEntity<SubwayLineResponse> getRentHouseListByUserFavorite(Integer lineId) {
        SubwayLineDo subwayLineDo = subwayLineService.selectLineInfoByLineId(lineId);
        SubwayLineResponse subwayLineResponse = new SubwayLineResponse();
        BeanUtils.copyProperties(subwayLineDo, subwayLineResponse);
        return new ResponseEntity<>(subwayLineResponse, HttpStatus.OK);
    }
}
