package com.toutiao.appV2.api.suggest;

import com.toutiao.appV2.model.agent.AgentRequest;
import com.toutiao.appV2.model.agent.AgentResponse;
import com.toutiao.appV2.model.search.SearchConditionRequest;
import com.toutiao.appV2.model.search.SearchConditionResponse;
import com.toutiao.appV2.model.suggest.SuggestRequest;
import com.toutiao.appV2.model.suggest.SuggestResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:37:50.619Z")

@Api(value = "搜索联想词", description = "搜索联想词")
public interface SuggestRestApi {

    @ApiOperation(value = "搜索联想词提示", nickname = "getSuggestByKeyword", notes = "", response = SuggestResponse.class, tags = {"搜索联想词",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SuggestResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/suggest/getSuggestByKeyword",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SuggestResponse> getSuggestByKeyword(@ApiParam(value = "suggestRequest", required = true) @Valid SuggestRequest suggestRequest);

    @ApiOperation(value = "获取经纪人信息", nickname = "getAgentInfoByUserId", notes = "", response = AgentResponse.class, tags = {"搜索联想词",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AgentResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/agent/getAgentInfoByUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<AgentResponse> getAgentInfoByUserId(@ApiParam(value = "agentRequest", required = true) @Valid AgentRequest agentRequest);

    @ApiOperation(value = "根据城市代码获取查询条件", nickname = "selectSearchContentByCityIdAndType", notes = "", response = SearchConditionResponse.class, tags = {"搜索联想词",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SuggestResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/suggest/selectSearchContentByCityIdAndType",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SearchConditionResponse> selectSearchConditionByCityIdAndType(@ApiParam(value = "searchConditionRequest", required = true) @Valid SearchConditionRequest searchConditionRequest);

}
