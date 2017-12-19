<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/login.css">
    <title>登录页</title>
</head>
<body>
<h2>手机快捷登录</h2>
<p class="registration-prompt">（未注册过的手机号将自动创建账号）</p>
<form action="" class="login-form">
    <div class="input-phone">
        <input type="tel" maxlength="11" placeholder="请输入手机号">
        <div class="code-btn-box">
            <span class="code-btn">获取验证码</span>
        </div>
    </div>
    <input class="code-text" type="number" maxlength="6" placeholder="请输入验证码">
    <button type="submit" class="submit-btn">登录</button>
</form>
<p class="fixed-bottom-tips">注册/登录即代表同意《头条房产用户使用协议》</p>
</body>
</html>