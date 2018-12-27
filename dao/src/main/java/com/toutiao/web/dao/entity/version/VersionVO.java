package com.toutiao.web.dao.entity.version;

import lombok.Data;

/**
 * Created by CuiShihao on 2018/12/27
 */
@Data
public class VersionVO {

    /**
     * 是否有新版本（0-没有  其他表示对应的版本号）
     */
    private Integer isNew;

    /**
     * 版本对应的下载地址
     */
    private String url;
}
