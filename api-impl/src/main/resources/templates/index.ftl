<!DOCTYPE html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/index.css">
    <title>大首页</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script src="/static/js/modernizr.custom.js"></script>
</head>
<body>
<header class="main-top-header gradient">
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
                    <img src="${staticurl}/images/index/dsy_banner.jpg" alt="年末特惠">
                </li>
            </ul>
            <div class="swiper-pagination pictrue-index"></div>
            <input type="hidden" id="url" value="${router_city()}">
        </div>
        <div class="banner-nav">
            <div class="banner-nav-item index-nav-item"><a href="${router_city('/xinfang/')}">
                <i class="index-new-icon"></i><p>新房</p>
            </a></div>

            <div class="banner-nav-item index-nav-item"><a href="${router_city('/xiaoqu/')}">
                <i class="index-plot-icon"></i><p>小区</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a href="${router_city('/esf/')}">
                <i class="index-esf-icon"></i><p>二手房</p>
            </a></div>
            <div class="banner-nav-item index-nav-item"><a href="${router_city('/findhouse/qidong')}">
                <i class="index-intelligent-icon"></i><p>懂房帝</p>
            </a></div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section class="bulletin-board">
        <div class="img index-img"><img src="${staticurl}/images/index/index_shopping_guide.png" alt="购物指南"></div>
        <div class="text-scroll index-text">
            <ul>
                <li><em class="scroll-text-tag">市场</em>北京二手房现全面“砍价”全年成交13.4万</li>
                <li><em class="scroll-text-tag">市场</em>猛犸温泉高档小区开盘在即，各大商铺积极响猛犸温泉高档小区开盘在即，各大商铺积极响</li>
                <li><em class="scroll-text-tag">市场</em>猛犸温泉高档小区开盘在即，各大商铺猛犸温泉高档小区开盘在即，各大商铺积极响铺积极响铺积极响</li>
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section class="elastics-stack-box">
        <div class="elastics-stack-content">
            <ul id="elastics-stack" class="elastics-stack">
                <li class="bgtype-1">
                    <div>
                        <h4>2018纯新盘</h4>
                        <p>北京全新楼盘抢先看</p>
                    </div>
                    <img src="${staticurl}/images/index/dsy_ts_image1.jpg" alt="2018纯新盘">
                </li>
                <li class="bgtype-2">
                    <div>
                        <h4>海淀热门房源</h4>
                        <p>看看大家关注哪里的房</p>
                    </div>
                    <img src="${staticurl}/images/index/dsy_ts_image2.jpg" alt="海淀热门房源">
                </a></li>
                <li class="bgtype-3">
                    <div>
                        <h4>200万电梯房</h4>
                        <p>少花钱多办事上下自由</p>
                    </div>
                    <img src="${staticurl}/images/index/dsy_ts_image3.jpg" alt="200万电梯房">
                </li>
            </ul>
        </div>
    </section>
    <section>
        <div class="index-module-header border-bot-none">
            <h3>精选主题</h3>
        </div>
        <div class="hot-topic pt0">
            <div class="column">
                <div class="hot-topic-item index-topic-item"><a href="http://www.toutiaopage.com/tetris/page/1587830184804366/">
                    <div class="topic-item-content">
                        <h5>新开通地铁旁楼盘</h5>
                        <p>地铁盘推荐 </p>
                    </div>
                    <img class="item-1" src="${staticurl}/images/index/dsy_jxzt_image1.png" alt="地铁盘推荐">
                </a></div>
                <div class="hot-topic-item index-topic-item"><a href="${router_city('/esf?houseLabelId=16')}">
                    <div class="topic-item-content">
                        <h5>满五税少房推荐</h5>
                        <p>每日热推</p>
                    </div>
                    <img class="item-2" src="${staticurl}/images/index/dsy_jxzt_image2.png" alt="每日热推">
                </a></div>
            </div>
            <div class="column">
                <div class="hot-topic-item index-topic-item"><a href="${router_city('/esf?beginPrice=350.0&endPrice=400.0&districtId=105037')}">
                    <div class="topic-item-content">
                        <h5>西城区400万房源</h5>
                        <p>热点专区</p>
                    </div>
                    <img class="item-3" src="${staticurl}/images/index/dsy_jxzt_image3.png" alt="热点专区">
                </a></div>
                <div class="hot-topic-item index-topic-item"><a href="${router_city('/esf?houseYearId=[0-5]')}">
                    <div class="topic-item-content">
                        <h5>5年内楼龄房源</h5>
                        <p>品质小区</p>
                    </div>
                    <img class="item-4" src="${staticurl}/images/index/dsy_jxzt_image4.png" alt="品质小区">
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
            <div class="row clear">
                <div class="cloumn">
                    <h6>昨日新房成交量</h6>
                    <div><em>185</em>套<span class="high-light-red">24.20% ↓</span></div>
                </div>
                <div class="cloumn">
                    <h6>上周新房成交均价</h6>
                    <div><em>35270</em>元/㎡</div>
                </div>
            </div>
            <div class="row clear">
                <div class="cloumn">
                    <h6>上周二手房房成交量</h6>
                    <div><em>1865</em>套<span class="high-light-green">24.20% ↓</span></div>
                </div>
                <div class="cloumn">
                    <h6>12月二手房参考均价</h6>
                    <div><em>57650</em>元/㎡</div>
                </div>
            </div>
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
            <#if map_index==3>
            <li><a class="list-item new new-ad-item" href="#">
                <div class="list-item-cont-ad">
                    <h3 class="cont-block-1">
                        <span>中骏·西山天璟</span>
                        <em>别墅</em>
                    </h3>
                    <p class="cont-block-3">东城/88㎡—526㎡</p>
                </div>
                <div class="clear">
                    <div class="list-item-img-box">
                        <img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">
                    </div>
                    <div class="list-item-img-box">
                        <img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">
                    </div>
                    <div class="list-item-img-box">
                        <img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">
                    </div>
                </div>
                <div class="pr">
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>复式</span>
                        <span>五证齐全</span>
                        <span>花园洋房</span>
                    </div>
                    <p class="cont-block-2 high-light-red">68000元/㎡</p>
                </div>

                <div class="new-active">
                    <i class="icon"></i><em>活动：</em>
                    <span>梦马温泉项目位于门头沟双屿岛...梦马温泉项目位于门...</span>
                </div>
            </a></li></#if>
            <li><a class="list-item new" href="${router_city('/loupan/'+map['building_name_id']?c+'.html')}">
                <div class="clear">
                    <div class="list-item-img-box">
                        <#if map['building_imgs']?exists>
                            <#assign item = map['building_imgs']?split(",")>
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
                        <#if (map['average_price']?exists && map['average_price'] > 0)>
                            <p class="cont-block-2 high-light-red">${map['average_price']}/㎡</p>
                        </#if>
                        <p class="cont-block-3">
                            <#if map['nearsubway']??>
                            ${map['nearsubway']}
                            <#else>${map['district_name']}
                            </#if>
                            <#if map['house_min_area']?exists&&map['house_max_area']?exists>
                                /${map['house_min_area']}㎡—${map['house_max_area']}㎡</p>
                            </#if>
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
                    <span>
                        ${map['activity_desc']}
                    </span>
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
            <#if map_index==3>
            <li><a class="list-item new new-ad-item" href="#">
                <div class="list-item-cont-ad">
                    <h3 class="cont-block-1">新龙城</h3>
                    <p class="cont-block-3 distance"><i class="icon"></i>距离您0.5km</p>
                    <p class="cont-block-2">2008年建成</p>
                </div>
                <div class="clear">
                    <div class="list-item-img-box">
                        <img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">
                    </div>
                    <div class="list-item-img-box">
                        <img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">
                    </div>
                    <div class="list-item-img-box">
                        <img src="${staticurl}/images/global/tpzw_image.png" alt="中骏·西山天璟">
                    </div>
                </div>
                <div class="pr">
                    <div class="cont-block-4 house-labelling gray middle">
                        <span>复式</span>
                        <span>五证齐全</span>
                        <span>花园洋房</span>
                    </div>
                    <p class="cont-block-2 high-light-red">68000元/㎡</p>
                </div>
            </a></li>
            <#elseif map_index==4>
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

<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="/static/js/draggabilly.pkgd.min.js"></script>
<script src="/static/js/elastiStack.js"></script>
<script src="${staticurl}/js/URI.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
<script>
    $('.type-tab-box').removeClass('none');
    new  ElastiStack(document.getElementById('elastics-stack'));
</script>
</body>
</html>