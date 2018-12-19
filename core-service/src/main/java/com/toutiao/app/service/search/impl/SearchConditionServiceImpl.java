package com.toutiao.app.service.search.impl;

import com.toutiao.app.service.search.SearchConditionService;
import com.toutiao.web.dao.entity.search.SearchCondition;
import com.toutiao.web.dao.mapper.search.SearchConditionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchConditionServiceImpl implements SearchConditionService {

    private SearchConditionMapper searchConditionMapper;

    @Autowired
    public SearchConditionServiceImpl(SearchConditionMapper searchConditionMapper){
        this.searchConditionMapper = searchConditionMapper;
    }

//    @Override
//    public SearchCondition selectSearchConditionByCityIdAndType(Integer cityId, Integer type) {
//        return searchConditionMapper.selectSearchConditionByCityIdAndType(cityId,type);
//    }
}
