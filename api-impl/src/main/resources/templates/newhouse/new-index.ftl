<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/new-index.css">
    <title>上头条 找新房</title>
    <meta name="description" content="让美好生活 来找你">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<header class="main-top-header">
    <input id="url" type="hidden"  value="${router_city('/loupan')}">
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" class="search-link" placeholder="">
    </div>
    <a href="javascript:;" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="个人中心"></a>
</header>
<div class="module-bottom-fill">
    <section class="banner-index-box">
        <div class="swiper-container carousel-swiper" id="index-swiper">
            <ul class="swiper-wrapper" id="house-pic-container">
                <li class="swiper-slide">
                    <img src="${staticurl}/images/newindex/sy_banner.jpg" alt="美好新居 尽在头条">
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
            <#--<div class="banner-nav-item"><a href="#">
                <i class="houses-intelligent"></i><p>懂房帝</p>
            </a></div>-->
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section class="bulletin-board">
        <div class="img"><img src="${staticurl}/images/newindex/sy_fctt.png" alt=""></div>
        <div class="text-scroll">
            <ul>
                <li><a href="http://www.toutiaopage.com/tetris/page/1586114170135565/"><em class="scroll-text-tag">政策</em>2018年五险一金将迎来5个变化 每个都是好消息！</a></li>
                <li><a href="http://www.toutiaopage.com/tetris/page/1586541609479182/"><em class="scroll-text-tag">问答</em>如果你现在有50万，是买房还是存着？</a></li>
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
                <div class="hot-topic-item"><a href="https://www.toutiao.com/i6507390085581767176/?tt_from=weixin&utm_campaign=client_share&timestamp=1515143399&app=news_article&utm_source=weixin&iid=22476244001&utm_medium=toutiao_ios&wxshare_count=1">
                    <div class="topic-item-content">
                        <h5>房价排行榜</h5>
                        <p>2017年房价排行榜出炉</p>
                    </div>
                    <img src="${staticurl}/images/newindex/xf_zt_image1.jpg" alt="房价排行榜">
                </a></div>
                <div class="hot-topic-item"><a href="https://www.toutiao.com/i6507035043548889607/?tt_from=weixin&utm_campaign=client_share&timestamp=1515143385&app=news_article&utm_source=weixin&iid=22476244001&utm_medium=toutiao_ios&wxshare_count=1">
                    <div class="topic-item-content">
                        <h5>图说豪宅</h5>
                        <p>北京豪宅震撼大片</p>
                    </div>
                    <img src="${staticurl}/images/newindex/xf_zt_image2.jpg" alt="图说豪宅">
                </a></div>
            </div>
            <div class="column">
                <div class="hot-topic-item"><a href="http://www.toutiaopage.com/tetris/page/1587903362775054/">
                    <div class="topic-item-content">
                        <h5>90平小三居</h5>
                        <p>改善族买房标配</p>
                    </div>
                    <img src="${staticurl}/images/newindex/xf_zt_image3.jpg" alt="90平小三居">
                </a></div>
                <div class="hot-topic-item"><a href="http://www.toutiaopage.com/tetris/page/1587905703017485/">
                    <div class="topic-item-content">
                        <h5>万科楼盘</h5>
                        <p>品牌房企集中亮相</p>
                    </div>
                    <img src="${staticurl}/images/newindex/xf_zt_image4.jpg" alt="万科楼盘">
                </a></div>
                <div class="hot-topic-item"><a href="http://www.toutiaopage.com/tetris/page/1587895837527054/">
                    <div class="topic-item-content">
                        <h5>下一站南五环</h5>
                        <p>南五环热盘盘点</p>
                    </div>
                    <img src="${staticurl}/images/newindex/xf_zt_image5.jpg" alt="下一站南五环">
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
        <#--<#if map_index==2>-->
            <#--<li><a class="list-item new" href="#">-->
                <#--<div class="clear">-->
                    <#--<div class="list-item-img-box">-->
                        <#--<img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">-->
                    <#--</div>-->
                    <#--<div class="list-item-cont">-->
                        <#--<h3 class="cont-block-1">-->
                            <#--<span class="ellipsis">中骏·西山天璟</span>-->
                            <#--<em>别墅</em>-->
                        <#--</h3>-->
                        <#--<p class="cont-block-2 high-light-red">68000元/㎡</p>-->
                        <#--<p class="cont-block-3">东城/88㎡—526㎡</p>-->
                        <#--<div class="cont-block-4 house-labelling gray middle">-->
                            <#--<span>复式</span>-->
                            <#--<span>五证齐全</span>-->
                            <#--<span>花园洋房</span>-->
                        <#--</div>-->
                        <#--<div class="cont-block-sale">-->
                            <#--<em>在售</em>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="new-active">-->
                    <#--<i class="icon"></i><em>活动：</em>-->
                    <#--<span>梦马温泉项目位于门头沟双屿岛...梦马温泉项目位于门...</span>-->
                <#--</div>-->
            <#--</a></li>-->
        <#--</#if>-->
        <#if map_index==5>
            <#break>
        </#if>
        <li><a class="list-item new" href="${router_city('/loupan/'+map['building_name_id']?c+'.html')}">
            <div class="clear">
                <div class="list-item-img-box">
                    <#if map['building_title_img']?exists>
                    <#assign item = map['building_title_img']?split(",")>
                    <#if item[0]?? && item[0] != ''><img src="${qiniuimage}/${item[0]}-tt400x300" alt="${map['building_name']}">
                        <#else><img src="${staticurl}/images/global/tpzw_image.png" alt="${map['building_name']}">
                    </#if>
                    </#if>
                </div>
                <div class="list-item-cont">
                    <span hidden="hidden">${map['building_name_id']!'暂无'}</span>
                    <h3 class="cont-block-1">
                        <span class="ellipsis">${map['building_name']}</span>
                        <em>${map['property_type']}</em>
                    </h3>
                    <p class="cont-block-2 high-light-red">
                        <#if map['average_price']?exists && map['average_price'] gt 0>
                            ${map['average_price']}/㎡
                        <#else>
                            售价待定
                        </#if>
                    </p>
                    <p class="cont-block-3">
                        <#if map['nearsubway']??>
                        ${map['nearsubway']}
                        <#else>${map['district_name']}
                        </#if>
                        <#if map['house_min_area']??&&map['house_max_area']??>/ ${map['house_min_area']}㎡-${map['house_max_area']}㎡</#if>
                        </p>
                    <div class="cont-block-4 house-labelling gray middle">
                        <#if map['building_tags']?exists>
                            <#assign item =  map['building_tags']>
                            <#list item as itemValue>
                                <span>${itemValue}</span>
                            </#list>
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
<a href="${router_city('/loupan')}" class="new-index-pull-down">
    <p>想查看更多房源，跟我来！</p><img src="${staticurl}/images/newindex/sy_xf_icon_xl.png" alt="查看更多房源">
</a>
<#include "../user.ftl">
<#include "../search.ftl">

<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="${staticurl}/js/URI.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
</body>
</html>