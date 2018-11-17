package com.toutiao.appV2.api.suggest;

import com.toutiao.appV2.model.suggest.SuggestRequest;
import com.toutiao.appV2.model.suggest.SuggestResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:37:50.619Z")

@Api(value = "SuggestRestApi", description = "SuggestRestApi")
public interface SuggestRestApi {

    @ApiOperation(value = "搜索联想词提示", nickname = "getSuggestByKeyword", notes = "", response = SuggestResponse.class, tags={ "suggest", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = SuggestResponse.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/suggest/getSuggestByKeyword",
        produces = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<SuggestResponse> getSuggestByKeyword(@ApiParam(value = "suggestRequest", required = true) @Valid SuggestRequest suggestRequest);

}
