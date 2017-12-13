<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/house-type.css">
    <title>户型详情</title>
</head>
<body>
<div class="module-bottom-fill">
    <div class="house-type-state">
        <span  data-id="0"  data-bid="${bid}" <#if tags==0>class="current"</#if>>全部（0）</span>
    <#if room?exists>
    <#list room as roomnode>
       <#assign roomid=roomnode['room']>
        <span data-bid="${bid}" <#if tags==roomid>class="current"</#if> data-id="${roomid}">${roomid}居（${roomnode['count']}）</span>
    </#list>
    </#if>
    </div>
</div>
<div class="module-bottom-fill">
       <#if layoutDetails?exists>
           <#list layoutDetails as datail>
             <section>
        <div class="house-type-header">
            <p>${datail['room']}室${datail['hall']}厅${datail['toilet']}卫/${datail['living_area']}㎡</p>
        </div>
        <div class="house-type-tag">
            <p>均价：<em class="high-light-red">${datail['reference_total_price']}万</em>/套</p>
            <div class="house-labelling normal">
               <#if datail['layout_tag']?exists>
                   <#list datail['layout_tag'] as tag>
                       <span>${tag}</span>
                   </#list></#if>
            </div>
        </div>
        <div class="house-type-image">
            <div>
                <img src="${staticurl}/images/newhouse/hxxq_image1@3x.png" alt="户型图">
                <span class="sale-state">
                    <#if datail['is_sale']==1>在售
                     <#else>不在售
                    </#if>
                </span>
            </div>
        </div>
        <div class="describe-box">
            <div class="describe-header">户型描述</div>
            <div class="describe-cont">
                <p>${datail['layout_desc']}</p>
                <span class="describe-show-btn">>>展开</span>
            </div>
        </div>
    </section>
           </#list>
       </#if>
</div>
<p class="bottom-tips">以上是全部户型</p>
<section class="detail-contact-box" id="detailContactState">
    <div class="detail-contact-content">
        <a href="#" class="contact-share"><i></i>分享</a>
        <a href="#" class="contact-collect"><i></i>收藏</a>
        <a href="tel:1234789" class="contact-telephone-counseling">咨询售楼处</a>
    </div>
</section>

<script src="${staticurl}/js/zepto.min.js"></script>
<!-------- photoswipe -------->
<script src="${staticurl}/js/detail.js"></script>
</body>
</html>