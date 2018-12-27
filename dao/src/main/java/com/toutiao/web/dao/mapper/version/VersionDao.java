package com.toutiao.web.dao.mapper.version;

import com.toutiao.web.dao.entity.version.VersionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by CuiShihao on 2018/12/27
 */
@Repository
public interface VersionDao {

    /**
     * 验证APP是否有新版本
     * @param type
     * @return
     */
    VersionVO getIsNewAppVersion(@Param("type") Integer type);
}
