package com.toutiao.appV2.apiimpl.suggest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.suggest.SuggestDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.search.SearchConditionService;
import com.toutiao.app.service.suggest.SuggestService;
import com.toutiao.appV2.api.suggest.SuggestRestApi;
import com.toutiao.appV2.model.agent.AgentRequest;
import com.toutiao.appV2.model.agent.AgentResponse;
import com.toutiao.appV2.model.search.SearchConditionRequest;
import com.toutiao.appV2.model.search.SearchConditionResponse;
import com.toutiao.appV2.model.suggest.SuggestRequest;
import com.toutiao.appV2.model.suggest.SuggestResponse;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.search.SearchCondition;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
    private AgentService agentService;

    @Autowired
    private SearchConditionService searchConditionService;

    public ResponseEntity<SuggestResponse> getSuggestByKeyword(@ApiParam(value = "suggestRequest", required = true) @Valid SuggestRequest suggestRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SuggestResponse suggestResponse = new SuggestResponse();
                SuggestDo suggest = suggestService.suggest(suggestRequest.getKeyword(), suggestRequest.getProperty(), CityUtils.getCity());
                BeanUtils.copyProperties(suggest, suggestResponse);
                return new ResponseEntity<SuggestResponse>(suggestResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<SuggestResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SuggestResponse>(HttpStatus.NOT_IMPLEMENTED);
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


}
