package com.toutiao.web.dao.mapper.officeweb.hotplot;

import com.toutiao.app.domain.hotplot.SearchHotProj;
import com.toutiao.app.domain.hotplot.SearchHotProjDo;
import com.toutiao.web.dao.BaseDao;

import java.util.List;

public interface SearchHotProjMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SearchHotProj record);

    int insertSelective(SearchHotProj record);

    SearchHotProj selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SearchHotProj record);

    int updateByPrimaryKey(SearchHotProj record);


    /**
     * 根据城市id获取城市热门小区
     * @param city
     * @return
     */
    List<SearchHotProjDo> queryHotPlotsByCityId(Integer city);
}