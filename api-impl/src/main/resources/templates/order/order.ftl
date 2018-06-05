<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>我的订单</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-order.css?v=${staticversion}">
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<#-- 判断是否有订单，没有订单时添加none -->
<section class="order-wrapper">
<#if payOrderDos?exists>
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
<#else>
</section>
<#-- 没有订单时显示，去掉none -->
<section class="empty-order none">
    <div>
        <img src="${staticurl}/images/payment/payment-no-dingdan.png" alt="">
        <p>您暂时没有订单</p>
    </div>
</section>
</#if>
</body>
</html>