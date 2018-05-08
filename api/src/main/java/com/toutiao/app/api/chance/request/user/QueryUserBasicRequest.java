package com.toutiao.app.api.chance.request.user;

import com.toutiao.web.common.assertUtils.First;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class QueryUserBasicRequest {

    /**
     * 用户id
     */
    @NotEmpty(message = "缺少用户Id")
    private String userId;

    @NotEmpty(groups = {First.class},message = "缺少融云Id")
    private String rcId;
}
