package com.toutiao.web.dao.mapper.admin;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.admin.SysRoleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends BaseDao {
    int deleteByPrimaryKey(String code);

    int insert(SysRoleEntity record);

    int insertSelective(SysRoleEntity record);

    SysRoleEntity selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SysRoleEntity record);

    int updateByPrimaryKey(SysRoleEntity record);

    List<SysRoleEntity> selectByName(@Param("name") String name);

    List<SysRoleEntity> loginRoleList(int id);

}