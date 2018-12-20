package com.toutiao.web.dao.mapper.subscribe;

import com.toutiao.web.dao.entity.subscribe.SubwayLineData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 18710 on 2018/11/22.
 */
@Repository
public interface SubwayLineDao {
    List<SubwayLineData> getSubwayLineInfoByCityId(@Param("cityId") Integer cityId);
}
