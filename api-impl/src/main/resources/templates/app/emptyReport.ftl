<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${appstaticurl}/css/404.css?v=${staticversion}">
    <title>我的购房报告</title>
    <meta name="description" content="懂房帝 买房秒懂">
    <meta name="keywords" content="">
    <script src="${appstaticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<div class="page-empty report">
    <img src="${appstaticurl}/images/global/wdbg_icon_bg.png" alt="还没有报告页？">
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
            <p>懂房帝大数据，为您匹配专属理想家。</p>
        </div>
    </div>
    <a class="link-intelligent" href="${router_city('/findhouse/dongfangdi')}">获取报告</a>
</div>
<script src="${appstaticurl}/js/fastclick.js?v=${staticversion}"></script>
<script src="${appstaticurl}/js/default-touch.js?v=${staticversion}"></script>
</body>
</html>