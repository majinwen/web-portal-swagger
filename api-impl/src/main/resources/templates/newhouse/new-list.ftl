<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/dropload.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/list.css?v=${staticversion}">
    <title>来头条房产看新房</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<img class="shareTopImg" height="0" width="0" src="http://wap-qn.toutiaofangchan.com/logo/tt.jpg" alt="头条·房产">
<header class="main-top-header">
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
        <li data-mark="tab-price"><span><em>均价</em><i></i></span></li>
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
                    <li data-begin-price="0.0" data-end-price="20000.0">2万元以下</li>
                    <li data-begin-price="20000.0" data-end-price="30000.0">2-3万元</li>
                    <li data-begin-price="30000.0" data-end-price="40000.0">3-4万元</li>
                    <li data-begin-price="40000.0" data-end-price="60000.0">4-6万元</li>
                    <li data-begin-price="60000.0" data-end-price="80000.0">6-8万元</li>
                    <li data-begin-price="80000.0" data-end-price="800000.0">8万元以上</li>
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
                    <li data-type="5">五居及以上 <i></i></li>
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
                        <span data-info="1">住宅</span>
                        <span data-info="2">别墅</span>

                        <#--<span data-info="3">写字楼</span>
                        <span data-info="4">商铺</span>
                        <span data-info="5">底商</span>-->
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
                    <dt data-type="elevatorFlag">电梯</dt>
                    <dd>
                        <span class="only" data-info="1">有</span>
                        <span class="only" data-info="0">无</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="buildingType">建筑类型</dt>
                    <dd>
                        <span data-info="1">板楼</span>
                        <span data-info="2">塔楼</span>
                        <span data-info="3">板塔结合</span>
                        <#--<span data-info="4">砖楼</span>-->
                    </dd>
                </dl>
                <dl>
                    <dt data-type="saleType">销售状态</dt>
                    <dd>
                        <span data-info="0">售完</span>
                        <span data-info="1">在售</span>
                        <span data-info="5">待售</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="buildingFeature">楼盘特色</dt>
                    <dd>
                        <span data-info="1">近地铁</span>
                        <span data-info="7">车位充足</span>
                        <span data-info="9">500强房企</span>
                        <span data-info="10">优质物业</span>
                        <span data-info="11">购物方便</span>
                        <span data-info="12">教育配套</span>
                        <span data-info="13">医疗配套</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="deliverStyle">装修标准</dt>
                    <dd>
                        <span data-info="1">毛坯</span>
                        <span data-info="2">普通装修</span>
                        <span data-info="3">精装修</span>
                        <span data-info="4">豪华装修</span>
                        <span data-info="5">其他</span>
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
    <ul id="valueList"><#if builds?exists>
        <#list builds as map>
            <li><a class="list-item new" href="${router_city('/loupan/'+map['building_name_id']?c+'.html')}">
                <div class="clear">
                    <div class="list-item-img-box">
                        <#if map['building_title_img']?exists>
                            <#assign imgt = map['building_title_img']?split(",")>
                            <#if imgt[0]?? && imgt[0] != ''>
                                <img src="${qiniuimage}/${imgt[0]}-tt400x300" alt="${map['building_name']}">
                            <#else>
                                <img src="${staticurl}/images/global/tpzw_image.png" alt="${map['building_name']}">
                            </#if>
                        </#if>
                    </div>
                    <div class="list-item-cont">
                        <span hidden="hidden"><#if map['building_name_id']?exists>${map['building_name_id']}</#if></span>
                        <h3 class="cont-block-1">
                            <span class="ellipsis">${map['building_name']}</span>
                            <#if map['property_type']?exists>
                                <em>${map['property_type']}</em>
                            </#if>
                        </h3>
                        <p class="cont-block-2">
                            <em class="high-light-red">
                                <#if map['average_price']?exists && map['average_price'] gt 0>
                                ${map['average_price']}元/㎡
                                <#else>
                                    <#if map['total_price']?exists && map['total_price'] gt 0>
                                    ${map['total_price']}万元/套
                                    <#else>暂无价格
                                    </#if>
                                </#if>
                            </em>
                        </p>
                        <p class="cont-block-3">
                            <#if map['nearsubway']??>
                                <#assign rounditems = map['nearsubway']?split("$")>
                                <#if rounditems[2]?number gt 1000>
                                    <#assign x = rounditems[2]?number/1000>
                                    距离${rounditems[1]}[${rounditems[0]}] ${x?string("#.#")}km
                                <#else>
                                    距离${rounditems[1]}[${rounditems[0]}] ${rounditems[2]}m
                                </#if>
                            <#else>
                                <#if map['district_name']?exists>
                                    ${map['district_name']}
                                </#if>
                                <#if map['house_min_area']?exists&&map['house_max_area']?exists>
                                    / ${map['house_min_area']}㎡-${map['house_max_area']}㎡
                                </#if>
                            </#if>
                        </p>
                        <div class="cont-block-4 house-labelling gray middle">
                            <#if  map['building_tags']?exists>
                                <#assign item = map['building_tags']>
                                <#list item as itemValue>
                                    <#if itemValue?exists && itemValue_index<3>
                                        <span>${itemValue}</span>
                                    </#if>
                                </#list>
                            </#if>
                        </div>
                        <div class="cont-block-sale">
                            <em><#if map['sale_status_name']?exists>${map['sale_status_name']}</#if></em>
                        </div>
                    </div>
                </div>
                <#if map['activity_desc']?exists>
                <div class="new-active">
                    <i class="icon"></i><em>活动：</em>
                   <span>${map['activity_desc']}</span>
                </div>
                </#if>
            </a></li>
        </#list>
    </#if></ul>
    <p class="tip-box none">有新上房源，我们会及时通知您哦！</p>
</section>

<div class="sort-icon"></div>
<div class="sort-content-box">
    <div class="sort-mask"></div>
    <ul class="sort-content">
    <#if sort?exists>
        <li value="0" <#if sort==0>class="current"</#if>><p>默认排序</p></li>
        <li value="2" <#if sort==2>class="current"</#if>><p>价格由高到低</p></li>
        <li value="1" <#if sort==1>class="current"</#if>><p>价格由低到高</p></li>
    </#if>
    </ul>
</div>

<#include "../user.ftl">
<#include "../search.ftl">

<script id="listContent" type="text/html">
{{each data}}
<li><a class="list-item new" href="<%= $imports.router_city('/loupan/'+$value.building_name_id+'.html') %>">

    <div class="clear">
        <div class="list-item-img-box">
            {{if ($value.building_imgs) != ''}}
                <img src="${qiniuimage}/{{$value.building_title_img}}-tt400x300" alt="{{$value.building_name}}">
            {{else}}
                <img src="${staticurl}/images/global/tpzw_image.png" alt="{{$value.building_name}}">
            {{/if}}
        </div>
        <div class="list-item-cont">
            <span hidden="hidden">{{$value.building_name_id}}</span>
            <h3 class="cont-block-1">
                <span class="ellipsis">{{$value.building_name}}</span>
                <em>{{$value.property_type}}</em>
            </h3>
            <p class="cont-block-2">
                <em class="high-light-red">
                    {{if $value.average_price != null && $value.average_price > 0}}
                        {{$value.average_price}}元/㎡
                    {{else}}
                    {{if $value.total_price != null && $value.total_price > 0}}
                    {{$value.total_price}}万元/套
                    {{else}}
                        售价待定
                    {{/if}}
                    {{/if}}
                </em>
            </p>
            <p class="cont-block-3">
                {{if $value.nearsubway}}
                    {{if $value.subwayDesc}}
                        {{$value.subwayDesc}}
                    {{/if}}
                {{else}}
                    {{$value.district_name}}
                    {{if $value.house_min_area != null}}
                        {{if $value.house_max_area != null}}
                            / {{$value.house_min_area}}㎡-{{$value.house_max_area}}㎡
                        {{/if}}
                    {{/if}}
                {{/if}}
            </p>
            <div class="cont-block-4 house-labelling gray middle">
                {{each $value.building_tags tag i}}
                    {{if i<3}}
                        <span>{{tag}}</span>
                    {{/if}}
                {{/each}}
            </div>
            <div class="cont-block-sale">
                <em>{{$value.sale_status_name}}</em>
            </div>
        </div>
    </div>
    {{if $value.activity_desc != null}}
        <div class="new-active">
            <i class="icon"></i><em>活动：</em>
            <span>{{$value.activity_desc}}</span>
        </div>
    {{/if}}
</a></li>
{{/each}}
</script>

</body>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>
<script src="${staticurl}/js/dropload.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/list-category.js?v=${staticversion}"></script>
<script src="${staticurl}/js/template-web.js?v=${staticversion}"></script>
<script>
    $(function () {
        var url = document.referrer;
        if(url.indexOf("/xinfang") > 0){
            zhuge.track("搜索_新房",{
                "关键词":GetQueryString("keyword"),
               "返回结果数量":$("input['name['total']']").val()
            })
        }else{
            zhuge.track("搜索_大首页",{
                "搜索类型":"新房",
                "关键词":GetQueryString("keyword"),
               "返回结果数量":$("input['name['total']']").val()
            })
        }
    });
    function GetQueryString(name) {
        var r = decodeURI(req[name]);
        if(r!=null)return r; return null;
    }
    function joinParams(req) {
        var targetUrl = '';

        for (var key in req) {
            if (null != req[key]) {
                targetUrl += '&' + key + "=" + req[key];
            }
        }

        if (targetUrl.length > 1) {
            targetUrl = '?' + targetUrl.substring(1);
        }

        return targetUrl;
    }
</script>
</html>