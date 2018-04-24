package com.toutiao.app.service.user.impl;

import com.qiniu.storage.model.DefaultPutRet;
import com.toutiao.app.domain.user.UserBasicDo;
import com.toutiao.app.service.sys.IMService;
import com.toutiao.app.service.user.UserBasicInfoService;
import com.toutiao.web.common.constant.syserror.RestfulInterfaceErrorCodeEnum;
import com.toutiao.web.common.constant.syserror.UserInterfaceErrorCodeEnum;
import com.toutiao.web.common.exceptions.BaseException;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.util.StringTool;
import com.toutiao.web.common.util.UploadUtil;
import com.toutiao.web.dao.entity.officeweb.user.UserBasic;
import com.toutiao.web.dao.mapper.officeweb.user.UserBasicMapper;
import io.rong.models.Result;
import io.rong.models.response.TokenResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserBasicInfoServiceImpl implements UserBasicInfoService{

    @Autowired
    private UserBasicMapper userBasicMapper;
    @Autowired
    private IMService imService;

    /**
     * 更新用户头像
     * @param userId
     * @param file
     * @return
     */
    @Override
    public UserBasicDo updateUserAvatar(String userId, MultipartFile file) {

        InvokeResult invokeResult = UploadUtil.uploadImages(file);
        UserBasic userBasic = new UserBasic();
        UserBasicDo userBasicDo = new UserBasicDo();
        if (null != ((DefaultPutRet) invokeResult.getData()).key) {

            userBasic.setAvatar(((DefaultPutRet) invokeResult.getData()).key);
            userBasic.setUserId(userId);
            int res = userBasicMapper.updateByPrimaryKeySelective(userBasic);
            if(res == 1){
                userBasic = userBasicMapper.selectByPrimaryKey(userId);
                //把更新的图片上传到融云
                Result rcToken = imService.refreshRongCloudByUser(userBasic.getUserOnlySign(),userBasic.getUserName(),userBasic.getAvatar());
                if(rcToken==null && rcToken.getCode()!=200){
                    throw new BaseException(RestfulInterfaceErrorCodeEnum.UPDATE_USER_AVATAR_RONGCLOUD_ERROR,"RongCloud更新用户头像异常");
                }

            }else{
                throw new BaseException(UserInterfaceErrorCodeEnum.UPDATE_USER_AVATAR_ERROR,"更新用户头像信息失败");
            }
        }
        BeanUtils.copyProperties(userBasic,userBasicDo);
        return userBasicDo;
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @Override
    public UserBasicDo queryUserBasic(String userId) {

        UserBasic userBasic = userBasicMapper.selectByPrimaryKey(userId);
        UserBasicDo userBasicDo = new UserBasicDo();
        if(StringTool.isBlank(userBasic)){
            throw new BaseException(UserInterfaceErrorCodeEnum.QUERY_USER_BASIC_ERROR,"用户不存在");
        }
        BeanUtils.copyProperties(userBasic,userBasicDo);
        return userBasicDo;
    }

    /**
     * 根据用户电话查询用户信息
     * @param phone
     * @return
     */
    @Override
    public UserBasicDo queryUserBasicByPhone(String phone) {
        UserBasic userBasic = new UserBasic();
        UserBasicDo userBasicDo = new UserBasicDo();
        userBasic.setPhone(phone);
        userBasic = userBasicMapper.selectUserByPhone(userBasic);
        if(null!=userBasic){
            BeanUtils.copyProperties(userBasic,userBasicDo);
        }
        userBasicDo = null;
        return userBasicDo;
    }

    @Override
    public int addUserBasic(UserBasic userBasic) {

        return  userBasicMapper.insertSelective(userBasic);
    }
}
