package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.TotalListedRatio;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TotalListedRatioMapper extends BaseDao {

    int insert(TotalListedRatio record);

    int insertSelective(TotalListedRatio record);

    List<TotalListedRatio> selectByTotalPrice(@PathVariable("plotTotal") Double plotTotal);
}