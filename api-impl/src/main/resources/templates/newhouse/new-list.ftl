<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/list.css">
    <title>新房列表</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<header class="main-top-header">
    <input id="url" type="hidden" value="/newhouse/searchNewHouse">
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
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
                    <dt data-type="propertyTypeId">物业类型</dt>
                    <dd>
                        <span data-info="1">住宅</span>
                        <span data-info="2">别墅</span>
                        <span data-info="3">写字楼</span>
                        <span data-info="4">商铺</span>
                        <span data-info="5">底商</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="houseAreaSize">面积</dt>
                    <dd>
                        <span data-info="0,60">60以下</span>
                        <span data-info="60,90">60-90</span>
                        <span data-info="90,120">90-120</span>
                        <span data-info="120,1000">120以上</span>
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
                        <span data-info="4">砖楼</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="saleType">销售状态</dt>
                    <dd>
                        <span data-info="1">售完</span>
                        <span data-info="2">在售</span>
                        <span data-info="3">不在售</span>
                        <span data-info="4">出租</span>
                        <span data-info="4">待租</span>
                        <span data-info="4">待售</span>
                    </dd>
                </dl>
                <dl>
                    <dt data-type="buildingFeature">楼盘特色</dt>
                    <dd>
                        <span data-info="1">别墅</span>
                        <span data-info="2">花园洋房</span>
                        <span data-info="3">近地铁</span>
                        <span data-info="4">车位充足</span>
                        <span data-info="5">低密度</span>
                        <span data-info="6">高绿化</span>
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
</section>
<section>
    <ul><#if builds?exists>
        <#list builds as map>
            <li><a class="list-item new" href="/newhouse/getNewHouseDetails?id=${map['building_name_id']?c}">
                <div class="clear">
                    <div class="list-item-img-box">
                        <#if map['building_imgs']?exists>
                            <#assign imgt = map['building_imgs']?split(",")>
                        <#--   <#assign item = map['building_imgs']>
                           <#if item[0]?exists>
                               <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="${map['building_name']}">
                           </#if>-->
                            <img src="<#--${staticurl}-->${qiniuimage}/${imgt[0]}" alt="${map['building_name']}">
                        <#else >
                            <img src="${qiniuimage}/images/esf/esxq_xq_image2@3x.png" alt="${map['building_name']}">
                        </#if>
                    </div>
                    <div class="list-item-cont">
                        <span hidden="hidden"><#if map['building_name_id']?exists>${map['building_name_id']}</#if></span>
                        <h3 class="cont-block-1">${map['building_name']!"暂无"}
                             <em>${map['property_type']!"暂无"}</em>
                        </h3>
                        <p class="cont-block-2"><em class="high-light-red">${map['average_price']!0}</em>元/㎡</p>
                        <p class="cont-block-3">
                            <#if map['nearsubway']??>
                            <#assign rounditems = map['nearsubway']?split("$")>
                            距离${rounditems[1]!""}[${rounditems[0]!'暂无'}] ${rounditems[2]?number/1000}km
                            <#else>
                                <#if map['district_name']?exists>${map['district_name']}</#if><#if map['house_min_area']?exists&&map['house_max_area']?exists>/${map['house_min_area']}㎡—${map['house_max_area']}㎡</#if>
                            </#if>
                        </p>
                        <div class="cont-block-4 house-labelling gray middle">
                            <#if  map['building_tags']?exists>
                                <#assign item =  map['building_tags']>
                                <#list item as itemValue>
                                    <#if itemValue?exists>
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
</section>
<#include "../user.ftl">
<#include "../search.ftl">
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

<script src="${staticurl}/js/categorys.js"></script>
<script src="${staticurl}/js/main.js?version=123"></script>
</body>
</html>
