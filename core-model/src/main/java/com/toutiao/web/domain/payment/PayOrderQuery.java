package com.toutiao.web.domain.payment;
import com.toutiao.web.common.assertUtils.First;
import com.toutiao.web.common.assertUtils.Second;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class PayOrderQuery {
    private  Integer pageNum=1;
    private  Integer pageSize=500;

}
