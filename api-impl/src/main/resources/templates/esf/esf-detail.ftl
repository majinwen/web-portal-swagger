<!DOCTYPE html>
<html>
<head>
    <#include "../staticHeader.ftl">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/esf-detail.css">
    <title><#if houseDetail.plotName?exists&&houseDetail.plotName!=''>${houseDetail.plotName}</#if>  <#if houseDetail.buildArea?exists &&(houseDetail.buildArea!=0)>${houseDetail.buildArea}㎡</#if> <#if houseDetail.room?exists&&houseDetail.hall?exists>${houseDetail.room}室${houseDetail.hall}厅</#if></title>
    <meta name="description" content="我在头条房产发现一套 【<#if houseDetail.plotName?exists&&houseDetail.plotName!=''>${houseDetail.plotName}</#if>】【 <#if houseDetail.houseTotalPrices?exists&&(houseDetail.houseTotalPrices!=0)>${houseDetail.houseTotalPrices}</#if>】【<#if houseDetail.room?exists&&houseDetail.hall?exists>${houseDetail.room}室${houseDetail.hall}厅</#if>】的房子推荐给你">
    <meta name="keyword" content="">
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS"></script>
    <#include "../StatisticsHeader.ftl">
</head>
<body>
<img class="shareTopImg" height="0" width="0" src="<#if houseDetail['housePhoto']?? && (houseDetail['housePhoto']?size>0)>${houseDetail['housePhoto'][0]!""}-tt1200x640</#if>" alt="">
<div class="carousel-box">
    <div class="swiper-container carousel-swiper" id="detail-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
        <#if houseDetail['housePhoto']?? && (houseDetail['housePhoto']?size>0)>
            <#list houseDetail['housePhoto'] as itemValue>
                <li onclick="initphoto(this,${itemValue_index})" class="swiper-slide">
                    <img src="${itemValue}" data-src="${itemValue}" alt="">
                </li>
            </#list>
        <#else >
            <li onclick="initphoto(this,0)" class="swiper-slide">
                <img src="${staticurl}/images/global/tpzw_banner_image.png" data-src="${staticurl}/images/global/tpzw_banner_image.png" alt="拍摄中">
            </li>
        </#if>
        </ul>
        <div class="banner-title">
            <div class="banner-house-number">房源编号：${houseDetail.houseId}</div>
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
            <h2><#if houseDetail.houseTitle?exists>${houseDetail.houseTitle}</#if></h2>
            <div class="primary-header-tag house-labelling gray">
            <#assign item =houseDetail['tagsName']>
            <#list item as itemValue>
                <#if itemValue?exists>
                    <span>${itemValue}</span>
                </#if>
            </#list>
            </div>
        </div>
        <ul class="primary-item">
            <li>
                <ol>
                    <li>
                        <span>总价</span>
                        <em>
                        <#if houseDetail.houseTotalPrices?exists&&(houseDetail.houseTotalPrices!=0)>
                        ${houseDetail.houseTotalPrices?number?round}万
                        <#else>
                            暂无数据
                        </#if>
                        </em>
                    </li>
                    <li>
                        <span>户型</span>
                        <em><#if houseDetail.room?exists&&houseDetail.hall?exists>
                        ${houseDetail.room}室${houseDetail.hall}厅
                        <#else>
                            暂无数据
                        </#if>
                        </em>
                    </li>
                    <li>
                        <span>面积</span>
                        <em>
                        <#if houseDetail.buildArea?exists &&(houseDetail.buildArea!=0)>
                        ${houseDetail.buildArea}㎡
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
                ${((houseDetail.houseTotalPrices / houseDetail.buildArea)?if_exists?number?round) * 10000}元/㎡
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
                    ${houseDetail.floor}楼层/${houseDetail.totalFloor}层
                    <#else >
                        <#if houseDetail.floor?exists&& (houseDetail.floor!='')>
                        ${houseDetail.floor}楼层
                        </#if >
                        <#if houseDetail.totalFloor?exists&&(houseDetail.totalFloor!=0)>
                        ${houseDetail.totalFloor}层
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
        <#if houseDetail.traffic?exists>
            <li>
                <p>
                    交通信息：距离${houseDetail.traffic?split("$")[0]}${houseDetail.traffic?split("$")[1]}${houseDetail.traffic?split("$")[2]}m
                    <em class="primary-distance"></em>
                    <a href="${router_city('/esf/'+houseDetail.newcode+'/map.html')}" class="primary-map-icon"></a>
                    <a href="${router_city('/esf/'+houseDetail.newcode+'/map.html')}" class="arrows-right"></a>
                </p>
            </li></#if>
        </ul>
    </section>
</div>
<#if houseDetail.houseProxyPhone?exists||houseDetail.houseProxyPhoto?exists||houseDetail.ofCompany?exists||houseDetail.houseDesc?exists>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>房源描述</h3>
        </div>
        <div class="describe-box">
            <div class="describe-header">
                <img class="source-icon" <#if houseDetail.houseProxyPhoto?exists>src="${houseDetail.houseProxyPhoto}" alt="" <#else >src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中"</#if>>
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
<div class="module-bottom-fill">
    <section>
        <#if houseDetail.newcode?exists>
            <div class="module-header-message">
                <h3>小区信息</h3>
                <a href="${router_city('/xiaoqu/'+houseDetail.newcode)+'.html'}" class="more-arrows">小区详情<i class="arrows-right"></i></a>
            </div>
        <ul class="tilelist row">
        <li><a href="${router_city('/xiaoqu/'+houseDetail.newcode+'.html')}">
        </#if>
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
                    参考均价<em class="high-light-red">${village['avgPrice']?number?round}元</em>/㎡
                </#if>
            </p>
        </div>
    </a></li>
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
                <a href="${router_city('/esf/'+houseDetail.newcode+'/map.html')}" class="more-arrows"><i class="arrows-right"></i></a>
            </div>
        </#if>
        <a href="${router_city('/esf/'+houseDetail.newcode+'/map.html')}" class="detail-map">
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
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>附近好房</h3>
        </div>
        <ul class="tilelist">
            <#list plot as map>
                <li>
                    <#if map.houseId?exists><a href="${router_city('/esf/'+map.houseId+'.html')}">
                    <#else><a href="#">
                    </#if>
                    <div class="picture-box">
                        <div class="picture-box">
                            <#if map['housePhotoTitle']?exists>
                                <#if map.housePhotoTitle??&& map.housePhotoTitle!=''>
                                    <img src="${map.housePhotoTitle}" alt="">
                                </#if>
                            <#else >
                                <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                            </#if>
                            <div class="bottom-text">
                                <#if map.housetToPlotDistance?exists>${map.housetToPlotDistance}</#if>
                            </div>
                        </div>
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first">
                            <em><#if map.houseTotalPrices?exists && map.houseTotalPrices!=0>${map.houseTotalPrices?number?round}万</em>/</#if><#if map.buildArea?exists&&(map.buildArea>0)>${map.buildArea}㎡</#if>/<#if map.room?exists&&map.hall?exists>${map.room}室</#if>
                        </p>
                        <h4 class="cont-last"><#if map.plotName?exists>${map.plotName}</#if></h4>
                    </div>
                </a></li>
            </#list>
        </ul>
    </section>
</div>
</#if>
<#if plotList?exists>
<section>
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
                        <p class="cont-last"><em>${plotInfo.avgPrice?number?round}元</em>/㎡</p>
                    </#if>
                </div>
            </a></li>
        </#list>
    </ul>
</section>
</#if>
<#if houseDetail.houseProxyPhone?exists>
<div class="detail-contact-wrapper">
    <section class="detail-contact-box" id="detailContactState">
        <div class="detail-contact-content">
            <a href="tel:${houseDetail.houseProxyPhone}" class="only contact-telephone-counseling">咨询经纪人</a>
        </div>
    </section>
</div>
</#if>
<!-------- photoswipe -------->
<script src="${staticurl}/js/photoswipe.min.js"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="${staticurl}/js/URI.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
<script>
    $(function(){
        var text = $("tilePlotDesc").find("p").text();
        if(text.indexOf(",")==0){
            var s = text.substring(1);
            $("tilePlotDesc").find("p").html(s);
        }
    })
</script>
</body>
</html>