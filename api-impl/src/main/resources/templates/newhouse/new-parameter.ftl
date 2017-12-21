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
            <#assign tags = discript['building_tags']>
            <#list tags as item>
                <span>${item}</span>
            </#list>
            </div>
        </div>
        <ul class="primary-item">
            <li>
                <p>销售状态：待售</p>
                <p>最新开盘：${discript['opened_time']}</p>
                <p>交房时间：${discript['deliver_time']}</p>
                <p>参考均价：${discript['average_price']}元/㎡ </p>
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
                <p>环线位置：${discript['roundstation']}</p>
                <p>区域位置：${discript['district_name']}</p>
                <p>楼盘地址：${discript['building_address']}</p>
                <p>售楼处地址：${discript['sale_address']}</p>
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
                <p>建筑类型：${discript['building_type']}</p>
                <p>装修类型：${discript['redecorate_type']}</p>
                <p>产权年限：${discript['building_life']}</p>
                <p>占地面积：${discript['ground_area']}㎡</p>
                <p>建筑面积：${discript['purpose_area']}㎡</p>
                <p>容积率：${discript['dimension']}</p>
                <p>绿化率：${discript['virescencerate']}%</p>
                <p>规划户数：${discript['totaldoor']}</p>
                <p>规划车位：${discript['park_space']}</p>
                <p>车位配比：${discript['park_radio']}</p>
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
                <p>物业类型：${discript['property_type']}</p>
                <p>物业公司：${discript['propertymanage']}</p>
                <p>物业费：${discript['propertyfee']}</p>
                <p>供暖：${discript['heating_type']}</p>
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