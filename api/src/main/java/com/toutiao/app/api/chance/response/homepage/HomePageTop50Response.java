package com.toutiao.app.api.chance.response.homepage;

import lombok.Data;

@Data
public class HomePageTop50Response {
    private  Integer districtId;

    private String districtName;

    private Integer count;
}
