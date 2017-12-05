package com.toutiao.web.apiimpl.authentication;

import com.alibaba.fastjson.JSON;
//import com.toutiao.agent.dao.view.model.sys.UserRoleDatapermission;
import com.toutiao.web.common.authentication.menu;
import com.toutiao.web.dao.entity.admin.SysRoleEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * zhangjinglei 2017/9/16 下午2:08
 */
@Data
public class SerializableData implements Serializable {

    private static final long serialVersionUID = -2708313442050410935L;

    Integer UserId;
    String UserName;
    SysRoleEntity currentRole;
    List<menu> menus;

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public static SerializableData fromString(String value) {
        return JSON.parseObject(value, SerializableData.class);
    }

    public final static String GLOBAL_VERSION = "1.3";
    private String version = "";

    public boolean checkVersion() {
        if (this.version != null && this.version.equals(GLOBAL_VERSION)) {
            return true;
        }
        return false;
    }
}
