package com.toutiao.app.domain.subscribe;

import com.toutiao.web.common.constant.city.CityEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserSubscribeListDo {
    private Integer id;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("用户订阅字典")
    private String userSubscribeMap;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("新增数量")
    private Long newCount;

    private UserSubscribeDetailDo userSubscribeDetail;

    @ApiModelProperty("房源详情,列表页返回")
    private Object houseList;

    @ApiModelProperty("城市ID")
    private Integer cityId;

    @ApiModelProperty("城市名称")
    private String city;

    @ApiModelProperty("类型0-房源专题订阅，3-排行榜订阅")
    private Integer subscribeType;

    @ApiModelProperty(value = "跳转地址", name = "url")
    private String url;

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
        if(null!=cityId){
            this.city = CityEnum.getId(cityId);
        }
    }
}
