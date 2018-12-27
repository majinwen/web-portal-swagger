package com.toutiao.app.service.version.impl;

import com.toutiao.web.dao.entity.version.VersionVO;
import com.toutiao.app.service.version.VersionService;
import com.toutiao.web.dao.mapper.version.VersionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CuiShihao on 2018/12/27
 */
@Service
public class VersionServiceImpl implements VersionService {

    @Autowired
    private VersionDao versionDao;

    @Override
    public VersionVO getIsNewAppVersion(Integer type, Integer version) {
        VersionVO versionVO = versionDao.getIsNewAppVersion(type);
        if (versionVO.getIsNew().equals(version)) {
            versionVO.setIsNew(0);
            versionVO.setUrl("");
        }
        return versionVO;
    }
}
