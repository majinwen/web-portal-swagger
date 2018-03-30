<!DOCTYPE html>
<html class="no-js">
<head>
    <#include "staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/index.css?v=${staticversion}">
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <title>头条房产 发现美好</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
    <#include "StatisticsHeader.ftl">
</head>
<body>
<header class="main-top-header gradient">
    <a href="/" onclick="dashouyelogo(this)" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" class="search-link" placeholder="">
    </div>
    <a href="javascript:;" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="个人中心"></a>
</header>
<div class="module-bottom-fill">
    <section class="banner-index-box">
        <div class="swiper-container carousel-swiper" id="index-swiper">
            <ul class="swiper-wrapper" id="topbanner"></ul>
            <div class="swiper-pagination pictrue-index"></div>
        </div>
        <div class="banner-nav">
            <div class="banner-nav-item index-nav-item"><a id="index-xinfang" class="index-xinfang" href="${router_city('/xinfang/')}" onclick="zhuge.track('导航_大首页',{'导航名称':'新房','页面来源URL':window.location.href})">
                <i class="index-new-icon"></i><p>新房</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a class="index-esf" href="${router_city('/esf/')}">
                <i class="index-esf-icon"></i><p>二手房</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a id="index-renthouse" class="index-renthouse" href="${router_city('/zufang/')}" onclick="zhuge.track('导航_大首页',{'导航名称':'租房','页面来源URL':window.location.href})">
                <i class="index-rent-icon"></i><p>租房</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a class="index-xiaoqu">
                <i class="index-plot-icon"></i><p>小区</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a id="index-findhouse" class="index-findhouse" href="${router_city('/findhouse/')}" onclick="zhuge.track('导航_大首页',{'导航名称':'懂房帝','页面来源URL':window.location.href})">
                <i class="index-intelligent-icon"></i><p>懂房帝</p>
            </a></div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section class="bulletin-board">
        <div class="img index-img"><a href="http://www.chengzijianzhan.com/tetris/page/1588848809361416/"><img src="${staticurl}/images/index/index-building-entrance.png" alt="楼市聚焦"></a></div>
        <div class="text-scroll index-text" id="shoppingGuide">
            <ul id="ul_index_lunbo_guanggao">

            </ul>
        </div>
    </section>
</div>

<div class="module-bottom-fill">
    <section>
        <div class="index-module-header border-bot-none">
            <h3>精选主题</h3>
        </div>
        <div class="hot-topic pt0">
            <div class="column">
                <div class="hot-topic-item index-topic-item" id='ad-positon-lefttop'></div>
                <div class="hot-topic-item index-topic-item" id='ad-positon-leftright'></div>
            </div>
            <div class="column">
                <div class="hot-topic-item index-topic-item" id='ad-positon-leftbottom'></div>
                <div class="hot-topic-item index-topic-item" id="ad-positon-rightbottom"></div>
            </div>
        </div>
    </section>
</div>

<div class="module-bottom-fill">
    <section>
        <div class="index-module-header border-bot-none">
            <h3>北京市场成交行情</h3>
        </div>
        <div class="markets-quotations">
            <#if TradeQuotations['newHouse']?exists&&TradeQuotations['newHouse']!=''>
            <div class="row clear">
                <#if TradeQuotations['newHouseHref']?exists&&TradeQuotations['newHouseHref']!=''>
                    <a href=${TradeQuotations['newHouseHref']}>
                <#else >
                    <a href="#">
                </#if>
                    <div class="cloumn">
                        <h6>昨日新房成交量</h6>
                        <div><#if TradeQuotations['newHouse']?split(',')[0]?exists&&TradeQuotations['newHouse']?split(',')[0]?number gt 0><em>${TradeQuotations['newHouse']?split(',')[0]}</em>套<#else >暂无数据</#if>
                        <#if TradeQuotations['newHouse']?split(',')[1]?exists&&TradeQuotations['newHouse']?split(',')[1]?number gt 0><span class="high-light-red">${TradeQuotations['newHouse']?split(',')[1]}%↑
                        <#elseif TradeQuotations['newHouse']?split(',')[1]?exists&&TradeQuotations['newHouse']?split(',')[1]?number lt 0><span class="high-light-green"> ${TradeQuotations['newHouse']?split(',')[1]?number?abs}%↓ <#else >/暂无数据</#if>
                        </span></div>
                    </div>
                    <div class="cloumn">
                        <h6>上周新房成交均价</h6>
                        <div><#if TradeQuotations['newHouse']?split(',')[2]?exists&&TradeQuotations['newHouse']?split(',')[2]?number gt 0 ><em>${TradeQuotations['newHouse']?split(',')[2]}</em><#else >暂无数据</#if>元/㎡</div>
                    </div>
                </a>
            </div>
            </#if>
            <#if TradeQuotations['esfHouse']?exists&&TradeQuotations['esfHouse']!=''>
            <div class="row clear">
                <#if TradeQuotations['esfHouseHref']?exists&&TradeQuotations['esfHouseHref']!=''>
                    <a href=${TradeQuotations['esfHouseHref']}>
                <#else >
                    <a href="#">
                </#if>
                    <div class="cloumn">
                        <h6>上周二手房成交量</h6>
                        <div><#if TradeQuotations['esfHouse']?split(',')[0]?exists&&TradeQuotations['esfHouse']?split(',')[0]?number gt 0><em>${TradeQuotations['esfHouse']?split(',')[0]}</em>套<#else >暂无数据</#if>
                        <#if TradeQuotations['esfHouse']?split(',')[1]?exists&&TradeQuotations['esfHouse']?split(',')[1]?number gt 0><span class="high-light-red">${TradeQuotations['esfHouse']?split(',')[1]}%↑
                        <#elseif TradeQuotations['esfHouse']?split(',')[1]?exists&&TradeQuotations['esfHouse']?split(',')[1]?number lt 0><span class="high-light-green"> ${TradeQuotations['esfHouse']?split(',')[1]?number?abs}%↓ <#else >/暂无数据</#if>
                        </span></div>
                    </div>
                    <div class="cloumn">
                        <#if TradeQuotations['month']?exists&&TradeQuotations['month']?number gt 0>
                        <h6>${TradeQuotations['month']}月二手房参考均价</h6>
                        </#if>
                        <div><#if TradeQuotations['esfHouse']?split(',')[2]?exists&&TradeQuotations['esfHouse']?split(',')[2]?number gt 0 ><em>${TradeQuotations['esfHouse']?split(',')[2]}</em><#else >暂无数据</#if>元/㎡</div>
                        <#--<div><em>57650</em>元/㎡</div>-->
                    </div>
                </a>
            </div>
            </#if>
        </div>
    </section>
</div>
<section id="esf_desc">
    <div class="index-module-header">
        <h3>最新挂牌二手房</h3>
    </div>
    <ul><#if esfList?exists>
        <#list esfList as map>
        <#--<#if map_index==3>-->
        <#--<li><a class="list-item new new-ad-item" href="#">-->
        <#--<div class="list-item-cont-ad">-->
        <#--<h3 class="cont-block-1">新龙城</h3>-->
        <#--<p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>-->
        <#--<p class="cont-block-2">2008年建成</p>-->
        <#--</div>-->
        <#--<div class="clear">-->
        <#--<div class="list-item-img-box">-->
        <#--<img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">-->
        <#--</div>-->
        <#--<div class="list-item-img-box">-->
        <#--<img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">-->
        <#--</div>-->
        <#--<div class="list-item-img-box">-->
        <#--<img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">-->
        <#--</div>-->
        <#--</div>-->
        <#--<div class="pr">-->
        <#--<div class="cont-block-4 house-labelling gray middle">-->
        <#--<span>复式</span>-->
        <#--<span>五证齐全</span>-->
        <#--<span>花园洋房</span>-->
        <#--</div>-->
        <#--<p class="cont-block-2 high-light-red">68000元/㎡</p>-->
        <#--</div>-->
        <#--</a></li>-->
        <#--<#elseif map_index==4>-->
        <#--<#break>-->
        <#--</#if>-->
            <#if map_index==5>
                <#break>
            </#if>
            <li><a id="${map_index+1}" class="list-item" href="${router_city('/esf/'+map.houseId+'.html')}">
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
                                / <#if map.room?number lt 99 >${map.room}<#elseif map.room?number gte 99 >多</#if>室<#if map.hall?number lt 99>${map.hall}<#elseif map.hall?number gte 99>多</#if>厅
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
                                <#else>
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
                                    <span>${(((map.houseTotalPrices?number / (map.buildArea?number))) * 10000)}元/㎡</span>
                                </#if>
                            </#if>
                        </div>
                    </div>
                </div>
            </a></li>
        </#list>
    </#if></ul>
</section>
<section>
    <div class="index-module-header">
        <h3>最近热销新房</h3>
    </div>
    <ul id="index-recommend-newhouse"><#if newbuilds?exists>
        <#assign builds = newbuilds['data']>
        <#list builds as map>
            <#--<#if map_index==3>-->
            <#--<li><a class="list-item new new-ad-item" href="#">-->
                <#--<div class="list-item-cont-ad">-->
                    <#--<h3 class="cont-block-1">-->
                        <#--<span>中骏·西山天璟</span>-->
                        <#--<em>别墅</em>-->
                    <#--</h3>-->
                    <#--<p class="cont-block-3">东城/88㎡—526㎡</p>-->
                <#--</div>-->
                <#--<div class="clear">-->
                    <#--<div class="list-item-img-box">-->
                        <#--<img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">-->
                    <#--</div>-->
                    <#--<div class="list-item-img-box">-->
                        <#--<img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">-->
                    <#--</div>-->
                    <#--<div class="list-item-img-box">-->
                        <#--<img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="pr">-->
                    <#--<div class="cont-block-4 house-labelling gray middle">-->
                        <#--<span>复式</span>-->
                        <#--<span>五证齐全</span>-->
                        <#--<span>花园洋房</span>-->
                    <#--</div>-->
                    <#--<p class="cont-block-2 high-light-red">68000元/㎡</p>-->
                <#--</div>-->

                <#--<div class="new-active">-->
                    <#--<i class="icon"></i><em>活动：</em>-->
                    <#--<span>梦马温泉项目位于门头沟双屿岛...梦马温泉项目位于门...</span>-->
                <#--</div>-->
            <#--</a></li></#if>-->
            <#if map_index==5>
                <#break>
            </#if>
            <li><a class="list-item new" href="${router_city('/loupan/'+map['building_name_id']?c+'.html')}">
                <div class="clear">
                    <div class="list-item-img-box">
                        <#if map['building_title_img']?exists>
                            <#assign item = map['building_title_img']?split(",")>
                            <#if item[0]?? && item[0] != ''><img src="${qiniuimage}/${item[0]}-tt400x300" alt="${map['building_name']}">
                            <#else><img src="${staticurl}/images/global/tpzw_image.png" alt="${map['building_name']}"></#if>
                        </#if>
                    </div>
                    <div class="list-item-cont">
                        <span hidden="hidden">${map['building_name_id']!'暂无'}</span>
                        <h3 class="cont-block-1">
                            <span class="ellipsis">${map['building_name']}</span>
                            <em><#if map['property_type']?exists>${map['property_type']}</#if></em>
                        </h3>
                        <#if map['average_price']?exists && map['average_price'] gt 0>
                            <p class="cont-block-2 high-light-red">${map['average_price']}元/㎡</p>
                        <#else>
                            <#if map['total_price']?exists && map['total_price'] gt 0>
                                <p class="cont-block-2 high-light-red">${map['total_price']}万元/套</p>
                            <#else><p class="cont-block-2 high-light-red">售价待定</p>
                            </#if>
                        </#if>
                        <p class="cont-block-3">
                            <#if map['nearsubway']??>${map['nearsubway']}
                                <#else>${map['district_name']}
                            </#if>
                            <#if (map['house_min_area']?exists && map['house_min_area'] gt 0)&&(map['house_max_area']?exists && map['house_max_area'] gt 0)>
                                / ${map['house_min_area']}㎡-${map['house_max_area']}㎡</p>
                            </#if>
                        <div class="cont-block-4 house-labelling gray middle">
                            <#if map['building_tags']?exists>
                                <#assign item = map['building_tags']>
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

<#include "user.ftl">
<#include "search.ftl">

<script src="${staticurl}/js/fastclick.js?v=${staticversion}"></script>
<script src="${staticurl}/js/default-touch.js?v=${staticversion}"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>
<script src="${staticurl}/js/toutiao.ad-jsonp.js?v=${staticversion}"></script>
<script>
//    $('.type-tab-box').removeClass('none');
    $('.index-topic-item').on('click', 'a', function () {
        var link = $(this);
        zhuge.track('精选主题_大首页',{
            '主题类别': link.find('.topic-item-content').find('p').text(),
            '主题名称': link.find('.topic-item-content').find('h5').text(),
            '顺序': link.find('.topic-item-content').attr('data-index')
        },function () {
            location.href = link.attr('href');
        });
        return false;
    })
</script>
<script>
    $("#esf_desc").on('click', 'li', function () {
        var link = $(this);
        zhuge.track('大首页-点击二手房推荐', {
            "楼盘名称":link.find('img').attr('alt'),
            "总价":link.find('div.cont-block-price').find('em').text(),
            "单价":link.find('div.cont-block-price').find('span').text(),
            "面积":link.find('p.cont-block-2').text().split("/")[0],
            "户型":link.find('p.cont-block-2').text().split("/")[1],
            "朝向":link.find('p.cont-block-2').text().split("/")[2],
            "标签":link.find('div.cont-block-4.house-labelling.gray.middle.esf').text(),
            "位置信息":link.find('div.list-item-cont').find('p.cont-block-3.distance').text(),
            "是否为广告":"否"
        }, function () {
            location.href = link.find('a').attr('href');
        });
        return false;
    });


    $("#index-recommend-newhouse").find('li').each(function () {
        $(this).on('click', function () {
            var link = $(this);
            if($(this).children().hasClass('ad16')){
                zhuge.track('大首页-点击新房推荐广告N0.4', {
                    "房屋类型":link.find('div.list-item-cont-ad').find('h3.cont-block-1').find('em').text().trim(),
                    "参考均价":link.find('div.pr').find('p.cont-block-2.high-light-red').text().trim(),
                    "区域":link.find('div.list-item-cont-ad').find('p.cont-block-3').text().split("/")[0].trim(),
                    "面积范围":link.find('div.list-item-cont-ad').find('p.cont-block-3').text().split("/")[1]==undefined?'':link.find('div.list-item-cont-ad').find('p.cont-block-3').text().split("/")[1].trim(),
                    "标签":link.find('div.pr').find('div.cont-block-4.house-labelling.gray.middle').find("span").text().trim(),
                    "业态":"",
                    "优惠活动":link.find('div.new-active').find("span").text().trim(),
                    "楼盘名称":link.find('div.list-item-cont-ad').find('h3.cont-block-1').find('span').text().trim(),
                    "页面位置及序号":$(this).index()+1
                }, function () {
                    location.href = link.find('a').attr('href');
                });
                return false;
            }else
            if($(this).children().hasClass('ad17')){
                zhuge.track('大首页-点击新房推荐广告N0.1', {
                    "房屋类型":link.find('div.list-item-cont').find('h3.cont-block-1').find('em').text().trim(),
                    "参考均价":link.find('div.list-item-cont').find('p.cont-block-2.high-light-red').text().trim(),
                    "区域":link.find('div.list-item-cont').find('p.cont-block-3').text().split("/")[0].trim(),
                    "面积范围":link.find('div.list-item-cont').find('p.cont-block-3').text().split("/")[1]==undefined?'':link.find('div.list-item-cont').find('p.cont-block-3').text().split("/")[1].trim(),
                    "标签":link.find('div.cont-block-4.house-labelling.gray.middle').find("span").text().trim(),
                    "业态":"",
                    "优惠活动":link.find('div.new-active').find("span").text().trim(),
                    "楼盘名称":link.find('div.list-item-cont').find('span.ellipsis').text().trim(),
                    "页面位置及序号":$(this).index()+1

                }, function () {
                    location.href = link.find('a').attr('href');
                });
                return false;
            }else{
//                    var property = text.find(".newhouse_property").text();
                zhuge.track('大首页-点击新房推荐房屋', {
                    "房屋类型":link.find('div.list-item-cont').find('h3.cont-block-1').find('em').text().trim(),
                    "参考均价":link.find('div.list-item-cont').find('p.cont-block-2.high-light-red').text().trim(),
                    "区域":link.find('div.list-item-cont').find('p.cont-block-3').text().split("/")[0].trim(),
                    "面积范围":link.find('div.list-item-cont').find('p.cont-block-3').text().split("/")[1]==undefined?'':link.find('div.list-item-cont').find('p.cont-block-3').text().split("/")[1].trim(),
                    "标签":link.find('div.cont-block-4.house-labelling.gray.middle').find("span").text().trim(),
                    "业态":"",
                    "优惠活动":link.find('div.new-active').find("span").text().trim(),
                    "楼盘名称":link.find('div.list-item-cont').find('span.ellipsis').text().trim(),
                    "页面位置及序号":$(this).index()+1
                }, function () {
                    location.href = link.find('a').attr('href');
                });
                return false;
            }
//
        })

    });


    function dashouyelogo(dashouye) {
        var link = $(dashouye);
        zhuge.track('大首页-进入大首页logo', {
            "页面来源URL": window.location.href
        }, function () {
            location.href = link.attr('href');
        });
        return false
    }
    $(function () {
        zhuge.track('大首页-进入大首页',{"页面来源URL": document.referrer});
    })
</script>

<script>
    var config = [{"pid": 1, "jqid": "#ad-positon-lefttop"},
        {"pid": 2, "jqid": "#ad-positon-leftright"},
        {"pid": 3, "jqid": "#ad-positon-leftbottom"},
        {"pid": 4, "jqid": "#ad-positon-rightbottom"}/*,
        {"pid": 5, "jqid": "#topbanner", callback: function (html) {
            html.click(function () {
                zhuge.track('banner_大首页', {'banner名称': '智能人居更懂人需'})
            })
        }}*/];
    $com.toutiao.ad.json(config);
    var lunbo = [
        {"pid": 5,callback: function (html) {
            var parent=$('<li class="swiper-slide index-slide-overflow"></li>');
            parent.append(html);
            $("#topbanner").append(parent);
            var nameText = '';
            if (typeof (html.find('.scaleImg').attr('art')) == 'undefined') {
                nameText = '智能人居更懂人需'
            } else {
                nameText = html.find('.scaleImg').attr('art')
            }
            html.click(function () {
                zhuge.track('banner_大首页', {'banner名称': nameText})
            })
        }},
        {"pid": 20,callback: function (html) {
            var parent=$('<li class="swiper-slide index-slide-overflow"></li>');
            parent.append(html);
            $("#topbanner").append(parent);
            html.click(function () {
                zhuge.track('banner_大首页', {'banner名称': html.find('.scaleImg').attr('art')})
            })
        }},
        {"pid": 21,callback: function (html) {
            var parent=$('<li class="swiper-slide index-slide-overflow"></li>');
            parent.append(html);
            $("#topbanner").append(parent);
            html.click(function () {
                zhuge.track('banner_大首页', {'banner名称': html.find('.scaleImg').attr('art')})
            })
        }},
        {"pid":12,callback:function (html) {
            var parent=$('<li></li>');
            parent.append(html);
            $("#ul_index_lunbo_guanggao").append(parent);
            textSlider();
            html.click(function () {
                zhuge.track('购房指南_大首页',{'指南类别':'资讯','指南名称':html.text()});
            })
        }},
        {"pid":13,callback:function (html) {
            var parent=$('<li></li>');
            parent.append(html);
            $("#ul_index_lunbo_guanggao").append(parent);
            textSlider();
            html.click(function () {
                zhuge.track('购房指南_大首页',{'指南类别':'资讯','指南名称':html.text()});
            })
        }},
        {"pid":16,callback:function (html) {
           if(html != ''){
               $('#index-recommend-newhouse').find('li').each(function(){
                   if($(this).index()==3){
                       $(this)[0].innerHTML = html[0].innerHTML
                   }
               })
           }
        }},
        {"pid":17,callback:function (html) {
            if(html != ''){
                $('#index-recommend-newhouse').find('li').each(function(){
                    if($(this).index()==0){
                        $(this)[0].innerHTML = html[0].innerHTML
                    }
                })
            }
        }}
    ]
    $com.toutiao.ad.json_chain(lunbo);

</script>
</body>
</html>