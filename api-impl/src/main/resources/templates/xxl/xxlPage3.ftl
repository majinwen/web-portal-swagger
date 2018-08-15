<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/cpc.css?v=${staticversion}">
    <title>懂房帝 买房秒懂</title>
    <meta name="description" content="懂房帝 买房秒懂">
    <meta name="keyword" content="">
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<a href="http://m.toutiaofangchan.com/bj/esf?=xxl3esf_top_banner&beginPrice=0.0&endPrice=200.0"><img src="${staticurl}/images/cpc/cpc-page3-banner.png" width="100%" alt="懂房帝"></a>
<#if adcpc?exists><section class="recommend-list">
    <ul>
        <#assign listdetail = adcpc['data']>
        <#list listdetail as itemdetail>
        <li><a href="${router_city('/esf/'+itemdetail['houseId']?c+'.html?xxl3esf_detail')}" class="list-item">
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
                    </p>
                    <#if itemdetail['traffic']?exists || itemdetail['houseBusinessName']?exists>
                    <p class="cont-block-3 distance"><i class="icon"></i>
                        <#if itemdetail['traffic']?exists>
                            <#assign traffic = itemdetail['traffic']>
                            <#assign split = traffic?split('$')>
                            <#if split[2]?number gt 1000>
                                <#assign x = split[2]?number/1000>
                                距离 ${split[1]} [${split[0]}] ${x?string("#.#")}km
                            <#else>
                                距离 ${split[1]} [${split[0]}] ${split[2]}m
                            </#if>
                        <#elseif itemdetail['houseBusinessName']?exists>
                            <#if itemdetail['area']?exists && itemdetail['area'] != ''>${itemdetail['area']}<#else >暂无数据</#if> <#if itemdetail['houseBusinessName'] != ''>${itemdetail['houseBusinessName']}<#else >暂无数据</#if>
                        </#if>
                    </p>
                    <#else >
                    <p class="cont-block-3 distance">暂无数据</p>
                    </#if>
                    <#if itemdetail['tagsName']?exists>
                        <#assign item = itemdetail['tagsName']>
                        <div class="cont-block-4 house-labelling normal esf">
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
</section></#if>
<a class="bottom-link-box" href="http://m.toutiaofangchan.com/bj/esf?=xxl3esf_two&beginPrice=0.0&endPrice=200.0">
    <img src="http://wap-qn.toutiaofangchan.com/adideas/luodiyesucai/80a5ec258cf84945adaad2b6587c4f9b/bottom-link.jpg" width="100%" alt="发现好房 只要两步">
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
            window.location.href = "http://m.toutiaofangchan.com/bj/esf?=xxl3esf_bottom&beginPrice=0.0&endPrice=200.0";
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