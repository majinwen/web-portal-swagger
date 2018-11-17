package com.toutiao.app.domain.Intelligence;

import com.toutiao.web.domain.intelligenceFh.IntelligenceFhTdRatio;
import lombok.Data;

import java.util.List;

@Data
public class PriceRatioDo {

    private Integer searchPrice;

    private List trend;

    private IntelligenceFhTdRatio ratio;
}
