<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>个人中心</title>
    <meta name="description" content="懂房帝 买房秒懂">
    <meta name="keyword" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-center.css?v=${staticversion}">
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<section>
    <div class="balance-title">
        <p>余额账户(元)</p>
        <#if money?exists>
        <strong>${money?string("#.##")}</strong>
        </#if>
    </div>
    <ul class="list-box">
        <li><a href="${router_city('/order/recharge?type=1&totalMoney='+money?string("#.##"))}" class="list-item">
            <p><i class="chongzhi"></i><span>充值</span></p>
            <i class="arrows"></i>
        </a></li>
        <li><a href="${router_city('/payOrder/order/getMyCharge')}" class="list-item">
            <p><i class="mingxi"></i><span>明细</span></p>
            <i class="arrows"></i>
        </a></li>
    </ul>
    <ul class="list-box">
        <li><a href="${router_city('/payOrder/order/getMyOrder')}" class="list-item">
            <p><i class="goumai"></i><span>我的订单</span></p>
            <i class="arrows"></i>
        </a></li>
    </ul>
    <#--<p class="tip-tel">如需退款请拨打：<a href="tel:400000000">400-99999</a></p>-->
</section>
</body>
</html>