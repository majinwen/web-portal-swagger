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
    <div class="swiper-container carousel-swiper">
    <#assign item =houseDetail['housePhoto']>
    <#list item as itemValue>
    <#if itemValue?exists>
        <ul class="swiper-wrapper" id="house-pic-container">
            <li onclick="initphoto(this,${itemValue_index})" class="swiper-slide">
                <img src="${staticurl}/images/esf/${itemValue}" data-src="${staticurl}/images/esf/esxq_banner@3x.png" alt="${itemValue_index +1}">
            </li>
        </ul>
    </#if>
    </#list>
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
            <h2><#if houseDetail.houseRecommendInfo?exists>${houseDetail.houseRecommendInfo}<#else>暂无</#if></h2>
            <div class="primary-header-tag house-labelling gray">
            <#assign item =houseDetail['houseLabel']>
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
                        <em><#if houseDetail.houseTotalPrices?exists>${houseDetail.houseTotalPrices}万<#else>暂无</#if></em>
                    </li>
                    <li>
                        <span>户型</span>
                        <em><#if houseDetail.houseType?exists&&houseDetail.houseOrientation?exists>${houseDetail.houseType}/${houseDetail.houseOrientation}<#else>暂无</#if></em>
                    </li>
                    <li>
                        <span>面积</span>
                        <em><#if houseDetail.houseArea?exists>${houseDetail.houseArea}㎡<#else>暂无</#if></em>
                    </li>
                </ol>
            </li>
            <li>
                <p>单价：<#if houseDetail.houseUnitCost?exists>${houseDetail.houseUnitCost}元/㎡<#else>暂无</#if></p>
            </li>
            <li>
                <p>预算：<#if houseDetail.houseBudget?exists>${houseDetail.houseBudget}<#else>暂无</#if></p>
            </li>
            <li>
                <dl class="module-table-item">
                    <dd class="odd-item">楼层：<span><#if houseDetail.houseFloor?exists>${houseDetail.houseFloor}<#else>暂无</#if></span></dd>
                    <dd class="even-item">电梯：<em><#if houseDetail.houseLift?exists>${houseDetail.houseLift}<#else>暂无</#if></em></dd>
                    <dd class="odd-item">类别：
                    <#if houseDetail.housePurpose?exists>
                        <em>${houseDetail.housePurpose}</em>
                    <#else>
                    <#if houseDetail.houseBuildingType?exists> <em>${houseDetail.houseBuildingType}</em><#else>暂无</#if>
                    </#if>
                    </dd>
                    <dd class="even-item">楼龄：<em><#if houseDetail.houseYear?exists>${houseDetail.houseYear}年<#else>暂无</#if></em></dd>
                    <dt>小区：<em><#if houseDetail.housePlotName?exists>${houseDetail.housePlotName}<#else>暂无</#if>
                    <#if houseDetail.areaName?exists&&houseDetail.houseBusinessName?exists> [${houseDetail.areaName}-${houseDetail.houseBusinessName}]<#else>暂无</#if></em></dt>
                    <dt>更新时间：<#if houseDetail.houseUpdateTime?exists>${houseDetail.houseUpdateTime}<#else>暂无</#if></dt>
                </dl>
            </li>
            <li>
                <p>
                    交通信息：<#if houseDetail.houseTrafficInfo?exists>${houseDetail.houseTrafficInfo}<#else>暂无</#if><em class="primary-distance"></em>
                    <a href="#" class="primary-map-icon"></a>
                    <a href="#" class="arrows-right"></a>
                </p>
            </li>
        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>房源描述</h3>
        </div>
        <div class="describe-box">
            <div class="describe-header">
                <img class="source-icon" src="${staticurl}/images/esf/${houseDetail.houseProxyPhoto}" alt="${houseDetail.houseProxyName}">
                <p>
                    <span><#if houseDetail.houseManagementType?exists>【${houseDetail.houseManagementType}】</#if><#if houseDetail.houseProxyName?exists>${houseDetail.houseProxyName}</#if></span>
                    <em>房屋信息发布人</em>
                </p>
                <a href="tel:${houseDetail.houseProxyPhone}" class="issuer-tel-icon"></a>
            </div>
            <div class="describe-cont">
                <p><#if houseDetail.houseRecommend?exists>${houseDetail.houseRecommend}</#if></p>
                <span class="describe-show-btn">>>展开</span>
            </div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>小区信息</h3>
            <a href="#${houseDetail.housePlotId}" class="more-arrows">小区详情<i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist row">
            <li>
                <div class="picture-box">
                <#assign item=houseDetail['housePlotPhoto']>
                    <#if item[0]?exists><img src="${staticurl}/images/esf/${item[0]}" alt="${houseDetail.housePlotName}"></#if>
                </div>
                <div class="tilelist-content">
                    <h4><#if houseDetail.housePlotName?exists>${houseDetail.housePlotName}<#else>暂无</#if></h4>
                    <p><#if houseDetail.housePlotInfo?exists>${houseDetail.housePlotInfo}<#else>暂无</#if></p>
                </div>
                <#--<div class="tilelist-content">
                    <h4>${houseDetail.housePlotName}首城国际</h4>
                    <p>共18栋（2558户），板楼/板塔结合</p>
                    <p>参考均价<em>115982元</em>/㎡</p>
                </div>-->
            </li>
        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>配套地图</h3>
            <a href="#" class="more-arrows">配套详情<i class="arrows-right"></i></a>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>附近好房</h3>
        </div>
        <ul class="tilelist">
        <#if plot?exists>
            <#list plot as map>
                <li>
                    <#assign itemLocation=map['housePlotLocation']>
                    <a href="/queryByHouseIdandLocation/${map.houseId}/${itemLocation[0]}/${itemLocation[1]}">
                        <div class="picture-box">
                            <#assign item=map['housePhoto']>
                            <img src="${staticurl}/images/esf/${item[0]}" alt="${map.housePlotName}">
                        </div>
                        <div class="tilelist-content">
                            <p class="cont-first"><em>${map.houseTotalPrices}万</em>/${map.houseArea}㎡/${map.houseType}</p>
                            <h4 class="cont-last">${map.housePlotName}</h4>
                        </div>
                    </a>
                </li>
            </#list>
        </#if>
        </ul>
    </section>
</div>
<section>
    <div class="module-header-message">
        <h3>附近小区</h3>
    </div>
        <ul class="tilelist">
            <#if plotList?exists>
                <#list plotList as plotInfo>
                    <li><a href="/villageDetail?id=${plotInfo['id']}">
                        <div class="picture-box">
                            <#if plotInfo['photo']?exists>
                                <#assign plotImage=plotInfo['photo'] >
                                <#if plotImage[0]?exists><img src="${staticurl}/images/esf/${plotImage[0]}" alt="${plotInfo.rc}">
                                <#else><img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="${plotInfo.rc}">
                                </#if>
                                <#else >
                                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="${plotInfo.rc}">
                            </#if>
                        </div>
                        <div class="tilelist-content">
                            <#if plotInfo['desc']?exists>
                                <h4 class="cont-first">${plotInfo.desc}</h4>
                            </#if>
                            <#if plotInfo['avgPrice']?exists>
                                <p class="cont-last"><em>${plotInfo.avgPrice}元</em>/㎡</p>
                            </#if>
                        </div>
                    </a></li>
                </#list>
            </#if>
        </ul>
</section>
<section class="detail-contact-box" id="detailContactState">
    <div class="detail-contact-content">
        <a href="#" class="contact-share"><i></i>分享</a>
        <a href="#" class="contact-collect"><i></i>收藏</a>
        <a href="tel:1234789" class="contact-telephone-counseling">咨询售楼处</a>
    </div>
</section>

<!-------- photoswipe -------->
<script src="${staticurl}/js/photoswipe.min.js"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
</body>
</html>