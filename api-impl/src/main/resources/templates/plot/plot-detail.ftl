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
            <#list village['photo'] as vpphoto>
            <li onclick="initphoto(this,${vpphoto_index})" class="swiper-slide">
                <img src="${staticurl}/${vpphoto}" data-src="${staticurl}/${vpphoto}" alt="">
            </li>
            </#list>
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
            <h2>${village['rc']}</h2>
            <p>[顺义-商圈] 中央别墅区 顺语路57号</p>
            <p><#assign userMap = village['metroWithPlotsDistance']/>
            <#assign  keys=userMap?keys/>
            <#list keys as key>
            ${userMap[key]!''} ${key}
            </#list></p>
            <div class="house-labelling gray">
                <#list village['label'] as label>
                <span>${label}</span>
                </#list>
            </div>
        </div>
        <div class="plot-primary-map-box"></div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>推荐小区好房</h3>
            <a href="#" class="more-arrows">查看全部房源<i class="arrows-right"></i></a>
        </div>
        <ul class="tilelist">
            <#list reViHouse as reitem>
                  <#if reitem_index==4>
                      <#break >
                  </#if>
                <#assign itemLocation=reitem['housePlotLocation']>
                <li><a href="/queryByHouseIdandLocation/${reitem.houseId}/${itemLocation[0]}/${itemLocation[1]}">
                    <div class="picture-box">
                        <#assign photoitem=reitem['housePhoto']>
                        <img src="${staticurl}/${photoitem[0]}" alt=">${reitem['houseTitle']}">
                        <p class="bottom-text">${reitem['houseArea']}㎡</p>
                    </div>
                    <div class="tilelist-content">
                        <p class="cont-first text-center"><em>${reitem.houseTotalPrices}万</em>/${reitem.houseOrientation}/${reitem.houseType}室</p>
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
                    <p>${village['avgPrice']}元/㎡</p>
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
            <a href="#" class="more-arrows">查看更多<i class="arrows-right"></i></a>
        </div>
        <div class="basic-information">
            <div class="column item-only-one">
                <div class="info-card-item">${village['rc']}，<em class="high-light-red">${village['abbreviatedAge']}</em>年建成住宅，共<em class="high-light-red">${village['sumBuilding']}</em>栋（${village['sumHousehold']}户）<em class="high-light-red">${village['buildingStructure']}</em></div>
            </div>
            <div class="column item-column-two">
                <div class="info-card-item">
                    <i class="item-two-1"></i>
                    <div class="info-item-text">
                        <p>人均绿化</p>
                        <em>${village['avgGreeningRate']}平方米</em>
                    </div>
                </div>
                <div class="info-card-item">
                    <i class="item-two-2"></i>
                    <div class="info-item-text">
                        <p>车位配比</p>
                        <em>${village['carPositionRatio']}车位/户</em>
                    </div>
                </div>
            </div>
            <div class="column item-column-two">
                <div class="info-card-item">
                    <i class="item-two-3"></i>
                    <div class="info-item-text">
                        <p>户均电梯</p>
                        <em>暂无</em>
                    </div>
                </div>
                <div class="info-card-item">
                    <i class="item-two-4"></i>
                    <div class="info-item-text">
                        <p>空气质量</p>
                        <em>暂无</em>
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
                <div class="info-card-item">
                    <i class="item-three-1"></i>
                    <em>公交</em>
                    <p>四惠公交站</p>
                    <span>24条线路</span>
                </div>
                <div class="info-card-item">
                    <i class="item-three-2"></i>
                    <em>地铁</em>
                    <p>国贸站[1号线]</p>
                    <span>0.5km</span>
                </div>
                <div class="info-card-item">
                    <i class="item-three-3"></i>
                    <em>自驾</em>
                    <p>三环主路</p>
                    <span>0.7km</span>
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
                        <span class="expand-price">3元/吨</span>
                    </p>
                    <span class="expand-distance tips">居民用水价格范围为1-4元/吨</span>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">电费</span>
                        <span class="expand-price">3元/度</span>
                    </p>
                    <span class="expand-distance tips">居民用电价格范围为1-4元/度</span>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">物业费</span>
                        <span class="expand-price">4元/㎡·月</span>
                    </p>
                </li>
                <li>
                    <p>
                        <i class="expand-icon living-cost"></i>
                        <span class="expand-type">停车费</span>
                        <span class="expand-price">30元/月</span>
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
            <a href="#" class="more-arrows">配套详情<i class="arrows-right"></i></a>
        </div>
    </section>
</div>
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>待售房源</h3>
            <a href="#" class="more-arrows">查看全部待售<i class="arrows-right"></i></a>
        </div>
    </section>
</div>
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
            <li><a href="/villageDetail?id=${nearviitem['id']}">
                <div class="picture-box">
                    <#assign photos = nearviitem['photo']>
                    <img src="${staticurl}/${photos[0]}" alt="${nearviitem['rc']}">
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
<section>
    <div class="module-header-message">
        <h3>新房推荐</h3>
    </div>
    <ul class="tilelist">
    <#list newbuilds as builditem>
        <li><a href="/newhouse/getNewHouseDetails?id=${builditem['building_name_id']}">
            <div class="picture-box">
        <#assign imglist = builditem['building_imgs']>
                <img src="${staticurl}/${imglist[0]}" alt="${imglist[0]}">
            </div>
            <div class="tilelist-content">
                <h4 class="cont-first">${builditem['building_name']}</h4>
                <p class="cont-last">均价：<em>${builditem['average_price']}元</em>/㎡</p>
            </div>
        </a></li>
    </#list>
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
<script src="${staticurl}/js/plot-detail-map-message.js"></script>
</body>
</html>