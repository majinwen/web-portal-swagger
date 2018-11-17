package com.toutiao.app.domain.Intelligence;

import lombok.Data;

import java.util.List;

@Data
public class PriceTrendDo {

    private Double minTarget;

    private Double maxTarget;

    private Double target;

    private List ptlists;

    private Integer searchPrice;

}
