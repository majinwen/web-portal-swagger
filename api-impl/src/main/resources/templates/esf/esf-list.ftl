<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/list.css">
    <title>二手房列表</title>
</head>
<body>
<header>
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" placeholder="中骏·西山天璟">
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
                    <li class="current">不限</li>
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
                <#assign itemLocation=map['housePlotLocation']>
                <a class="list-item" href="/queryByHouseIdandLocation/${map.houseId}/${itemLocation[0]}/${itemLocation[1]}">
                <input type="hidden" name="houseId" value="${map.houseId}"/>

                <input type="hidden" name="lat" value="${itemLocation[0]}"/>
                <input type="hidden" name="lon" value="${itemLocation[1]}"/>
                <div class="clear">
                    <div class="list-item-img-box">
                        <#assign item=map['housePhoto']>
                        <img src="${staticurl}/images/esf/${item[0]}" alt="${map.houseTitle}">
                    </div>
                    <div class="list-item-cont">
                        <h3 class="cont-block-1">${map.houseTitle}</h3>
                        <p class="cont-block-2">${map.houseArea}㎡/${map.houseType}/${map.houseOrientation}/${map.housePlotName}</p>
                        <#assign item=map['houseToSubwayDistance']>
                        <#if map['key']?exists>
                            <#if item[map['key']]?exists>
                                <p class="cont-block-3 distance"><i class="icon"></i>${item[map['key']]}</p>
                            </#if >
                            <#else >
                                <p class="cont-block-3 distance"><i class="icon"></i>${map.areaName}[${map.houseBusinessName}]</p>
                        </#if>
                        <div class="cont-block-4 house-labelling gray middle">
                            <#assign item =  map['houseLabel']>
                            <#list item as itemValue>
                                <span>${itemValue}</span>
                            </#list>
                        </div>
                        <div class="cont-block-price">
                            <em>${map.houseTotalPrices}万</em>
                            <span>${map.houseUnitCost}元/㎡</span>
                        </div>
                    </div>
                </div>
            </a></li>
        </#list>
    </#if>
    </ul>
    <p class="tip-box">有新上房源，我们会及时通知您哦！</p>
</section>
<#-- 个人中心 侧栏菜单 -->
<section class="side-nav-cont">
    <div class="side-user">
        <img src="${staticurl}/images/global/grcl_tx.png" alt="用户头像">
        <p>188********</p>
    </div>
    <div class="side-nav-item-wrapper">
        <ul class="side-nav-item item-link">
            <li><a href="#"><i class="icon-index"></i><span>首页</span></a></li>
            <li><a href="#"><i class="icon-esf"></i><span>找二手房</span></a></li>
            <li><a href="#"><i class="icon-plot"></i><span>找小区</span></a></li>
            <li><a href="#"><i class="icon-new"></i><span>找新房</span></a></li>
        </ul>
        <ul class="side-nav-item item-my">
            <li><a href="#"><i class="icon-collect"></i><span>我的收藏</span></a></li>
            <li><a href="#"><i class="icon-report"></i><span>我的报告</span></a></li>
        </ul>
    </div>
    <div class="side-house-intelligent">
        <a href="#"><em>智能找房</em></a>
    </div>
</section>
<div class="scroll-mask"></div>

<script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
<script src="${staticurl}/js/categorys.js"></script>
<script src="${staticurl}/js/main.js"></script>
</body>
</html>