package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.TotalRoomRatio;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Repository
public interface TotalRoomRatioMapper extends BaseDao {
    int insert(TotalRoomRatio record);

    int insertSelective(TotalRoomRatio record);

    List<Double> selectByTotal(@Param("plotTotalFirst")Integer plotTotalFirst,@Param("plotTotalEnd")Integer plotTotalEnd);

    List<TotalRoomRatio> selectByTotalAndCategory(@Param("plotTotalFirst")Double plotTotalFirst,@Param("plotTotalEnd")Double plotTotalEnd, @Param("categoryId") Integer category_id);


}