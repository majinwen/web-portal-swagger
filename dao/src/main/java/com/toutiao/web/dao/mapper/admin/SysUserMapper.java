package com.toutiao.web.dao.mapper.admin;

import com.toutiao.web.dao.BaseDao;
import com.toutiao.web.dao.entity.admin.SysUserEntity;

import java.util.List;

public interface SysUserMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserEntity record);

    int insertSelective(SysUserEntity record);

    SysUserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserEntity record);

    int updateByPrimaryKey(SysUserEntity record);

    List<SysUserEntity> selectByKey(SysUserEntity record);

    SysUserEntity  selectByPhone(String phone);

    int insertPhone(String phone);
}