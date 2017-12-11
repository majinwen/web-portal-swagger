package com.toutiao.web.service.repository.admin.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.web.dao.entity.admin.SysUserEntity;
import com.toutiao.web.dao.mapper.admin.SysUserMapper;
import com.toutiao.web.service.repository.admin.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 18710 on 2017/11/21.
 */
@Service
public class SysUserServiceImpl implements SysUserService{
    @Autowired
    SysUserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysUserEntity  selectByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public int insertSelective(SysUserEntity record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public SysUserEntity selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUserEntity record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<SysUserEntity> selectByKey(SysUserEntity record) {
        PageHelper.startPage(1,10000);
        return userMapper.selectByKey(record);
    }

    @Override
    public int insertPhone(String phone) {
        return userMapper.insertPhone(phone);
    }
}
