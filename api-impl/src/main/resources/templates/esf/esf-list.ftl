<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/dropload.css">
    <link rel="stylesheet" href="${staticurl}/css/list.css">
    <meta name="description" content="头条房产，帮你发现美好生活">
    <title>来头条房产二手房</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
    <#include "../StatisticsHeader.ftl">
</head>
<#setting url_escaping_charset="UTF-8">
<body>
<img height="1" width="1" hidden src="http://wap-qn.toutiaofangchan.com/logo/tt.jpg" alt="头条·房产">
<header class="main-top-header">
    <input id="url" type="hidden" value="${router_city('/esf')}">
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" class="search-link" placeholder="" value="<#if RequestParameters.keyword??>${RequestParameters.keyword}</#if>">
    </div>
    <a href="javascript:;" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="头条·房产"></a>
</header>
<section class="category-box">
    <ul id="category-tab">
        <li data-mark="tab-place"><span><em>区域</em><i></i></span></li>
        <li data-mark="tab-price"><span><em>总价</em><i></i></span></li>
        <li data-mark="tab-type"><span><em>户型</em><i></i></span></li>
        <li data-mark="tab-more"><span><em>更多</em><i></i></span></li>
    </ul>
    <div class="global-mark none">
        <div class="category-cont">
            <!-- 区域 -->
            <div class="filter-item" data-mark="panel-place">
                <div class="place-list">
                    <ul id="level1" class="nav" data-mark="level1"></ul>
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
                        <li data-begin-price="300.0" data-end-price="400.0">300-400万</li>
                        <li data-begin-price="400.0" data-end-price="500.0">400-500万</li>
                        <li data-begin-price="500.0" data-end-price="800.0">500-800万</li>
                        <li data-begin-price="800.0" data-end-price="1000.0">800-1000万</li>
                        <li data-begin-price="1000.0" data-end-price="100000000.0">1000万以上</li>
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
                        <dt data-type="propertyTypeId">物业类型</dt>
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
                            <span data-info="3">随时看</span>
                            <span data-info="5">满二年</span>
                            <span data-info="4">满五年</span>
                            <span data-info="2">近公园</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="houseAreaSize">面积</dt>
                        <dd>
                            <span data-info="[0-60]">60平以下</span>
                            <span data-info="[60-90]">60-90平</span>
                            <span data-info="[90-110]">90-110平</span>
                            <span data-info="[110-130]">110-130平</span>
                            <span data-info="[130-150]">130-150平</span>
                            <span data-info="[150-200]">150-200平</span>
                            <span data-info="[200-10000]">200平以上</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="houseYearId">楼龄</dt>
                        <dd>
                            <span class="only" data-info="[0-5]">5年内</span>
                            <span class="only" data-info="[0-10]">10年内</span>
                            <span class="only" data-info="[0-15]">15年内</span>
                            <span class="only" data-info="[0-20]">20年内</span>
                            <span class="only" data-info="[20-120]">20年以上</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt data-type="elevatorFlag">电梯</dt>
                        <dd>
                            <span class="only" data-info="1">有</span>
                            <span class="only" data-info="2">无</span>
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
                        <dt data-type="ownership">权属</dt>
                        <dd>
                            <span data-info="1">已购公房</span>
                            <span data-info="2">商品房</span>
                            <span data-info="3">空置房</span>
                            <span data-info="4">使用权房</span>
                            <span data-info="5">央产</span>
                            <span data-info="6">经济适用房</span>
                        </dd>
                    </dl>
                </div>
                <div class="submit-wrapper">
                    <a href="javascript:;" class="operation-button more-reset" id="moreReset">重置</a>
                    <a href="javascript:;" class="operation-button more-submit" id="moreSubmit">确定</a>
                </div>
            </div>
        </div>
    </div>
</section>
<section id="result-section">
    <#if builds?exists><ul id="valueList">
        <#list builds as map>
            <li><a class="list-item" href="${router_city('/esf/'+map.houseId+'.html')}">
                <div class="clear">
                    <div class="list-item-img-box">
                        <#assign item=map['housePhotoTitle']>
                        <#if item?? && item!=''><img src="${item}" alt="${map.houseTitle}">
                            <#else ><img src="${staticurl}/images/global/tpzw_image.png" alt="${map.houseTitle}">
                        </#if>
                    </div>
                    <div class="list-item-cont">
                        <h3 class="cont-block-1"><span>${map.houseTitle}</span></h3>
                        <p class="cont-block-2">
                            <#if map.buildArea?exists&&(map.buildArea>0)>
                                ${map.buildArea}㎡
                            </#if>
                            <#if map.room?exists&&map.hall?exists>
                                / ${map.room}室${map.hall}厅
                            </#if>
                            <#if map.forwardName?exists>
                                / ${map.forwardName}
                            </#if>
                            <#if map.plotName?exists>
                                / ${map.plotName}
                            </#if>
                        </p>
                        <#if map['subwayDistince']?exists>
                            <#assign item=map['subwayDistince']>
                            <#if map['key']?exists>
                                <#if item[map['key']]?exists>
                                    <p class="cont-block-3 distance"><i class="icon"></i>
                                        <#assign rounditems=item[map['key']]?split("$")>
                                        <#if rounditems[2]?number gt 1000>
                                            <#assign x = rounditems[2]?number/1000>
                                            距离${rounditems[1]}[${rounditems[0]}] ${x?string("#.#")}km
                                        <#else>
                                            距离${rounditems[1]}[${rounditems[0]}] ${rounditems[2]}m
                                        </#if>
                                    </p>
                                </#if>
                            <#else>
                                <p class="cont-block-3 distance"><i class="icon"></i><#if map.area?exists&&map.area!=''&&map.houseBusinessName?exists&&map.houseBusinessName!=''>${map.area}-${map.houseBusinessName}<#else></#if></p>
                            </#if>
                        <#else >
                            <p class="cont-block-3 distance"><i class="icon"></i><#if map.area?exists&&map.houseBusinessName?exists>${map.area}-${map.houseBusinessName}<#else></#if></p>
                        </#if>
                        <div class="cont-block-4 house-labelling gray middle esf">
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
                            <#if map.houseTotalPrices?exists && map.houseTotalPrices?number gt 0>
                                <em>${map.houseTotalPrices}万</em>
                            </#if>
                            <#if map.houseTotalPrices?exists && map.buildArea?exists>
                                <#if map.houseTotalPrices?number gt 0 && map.buildArea?number gt 0>
                                    <span>${((map.houseTotalPrices / map.buildArea)?if_exists?string("##.0"))?number * 10000}元/㎡</span>
                                </#if>
                            </#if>
                        </div>
                    </div>
                </div>
            </a></li>
        </#list>
    </ul></#if>
    <p class="tip-box none">有新上房源，我们会及时通知您哦！</p>
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

<script id="listContent" type="text/html">
    {{each data}}
    <li><a class="list-item" href="${router_city('/esf/{{$value.houseId}}.html')}">
        <div class="clear">
            <div class="list-item-img-box">
                {{if $value.housePhotoTitle && $value.housePhotoTitle.length > 0}}
                    <img src="{{$value.housePhotoTitle}}" alt="{{$value.houseBusinessName}}">
                {{else}}
                    <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                {{/if}}
            </div>
            <div class="list-item-cont">
                <h3 class="cont-block-1"><span>{{$value.houseTitle}}</span></h3>
                <p class="cont-block-2">
                    {{if $value.buildArea && $value.buildArea > 0}}
                        {{$value.buildArea}}㎡
                    {{/if}}
                    {{if $value.room && $value.hall}}
                        / {{$value.room}}室{{$value.hall}}厅
                    {{/if}}
                    {{if $value.forwardName}}
                        / {{$value.forwardName}}
                    {{/if}}
                    {{if $value.plotName}}
                        / {{$value.plotName}}
                    {{/if}}
                </p>
                <p class="cont-block-3 distance">
                    <i class="icon"></i>

                    {{if $value.subwayDesc}}
                        {{$value.subwayDesc}}
                    {{else}}
                        {{if $value.area && $value.houseBusinessName}}
                            {{$value.area}}-{{$value.houseBusinessName}}
                        {{/if}}
                    {{/if}}
                </p>
                <div class="cont-block-4 house-labelling gray middle esf">
                    {{if $value.tagsName}}
                        {{each $value.tagsName value index}}
                            <span>{{value}}</span>
                        {{/each}}
                    {{/if}}
                </div>
                <div class="cont-block-price">
                    {{if $value.houseTotalPrices && $value.houseTotalPrices > 0}}
                        <em>{{$value.houseTotalPrices}}万</em>
                    {{/if}}
                    {{if $value.houseTotalPrices && $value.buildArea}}
                        {{if $value.houseTotalPrices > 0 && $value.buildArea > 0}}
                            <span>{{$value.unitCost}}元/㎡</span>
                        {{/if}}
                    {{/if}}
                </div>
            </div>
        </div>
    </a></li>
    {{/each}}
</script>
</body>
<script src="${staticurl}/js/URI.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
<script src="${staticurl}/js/dropload.min.js"></script>
<script src="${staticurl}/js/list-category.js"></script>
<script src="${staticurl}/js/template-web.js"></script>
</html>