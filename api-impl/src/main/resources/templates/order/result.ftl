<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>充值</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-result.css?v=${staticversion}">
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<section class="result-wrapper">
    <#-- 支付成功 点击完成到我的订单列表页 -->
    <div class="">
        <img src="/static/images/payment/payment-chengong.png" alt="成功">
        <p>支付完成</p>
        <button onclick="newhouse()">完成</button>
    </div>
    <#-- 支付失败 点击返回到我的订单列表页 -->
    <div class="none">
        <img src="/static/images/payment/payment-shibai.png" alt="失败">
        <p>支付失败</p>
        <button>返回</button>
    </div>
    <#-- 购买失败 点击返回到我的订单列表页 -->
    <div class="none">
        <img src="/static/images/payment/payment-shibai.png" alt="失败">
        <p>购买失败</p>
        <button>返回</button>
    </div>
    <#-- 购买成功 点击返回到我的优惠卡(coupon) -->
    <div class="none">
        <img src="/static/images/payment/payment-shibai.png" alt="失败">
        <p>购买失败</p>
        <button>返回</button>
    </div>
</section>
<script>
    function newhouse() {
        window.location.href = "http://m.toutiaofangchan.com/bj/xinfang";
    }
</script>
</body>
</html>