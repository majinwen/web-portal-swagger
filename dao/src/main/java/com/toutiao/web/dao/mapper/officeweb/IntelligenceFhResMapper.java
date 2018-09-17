package com.toutiao.web.dao.mapper.officeweb;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.officeweb.IntelligenceFhRes;
import com.toutiao.web.domain.intelligenceFh.IntelligenceFhResDo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntelligenceFhResMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IntelligenceFhRes record);

    int insertSelective(IntelligenceFhRes record);

    IntelligenceFhRes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntelligenceFhRes record);

    int updateByPrimaryKey(IntelligenceFhRes record);

    List<IntelligenceFhRes> selectByUserPhone(String userPhone);

    Integer saveData(IntelligenceFhResDo intelligenceFhResDo);

    Integer saveData(IntelligenceFhRes intelligenceFhRes);

    int  deleteMyReport(@Param("reportId") Integer reportId);

    int updateMyReportCollectStatus(@Param("reportId") Integer reportId,@Param("usePhone") String usePhone);
}