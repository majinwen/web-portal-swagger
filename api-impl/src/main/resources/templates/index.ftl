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
    <a href="/" class="header-logo"><img src="${staticurl}/images/global/sy_logo@3x.png" alt="头条·房产"></a>
    <div class="search-box">
        <i class="icon"></i>
        <input type="text" class="search-link" placeholder="">
    </div>
    <a href="javascript:;" class="header-user"><img src="${staticurl}/images/global/xf_grzx@3x.png" alt="个人中心"></a>
</header>
<div class="module-bottom-fill">
    <section class="banner-index-box">
        <div class="carousel-swiper">
            <img class="scaleImg" width="100%" src="${staticurl}/${TradeQuotations['homepagePicture']}?v=${staticversion}" alt="智能人居更懂人需" onclick="zhuge.track('banner_大首页',{'banner名称':'智能人居更懂人需'})">
            <input type="hidden" id="url" value="${router_city()}">
        </div>
        <div class="banner-nav">
            <div class="banner-nav-item index-nav-item"><a id="index-xinfang" class="index-xinfang" href="${router_city('/xinfang/')}" onclick="zhuge.track('导航_大首页',{'导航名称':'新房'})">
                <i class="index-new-icon"></i><p>新房</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a class="index-xiaoqu" onclick="zhuge.track('导航_大首页',{'导航名称':'小区'})">
                <i class="index-plot-icon"></i><p>小区</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a class="index-esf" onclick="zhuge.track('导航_大首页',{'导航名称':'二手房'})">
                <i class="index-esf-icon"></i><p>二手房</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a id="index-findhouse" class="index-findhouse" href="${router_city('/findhouse/')}" onclick="zhuge.track('导航_大首页',{'导航名称':'懂房帝'})">
                <i class="index-intelligent-icon"></i><p>懂房帝</p>
            </a></div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section class="bulletin-board">
        <div class="img index-img"><img src="${staticurl}/images/index/index_shopping_guide.png" alt="购物指南"></div>
        <div class="text-scroll index-text" id="shoppingGuide">
            <ul>
                <li><a href="http://www.toutiaopage.com/tetris/page/1586114170135565/" onclick="zhuge.track('购房指南_大首页',{'指南类别':'政策','指南名称':'2018年五险一金将迎来5个变化 每个都是好消息！'})"><em class="scroll-text-tag">政策</em>2018年五险一金将迎来5个变化 每个都是好消息！</a></li>
                <li><a href="http://www.toutiaopage.com/tetris/page/1586483105661965/" onclick="zhuge.track('购房指南_大首页',{'指南类别':'导购','指南名称':'北京城市副中心正式启用 看看还有哪些“正经”盘可以买'})"><em class="scroll-text-tag">导购</em>北京城市副中心正式启用 看看还有哪些“正经”盘可以买</a></li>
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
                <div class="hot-topic-item index-topic-item"><a href="https://www.toutiao.com/i6506676833080050184/?tt_from=weixin&utm_campaign=client_share&timestamp=1515143248&app=news_article&utm_source=weixin&iid=22476244001&utm_medium=toutiao_ios&wxshare_count=1">
                    <div class="topic-item-content" data-index="1">
                        <h5>2018房价是涨是降？</h5>
                        <p>市场预测</p>
                    </div>
                    <img class="item-1" src="${staticurl}/images/index/dsy_jxzt_image1.png" alt="市场预测">
                </a></div>
                <div class="hot-topic-item index-topic-item"><a href="http://www.toutiaopage.com/tetris/page/1588549788609539/">
                    <div class="topic-item-content" data-index="2">
                        <h5>房贷知识知多少</h5>
                        <p>热门问答</p>
                    </div>
                    <img class="item-2" src="${staticurl}/images/index/dsy_jxzt_image2.png" alt="热门问答">
                </a></div>
            </div>
            <div class="column">
                <div class="hot-topic-item index-topic-item"><a href="http://www.toutiaopage.com/tetris/page/1588564480256007/">
                    <div class="topic-item-content" data-index="3">
                        <h5>最美样板间实景VR</h5>
                        <p>VR+房产</p>
                    </div>
                    <img class="item-3" src="${staticurl}/images/index/dsy_jxzt_image3.png" alt="VR+房产">
                </a></div>
                <div class="hot-topic-item index-topic-item"><a href="http://www.toutiaopage.com/tetris/page/1587830184804366/">
                    <div class="topic-item-content" data-index="4">
                        <h5>新开通地铁沿线盘</h5>
                        <p>精选导购</p>
                    </div>
                    <img class="item-4" src="${staticurl}/images/index/dsy_jxzt_image4.png" alt="精选导购">
                </a></div>
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
                <div class="cloumn">
                    <h6>昨日新房成交量</h6>
                    <div><#if TradeQuotations['newHouse']?split(',')[0]?exists&&TradeQuotations['newHouse']?split(',')[0]?number gt 0><em>${TradeQuotations['newHouse']?split(',')[0]}</em>套<#else >暂无数据</#if><span class="high-light-red">
                    <#if TradeQuotations['newHouse']?split(',')[1]?exists&&TradeQuotations['newHouse']?split(',')[1]?number gt 0>${TradeQuotations['newHouse']?split(',')[1]}%↑
                    <#elseif TradeQuotations['newHouse']?split(',')[1]?exists&&TradeQuotations['newHouse']?split(',')[1]?number lt 0> ${TradeQuotations['newHouse']?split(',')[1]}%↓ <#else >/暂无数据</#if>
                    </span></div>
                </div>
                <div class="cloumn">
                    <h6>上周新房成交均价</h6>
                    <div><#if TradeQuotations['newHouse']?split(',')[2]?exists&&TradeQuotations['newHouse']?split(',')[2]?number gt 0 ><em>${TradeQuotations['newHouse']?split(',')[2]}</em><#else >暂无数据</#if>元/㎡</div>
                </div>
            </div>
            </#if>
            <#if TradeQuotations['esfHouse']?exists&&TradeQuotations['esfHouse']!=''>
            <div class="row clear">
                <div class="cloumn">
                    <h6>上周二手房房成交量</h6>
                    <div><#if TradeQuotations['esfHouse']?split(',')[0]?exists&&TradeQuotations['esfHouse']?split(',')[0]?number gt 0><em>${TradeQuotations['esfHouse']?split(',')[0]}</em>套<#else >暂无数据</#if><span class="high-light-green">
                    <#if TradeQuotations['esfHouse']?split(',')[1]?exists&&TradeQuotations['esfHouse']?split(',')[1]?number gt 0>${TradeQuotations['esfHouse']?split(',')[1]}%↑
                    <#elseif TradeQuotations['esfHouse']?split(',')[1]?exists&&TradeQuotations['esfHouse']?split(',')[1]?number lt 0> ${TradeQuotations['esfHouse']?split(',')[1]}%↓ <#else >/暂无数据</#if>
                    </span></div>
                    <#--<div><em>1865</em>套<span class="high-light-green">24.20% ↓</span></div>-->
                </div>
                <div class="cloumn">
                    <#if TradeQuotations['month']?exists&&TradeQuotations['month']?number gt 0>
                    <h6>${TradeQuotations['month']}月二手房参考均价</h6>
                    </#if>
                    <div><#if TradeQuotations['esfHouse']?split(',')[2]?exists&&TradeQuotations['esfHouse']?split(',')[2]?number gt 0 ><em>${TradeQuotations['esfHouse']?split(',')[2]}</em><#else >暂无数据</#if>元/㎡</div>
                    <#--<div><em>57650</em>元/㎡</div>-->
                </div>
            </div>
            </#if>
        </div>
    </section>
</div>

<section>
    <div class="index-module-header">
        <h3>新房推荐</h3>
    </div>
    <ul><#if newbuilds?exists>
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
                        <#--<#if (map['average_price']?exists && map['average_price'] > 0)>-->
                            <#--<p class="cont-block-2 high-light-red">${map['average_price']}/㎡</p>-->
                        <#--<#else >-->
                            <#--<p class="cont-block-2 high-light-red">售价待定</p>-->
                        <#--</#if>-->
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
                            <#if map['house_min_area']?exists&&map['house_max_area']?exists>
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
<section>
    <div class="index-module-header">
        <h3>小区推荐</h3>
    </div>
    <ul><#if villageList?exists>
        <#list villageList as map>
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
            <li><a class="list-item" href="${router_city('/xiaoqu/'+map['id']?c+'.html')}">
                <div class="clear">
                    <div class="list-item-img-box">
                        <#if map['photo'][0]?? && map['photo'][0] != ''><img src="${qiniuimage}/${map['photo'][0]}-tt400x300" alt="${map['rc']}">
                            <#else ><img src="${staticurl}/images/global/tpzw_image.png" alt="${map['rc']}">
                        </#if>
                    </div>
                    <div class="list-item-cont">
                        <input type="hidden" value="${map['id']}">
                        <h3 class="cont-block-1">${map['rc']}</h3>
                        <#if map['abbreviatedAge']?exists>
                            <p class="cont-block-2">${map['abbreviatedAge']}年建成</p>
                        </#if>

                        <p class="cont-block-3 distance"><i class="icon"></i>${map['area']}-${map['tradingArea']}</p>
                        <div class="cont-block-4 house-labelling gray middle">
                            <#if (map['label']??)&&(map['label']?size>0)>
                            <#list map['label'] as label>
                                <#if label?exists><span>${label}</span></#if>
                            </#list>
                            </#if>
                        </div>
                        <div class="cont-block-price plot">
                            <#if map['avgPrice']?? && map['avgPrice'] gt 0>
                                <em>${map['avgPrice']}元/㎡</em>
                            <#else>
                                <em>售价待定</em>
                            </#if>
                        </div>
                    </div>
                </div>
            </a></li>
        </#list>
    </#if></ul>
</section>
<#include "user.ftl">
<#include "search.ftl">

<script src="${staticurl}/js/swiper-3.4.2.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>
<script>
    $('.type-tab-box').removeClass('none');
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
</body>
</html>