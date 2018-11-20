package com.toutiao.app.service.search;

import com.toutiao.web.dao.entity.search.SearchCondition;
import org.apache.ibatis.annotations.Param;

public interface SearchConditionService {

    SearchCondition selectSearchConditionByCityIdAndType(@Param("cityId") Integer cityId, @Param("type") Integer type);

}
