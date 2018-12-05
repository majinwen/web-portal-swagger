package com.toutiao.appV2.apiimpl.suggest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toutiao.app.domain.agent.AgentBaseDo;
import com.toutiao.app.domain.sellhouse.HouseSubject;
import com.toutiao.app.domain.suggest.SearchEnginesDo;
import com.toutiao.app.domain.suggest.SearchScopeDo;
import com.toutiao.app.domain.suggest.SuggestListDo;
import com.toutiao.app.domain.sellhouse.HouseSubjectListResponse;
import com.toutiao.app.domain.suggest.SuggestResultDo;
import com.toutiao.app.service.agent.AgentService;
import com.toutiao.app.service.search.SearchConditionService;
import com.toutiao.app.service.subscribe.CityService;
import com.toutiao.app.service.suggest.SuggestService;
import com.toutiao.appV2.api.suggest.SuggestRestApi;
import com.toutiao.appV2.model.agent.AgentRequest;
import com.toutiao.appV2.model.agent.AgentResponse;
import com.toutiao.appV2.model.search.SearchConditionRequest;
import com.toutiao.appV2.model.search.SearchConditionResponse;
import com.toutiao.appV2.model.subscribe.*;
import com.toutiao.appV2.model.suggest.SearchEnginesResponse;
import com.toutiao.appV2.model.suggest.SuggestRequest;
import com.toutiao.appV2.model.suggest.SuggestResultResponse;
import com.toutiao.web.common.util.city.CityUtils;
import com.toutiao.web.dao.entity.search.SearchCondition;
import com.toutiao.web.dao.entity.subscribe.City;
import com.toutiao.web.dao.entity.subscribe.CityParkInfo;
import com.toutiao.web.dao.entity.subscribe.SubwayLineData;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-16T10:37:50.619Z")

@RestController
@Slf4j
public class SuggestRestController implements SuggestRestApi {

    private static final Logger log = LoggerFactory.getLogger(SuggestRestController.class);

    private final HttpServletRequest request;

    @Autowired
    public SuggestRestController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.request = request;
    }

    @Autowired
    private SuggestService suggestService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private SearchConditionService searchConditionService;

    @Override
    public ResponseEntity<SuggestResultResponse> getSuggestByKeyword(@ApiParam(value = "suggestRequest", required = true) @Valid SuggestRequest suggestRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                SuggestResultDo suggestResultDo = suggestService.suggest_v2(suggestRequest.getKeyword(), suggestRequest.getProperty(), CityUtils.getCity());
                SuggestResultResponse suggestResultResponse = new SuggestResultResponse();
                List<SearchEnginesResponse> searchEnginesResponseList = new ArrayList<>();
                if (suggestResultDo.getSuggestResultList().size() > 0) {
                    for (SuggestListDo suggestListDo : suggestResultDo.getSuggestResultList()) {
                        //搜索结果无商圈信息
                        SearchScopeDo searchScope = suggestListDo.getSearchScope();
                        if (searchScope == null) {
                            if (suggestListDo.getSearchEnginesList().size() > 0) {
                                for (SearchEnginesDo searchEnginesDo : suggestListDo.getSearchEnginesList()) {
                                    SearchEnginesResponse searchEnginesResponse = new SearchEnginesResponse();
                                    BeanUtils.copyProperties(searchEnginesDo, searchEnginesResponse);
                                    searchEnginesResponse.setIsArea(0);
                                    searchEnginesResponseList.add(searchEnginesResponse);
                                }
                            }
                        } else {
                            SearchEnginesResponse searchScopeResponse = new SearchEnginesResponse();
                            searchScopeResponse.setSearchName(searchScope.getSearchName());
                            searchScopeResponse.setAreaId(searchScope.getSearchId());
                            searchScopeResponse.setCityId(searchScope.getCityId());
                            searchScopeResponse.setIsArea(searchScope.getLocationTypeSings());
                            searchScopeResponse.setSearchTypeSings(searchScope.getSearchTypeSings());
                            searchEnginesResponseList.add(searchScopeResponse);
                            Integer listSize = suggestListDo.getSearchEnginesList().size();
                            if (listSize > 0) {
                                if (listSize > 2) {
                                    listSize = listSize - 1;
                                }
                                for (int i = 0; i < listSize; i++) {
                                    SearchEnginesDo searchEnginesDo = suggestListDo.getSearchEnginesList().get(i);
                                    SearchEnginesResponse searchEnginesResponse = new SearchEnginesResponse();
                                    BeanUtils.copyProperties(searchEnginesDo, searchEnginesResponse);
                                    searchEnginesResponse.setIsArea(0);
                                    searchEnginesResponse.setSearchTypeSings(searchScope.getSearchTypeSings());
                                    List searchNickname = searchEnginesDo.getSearchNickname();
                                    String nickname = "";
                                    if (searchNickname != null && searchNickname.size() > 0) {
                                        for (int j = 0; j < searchNickname.size(); j++) {
                                            nickname += searchNickname.get(j).toString() + "·";
                                        }
                                    }
                                    if (nickname.endsWith("·")) {
                                        nickname = nickname.substring(0, nickname.length() - 1);
                                    }
                                    searchEnginesResponse.setSearchNickname(nickname);
                                    searchEnginesResponseList.add(searchEnginesResponse);
                                }
                            }
                        }
                    }

                }
                suggestResultResponse.setSearchEnginesList(searchEnginesResponseList);
                suggestResultResponse.setTotalCount(searchEnginesResponseList.size());
                //SuggestResultResponse suggestResultResponse = JSON.parseObject(JSON.toJSONString(suggestResultDo), SuggestResultResponse.class);
                return new ResponseEntity<SuggestResultResponse>(suggestResultResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<SuggestResultResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SuggestResultResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<AgentResponse> getAgentInfoByUserId(@ApiParam(value = "agentRequest", required = true) @Valid AgentRequest agentRequest) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                AgentResponse agentResponse = new AgentResponse();
                AgentBaseDo agentBaseDo = agentService.queryAgentInfoByUserId(agentRequest.getUserId(), CityUtils.getCity());
                BeanUtils.copyProperties(agentBaseDo, agentResponse);
                return new ResponseEntity<AgentResponse>(agentResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<AgentResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<AgentResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<HouseSubjectListResponse> selectSearchTagsByCityIdAndType(SearchConditionRequest searchConditionRequest) {
        HouseSubjectListResponse houseSubjectListResponse = new HouseSubjectListResponse();
        List<HouseSubject> houseSubjectList = new ArrayList<>();
        if (searchConditionRequest.getType() == 1) {
            HouseSubject houseSubject = new HouseSubject();
            houseSubject.setText("低密度");
            houseSubject.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject);
            HouseSubject houseSubject1 = new HouseSubject();
            houseSubject1.setText("近地铁");
            houseSubject1.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject1);
            HouseSubject houseSubject2 = new HouseSubject();
            houseSubject2.setText("500强房企");
            houseSubject2.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject2);
        } else if (searchConditionRequest.getType() == 3) {
            HouseSubject houseSubject = new HouseSubject();
            houseSubject.setText("地铁房");
            houseSubject.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject);
            HouseSubject houseSubject1 = new HouseSubject();
            houseSubject1.setText("独立阳台");
            houseSubject1.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject1);
            HouseSubject houseSubject2 = new HouseSubject();
            houseSubject2.setText("独立卫生间");
            houseSubject2.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject2);
        } else if (searchConditionRequest.getType() == 4) {
            HouseSubject houseSubject = new HouseSubject();
            houseSubject.setText("近地铁");
            houseSubject.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject);
            HouseSubject houseSubject1 = new HouseSubject();
            houseSubject1.setText("top50社区");
            houseSubject1.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject1);
            HouseSubject houseSubject2 = new HouseSubject();
            houseSubject2.setText("首次置业");
            houseSubject2.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject2);
            HouseSubject houseSubject3 = new HouseSubject();
            houseSubject3.setText("换房升级");
            houseSubject3.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject3);
            HouseSubject houseSubject4 = new HouseSubject();
            houseSubject4.setText("豪宅社区");
            houseSubject4.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject4);
            HouseSubject houseSubject5 = new HouseSubject();
            houseSubject5.setText("别墅社区");
            houseSubject5.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject5);
            HouseSubject houseSubject6 = new HouseSubject();
            houseSubject6.setText("公园社区");
            houseSubject6.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject6);
        } else {
            HouseSubject houseSubject = new HouseSubject();
            houseSubject.setText("地铁房");
            houseSubject.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject);
            HouseSubject houseSubject1 = new HouseSubject();
            houseSubject1.setText("降价房");
            houseSubject1.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject1);
            HouseSubject houseSubject2 = new HouseSubject();
            houseSubject2.setText("捡漏房");
            houseSubject2.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject2);
            HouseSubject houseSubject3 = new HouseSubject();
            houseSubject3.setText("抢手房");
            houseSubject3.setUrl("http://app.bidewu.com/");
            houseSubjectList.add(houseSubject3);
        }
        houseSubjectListResponse.setHouseSubjectList(houseSubjectList);
        houseSubjectListResponse.setTotalCount(houseSubjectList.size());
        return new ResponseEntity<HouseSubjectListResponse>(houseSubjectListResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityAllInfoMap> getCityAllInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId", required = false, defaultValue = "0") Integer cityId, @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain", required = false, defaultValue = "") String cityDomain) {
        if (cityId == 0 && Objects.equals(cityDomain, "")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cityId == 0) {
            City city = cityService.selectCityByDomain(cityDomain);
            if (city != null) {
                cityId = city.getCityId();
            }
        }
        CityAllInfoMap cityAllInfoMap = new CityAllInfoMap();
        Map<String, Object> res = cityService.getCityAllInfo(cityId);
        cityAllInfoMap.setVersion(3);
        cityAllInfoMap.setCityAllInfos(res);
        return new ResponseEntity<CityAllInfoMap>(cityAllInfoMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityConditionInfoMap> getCityConditionInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId", required = false, defaultValue = "0") Integer cityId, @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain", required = false, defaultValue = "") String cityDomain) {
        if (cityId == 0 && Objects.equals(cityDomain, "")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cityId == 0) {
            City city = cityService.selectCityByDomain(cityDomain);
            if (city != null) {
                cityId = city.getCityId();
            }
        }
        CityConditionInfoMap cityConditionInfoMap = new CityConditionInfoMap();
        Map<String, Object> res = cityService.getCityConditionInfo(cityId);
        cityConditionInfoMap.setCityConditionInfos(res);
        return new ResponseEntity<CityConditionInfoMap>(cityConditionInfoMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityCircleInfoMap> getCityCircleInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId", required = false, defaultValue = "0") Integer cityId, @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain", required = false, defaultValue = "") String cityDomain) {
        if (cityId == 0 && Objects.equals(cityDomain, "")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cityId == 0) {
            City city = cityService.selectCityByDomain(cityDomain);
            if (city != null) {
                cityId = city.getCityId();
            }
        }
        CityCircleInfoMap cityCircleInfoMap = new CityCircleInfoMap();
        Map<String, Object> res = cityService.getCityCircleInfo(cityId);
        cityCircleInfoMap.setCityCircleInfos(res);
        return new ResponseEntity<CityCircleInfoMap>(cityCircleInfoMap, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityDiscrictInfoMap> getCityDistrictInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId", required = false, defaultValue = "0") Integer cityId, @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain", required = false, defaultValue = "") String cityDomain) {
        if (cityId == 0 && Objects.equals(cityDomain, "")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cityId == 0) {
            City city = cityService.selectCityByDomain(cityDomain);
            if (city != null) {
                cityId = city.getCityId();
            }
        }
        CityDiscrictInfoMap cityDiscrictInfoMap = new CityDiscrictInfoMap();
        Map<String, Object> res = cityService.getCityDistrictInfo(cityId);
        cityDiscrictInfoMap.setCityDiscrictInfos(res);
        return new ResponseEntity<CityDiscrictInfoMap>(cityDiscrictInfoMap, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CityParkInfoMap> getCityParkInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId", required = false, defaultValue = "0") Integer cityId, @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain", required = false, defaultValue = "") String cityDomain) {
        if (cityId == 0 && Objects.equals(cityDomain, "")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cityId == 0) {
            City city = cityService.selectCityByDomain(cityDomain);
            if (city != null) {
                cityId = city.getCityId();
            }
        }
        CityParkInfoMap cityParkInfoMap = new CityParkInfoMap();
        Map<String, Object> res = cityService.getCityParkInfo(cityId);
        cityParkInfoMap.setCityParkInfos(res);
        return new ResponseEntity<CityParkInfoMap>(cityParkInfoMap, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CitySubwayInfoMap> getCitySubwaysInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId", required = false, defaultValue = "0") Integer cityId, @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain", required = false, defaultValue = "") String cityDomain) {
        if (cityId == 0 && Objects.equals(cityDomain, "")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cityId == 0) {
            City city = cityService.selectCityByDomain(cityDomain);
            if (city != null) {
                cityId = city.getCityId();
            }
        }
        CitySubwayInfoMap citySubwayInfoMap = new CitySubwayInfoMap();
        List<SubwayLineData> res = cityService.getCitySubwayInfo(cityId);
        citySubwayInfoMap.setCitySubwayInfos(res);
        return new ResponseEntity<CitySubwayInfoMap>(citySubwayInfoMap, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CityPidsInfoMap> getCityPidsInfo(@ApiParam(value = "cityId", required = false) @Valid @RequestParam(value = "cityId", required = false, defaultValue = "0") Integer cityId, @ApiParam(value = "cityDomain", required = false) @Valid @RequestParam(value = "cityDomain", required = false, defaultValue = "") String cityDomain) {
        if (cityId == 0 && Objects.equals(cityDomain, "")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cityId == 0) {
            City city = cityService.selectCityByDomain(cityDomain);
            if (city != null) {
                cityId = city.getCityId();
            }
        }
        CityPidsInfoMap cityPidsInfoMap = new CityPidsInfoMap();
        Map<String, Object> res = cityService.getCityPidsInfo(cityId);
        cityPidsInfoMap.setCityPidsInfos(res);
        return new ResponseEntity<CityPidsInfoMap>(cityPidsInfoMap, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<WapCityList> getWapCity() {
        WapCityList wapCityList = new WapCityList();
        wapCityList.setWapCityList(cityService.selectWapCity());
        return new ResponseEntity<WapCityList>(wapCityList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityConditionDoList> getCityconditionByIdAndType(@ApiParam(value = "cityId", required = true) @Valid @RequestParam(value = "cityId", required = true) Integer cityId, @ApiParam(value = "type", required = true) @Valid @RequestParam(value = "type", required = true) String type) {
        CityConditionDoList cityConditionDoList = new CityConditionDoList();
        cityConditionDoList.setCityConditionDos(cityService.getCityConditionByIdAndType(cityId, type));
        return new ResponseEntity<CityConditionDoList>(cityConditionDoList, HttpStatus.OK);
    }

}
