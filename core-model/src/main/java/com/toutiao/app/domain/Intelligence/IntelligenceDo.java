package com.toutiao.app.domain.Intelligence;

import lombok.Data;

@Data
public class IntelligenceDo {

    private String layout;

    private String district;

    private PriceTrendDo fhpt;

    private PriceRatioDo fhtp;

    private Integer collectStatus;

    private String backUrl;

    private Integer totalPrice;

    private String datajson;
}
