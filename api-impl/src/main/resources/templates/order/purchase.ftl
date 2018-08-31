<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>购买</title>
    <meta name="description" content="懂房帝 买房秒懂">
    <meta name="keywords" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-purchase.css?v=${staticversion}">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script src="${staticurl}/js/main.js?v=${staticversion}"></script>
    <script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
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
                    <#if json.buildingTitleImg?exists>
                        <img src="${qiniuimage}/${json.buildingTitleImg}-tt400x300" alt="${json.buildingName}">
                    <#else >
                        <img src="${staticurl}/images/global/tpzw_image.png" alt="${json.buildingName}">
                    </#if>
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
    <div><div class="readed"><i class="active"></i><span>我已阅读并同意</span></div><a class="protocol" href="http://m.toutiaofangchan.com/ad/ldy/xieyi.html">《比得屋优惠服务说明》</a></div>
    <div class="payment-text">
        <strong>待支付</strong>
        <span>实付款：<em>¥${commodityOrder['payMoney']}</em></span>
    </div>
    <#if balance?exists && balance?size gt 0>
        <#if balance['balance'] gte commodityOrder['payMoney']>
            <button class="payment-btn" id="purchase">确认</button>
        <#else >
            <button class="payment-btn">充值</button>
        </#if>
        <p class="current-balance">当前余额：${balance['balance']?string("#.#######")}元</p>
    <#else >
        <button class="payment-btn">充值</button>
        <p class="current-balance">当前余额：0元</p>
    </#if>
</section>
</body>
</html>
<script>
    $(function () {
        $('.readed').on('click', function () {
            $(this).find('i').toggleClass('active');

            if (!$(this).find('i').hasClass('active')) {
                $('.payment-btn').unbind('click');
                $('.payment-btn').addClass('undisable');
                console.log('取消绑定')
            } else {
                $('.payment-btn').removeClass('undisable');
                paymentFn();
            }
        });

        if (!$('.readed i').hasClass('active')) {
            $('.payment-btn').unbind('click');
            $('.payment-btn').addClass('undisable');
        } else {
            $('.payment-btn').removeClass('undisable');
            paymentFn();
        }
        
        function paymentFn() {
            <#if balance?exists && balance?size gt 0>
                <#if balance['balance'] gte commodityOrder['payMoney']>
                    $('.payment-btn').on('click', function () {
                        $.ajax({
                            type:"get",
                            contentType: 'application/json',
                            url: router_city('/order/paymentCommodityOrder')+ "?orderNo=" + ${commodityOrder['orderNo']},
                            async: true,
                            dataType: "json",
                            success: function (data) {
                                console.log(JSON.stringify(data.data.code));
                                if (JSON.parse(data.data).code == "success") {
                                    window.location.href = '${router_city('/order/orderDetails?orderNo=' + commodityOrder['orderNo'])}'
                                } else if(JSON.parse(data.data).code == "fail"){
                                    window.location.href = '${router_city('/payOrder/order/getMyOrder')}'
                                }
                            }
                        })
                    });
                <#else >
                    $('.payment-btn').on('click', function () {
                        window.location.href='${router_city('/order/recharge?type=1&totalMoney='+balance['balance']?string("#.#######"))}'
                    });
                </#if>
            <#else >
                $('.payment-btn').on('click', function () {
                    window.location.href='${router_city('/order/recharge?type=1&totalMoney=0')}'
                });
            </#if>
        }
    });
</script>