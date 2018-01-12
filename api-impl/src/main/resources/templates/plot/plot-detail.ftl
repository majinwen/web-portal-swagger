<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/plot-detail.css?v=${staticversion}">
    <title>来头条房产看【${village['rc']!'小区'}】</title>
    <meta name="description" content="推荐你上头条房产看看【${village['rc']!'小区'}】的价格走势与小区详情">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script src="${staticurl}/js/echarts.min.js?v=${staticversion}"></script>
    <script>
        var locationnumber = '${village['location']}';
        var mapBaiduNumber = locationnumber.split(",").indexOf(1) + locationnumber.split(",").indexOf(0)
    </script>
<#include "../StatisticsHeader.ftl">
</head>
<body>
<#assign ptCD0 = tradeline['buildingline']>
<#assign ptCD1 = tradeline['arealine']>
<#assign ptCD2 = tradeline['tradearealine']>
<#assign mouthList = tradeline['mouthList']>
<img class="shareTopImg" height="0" width="0" src="${qiniuimage}/${village['photo'][0]!""}-tt1200x640" alt="">
<div class="carousel-box">
    <div class="swiper-container carousel-swiper" id="detail-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
        <#if village['photo']?exists&&(village['photo']?size gt 0)>
            <#list village['photo'] as vpphoto>
                <li onclick="initphoto(this,${vpphoto_index})" class="swiper-slide">
                    <img src="${qiniuimage}/${vpphoto}-ttfdc1200x640" data-src="${qiniuimage}/${vpphoto}-ttfdc1200x640" alt="">
                </li>
            </#list>
        <#else>
            <li onclick="initphoto(this,0)" class="swiper-slide">
                <img src="${staticurl}/images/global/tpzw_banner_image.png" data-src="${staticurl}/images/global/tpzw_banner_image.png" alt="">
            </li>
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
    <section class="plot-primary-header">
        <div class="plot-primary-text">
            <h2>${village['rc']!''}</h2>
            <p><#if village['area']?exists&&village['area']!=''&&village['tradingArea']?exists&&village['tradingArea']!=''>
                        ${'['+village['area']}${'-'+village['tradingArea']+']'}
                <#else>
                    <#if village['area']?exists&&village['area']!=''>
                        ${'['+village['area']+']'}
                    </#if>
                    <#if village['tradingArea']?exists&&village['tradingArea']!=''>
                        ${'['+village['tradingArea']+']'}
                    </#if>
                </#if>
            <#if village['address']?exists&&village['address']!=''>
            <#assign split = village['address']?split('-')>
            <#if split?size gt 1>${split[1]}<#else >
            ${village['address']!''}</#if></#if></p>
            <p>${village['trafficInformation']!''}</p>
            <div class="house-labelling gray">
            <#if village['label']?exists&&(village['label']?size gt 0)>
                <#list village['label'] as label>
                    <#if label?exists><span>${label}</span></#if>
                </#list>
            </#if>
            </div>
        </div>
        <a href="${router_city('/xiaoqu/'+village['id']+'/map.html')}" class="plot-primary-map-box"><img src="/static/images/plot/detail_static_map.png" alt="地图"></a>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>市场行情<span class="subtitle">洞察价格走势</span></h3>
            <#--<div class="markets-btn"><i class="price-trend-btn current"></i><i class="supply-contrast-btn"></i></div>-->
        </div>
        <div class="basic-information price-trend">
            <div class="column item-column-three">
                <div class="info-card-item">
                    <em>均价</em>
                    <p><#if village['avgPrice']?exists>${village['avgPrice']}元/㎡<#else>暂无数据</#if></p>
                </div>
                <div class="info-card-item">
                    <em>环比上月</em>
                    <p class="green">
                    <#if village['huanbi']?exists&&(village['huanbi'] gt 0)>
                        <#assign x = village['huanbi']?abs * 100>
                        ↑ ${x?string("#.##")}%
                    <#elseif village['huanbi']?exists&&village['huanbi'] lt 0>
                        <#assign x = village['huanbi']?abs * 100>
                        ↓ ${x?string("#.##")}%
                    <#else>
                        暂无数据
                    </#if>
                    </p>
                </div>
                <div class="info-card-item">
                    <em>同比去年</em>
                    <p class="green">
                    <#if village['tongbi']?exists&&(village['tongbi'] gt 0)>
                        <#assign x = village['tongbi']?abs * 100>
                        ↑ ${x?string("#.##")}%
                    <#elseif village['tongbi']?exists&&village['tongbi'] lt 0>
                        <#assign x = village['tongbi']?abs * 100>
                        ↓ ${x?string("#.##")}%
                    <#else>
                        暂无数据
                    </#if>
                    </p>
                </div>
            </div>
            <div>
            <#--<div class="module-header-message">-->
            <#--<h3>价格走势</h3>-->
            <#--</div>-->
            <#if  (mouthList?size>0)>
                <div class="echarts-box">
                    <div class="echarts-content" id="village-price-trade" ></div>
                </div>
            </#if>
            </div>
        </div>
    </section>
</div>
<#if (reViHouse?exists) && (reViHouse?size>0)>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>小区好房</h3>
            <a href="${router_city('/esf?newcode='+village['id'])}" class="more-arrows">查看全部<i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist">
            <#list reViHouse as reitem>
                <#if reitem_index==4>
                    <#break >
                </#if>
                <#assign itemLocation=reitem['housePlotLocation']>
                <li><a href="${router_city('/esf/'+reitem.houseId)+'.html'}">
                    <div class="picture-box">
                        <#if reitem['housePhoto']?exists>
                            <#assign photoitem=reitem['housePhoto']>
                            <#if photoitem[0]?? && photoitem[0] != ''><img src="${photoitem[0]}" alt="">
                            <#else ><img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                            </#if>
                        <#--<#if reitem['buildArea']?exists><p class="bottom-text">${reitem['buildArea']}㎡</p></#if>-->
                        </#if>
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first text-center"><em>
                            <#if reitem['houseTotalPrices']?exists&&reitem['houseTotalPrices']?number gt 0>${reitem.houseTotalPrices+'万'}</#if></em>
                        <#--<#if reitem['forwardName']?exists>${reitem.forwardName}</#if>-->
                        <#if reitem['buildArea']?exists><#--<p class="bottom-text">-->${reitem['buildArea']}㎡<#--</p>--></#if>
                        <#if reitem['room']?exists&&reitem['room']?number gt 0>${reitem.room+'室'}</#if>
                        <#if reitem['hall']?exists&&reitem['hall']?number gt 0>${reitem.hall+'厅'}</#if>
                        </p>
                    </div>
                </a></li>
            </#list>
        </ul>
    </section>
</div>
</#if>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>基本信息<span class="subtitle">了解居住环境</span></h3>
            <a href="${router_city('/xiaoqu/'+village['id']+'/desc.html')}" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <div class="basic-information">
            <div class="column item-only-one">
                <div class="info-card-item" id="base-info">
                <#if village['rc']?exists>${village['rc']}</#if>
                <#if village['abbreviatedAge']?exists&&(village['abbreviatedAge']?number gt 0)>,<em class="high-light-red">${village['abbreviatedAge']}</em>年建成住宅</#if>
                <#if village['sumBuilding']?exists&&(village['sumBuilding']!='')>,共<em class="high-light-red">${village['sumBuilding']}</em>栋</#if>
                <#if village['sumHousehold']?exists>
                    <#if village['sumHousehold']?number gt 0>
                        (${village['sumHousehold']}户)
                    </#if>
                </#if>
                <#if village['buildingStructure']?exists&&(village['buildingStructure']!='')>
                    ,${village['buildingStructure']}
                </#if>
                </div>
            </div>
            <div class="column item-column-two">
                <div class="info-card-item">
                    <i class="item-two-1"></i>
                    <div class="info-item-text">
                        <p>绿化率</p>
                    <#if village['avgGreening']?exists>
                        <#if village['avgGreening'] gt 0>
                            <em>${village['avgGreening']?string('#.##')}%</em>
                        <#else >
                            <em>暂无数据</em>
                        </#if>
                    <#else >
                        <em>暂无数据</em>
                    </#if>
                    </div>
                </div>
                <div class="info-card-item">
                    <i class="item-two-2"></i>
                    <div class="info-item-text">
                        <p>车位配比</p>
                        <em><#if village['carPositionRatio']??&&village['carPositionRatio']!=''>
                           ${village['carPositionRatio']}
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
                        <em><#if village['avgElevator']??&&village['avgElevator']!=''>
                          ${village['avgElevator']}
                        <#else>暂无数据
                        </#if></em>
                    </div>
                </div>
                <div class="info-card-item">
                    <i class="item-two-4"></i>
                    <div class="info-item-text">
                        <p>空气质量</p>
                        <em><#if village['airQuality']??&&village['airQuality']!=''>
                           ${village['airQuality']}
                        <#else>暂无数据
                        </#if></em>
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
            <#if village['metroWithPlotsDistance']?exists>
                <div class="info-card-item">
                    <i class="item-three-1"></i>
                    <em>公交</em>
                        <p id="busStation"><#if datainfo["gongjiao"]["name"]?exists>${datainfo["gongjiao"]["name"]}<#else >暂无数据</#if></p>
                        <span id="busStationNumber"><#if datainfo["gongjiao"]["lines"]?exists>${datainfo["gongjiao"]["lines"]}条线路<#else >暂无数据</#if></span>
                </div>
                <div class="info-card-item">
                    <i class="item-three-2"></i>
                    <em>地铁</em>
                    <p id="subwayLine"><#if datainfo["ditie"]["name"]?exists>${datainfo["ditie"]["name"]}<#else >暂无数据</#if></p>
                    <span id="subwayDistance"><#if datainfo["ditie"]["line"]?exists><#if datainfo["ditie"]["line"]?substring(0,2) == '地铁'>${ datainfo["ditie"]["line"]?substring(2)}<#else>${ datainfo["ditie"]["line"]}</#if><#else >暂无数据</#if></span>
                </div>
            </#if>
                <div class="info-card-item">
                    <i class="item-three-3"></i>
                    <em>自驾</em>
                    <p>${village['ringRoadName']!'暂无数据'}</p>
                    <span>
                    <#if village['ringRoadDistance']?exists>
                    <#--<#if village['ringRoadDistance']?exists && village['ringRoadDistance']!=''>-->
                    <#--${(village['ringRoadDistance']/1000)?string('#.#')}km-->
                    <#--<#else >-->
                    <#--暂无数据-->
                    <#--</#if>-->
                        <#if village['ringRoadDistance']?number gt 0>
                        ${(village['ringRoadDistance']/1000)?string('#.#')}km
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
                    <li class="parent-child" data-type="qinzi"><i></i><span>亲子</span></li>
                    <li class="kindergarten" data-type="youeryuan"><i></i><span>幼儿园</span></li>
                    <li class="primary-school" data-type="xiaoxue"><i></i><span>小学</span></li>
                    <li class="middle-school" data-type="zhongxue"><i></i><span>中学</span></li>
                    <#--<li class="university" data-type="gaodeng"><i></i><span>大学</span></li>-->
                </ul>
            </div>
            <ul class="result-data-expand" id="qinzi">
                <#assign qinzi=datainfo['jiaoyu']['qinzi'] />
                <#list qinzi as item>
                    <li>
                        <p><i class="expand-icon expand-radius">${item_index+1}</i><span class="expand-name">${item.name}</span></p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                </#list>
            </ul>
            <ul class="result-data-expand none" id="youeryuan">
                <#assign youeryuan=datainfo['jiaoyu']['youeryuan'] />
                <#list youeryuan as item>
                    <li>
                        <p><i class="expand-icon expand-radius">${item_index+1}</i><span class="expand-name">${item.name}</span></p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                </#list>
            </ul>
            <ul class="result-data-expand none" id="xiaoxue">
                <#assign xiaoxue=datainfo['jiaoyu']['xiaoxue'] />
                <#list xiaoxue as item>
                    <li>
                        <p><i class="expand-icon expand-radius">${item_index+1}</i><span class="expand-name">${item.name}</span></p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                </#list>
            </ul>
            <ul class="result-data-expand none" id="zhongxue">
                <#assign zhongxue=datainfo['jiaoyu']['zhongxue'] />
                <#list zhongxue as item>
                    <li>
                        <p><i class="expand-icon expand-radius">${item_index+1}</i><span class="expand-name">${item.name}</span></p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                </#list>
            </ul>
            <ul class="result-data-expand none" id="gaodeng">
                <#assign gaodeng=datainfo['jiaoyu']['gaodeng'] />
                <#list gaodeng as item>
                    <li>
                        <p><i class="expand-icon expand-radius">${item_index+1}</i><span class="expand-name">${item.name}</span></p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                </#list>
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
                <#assign caishichang=datainfo["xiuxian"]["caishichang"]/>
                <#list caishichang as item>
                    <li>
                        <p>
                            <i class="expand-icon expand-radius">${item_index+1}</i>
                            <span class="expand-name">
                                <em>${item.name}</em>
                                <em class="star-box">
                                    <#if item.star?exists&& (item.star??)>
                                        <#assign star=item.star?number/>
                                            <#if star gt 0>
                                                    <#list 1..5 as i>
                                                        <#if star gt i>
                                                            <i class="red-star"></i>
                                                        <#else >
                                                            <i class="star-icon"></i>
                                                        </#if>
                                                    </#list>
                                                <#else>
                                            </#if>
                                    </#if>
                                </em>
                            </span>
                        </p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                </#list>
            </ul>
            <ul class="result-data-expand height-type none" id="chaoshi">
                <#assign chaoshi=datainfo["xiuxian"]["chaoshi"]/>
                <#list chaoshi as item>
                    <li>
                        <p>
                            <i class="expand-icon expand-radius">${item_index+1}</i>
                            <span class="expand-name">
                                <em>${item.name}</em>
                                <em class="star-box">
                                    <#if item.star?exists&& (item.star??)>
                                        <#assign star=item.star?number/>
                                        <#if star gt 0>
                                            <#list 1..5 as i>
                                                <#if star gt i>
                                                    <i class="red-star"></i>
                                                <#else >
                                                    <i class="star-icon"></i>
                                                </#if>
                                            </#list>
                                        <#else>
                                        </#if>
                                    </#if>
                                </em>
                            </span>
                        </p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                </#list>
            </ul>
            <ul class="result-data-expand height-type none" id="shangchang">
                <#assign shangchang=datainfo["xiuxian"]["shangchang"]/>
                <#list shangchang as item>
                    <li>
                        <p>
                            <i class="expand-icon expand-radius">${item_index+1}</i>
                            <span class="expand-name">
                                <em>${item.name}</em>
                                <em class="star-box">
                                    <#if item.star?exists&& (item.star??)>
                                        <#assign star=item.star?number/>
                                        <#if star gt 0>
                                            <#list 1..5 as i>
                                                <#if star gt i>
                                                    <i class="red-star"></i>
                                                <#else >
                                                    <i class="star-icon"></i>
                                                </#if>
                                            </#list>
                                        <#else>
                                        </#if>
                                    </#if>
                                </em>
                            </span>
                        </p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                </#list>
            </ul>
            <ul class="result-data-expand height-type none" id="canting">
                <#assign canting=datainfo["xiuxian"]["canting"]/>
                <#list canting as item>
                    <li>
                        <p>
                            <i class="expand-icon expand-radius">${item_index+1}</i>
                            <span class="expand-name">
                                <em>${item.name}</em>
                                <em class="star-box">
                                    <#if item.star?exists&& (item.star??)>
                                        <#assign star=item.star?number/>
                                        <#if star gt 0>
                                            <#list 1..5 as i>
                                                <#if star gt i>
                                                    <i class="red-star"></i>
                                                <#else >
                                                    <i class="star-icon"></i>
                                                </#if>
                                            </#list>
                                        <#else>
                                        </#if>
                                    </#if>
                                </em>
                            </span>
                        </p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                </#list>
            </ul>
            <ul class="result-data-expand height-type none" id="jianshenzhongxin">
                <#assign jianshenzhongxin=datainfo["xiuxian"]["jianshenzhongxin"]/>
                <#list jianshenzhongxin as item>
                    <li>
                        <p>
                            <i class="expand-icon expand-radius">${item_index+1}</i>
                            <span class="expand-name">
                                <em>${item.name}</em>
                                <em class="star-box">
                                    <#if item.star?exists&& (item.star??)>
                                        <#assign star=item.star?number/>
                                        <#if star gt 0>
                                            <#list 1..5 as i>
                                                <#if star gt i>
                                                    <i class="red-star"></i>
                                                <#else >
                                                    <i class="star-icon"></i>
                                                </#if>
                                            </#list>
                                        <#else>
                                        </#if>
                                    </#if>
                                </em>
                            </span>
                        </p>
                        <span class="expand-distance">${(item.distance/1000)?string("0.##")}km</span>
                    </li>
                </#list>
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill" id="hospitalListWrapper">
    <section>
        <div class="module-header-message">
            <h3>医疗配套<span class="subtitle">为您的健康保驾护航</span></h3>
            <a href="javascript:;" class="more-arrows expand-btn"><i class="arrows-expand"></i></a>
        </div>
        <div class="expand-content">
            <#assign yiliao=datainfo['yiliao'] />
            <ul class="result-data-expand" id="hospitalListDom">
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
            <a href="javascript:;" class="more-arrows expand-btn"><i class="arrows-expand"></i></a>
        </div>
        <div class="expand-content">
            <ul class="result-data-expand">
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">水费</span>
                    <#if village['waterFee']?exists>
                        <span class="expand-price">${village['waterFee']}元/吨</span>
                    <#else >
                        <span class="expand-price">暂无数据</span>
                    </#if>
                    </p>
                <#--<span class="expand-distance tips">居民用水价格范围为1-4元/吨</span>-->
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">电费</span>
                    <#if village['electricFee']?exists>
                        <span class="expand-price">${village['electricFee']}元/度</span>
                    <#else >
                        <span class="expand-price">暂无数据</span>
                    </#if>
                    </p>
                <#--<span class="expand-distance tips">居民用电价格范围为1-4元/度</span>-->
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">物业费</span>
                    <#if village['propertyFee']?exists>
                        <span class="expand-price">
                            <#if village['propertyFee']?number gt 0>
                            ${village['propertyFee']}元/㎡·月
                            <#else >
                                暂无数据
                            </#if>
                        </span>
                    <#else >
                        <span class="expand-price">暂无数据</span>
                    </#if>
                    </p>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">停车费</span>
                    <#if village['parkingRate']?exists&&village['parkingRate']!=''>
                        <span class="expand-price">
                            <#if village['parkingRate']??>
                            ${village['parkingRate']}元/月
                            <#else >
                                暂无数据
                            </#if>
                        </span>
                    <#else >
                        <span class="expand-price">暂无数据</span>
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
            <a href="${router_city('/xiaoqu/'+village['id']+'/map.html')}" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <a href="${router_city('/xiaoqu/'+village['id']+'/map.html')}" class="detail-map">
            <i class="map-marker-icon"></i>
            <#if village['location']?exists>
                <#assign locationIp = village['location'] ? split(",")>
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=${locationIp[1]},${locationIp[0]}&&zoom=16" alt="">
            <#else>
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=116.382001,39.913329&&zoom=16" alt="">
            </#if>
        </a>
    </section>
</div>
<#if reViHouse?exists&&reViHouse?size gt 0>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>待售房源</h3>
            <a href="${router_city('/esf?newcode='+village['id'])}" class="more-arrows">查看全部<i class="arrows-right"></i></a>
        </div>
    </section>
</div>
</#if>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>看了本小区的用户还看了</h3>
        </div>
        <ul class="tilelist">
        <#list nearvillage as nearviitem>
            <#if nearviitem_index == 4>
                <#break>
            </#if>
            <li><a href="${router_city('/xiaoqu/'+nearviitem['id']?c+'.html')}">
                <div class="picture-box">
                    <#assign photos = nearviitem['photo']>
                    <#if photos[0]?exists>
                        <img src="${qiniuimage}/${photos[0]}-tt400x300" alt="${nearviitem['rc']}">
                    <#else><img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                    </#if>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">${nearviitem['rc']}</p>
                    <p class="cont-center">
                        <span>${nearviitem['area']!" "}</span><span>${nearviitem['tradingArea']!" "}</span>
                    </p>
                    <h4 class="cont-last">均价：<em>${nearviitem['avgPrice']}</em>元/㎡</h4>
                </div>
            </a></li>
        </#list>
        </ul>
    </section>
</div>
<section>
    <div class="module-header-message">
        <h3>新房推荐</h3>
    </div>
    <ul class="tilelist">
    <#list newbuilds as builditem>
        <li><a href="${router_city('/loupan/'+builditem['building_name_id']?c+'.html')}">
            <div class="picture-box">
                <#assign imglist = builditem['building_title_img']>
                <#if imglist?exists >
                    <#if imglist?split(",")[0]?? && imglist?split(",")[0] != ''><img src="${qiniuimage}/${imglist?split(",")[0]}-tt400x300" alt="${imglist?split(",")[0]}">
                        <#else ><img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                    </#if>
                </#if>
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">${builditem['building_name']!''}</h4>
                <#if builditem['average_price']?exists && builditem['average_price'] gt 0>
                    <p class="cont-last">均价：<em>${builditem['average_price']}元</em>/㎡</p>
                <#else>
                    <#if builditem['total_price']?exists && builditem['total_price'] gt 0>
                        <p class="cont-last">总价：<em>${builditem['total_price']}万</em>/套</p>
                    <#else><em>售价待定</em>
                    </#if>
                </#if>

            </div>
        </a></li>
    </#list>
    </ul>
</section>
<#--22222222222222-->
<!-------- photoswipe -------->
<script src="${staticurl}/js/photoswipe.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>
<script src="${staticurl}/js/plot-detail-map-message.js?v=${staticversion}"></script>
<script>
    var chartGrid = {
        left: '2%',
        right: '6%',
        bottom: 0,
        containLabel: true
    };
    var baseFontSize = 12 * dpr;
    var baseItemWidth = 25 * dpr;
    <#if  (mouthList?size>0)>
    var myChartline = echarts.init(document.getElementById('village-price-trade'), null, {renderer: 'svg'}, {
        devicePixelRatio: dpr,
        width: '100%',
        height: '100%'
    });
    </#if>
        option = {
            textStyle: {fontSize: baseFontSize},
            tooltip: {
                trigger: 'axis',
                textStyle: {fontSize: baseFontSize}
            },
            legend: {
                itemGap: 20,
                itemWidth: baseItemWidth,
                data:['${village['area']!'区域'}价格','${village['tradingArea']!'商圈'}价格'],
            },
            grid: chartGrid,
            xAxis:
                {
                    show: true,
                    boundaryGap: false,
                    scale: true,
                    axisLabel: {fontSize: baseFontSize - 4},
                    data: [<#list  mouthList as item >'${item}',</#list>],
                },
            yAxis: {
                type: 'value',
                axisLabel: {
                    formatter: '{value}',
                    fontSize:8
                },
                scale:true
            },
            dataZoom: [
                {
                    type: 'inside',
                    start: 50,
                    end: 100,
                    filterMode: 'empty',
                    zoomLock: true
                }
            ],
            series: [
                {
                    name:'${village['area']!'区域'}价格',
                    type:'line',
                    data:[<#list ptCD1 as item ><#if item['price'] != 0&&item['price']??>['${item['tumonth']}',${item['price']}],<#else></#if></#list>],
                    showSymbol: false
                },
                {
                    name:'${village['tradingArea']!'商圈'}价格',
                    type:'line',
                    data:[<#list ptCD2 as item ><#if item['price'] != 0&&item['price']??>['${item['tumonth']}',${item['price']}],<#else></#if></#list>],
                    showSymbol: false
                }
            ]
        };
    <#if (mouthList?size>0)>
        myChartline.setOption(option);
    </#if>
</script>
<script>
    $(function () {
        var _divContent = $('#base-info').html();
        if (_divContent.indexOf(',') == 0) {
            _divContent = _divContent.substring(1);
            $('#base-info').html(_divContent);
        }
    });
</script>
</body>
</html>