package com.toutiao.web.dao.mapper.subway;

import com.toutiao.web.dao.entity.subway.SubwayLineDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by CuiShihao on 2018/12/13
 */
@Repository
public interface SubwayLineInfoDao {


    SubwayLineDo selectLineInfoByLineId(@Param("lineId") Integer lineId);
}
