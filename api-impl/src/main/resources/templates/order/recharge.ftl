<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>充值</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-recharge.css?v=${staticversion}">
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<section>
    <p class="remaining-sum">可用余额：<em>20000000.00元</em></p>
    <div class="input-sum">
        <input type="tel" placeholder="充值金额(元)">
    </div>
    <ul class="payment-method">
        <li>
            <a href="#" class="method-item">
                <img src="/static/images/payment/payment-zhifubao.png" alt="支付宝">
                <div>
                    <p>支付宝支付</p>
                    <span>支付宝安全支付</span>
                </div>
                <i></i>
            </a>
        </li>
        <li>
            <a href="#" class="method-item">
                <img src="/static/images/payment/payment-weixin.png" alt="微信">
                <div>
                    <p>微信支付</p>
                    <span>微信安全支付</span>
                </div>
                <i></i>
            </a>
        </li>
    </ul>
</section>
</body>
</html>