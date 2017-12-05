package com.toutiao.web.dao.mapper.admin;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.admin.SysMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends BaseDao {
    int deleteByPrimaryKey(String code);

    int insert(SysMenuEntity record);

    int insertSelective(SysMenuEntity record);

    SysMenuEntity selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SysMenuEntity record);

    int updateByPrimaryKey(SysMenuEntity record);

    List<SysMenuEntity> selectByStatus(@Param("status")Integer status);
    List<SysMenuEntity> selectByRoleID(@Param("role_id")Integer role_id);

}