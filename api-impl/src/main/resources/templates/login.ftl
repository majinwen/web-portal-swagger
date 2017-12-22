<!DOCTYPE html>
<html xmlns:c="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/login.css">
    <title>登录页</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<h2>手机快捷登录</h2>
<p class="registration-prompt">（未注册过的手机号将自动创建账号）</p>
<form action="/user/tologin" id="myform" class="login-form" method="post">
    <input type="hidden" name="count" id="count" value="<#if count?exists>${count}</#if>">
    <div class="input-phone">
        <input type="tel" maxlength="11" id="user_phone" name="phone" placeholder="请输入手机号"
               value="<#if phone?exists>${phone}</#if>"
               onkeyup="this.value=this.value.replace(/[^0-9]*$/,'') ">
        <div id="phone_code" class="code-btn-box">
            <span class="code-btn">获取验证码</span>
        </div>
    </div>
<#if count?exists>
    <#if (count>3)>
        <div style="position: relative; margin-top: 0.5333333333rem;;">
            <input type="text" class="input_css" id="image_code" name="imageCode" maxlength="4" value="<#if imageCode?exists>${imageCode}</#if>" placeholder="请输入图片验证码">
            <div id="infoDiv" class="show" style="position: absolute; top: 0; right: 200px; display:inline-block; width:37px; height: 76px; line-height: 79px; text-align: center"></div>
            <a href="javascript:changeImg();" style="position: absolute; top:  0; right: 0;">
                <img width="200" height="80" class="code-btn-box" id="checkcode" src="/code/imageCode"/>
            </a>
        </div>
    </#if>
</#if>
    <input class="code-text" type="number" id="mycode" name="code" maxlength="4" placeholder="请输入验证码">
    <span id="message" class="code-text" style="color: red;"><#if message?exists>${message}</#if></span>
    <button type="button" id="log_in_button" class="submit-btn">登录</button>
</form>
<p class="fixed-bottom-tips">注册/登录即代表同意《头条房产用户使用协议》</p>
<script>
    $(document).ready(function () {
        $("#image_code").val('');
        $("#infoDiv").html('');
    });
    //获取短信验证码
    $("#phone_code").click(function () {
        var bo = false;
        var msgInfo = "请先修正以下信息后再操作：<br/>";

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
                        //发送成功 js定时器
                        $("#count").val(data1.data);
                    }
                }
            });
        }
    });

    var count = $("#count").val();
    if (count != null) {
        if (count > 3) {
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
                    // 检查会员级别名称是否存在
                    $.ajax({
                        type: "post",
                        url: "/code/getCode",
                        data: {pageCode: code},
                        async: false,
                        success: function (data) {
                            if (data == "yes") {
                                $("#infoDiv").html("<img id=\"valCode\" src=\"${staticurl}/images/yes-1.png\"/>");
                            } else {
                                $("#infoDiv").html("<img id=\"valCode\" src=\"${staticurl}/images/no-1.png\"/>");
                            }
                        }
                    });
                } else {
                    $("#message").html("请填写图片验证码！");
                }
            }

        }

    }
    //登陆
    $("#log_in_button").click(function () {
        var bo = false;
        var msgInfo = "请先修正以下信息后再操作：<br/>";

        var user_phone = $("#user_phone").val();
        var mycode = $("#mycode").val();
        if (user_phone.trim() == null || user_phone.trim() == '') {

            msgInfo += "输入的手机号不能为空！<br/>";
            bo = true;
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