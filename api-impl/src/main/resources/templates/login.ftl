<!DOCTYPE html>
<html>
<head>
    <#include "staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/login.css">
    <title>登录页</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<h2>手机快捷登录</h2>
<p class="registration-prompt">（未注册过的手机号将自动创建账号）</p>
<form action="/user/tologin" id="myform" class="login-form" method="post">
    <input type="hidden" name="count" id="count" value="<#if count?exists>${count}<#else >0</#if>">
    <div class="input-phone">
        <input type="tel" maxlength="11" id="user_phone" name="phone" placeholder="请输入手机号"
               value="<#if phone?exists>${phone}</#if>"
               onkeyup="this.value=this.value.replace(/[^0-9]*$/,'') ">
        <div class="code-btn-box">
            <span class="code-btn" id="phone_code">获取验证码</span>
            <span class="code-btn disabled none"><em class="time-number">120</em> 重新获取</span>
        </div>
    </div>
    <div class="code-pic-box">
        <input type="text" class="input_css" id="image_code" name="imageCode" maxlength="4"
               value="<#if imageCode?exists>${imageCode}</#if>" placeholder="请输入图片验证码">
        <div id="infoDiv" class="show"></div>
        <a href="javascript:changeImg();">
            <img width="200" height="80" class="code-btn-box" id="checkcode" src="/code/imageCode"/>
        </a>
    </div>
    <input class="code-text" type="number" id="mycode" name="code" maxlength="4" placeholder="请输入验证码">
    <span id="message" class="code-text-tips"><#if message?exists>${message}</#if></span>
    <button type="button" id="log_in_button" class="submit-btn">登录</button>
</form>
<p class="fixed-bottom-tips">注册/登录即代表同意《头条房产用户使用协议》</p>
<script>
    $(document).ready(function () {
        $("#image_code").val('');
        $("#infoDiv").html('');
        var count = $("#count").val();
        if (count <= 3) {
            $(".code-pic-box").hide();
        } else {
            $(".code-pic-box").show();
        }
    });
    //获取短信验证码
    $('.code-btn-box').on('click', '#phone_code', function () {
        var bo = false;
        var msgInfo = '';

        var user_phone = $("#user_phone").val();

        if (user_phone.trim() == null || user_phone.trim() == '') {

            msgInfo += "输入的手机号不能为空！<br/>";
            bo = true;
        }
        if (user_phone.match("^[0](13|15)[0-9]{9}$")) {
            msgInfo += "输入的手机号格式不符！<br/>";
            bo = true;
        }
        if (bo) {
            $("#message").html(msgInfo);
        } else {
            var seconds = 120;
            $('#phone_code').addClass('none');
            $('.disabled').removeClass('none');
            timer = setInterval(function () {
                seconds -= 1;
                $('.time-number').text(seconds);
                if (seconds <= 0) {
                    clearInterval(timer);
                    $('#phone_code').removeClass('none');
                    $('.disabled').addClass('none')
                }
            }, 1000);

            $.ajax({
                type: "post",
                url: "/message/getCode",
                data: {phone: user_phone},
                async: false,
                success: function (data1) {
                    //获取异步调用的数据
                    if (data1.code == 'fail') {
                        $("#message").html(data1.msg);
                    }
                    if (data1.code == 'success') {
                        //发送成功
                        $("#count").val(data1.data);
                        if (data1.data > 3) {
                            $(".code-pic-box").show();
                        }
                    }
                }
            });
        }
    });
    //切换图片
    function changeImg() {
        $("#image_code").val('');
        $("#infoDiv").html('');
        $("#checkcode").attr("src", "/code/imageCode?date=" + new Date().getTime());
    };

    //验证码输入框失去焦点验证验证码是否正确
    $("#image_code").blur(function () {

        var code = $("#image_code").val();

        //输入验证码后立即验证
        var code = $("#image_code").val();

        if (code == null || code == '') {
            $("#infoDiv").html('');
        } else {
            if (code.length == 4) {
                setTimeout(valideteCode, 100);
            }
        }
    });

    //验证验证码是否正确
    function valideteCode() {
        var code = $("#image_code").val();
        if (code != '') {
            $.ajax({
                type: "post",
                url: "/code/getCode",
                data: {pageCode: code},
                async: false,
                success: function (data) {
                    if (data == "yes") {
                        $("#infoDiv").html("<img id=\"valCode\" src=\"${staticurl}/images/yes-1.png\"/>");
                    } else {
                        $("#image_code").val(data.imageCode);
                        $("#infoDiv").html("<img id=\"valCode\" src=\"${staticurl}/images/no-1.png\"/>");
                    }
                }
            });
        } else {
            $("#message").html("请填写图片验证码！");
        }
    }

    //登陆
    $("#log_in_button").click(function () {
        var bo = false;
        var msgInfo = '';

        var user_phone = $("#user_phone").val();
        var mycode = $("#mycode").val();
        if (user_phone.trim() == null || user_phone.trim() == '') {

            msgInfo += "输入的手机号不能为空！<br/>";
            bo = true;
        }
        var count = $("#count").val();
        if (count > 3) {
            if($("#image_code").val()==null&& $("#image_code").val().trim()=='') {
                //判断图片验证码是否存在
                msgInfo += "图片验证码不能为空！<br/>";
                bo = true;
            }
        }
        if (user_phone.match("^[0](13|15)[0-9]{9}$")) {
            msgInfo += "输入的手机号格式不符！<br/>";
            bo = true;
        }
        if (mycode == null || mycode == '') {
            msgInfo += "短信验证码不能为空！<br/>";
            bo = true;
        }
        if (bo) {
            $("#message").html(msgInfo);
        } else {
            //点击登陆按钮进行跳转
            $("#myform").submit();
        }
    });

</script>
</body>
</html>