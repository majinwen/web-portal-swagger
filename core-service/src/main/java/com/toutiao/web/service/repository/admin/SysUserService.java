package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.SysUserEntity;

import java.util.List;

/**
 * Created by 18710 on 2017/11/21.
 */
public interface SysUserService {
    int deleteByPrimaryKey(Integer id);
    SysUserEntity  selectByPhone(String phone);
    int insertSelective(SysUserEntity record);
    SysUserEntity selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(SysUserEntity record);
    List<SysUserEntity> selectByKey(SysUserEntity record);

    int insertPhone(String phone);
}
