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
<#if payOrderDos?exists>
    <ul class="list-wrapper">
<#list payOrderDos as key >
        <#if key.type==1>
        <li>
            <a href="#" class="list-item">
                <div class="clear"><span class="price-type"> 充值到余额
                </span><span class="time">${key.createTime?string("yyyy-MM-dd HH:mm:ss")}</span></div>
                <div class="clear"><span></span><span class="price-sum">+${key.payMoney}</span></div>
            </a>
        </li>
       <#else>
        <li>
            <a href="#" class="list-item">
                <div class="clear"><span class="price-type">购买优惠卡</span><span class="time">${key.createTime?string("yyyy-MM-dd HH:mm:ss")}</span></div>
                <div class="clear"><span></span><span class="price-sum">-${key.payMoney}</span></div>
            </a>
        </li>
       </#if>
    </#list>
    </ul>
<#else>
<section class="empty-order">
    <div>
        <img src="${staticurl}/images/payment/payment-no-dingdan.png" alt="">
        <p>您暂时没有明細</p>
    </div>
</section>

</#if>

</body>
</html>