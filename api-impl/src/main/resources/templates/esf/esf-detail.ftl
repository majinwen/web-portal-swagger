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
        <#assign item =houseDetail['housePhoto']>
        <#list item as itemValue>
            <#if itemValue?exists>
                <li onclick="initphoto(this,${itemValue_index})" class="swiper-slide">
                    <img src="${itemValue}" data-src="${itemValue}" alt="${itemValue_index}">
                </li>
            </#if>
        </#list>
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
                        <em><#if houseDetail.houseTotalPrices?exists>${houseDetail.houseTotalPrices}万<#else>
                            暂无</#if></em>
                    </li>
                    <li>
                        <span>户型</span>
                        <em><#if houseDetail.room?exists>${houseDetail.room}室<#else>
                            暂无</#if><#if houseDetail.hall?exists>${houseDetail.hall}厅<#else>暂无</#if></em>
                    </li>
                    <li>
                        <span>面积</span>
                        <em><#if houseDetail.buildArea?exists>${houseDetail.buildArea}㎡<#else>暂无</#if></em>
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
                    <dd class="odd-item">楼层：<span><#if houseDetail.floor?exists>${houseDetail.floor}<#else>暂无</#if>
                        /<#if houseDetail.floorNo?exists>${houseDetail.floorNo}层<#else>暂无</#if></span></dd>
                    <dd class="even-item">电梯：<em><#if houseDetail.elevator?exists>${houseDetail.elevator}<#else>
                        暂无</#if></em></dd>
                    <dd class="odd-item">类别：
                    <#if houseDetail.houseTypeName?exists>
                        <em>${houseDetail.houseTypeName}</em>
                    <#else>
                        <#if houseDetail.buildCategoryName?exists> <em>${houseDetail.buildCategoryName}</em><#else>
                            暂无
                        </#if>
                    </#if>
                    </dd>
                    <dd class="even-item">楼龄：<em><#if houseDetail.year?exists>${houseDetail.year}年<#else>暂无</#if></em>
                    </dd>
                    <dt>小区：<em><#if houseDetail.plotName?exists>${houseDetail.plotName}<#else>暂无</#if>
                    <#if houseDetail.area?exists&&houseDetail.houseBusinessName?exists> [${houseDetail.area}
                        -${houseDetail.houseBusinessName}]<#else>暂无</#if></em></dt>
                    <dt>更新时间：<#if houseDetail.updateTime?exists>${houseDetail.updateTime}<#else>暂无</#if></dt>
                </dl>
            </li>
            <li>
                <p>
                    交通信息：<#if houseDetail.traffic?exists>${houseDetail.traffic}<#else>暂无</#if><em
                        class="primary-distance"></em>
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
                <img class="source-icon"
                     src="<#if houseDetail.houseProxyPhoto?exists>${houseDetail.houseProxyPhoto}<#else>暂无</#if>"
                     alt="<#if houseDetail.houseProxyName?exists>${houseDetail.houseProxyName}<#else>暂无</#if>">
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
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>小区信息</h3>
            <a href="/villageDetail?id=${houseDetail.newcode}" class="more-arrows">小区详情<i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist row">
            <li>
                <div class="picture-box">
                <#assign item=houseDetail['plotPhoto']>
                <#if item[0]?exists><img src="${qiniuimage}/${item[0]}" alt="${houseDetail.plotName}"></#if>
                </div>
                <div class="tilelist-content">
                    <h4><#if houseDetail.plotName?exists>${houseDetail.plotName}<#else>暂无</#if></h4>
                    <p><#if houseDetail.plotdesc?exists>${houseDetail.plotdesc}<#else>暂无</#if></p>
                </div>
            </li>
        </ul>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>配套地图</h3>
            <a href="/getProjHouseMapDetail?houseId=${houseDetail.houseId}" class="more-arrows">配套详情<i class="arrows-right"></i></a>
        </div>
        <a href="#" class="detail-map">
            <i class="map-marker-icon"></i>
            <img src="http://api.map.baidu.com/staticimage/v2?ak=57b4dbd0d142e9649ed54160b45ecb1f&width=700&height=350&center=116.382001,39.913329&&zoom=16"
                 alt="">
        </a>
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

                    <#if map.houseId?exists>
                    <a href="/queryByHouseIdandLocation/${map.houseId}">
                    <#--<a class="list-item" href="/queryByHouseIdandLocation/${map.houseId}/${map.lon?if_exists?string(",####.####################")}/${map.lat?if_exists?string(",####.####################")}">-->
                    <#else>
                    <a href="#">
                    </#if>
                    <div class="picture-box">
                        <#if map['housePhoto']?exists>
                            <#assign item=map['housePhoto']>
                            <img src="<#if item[0]?exists>${item[0]}</#if>"
                                 alt="<#if map.houseTitle?exists>${map.houseTitle}</#if>" width="150" height="119">
                        </#if>
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first"><em><#if map.houseTotalPrices?exists>${map.houseTotalPrices}万<#else>
                            暂无</#if></em>|<#if map.buildArea?exists>${map.buildArea}㎡<#else>
                            暂无</#if>|<#if map.room?exists&&map.hall?exists>${map.room}室${map.hall}厅<#else>暂无</#if></p>
                        <h4 class="cont-last"><#if map.plotName?exists>${map.plotName}<#else>暂无</#if></h4>
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
                        <#if plotImage[0]?exists>
                              <img src="${qiniuimage}/${plotImage[0]}" alt="${plotInfo.rc}" width="150" height="119">
                        <#else >
                            <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="${plotInfo.rc}">
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
    </#if>
</ul>
</section>
<div class="detail-contact-wrapper">
    <section class="detail-contact-box" id="detailContactState">
        <div class="detail-contact-content">
            <a href="#" class="contact-share"><i></i>分享</a>
            <a href="#" class="contact-collect"><i></i>收藏</a>
            <a href="tel:1234789" class="contact-telephone-counseling">咨询售楼处</a>
        </div>
    </section>
</div>

<!-------- photoswipe -------->
<script src="${staticurl}/js/photoswipe.min.js"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
</body>
</html>