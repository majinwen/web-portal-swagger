<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <title>充值</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <link rel="stylesheet" href="${staticurl}/css/payment-recharge.css?v=${staticversion}">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<section>
    <p class="remaining-sum">可用余额：<em>${recharge['totalMoney']}</em></p>
    <div class="input-sum">
        <input type="tel"  class="key-words" placeholder="充值金额(元)">
    </div>
    <ul class="payment-method">
        <li onclick="alipay()">
            <a href="#" class="method-item">
                <img src="/static/images/payment/payment-zhifubao.png" alt="支付宝">
                <div>
                    <p>支付宝支付</p>
                    <span>支付宝安全支付</span>
                </div>
                <i></i>
            </a>
        </li>
        <li>
            <a href="#" class="method-item">
                <img src="/static/images/payment/payment-weixin.png" alt="微信">
                <div>
                    <p>微信支付</p>
                    <span>微信安全支付</span>
                </div>
                <i></i>
            </a>
        </li>
    </ul>
</section>
<script>
    var type = ${recharge['type']};  //订单类型
    var productNo = ${recharge['productNo']};  //商品编号
    var productDetails = ${recharge['productDetails']};  //商品详情
    var _keyword = 0;
    var url = "http://192.168.1.110:8085/bj/order/payment?payType=1&payMoney="+_keyword+"&type="+type+"&productNo="+productNo+"&productDetails="+productDetails;
    $('.key-words').bind('input',function () {
         _keyword = $('.key-words').val()||0;
        url = "http://192.168.1.110:8085/bj/order/payment?payType=1&payMoney="+_keyword+"&type="+type+"&productNo="+productNo+"&productDetails="+productDetails;
    })

    function alipay() {
        window.location.href =url;
    }
</script>
</body>
</html>