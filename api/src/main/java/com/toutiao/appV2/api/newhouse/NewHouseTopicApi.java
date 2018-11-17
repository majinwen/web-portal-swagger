/*
package com.toutiao.appV2.api.newhouse;

import com.toutiao.app.api.chance.request.newhouse.NewHouseListRequest;
import com.toutiao.app.api.chance.response.newhouse.NewHouseListDomainResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

*/
/**
 * @author zym
 *//*

@Api(value = "NewHouseTopicApi", description = "五环最美新房控制层")
public interface NewHouseTopicApi {
    @ApiOperation(value = "五环最美新房专题", nickname = "getNewHouseTopic", notes = "五环最美新房专题",
            response = NewHouseListDomainResponse.class, tags={ "new-house-topic-api-controller", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = NewHouseListDomainResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/newhouse/topic/getNewHouseTopic",
            produces = { "application/json" },
//            consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<NewHouseListDomainResponse> getNewHouseTopic(NewHouseListRequest newHouseListRequest);
}
*/
