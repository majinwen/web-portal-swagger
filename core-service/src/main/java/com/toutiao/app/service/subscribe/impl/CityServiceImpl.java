package com.toutiao.app.service.subscribe.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.app.service.subscribe.CityService;
import com.toutiao.app.service.subscribe.SubwayService;
import com.toutiao.web.common.util.JSONUtil;
import com.toutiao.web.dao.entity.subscribe.*;
import com.toutiao.web.dao.mapper.subscribe.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by 18710 on 2018/11/22.
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Autowired
    private SubwayService subwayService;

    @Override
    public Map<String, Object> getCityAllInfo(Integer cityId) {

        Map<String, Object> returnMap = new HashMap<>();
        // 城市广告、区域、公园信息
//        CityInfoData cityInfos = cityDao.getCityInfos(cityId);
        List<CityAdInfo> cityAdInfo = cityDao.getCityAdInfo(cityId);
        List<CityDistrctInfo> cityDistrictInfos = cityDao.getCityDistrictInfos(cityId);
        List<CityConditionDo> cityCondition = cityDao.getCityCondition(cityId);
        List<ParkInfo> parkInfos = cityDao.getParkInfo(cityId);
        // 地铁信息
        List<SubwayLineData> subwayLineInfos = subwayService.getSubwayLineInfos(cityId);
        // 商圈信息
        List<AreaInfoData> areaInfoDataList = cityDao.getCityCircleData(cityId);

        if (null == cityAdInfo || cityAdInfo.isEmpty()) {
            returnMap.put("pidsList", JSONUtil.parse("{}", null));
        } else {
            returnMap.put("pidsList", JSONUtil.parse(cityAdInfo.get(0).getAdInfo(), null));
        }

        if (null == cityDistrictInfos || cityDistrictInfos.isEmpty()) {
            returnMap.put("districtInfo", JSONUtil.parse("[]", null));
        } else {
            returnMap.put("districtInfo", JSONUtil.parse(cityDistrictInfos.get(0).getDistrictInfo(), null));
        }

        if (null == subwayLineInfos || subwayLineInfos.isEmpty()) {
            returnMap.put("subwayDataList", JSONUtil.parse("[]", null));
        } else {
            returnMap.put("subwayDataList", subwayLineInfos);
        }

        if (null == parkInfos || parkInfos.isEmpty()) {
            returnMap.put("parkInfo", JSONUtil.parse("[]", null));
        } else {
            returnMap.put("parkInfo", JSONUtil.parse(parkInfos.get(0).getParkInfo(), null));
        }

        Map<String, Object> cityConditionMap = new HashMap<>();
        for (CityConditionDo cityConditionData : cityCondition) {
            Map<String, Object> cityConditionTypeMap = new HashMap<>();
            JSONObject jsonObject = JSONObject.parseObject(cityConditionData.getCondition());
            Set<String> strings = jsonObject.keySet();
            for (String str : strings) {
                if (!Objects.equals(str, "priceUnit")) {
                    JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString(str).replace("'", "\""));
                    cityConditionTypeMap.put(str, jsonArray);
                } else {
                    cityConditionTypeMap.put(str, jsonObject.getString(str));
                }
            }

            cityConditionMap.put(cityConditionData.getType(), cityConditionTypeMap);
        }
        returnMap.put("searchConditionData", cityConditionMap);
        returnMap.put("circleDataList", areaInfoDataList);

        return returnMap;
    }

    @Override
    public Map<String, Object> getCityConditionInfo(Integer cityId) {

        Map<String, Object> returnMap = new HashMap<>();
        // 城市广告、区域、公园信息
//        CityInfoData cityInfos = cityDao.getCityInfos(cityId);
        List<CityConditionDo> cityCondition = cityDao.getCityCondition(cityId);

        Map<String, Object> cityConditionMap = new HashMap<>();
        for (CityConditionDo cityConditionData : cityCondition) {
            Map<String, Object> cityConditionTypeMap = new HashMap<>();
            JSONObject jsonObject = JSONObject.parseObject(cityConditionData.getCondition());
            Set<String> strings = jsonObject.keySet();
            for (String str : strings) {
                if (!Objects.equals(str, "priceUnit")) {
                    JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString(str).replace("'", "\""));
                    cityConditionTypeMap.put(str, jsonArray);
                } else {
                    cityConditionTypeMap.put(str, jsonObject.getString(str));
                }
            }

            cityConditionMap.put(cityConditionData.getType(), cityConditionTypeMap);
        }
        returnMap.put("searchConditionData", cityConditionMap);


        return returnMap;
    }

    @Override
    public Map<String, Object> getCityCircleInfo(Integer cityId) {

        Map<String, Object> returnMap = new HashMap<>();

        // 商圈信息
        List<AreaInfoData> areaInfoDataList = cityDao.getCityCircleData(cityId);

        returnMap.put("circleDataList", areaInfoDataList);

        return returnMap;
    }

    @Override
    public List<SubwayLineData> getCitySubwayInfo(Integer cityId) {

        Map<String, Object> returnMap = new HashMap<>();

        // 地铁信息
        List<SubwayLineData> subwayLineInfos = subwayService.getSubwayLineInfos(cityId);

        List<SubwayLineData> parse = null;

  /*      if (null == subwayLineInfos || subwayLineInfos.isEmpty()) {
            parse = (JSONArray) JSONUtil.parse("{}", null);
            returnMap.put("subwayDataList", JSONUtil.parse("[]", null));
        } else {
            parse = subwayLineInfos;
            returnMap.put("subwayDataList", subwayLineInfos);
        }*/

        return subwayLineInfos;
    }


    @Override
    public  Map<String, Object>  getCityPidsInfo(Integer cityId) {

        Map<String, Object> returnMap = new HashMap<>();
        // 城市广告、区域、公园信息

        List<CityAdInfo> cityAdInfo = cityDao.getCityAdInfo(cityId);

        JSONObject parse = null;

        if (null == cityAdInfo || cityAdInfo.isEmpty()) {
          //  parse = (JSONObject) JSONUtil.parse("{}", null);
            returnMap.put("pidsList", JSONUtil.parse("{}", null));
        } else {
           // parse = (JSONObject) JSONUtil.parse(cityAdInfo.get(0).getAdInfo(), null);
            returnMap.put("pidsList", JSONUtil.parse(cityAdInfo.get(0).getAdInfo(), null));
        }


        return returnMap;
    }

    @Override
    public Map<String, Object> getCityParkInfo(Integer cityId) {

        Map<String, Object> returnMap = new HashMap<>();
        // 城市广告、区域、公园信息
//        CityInfoData cityInfos = cityDao.getCityInfos(cityId);
        List<ParkInfo> parkInfos = cityDao.getParkInfo(cityId);
        JSONArray parse = null;
        if (null == parkInfos || parkInfos.isEmpty()) {
          //  parse = (JSONArray)JSONUtil.parse("[]", null);
            returnMap.put("parkInfo", JSONUtil.parse("[]", null));
        } else {
           // parse = (JSONArray)JSONUtil.parse(parkInfos.get(0).getParkInfo(), null);
            returnMap.put("parkInfo", JSONUtil.parse(parkInfos.get(0).getParkInfo(), null));
        }

        return returnMap;
    }

    @Override
    public Map<String, Object> getCityDistrictInfo(Integer cityId) {

        Map<String, Object> returnMap = new HashMap<>();
        // 城市广告、区域、公园信息
//        CityInfoData cityInfos = cityDao.getCityInfos(cityId);
        List<CityDistrctInfo> cityDistrictInfos = cityDao.getCityDistrictInfos(cityId);
        JSONArray parse = null;
        if (null == cityDistrictInfos || cityDistrictInfos.isEmpty()) {
            returnMap.put("districtInfo", JSONUtil.parse("[]", null));
          //parse = (JSONArray) JSONUtil.parse(cityDistrictInfos.get(0).getDistrictInfo(), null);
        } else {
            returnMap.put("districtInfo", JSONUtil.parse(cityDistrictInfos.get(0).getDistrictInfo(), null));
           // parse = (JSONArray) JSONUtil.parse(cityDistrictInfos.get(0).getDistrictInfo(), null);
        }

        return returnMap;
    }

    @Override
    public City selectCityByDomain(String cityDomain) {
        return cityDao.selectCityByDomain(cityDomain);
    }

    @Override
    public List<WapCity> selectWapCity() {
        return cityDao.selectWapCity();
    }

    @Override
    public List<CityConditionDo> getCityConditionByIdAndType(Integer cityId, String type) {
        return cityDao.getCityConditionByIdAndType(cityId,type);
    }
}
