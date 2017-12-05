package com.toutiao.web.dao.entity.admin;

import lombok.Data;

import java.util.Date;

@Data
public class SysRoleEntity {
    private String code;

    private String name;

    private Integer createId;

    private Date createTime;

    private Integer modifyId;

    private Date modifyTime;

    private Integer id;


}