package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.TotalListedRatio;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface TotalListedRatioMapper extends BaseDao {

    int insert(TotalListedRatio record);

    int insertSelective(TotalListedRatio record);

    Double selectByTotalPrice(@Param("plotTotalFirst")Double plotTotalFirst,@Param("plotTotalEnd")Double plotTotalEnd);
}