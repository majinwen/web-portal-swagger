<!DOCTYPE html>
<html xmlns:c="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/jquery.fullPage.css">
    <link rel="stylesheet" href="${staticurl}/css/intelligent.css">
    <title>登录页</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script src="${staticurl}/js/jquery.fullPage.min.js"></script>
</head>
<body>
<div id="superContainer">
    <div class="section page1">
        <div class="bgbox bg1">
            <div class="page-content">
                <h1>智能找房</h1>
                <p>房产头条大数据<br>根据您的需求推荐最匹配的小区和房源</p>
                <button type="button" class="button">开始体验</button>
            </div>
        </div>
    </div>
    <div class="section page2 active">
        <div class="bgbox bg2">
            <div class="page-content">
                <h2>请选择程序关键词</h2>
                <p>方便给您提供最精确的服务</p>
                <div class="choose-wrapper">
                    <div class="choose-item-box current">
                        <div class="box-line-triangle"></div>
                        <div class="choose-item-cont">
                            <p>
                                <span>自住</span>
                                <span>首套</span>
                                <span>交通便利</span>
                            </p>
                        </div>
                    </div>
                    <div class="clear">
                        <div class="choose-item-box">
                            <div class="box-line-triangle"></div>
                            <div class="choose-item-cont">
                                <p>
                                    <span>自住</span>
                                    <span>二套</span>
                                    <span>配套完善</span>
                                </p>
                            </div>
                        </div>
                        <div class="choose-item-box">
                            <div class="box-line-triangle"></div>
                            <div class="choose-item-cont">
                                <p>
                                    <span>出租</span>
                                    <span>保值</span>
                                    <span>回报率</span>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="button" class="button">确定</button>
            </div>
        </div>
    </div>
    <div class="section page3">
3
    </div>
    <div class="section page4">
4
    </div>
</div>

<script>
    $(function () {
        $('#superContainer').fullpage();
    })
</script>
</body>
</html>