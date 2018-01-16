<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/new-detail.css?v=${staticversion}">
    <title>来头条看【${build['building_name']}】</title>
    <meta name="description" content="头条房产，帮你发现美好生活">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<#--<#assign ptCD0 = tradeline['buildingline']>-->
<#--<#assign ptCD1 = tradeline['arealine']>-->
<#--<#assign ptCD2 = tradeline['tradearealine']>-->
<#--<#assign mouthList = tradeline['mouthList']>-->
<img height="1px" width="1px" hidden src="${qiniuimage}/${build['building_title_img']!""}-ttfdc1200x640" alt="头条·房产">
<div class="carousel-box">
    <div class="swiper-container carousel-swiper" id="detail-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
            <#if build['building_imgs']?exists>
            <#list build['building_imgs']?split(",") as item>
            <#if item?exists>
                <#if item?? && item!= ''>
                    <li onclick="initphoto(this,${item_index},window.location.href)" class="swiper-slide">
                        <img src="${qiniuimage}/${item}-ttfdc1200x640" data-src="${qiniuimage}/${item}-ttfdc1200x640" alt="${build['building_name']}">
                    </li>
                <#else >
                    <li onclick="initphoto(this,0,window.location.href)" class="swiper-slide">
                        <img src="${staticurl}/images/global/tpzw_banner_image.png" data-src="${staticurl}/images/global/tpzw_banner_image.png" alt="${build['building_name']}">
                    </li>
                </#if>
            </#if>
            </#list>
            </#if>
        </ul>
        <div class="banner-title">
            <#--<div class="banner-house-number">房源编号：${build['building_name']}</div>-->
            <div class="swiper-pagination pictrue-index"></div>
        </div>
    </div>
    <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="pswp__bg"></div>
        <div class="pswp__scroll-wrap">
            <div class="pswp__container">
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
            </div>
            <div class="pswp__ui pswp__ui--hidden">
                <div class="pswp__top-bar">
                    <div class="pswp__counter"></div>
                    <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>
                    <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>
                    <div class="pswp__preloader">
                        <div class="pswp__preloader__icn">
                            <div class="pswp__preloader__cut">
                                <div class="pswp__preloader__donut"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                    <div class="pswp__share-tooltip"></div>
                </div>
                <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button>
                <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)"></button>
                <div class="pswp__caption">
                    <div class="pswp__caption__center"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="module-bottom-fill">
    <section class="primary-message">
        <div class="primary-header">
            <h2>${build['building_name']}<em class="sale-state"><#if build['sale_status_name']?exists>${build['sale_status_name']}</#if></em></h2>
            <#if build['building_nickname']??&&(build['building_nickname']!='')><p>别名：${build['building_nickname']}</p></#if>
            <div class="primary-header-tag house-labelling gray middle">
            <#if (build['building_tags']?exists)&&(build['building_tags']?size>0)>
                <#list build['building_tags'] as item>
                    <#if item?exists><span>${item}</span></#if>
                </#list>
            </#if>
            </div>
        </div>
        <ul class="primary-item">
            <li>
             <#if build['average_price']?exists && build['average_price'] gt 0>
                <p>均价：<em class="high-light-red">
                    ${build['average_price']}元/㎡
                <#else>
                 <#if build['total_price']?exists && build['total_price'] gt 0>
                <p>总价：<em class="high-light-red">
                    ${build['total_price']}万元/套
                 <#else>售价待定
                 </#if>
                </#if>
                </em></p>
            </li>
            <li>
                <p>
                    地址：<#if build['district_name']?exists>[${build['district_name']}]</#if>
                           ${build['building_address']!'暂无数据'}
                    <a href="${router_city('/loupan/'+build['building_name_id']?c+'/map.html')}" class="primary-map-icon"></a>
                    <a href="${router_city('/loupan/'+build['building_name_id']?c+'/map.html')}" class="arrows-right"></a>
                </p>
                <p>
                    <#if build['roundstation']?exists>
                        <#assign rounditems = build['roundstation']?split("$")>
                        <#if rounditems[2]?number gt 1000>
                            <#assign x = rounditems[2]?number/1000>
                            交通信息：距离${rounditems[1]}[${rounditems[0]}] ${x?string("#.#")}km
                        <#else>
                            交通信息：距离${rounditems[1]}[${rounditems[0]}] ${rounditems[2]}m
                        </#if>
                    </#if>
                </p>
            </li>
            <li>
                <p>最新开盘：${build['opened_time']!'暂无数据'}</p>
                <p>交房时间：${build['deliver_time']!'暂无数据'}</p>
                <p>售楼许可证：<#if (build['sell_licence']?exists)&&(build['sell_licence']?size>0)>
                    <#assign item = build['sell_licence'] >
                    <#if item['licenseName']?exists><span>${item['licenseName']}</span></#if>
                </#if></p>
            </li>
        </ul>
    </section>
</div>
<#if build['activity_desc']?exists>
<div class="module-bottom-fill">
    <div class="active-module-box">
        <a href="tel:1234567" class="active-module-content">
            <p class="active-text"><i class="active-icon"></i><span>最新活动：${build['activity_desc']}</span></p>
            <div class="consule-message">
                <p>
                    <span>更多优惠信息</span>
                    <span>请咨询售楼处</span>
                </p>
                <i></i>
            </div>
        </a>
    </div>
</div>
</#if>
<#if (layout?exists) && (layout?size>0)>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>户型信息</h3>
            <a href="${router_city('/loupan/'+build['building_name_id']+'/huxing.html?tags=0')}" class="more-arrows">全部户型<i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist">
            <#list layout as item>
                <li>
                    <a href="${router_city('/loupan/'+build['building_name_id']+'/huxing.html?tags=0')}">
                        <div class="picture-box">
                            <#if item['layout_img']?exists>
                                <#assign layoutimgs = item['layout_img']?split(",")>
                                <img src="${qiniuimage}/${layoutimgs[0]}-tt400x300" alt="${build['building_name']}">
                            <#else><img src="${staticurl}/images/newhouse/huxing_img.png" alt="${build['building_name']}">
                            </#if>
                            <span class="sale-state">在售</span>
                        </div>
                        <div class="tilelist-content">
                            <p class="cont-first"><span>${item['room']!'暂无数据'}室${item['hall']!'暂无数据'}厅${item['toilet']!'暂无数据'}卫</span><span>${item['building_area']!'暂无数据'}㎡</span></p>
                        <#--<h4 class="cont-last">均价：${item['reference_price']+"元/㎡"!'暂无数据'}</h4>-->
                            <div class="house-labelling normal small tilelist-tag">
                                <#if item['layout_tag']??>
                                    <#assign layouttagitem = item['layout_tag']>
                                    <#list layouttagitem as tagatem>
                                        <#if tagatem?exists>
                                            <span>${tagatem}</span>
                                        </#if>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </a>
                </li>
            </#list>
        </ul>
    </section>
</div>
</#if>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>基本信息<span class="subtitle">了解居住环境</span></h3>
            <a href="${router_city('/loupan/'+build['building_name_id']?c+'/desc.html')}" onclick="zhugebaseinfo()" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <div class="basic-information">
            <div class="column item-column-two">
                <div class="info-card-item">
                    <i class="item-two-1"></i>
                    <div class="info-item-text">
                        <p>绿化率</p>
                        <#if build['virescencerate']??&&build['virescencerate']?number gt 0>
                            <em>${build['virescencerate']?string("#.####")}%</em>
                        <#else >
                            <em>暂无数据</em>
                        </#if>
                    </div>
                </div>
                <div class="info-card-item">
                    <i class="item-two-2"></i>
                    <div class="info-item-text">
                        <p>车位配比</p>
                        <em> <#if build['park_radio']?exists>
                            ${build['park_radio']}
                              <#else>暂无数据
                             </#if></em>
                    </div>
                </div>
            </div>
            <div class="column item-column-two">
                <div class="info-card-item">
                    <i class="item-two-3"></i>
                    <div class="info-item-text">
                        <p>电梯配备</p>
                        <em><#if build['lift_door_radio']??&&build['lift_door_radio']!=''>
                        ${build['lift_door_radio']}
                        <#else>暂无数据
                        </#if></em>
                    </div>
                </div>
                <div class="info-card-item">
                    <i class="item-two-4"></i>
                    <div class="info-item-text">
                        <p>空气质量</p>
                        <em><#if build['air_quality']??&&build['air_quality']!=''>
                        ${build['air_quality']}
                        <#else>暂无数据
                        </#if>
                        </em>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>交通信息<span class="subtitle">最便捷的出行方式</span></h3>
        </div>
        <div class="basic-information">
            <div class="column item-column-three">

                <div class="info-card-item">
                    <i class="item-three-1"></i>
                    <em>公交</em>
                    <p id="busStation"><#if datainfo??&&datainfo["gongjiao"]["name"]?exists>${datainfo["gongjiao"]["name"]}<#else >暂无数据</#if></p>
                    <span id="busStationNumber"><#if datainfo??&&datainfo["gongjiao"]["lines"]?exists>${datainfo["gongjiao"]["lines"]}条线路<#else >暂无数据</#if></span>

                </div>
                <div class="info-card-item">
                    <i class="item-three-2"></i>
                    <em>地铁</em>
                    <#if build['roundstation']?exists>
                        <#assign rounditems = build['roundstation']?split("$")>
                        <#assign x = rounditems[2]?number/1000>
                        <p id="subwayLine">${rounditems[1]}</p>
                        <span id="subwayDistance">${rounditems[0]}</span>
                    <#else >
                        暂无数据
                    </#if>
                </div>
                <div class="info-card-item">
                    <i class="item-three-3"></i>
                    <em>自驾</em>
                    <p>${build['ringRoadName']!'暂无数据'}</p>
                    <span>
                    <#if build['ringRoadDistance']?exists>
                    <#--<#if village['ringRoadDistance']?exists && village['ringRoadDistance']!=''>-->
                    <#--${(village['ringRoadDistance']/1000)?string('#.#')}km-->
                    <#--<#else >-->
                    <#--暂无数据-->
                    <#--</#if>-->
                        <#if build['ringRoadDistance']?number gt 0>
                        ${(build['ringRoadDistance']/1000)?string('#.#')}km
                        <#else>
                            暂无数据
                        </#if>
                    </#if>
                    </span>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>教育配套<span class="subtitle">看你发芽 陪你长大</span></h3>
        </div>
        <div class="expand-content content-visible tab_jiaoyupeixun_info">
            <div class="map-education-box">
                <ul class="map-message-btn clear" data-type="教育培训">
                    <li class="parent-child" data-zhuge="亲子" data-type="qinzi"><i></i><span>亲子</span></li>
                    <li class="kindergarten" data-zhuge="幼儿园" data-type="youeryuan"><i></i><span>幼儿园</span></li>
                    <li class="primary-school" data-zhuge="小学" data-type="xiaoxue"><i></i><span>小学</span></li>
                    <li class="middle-school" data-zhuge="中学" data-type="zhongxue"><i></i><span>中学</span></li>
                    <#--<li class="university" data-type="gaodeng"><i></i><span>大学</span></li>-->
                </ul>
            </div>
            <ul class="result-data-expand" id="qinzi">
            </ul>
            <ul class="result-data-expand none" id="youeryuan">
            </ul>
            <ul class="result-data-expand none" id="xiaoxue">
            </ul>
            <ul class="result-data-expand none" id="zhongxue">
            </ul>
            <ul class="result-data-expand none" id="gaodeng">
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>休闲购物<span class="subtitle">3km内生活圈</span></h3>
        </div>
        <div class="expand-content content-visible tab_xiuxiangouwu_click">
            <div class="map-shopping-box">
                <ul class="map-message-btn" data-type="休闲购物">
                    <li class="vegetable-market" data-type="caishichang"><span>菜市场</span><i></i></li>
                    <li class="supermarket" data-type="chaoshi"><span>超市</span><i></i></li>
                    <li class="shopping-mall" data-type="shangchang"><span>商场</span><i></i></li>
                    <li class="dining-room" data-type="canting"><span>餐厅</span><i></i></li>
                    <li class="fitness" data-type="jianshenzhongxin"><span>健身</span><i></i></li>
                </ul>
                <img src="${staticurl}/images/plot/xqxq_xxgw_tu@3x.png" width="100%" alt="">
            </div>
            <ul class="result-data-expand height-type" id="caishichang">
            </ul>
            <ul class="result-data-expand height-type none" id="chaoshi">
            </ul>
            <ul class="result-data-expand height-type none" id="shangchang">
            </ul>
            <ul class="result-data-expand height-type none" id="canting">
            </ul>
            <ul class="result-data-expand height-type none" id="jianshenzhongxin">
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill" id="hospitalListWrapper">
    <section>
        <div class="module-header-message">
            <h3>医疗配套<span class="subtitle">为您的健康保驾护航</span></h3>
            <a href="javascript:;" data-zg="医疗配套" class="more-arrows expand-btn"><i class="arrows-expand"></i></a>
        </div>
        <div class="expand-content">
        <#assign yiliao=datainfo['yiliao'] />
            <ul class="result-data-expand" id="hospitalListDom">
                <#--<#if (yiliao['zhuanke']?size>0)>
                ${yiliao['zhuanke']}
                </#if>-->
                <#assign itnum = 0>
                <#if (yiliao['zhuanke']?size>0)>
                <#list yiliao['zhuanke'] as item>
                    <#if itnum<5>
                        <#assign itnum=itnum+1>
                    <li>
                        <p><i class="expand-icon medical-treatment"></i><span class="expand-name">${item.name}【专科】</span></p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                    <#else><#break>
                    </#if>
                </#list>
                </#if>
                <#if (yiliao['zhensuo']?size>0)>
                    <#list yiliao['zhensuo'] as item>
                        <#if itnum<5>
                          <#assign itnum=itnum+1>
                        <li>
                            <p><i class="expand-icon medical-treatment"></i><span class="expand-name">${item.name}【诊所】</span></p>
                            <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                        </li>
                        <#else><#break>
                        </#if>
                    </#list>
                </#if>
                <#if (yiliao['zonghe']?size>0)>
                    <#list yiliao['zonghe'] as item>
                        <#if itnum<5>
                            <#assign itnum=itnum+1>
                        <li>
                            <p><i class="expand-icon medical-treatment"></i><span class="expand-name">${item.name}【综合】</span></p>
                            <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                        </li>
                        <#else><#break>
                        </#if>
                    </#list>
                </#if>
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>生活成本<span class="subtitle">您的居住费用清单</span></h3>
            <a href="javascript:;" data-zg="生活成本" class="more-arrows expand-btn"><i class="arrows-expand"></i></a>
        </div>
        <div class="expand-content">
            <ul class="result-data-expand">
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">水费</span>
                    <#if (build['water_supply']?exists) && build['water_supply'] == "商水">
                        <span class="expand-price">6元/吨</span>
                    <#else >
                        <span class="expand-price">5元/吨</span>
                    </#if>
                    </p>
                <#--<span class="expand-distance tips">居民用水价格范围为1-4元/吨</span>-->
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">电费</span>
                    <#if (build['electric_supply']?exists) && build['electric_supply'] == "商电">
                        <span class="expand-price">1.33元/度</span>
                    <#else >
                        <span class="expand-price">0.48元/度</span>
                    </#if>
                    </p>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">物业费</span>
                        <#if (build['propertyfee']?exists)&&build['propertyfee']?number gt 0>
                            <span class="expand-price">${build['propertyfee']}元/㎡·月</span>
                        <#else>暂无数据
                        </#if>
                    </p>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">停车费</span>
                        <#if (build['car_rent_price']?exists)&&build['car_rent_price']?number gt 0>
                            <span class="expand-price">${build['car_rent_price']}元/月</span>
                        <#else>暂无数据
                        </#if>
                    </p>
                </li>
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>配套地图</h3>
            <a id="more-map-info-new" href="${router_city('/loupan/'+build['building_name_id']?c+'/map.html')}" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <a href="${router_city('/loupan/'+build['building_name_id']?c+'/map.html')}" class="detail-map">
            <i class="map-marker-icon"></i>
            <#if build['location']?exists>
                <#assign locations = build['location']?split(",")>
                <img id="map-img" data-lat="${locations[0]}" data-lng="${locations[1]}" src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=${locations[1]},${locations[0]}&&zoom=16" alt="${build['building_name']}">
            <#else ><img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=116.382001,39.913329&&zoom=16" alt="">
            </#if>
        </a>
    </section>
</div>
<#--<#if  (mouthList?size>0)>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>价格走势</h3>
        </div>
        <div class="echarts-box">
            <div class="echarts-content" id="newhousetrad"></div>
        </div>
    </section>
</div>
</#if>-->
<#if nearbybuild?exists>
<section>
    <div class="module-header-message">
        <h3>看了本楼盘的用户还看了</h3>
    </div>
    <ul class="tilelist">
    <#list nearbybuild as nearitem>
        <li>
            <a class="newzhugetuijian"  data-zg="${nearitem['building_name']!'暂无'}+${nearitem['average_price']!'暂无'}+${nearitem['district_name']!'暂无'}+${nearitem_index+1}" href="${router_city('/loupan/'+nearitem['building_name_id']?c+'.html')}">
                <div class="picture-box">
                    <#if nearitem['building_imgs']?exists>
                    <#assign imgt = nearitem['building_imgs']?split(",")>
                        <#if imgt[0]?? && imgt[0] != ''><img src="${qiniuimage}/${imgt[0]}-tt400x300" alt="${nearitem['building_name']}">
                            <#else ><img src="${staticurl}/images/global/tpzw_image.png" alt="${nearitem['building_name']}">
                        </#if>
                    </#if>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">${nearitem['building_name']!'暂无数据'}</p>
                    <p class="cont-center"><span>${nearitem['district_name']!'暂无数据'}</span><span>${nearitem['area_name']!'暂无数据'}</span></p>
                    <h4 class="cont-last">
                        <#if nearitem['average_price']?exists&&nearitem['average_price']?number gt 0>均价：<em>${nearitem['average_price']}</em>元/㎡
                            <#else >
                                <#if nearitem['total_price']?exists&&nearitem['total_price']?number gt 0>总价：<em>${nearitem['total_price']}</em>万元/套
                                    <#else ><em>售价待定</em>
                                </#if>
                        </#if>
                    </h4>
                </div>
            </a>
        </li>
    </#list>
    </ul>
</section>
</#if>
<#if build['saletelphone']?exists&&build['saletelphone']!=''>
    <div class="detail-contact-wrapper">
        <section class="detail-contact-box" id="detailContactState">
            <div class="detail-contact-content">
                <a href="tel:${build['saletelphone']}" class="only contact-telephone-counseling">咨询售楼处</a>
            </div>
        </section>
    </div>
</#if>
<!-------- photoswipe -------->

<div id="mapContainer" style="display: none;"> </div>
<script src="${staticurl}/js/photoswipe.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
<script type="text/javascript" src="${staticurl}/js/base-map.js?v=${staticversion}"></script>
<script src="${staticurl}/js/plot-detail-map-message.js?v=${staticversion}"></script>
</body>
</html>
<script>
    $(function () {
        zhuge.track('进入新房详情页', {
            '页面来源' : window.location.href,
            '区域' : '${build['district_name']!''}',
            '商圈' : '${build['area_name']!''}',
            '均价' : '${build['average_price']!''}',
            'id' : '${build['building_name_id']!''}',
            '楼盘名称' : '${build['building_name']!''}'
        });

        function zhugebaseinfo(){
            zhuge.track('新房-点击基本信息', {
                '新房基本信息' : '新房基本信息'
            });
        }

        $(".newzhugetuijian").click(function() {
            /*console.log($(this).attr('data-zg'));*/
            var zg = $(this).attr('data-zg');
           var zgs = zg.split('+');
           var url = $(this).attr('href');
/*           console.log(url);*/
            zhuge.track('新房-点击推荐新房', {
                        '楼盘名称': zgs[0],
                        '参考均价': zgs[1],
                        '位置信息': zgs[2],
                        '页面位置序号': zgs[3]
                    },
                    function() {
                        location.href = url; //继续跳转到目标页面
                    });
            return false;
        });

    })
</script>