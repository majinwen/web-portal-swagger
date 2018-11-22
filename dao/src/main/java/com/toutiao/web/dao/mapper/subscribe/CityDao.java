package com.toutiao.web.dao.mapper.subscribe;

import com.toutiao.web.dao.entity.subscribe.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDao {
    int countByExample(City example);

    int deleteByExample(CityQuery example);

    int deleteByPrimaryKey(Integer cityId);

    int insert(City record);

    int insertSelective(City record);

    List<City> selectByExample(CityQuery example);

    City selectByPrimaryKey(Integer cityId);

    int updateByExampleSelective(@Param("record") City record, @Param("example") CityQuery example);

    int updateByExample(@Param("record") City record, @Param("example") CityQuery example);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);

    /**
     * 获取城市名称
     *
     * @param cityId
     * @return
     */
    String selectCityName(Integer cityId);

    City selectCityByDomain(@Param("cityDomain") String cityDomain);

    List<District> selectDistrictListByCityId(@Param("cityId") Integer cityId);

    List<DistrictAreaConfig> selectConfigListByDisIdHouType(DistrictAreaConfigQuery districtAreaConfigQuery);

    Area selectAreaByAreaId(Integer areaId);

    List<CityInfo> getCities(@Param("isShow") Integer isShow);


    List<CityDistrctInfo> getCityDistrictInfos(@Param("cityId") Integer cityId);


    List<CityAdInfo> getCityAdInfo(@Param("cityId") Integer cityId);

    List<CityConditionDo> getCityCondition(@Param("cityId") Integer cityId);

    CityInfoData getCityInfos(@Param("cityId") Integer cityId);

    List<AreaInfoData> getCityCircleData(@Param("cityId") Integer cityId);

    List<ParkInfo> getParkInfo(@Param("cityId") Integer cityId);

    List<WapCity> selectWapCity();

    List<CityConditionDo> getCityConditionByIdAndType(@Param("cityId") Integer cityId,@Param("type") String type);
}