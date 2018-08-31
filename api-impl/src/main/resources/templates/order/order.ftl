<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>我的订单</title>
    <meta name="description" content="懂房帝 买房秒懂">
    <meta name="keywords" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-order.css?v=${staticversion}">
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<#-- 判断是否有订单，没有订单时添加none -->
<#if payOrderDos?exists>
<section class="order-wrapper">

    <#list payOrderDos as key>
        <a href="#" class="order-item-wrapper">
        <div class="order-item">
            <div class="img-box">
                <#if key.commentDo.buildingTitleImg!='' >
                <img src="${qiniuimage}/${key.commentDo.buildingTitleImg}-tt400x300" alt="">
                <#else>
                    <img src="${staticurl}/images/global/tpzw_image.png" alt="">
                </#if>
            </div>
            <div class="order-content">
                <div>
                    <p class="title">${key.commentDo.buildingName}</p>
                    <p class="price">¥${key.payMoney}<span>元</span></p>
                </div>
                <#if key.status==1>
                <div class="link-btn" onclick="window.location.href='${router_city('/order/orderDetails?orderNo='+key.orderNo)}'">查看</div>
                <#else>
                    <div class="link-btn unfinished"  onclick="window.location.href='${router_city('/order/buildCommodityOrder?productNo='+key.productNo+'&orderNo='+key.orderNo)}'">支付</div>
                </#if>
            </div>
        </div>
    </a>
    </#list>
</section>
<#else>
<#-- 没有订单时显示，去掉none -->
<section class="empty-order">
    <div>
        <img src="${staticurl}/images/payment/payment-no-dingdan.png" alt="">
        <p>您暂时没有订单</p>
    </div>
</section>
</#if>
</body>
</html>