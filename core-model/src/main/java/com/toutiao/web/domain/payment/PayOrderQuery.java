package com.toutiao.web.domain.payment;

import lombok.Data;

@Data
public class PayOrderQuery {
    private  Integer pageNum=1;

    private  Integer pageSize=5;
}
