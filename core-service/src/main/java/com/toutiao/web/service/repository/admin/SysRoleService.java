package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.SysRoleEntity;

import java.util.List;

public interface SysRoleService {
    List<SysRoleEntity> selectByName(String name);

    SysRoleEntity selectByCode(String code);

    int insert(SysRoleEntity record);

    int updateByPrimaryKeySelective(SysRoleEntity record);

    int deleteByPrimaryKey(String code);

    List<SysRoleEntity> loginRoleList(int id);
}
