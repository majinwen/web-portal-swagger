package com.toutiao.appV2.api.suggest;

import com.toutiao.app.domain.sellhouse.HouseSubjectListResponse;
import com.toutiao.appV2.model.agent.AgentRequest;
import com.toutiao.appV2.model.agent.AgentResponse;
import com.toutiao.appV2.model.houseCount.HouseCountRequest;
import com.toutiao.appV2.model.houseCount.HouseCountResponse;
import com.toutiao.appV2.model.search.SearchConditionRequest;
import com.toutiao.appV2.model.subscribe.*;
import com.toutiao.appV2.model.suggest.SuggestRequest;
import com.toutiao.appV2.model.suggest.SuggestResponse;
import com.toutiao.appV2.model.suggest.SuggestResultResponse;
import com.toutiao.appV2.model.version.VersionResponse;
import com.toutiao.web.dao.entity.subscribe.CityParkInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:37:50.619Z")

@Api(value = "搜索联想词", description = "搜索联想词")
public interface SuggestRestApi {

    @ApiOperation(value = "搜索联想词提示", nickname = "getSuggestByKeyword", notes = "", response = SuggestResponse.class, tags = {"其他",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SuggestResultResponse.class),
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

    @ApiOperation(value = "根据城市代码获取查询条件", nickname = "selectSearchTagsByCityIdAndType", notes = "", response = HouseSubjectListResponse.class, tags = {"其他",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HouseSubjectListResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(value = "/rest/suggest/selectSearchTagsByCityIdAndType",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<HouseSubjectListResponse> selectSearchTagsByCityIdAndType(@ApiParam(value = "searchConditionRequest", required = true) @Valid SearchConditionRequest searchConditionRequest);

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
    @ApiIgnore
    ResponseEntity<CityAllInfoMap> getCityAllInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId",required = false,defaultValue = "0") Integer cityId,
                                                  @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain",required = false,defaultValue = "") String cityDomain);


    @ApiOperation(value = "查询城市所有区域信息", nickname = "getCityDistrictInfo", notes = "", response = CityDiscrictInfoMap.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CityDiscrictInfoMap.class),
            @ApiResponse(code = 201, message = "Created", response = CityDiscrictInfoMap.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getCityDistrictInfo",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<CityDiscrictInfoMap> getCityDistrictInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId",required = false,defaultValue = "0") Integer cityId,
                                                            @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain",required = false,defaultValue = "") String cityDomain);


    @ApiOperation(value = "查询搜索条件信息", nickname = "getCityConditionInfo", notes = "", response = CityConditionInfoMap.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CityConditionInfoMap.class),
            @ApiResponse(code = 201, message = "Created", response = CityConditionInfoMap.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getCityConditionInfo",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<CityConditionInfoMap> getCityConditionInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId",required = false,defaultValue = "0") Integer cityId,
                                                    @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain",required = false,defaultValue = "") String cityDomain);


    @ApiOperation(value = "查询城市商圈信息", nickname = "getCityCircleInfo", notes = "", response = CityCircleInfoMap.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CityCircleInfoMap.class),
            @ApiResponse(code = 201, message = "Created", response = CityCircleInfoMap.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getCityCircleInfo",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<CityCircleInfoMap> getCityCircleInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId",required = false,defaultValue = "0") Integer cityId,
                                                    @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain",required = false,defaultValue = "") String cityDomain);



    @ApiOperation(value = "查询城市所有公园信息", nickname = "getCityParkInfo", notes = "", response = CityParkInfoMap.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CityParkInfoMap.class),
            @ApiResponse(code = 201, message = "Created", response = CityParkInfoMap.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getCityParkInfo",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<CityParkInfoMap> getCityParkInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId",required = false,defaultValue = "0") Integer cityId,
                                                    @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain",required = false,defaultValue = "") String cityDomain);

    @ApiOperation(value = "查询城市所有广告位信息", nickname = "getCityPidsInfo", notes = "", response = CityPidsInfoMap.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CityPidsInfoMap.class),
            @ApiResponse(code = 201, message = "Created", response = CityPidsInfoMap.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getCityPidsInfo",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<CityPidsInfoMap> getCityPidsInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId",required = false,defaultValue = "0") Integer cityId,
                                                    @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain",required = false,defaultValue = "") String cityDomain);


    @ApiOperation(value = "查询城市所有地铁信息", nickname = "getCitySubwaysInfo", notes = "", response = CitySubwayInfoMap.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CitySubwayInfoMap.class),
            @ApiResponse(code = 201, message = "Created", response = CitySubwayInfoMap.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getCitySubwaysInfo",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @ApiIgnore
    ResponseEntity<CitySubwayInfoMap> getCitySubwaysInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId",required = false,defaultValue = "0") Integer cityId,
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
    @ApiIgnore
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

    @ApiOperation(value = "关键字搜索房源数量", nickname = "getHouseCount", notes = "", response = HouseCountResponse.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HouseCountResponse.class),
            @ApiResponse(code = 201, message = "Created", response = HouseCountResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getHouseCount",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<HouseCountResponse> getHouseCount(@ApiParam(value = "houseCountRequest", required = false)HouseCountRequest houseCountRequest);

    @ApiOperation(value = "验证APP是否有新版本", nickname = "getAppVersion", notes = "", response = VersionResponse.class, tags={ "其他", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = VersionResponse.class),
            @ApiResponse(code = 201, message = "Created", response = VersionResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    @RequestMapping(value = "/rest/getVersion",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<VersionResponse> getVersion(@ApiParam(value = "type", required = true) @Valid @RequestParam(value = "type",required = true) Integer type,
                                                  @ApiParam(value = "version", required = true) @Valid @RequestParam(value = "version",required = true) Integer version);
}
