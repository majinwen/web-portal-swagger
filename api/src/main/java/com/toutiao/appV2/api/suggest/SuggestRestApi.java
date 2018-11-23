package com.toutiao.appV2.api.suggest;

import com.toutiao.app.api.chance.response.suggest.SuggestListResponse;
import com.toutiao.app.api.chance.response.suggest.SuggestResultResponse;
import com.toutiao.app.domain.sellhouse.HouseSubjectListResponse;
import com.toutiao.appV2.model.agent.AgentRequest;
import com.toutiao.appV2.model.agent.AgentResponse;
import com.toutiao.appV2.model.search.SearchConditionRequest;
import com.toutiao.appV2.model.search.SearchConditionResponse;
import com.toutiao.appV2.model.subscribe.CityAllInfoMap;
import com.toutiao.appV2.model.subscribe.CityConditionDoList;
import com.toutiao.appV2.model.subscribe.WapCityList;
import com.toutiao.appV2.model.suggest.SuggestRequest;
import com.toutiao.appV2.model.suggest.SuggestResponse;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:37:50.619Z")

@Api(value = "搜索联想词", description = "搜索联想词")
public interface SuggestRestApi {

    @ApiOperation(value = "搜索联想词提示", nickname = "getSuggestByKeyword", notes = "", response = SuggestResponse.class, tags = {"其他",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SuggestListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/suggest/getSuggestByKeyword",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<SuggestResultResponse> getSuggestByKeyword(@ApiParam(value = "suggestRequest", required = true) @Valid SuggestRequest suggestRequest);

    @ApiOperation(value = "获取经纪人信息", nickname = "getAgentInfoByUserId", notes = "", response = AgentResponse.class, tags = {"其他",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AgentResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/agent/getAgentInfoByUserId",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<AgentResponse> getAgentInfoByUserId(@ApiParam(value = "agentRequest", required = true) @Valid AgentRequest agentRequest);

    @ApiOperation(value = "根据城市代码获取查询条件", nickname = "selectSearchConditionByCityIdAndType", notes = "", response = HouseSubjectListResponse.class, tags = {"其他",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HouseSubjectListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/suggest/selectSearchConditionByCityIdAndType",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HouseSubjectListResponse> selectSearchConditionByCityIdAndType(@ApiParam(value = "searchConditionRequest", required = true) @Valid SearchConditionRequest searchConditionRequest);

    @ApiOperation(value = "查询城市所有信息", nickname = "getCityAllInfo", notes = "", response = CityAllInfoMap.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CityAllInfoMap.class),
            @ApiResponse(code = 201, message = "Created", response = CityAllInfoMap.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getCityAllInfo",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<CityAllInfoMap> getCityAllInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId",required = false,defaultValue = "0") Integer cityId,
                                                  @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain",required = false,defaultValue = "") String cityDomain);




    @ApiOperation(value = "查询城市信息", nickname = "getWapCity", notes = "", response = WapCityList.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = WapCityList.class),
            @ApiResponse(code = 201, message = "Created", response = WapCityList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getWapCity",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<WapCityList> getWapCity();


    @ApiOperation(value = "根据城市id和type查询citycondition", nickname = "getCityconditionByIdAndType", notes = "", response = CityConditionDoList.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CityConditionDoList.class),
            @ApiResponse(code = 201, message = "Created", response = CityConditionDoList.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getCityconditionByIdAndType",
            produces = { "application/json" },
            //consumes = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<CityConditionDoList> getCityconditionByIdAndType(@ApiParam(value = "cityId", required = true) @Valid @RequestParam(value = "cityId",required = true) Integer cityId,
                                                                    @ApiParam(value = "type", required = true) @Valid @RequestParam(value = "type",required = true) String type);

}
