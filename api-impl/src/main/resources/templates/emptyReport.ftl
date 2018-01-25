<!DOCTYPE html>
<html>
<head>
    <#include "staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/404.css?v=${staticversion}">
    <title>我的报告页</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
</head>
<body>
<div class="page-empty report">
    <img src="${staticurl}/images/global/wdbg_icon_bg.png" alt="还没有报告页？">
    <p>还没有报告页？</p>
    <div class="tips-content-wrapper">
        <span>1</span>
        <div class="tips-content">
            <h3>报告从哪里来？</h3>
            <p>快去体验懂房帝，获取您的专属理想家分析报告。</p>
        </div>
    </div>
    <div class="tips-content-wrapper mt">
        <span>2</span>
        <div class="tips-content">
            <h3>懂房帝是什么？</h3>
            <p>头条房产大数据，为您匹配专属理想家。</p>
        </div>
    </div>
    <a class="link-intelligent" href="${router_city('/findhouse/')}">获取报告</a>
</div>
<script src="${staticurl}/js/fastclick.js"></script>
<script src="${staticurl}/js/default-touch.js?v=${staticversion}"></script>
</body>
</html>