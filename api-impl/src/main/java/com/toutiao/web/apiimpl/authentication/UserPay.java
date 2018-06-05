package com.toutiao.web.apiimpl.authentication;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserPay {
    private UserPay(){}
    private Integer userId;
    private  String userName;


    public static UserPay getCurrent( ){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


             UserPay user=new UserPay();
            String cookies = (String) request.getAttribute("userLogin");
            if ("请登录".equals(cookies))
            {
                 return user;
            }
            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<String, Object>();
            map = gson.fromJson(cookies, map.getClass());

            user.setUserId(Integer.valueOf(map.get("userId").toString()));
            String userName=map.get("userOnlySign").toString();
            user.setUserName(userName.substring(userName.length()-11,userName.length()));
            return user;
    }
}
