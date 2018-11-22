package com.toutiao.app.service.subscribe;

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

    City selectCityByDomain(String cityDomain);

    List<WapCity> selectWapCity();

    List<CityConditionDo>getCityConditionByIdAndType(Integer cityId, String type);

}
