package com.toutiao.web.dao.mapper.search;

import com.toutiao.web.dao.entity.search.SearchCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchConditionMapper {

    SearchCondition selectSearchConditionByCityIdAndType(@Param("cityId") Integer cityId, @Param("type") Integer type);

}
