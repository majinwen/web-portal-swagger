package com.toutiao.web.service.repository.admin;

import com.toutiao.web.dao.entity.admin.SysMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 18710 on 2017/11/21.
 */

public interface SysMenuService {
    List<SysMenuEntity> selectByStatus(Integer status);

    int insertSelective(SysMenuEntity record);

    SysMenuEntity selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(SysMenuEntity record);

    int deleteByPrimaryKey(String code);
    List<SysMenuEntity> selectByRoleID(Integer role_id);
}
