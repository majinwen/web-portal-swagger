<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/esf-detail.css">
    <title>二手房详情</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
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
            <h2><#if houseDetail.houseTitle?exists>${houseDetail.houseTitle}<#else>暂无</#if></h2>
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
                        ${houseDetail.houseTotalPrices}万
                        <#else>
                            暂无
                        </#if>
                        </em>
                    </li>
                    <li>
                        <span>户型</span>
                        <em><#if houseDetail.room?exists&&houseDetail.hall?exists>

                        ${houseDetail.room}室${houseDetail.hall}厅
                        <#else>
                            暂无
                        </#if>
                        </em>
                    </li>
                    <li>
                        <span>面积</span>
                        <em>
                        <#if houseDetail.buildArea?exists &&(houseDetail.buildArea!=0)>
                        ${houseDetail.buildArea}㎡
                        <#else>
                            暂无
                        </#if>
                        </em>
                    </li>
                </ol>
            </li>
            <li>
                <p>单价：
                <#if houseDetail.houseTotalPrices?exists&&houseDetail.buildArea?exists
                          &&houseDetail.houseTotalPrices?number gt 0&&houseDetail.buildArea?number gt 0>
                   ${(houseDetail.houseTotalPrices / houseDetail.buildArea)?number *10000}元/㎡
                <#else>
                    暂无
                </#if>
                </p>
            </li>
            <li>
                <p>预算：
                <#if houseDetail.houseBudget?exists>
                ${houseDetail.houseBudget}元/㎡
                <#else>
                    暂无
                </#if>
                </p>
            </li>
            <li>
                <dl class="module-table-item">
                    <dd class="odd-item">楼层：<span>
                    <#if (houseDetail.floor?exists&& (houseDetail.floor!=''))&& (houseDetail.floorNo?exists&&(houseDetail.floorNo!=0))>
                    ${houseDetail.floor}楼层/${houseDetail.floorNo}层
                    <#else >
                        <#if houseDetail.floor?exists&& (houseDetail.floor!='')>
                        ${houseDetail.floor}楼层
                        </#if >
                        <#if houseDetail.floorNo?exists&&(houseDetail.floorNo!=0)>
                        ${houseDetail.floorNo}层
                        </#if >
                        <#if (houseDetail.floorNo??&&houseDetail.floorNo==0)&&(houseDetail.floor??&&houseDetail.floor=='')>
                            暂无
                        </#if >
                    </#if>
                    </span></dd>
                    <dd class="even-item">电梯：<em><#if houseDetail.elevatorName?exists>${houseDetail.elevatorName}
                        电梯<#else>
                        暂无</#if></em></dd>
                    <dd class="odd-item">类别：
                    <#if houseDetail.houseTypeName?exists&& (houseDetail.houseTypeName !='')>
                        <em>${houseDetail.houseTypeName}</em>
                    <#else>
                        <#if houseDetail.buildCategoryName?exists && (houseDetail.buildCategoryName!='') >
                            <em>${houseDetail.buildCategoryName}</em>
                        <#else>
                            暂无
                        </#if>
                    </#if>
                    </dd>
                    <dd class="even-item">建成年代：<em><#if houseDetail.year?exists>${houseDetail.year}年<#else>暂无</#if></em>
                    </dd>
                    <dt>小区：<em><#if houseDetail.plotName?exists>${houseDetail.plotName}<#else>暂无</#if>
                    <#if houseDetail.area?exists&&houseDetail.houseBusinessName?exists> [${houseDetail.area}
                        -${houseDetail.houseBusinessName}]<#else>暂无</#if></em></dt>
                    <dt>更新时间：<#if houseDetail.updateTime?exists>${houseDetail.updateTime}<#else>暂无</#if></dt>
                </dl>
            </li>
        <#if houseDetail.traffic?exists>
            <li>
                <p>
                    交通信息：${houseDetail.traffic}
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
                <img class="source-icon" <#if houseDetail.houseProxyPhoto?exists>src="${houseDetail.houseProxyPhoto}" alt="" <#else >src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中"</#if> >
                <p>
                    <span><#if houseDetail.ofCompany?exists>【${houseDetail.ofCompany}】<#else>
                        暂无</#if><#if houseDetail.houseProxyName?exists>${houseDetail.houseProxyName}<#else>
                        暂无</#if></span>
                    <em>房屋信息发布人</em>
                </p>
                <a href="tel:<#if houseDetail.houseProxyPhone?exists>${houseDetail.houseProxyPhone}<#else>#</#if>"
                   class="issuer-tel-icon"></a>
            </div>
            <div class="describe-cont">
                <p><#if houseDetail.houseDesc?exists>${houseDetail.houseDesc}<#else>暂无</#if></p>
                <span class="describe-show-btn">>>展开</span>
            </div>
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
            <#if item[0]?exists><img src="${qiniuimage}/${item[0]}" alt="${houseDetail.plotName}"><#else ><img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中"></#if>
        </div>
        <div class="tilelist-content">
            <h4><#if houseDetail.plotName?exists>${houseDetail.plotName}<#else></#if></h4>
            <p><#if houseDetail.plotdesc?exists>${houseDetail.plotdesc}<#else></#if></p>
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
                <a href="${router_city('/esf/'+houseDetail.newcode+'/map.html')}" class="more-arrows"><i
                        class="arrows-right"></i></a>
            </div>
        </#if>
        <a href="${router_city('/esf/'+houseDetail.newcode+'/map')}" class="detail-map">
            <i class="map-marker-icon"></i>
            <#if houseDetail.lat?exists&&houseDetail.lon?exists>
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=${houseDetail.lat?if_exists?string("####.#######################")},${houseDetail.lon?if_exists?string("####.#######################")}&&zoom=16"
                     alt="">
            <#else >
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=116.382001,39.913329&&zoom=16"
                     alt="">
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
                        <#if map['housePhotoTitle']?exists>
                            <#assign item=map['housePhotoTitle']>
                            <#if item?exists>
                                <img src="${item}" alt="">
                            </#if>
                        <#else >
                            <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                        </#if>
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first">
                            <em>
                                <#if map.houseTotalPrices?exists>
                                    <#if map.houseTotalPrices==0></#if>
                                <#else>${map.houseTotalPrices}万/
                                </#if>
                                <#if map.buildArea?exists&&(map.buildArea>0)>${map.buildArea}㎡/</#if>
                                <#if map.room?exists&&map.hall?exists>${map.room}室${map.hall}厅<#else></#if>
                            </em>
                        </p>
                        <h4 class="cont-last"><#if map.plotName?exists>${map.plotName}<#else></#if></h4>
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
            <li>
                <#if plotInfo['id']?exists>
                <a href="${router_city('/xiaoqu/'+plotInfo['id']+'.html')}">
                <#else >
                <a href="#">
                </#if>
                <div class="picture-box">
                    <#if plotInfo['photo']?exists>
                        <#assign plotImage=plotInfo['photo'] >
                        <#if plotImage[0]?exists><img src="${qiniuimage}/${plotImage[0]}" alt="">
                        <#else >
                            <img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
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
<script src="${staticurl}/js/main.js"></script>
</body>
</html>