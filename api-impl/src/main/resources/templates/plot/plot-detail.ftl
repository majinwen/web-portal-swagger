<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/plot-detail.css">
    <title>小区详情</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
</head>
<body>
<div class="carousel-box">
    <div class="swiper-container carousel-swiper" id="detail-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
        <#if village['photo']?exists>
            <#list village['photo'] as vpphoto>
                <#if vpphoto?exists>
                    <li onclick="initphoto(this,${vpphoto_index})" class="swiper-slide">
                        <img src="${qiniuimage}/${vpphoto}" data-src="${qiniuimage}/${vpphoto}" alt="">
                    </li>
                <#else>
                    <li onclick="initphoto(this,${vpphoto_index})" class="swiper-slide">
                        <img src="${qiniuimage}/" data-src="${qiniuimage}/" alt="暂无">
                    </li>
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
    <section class="plot-primary-header">
        <div class="plot-primary-text">
            <h2>${village['rc']!''}</h2>
            <p>[${village['area']!''}-${village['tradingArea']!''}] ${village['address']!''}</p>
        <#--<p><#if village['trafficInformation']?exists>${village['trafficInformation']}</#if></p>-->
            <p>${village['trafficInformation']!'暂无'}</p>
            <div class="house-labelling gray">
            <#if village['label']?exists>
                <#list village['label'] as label>
                    <#if label?exists><span>${label}</span><#else><span>暂无</span></#if>
                </#list>
            <#else><span>暂无</span>
            </#if>
            </div>
        </div>
        <a href="#" class="plot-primary-map-box"><img src="/static/images/plot/detail_static_map.png" alt="地图"></a>
    </section>
</div>
<#if (reViHouse?exists) && (reViHouse?size>0)>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>推荐小区好房</h3>
            <a href="/findProjHouseInfo/?newcode=${village['id']}" class="more-arrows">查看全部房源<i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist">
            <#list reViHouse as reitem>
                <#if reitem_index==4>
                    <#break >
                </#if>
                <#assign itemLocation=reitem['housePlotLocation']>
                <li><a href="/queryByHouseIdandLocation/${reitem.houseId}<#--/${itemLocation[0]}/${itemLocation[1]}-->">
                    <div class="picture-box">
                        <#if reitem['housePhoto']?exists>
                        <#assign photoitem=reitem['housePhoto']>
                        <img src="${qiniuimage}/${photoitem[0]}" alt=">${reitem['houseTitle']}">
                        <p class="bottom-text">${reitem['houseArea']}㎡</p>
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first text-center"><em>
                         <#if reitem['houseTotalPrices']?exists>${reitem.houseTotalPrices+'万/'}</#if></em>
                         <#if reitem['houseOrientation']?exists>${reitem.houseOrientation+'/'}</#if>
                         <#if reitem['houseType']?exists>${reitem.houseType+'室'}</#if></p>
                    </div>
                </a></li>
            </#list>


        <#--    <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>
            <li><a href="#">
                <div class="picture-box">
                    <img src="${staticurl}/images/esf/esxq_xq_image2@3x.png" alt="首城国际">
                    <p class="bottom-text">262㎡</p>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first text-center"><em>1800万</em>/南/5室</p>
                </div>
            </a></li>-->
        </ul>
    </section>
</div>
</#if>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>市场行情<span class="subtitle">价格走势</span></h3>
            <div class="markets-btn"><i class="price-trend-btn current"></i><i class="supply-contrast-btn"></i></div>
        </div>
        <div class="basic-information price-trend">
            <div class="column item-column-three">
                <div class="info-card-item">
                    <em>均价</em>
                    <p><#if village['avgPrice']?exists>${village['avgPrice']}元/㎡<#else>暂无</#if></p>
                </div>
                <div class="info-card-item">
                    <em>环比上月</em>
                    <p class="green">↓ 6.68%</p>
                </div>
                <div class="info-card-item">
                    <em>同比去年</em>
                    <p class="green">↓ 3.46%</p>
                </div>
            </div>
            <div></div>
        </div>
        <div class="basic-information supply-contrast none">
            <div class="column item-column-two">
                <div class="info-card-item">
                    <em>本月</em>
                    <table>
                        <tr>
                            <td>挂牌出售：</td>
                            <td>34套</td>
                        </tr>
                        <tr>
                            <td>关注用户：</td>
                            <td>230人</td>
                        </tr>
                    </table>
                    <p>供需充足，可选余地大。</p>
                </div>
                <div class="info-card-item">
                    <em>上月</em>
                    <table>
                        <tr>
                            <td>挂牌出售：</td>
                            <td>34套</td>
                        </tr>
                        <tr>
                            <td>成交：</td>
                            <td>23套</td>
                        </tr>
                        <tr>
                            <td>存量：</td>
                            <td>36.57%</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div></div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>基本信息</h3>
            <a href="/plotParameter?id=${village['id']}" class="more-arrows">查看更多<i class="arrows-right"></i></a>
        </div>
        <div class="basic-information">
            <div class="column item-only-one">
                <div class="info-card-item">
                    <#if village['rc']?exists>${village['rc']}</#if>
                    <#if village['abbreviatedAge']?exists>，<em class="high-light-red">${village['abbreviatedAge']}</em>年建成住宅，</#if>
                    <#if village['sumBuilding']?exists>共<em class="high-light-red">${village['sumBuilding']}</em>栋</#if>
                    <#if village['sumHousehold']?exists>
                        <#if village['sumHousehold']?number gt 0>
                            （${village['sumHousehold']}户）
                        </#if>
                        <#if village['buildingStructure']?exists>
                            ，${village['buildingStructure']}
                        </#if>
                    </#if>
                </div>
            </div>
            <div class="column item-column-two">
                <div class="info-card-item">
                    <i class="item-two-1"></i>
                    <div class="info-item-text">
                        <p>人均绿化</p>
                    <#if village['carPositionRatio']?number gt 0>
                        <em>${village['avgGreeningRate']}平方米</em>
                    <#else >
                        <em>暂无</em>
                    </#if>
                    </div>
                </div>
                <div class="info-card-item">
                    <i class="item-two-2"></i>
                    <div class="info-item-text">
                        <p>车位配比</p>
                    <#if village['carPositionRatio']?number gt 0>
                        <em>${village['carPositionRatio']}车位/户</em>
                    <#else >
                        <em>暂无</em>
                    </#if>
                    </div>
                </div>
            </div>
            <div class="column item-column-two">
                <div class="info-card-item">
                    <i class="item-two-3"></i>
                    <div class="info-item-text">
                        <p>户均电梯</p>
                        <em>${village['liftDoorRadio']!'暂无'}</em>
                    </div>
                </div>
                <div class="info-card-item">
                    <i class="item-two-4"></i>
                    <div class="info-item-text">
                        <p>空气质量</p>
                        <em>${village['airQuality']!'暂无'}</em>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>交通信息</h3>
        </div>
        <div class="basic-information">
            <div class="column item-column-three">
            <#if village['metroWithPlotsDistance']?exists>
                    <div class="info-card-item">
                        <i class="item-three-1"></i>
                        <em>公交</em>
                        <p id="busStation">暂无</p>
                        <span id="busStationNumber">暂无</span>
                    </div>
                    <div class="info-card-item">
                        <i class="item-three-2"></i>
                        <em>地铁</em>
                        <p id="subwayLine">暂无</p>
                        <span id="subwayDistance">暂无</span>
                    </div>
            </#if>
                <div class="info-card-item">
                    <i class="item-three-3"></i>
                    <em>自驾</em>
                    <p>${village['ringRoadName']!'暂无'}</p>
                    <span>${village['ringRoadDistance']!'暂无'}</span>
                </div>
            </div>
        </div>
    </section>
</div>


<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>教育配套<span class="subtitle">看你发芽，陪你长大</span></h3>
        </div>
        <div class="expand-content content-visible">
            <div class="map-education-box">
                <ul class="map-message-btn clear" data-type="教育配套">
                    <li class="parent-child"><i></i><span>亲子</span></li>
                    <li class="kindergarten"><i></i><span>幼儿园</span></li>
                    <li class="primary-school"><i></i><span>小学</span></li>
                    <li class="middle-school"><i></i><span>中学</span></li>
                    <li class="university"><i></i><span>大学</span></li>
                </ul>
            </div>
            <ul class="result-data-expand" id="educationListDom"></ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>休闲购物<span class="subtitle">新世界丽樽生活圈</span></h3>
        </div>
        <div class="expand-content content-visible">
            <div class="map-shopping-box">
                <ul class="map-message-btn" data-type="休闲购物">
                    <li class="vegetable-market"><span>菜市场</span><i></i></li>
                    <li class="supermarket"><span>超市</span><i></i></li>
                    <li class="shopping-mall"><span>商场</span><i></i></li>
                    <li class="dining-room"><span>餐厅</span><i></i></li>
                    <li class="fitness"><span>健身</span><i></i></li>
                </ul>
                <img src="${staticurl}/images/plot/xqxq_xxgw_tu@3x.png" width="100%" alt="">
            </div>
            <ul class="result-data-expand height-type" id="shoppintListDom"></ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>医疗配套</h3>
            <a href="javascript:;" class="more-arrows expand-btn"><em>展开</em><i class="arrows-expand"></i></a>
        </div>
        <div class="expand-content">
            <ul class="result-data-expand" id="hospitalListDom">
            </ul>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>生活成本</h3>
            <a href="javascript:;" class="more-arrows expand-btn"><em>展开</em><i class="arrows-expand"></i></a>
        </div>
        <div class="expand-content">
            <ul class="result-data-expand">
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">水费</span>
                    <#--<#if village['waterFee']?exists&&village['waterFee']?number gt 0>-->
                    <#if village['waterFee']?exists>
                        <span class="expand-price">${village['waterFee']}元/吨</span>
                    <#else >
                        <span class="expand-price">暂无</span>
                    </#if>
                    </p>
                    <span class="expand-distance tips">居民用水价格范围为1-4元/吨</span>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">电费</span>
                    <#--<#if village['parkingRate']?exists&&village['parkingRate']?number gt 0>-->
                    <#if village['electricFee']?exists>
                        <span class="expand-price">${village['electricFee']}元/度</span>
                    <#else >
                        <span class="expand-price">暂无</span>
                    </#if>
                    </p>
                    <span class="expand-distance tips">居民用电价格范围为1-4元/度</span>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">物业费</span>
                    <#--<#if village['parkingRate']?exists&&village['parkingRate']?number gt 0>-->
                    <#if village['electricFee']?exists>
                        <span class="expand-price">${village['electricFee']}元/㎡·月</span>
                    <#else >
                        <span class="expand-price">暂无</span>
                    </#if>
                    </p>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">停车费</span>
                    <#--<#if village['parkingRate']?exists&&village['parkingRate']?number gt 0>-->
                    <#if village['parkingRate']?exists>
                        <span class="expand-price">${village['parkingRate']}元/月</span>
                    <#else >
                        <span class="expand-price">暂无</span>
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
            <a href="/getProjHouseMapDetail?newcode=${village['id']}" class="more-arrows">配套详情<i class="arrows-right"></i></a>
        </div>
        <a href="#" class="detail-map">
            <i class="map-marker-icon"></i>
            <#if  village['location']?exists>
                <#assign locationIp = village['location'] ? split(",")>
                <img src="http://api.map.baidu.com/staticimage/v2?ak=57b4dbd0d142e9649ed54160b45ecb1f&width=700&height=350&center=${locationIp[1]},${locationIp[0]}&&zoom=16" alt="">
              <#else>
                  <img src="http://api.map.baidu.com/staticimage/v2?ak=57b4dbd0d142e9649ed54160b45ecb1f&width=700&height=350&center=116.382001,39.913329&&zoom=16" alt="">
            </#if>
        </a>
    </section>
</div>
<#if reViHouse?exists&&reViHouse?size gt 0>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>待售房源</h3>
            <a href="/findProjHouseInfo/?newcode=${village['id']}" class="more-arrows">查看全部待售<i class="arrows-right"></i></a>
        </div>
    </section>
</div>
</#if>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>看了本楼盘的用户还看了</h3>
        </div>
        <ul class="tilelist">
            <#list nearvillage as nearviitem>
            <#if nearviitem_index == 4>
                <#break>
            </#if>
            <li><a href="/villageDetail?id=${nearviitem['id']?c}">
                <div class="picture-box">
                    <#assign photos = nearviitem['photo']>
                    <#if photos[0]?exists>
                        <img src="${qiniuimage}/${photos[0]}" alt="${nearviitem['rc']}">
                    </#if>

                </div>
                <div class="tilelist-content">
                    <p class="cont-first">${nearviitem['rc']}</p>
                    <p class="cont-center"><span><#if nearviitem['area']?exists>${nearviitem['area']}</#if></span><span>${nearviitem['address']}</span></p>
                    <h4 class="cont-last">均价：<em>${nearviitem['avgPrice']}</em>/㎡</h4>
                </div>
            </a></li>
            </#list>
        </ul>
    </section>
</div>
<script>
    var locationnumber = '${village['location']}';
    var mapBaiduNumber = locationnumber.split(",").indexOf(1)+locationnumber.split(",").indexOf(0)
</script>
<section>
    <div class="module-header-message">
        <h3>新房推荐</h3>
    </div>
    <ul class="tilelist">
    <#list newbuilds as builditem>
        <li><a href="/newhouse/getNewHouseDetails?id=${builditem['building_name_id']!''}">
            <div class="picture-box">
                <#assign imglist = builditem['building_imgs']>
                <#if imglist?exists>
                <img src="${qiniuimage}/${imglist?split(",")[0]!''}" alt="${imglist?split(",")[0]!''}">
                </#if>
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">${builditem['building_name']!''}</h4>
                <#if builditem['average_price']?exists>
                    <#if builditem['average_price']?number gt 0>
                        <p class="cont-last">均价：<em>${builditem['average_price']}元</em>/㎡</p>
                    <#else >
                        <p class="cont-last">均价：<em>暂无</em></p>
                    </#if>
                <#else >
                    <p class="cont-last">均价：<em>暂无</em></p>
                </#if>
            </div>
        </a></li>
    </#list>
    </ul>
</section>
<div class="detail-contact-wrapper">
    <section class="detail-contact-box" id="detailContactState">
        <div class="detail-contact-content">
            <#--<a href="#" class="contact-share"><i></i>分享</a>
            <a href="#" class="contact-collect"><i></i>收藏</a>-->
            <a href="tel:1234789" class="only contact-telephone-counseling">咨询售楼处</a>
        </div>
    </section>
</div>

<!-------- photoswipe -------->

<script src="${staticurl}/js/photoswipe.min.js"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
<script src="${staticurl}/js/plot-detail-map-message.js"></script>
</body>
</html>