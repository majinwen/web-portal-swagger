<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/list.css">
    <title>二手房列表</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
</head>
<#setting url_escaping_charset="UTF-8">
<body>
<header class="main-top-header">
    <input id="url" type="hidden" value="/findProjHouseInfo">
    <a href="/index" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" class="search-link" placeholder="中骏·西山天璟">
    </div>
    <a href="javascript:;" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="个人中心"></a>
</header>
<section class="category-box">
    <ul id="category-tab">
        <li data-mark="tab-place"><span><em>区域</em><i></i></span></li>
        <li data-mark="tab-price"><span><em>总价</em><i></i></span></li>
        <li data-mark="tab-type"><span><em>户型</em><i></i></span></li>
        <li data-mark="tab-more"><span><em>更多</em><i></i></span></li>
    </ul>
    <div class="global-mark none"></div>
    <div class="category-cont">
        <!-- 区域 -->
        <div class="filter-item" data-mark="panel-place">
            <div class="place-list">
                <ul id="level1" class="nav" data-mark="level1">
                    <li onclick="showDistrict()">区域</li>
                    <li onclick="showSubway()">地铁</li>
                </ul>
                <ul id="level2" class="guide none" data-mark="level2"></ul>
                <ul id="level3" class="cont none" data-mark="level3"></ul>
            </div>
        </div>
        <!-- 价格 -->
        <div class="filter-item" data-mark="panel-price">
            <div class="price-list">
                <ul>
                    <li data-begin-price="" data-end-price="" class="current">不限</li>
                    <li data-begin-price="0.0" data-end-price="200.0">200万以下</li>
                    <li data-begin-price="200.0" data-end-price="250.0">200-250万</li>
                    <li data-begin-price="250.0" data-end-price="300.0">250-300万</li>
                    <li data-begin-price="300.0" data-end-price="350.0">300-350万</li>
                    <li data-begin-price="350.0" data-end-price="400.0">350-400万</li>
                    <li data-begin-price="400.0" data-end-price="1000.0">400万以上</li>
                </ul>
            </div>
        </div>
        <!-- 户型 -->
        <div class="filter-item" data-mark="panel-type">
            <div class="type-list">
                <ul>
                    <li class="current" data-type="0">不限</li>
                    <li data-type="1">一居 <i></i></li>
                    <li data-type="2">二居 <i></i></li>
                    <li data-type="3">三居 <i></i></li>
                    <li data-type="4">四居 <i></i></li>
                    <li data-type="5">五居及五居以上 <i></i></li>
                </ul>
                <div class="submit-wrapper">
                    <a href="javascript:;" class="operation-button type-submit" id="typeSubmit">确定</a>
                </div>
            </div>
        </div>
        <!-- 更多 -->
        <div class="filter-item" data-mark="panel-more">
            <div class="more-list">
                <dl>
                    <dt data-type="houseManagementTypeId">物业类型</dt>
                    <dd>
                        <span data-info="1">普通住宅</span>
                        <span data-info="2">公寓</span>
                        <span data-info="3">酒店式公寓</span>
                        <span data-info="4">花园洋房</span>
                        <span data-info="5">商住楼</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="houseOrientationId">朝向</dt>
                    <dd>
                        <span data-info="1">东</span>
                        <span data-info="2">西</span>
                        <span data-info="3">南</span>
                        <span data-info="4">北</span>
                        <span data-info="5">东南</span>
                        <span data-info="6">西南</span>
                        <span data-info="7">东北</span>
                        <span data-info="8">西北</span>
                        <span data-info="9">东西</span>
                        <span data-info="10">南北</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="houseLabelId">标签</dt>
                    <dd>
                        <span data-info="1">近地铁</span>
                        <span data-info="4">随时看</span>
                        <span data-info="8">满二年</span>
                        <span data-info="16">满五年</span>
                        <span data-info="32">近公园</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="houseAreaId">面积</dt>
                    <dd>
                        <span data-info="0,60">60以下</span>
                        <span data-info="60,90">60-90</span>
                        <span data-info="90,120">90-120</span>
                        <span data-info="120,1000">120以上</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="houseYearId">楼龄</dt>
                    <dd>
                        <span class="only" data-info="0,5">5年内</span>
                        <span class="only" data-info="0,10">10年内</span>
                        <span class="only" data-info="0,15">15年内</span>
                        <span class="only" data-info="0,20">20年内</span>
                        <span class="only" data-info="20,100">20年以上</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="elevator">电梯</dt>
                    <dd>
                        <span data-info="1">有</span>
                        <span data-info="2">无</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="buildingTypeId">建筑类型</dt>
                    <dd>
                        <span data-info="1">板楼</span>
                        <span data-info="2">塔楼</span>
                        <span data-info="3">板塔结合</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="buildingFeature">权属</dt>
                    <dd>
                        <span data-info="1">已购公房</span>
                        <span data-info="2">商品房</span>
                        <span data-info="3">空置房</span>
                        <span data-info="4">使用权房</span>
                        <span data-info="5">央产</span>
                        <span data-info="6">经济适用房</span>
                    </dd>
                </dl>
                <div class="submit-wrapper">
                    <a href="javascript:;" class="operation-button more-reset" id="moreReset">重置</a>
                    <a href="javascript:;" class="operation-button more-submit" id="moreSubmit">确定</a>
                </div>
            </div>
        </div>
    </div>
</section>
<section>
    <ul>
    <#if builds?exists>
        <#list builds as map>
            <li>
                <a class="list-item" href="/queryByHouseIdandLocation/${map.houseId}">
                    <div class="clear">
                        <div class="list-item-img-box">
                            <#assign item=map['housePhoto']>
                                <img src="<#if item[0]?exists>${item[0]}</#if>"
                                     alt="">
                                <#--<#else >
                                    <img  src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产">-->
                        </div>
                        <div class="list-item-cont">
                            <h3 class="cont-block-1">${map.houseTitle}</h3>
                        <p class="cont-block-2">
                            <#if map.buildArea?exists&&(map.buildArea>0)>
                                 ${map.buildArea}㎡|
                            </#if>
                            <#if map.room?exists&&map.hall?exists>
                               ${map.room}室${map.hall}厅|
                            </#if>
                            <#if map.forwardName?exists>
                               ${map.forwardName}|
                            </#if>
                            <#if map.plotName?exists>
                                ${map.plotName}
                            </#if>
                        </p>
                            <#if map['subwayDistince']?exists>
                                <#assign item=map['subwayDistince']>
                                <#if map['key']?exists>
                                    <#if item[map['key']]?exists>
                                        <p class="cont-block-3 distance"><i
                                                class="icon"></i><#assign infoitem=item[map['key']]?split("$")>距离地铁${infoitem[1]}[${infoitem[0]}]${infoitem[2]}m
                                        </p>
                                    </#if >
                                <#else >
                                    <p class="cont-block-3 distance"><i
                                            class="icon"></i><#if map.area?exists&&map.houseBusinessName?exists>${map.area}
                                        [${map.houseBusinessName}]<#else></#if></p>
                                </#if>
                            <#else >
                                <p class="cont-block-3 distance"><i
                                        class="icon"></i><#if map.area?exists&&map.houseBusinessName?exists>${map.area}
                                    [${map.houseBusinessName}]<#else></#if></p>
                            </#if>
                            <div class="cont-block-4 house-labelling gray middle">
                                <#if map['tagsName']?exists>
                                    <#assign item =map['tagsName']>
                                    <#list item as itemValue>
                                        <#if itemValue?exists>
                                            <span>${itemValue}</span>
                                        </#if>
                                    <#else >
                                    </#list>
                                <#else >
                                </#if>
                            </div>
                            <div class="cont-block-price">
                                <em>
                                    <#if map.houseTotalPrices?exists>
                                        <#if map.houseTotalPrices==0>
                                        </#if>
                                    <#else>
                                        ${map.houseTotalPrices}万
                                    </#if>
                                </em>
                                <em>
                                    <#if map.houseUnitCost?exists>
                                        <#if map.houseUnitCost==0>
                                        </#if>
                                        ${map.houseUnitCost}元/㎡
                                    </#if>
                                </em>
                            </div>
                        </div>
                    </div>
                </a></li>
        </#list>
    </#if>
    </ul>
    <p class="tip-box">有新上房源，我们会及时通知您哦！</p>
</section>
<#include "../user.ftl">
<#include "../search.ftl">
<div class="sort-icon"></div>
<div class="sort-content-box">
    <div class="sort-mask"></div>
    <ul class="sort-content">
    <#if sort?exists>
        <li value="0" <#if sort==0>class="current"</#if>><p>默认排序</p></li>
        <li value="1" <#if sort==1>class="current"</#if>><p>价格由高到低</p></li>
        <li value="2" <#if sort==2>class="current"</#if>><p>价格由低到高</p></li>
    </#if>
    </ul>
</div>

<script src="${staticurl}/js/categorys.js"></script>
<script src="${staticurl}/js/main.js"></script>
</body>
</html>