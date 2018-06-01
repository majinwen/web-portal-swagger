package com.toutiao.web.domain.payment;
import com.toutiao.web.common.assertUtils.First;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class PayOrderQuery {

    @NotNull(groups ={First.class},message = "订单号不为空")
    private String outTradeNo;
    private  Integer pageNum=1;

    private  Integer pageSize=500;
}
