package com.toutiao.app.api.chance.request.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class UploadUserAvatarRequest {

    /**
     * 图片文件
     */
    @NotNull(message = "缺少图片文件")
    private MultipartFile file;

    /**
     * 用户id
     */
    @NotEmpty(message = "缺少用户Id")
    private String userId;
}
