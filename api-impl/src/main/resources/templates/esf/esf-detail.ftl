<!DOCTYPE html>
<html>
<head>
<#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css?v=${staticversion}">
    <link rel="stylesheet" href="${staticurl}/css/esf-detail.css?v=${staticversion}">
    <title><#if houseDetail.plotName?exists&&houseDetail.plotName!=''>${houseDetail.plotName}</#if>  <#if houseDetail.buildArea?exists &&(houseDetail.buildArea!=0)>${houseDetail.buildArea}
        ㎡</#if> <#if houseDetail.room?exists&&houseDetail.hall?exists>${houseDetail.room}室${houseDetail.hall}厅</#if></title>
    <meta name="description"
          content="我在头条房产发现一套 【<#if houseDetail.plotName?exists&&houseDetail.plotName!=''>${houseDetail.plotName}</#if>】【 <#if houseDetail.houseTotalPrices?exists&&(houseDetail.houseTotalPrices!=0)>${houseDetail.houseTotalPrices}</#if>】【<#if houseDetail.room?exists&&houseDetail.hall?exists>${houseDetail.room}室${houseDetail.hall}厅</#if>】的房子推荐给你">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js?v=${staticversion}"></script>
    <script type="text/javascript" src="${staticurl}/js/loghub-tracking.js" async></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
    <#include "../StatisticsHeader.ftl">
    <script type="text/javascript">
        (function(root) {
            root._tt_config = true;
            var ta = document.createElement('script'); ta.type = 'text/javascript'; ta.async = true;
            ta.src = document.location.protocol + '//' + 's1.pstatp.com/bytecom/resource/track_log/src/toutiao-track-log.js';
            ta.onerror = function () {
                var request = new XMLHttpRequest();
                var web_url = window.encodeURIComponent(window.location.href);
                var js_url  = ta.src;
                var url = '//ad.toutiao.com/link_monitor/cdn_failed?web_url=' + web_url + '&js_url=' + js_url + '&convert_id=1598714777660436';
                request.open('GET', url, true);
                request.send(null);
            }
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ta, s);
        })(window);
    </script>
</head>
<body>
<img class="shareTopImg" height="0" width="0" src="<#if houseDetail['housePhoto']?? && (houseDetail['housePhoto']?size>0)>${houseDetail['housePhoto'][0]!""}</#if>" alt="">
<div class="carousel-box">
    <div class="swiper-container carousel-swiper" id="detail-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
        <#if houseDetail['claimHouseId']?exists>
            <#if houseDetail['housePhoto']?exists&& houseDetail['housePhoto']?size gt 0>
                <#list houseDetail['housePhoto'] as itemValue>
                    <li onclick="initphoto(this,${itemValue_index})" class="swiper-slide">
                        <#if itemValue?string?index_of("http") gt -1>
                            <img src="${itemValue}" data-src="${itemValue}" alt="">
                        <#else>
                            <img src="${qiniuimage}/${itemValue}-ttfc1200x640" data-src="${qiniuimage}/${itemValue}-ttfc1200x640" alt="">
                        </#if>
                    </li>
                </#list>
            <#else>
                <li onclick="initphoto(this,0)" class="swiper-slide">
                    <img src="${staticurl}/images/global/tpzw_banner_image.png" data-src="${staticurl}/images/global/tpzw_banner_image.png" alt="拍摄中">
                </li>
            </#if>
        <#else >
            <#if houseDetail['housePhoto']?? && (houseDetail['housePhoto']?size>0)>
                <#list houseDetail['housePhoto'] as itemValue>
                    <li onclick="initphoto(this,${itemValue_index})" class="swiper-slide">
                        <#if itemValue?string?index_of("http") gt -1>
                            <img src="${itemValue}" data-src="${itemValue}" alt="">
                        <#else>
                            <img src="${qiniuimage}/${itemValue}-ttfc1200x640" data-src="${qiniuimage}/${itemValue}-ttfc1200x640" alt="">
                        </#if>
                    </li>
                </#list>
            <#else>
                <li onclick="initphoto(this,0)" class="swiper-slide">
                    <img src="${staticurl}/images/global/tpzw_banner_image.png" data-src="${staticurl}/images/global/tpzw_banner_image.png" alt="拍摄中">
                </li>
            </#if>
        </#if>
        </ul>
        <div class="banner-title">
            <#if houseDetail.claimHouseId?exists && houseDetail.claimHouseId!=''>
                <div class="banner-house-number">房源编号：<em class="sellHouseId">${houseDetail.claimHouseId}</em></div>
            <#else >
                <div class="banner-house-number">房源编号：<em class="sellHouseId">${houseDetail.houseId}</em></div>
            </#if>
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
        <div class="primary-header text-center">
            <#if houseDetail.claimHouseId?exists>
                <#if houseDetail.claimHouseTitle?exists && houseDetail.claimHouseTitle!=''>
                    <h2>${houseDetail.claimHouseTitle}</h2>
                </#if>
            <#else >
                <h2><#if houseDetail.houseTitle?exists>${houseDetail.houseTitle}</#if></h2>
            </#if>
            <div class="primary-header-tag house-labelling gray">
            <#if houseDetail.claimHouseId?exists>
                <#if houseDetail['claimTagsName']?exists&&houseDetail['claimTagsName']?size gt 0>
                    <#list houseDetail['claimTagsName'] as itemValue>
                            <span>${itemValue}</span>
                    </#list>
                </#if>
            <#else >
                <#assign item =houseDetail['tagsName']>
                <#list item as itemValue>
                    <#if itemValue?exists>
                        <span>${itemValue}</span>
                    </#if>
                </#list>
            </#if>
            </div>
        </div>
        <ul class="primary-item">
            <li>
                <ol>
                    <li>
                        <span>总价</span>
                        <em>
                        <#if houseDetail.houseTotalPrices?exists&&(houseDetail.houseTotalPrices!=0)>
                        ${houseDetail.houseTotalPrices}万
                        <#else>
                            暂无数据
                        </#if>
                        </em>
                    </li>
                    <li>
                        <span>户型</span>
                        <em><#if houseDetail.room?exists&&houseDetail.hall?exists>
                        ${houseDetail.room}室${houseDetail.hall}厅<#if houseDetail.toilet?exists>${houseDetail.toilet}卫</#if><#if houseDetail.forwardName?exists>/${houseDetail.forwardName} </#if>
                        <#else>
                            暂无数据
                        </#if>
                        </em>
                    </li>
                    <li>
                        <span>面积</span>
                        <em>
                        <#if houseDetail.buildArea?number?exists &&(houseDetail.buildArea?number!=0)>
                        ${houseDetail.buildArea?string("0.########")}㎡
                        <#else>
                            暂无数据
                        </#if>
                        </em>
                    </li>
                </ol>
            </li>
            <li>
                <p>单价：
                <#if houseDetail.houseTotalPrices?exists&&houseDetail.buildArea?exists
                &&houseDetail.houseTotalPrices?number gt 0&&houseDetail.buildArea?number gt 0>
                ${(((houseDetail.houseTotalPrices / houseDetail.buildArea)) * 10000)?floor}元/㎡
                <#else>
                    暂无数据
                </#if>
                </p>
            </li>
            <li>
                <p>预算：
                <#if houseDetail.housingDeposit?exists&&houseDetail.houseMonthPayment?exists>
                    参考首付${houseDetail.housingDeposit}万，月供${houseDetail.houseMonthPayment}元/月
                <#else>
                    暂无数据
                </#if>
                </p>
            </li>
            <li>
                <dl class="module-table-item">
                    <dd class="odd-item">楼层：<span>
                    <#if (houseDetail.floor?exists&& (houseDetail.floor!=''))&& (houseDetail.totalFloor?exists&&(houseDetail.totalFloor!=0))>
                    ${houseDetail.floor}楼层/共${houseDetail.totalFloor}层
                    <#else >
                        <#if houseDetail.floor?exists&& (houseDetail.floor!='')>
                        ${houseDetail.floor}楼层
                        </#if >
                        <#if houseDetail.totalFloor?exists&&(houseDetail.totalFloor!=0)>
                            共${houseDetail.totalFloor}层
                        </#if >
                        <#if (houseDetail.totalFloor??&&houseDetail.totalFloor==0)&&(houseDetail.floor??&&houseDetail.floor=='')>
                            暂无数据
                        </#if >
                    </#if>
                    </span></dd>
                    <dd class="even-item">电梯：<em><#if houseDetail.elevatorName?exists>${houseDetail.elevatorName}电梯<#else>暂无数据</#if></em></dd>
                    <dd class="odd-item">类别：
                    <#if houseDetail.buildCategoryName?exists&& (houseDetail.buildCategoryName !='')>
                        <em>${houseDetail.buildCategoryName}</em>
                    <#else>
                    <#-- <#if houseDetail.houseTypeName?exists && (houseDetail.houseTypeName!='') >
                         <em>${houseDetail.houseTypeName}</em>
                     <#else>
                         暂无数据
                     </#if>-->
                        暂无数据
                    </#if>
                    </dd>
                    <dd class="even-item">建成年代：<em><#if houseDetail.year?exists>${houseDetail.year}年<#else>暂无数据</#if></em>
                    </dd>
                <#if houseDetail.plotName?exists&&houseDetail.plotName!=''>
                    <dt>小区：
                        <em>
                        ${houseDetail.plotName}
                            <#if houseDetail.area?exists&&houseDetail.area!=''&&houseDetail.houseBusinessName?exists&&houseDetail.houseBusinessName!=''>
                                [${houseDetail.area}-${houseDetail.houseBusinessName}]
                            <#else >
                                <#if houseDetail.area?exists&&houseDetail.area!=''>
                                    [${houseDetail.area}]
                                </#if>
                                <#if houseDetail.houseBusinessName?exists&&houseDetail.houseBusinessName!=''>
                                    [${houseDetail.houseBusinessName}]
                                </#if>
                            </#if>
                        </em>
                    </dt>
                </#if>
                <#if houseDetail.updateTime?exists&&houseDetail.updateTime!=''>
                    <dt>更新时间：${houseDetail.updateTime}</dt></#if>
                </dl>
            </li>

        <#if houseDetail.traffic?exists && houseDetail.traffic!=''>
            <li>
                <p id="traffic_info">
                    交通信息：距离${houseDetail.traffic?split("$")[0]}${houseDetail.traffic?split("$")[1]}${houseDetail.traffic?split("$")[2]}m
                    <em class="primary-distance">
                        <a href="${router_city('/esf/'+houseDetail.newcode+'/map.html')}" class="primary-map-icon"></a>
                        <a href="${router_city('/esf/'+houseDetail.newcode+'/map.html')}" class="arrows-right"></a>
                    </em>
                </p>
            </li></#if>
        </ul>
    </section>
</div>


<#if houseDetail['claimHouseId']?exists>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>房源描述</h3>
        </div>
        <div class="describe-box">
            <div class="describe-header">
                <img class="source-icon" <#if houseDetail['houseProxyPhoto']?exists&& houseDetail['houseProxyPhoto']!=''>src="${qiniuimage}/${houseDetail['houseProxyPhoto']}-tt100x141" alt="名片" onerror="this.src='${staticurl}/images/global/tpzw_image.png'" <#else >src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中"</#if>>
                <p>
                    <span>
                        <#if houseDetail['ofCompany']?exists&&houseDetail['ofCompany']!=''>【${houseDetail['ofCompany']}】</#if>
                        <#if houseDetail['houseProxyName']?exists&&houseDetail['houseProxyName']!=''>${houseDetail['houseProxyName']}</#if></span>
                    <em>房屋信息发布人</em>
                </p>
                <#if houseDetail['houseProxyPhone']?exists&&houseDetail['houseProxyPhone']!=''>
                    <a href="tel:${houseDetail['houseProxyPhone']}" class="issuer-tel-icon"></a>
                </#if>
            </div>
            <#if houseDetail['houseDesc']?exists&&houseDetail['houseDesc']!=''>
                <div class="describe-cont">
                    <p>${houseDetail['houseDesc']}</p>
                <#--<span class="describe-show-btn">>>展开</span>-->
                </div>
            </#if>

        </div>
    </section>
</div>
<#elseif houseDetail.houseProxyPhone?exists||houseDetail.houseProxyPhoto?exists||houseDetail.ofCompany?exists||houseDetail.houseDesc?exists>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>房源描述</h3>
        </div>
        <div class="describe-box">
            <div class="describe-header">
                <img class="source-icon" <#if houseDetail.houseProxyPhoto?exists>src="${houseDetail.houseProxyPhoto}" alt="" onerror="this.src='${staticurl}/images/global/tpzw_image.png'" <#else >src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中"</#if>>
                <p>
                    <span>
                        <#if houseDetail.ofCompany?exists&&houseDetail.ofCompany!=''>【${houseDetail.ofCompany}】</#if>
                        <#if houseDetail.houseProxyName?exists&&houseDetail.houseProxyName!=''>${houseDetail.houseProxyName}</#if></span>
                    <em>房屋信息发布人</em>
                </p>
                <#if houseDetail.houseProxyPhone?exists&&houseDetail.houseProxyPhone!=''>
                    <a href="tel:${houseDetail.houseProxyPhone}" class="issuer-tel-icon"></a>
                </#if>
            </div>
            <#if houseDetail.houseDesc?exists&&houseDetail.houseDesc!=''>
                <div class="describe-cont">
                    <p>${houseDetail.houseDesc}</p>
                <#--<span class="describe-show-btn">>>展开</span>-->
                </div>
            </#if>

        </div>
    </section>
</div>
</#if>
<#if houseDetail.newcode?exists || houseDetail['plotPhoto']?exists|| houseDetail.plotName?exists|| houseDetail.plotdesc?exists>
<div  class="module-bottom-fill">
    <section>
        <#if houseDetail.newcode?exists>
            <div  class="module-header-message">
                <h3>小区信息</h3>
                <a onclick="plotDetailInfo_1(this)" href="${router_city('/xiaoqu/'+houseDetail.newcode)+'.html'}" class="more-arrows">小区详情<i class="arrows-right"></i></a>
            </div>
        <ul class="tilelist row">
        <li>
        </#if>
        <a onclick="plotDetailInfo_2(this)" href="${router_city('/xiaoqu/'+houseDetail.newcode+'.html')}">

            <div class="picture-box">
                <#assign item=houseDetail['plotPhoto']>
                <#if item[0]?exists><img src="${qiniuimage}/${item[0]}-tt400x300" alt="${houseDetail.plotName}"><#else ><img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中"></#if>
            </div>
            <div id="tilePlotDesc" class="tilelist-content">
                <h4>
                    <#if houseDetail.plotName?exists>${houseDetail.plotName}<#else></#if>
                </h4>
                <p>
                    <#if village['abbreviatedAge']?exists&&(village['abbreviatedAge']?number gt 0)>
                        <em class="high-light-red">${village['abbreviatedAge']}</em>年建成住宅,
                    </#if>
                    <#if village['sumBuilding']?exists&&(village['sumBuilding']!='')>共<em class="high-light-red">${village['sumBuilding']}</em>栋</#if>
                    <#if village['sumHousehold']?exists&&village['sumHousehold']?number gt 0>(${village['sumHousehold']}户)</#if>
                    <#if village['buildCategoryName']?exists&&(village['buildCategoryName']!='')>${village['buildCategoryName']}</#if>
                </p>
                <p>
                    <#if village['avgPrice']?exists&&(village['avgPrice']?number gt 0)>
                        参考均价<em class="high-light-red">${village['avgPrice']}元</em>/㎡
                    </#if>
                </p>
            </div>
        </a>
    </li>
    </ul>
    </section>
</div>
</#if>
<#if houseDetail.newcode?exists||(houseDetail.lat?exists&&houseDetail.lon?exists)>
<div class="module-bottom-fill">
    <section>
        <#if houseDetail.newcode?exists>
            <div class="module-header-message">
                <h3>配套地图</h3>
                <a onclick="esf_map_1(this)" href="${router_city('/esf/'+houseDetail.newcode+'/map.html')}" class="more-arrows"><i class="arrows-right"></i></a>
            </div>
        </#if>
        <a onclick="esf_map_2(this)" href="${router_city('/esf/'+houseDetail.newcode+'/map.html')}" class="detail-map">
            <i class="map-marker-icon"></i>
            <#if houseDetail.lat?exists&&houseDetail.lon?exists>
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=${houseDetail.lat?if_exists?string("####.#######################")},${houseDetail.lon?if_exists?string("####.#######################")}&&zoom=16" alt="">
            <#else >
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=116.382001,39.913329&&zoom=16" alt="">
            </#if>
        </a>
    </section>
</div>
</#if>
<#if plot?exists>
<div id="nearbynewesf">
    <section>
        <div class="module-header-message">
            <h3>看过本房的用户正在看</h3>
        </div>
        <ul class="tilelist-type">
            <#list plot as map>
                <#if map.claimHouseId?exists>
                    <li>
                        <#--<img src='http://${exposurelogproject}.${exposureloghost}/logstores/${exposurelogstore}/track.gif?APIVersion=0.6.0&houseId=${map.claimHouseId}&__topic__=esfbaoguang'/>-->
                        <#if map.claimHouseId?exists><a href="${router_city('/esf/'+map.claimHouseId+'.html')}">
                        <#else><a href="#">
                        </#if>
                        <div class="picture-box">
                            <#if map.claimHousePhotoTitle?exists && map.claimHousePhotoTitle!=''>
                                <#if map.claimHousePhotoTitle?string?index_of("http") gt -1>
                                    <img src="${map.claimHousePhotoTitle}" alt="${map.plotName}">
                                <#else>
                                    <img src="${qiniuimage}/${map.claimHousePhotoTitle}-tt400x300" alt="${map.plotName}">
                                </#if>
                            <#else >
                                <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                            </#if>
                            <div class="bottom-text">
                                <#if map.housetToPlotDistance?exists>${map.housetToPlotDistance}以内</#if>
                            </div>
                        </div>
                        <div class="tilelist-content">
                            <h4 class="cont-last"><#if map.claimHouseTitle?exists>${map.claimHouseTitle}</#if></h4>
                            <p class="cont-first">
                            <em><#if map.houseTotalPrices?exists && map.houseTotalPrices!=0>${map.houseTotalPrices}万</em></#if>
                                <#if map.buildArea?exists&&(map.buildArea>0)>${map.buildArea}㎡</#if>
                                <#if map.room?exists&&map.hall?exists>
                                    <#if map.room?number lt 99> ${map.room}<#elseif map.room?number gte 99>多</#if>室</#if>
                                <#if map.forwardName?exists> ${map.forwardName}</#if>
                            </p>
                            <div class="cont-block-4 house-labelling normal">
                                <#if map.claimTagsName?exists>
                                    <#assign item = map.claimTagsName>
                                    <#list item as itemValue>
                                        <#if itemValue?exists>
                                            <#if itemValue_index lt 3>
                                                <span>${itemValue}</span>
                                            </#if>
                                        </#if>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </a></li>
                <#else>
                    <li>
                        <#if map.houseId?exists><a href="${router_city('/esf/'+map.houseId+'.html')}">
                        <#--<img src='http://${exposurelogproject}.${exposureloghost}/logstores/${exposurelogstore}/track.gif?APIVersion=0.6.0&houseId=${map.houseId}&__topic__=esfbaoguang'/>-->
                        <#else><a href="#">
                        </#if>
                        <div class="picture-box">
                            <#if map.housePhotoTitle?exists && map.housePhotoTitle!=''>
                                <#if map.housePhotoTitle?string?index_of("http") gt -1>
                                    <img src="${map.housePhotoTitle}" alt="${map.plotName}">
                                <#else>
                                    <img src="${qiniuimage}/${map.housePhotoTitle}-tt400x300" alt="${map.plotName}" >
                                </#if>
                            <#else >
                                <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                            </#if>
                            <div class="bottom-text">
                                <#if map.housetToPlotDistance?exists>${map.housetToPlotDistance}以内</#if>
                            </div>
                        </div>
                        <div class="tilelist-content">
                            <h4 class="cont-last"><#if map.houseTitle?exists>${map.houseTitle}</#if></h4>
                            <p class="cont-first">
                            <em><#if map.houseTotalPrices?exists && map.houseTotalPrices!=0>${map.houseTotalPrices}万</em></#if>
                                <#if map.buildArea?exists&&(map.buildArea>0)>${map.buildArea}㎡</#if>
                                <#if map.room?exists&&map.hall?exists>
                                    <#if map.room?number lt 99> ${map.room}<#elseif map.room?number gte 99>多</#if>室</#if>
                                <#if map.forwardName?exists> ${map.forwardName}</#if>
                            </p>
                            <div class="cont-block-4 house-labelling normal">
                                <#if map.tagsName?exists>
                                    <#assign item = map.tagsName>
                                    <#list item as itemValue>
                                        <#if itemValue?exists>
                                            <#if itemValue_index lt 3>
                                                <span>${itemValue}</span>
                                            </#if>
                                        </#if>
                                    </#list>
                                </#if>
                            </div>
                        </div>
                    </a></li>
                </#if>
                <#--<li>-->
                    <#--<#if map.houseId?exists><a href="${router_city('/esf/'+map.houseId+'.html')}">-->
                    <#--<#else><a href="#">-->
                    <#--</#if>-->
                    <#--<div class="picture-box">-->
                        <#--<#if map.housePhotoTitle?exists && map.housePhotoTitle!=''>-->
                            <#--<img src="${map.housePhotoTitle}" alt="${map.plotName}">-->
                        <#--<#else >-->
                            <#--<img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">-->
                        <#--</#if>-->
                        <#--<div class="bottom-text">-->
                            <#--<#if map.housetToPlotDistance?exists>${map.housetToPlotDistance}以内</#if>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="tilelist-content">-->
                        <#--<h4 class="cont-last"><#if map.houseTitle?exists>${map.houseTitle}</#if></h4>-->
                        <#--<p class="cont-first">-->
                            <#--<em><#if map.houseTotalPrices?exists && map.houseTotalPrices!=0>${map.houseTotalPrices}万</em></#if>-->
                                <#--<#if map.buildArea?exists&&(map.buildArea>0)>${map.buildArea}㎡</#if>-->
                                <#--<#if map.room?exists&&map.hall?exists>-->
                                <#--<#if map.room?number lt 99> ${map.room}<#elseif map.room?number gte 99>多</#if>室</#if>-->
                                <#--<#if map.forwardName?exists> ${map.forwardName}</#if>-->
                        <#--</p>-->
                        <#--<div class="cont-block-4 house-labelling normal">-->
                            <#--<#if map.tagsName?exists>-->
                                <#--<#assign item = map.tagsName>-->
                                <#--<#list item as itemValue>-->
                                    <#--<#if itemValue?exists>-->
                                        <#--<#if itemValue_index lt 3>-->
                                            <#--<span>${itemValue}</span>-->
                                        <#--</#if>-->
                                    <#--</#if>-->
                                <#--</#list>-->
                            <#--</#if>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</a></li>-->
            </#list>
        </ul>
    </section>
</div>
</#if>
<#--<#if plotList?exists>
<section id="nearbypLOT">
    <div class="module-header-message">
        <h3>附近小区</h3>
    </div>
    <ul class="tilelist">
        <#list plotList as plotInfo>
            <li><a href="${router_city('/xiaoqu/'+plotInfo['id']+'.html')}">
                <div class="picture-box">
                    <#if plotInfo['photo']?exists>
                        <#assign plotImage=plotInfo['photo'] >
                        <#if plotImage[0]?exists><img src="${qiniuimage}/${plotImage[0]}-tt400x300" alt="${plotInfo.rc}">
                        <#else >
                            <img src="${staticurl}/images/global/tpzw_image.png" alt="${plotInfo.rc}">
                        </#if >
                    </#if>
                </div>
                <div class="tilelist-content">
                    <#if plotInfo['rc']?exists>
                        <h4 class="cont-first">${plotInfo.rc}</h4>
                    </#if>
                    <#if plotInfo['avgPrice']?exists>
                        <p class="cont-last"><em>${plotInfo.avgPrice}元</em>/㎡</p>
                    </#if>
                </div>
            </a></li>
        </#list>
    </ul>
</section>
</#if>-->

<#--<#if houseDetail.houseProxyPhone?exists>-->
<#--<div class="detail-contact-wrapper">-->
    <#--<section class="detail-contact-box" id="detailContactState">-->
        <#--<div class="detail-contact-content">-->
            <#--<a href="tel:${houseDetail.houseProxyPhone}" class="only contact-telephone-counseling">咨询经纪人</a>-->
        <#--</div>-->
    <#--</section>-->
<#--</div>-->
<#--<#else >-->
    <#if houseDetail['claimHouseId']?exists&&houseDetail['claimHouseId']!=''>
        <div class="detail-contact-wrapper">
            <section class="detail-contact-box" id="detailContactState">
                <div class="detail-contact-content">
                    <a href="tel:${houseDetail['houseProxyPhone']}" class="contact-telephone-counseling"><img src="${staticurl}/images/tel0415.png" style="height: 60%;margin-top: -0.06rem;">立即咨询底价</a>
                    <a href="javascript:void(0)" class="contact-next">预约咨询</a>
                </div>
            </section>
        </div>
    <#else>
        <#if houseDetail.houseProxyPhone?exists>
            <div class="detail-contact-wrapper">
                <section class="detail-contact-box" id="detailContactState">
                    <div class="detail-contact-content">
                        <a href="tel:${houseDetail.houseProxyPhone}" class="only contact-telephone-counseling"><img src="${staticurl}/images/tel0415.png" style="height: 60%;margin-top: -0.06rem;">立即咨询底价</a>
                        <#--<a href="javascript:void(0)" class="contact-next">预约咨询</a>-->
                    </div>
                </section>
            </div>
        </#if>
    </#if>
<#--</#if>-->
<#if houseDetail.houseProxyPhone?exists>
<div class="reservation-pop none">
    <div class="mask"></div>
    <div class="reservation-content">
        <h2>预约咨询</h2>
        <p>您对本房源和小区还想多了解一点？请马上与本小区资深顾问预约联系！您可以提供进一步信息，以便获得更深入解答</p>

        <div class="user-phone">
            <div class="clear"><span>电话<em class="required">*</em>：</span><input class="userPhone" type="tel" placeholder="请输入您的手机号码" maxlength="11" /></div>
            <div class="error clear none"><span class="error-text"></span></div>
        </div>
        <div class="textarea-content">
            <div class="clear">
                <p>留言<em class="required">*</em>：</p>
                <textarea class="user-content"></textarea>
            </div>
            <div class="error clear none"><span class="error-text"></span></div>
        </div>
        <button class="reservation-submit">确定预约</button>
        <a class="dialing" href="tel:${houseDetail.houseProxyPhone}">等不及？马上拨打经纪人电话</a>
    </div>
</div>
</#if>
<!-------- photoswipe -------->
<script src="${staticurl}/js/fastclick.js?v=${staticversion}"></script>
<script src="${staticurl}/js/default-touch.js?v=${staticversion}"></script>
<script src="${staticurl}/js/photoswipe.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/URI.min.js?v=${staticversion}"></script>
<script src="${staticurl}/js/main.js?v=${staticversion}"></script>
<script>
    $(function () {
        $('.contact-next').on('click', function () {
            $('.reservation-pop').removeClass('none');
        });
        $('.reservation-pop').on('click', '.mask', function (e) {
            $('.reservation-pop').addClass('none');
        });
        var subPhone = false;
        var reservationData = {};

        reservationData['sellHouseId'] = $('.sellHouseId').text();
        reservationData['price'] = <#if houseDetail.houseTotalPrices?exists&&(houseDetail.houseTotalPrices!=0)>${houseDetail.houseTotalPrices}<#else ></#if>;
        reservationData['area'] = <#if houseDetail.buildArea?exists&& houseDetail.buildArea!=0>${houseDetail.buildArea}<#else ></#if>;
        reservationData['room'] = <#if houseDetail.room?exists>${houseDetail.room}<#else ></#if>;
        reservationData['hall'] = <#if houseDetail.hall?exists>${houseDetail.hall}<#else ></#if>;

        $('.userPhone').on('blur', function () {
            isPhone($(this).val());
        });
        var isPhone = function (str) {
            var reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
            if (reg.test(str)) {
                subPhone = true;
                $('.user-phone').find('.error').addClass('none');
            } else {
                subPhone = false;
                $('.user-phone').find('.error').removeClass('none');
                $('.user-phone').find('.error-text').text('请输入正确格式的手机号码');
            }
        };

        $('.reservation-submit').on('click', function () {
            if ($('.userPhone').val() != '' && subPhone) {
                reservationData['userPhone'] = $('.userPhone').val();
                reservationData['content'] = $('.user-content').val();
                $.ajax({
                    type: 'POST',
                    url: '/duankou/v1.0.0/agentHouseSell/saveAgentHouseSellLeaveMessage',
                    data: reservationData,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == '0' && data.data == 'success') {
                            $('.reservation-pop').addClass('none');
                            $('.userPhone').val('');
                            $('.user-content').val('');
                        }
                    },
                    error: function (msg) {
                        console.log(msg)
                    }
                });
            } else {
                if ($('.userPhone').val() != '' && !subPhone) {
                    $('.user-phone').find('.error').removeClass('none');
                    $('.user-phone').find('.error-text').text('请输入正确格式的手机号码！')
                }

                if ($('.userPhone').val() == '') {
                    $('.user-phone').find('.error').removeClass('none');
                    $('.user-phone').find('.error-text').text('手机号码不能为空！')
                }
            }
        });

        $('.dialing').on('click', function () {
            $('.reservation-pop').addClass('none');
        });

        var text = $("tilePlotDesc").find("p").text();
        if (text.indexOf(",") == 0) {
            var s = text.substring(1);
            $("tilePlotDesc").find("p").html(s);
        }

        if(window.location.href.indexOf('cpcesfTOP10_detail') > -1){
            var logger = new window.Tracker('${trackingHost}','${trackingProject}','${trackingLogstore}');
            logger.push('houseId', '${houseDetail.houseId}');
            logger.push('__topic__', 'esfTop10');
            logger.logger();
        }
    });
    function desc(url) {
        if(url.indexOf('esf')>-1){
            return '二手房-进入二手房详情页'
        }
        if(url.indexOf('/ad')>-1){
            return '广告-进入二手房详情页'
        }
        if(url.indexOf(window.location.hostname)>-1){
            return '大首页-点击二手房推荐'
        }
    }
    zhuge.track(desc(document.referrer), {
        '区域' : '<#if houseDetail.area?exists&& houseDetail.area!=''>${houseDetail.area}</#if>',
        '商圈' : '<#if houseDetail.houseBusinessName?exists&& houseDetail.houseBusinessName!=''>${houseDetail.houseBusinessName}</#if>',
        '小区名称' : '<#if houseDetail.plotName?exists&& houseDetail.plotName!=''>${houseDetail.plotName}</#if>',
        '总价' : '<#if houseDetail.houseTotalPrices?exists&&(houseDetail.houseTotalPrices!=0)>${houseDetail.houseTotalPrices}</#if>'+'万',
        '面积' : '<#if houseDetail.buildArea?exists&& houseDetail.buildArea!=0>${houseDetail.buildArea}'+"㎡"</#if>,
        '户型' : '<#if houseDetail.room?exists>${houseDetail.room}室</#if><#if houseDetail.hall?exists>${houseDetail.hall}厅</#if>',
        '经济公司' : '<#if houseDetail.ofCompany?exists&& houseDetail.ofCompany!=''>${houseDetail.ofCompany}</#if>',
        '经济人' : '<#if houseDetail.houseProxyName?exists&& houseDetail.houseProxyName!=''>${houseDetail.houseProxyName}</#if>',
        '经济人电话' : '<#if houseDetail.houseProxyPhone?exists&& houseDetail.houseProxyPhone!=''>${houseDetail.houseProxyPhone}</#if>',
        'ID' : '<#if houseDetail.houseId?exists>${houseDetail.houseId}</#if>'
    });

    zhuge.track('二手房详情页点击量', {
        '区域' : '<#if houseDetail.area?exists&& houseDetail.area!=''>${houseDetail.area}</#if>',
        '商圈' : '<#if houseDetail.houseBusinessName?exists&& houseDetail.houseBusinessName!=''>${houseDetail.houseBusinessName}</#if>',
        '小区名称' : '<#if houseDetail.plotName?exists&& houseDetail.plotName!=''>${houseDetail.plotName}</#if>',
        '总价' : '<#if houseDetail.houseTotalPrices?exists&&(houseDetail.houseTotalPrices!=0)>${houseDetail.houseTotalPrices}</#if>'+'万',
        '面积' : '<#if houseDetail.buildArea?exists&& houseDetail.buildArea!=0>${houseDetail.buildArea}'+"㎡"</#if>,
        '户型' : '<#if houseDetail.room?exists>${houseDetail.room}室</#if><#if houseDetail.hall?exists>${houseDetail.hall}厅</#if>',
        '经济公司' : '<#if houseDetail.ofCompany?exists&& houseDetail.ofCompany!=''>${houseDetail.ofCompany}</#if>',
        '经济人' : '<#if houseDetail.houseProxyName?exists&& houseDetail.houseProxyName!=''>${houseDetail.houseProxyName}</#if>',
        '经济人电话' : '<#if houseDetail.houseProxyPhone?exists&& houseDetail.houseProxyPhone!=''>${houseDetail.houseProxyPhone}</#if>',
        'ID' : '<#if houseDetail.houseId?exists>${houseDetail.houseId}</#if>'
    });

    <#if houseDetail?exists>
        <#if houseDetail['claimHouseId']?exists && houseDetail['claimHouseId']!=''>
        zhuge.track('出售房源认领统计', {
            '区域' : '<#if houseDetail.area?exists&& houseDetail.area!=''>${houseDetail.area}</#if>',
            '商圈' : '<#if houseDetail.houseBusinessName?exists&& houseDetail.houseBusinessName!=''>${houseDetail.houseBusinessName}</#if>',
            '小区名称' : '<#if houseDetail.plotName?exists&& houseDetail.plotName!=''>${houseDetail.plotName}</#if>',
            '总价' : '<#if houseDetail.houseTotalPrices?exists&&(houseDetail.houseTotalPrices!=0)>${houseDetail.houseTotalPrices}</#if>'+'万',
            '面积' : '<#if houseDetail.buildArea?exists&& houseDetail.buildArea!=0>${houseDetail.buildArea}'+"㎡"</#if>,
            '户型' : '<#if houseDetail.room?exists>${houseDetail.room}室</#if><#if houseDetail.hall?exists>${houseDetail.hall}厅</#if>',
            '经济公司' : '<#if houseDetail.ofCompany?exists&& houseDetail.ofCompany!=''>${houseDetail.ofCompany}</#if>',
            '经济人' : '<#if houseDetail.houseProxyName?exists&& houseDetail.houseProxyName!=''>${houseDetail.houseProxyName}</#if>',
            '经济人电话' : '<#if houseDetail.houseProxyPhone?exists&& houseDetail.houseProxyPhone!=''>${houseDetail.houseProxyPhone}</#if>',
            'ID' : '<#if houseDetail.houseId?exists>${houseDetail.houseId}</#if>'
        });
        </#if>
    </#if>

    $(".describe-header").on('click', 'a', function () {
        _taq.push({convert_id:'1598714777660436', event_type:'phone'});
        var link = $(this);
        zhuge.track('二手房-点击拨打电话', {
            "经济人" : '<#if houseDetail.houseProxyName?exists&& houseDetail.houseProxyName!=''>${houseDetail.houseProxyName}</#if>',
            "经纪人电话": '<#if houseDetail.houseProxyPhone?exists&& houseDetail.houseProxyPhone!="">${houseDetail.houseProxyPhone}</#if>',
            "位置": "经纪人描述旁"
        }, function () {
            location.href = link.attr('href');
        });
        return false;
    });

    $(".detail-contact-content").on('click', '.contact-telephone-counseling', function () {
        _taq.push({convert_id:'1598714777660436', event_type:'phone'});
        var link = $(this);
        zhuge.track('二手房-点击拨打电话', {
            "经济人" : '<#if houseDetail.houseProxyName?exists&& houseDetail.houseProxyName!=''>${houseDetail.houseProxyName}</#if>',
            "经纪人电话": '<#if houseDetail.houseProxyPhone?exists&& houseDetail.houseProxyPhone!="">${houseDetail.houseProxyPhone}</#if>',
            "位置": "底部"
        }, function () {
            location.href = link.attr('href');
        });
        return false;
    });
    $(".dialing").on('click', function () {
        _taq.push({convert_id:'1598714777660436', event_type:'phone'});
        var link = $(this);
        zhuge.track('二手房-点击拨打电话', {
            "经济人" : '<#if houseDetail.houseProxyName?exists&& houseDetail.houseProxyName!=''>${houseDetail.houseProxyName}</#if>',
            "经纪人电话": '<#if houseDetail.houseProxyPhone?exists&& houseDetail.houseProxyPhone!="">${houseDetail.houseProxyPhone}</#if>',
            "位置": "预约咨询"
        }, function () {
            location.href = link.attr('href');
        });
        return false;
    });
    $("#nearbynewesf").on('click', 'li', function () {
        var link = $(this);
        zhuge.track('二手房-看过本房的用户正在看', {
            "户型":link.find('.tilelist-content').find('em').text().split("万/")[1].split("㎡/")[1],
            "面积":link.find('.tilelist-content').find('em').text().split("万/")[1].split("㎡/")[0]+"㎡",
            "价格":link.find('.tilelist-content').find('em').text().split("万/")[0]+"万",
            "距离":link.find('.bottom-text').text(),
            "页面位置序号":link.index()+1
        }, function () {
            location.href = link.find('a').attr('href');
        });
        return false;
    });
    /*$("#nearbypLOT").on('click', 'li', function () {
        var link = $(this);
        zhuge.track('二手房-点击查看推荐小区', {
            "小区名称":link.find('.tilelist-content').find('.cont-first').text(),
            "价格":link.find('.tilelist-content').find('.cont-last').text(),
            "页面位置序号":link.index()+1
        }, function () {
            location.href = link.find('a').attr('href');
        });
        return false;
    })*/
    function plotDetailInfo_1(a) {
        var link = $(a);
//        zhuge.track('二手房-点击查看小区详情', {
//            "二手房-点击查看小区详情": link.attr('href')
//        }, function () {
//            location.href = link.attr('href');
//        });
        location.href = link.attr('href');
        return false;
    }
    function plotDetailInfo_2(a) {
        var link = $(a);
//        zhuge.track('二手房-点击查看小区详情', {
//            "二手房-点击查看小区详情": link.attr('href')
//        }, function () {
//            location.href = link.attr('href');
//        });
        location.href = link.attr('href');
        return false;
    }
    function esf_map_1(a) {
        var link = $(a);
//        zhuge.track('二手房-点击配套地图', {
//            "二手房配套地图来源": link.attr('href')
//        }, function () {
//            location.href = link.attr('href');
//        });
        location.href = link.attr('href');
        return false;
    }
    function esf_map_2(a) {
        var link = $(a);
//        zhuge.track('二手房-点击配套地图', {
//            "二手房配套地图来源": link.attr('href')
//        }, function () {
//            location.href = link.attr('href');
//        });
        location.href = link.attr('href');
        return false;
    }
    $("#traffic_info .primary-distance").on('click','a',function () {
        var link = $(this);
        zhuge.track('二手房-点击查看交通详情', {
            "二手房-点击查看交通详情":link.attr('href')
        }, function () {
            location.href = link.attr('href');
        });
        return false;
    });

    <#--var page = ${pageNum}-->
    <#--var houseId = ${houseId}-->
    <#--function SetPageNumParam(url) {-->
    <#--url = url || '';-->
    <#--var pre = url.split('?');-->
    <#--var path = pre[0];-->
    <#--var params = pre.length > 1 ? pre[1].split('&') : [];-->
    <#--var match = false;-->
    <#--for (var i = 0; i < params.length; i++) {-->
    <#--if (params[i].indexOf('pageNum=') == 0) {-->
    <#--params[i] = 'pageNum=' + page;-->
    <#--match = true;-->
    <#--break;-->
    <#--}-->
    <#--}-->
    <#--if (!match) {-->
    <#--params.push('pageNum=' + page)-->
    <#--}-->
    <#--return path + '?' + params.join('&');-->

    <#--}-->
    <#--var href = document.referrer || '/bj/esf';-->
    <#--if (${reffer}>-->
    <#--0-->
    <#--)-->
    <#--{-->
    <#--$(function () {-->
    <#--var index = 0;-->
    <#--var historyLength = window.history.length;-->
    <#--var initHash = window.location.hash;-->
    <#--var hashtimer = setInterval(function () {-->
    <#--initHash = window.location.hash;-->
    <#--var newurl = window.location.href.replace(/#.*/, '');-->
    <#--newurl = newurl + '#hackdetail' + index;-->
    <#--window.location.href = newurl;-->
    <#--if (historyLength < window.history.length) {-->
    <#--clearInterval(hashtimer);-->
    <#--var timer = setInterval(function () {-->
    <#--if (initHash == window.location.hash) {-->
    <#--window.location.replace(SetPageNumParam(href));-->
    <#--clearInterval(timer);-->
    <#--}-->
    <#--}, 50)-->
    <#--}-->
    <#--index = index + 1;-->
    <#--}, 100);-->
    <#--});-->
    <#--}-->

    //            $(document).ready(function (e) {
    //                if (window.history && window.history.pushState) {
    //                    $(window).on('popstate', function () {
    //                        var href = document.referrer
    //                        if(href.indexOf('/esf')>0){
    //                            if(href.split('&').length>1&&href.split('&')[href.split('&').length-1].split('=')[0]=='pageNum'){
    //                                var len = href.split('&')[href.split('&').length-1].length
    //                                var lianjie = href.substring(0,(parseInt(href.length)-parseInt(len)))
    //                                window.location.href=lianjie+'pageNum='+page;
    //                            }else if (href.split('&').length>1){
    //                                window.location.href=href+'&pageNum='+page;
    //                            }else if (href.split('?').length>1&&href.split('?')[href.split('?').length-1].split('=')[0]=='pageNum' && href.indexOf('&')==-1){
    //                                var len = href.split('?')[href.split('?').length-1].length
    //                                var lianjie = href.substring(0,(parseInt(href.length)-parseInt(len)))
    //                                window.location.href=lianjie+'pageNum='+page;
    //                            }
    //                        }
    //                    });
    //                }
    //                window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
    //                window.history.forward(1);
    //            });
</script>
</body>
</html>