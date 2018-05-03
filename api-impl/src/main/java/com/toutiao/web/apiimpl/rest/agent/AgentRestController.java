package com.toutiao.web.apiimpl.rest.agent;

import com.toutiao.app.api.chance.request.agent.AgentRequest;
import com.toutiao.app.api.chance.response.agent.AgentResponse;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.web.common.restmodel.NashResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/agent")
public class AgentRestController {

    @Autowired
    private AgentService agentService;
    /**
     * 获取经纪人信息
     * @param agentRequest
     * @return
     */
    @RequestMapping(value = "/getAgentInfoByUserId",method = RequestMethod.GET)
    public NashResult getAgentInfoByUserId(@Validated AgentRequest agentRequest){
        AgentResponse agentResponse = new AgentResponse();
        AgentBaseDo agentBaseDo = agentService.queryAgentInfoByUserId(agentRequest.getUserId());
        BeanUtils.copyProperties(agentBaseDo,agentResponse);
        return NashResult.build(agentResponse);
    }
}
