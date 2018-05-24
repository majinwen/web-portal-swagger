package com.toutiao.web.common.constant.syserror;

import com.toutiao.web.common.constant.base.IntBaseType;

public enum RestfulInterfaceErrorCodeEnum implements IntBaseType {


    RONGCLOUDEXCEPTION("rongCloud服务异常",110001),
    IMAGE_UPLOAD_FAIL("图片上传失败", 110002),
    UPDATE_USER_AVATAR_RONGCLOUD_ERROR("RongCloud更新用户头像异常",110003),
    IMAGE_SIZE_BEYOND_LIMIT("图片大小超出限制", 110004);

    private String desc;
    private int value;

    RestfulInterfaceErrorCodeEnum(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }


    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return null;
    }
}
