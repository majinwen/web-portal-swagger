package com.toutiao.web.service.repository.admin.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.web.dao.entity.admin.SysRoleEntity;
import com.toutiao.web.dao.mapper.admin.SysRoleMapper;
import com.toutiao.web.service.repository.admin.SysRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public List<SysRoleEntity> selectByName(String name){
        PageHelper.startPage(1,10000);
        name = StringUtils.strip(name);
        return sysRoleMapper.selectByName(name);
    }

    @Override
    public SysRoleEntity selectByCode(String code){
        return sysRoleMapper.selectByPrimaryKey(code);
    }

    @Override
    public int insert(SysRoleEntity record) {
        return sysRoleMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRoleEntity record) {
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String code) {
        return sysRoleMapper.deleteByPrimaryKey(code);
    }

    @Override
    public List<SysRoleEntity> loginRoleList(int id) {
        return sysRoleMapper.loginRoleList(id);
    }


}
