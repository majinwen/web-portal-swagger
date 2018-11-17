package com.toutiao.appV2.apiimpl.newhouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.api.chance.request.newhouse.NewHouseLayoutRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHouseLayoutCountResponse;
import com.toutiao.app.domain.newhouse.NewHouseLayoutCountDomain;
import com.toutiao.app.domain.newhouse.NewHouseLayoutDo;
import com.toutiao.app.service.newhouse.NewHouseLayoutService;
import com.toutiao.appV2.api.newhouse.NewHouseLayoutApi;
import com.toutiao.appV2.model.newhouse.GetNewHouseLayoutResponse;
import com.toutiao.web.common.util.city.CityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zym
 */
@Controller
public class NewHouseLayoutApiController implements NewHouseLayoutApi {

    private static final Logger log = LoggerFactory.getLogger(NewHouseLayoutApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private NewHouseLayoutService newHouseLayoutService;

    @Autowired
    public NewHouseLayoutApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<NewHouseLayoutCountResponse> getLayoutCountByNewHouseId(@Validated NewHouseLayoutRequest newHouseLayoutRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                Integer newHouseId = newHouseLayoutRequest.getNewCode();
                NewHouseLayoutCountResponse newHouseLayoutCountResponse = new NewHouseLayoutCountResponse();
                NewHouseLayoutCountDomain newHouseLayoutCountDomain = newHouseLayoutService.getNewHouseLayoutByNewHouseId(newHouseId, CityUtils.getCity());
                BeanUtils.copyProperties(newHouseLayoutCountDomain, newHouseLayoutCountResponse);
                return new ResponseEntity<>(newHouseLayoutCountResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<GetNewHouseLayoutResponse> getNewHouseLayoutByNewCode(@Validated NewHouseLayoutRequest newHouseLayoutRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                Integer newHouseId = newHouseLayoutRequest.getNewCode();
                Integer room = newHouseLayoutRequest.getRoom();
                List<NewHouseLayoutDo> newHouseLayoutDoList = newHouseLayoutService.getNewHouseLayoutList(newHouseId,
                        room, CityUtils.getCity());
                GetNewHouseLayoutResponse response = GetNewHouseLayoutResponse.builder().data(newHouseLayoutDoList)
                        .totalNum(newHouseLayoutDoList.size()).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
