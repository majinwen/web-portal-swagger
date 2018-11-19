package com.toutiao.appV2.api.agent;

import com.toutiao.appV2.model.agent.AgentRequest;
import com.toutiao.appV2.model.agent.AgentResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:08:16.328Z")

@Api(value = "获取经纪人API", description = "获取经纪人API")
public interface AgentRestApi {

    @ApiOperation(value = "获取经纪人信息", nickname = "getAgentInfoByUserId", notes = "", response = AgentResponse.class, tags={ "获取经纪人API", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = AgentResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/agent/getAgentInfoByUserId",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<AgentResponse> getAgentInfoByUserId(@ApiParam(value = "agentRequest", required = true) @Valid AgentRequest agentRequest);

}
