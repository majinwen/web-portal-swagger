package com.toutiao.appV2.apiimpl.newhouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.request.newhouse.NewHouseListRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDomainResponse;
import com.toutiao.app.domain.newhouse.NewHouseDoQuery;
import com.toutiao.app.domain.newhouse.NewHouseListDomain;
import com.toutiao.app.service.newhouse.NewHouseTopicsRestService;
import com.toutiao.appV2.api.newhouse.NewHouseTopicApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zym
 */
@Controller
public class NewHouseTopicApiController implements NewHouseTopicApi {

    private static final Logger log = LoggerFactory.getLogger(NewHouseTopicApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    public NewHouseTopicApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    private NewHouseTopicsRestService newHouseTopicsRestService;

    @Override
    public ResponseEntity<NewHouseListDomainResponse> getNewHouseTopic(NewHouseListRequest newHouseListRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                NewHouseListDomainResponse newHouseListDomainResponse = new NewHouseListDomainResponse();
                NewHouseDoQuery newHouseDoQuery = new NewHouseDoQuery();
                BeanUtils.copyProperties(newHouseListRequest, newHouseDoQuery);
                NewHouseListDomain newHouseListDomain = newHouseTopicsRestService.getNewHouseTopic(newHouseDoQuery);
                BeanUtils.copyProperties(newHouseListDomain, newHouseListDomainResponse);
                return new ResponseEntity<>(newHouseListDomainResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
