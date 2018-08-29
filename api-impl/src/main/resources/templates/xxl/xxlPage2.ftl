<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/cpc.css?v=${staticversion}">
    <title>懂房帝 买房秒懂</title>
    <meta name="description" content="懂房帝 买房秒懂">
    <meta name="keywords" content="">
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<a href="http://m.toutiaofangchan.com/bj/?=xxl2esf_top_banner"><img src="${staticurl}/images/cpc/cpc-page2-banner.png?v=${staticversion}" width="100%" alt="懂房帝"style="margin-bottom:3%;"></a>
<#if adcpc?exists><section class="recommend-list">
    <ul>
        <#assign listdetail = adcpc['data']>
        <#list listdetail as itemdetail>
        <#if itemdetail_index==5>
            <#break >
        </#if>
        <li><a href="${router_city('/esf/'+itemdetail['houseId']?c+'.html?xxl2esf_detail')}" class="list-item">
            <div class="clear">
                <div class="list-item-img-box">
                    <#if itemdetail['housePhotoTitle']?exists>
                        <#assign photoitem=itemdetail['housePhotoTitle']>
                        <#if photoitem?? && photoitem != ''><img src="${photoitem}" alt="${itemdetail.plotName}">
                        <#else ><img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                        </#if>
                    </#if>
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-1"><span>${itemdetail.houseTitle}</span></h3>
                    <p class="cont-block-2">
                        <#if itemdetail['buildArea']?exists>${itemdetail['buildArea']}㎡</#if><#if itemdetail['room']?exists&&itemdetail['room']?number gt 0>/${itemdetail.room+'室'}</#if><#if itemdetail['hall']?exists&&itemdetail['hall']?number gt 0>${itemdetail.hall+'厅'}</#if><#if itemdetail['forwardName']?exists>/${itemdetail.forwardName}</#if>
                    <#if itemdetail['houseBusinessName']?exists>
                    <p class="cont-block-3 distance">
                        <i class="icon"></i>
                        <#if itemdetail['area']?exists && itemdetail['area'] != ''>${itemdetail['area']}<#else >暂无数据</#if> <#if itemdetail['houseBusinessName'] != ''>${itemdetail['houseBusinessName']}<#else >暂无数据</#if>
                    </p>
                    <#else >
                    <p class="cont-block-3 distance">暂无数据</p>
                    </#if>
                    <#if itemdetail['tagsName']?exists>
                        <#assign item = itemdetail['tagsName']>
                        <div class="cont-block-4 house-labelling normal esf company">
                            <span>${itemdetail.of_company}</span>
                            <#list item as itemValue>
                                <#if itemValue?exists>
                                    <span>${itemValue}</span>
                                </#if>
                            </#list>
                        </div>
                    </#if>
                    <div class="cont-block-price">
                        <em>¥${itemdetail.houseTotalPrices}<i> 万</i></em>
                        <span><#if itemdetail.houseTotalPrices?exists && itemdetail.buildArea?exists && itemdetail.houseTotalPrices?number gt 0 && itemdetail.buildArea?number gt 0>${((itemdetail.houseTotalPrices?number / itemdetail.buildArea?number)) * 10000}元/㎡<#else>暂无数据</#if></span>
                    </div>
                </div>
            </div>
        </a></li>
        </#list>
    </ul>
    <a href="http://m.toutiaofangchan.com/bj/esf?=xxl2esf_all&beginPrice=300.0&endPrice=500.0" class="change-list">查看全部78526套房源 ></a>
</section></#if>
<a class="bottom-link-box" href="http://m.toutiaofangchan.com/bj/?=xxl2esf_two">
    <img src="${staticurl}/images/cpc/bottom-link.png?v=${staticversion}" width="100%" alt="发现好房 只要两步">
</a>
<div class="bottom-tips">
    <img src="http://wap-qn.toutiaofangchan.com/adideas/luodiyesucai/700fba27781845049881eb3c23ae9674/20180209174127.png" alt="上拉进入懂房帝">
    <p>上拉进入懂房帝</p>
</div>
</body>
<script>
    var addHeight;
    window.onscroll = function(){
        if(/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)){
            addHeight = 200;
        }
        if(/(android|Windows Phone|windows|Windows)/i.test(navigator.userAgent)){
            addHeight = 0;
        }
        if(getScrollTop() + getClientHeight() >= getTotalHeight() + addHeight) {
            window.location.href = "http://m.toutiaofangchan.com/bj/?=xxl2esf_bottom";
        }
    };


    //获取滚动条当前的位置
    function getScrollTop() {
        var scrollTop = 0;
        if(document.documentElement && document.documentElement.scrollTop) {
            scrollTop = document.documentElement.scrollTop;
        } else if(document.body) {
            scrollTop = document.body.scrollTop;
        }
        return scrollTop;
    }

    //获取当前可视范围的高度
    function getClientHeight() {
        var clientHeight = 0;
        if(document.body.clientHeight && document.documentElement.clientHeight) {
            clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight);
        } else {
            clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight);
        }
        return clientHeight;
    }

    //获取文档完整的高度
    function getTotalHeight() {
        return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
    }
</script>
</html>