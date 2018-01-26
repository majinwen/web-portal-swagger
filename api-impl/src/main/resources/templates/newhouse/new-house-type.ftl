<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/house-type.css?v=${staticversion}">
    <title>户型详情</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<div class="module-bottom-fill">
    <div class="house-type-state">
        <span  data-id="0"  data-bid="${bid}" class="current">全部（${roomcount}）</span>
    <#if room?exists>
    <#list room as roomnode>
        <#assign roomid=roomnode['room']>
        <span data-bid="${bid}"  data-id="${roomid}">${roomid}居（${roomnode['count']}）</span>
    </#list>
    </#if>
    </div>
</div>
<div id="all-type" class="module-bottom-fill"><#if layoutDetails?exists>
    <#list layoutDetails as datail>
        <section class="room${datail['room']}">
        <div class="house-type-header">
            <#if datail['room'] gt 0 >
                    <p>${datail['room']}室${datail['hall']}厅${datail['toilet']}卫/${datail['building_area']}㎡</p>
                <#else>
                    <p>${datail['building_area']}㎡</p>
            </#if>
        </div>
        <div class="house-type-tag">
            <#--<p>均价：<#if datail['reference_total_price']?exists><em class="high-light-red">${datail['reference_total_price']}万</em>/套<#else>暂无</#if></p>-->
            <div class="house-labelling normal">
                <#if datail['layout_tag']?exists>
                <#list datail['layout_tag'] as tag>
                   <span><#if tag?exists>${tag}</#if></span>
                </#list>
              </#if>
            </div>
        </div>

        <div class="house-type-image">
            <div><#if datail['layout_img']?exists>
                <#assign layoutimgs = datail['layout_img']?split(",")>
                <#list layoutimgs as layoutimg>
                    <img src="${qiniuimage}/${layoutimg}-ttw800" alt="户型图">
                </#list>
            <#else>
                <img src="${staticurl}/images/newhouse/hxxq_image1@3x.png" alt="户型图">
            </#if>
                <span class="sale-state">${datail['is_sale']!'在售'}</span>
           </div>
        </div>
          <#--<div class="describe-box">
             <div class="describe-header">户型描述</div>
                <div class="describe-cont">
                    <#if datail['layout_desc']?exists>
                        <p>${datail['layout_desc']}</p><span class="describe-show-btn">>>展开</span>
                    <#else>
                        <p>暂无描述</p>
                    </#if>
                </div>
          </div>-->
    </section>
    </#list>
</#if>
</div>
<p class="bottom-tips">以上是全部户型</p>
<div class="detail-contact-wrapper">
<#assign discript = discript[0]>
<#if discript['saletelphone']?exists&&discript['saletelphone']!=''>
    <section class="detail-contact-box" id="detailContactState">
        <div class="detail-contact-content">
            <#--<a href="#" class="contact-share"><i></i>分享</a>
            <a href="#" class="contact-collect"><i></i>收藏</a>-->
            <a href="tel:${discript['saletelphone']}" class="only contact-telephone-counseling">咨询售楼处</a>
        </div>
    </section>
</#if>
</div>
<script src="${staticurl}/js/fastclick.js?v=${staticversion}"></script>
<script src="${staticurl}/js/default-touch.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>
</body>
</html>