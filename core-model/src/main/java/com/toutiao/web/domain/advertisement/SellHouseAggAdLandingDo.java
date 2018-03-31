package com.toutiao.web.domain.advertisement;

import lombok.Data;

@Data
public class SellHouseAggAdLandingDo {

    private String houseId;
    private String housePhotoTitle;
    private Double houseTotalPrices;
    private String[] housePhoto;
    private Integer room;
    private Integer hall;
    private Double buildArea;
    private String plotName;
    private String forwardName;
    private String houseTitle;
    private String[] tagsName;
    private String housePlotLocation;
    private String houseBusinessName;
    private String area;
    private String traffic;
    private Integer pageNum;
    private Long adSort;


}
