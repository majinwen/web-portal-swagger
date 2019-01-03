package com.toutiao.app.service.version;

import com.toutiao.web.dao.entity.version.VersionVO;

/**
 * Created by CuiShihao on 2018/12/27
 */
public interface VersionService {

    /**
     * 验证APP是否有新版本
     * @param type
     * @param version
     * @return
     */
    VersionVO getIsNewAppVersion(Integer type, Integer version);
}
