package com.toutiao.web.dao.mapper.compared;

import com.toutiao.web.dao.entity.compared.HouseCompared;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseComparedMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(HouseCompared record);

    int insertSelective(HouseCompared record);

    HouseCompared selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HouseCompared record);

    int updateByPrimaryKey(HouseCompared record);

    HouseCompared selectByUserIdAndHouseId(@Param("userId")Integer userId, @Param("houseId")String houseId, @Param("cityId")Integer cityId);

    List<HouseCompared> selectByUserId(@Param("userId")Integer userId, @Param("cityId")Integer cityId);
}