package com.toutiao.app.service.sys.impl;

import com.toutiao.app.service.sys.IMService;
import com.toutiao.web.common.constant.syserror.SystemExceptionErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IMServiceImpl implements IMService {

    private static final Logger logger = LoggerFactory.getLogger(IMServiceImpl.class);

    @Value("${tt.rongcloud.appkey}")
    private String rongCloudAppKey;
    @Value("${tt.rongcloud.secret}")
    private String rongCloudSecret;


    @Override
    public String queryRongCloudTokenByUser(String userId, String userName, String avatar) {


        RongCloud rongCloud = RongCloud.getInstance(rongCloudAppKey, rongCloudSecret);
        User User = rongCloud.user;
        UserModel user = new UserModel()
                .setId(userId)
                .setName(userName)
                .setPortrait(avatar);
        TokenResult result = null;
        try {
            result = User.register(user);
            if(result.getCode() != 200){
                throw new BaseException(SystemExceptionErrorCodeEnum.RONGCLOUDEXCEPTION);
            }
        } catch (Exception e) {
            logger.error("调用融云服务接口异常");
            e.printStackTrace();
        }
        return result.getToken();
    }



    @Override
    public Result refreshRongCloudByUser(String userId, String userName, String avatar) {
        RongCloud rongCloud = RongCloud.getInstance(rongCloudAppKey, rongCloudSecret);
        User User = rongCloud.user;
        UserModel user = new UserModel()
                .setId(userId)
                .setName(userName)
                .setPortrait(avatar);
        Result result = null;
        try {
            result = User.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
