package com.toutiao.appV2.apiimpl.suggest;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.response.suggest.SuggestResultResponse;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.suggest.SuggestResultDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.search.SearchConditionService;
import com.toutiao.app.service.subscribe.CityService;
import com.toutiao.app.service.suggest.SuggestService;
import com.toutiao.appV2.api.suggest.SuggestRestApi;
import com.toutiao.appV2.model.agent.AgentRequest;
import com.toutiao.appV2.model.agent.AgentResponse;
import com.toutiao.appV2.model.search.SearchConditionRequest;
import com.toutiao.appV2.model.search.SearchConditionResponse;
import com.toutiao.appV2.model.subscribe.CityAllInfoMap;
import com.toutiao.appV2.model.subscribe.CityConditionDoList;
import com.toutiao.appV2.model.subscribe.WapCityList;
import com.toutiao.appV2.model.suggest.SuggestRequest;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.search.SearchCondition;
import com.toutiao.web.dao.entity.subscribe.City;
import com.toutiao.web.dao.entity.subscribe.WapCity;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:37:50.619Z")

@RestController
@Slf4j
public class SuggestRestController implements SuggestRestApi {

    private static final Logger log = LoggerFactory.getLogger(SuggestRestController.class);

    private final HttpServletRequest request;

    @Autowired
    public SuggestRestController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    private SuggestService suggestService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private SearchConditionService searchConditionService;

    @Override
    public ResponseEntity<SuggestResultResponse> getSuggestByKeyword(@ApiParam(value = "suggestRequest", required = true) @Valid SuggestRequest suggestRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SuggestResultDo suggestResultDo = suggestService.suggest_v2(suggestRequest.getKeyword(), suggestRequest.getProperty(), CityUtils.getCity());
                SuggestResultResponse suggestResultResponse = JSON.parseObject(JSON.toJSONString(suggestResultDo), SuggestResultResponse.class);
                return new ResponseEntity<SuggestResultResponse>( suggestResultResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<SuggestResultResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SuggestResultResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<AgentResponse> getAgentInfoByUserId(@ApiParam(value = "agentRequest", required = true) @Valid AgentRequest agentRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                AgentResponse agentResponse = new AgentResponse();
                AgentBaseDo agentBaseDo = agentService.queryAgentInfoByUserId(agentRequest.getUserId(), CityUtils.getCity());
                BeanUtils.copyProperties(agentBaseDo, agentResponse);
                return new ResponseEntity<AgentResponse>(agentResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<AgentResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<AgentResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<SearchConditionResponse> selectSearchConditionByCityIdAndType(SearchConditionRequest searchConditionRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                SearchConditionResponse SearchConditionResponse = new SearchConditionResponse();
                int cityId = searchConditionRequest.getCityId();
                int type = searchConditionRequest.getType();
                SearchCondition searchCondition = searchConditionService.selectSearchConditionByCityIdAndType(cityId, type);
                BeanUtils.copyProperties(searchCondition, SearchConditionResponse);
                return new ResponseEntity<SearchConditionResponse>(SearchConditionResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<CityAllInfoMap> getCityAllInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId", required = false, defaultValue = "0") Integer cityId, @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain", required = false, defaultValue = "") String cityDomain) {
        if (cityId == 0 && Objects.equals(cityDomain, "")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cityId == 0) {
            City city = cityService.selectCityByDomain(cityDomain);
            if (city != null) {
                cityId = city.getCityId();
            }
        }
        CityAllInfoMap cityAllInfoMap = new CityAllInfoMap();
        Map<String, Object> res = cityService.getCityAllInfo(cityId);
        cityAllInfoMap.setCityAllInfos(res);
        return new ResponseEntity<CityAllInfoMap>(cityAllInfoMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WapCityList> getWapCity() {
        WapCityList wapCityList = new WapCityList();
        List<WapCity> wapCities = cityService.selectWapCity();
        wapCityList.setWapCityList(wapCities);
        wapCityList.setTotal(wapCities.size());
        return new ResponseEntity<WapCityList>(wapCityList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityConditionDoList> getCityconditionByIdAndType(@ApiParam(value = "cityId", required = true) @Valid @RequestParam(value = "cityId", required = true) Integer cityId, @ApiParam(value = "type", required = true) @Valid @RequestParam(value = "type", required = true) String type) {
        CityConditionDoList cityConditionDoList = new CityConditionDoList();
        cityConditionDoList.setCityConditionDos(cityService.getCityConditionByIdAndType(cityId, type));
        return new ResponseEntity<CityConditionDoList>(cityConditionDoList, HttpStatus.OK);
    }

}
