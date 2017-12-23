<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/parameter.css">
    <title>小区参数</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<div class="module-bottom-fill"></div>
<div class="module-bottom-fill">
    <#if (villageList?exists)&&(villageList?size>0)>
    <#if villageList[0]?exists><#assign village = villageList[0]>
    </#if>
    </#if>
    <section class="primary-message">
        <div class="primary-header">
            <h2>${village['rc']!"暂无"}</h2>
            <p>别名：${village['alias']!"暂无"}</p>
            <div class="primary-header-tag house-labelling gray">
                <#if village['label']?exists>
                     <#assign labels = village['label']>
                         <#list labels as label>
                             <span>${label!""}</span>
                         </#list>
                    <#else>暂无
                </#if>
            </div>
            <div class="isopsophic-air-index">
                <p>空气指数：暂无</p>
                <p>噪音指数：暂无</p>
            </div>
        </div>
        <ul class="primary-item">
            <li>
                <p>参考均价：<#if village['avgPrice']?exists><em class="high-light-red">${village['avgPrice']}元</em>/㎡ <#else>暂无</#if></p>
            </li>
        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <section class="primary-message">
        <div class="module-header-message">
            <h3>地址信息</h3>
        </div>
        <ul class="primary-item">
            <li>
                <p>环线位置：${village['ringRoad']!"暂无"}</p>
                <p>区域位置：${village['area']!"暂无"}</p>
                <p>小区地址：${village['address']!"暂无"}</p>
            </li>
        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <section class="primary-message">
        <div class="module-header-message">
            <h3>参数信息</h3>
        </div>
        <ul class="primary-item">
            <li>
                <p>建成年代：${village['abbreviatedAge']!"暂无"}</p>
                <p>建筑类型：<#if village['architectureType']?exists><#list village['architectureType'] as arType>
                        ${arType!""}&nbsp;&nbsp;
                </#list></#if>
                </p>
                <p>产权年限：${village['yopr']!"暂无"}年</p>
                <p>占地面积：${village['areaSize']!"暂无"}㎡</p>
                <p>建筑面积：${village['buildingAreaSize']!"暂无"}㎡</p>
                <p>容积率：${village['dimension']!"暂无"}</p>
                <p>绿化率：${village['avgGreening']!"暂无"}%</p>
                <p>规划户数：${village['sumBuilding']!"暂无"}栋/${village['sumHousehold']!"暂无"}户</p>
               <#-- <p>规划车位：EEEEEEEE</p>-->
                <p>车位配比：${village['sumHousehold']!"暂无"}户数/${village['carPositionRatio']!"暂无"}车位数</p>
            </li>
        </ul>
    </section>
</div>
<section class="primary-message">
    <div class="module-header-message">
        <h3>物业信息</h3>
    </div>
    <ul class="primary-item">
        <li>
            <p>物业类型：${village['propertyType']!"暂无"}</p>
            <p>物业公司：${village['property']!"暂无"}</p>
            <p>物业费：${village['propertyFee']!"暂无"}</p>
            <p>供暖：${village['heatingMode']!"暂无"}</p>
            <p>供水：${village['waterSupply']!"暂无"}</p>
            <p>供电：${village['electricSupply']!"暂无"}</p>
       <#--     <p>燃气：${village['heatingMode']!"暂无"}暂无</p>-->
        </li>
    </ul>
</section>
<div class="detail-contact-wrapper">
    <section class="detail-contact-box" id="detailContactState">
        <div class="detail-contact-content">
            <a href="#" class="contact-share"><i></i>分享</a>
            <a href="#" class="contact-collect"><i></i>收藏</a>
            <a href="tel:1234789" class="contact-telephone-counseling">咨询售楼处</a>
        </div>
    </section>
</div>

<script src="${staticurl}/js/main.js"></script>
</body>
</html>