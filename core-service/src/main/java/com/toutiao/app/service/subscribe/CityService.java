package com.toutiao.app.service.subscribe;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.web.dao.entity.subscribe.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by 18710 on 2018/11/22.
 */
public interface CityService {

    Map<String, Object> getCityAllInfo(Integer cityId);
    Map<String, Object> getCityDistrictInfo(Integer cityId);
    Map<String, Object> getCityParkInfo(Integer cityId);

    Map<String, Object> getCityPidsInfo(Integer cityId);

    List<SubwayLineData> getCitySubwayInfo(Integer cityId);

    Map<String, Object> getCityConditionInfo(Integer cityId);

    Map<String, Object> getCityCircleInfo(Integer cityId);

    City selectCityByDomain(String cityDomain);

    List<WapCity> selectWapCity();

    List<CityConditionDo>getCityConditionByIdAndType(Integer cityId, String type);

}
