<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${staticurl}/js/flexible.js"></script>
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${staticurl}/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="${staticurl}/css/new-detail.css">
    <title>新房详情</title>
    <script src="${staticurl}/js/jquery-2.1.4.min.js"></script>
    <script src="${staticurl}/js/echarts.js"></script>
</head>
<body>
<#assign ptCD0 = tradeline['buildingline']>;
<#assign ptCD1 = tradeline['arealine']>;
<#assign ptCD2 = tradeline['tradearealine']>;
<#assign mouthList = tradeline['mouthList']>;
<div class="carousel-box">
    <div class="swiper-container carousel-swiper" id="detail-swiper">
        <ul class="swiper-wrapper" id="house-pic-container">
            <#if build['building_imgs']?exists>
            <#list build['building_imgs']?split(",") as item>
            <#if item?exists>
                <#if item?? && item!= ''>
                    <li onclick="initphoto(this,${item_index})" class="swiper-slide">
                        <img src="${qiniuimage}/<#if item?exists>${item}</#if>" data-src="${qiniuimage}/<#if item?exists>${item}</#if>" alt="${build['building_name']}">
                    </li>
                <#else >
                    <li onclick="initphoto(this,0)" class="swiper-slide">
                        <img src="${staticurl}/images/global/tpzw_banner_image.png" data-src="${staticurl}/images/global/tpzw_banner_image.png" alt="拍摄中">
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
            <#if build['building_nickname']?exists><p>别名：${build['building_nickname']}</p></#if>
            <div class="primary-header-tag">
            <#if (build['building_tags']?exists)&&(build['building_tags']?size>0)>
                <#list build['building_tags'] as item>
                    <#if item?exists><span>${item}</span></#if>
                </#list>
            </#if>
            </div>
        </div>
        <ul class="primary-item">
            <li>
                <p>均价：<em class="high-light-red"><#if build['average_price']?exists>${build['average_price']}元/㎡<#else>售价待定</#if></em></p>
            </li>
            <li>
                <p>
                    地址：<#if build['district_name']?exists>[${build['district_name']}]</#if>
                           ${build['building_address']!'暂无'}
                    <a href="${router_city('/loupan/'+build['building_name_id']?c+'/map.html')}" class="primary-map-icon"></a>
                    <a href="${router_city('/loupan/'+build['building_name_id']?c+'/map.html')}" class="arrows-right"></a>
                </p>
                <p>
                    交通信息：<#if build['roundstation']?exists>
                        <#assign rounditems = build['roundstation']?split("$")>
                        <#assign x = rounditems[2]?number/1000>
                    距离${rounditems[1]!""}[${rounditems[0]!'暂无'}] <em>${x?string("0.##")}km</em>
                   <#else >暂无
                    </#if>
                </p>
            </li>
            <li>
                <p>最新开盘：${build['opened_time']!'暂无'}</p>
                <p>交房时间：${build['deliver_time']!'暂无'}</p>
                <p>售楼许可证：
                <#if (build['sell_licence']?exists)&&(build['sell_licence']?size>0)>
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
<div class="module-bottom-fill">
    <section>
        <div class="module-header-message">
            <h3>楼盘描述</h3>
            <a href="${router_city('/loupan/'+build['building_name_id']?c+'/desc.html')}" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <dl class="module-table-item">
            <dt>开发商：${build['developers']!'暂无'}</dt>
            <dd class="odd-item">物业类型：<span>${build['property_type']!'暂无'}</span></dd>
            <dd class="even-item">建筑类型：<em>${build['building_type']!'暂无'}</em></dd>
            <dd class="odd-item">产权年限：<em><#if build['building_life']?exists>${build['building_life']}年<#else>暂无</#if></em></dd>
            <dd class="even-item">车位配比：<em>${build['park_radio']!'暂无'}</em></dd>
        </dl>
    </section>
</div>
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
                                <img src="${qiniuimage}/${layoutimgs[0]}" alt="户型图">
                                <#else><img src="${staticurl}/images/newhouse/huxing_img.png" alt="户型图">
                            </#if>
                            <span class="sale-state">在售</span>
                        </div>
                        <div class="tilelist-content">
                            <p class="cont-first"><span>${item['room']!'暂无'}室${item['hall']!'暂无'}厅${item['toilet']!'暂无'}卫</span><span>${item['building_area']!'暂无'}㎡</span></p>
                            <#--<h4 class="cont-last">均价：${item['reference_price']+"元/㎡"!'暂无'}</h4>-->
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
            <h3>配套地图</h3>
            <a href="${router_city('/loupan/'+build['building_name_id']?c+'/map.html')}" class="more-arrows"><i class="arrows-right"></i></a>
        </div>
        <a href="${router_city('/loupan/'+build['building_name_id']?c+'/map.html')}" class="detail-map">
            <i class="map-marker-icon"></i>
            <#if build['location']?exists>
                <#assign locations = build['location']?split(",")>
                <img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=${locations[1]},${locations[0]}&&zoom=16" alt="">
            <#else ><img src="http://api.map.baidu.com/staticimage/v2?ak=UrflQIXBCuEZUVkwxgC3xE5y8rRPpjpS&width=700&height=350&center=116.382001,39.913329&&zoom=16" alt="">
            </#if>
        </a>
    </section>
</div>
<#if  (mouthList?size>0)>
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
</#if>
<section>
    <div class="module-header-message">
        <h3>看了本楼盘的用户还看了</h3>
    </div>
    <ul class="tilelist">
    <#if nearbybuild?exists>
    <#list nearbybuild as nearitem>
        <li>
            <a href="${router_city('/loupan/'+nearitem['building_name_id']?c+'.html')}">
                <div class="picture-box">
                    <#if nearitem['building_imgs']?exists>
                    <#assign imgt = nearitem['building_imgs']?split(",")>
                        <#if imgt[0]?? && imgt[0] != ''><img src="${qiniuimage}/${imgt[0]}" alt="${nearitem['building_name']}">
                            <#else ><img src="${staticurl}/images/global/tpzw_image.png" alt="拍摄中">
                        </#if>
                    </#if>
                </div>
                <div class="tilelist-content">
                    <p class="cont-first">${nearitem['building_name']!'暂无'}</p>
                    <p class="cont-center"><span>${nearitem['district_name']!'暂无'}</span><span>${nearitem['area_name']!'暂无'}</span></p>
                    <h4 class="cont-last">均价：<em><#if nearitem['average_price']?exists>${nearitem['average_price']}元/㎡<#else >暂无</#if></em></h4>
                </div>
            </a>
        </li>
    </#list>
    </#if>
    </ul>
</section>
<div class="detail-contact-wrapper">
    <section class="detail-contact-box" id="detailContactState">
        <div class="detail-contact-content">
            <#--<a href="#" class="contact-share"><i></i>分享</a>-->
            <#--<a href="#" class="contact-collect"><i></i>收藏</a>-->
            <a href="tel:1234789" class="only contact-telephone-counseling">咨询售楼处</a>
        </div>
    </section>
</div>


<!-------- photoswipe -------->
<script src="${staticurl}/js/photoswipe.min.js"></script>
<script src="${staticurl}/js/photoswipe-ui-default.min.js"></script>
<script src="${staticurl}/js/swiper-3.4.2.min.js"></script>
<script src="${staticurl}/js/main.js"></script>
<script>
    var myChartline = echarts.init(document.getElementById('newhousetrad'));
    option = {
        brushLink:null,
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['楼盘价格','区域价格','商圈价格']
        },
        textStyle: {
            fontSize: 28
        },
        xAxis:  {
            type: 'category',
            boundaryGap: false,
            data: [<#list  mouthList as item >'${item}',</#list>]
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}'
            }
        },
        series: [
            {
            name:'楼盘价格',
            type:'line',
            data:[<#list ptCD0 as item ><#if item['price']?number != 0>${item['price']}<#else>NaN</#if>,</#list>],
            symbolSize:14,
        },
            {
                name:'区域价格',
                type:'line',
                data:[<#list ptCD1 as item ><#if item['price']?number != 0>${item['price']}<#else>NaN</#if>,</#list>],
                symbolSize:14,
            },
        ]
    };
    myChartline.setOption(option);
</script>
</body>
</html>
