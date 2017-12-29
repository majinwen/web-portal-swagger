<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/new-index.css">
    <title>新房首页</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<header class="main-top-header">
    <input id="url" type="hidden"  value="${router_city('/loupan')}">
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" class="search-link" placeholder="中骏·西山天璟">
    </div>
    <a href="javascript:;" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="个人中心"></a>
</header>
<div class="module-bottom-fill">
    <section class="banner-index-box">
        <div class="swiper-container carousel-swiper" id="index-swiper">
            <ul class="swiper-wrapper" id="house-pic-container">
                <li class="swiper-slide">
                    <img src="${staticurl}/images/newindex/sy_banner.png" alt="一阙藏中国">
                </li>
            </ul>
            <div class="swiper-pagination pictrue-index"></div>
        </div>
        <div class="banner-nav">
            <div class="banner-nav-item"><a href="${router_city('/loupan')}">
                <i class="all-buildings"></i><p>全部楼盘</p>
            </a></div>
            <div class="banner-nav-item"><a href="${router_city('/loupan?saleType=1')}">
                <i class="featured-properties"></i><p>在售楼盘</p>
            </a></div>
            <div class="banner-nav-item"><a href="${router_city('/loupan?saleType=5')}">
                <i class="houses-open"></i><p>即将开盘</p>
            </a></div>
            <div class="banner-nav-item"><a href="#">
                <i class="houses-intelligent"></i><p>懂房帝</p>
            </a></div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section class="bulletin-board">
        <div class="img"><img src="${staticurl}/images/newindex/sy_fctt.png" alt=""></div>
        <div class="text-scroll">
            <ul>
                <li>猛犸温泉高档小区开盘在即，各大商铺积极响</li>
                <li>猛犸温泉高档小区开盘在即，各大商铺积极响猛犸温泉高档小区开盘在即，各大商铺积极响</li>
                <li>猛犸温泉高档小区开盘在即，各大商铺猛犸温泉高档小区开盘在即，各大商铺积极响铺积极响铺积极响</li>
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="index-module-header">
            <h3>热门主题</h3>
            <#--<a href="#" class="more-arrows">&lt;#&ndash;<i class="arrows-right"></i>&ndash;&gt;</a>-->
        </div>
        <div class="hot-topic">
            <div class="column">
                <div class="hot-topic-item"><a href="#">
                    <div class="topic-item-content">
                        <h5>五环亦庄线</h5>
                        <p>200万买3居</p>
                    </div>
                    <img src="${staticurl}/images/newindex/xf_zt_image1.png" alt="">
                </a></div>
                <div class="hot-topic-item"><a href="#">
                    <div class="topic-item-content">
                        <h5>品牌促销</h5>
                        <p>谁说便宜没好货</p>
                    </div>
                    <img src="${staticurl}/images/newindex/xf_zt_image1.png" alt="">
                </a></div>
            </div>
            <div class="column">
                <div class="hot-topic-item"><a href="#">
                    <div class="topic-item-content">
                        <h5>精装现房</h5>
                        <p>不用装修不用等</p>
                    </div>
                    <img src="${staticurl}/images/newindex/xf_zt_image5.png" alt="">
                </a></div>
                <div class="hot-topic-item"><a href="#">
                    <div class="topic-item-content">
                        <h5>地铁沿线</h5>
                        <p>给你最美的距离</p>
                    </div>
                    <img src="${staticurl}/images/newindex/xf_zt_image5.png" alt="">
                </a></div>
                <div class="hot-topic-item"><a href="#">
                    <div class="topic-item-content">
                        <h5>家有二宝</h5>
                        <p>通透三居配套全</p>
                    </div>
                    <img src="${staticurl}/images/newindex/xf_zt_image5.png" alt="">
                </a></div>
            </div>
        </div>
    </section>
</div>
<section>
    <div class="index-module-header">
        <h3>新房推荐</h3>
        <a href="${router_city('/loupan')}" class="more-arrows"><i class="arrows-right"></i></a>
    </div>
    <ul><#if newbuilds?exists>
    <#assign builds = newbuilds['data']>
    <#list builds as map>
    <#if map_index==2>
        <li><a class="list-item new" href="#">
            <div class="clear">
                <div class="list-item-img-box">
                    <img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">
                </div>
                <div class="list-item-cont">
                    <h3 class="cont-block-1">中骏·西山天璟<em>别墅</em></h3>
                    <p class="cont-block-2 high-light-red">68000元/㎡</p>
                    <p class="cont-block-3">东城/88㎡—526㎡</p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>复式</span>
                        <span>五证齐全</span>
                        <span>花园洋房</span>
                    </div>
                    <div class="cont-block-sale">
                        <em>在售</em>
                    </div>
                </div>
            </div>
            <div class="new-active">
                <i class="icon"></i><em>活动：</em>
                <span>梦马温泉项目位于门头沟双屿岛...梦马温泉项目位于门...</span>
            </div>
        </a></li>
    </#if>
        <li><a class="list-item new" href="${router_city('/loupan/'+map['building_name_id']?c+'.html')}">
            <div class="clear">
                <div class="list-item-img-box">
                    <#if map['building_imgs']?exists>
                    <#assign item = map['building_imgs']?split(",")>
                    <#if item[0]?? && item[0] != ''><img src="${qiniuimage}/${item[0]}" alt="${map['building_name']}">
                        <#else><img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                    </#if>
                    </#if>
                </div>
                <div class="list-item-cont">
                    <span hidden="hidden">${map['building_name_id']!'暂无'}</span>
                    <h3 class="cont-block-1">${map['building_name']}<em>${map['property_type']}</em></h3>
                    <p class="cont-block-2 high-light-red"><#if map['average_price']?exists>${map['average_price']}/㎡</#if></p>
                    <p class="cont-block-3">
                        <#if map['nearsubway']??>
                        ${map['nearsubway']}
                        <#else>${map['district_name']}
                        </#if>
                        <#if map['house_min_area']??&&map['house_max_area']??>/${map['house_min_area']}㎡—${map['house_max_area']}㎡</#if>
                        </p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <#if map['building_tags']?exists>
                            <#assign item =  map['building_tags']>
                            <#list item as itemValue>
                                <span>${itemValue}</span>
                            </#list>
                            <#else><span>暂无</span>
                        </#if>

                    </div>
                    <div class="cont-block-sale">
                        <em>${map['sale_status_name']}</em>
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
    </#if>
    </ul>
</section>
<div class="new-index-pull-down">
    <p>想查看更多房源，跟我来！</p><img src="${staticurl}/images/newindex/sy_xf_icon_xl.png" alt="查看更多房源">
</div>
<#include "../user.ftl">
<#include "../search.ftl">

<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
</body>
</html>