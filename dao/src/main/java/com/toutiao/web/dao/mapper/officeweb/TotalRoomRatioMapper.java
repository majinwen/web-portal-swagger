package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.TotalRoomRatio;
import com.toutiao.web.domain.query.IntelligenceQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TotalRoomRatioMapper extends BaseDao {
    int insert(TotalRoomRatio record);

    int insertSelective(TotalRoomRatio record);

    List<Double> selectByTotal(@Param("plotTotalFirst")Integer plotTotalFirst,@Param("plotTotalEnd")Integer plotTotalEnd);

    List<TotalRoomRatio> selectByTotalAndCategory(@Param("plotTotalFirst")Double plotTotalFirst,@Param("plotTotalEnd")Double plotTotalEnd, @Param("categoryId") Integer category_id);


    List<TotalRoomRatio> selectByTotalAndCategory1(@Param("plotTotalFirst")Double plotTotalFirst,@Param("plotTotalEnd")Double plotTotalEnd, @Param("categoryId") Integer category_id);

    List<TotalRoomRatio> selectByTotalAndCategory2(IntelligenceQuery intelligenceQuery);

    List<TotalRoomRatio> selectByTotalAndCategory3(IntelligenceQuery intelligenceQuery);
}