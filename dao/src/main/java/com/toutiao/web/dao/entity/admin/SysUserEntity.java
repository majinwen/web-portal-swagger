package com.toutiao.web.dao.entity.admin;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserEntity implements Serializable {

    private Integer id;
    private String phone;

}