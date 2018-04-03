package com.toutiao.web.domain.advertisement;

import lombok.Data;

@Data
public class RentHouseAggAdLandingDo {

    private String zufangName;

    private double houseArea;

    private Integer room;

    private String forward;

    private String districtName;

    private String areaName;

    private String rentTypeName;

    private String[] rentHouseTagsName;

    private double rentHousePrice;

    private String houseId;

    private Integer rentSign;


}
