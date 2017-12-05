package com.toutiao.web.service.repository.admin.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.web.dao.entity.admin.SysMenuEntity;
import com.toutiao.web.dao.mapper.admin.SysMenuMapper;
import com.toutiao.web.service.repository.admin.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 18710 on 2017/11/21.
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public List<SysMenuEntity> selectByStatus(Integer status) {
//        PageHelper.startPage(1,10000);
        return menuMapper.selectByStatus(status);
    }

    @Override
    public int insertSelective(SysMenuEntity record) {
        return menuMapper.insertSelective(record);
    }

    @Override
    public SysMenuEntity selectByPrimaryKey(String code) {
        return menuMapper.selectByPrimaryKey(code);
    }

    @Override
    public int updateByPrimaryKeySelective(SysMenuEntity record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String code) {
        return menuMapper.deleteByPrimaryKey(code);
    }

    @Override
    public List<SysMenuEntity> selectByRoleID(Integer role_id) {
        return menuMapper.selectByRoleID(role_id);
    }
}
