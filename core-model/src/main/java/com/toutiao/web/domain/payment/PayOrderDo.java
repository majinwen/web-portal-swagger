package com.toutiao.web.domain.payment;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class PayOrderDo {
    /**
     * 第三方邮箱
     */
    private  String buyerEmail;

    /**
     *第三方账号
     */
     private  String buyerId;

    /**
     *  备注
     */
    private String comment;

    /**
     * 创建人编号
     */
    private  String createId;

    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 失效时间
     */
    private  Date  failureTime;

    /**
     * 主键
     */
    private  Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 支付时间
     */
    private  Date  payAt;

    /**
     *支付完成时间
     */
     private  Date payFinishTime;

    /**
     *支付金额
     */
    private  Double payMoney;

    /**
     *支付状态(0未支付 1成功 2失败)
     */
    private  Integer payStatus;


    /**
     *支付类型(1支付宝 2微信)
     */
    private  Integer payType;

    /**
     *电话
     */
    private  String phone;

    /**
     *商品详情
     */
    private  String productDetails;

    /**
     * 商品编号
     */
    private  String productNo;

    /**
     * 订单状态(0处理中 1已成交 2未成交)
     */
    private  Integer status;


    /**
     * 第三方交易号
     */
    private  String tradeNo;

    /**
     *订单类型(1充值 2消费)
     */
    private  Integer type;

    /**
     *修改人编号
     */
    private  Integer updateId;

    /**
     * 修改时间
     */
    private  Date  updateTime;


    /**
     *用户id
     */
    private  Integer userId;

    /**
     *用户姓名
     */
    private  String  userName;

    private  CommentDo commentDo;
}
