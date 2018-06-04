<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>购买</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-purchase.css?v=${staticversion}">
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<section>
    <h3 class="title">楼盘信息</h3>
    <div class="card-info">
        <div class="img-box">
            <#if commodityOrder?size gt 0>
                <#if commodityOrder['comment']?exists>
                    <#assign json=commodityOrder['comment']?eval />
                    <img src="${qiniuimage}/${json.buildingTitleImg}-tt400x300" alt="${json.buildingName}">
                </#if>
            </#if>
        </div>
        <div class="info-content">

        <#if commodityOrder?size gt 0>
            <#if commodityOrder['comment']?exists>
                <#assign json=commodityOrder['comment']?eval />
                <p class="content-title">${json.buildingName}</p>
            </#if>
        </#if>
            <p class="content-price">¥${commodityOrder['payMoney']}<span>元</span></p>
        </div>
    </div>
</section>
<section>
    <h3 class="title">付款信息</h3>
    <div class="readed"><i></i><span>我已阅读并同意</span></div>
    <div class="payment-text">
        <strong>待支付</strong>
        <span>实付款：<em>¥${commodityOrder['payMoney']}</em></span>
    </div>
    <button>确认</button>
    <p class="current-balance">当前余额：${balance['balance']}元</p>
</section>
</body>
</html>