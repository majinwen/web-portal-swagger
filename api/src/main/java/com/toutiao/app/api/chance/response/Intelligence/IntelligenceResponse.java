package com.toutiao.app.api.chance.response.Intelligence;

import com.toutiao.app.domain.Intelligence.PriceRatioDo;
import com.toutiao.app.domain.Intelligence.PriceTrendDo;
import lombok.Data;

@Data
public class IntelligenceResponse {

    private String layout;

    private String district;

    private PriceTrendDo fhpt;

    private PriceRatioDo fhtp;

    private Integer collectStatus;

    private String backUrl;

    private Integer totalPrice;

    private String datajson;
}
