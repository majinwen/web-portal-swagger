<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>我的优惠卡</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-coupon-detail.css?v=${staticversion}">
    <script src="${staticurl}/js/code.js?v=${staticversion}"></script>
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<section>
    <div class="card-box">
        <div class="img-box">
            <#--<img src="https://bjstatic.centaline.com.cn:442/Images/20180316/082725_47e8b786-00d1-485c-9f10-a2816ca82aeb.jpg" alt="">-->
            <#if order?size gt 0>
                <#if order['comment']?exists>
                    <#assign json=order['comment']?eval />
                    <img src="${qiniuimage}/${json.buildingTitleImg}-tt400x300" alt="${json.buildingName}">
                </#if>
            </#if>
        </div>
        <div class="card-info">
            <#--<h3>香江健康小镇</h3>-->
            <#if order?size gt 0>
                <#if order['comment']?exists>
                    <#assign json=order['comment']?eval />
                    <h3>${json.buildingName}</h3>
                </#if>
                <#if order['productDetails']?exists>
                    <p>${order.productDetails}</p>
                <#else >
                    <p>暂无数据</p>
                </#if>
            </#if>
            <#--<p>优惠卡:5万抵12万</p>-->
        </div>
    </div>
</section>
<section>
    <div class="card-code-box">
        <p class="title">
        <#if order?size gt 0>
            <#if order['comment']?exists>
                <#assign json=order['comment']?eval />
                ${json.buildingName}<em> 优惠卡</em>
            </#if>
        </#if></p>
        <div class="card-code-num">
            <strong>券码：</strong>
            <#if paySuccess?size gt 0>
                <#if paySuccess['comment']?exists>
                    <p>${paySuccess['comment']}</p>
                </#if>
            </#if>
            <#--<p>1214 7206 4923 119</p>-->
        </div>
        <div class="code-img-box" id="qrCode">
            <#--<img src="https://qr.api.cli.im/qr?data=http%253A%252F%252Fcity.toutiaofangchan.com%252F%2523%252FcityManageSystem%252FagentDetail%253FagentId%253D3379%2526userId%253D3401&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=fc45f89fb42c4b4ea1d9aa1c149560cc" alt="">-->
        </div>
    </div>
    <button id="go-newHouse-hp" onclick="window.location.href='${router_city('/xinfang')}'">返回新房首页</button>
</section>
</body>
</html>

<script>

    new QRCode(document.getElementById('qrCode'), 'http://city.test.toutiaofangchan.com/v1.0.0/paycenter/updateStatusByOrderNo?orderNo=${paySuccess['orderNo']}');

</script>