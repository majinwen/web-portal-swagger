package com.toutiao.web.dao.mapper.admin;



import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.admin.ProjInfo;
//import com.toutiao.web.domain.query.HousingProjectQuery;

import java.util.List;

public interface ProjInfoMapper extends BaseDao {
    int deleteByPrimaryKey(Integer newcode);

    int insert(ProjInfo record);

    int insertSelective(ProjInfo record);

    ProjInfo selectByPrimaryKey(Integer newcode);

    int updateByPrimaryKeySelective(ProjInfo record);

    int updateByPrimaryKey(ProjInfo record);

    /**
     * 查询楼盘列表
     * @param query
     * @return
     */
    //List<ProjInfo> selectProjInfoList(HousingProjectQuery query);

}