<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/parameter.css?v=${staticversion}">
    <title>新房参数</title>
    <meta name="description" content="让美好生活 来找你">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<#assign discript = discript[0]>
<div class="module-bottom-fill"></div>
<div class="module-bottom-fill">
    <section class="primary-message">
        <div class="primary-header">
            <h2> ${discript['building_name']!'暂无数据'}</h2>
            <#if discript['building_nickname']??&&(discript['building_nickname']!='')><p>别名：${discript['building_nickname']}</p></#if>
            <div class="primary-header-tag">
                <#if discript['building_tags']?exists&&(discript['building_tags']?size>0)>
                    <#assign tags = discript['building_tags']>
                    <#list tags as item>
                        <#if item?exists><span>${item}</span></#if>
                    </#list><#else>暂无数据
                </#if>
            </div>
        </div>
        <ul class="primary-item">
            <li>
                <p>销售状态：<#if discript['sale_status_name']?exists>${discript['sale_status_name']}</#if></p>
                <p>最新开盘：<#if discript['opened_time']?exists>${discript['opened_time']}<#else>暂无</#if></p>
                <p>交房时间：<#if discript['deliver_time']?exists>${discript['deliver_time']}<#else>暂无</#if></p>
                <p>参考均价：<#if (discript['average_price']?exists && discript['average_price']>0)>${discript['average_price']}元/㎡<#else>售价待定</#if></p>
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
                <p>环线位置：<#if discript['loop_position']?exists>${discript['loop_position']}<#else>暂无数据</#if></p>
                <p>区域位置：<#if discript['district_name']?exists>${discript['district_name']}<#else>暂无数据</#if></p>
                <p>楼盘地址：<#if discript['building_address']?exists>${discript['building_address']}<#else>暂无数据</#if></p>
                <p>售楼处地址：<#if discript['sale_address']?exists>${discript['sale_address']}<#else>暂无数据</#if></p>
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
                <p>建筑类型：<#if discript['building_type']?exists && discript['building_type']!=''>${discript['building_type']}<#else>暂无数据</#if></p>
                <p>装修类型：<#if discript['redecorate_type']?exists && discript['redecorate_type']!=''>${discript['redecorate_type']}<#else>暂无数据</#if></p>
                <p>产权年限：<#if (discript['building_life']?exists && discript['building_life']>0)>${discript['building_life']}<#else>暂无数据</#if></p>
                <p>占地面积：<#if (discript['ground_area']?exists && discript['ground_area']>0)>${discript['ground_area']}㎡<#else>暂无数据</#if></p>
                <p>建筑面积：<#if (discript['purpose_area']?exists && discript['purpose_area']>0)>${discript['purpose_area']}㎡<#else>暂无数据</#if></p>
                <p>容积率：<#if discript['dimension']?exists && discript['dimension']?number gt 0>${discript['dimension']}<#else>暂无数据</#if></p>
                <p>绿化率：<#if (discript['virescencerate']?exists && discript['virescencerate']>0)>${discript['virescencerate']?string("#.##")}%<#else>暂无数据</#if></p>
                <p>规划户数：<#if discript['totaldoor']?exists && (discript['totaldoor']?number gt 0)>${discript['totaldoor']}<#else>暂无数据</#if></p>
                <p>规划车位：<#if (discript['park_space']?exists && discript['park_space']>0)>${discript['park_space']}<#else>暂无数据</#if></p>
                <p>车位配比：<#if discript['park_radio']?exists && discript['park_radio']!="">${discript['park_radio']}<#else>暂无数据</#if></p>
            </li>
        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <section class="primary-message">
        <div class="module-header-message">
            <h3>物业信息</h3>
        </div>
        <ul class="primary-item">
            <li>
                <p>物业类型：<#if discript['property_type']?exists>${discript['property_type']}<#else>暂无数据</#if></p>
                <p>物业公司：<#if discript['propertymanage']?exists && discript['propertymanage']!=''>${discript['propertymanage']}<#else>暂无数据</#if></p>
                <p>物业费：<#if discript['propertyfee']?exists&&discript['propertyfee']?number gt 0>${discript['propertyfee']}元/㎡·月<#else>暂无数据</#if></p>
                <p>供暖：<#if discript['heating_type']?exists && discript['heating_type'] == 0>未知
                    <#if discript['heating_type']?exists && discript['heating_type'] == 1>
                        集中供暖
                    </#if>
                    <#if discript['heating_type']?exists && discript['heating_type'] == 2>
                        自供暖
                    </#if>
                    <#else>
                        暂无数据
                    </#if>
                </p>
                <p>供水：<#if discript['water_supply']?exists>${discript['water_supply']}<#else>暂无数据</#if></p>
                <p>供电：<#if discript['electric_supply']?exists>${discript['electric_supply']}<#else>暂无数据</#if></p>
                <#--<p>燃气：<#if discript['heating_type']?exists>${discript['heating_type']}<#else>暂无</#if></p>-->
            </li>
        </ul>
    </section>
</div>
<section class="primary-message">
    <div class="module-header-message">
        <h3>许可证信息</h3>
    </div>
    <table class="primary-table">
        <tr>
            <th>许可证</th>
            <th>发证时间</th>
            <th>绑定楼栋</th>
        </tr>
    <#if discript['sales_license']?exists&&(discript['sales_license']?size>0)>
        <#assign tag = discript['sales_license']>
        <#list tag as tags>
            <tr>
                <td>${tags['licenseName']!'暂无数据'}</td>
                <td>${tags['time']?substring(0,10)!'暂无数据'}</td>
                <td>${tags['buildInfo']!'暂无数据'}</td>
            </tr>
        </#list>
         <#else>
             <tr>
                 <td>暂无数据</td>
             </tr>
    </#if>
    </table>
</section>
<#if discript['saletelphone']?exists&&discript['saletelphone']!=''>
<div class="detail-contact-wrapper">
    <section class="detail-contact-box" id="detailContactState">
        <div class="detail-contact-content">
            <#--<a href="#" class="contact-share"><i></i>分享</a>
            <a href="#" class="contact-collect"><i></i>收藏</a>-->
            <a href="tel:${discript['saletelphone']}" class="only contact-telephone-counseling">咨询售楼处</a>
        </div>
    </section>
</div>
</#if>
<script src="${staticurl}/js/fastclick.js?v=${staticversion}"></script>
<script src="${staticurl}/js/default-touch.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>
</body>
</html>