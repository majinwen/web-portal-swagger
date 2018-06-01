<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>充值</title>
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
            <img src="https://bjstatic.centaline.com.cn:442/Images/20180316/082725_47e8b786-00d1-485c-9f10-a2816ca82aeb.jpg" alt="">
        </div>
        <div class="info-content">
            <p class="content-title">香江健康小镇</p>
            <p class="content-price">¥50000<span>元</span></p>
        </div>
    </div>
</section>
<section>
    <h3 class="title">付款信息</h3>
    <div class="readed"><i></i><span>我已阅读并同意</span></div>
    <div class="payment-text">
        <strong>待支付</strong>
        <span>实付款：<em>¥50000</em></span>
    </div>
    <button>确认</button>
    <p class="current-balance">当前余额：100000.00元</p>
</section>
</body>
</html>