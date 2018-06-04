<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>收支明细</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-detailed.css?v=${staticversion}">
    <#include "../StatisticsHeader.ftl">
</head>
<body>
    <ul class="list-wrapper">
        <li>
            <a href="#" class="list-item">
                <div class="clear"><span class="price-type">线下退款</span><span class="time">2018-05-29 12:00</span></div>
                <div class="clear"><span>余额：1000</span><span class="price-sum">-2000</span></div>
            </a>
        </li>
        <li>
            <a href="#" class="list-item">
                <div class="clear"><span class="price-type">购买优惠卡</span><span class="time">2018-05-29 12:00</span></div>
                <div class="clear"><span>余额：1000</span><span class="price-sum">-1000</span></div>
            </a>
        </li>
        <li>
            <a href="#" class="list-item">
                <div class="clear"><span class="price-type">充值到余额</span><span class="time">2018-05-29 12:00</span></div>
                <div class="clear"><span>余额：1000</span><span class="price-sum">+1000</span></div>
            </a>
        </li>
    </ul>
</body>
</html>