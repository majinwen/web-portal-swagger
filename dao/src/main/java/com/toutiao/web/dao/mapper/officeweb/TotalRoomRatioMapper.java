package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.TotalRoomRatio;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Repository
public interface TotalRoomRatioMapper extends BaseDao {
    int insert(TotalRoomRatio record);

    int insertSelective(TotalRoomRatio record);

    List<Double> selectByTotal(@PathVariable("plotTotal") Double plotTotal);

    TotalRoomRatio selectByTotalAndCategory(@PathVariable("plotTotal") Double plotTotal,@PathVariable("categoryId") Integer category_id);


}