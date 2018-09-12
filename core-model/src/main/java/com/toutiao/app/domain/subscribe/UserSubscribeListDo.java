package com.toutiao.app.domain.subscribe;

import com.toutiao.web.common.constant.city.CityEnum;
import lombok.Data;

import java.util.Date;

@Data
public class UserSubscribeListDo {
    private Integer id;

    private Integer userId;

    private String userSubscribeMap;

    private Date createTime;

    private Date updateTime;

    private Long newCount;

    private UserSubscribeDetailDo userSubscribeDetail;

    private Object houseList;

    private Integer cityId;

    private String city;

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
        if(null!=cityId){
            this.city = CityEnum.getId(cityId);
        }
    }
}
