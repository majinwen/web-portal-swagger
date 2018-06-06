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
<section id="putong">
    <p class="remaining-sum">可用余额：<em>${recharge['totalMoney']}</em></p>
    <div class="input-sum">
        <input type="tel"  class="key-words" placeholder="充值金额(元)" onkeyup="onlyNumber(this)" >
    </div>
    <ul class="payment-method">
        <li onclick="alipay()">
            <a href="#" class="method-item">
                <img src="${staticurl}/images/payment/payment-zhifubao.png" alt="支付宝">
                <div>
                    <p>支付宝支付</p>
                    <span>支付宝安全支付</span>
                </div>
                <i></i>
            </a>
        </li>
        <li>
            <a href="#" class="method-item">
                <img src="${staticurl}/images/payment/payment-weixin.png" alt="微信">
                <div>
                    <p>微信支付</p>
                    <span>微信安全支付</span>
                </div>
                <i></i>
            </a>
        </li>
    </ul>
</section>
<section id="weixin" class="none">
    <div class="J-weixin-tip weixin-tip">
        <div class="weixin-tip-content">
            <img src="${staticurl}/images/payment/iphone-broswer.png" class="iphone-browser none">
            <img src="${staticurl}/images/payment/android-browser.png" class="android-browser none">
            <p>请在菜单中选择在浏览器中打开,<br/>
            以完成支付</p>
        </div>
    </div>
</section>
<script>
    $(function () {
        var WxObj=window.navigator.userAgent.toLowerCase();
        if(WxObj.match(/microMessenger/i)=='micromessenger'){
            $('#putong').addClass('none')
            $('#weixin').removeClass('none')
            if (WxObj.indexOf('iphone') != -1 || WxObj.indexOf('ipad') != -1 || WxObj.indexOf('ipod') != -1) {
                // 替换浏览器图标 iphone
                $('.iphone-browser').removeClass('none')
                $('.android-browser').addClass('none')
            } else {
                // 替换浏览器图标 android
                $('.android-browser').removeClass('none')
                $('.iphone-browser').addClass('none')
            }
        }

    })
    var type = "${recharge['type']}"  //订单类型
    var productNo = "${recharge['productNo']}"  //商品编号
    var productDetails = "${recharge['productDetails']}"  //商品详情
    var _keyword = 0;
    var url = "";
    $('.key-words').bind('input',function () {
         _keyword = $('.key-words').val()||0;
         if(_keyword>50000){
             _keyword = 50000;
             $('.key-words').val(50000)
         }
        url = "http://192.168.1.110:8085/bj/order/payment?payType=1&payMoney="+_keyword+"&type="+type+"&productNo="+productNo/*+"&productDetails="+productDetails*/;
         console.log(url)
    })

    function onlyNumber(obj){

        //得到第一个字符是否为负号
        var t = obj.value.charAt(0);
        //先把非数字的都替换掉，除了数字和.
        obj.value = obj.value.replace(/[^\d\.]/g,'');
        //必须保证第一个为数字而不是.
        obj.value = obj.value.replace(/^\./g,'');
        //保证只有出现一个.而没有多个.
        obj.value = obj.value.replace(/\.{2,}/g,'.');
        //保证.只出现一次，而不能出现两次以上
        obj.value = obj.value.replace('.','$#$').replace(/\./g,'').replace('$#$','.');

        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
        //如果第一位是负号，则允许添加
        if(t == '-'){

            return;
        }

    }

    function alipay() {
        if (_keyword>0&&url!=''){
            window.location.href =url;
        }
    }
</script>
</body>
</html>