package com.toutiao.web.apiimpl.authentication;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * zhangjinglei 2017/8/31 下午5:29
 */
public class User {

    private User(){}

    private Integer userId=0;
    private String userName="";
    private SerializableData data;

    public Integer getUserId() {
        return userId;
    }

    private void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }



    /**
     * 返回当前角色的功能权限项
     * @return
     */
    public SerializableData getSerializableData(){
        return this.data;
    }




    public static User getCurrent( ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        User user = (User) request.getAttribute("_apollouser_");
        if(user==null){
            SerializableData serializabledata_ = (SerializableData) request.getAttribute("_serializabledata_");
            user = new User();
            user.setUserId(serializabledata_.getUserId());
            user.setUserName(serializabledata_.getUserName());
            user.data = serializabledata_;
        }
        return user;
    }

}
