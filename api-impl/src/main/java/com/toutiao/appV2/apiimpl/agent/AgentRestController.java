//package com.toutiao.appV2.apiimpl.agent;
//
//import com.toutiao.app.domain.agent.AgentBaseDo;
//import com.toutiao.app.service.agent.AgentService;
//import com.toutiao.appV2.api.agent.AgentRestApi;
//import com.toutiao.appV2.model.agent.AgentRequest;
//import com.toutiao.appV2.model.agent.AgentResponse;
//import com.toutiao.web.common.util.city.CityUtils;
//import io.swagger.annotations.ApiParam;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-17T03:08:16.328Z")
//
//@RestController
//public class AgentRestController implements AgentRestApi {
//
//    private static final Logger log = LoggerFactory.getLogger(AgentRestController.class);
//
//    private final HttpServletRequest request;
//
//    @Autowired
//    private AgentService agentService;
//
//    @Autowired
//    public AgentRestController(HttpServletRequest request) {
//        this.request = request;
//    }
//
//    @Override
//    public ResponseEntity<AgentResponse> getAgentInfoByUserId(@ApiParam(value = "agentRequest" ,required=true )  @Valid  AgentRequest agentRequest) {
//        String accept = request.getHeader("Accept");
//        if (accept != null && accept.contains("application/json")) {
//            try {
//                AgentResponse agentResponse = new AgentResponse();
//                AgentBaseDo agentBaseDo = agentService.queryAgentInfoByUserId(agentRequest.getUserId(), CityUtils.getCity());
//                BeanUtils.copyProperties(agentBaseDo,agentResponse);
//                return new ResponseEntity<AgentResponse>(agentResponse, HttpStatus.OK);
//            } catch (Exception e) {
//                log.error("Couldn't serialize response for content type application/json", e);
//                return new ResponseEntity<AgentResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        }
//        return new ResponseEntity<AgentResponse>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//}
