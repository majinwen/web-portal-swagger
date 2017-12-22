<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/parameter.css">
    <title>新房参数</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<#assign discript = discript[0]>
<div class="module-bottom-fill"></div>
<div class="module-bottom-fill">
    <section class="primary-message">
        <div class="primary-header">
            <h2>${discript['building_name']}</h2>
            <p>别名：${discript['building_nickname']}</p>
            <div class="primary-header-tag">
                <#if discript['building_tags']?exists>
                    <#assign tags = discript['building_tags']?split(",")>
                    <#list tags as item>
                        <#if item?exists><span>${item}</span></#if>
                    </#list><#else>暂无
                </#if>

            </div>
        </div>
        <ul class="primary-item">
            <li>
                <p>销售状态：待售</p>
                <p>最新开盘：<#if discript['opened_time']?exists>${discript['opened_time']}<#else>暂无</#if></p>
                <p>交房时间：<#if discript['deliver_time']?exists>${discript['deliver_time']}<#else>暂无</#if></p>
                <p>参考均价：<#if discript['average_price']?exists>${discript['average_price']}元/㎡<#else>暂无</#if></p>
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
                <p>环线位置：<#if discript['roundstation']?exists>${discript['roundstation']}<#else>暂无</#if></p>
                <p>区域位置：<#if discript['district_name']?exists>${discript['district_name']}<#else>暂无</#if></p>
                <p>楼盘地址：<#if discript['building_address']?exists>${discript['building_address']}<#else>暂无</#if></p>
                <p>售楼处地址：<#if discript['sale_address']?exists>${discript['sale_address']}<#else>暂无</#if></p>
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
                <p>建筑类型：<#if discript['building_type']?exists>${discript['building_type']}<#else>暂无</#if></p>
                <p>装修类型：<#if discript['redecorate_type']?exists>${discript['redecorate_type']}<#else>暂无</#if></p>
                <p>产权年限：<#if discript['building_life']?exists>${discript['building_life']}<#else>暂无</#if></p>
                <p>占地面积：<#if discript['ground_area']?exists>${discript['ground_area']}㎡<#else>暂无</#if></p>
                <p>建筑面积：<#if discript['purpose_area']?exists>${discript['purpose_area']}㎡<#else>暂无</#if></p>
                <p>容积率：<#if discript['dimension']?exists>${discript['dimension']}<#else>暂无</#if></p>
                <p>绿化率：<#if discript['virescencerate']?exists>${discript['virescencerate']}%<#else>暂无</#if></p>
                <p>规划户数：<#if discript['totaldoor']?exists>${discript['totaldoor']}<#else>暂无</#if></p>
                <p>规划车位：<#if discript['park_space']?exists>${discript['park_space']}<#else>暂无</#if></p>
                <p>车位配比：<#if discript['park_radio']?exists>${discript['park_radio']}<#else>暂无</#if></p>
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
                <p>物业类型：<#if discript['property_type']?exists>${discript['property_type']}<#else>暂无</#if></p>
                <p>物业公司：<#if discript['propertymanage']?exists>${discript['propertymanage']}<#else>暂无</#if></p>
                <p>物业费：<#if discript['propertyfee']?exists>${discript['propertyfee']}<#else>暂无</#if></p>
                <p>供暖：<#if discript['heating_type']?exists>${discript['heating_type']}<#else>暂无</#if></p>
                <p>供水：暂无</p>
                <p>供电：暂无</p>
                <p>燃气：暂无</p>
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
        <tr>
            <td>X京(2017)不动产第34号</td>
            <td>20170909</td>
            <td>8号楼</td>
        </tr>
        <tr>
            <td>X京(2017)不动产第34号</td>
            <td>20170909</td>
            <td>8号楼</td>
        </tr>
        <tr>
            <td>X京(2017)不动产第34号</td>
            <td>20170909</td>
            <td>8号楼</td>
        </tr>
    </table>
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