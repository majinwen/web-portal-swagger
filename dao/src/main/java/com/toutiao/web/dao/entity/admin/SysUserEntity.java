package com.toutiao.web.dao.entity.admin;

import lombok.Data;

import java.util.Date;

@Data
public class SysUserEntity {
    private Integer id;

    private String loginName;

    private String password;

    private String userName;

    private Integer createId;

    private Date createTime;

    private Integer modifyId;

    private Date modifyTime;


}